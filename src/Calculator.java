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
            Onp onp = new Onp(equation);

            pw.println(equation + " = " + onp.getResult());
        }
        pw.close();
    }
}

