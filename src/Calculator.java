import java.util.Scanner;

public class Calculator {
    public void run(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj wyrażenie do obliczenia w kolejności wykonywania działań");
        String input = sc.next();

        StringBuilder number = new StringBuilder();
        float result = 0;
        Operation o = Operation.ADDITION;

        for (int i = 0; i < input.length(); i++){
            if(isNumeric(input.charAt(i))){
                number.append(input.charAt(i));
            }
            if (isOperator(input.charAt(i))){
                result = new Equation(result, Float.parseFloat(number.toString())).calculate(o);
                number.setLength(0);
                o = Operation.of(String.valueOf(input.charAt(i)));
            }
        }

        System.out.println(result);
    }

    public static boolean isNumeric(char value) {
        try{
            Integer.parseInt(String.valueOf(value));
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
}

