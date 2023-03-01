import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Onp {

    public Queue<String> Equation;
    public final float Result;
    public float getResult() {
        return Result;
    }
    public Onp(String equation) {

        this.Equation = ToOnpConverter(equation);
        this.Result = calculate();
    }
    private Queue<String> ToOnpConverter(String equation){
        StringBuilder number = new StringBuilder();
        Queue<String> exit = new LinkedList<>();
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < equation.length(); i++){
            if(Utility.isNumeric(equation.charAt(i)) || equation.charAt(i) == '.'){
                number.append(equation.charAt(i));
            }
            if(Utility.isOperator(equation.charAt(i))){
                if(number.length() > 0){
                    exit.add(number.toString());
                    number.setLength(0);
                }

                if(!stack.empty()){
                    while (Utility.getPriority(stack.peek()) >= Utility.getPriority(equation.charAt(i))) {
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
    private float calculate(){
        Stack<Float> stack = new Stack<>();
        for(var x : Equation){
            if(Utility.isNumeric(x)){
                stack.push(Float.parseFloat(x));
            }
            if(Utility.isOperator(x)){
                float b = stack.pop();
                float a = stack.pop();

                stack.push(new Equation(a,b).calculate(Operation.of(x)));
            }
        }

        return stack.pop();
    }

}
