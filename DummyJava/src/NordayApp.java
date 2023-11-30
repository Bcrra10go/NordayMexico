import java.util.*;

public class NordayApp {
    private List<ThermalMug> mugs;
    private List<ThermalMug> filteredMugs;

    public NordayApp(List<ThermalMug> mugs) {
        this.mugs = mugs;
        this.filteredMugs = mugs;
    }

    public static NordayApp read(Scanner fileScanner) {
        List<ThermalMug> mugs = new ArrayList<>();
        fileScanner.nextLine();
        while (fileScanner.hasNext()){
            String nextLine = fileScanner.nextLine();
            if(!nextLine.startsWith("\t")){
                mugs.add(ThermalMug.read(nextLine));
            }
        }

        return new NordayApp(mugs);
    }

    public void print() {
        for(ThermalMug mug : filteredMugs){
            System.out.println(mug);
        }

        if(filteredMugs.size() == 0){
            System.out.println("Ningun producto encontrado");
        }
    }

    public void filterProduct(Scanner input) {
        System.out.println("Porfavor escribe el nombre del producto:");
        String product = input.nextLine();
        filteredMugs = filteredMugs.stream()
                .filter(x -> x.getProduct().contains(product))
                .toList();
    }

    public void filterModel(Scanner input) {
        System.out.println("Porfavor escribe el nombre del modelo:");
        String model = input.nextLine().toUpperCase();
        filteredMugs = filteredMugs.stream()
                .filter(x -> x.getModel().equals(Enum.valueOf(Model.class, model)))
                .toList();
    }

    public void resetFilters(){
        filteredMugs = mugs;
    }

    public void sort(Scanner input) {
        System.out.println("Ordenar por:\n" +
                "1 - Producto\n" +
                "2 - Modelo\n" +
                "3 - Color\n" +
                "4 - Precio\n" +
                "5 - Ingresos\n" +
                "6 - Cantidad\n" +
                "7 - Vendidos\n" +
                "8 - Existencia");

        String choice = input.nextLine();
        switch (choice) {
            case "1" -> filteredMugs.sort((a, b) -> a.getProduct().compareTo(b.getProduct()));
            case "2" -> filteredMugs.sort((a, b) -> a.getModel().toString().compareTo(b.getModel().toString()));
            case "3" -> filteredMugs.sort((a, b) -> a.getColor().compareTo(b.getColor()));
            case "4" -> filteredMugs.sort(Comparator.comparingInt(ThermalMug::getPrice).reversed());
            case "5" -> filteredMugs.sort(Comparator.comparingInt(a -> a.getPrice() * (- a.getSold())));
            case "6" -> filteredMugs.sort(Comparator.comparingInt(ThermalMug::getQuantity).reversed());
            case "7" -> filteredMugs.sort(Comparator.comparingInt(ThermalMug::getSold).reversed());
            case "8" -> filteredMugs.sort(Comparator.comparingInt(a -> a.getSold() - a.getQuantity()));
        }
    }
}
