public class Utility {
    private Utility() {
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
    public static boolean isOperator(char value){
        try{
            Operation.of(String.valueOf(value));
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
    public static boolean isOperator(String value){
        try{
            Operation.of(value);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
