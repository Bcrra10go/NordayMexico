import java.util.Objects;

public class ThermalMug {
    private String product;
    private Model model;
    private String color;
    private int quantity;
    private int price;
    private int sold;

    /**
     * Constructor for ThermalMug
     * @param product
     * @param model
     * @param color
     * @param quantity
     * @param price
     * @param sold
     */
    public ThermalMug(String product, Model model, String color, int quantity, int price, int sold) {
        this.product = product;
        this.model = model;
        this.color = color;
        this.quantity = quantity;
        this.price = price;
        this.sold = sold;
    }


    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    @Override // str.substring(0, 1).toUpperCase() + str.substring(1)
    public String toString() {
        return  product + '\n' +
                "Modelo: " + model.toString().substring(0, 1) + model.toString().substring(1).toLowerCase() +
                ", Color: " + color +
                ", Cantidad: " + quantity +
                ", Precio: " + price +
                ", Vendidos: " + sold +
                ", Existencia: " + (quantity - sold) +
                ", Ingresos: " + (price * sold) + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThermalMug that = (ThermalMug) o;
        return price == that.price && sold == that.sold && Objects.equals(product, that.product) && model == that.model && Objects.equals(color, that.color) && Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, model, color, quantity, price, sold);
    }

    public static ThermalMug read(String nextLine) {
        String[] values = nextLine.split("\t");
        int price = castPrice(values[4]);
        return new ThermalMug(values[0],
                Enum.valueOf(Model.class, values[1].replace(" ", "").toUpperCase()),
                values[2],
                Integer.parseInt(values[3]),
                price,
                Integer.parseInt(values[7]));
    }

    public static int castPrice(String number){
        number = number.replace("$", "");
        number = number.replace(".00", "");
        number = number.replace(",", "");
        return Integer.parseInt(number);
    }
}
