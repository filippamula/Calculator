import java.util.Arrays;
import java.util.Scanner;

public class Calculator {
    public void run(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj pierwsza liczbę");
        float a = sc.nextFloat();
        System.out.println("Podaj drugą liczbe");
        float b = sc.nextFloat();
        System.out.println("Podaj operację");

        try {
            System.out.println(String.join(", ", Arrays.stream(Operation.values()).map(Operation::toString).toList()));
            Operation o =  Operation.of(sc.next());

            System.out.println(new Equation(a, b).calculate(o));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
