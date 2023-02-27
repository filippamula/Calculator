import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Calculator {
    public void run(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj wyrażenie do obliczenia");
        String input = sc.next();
        Queue<String> onp = toOnp(input);

        Stack<Float> stack = new Stack<>();
        for(var x : onp){
            if(isNumeric(x)){
              stack.push(Float.parseFloat(x));
            }
            if(isOperator(x)){
                float b = stack.pop();
                float a = stack.pop();

                stack.push(new Equation(a,b).calculate(Operation.of(x)));
            }
        }
        System.out.println(stack.pop());
    }

    public static Queue<String> toOnp(String equation){
        StringBuilder number = new StringBuilder();
        Queue<String> exit = new LinkedList<>();
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < equation.length(); i++){
            if(isNumeric(equation.charAt(i)) || equation.charAt(i) == '.'){
                number.append(equation.charAt(i));
            }
            if(isOperator(equation.charAt(i))){
                if(number.length() > 0){
                    exit.add(number.toString());
                    number.setLength(0);
                }

                if(!stack.empty()){
                    while (getPriority(stack.peek()) >= getPriority(equation.charAt(i))) {
                        exit.add(stack.pop().toString());
                        if(stack.empty()){
                            break;
                        }
                    }
                }
                stack.push(equation.charAt(i));
            }
            if(equation.charAt(i) == '('){
                stack.push(equation.charAt(i));
            }
            if(equation.charAt(i) == ')'){
                exit.add(number.toString());
                number.setLength(0);
                if(!stack.empty()){
                    while (stack.peek() != '('){
                        exit.add(stack.pop().toString());
                        if(stack.empty()){
                            break;
                        }
                    }
                }
            }
        }
        exit.add(number.toString());

        int size = stack.size();
        for (int i = 0; i < size; i++) {
            if (stack.peek() == '(' || stack.peek() == ')') {
                stack.pop();
                continue;
            }
            exit.add(String.valueOf(stack.pop()));
        }

        return exit;
    }

    public static int getPriority(char operator){
        return switch (operator) {
            case 'r', '(' -> 0;
            case '+', '-', ')' -> 1;
            case '*', '/' -> 2;
            default -> 100;
        };
    }
    public static boolean isNumeric(char value) {
        try{
            Integer.parseInt(String.valueOf(value));
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public static boolean isNumeric(String value) {
        try{
            Integer.parseInt(value);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public static boolean isOperator(char value){
        try{
            Operation.of(String.valueOf(value));
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public static boolean isOperator(String value){
        try{
            Operation.of(value);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}

