import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Calculator {
    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj ścieżkę");
        String path = sc.next();

        File csvOutput = new File("wynik.csv");
        Scanner file;
        PrintWriter pw;
        try {
            file = new Scanner(new File(path));
            pw = new PrintWriter(csvOutput);
        } catch (FileNotFoundException e) {
            System.out.println("Błąd - nie znaleziono pliku");
            return;
        }

        while (file.hasNext()){
            String equation = file.next();
            float a = 0,b;
            Operation o = Operation.ADDITION;
            StringBuilder number = new StringBuilder();

            for (var item : equation.toCharArray()) {
                if (isOperator(item)){
                    o = Operation.of(String.valueOf(item));
                    try{
                        a = Float.parseFloat(number.toString());
                    }catch (NumberFormatException e){
                        System.out.println("Błąd - żle podana liczba");
                        return;
                    }
                    number.setLength(0);
                }

                number.append(item);
            }

            try {
                b = Float.parseFloat(number.toString());
            }catch (NumberFormatException e){
                System.out.println("Błąd - źle podana liczba");
                return;
            }


            pw.println(equation + "=" + new Equation(a,b).calculate(o));
        }
        pw.close();
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

