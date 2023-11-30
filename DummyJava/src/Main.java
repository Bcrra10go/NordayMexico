import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

// Press ⇧ twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        File file = new File("src/norday.tsv");
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        NordayApp app = NordayApp.read(fileScanner);

        menu(app);
    }

    private static void menu(NordayApp app) {
        Scanner input = new Scanner(System.in);
        boolean terminate = false;

        while (!terminate) {
            System.out.println("Porfavor elige:\n" +
                    "1 - Ver productos con filtro actual\n" +
                    "2 - Filtrar por producto\n" +
                    "3 - Filtrar por modelo\n" +
                    "4 - Cambiar orden\n" +
                    "5 - Restaurar filtros\n" +
                    "6 - Terminate program");

            String choice = input.nextLine();
            switch (choice) {
                case "1" -> app.print();
                case "2" -> app.filterProduct(input);
                case "3" -> app.filterModel(input);
                case "4" -> app.sort(input);
                case "5" -> app.resetFilters();
                case "6" -> terminate = true;
            }
        }
    }
}