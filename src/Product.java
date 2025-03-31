//Задания 1 и 2
class Product {
    private String name;
    private String manufactureDate;
    private String manufacturer;
    private String country;
    private double price;
    private boolean isReserved;

    public Product(String name, String manufactureDate, String manufacturer,
                   String country, double price, boolean isReserved) {
        this.name = name;
        this.manufactureDate = manufactureDate;
        this.manufacturer = manufacturer;
        this.country = country;
        this.price = price;
        this.isReserved = isReserved;
    }

    public void displayInfo() {
        System.out.println("Название: " + name);
        System.out.println("Дата производства: " + manufactureDate);
        System.out.println("Производитель: " + manufacturer);
        System.out.println("Страна: " + country);
        System.out.println("Цена: " + price);
        System.out.println("Состояние бронирования: " +
                (isReserved ? "Забронировано" : "Свободно"));
        System.out.println("------------------------");
    }

    public static void main(String[] args) {
        Product[] products = new Product[5];

        products[0] = new Product(
                "Смартфон",
                "2020-01-01",
                "Самсунг",
                "Китай",
                60000.0,
                true
        );
        products[1] = new Product(
                "Ноутбук",
                "2021-03-15",
                "Dell",
                "США",
                120000.0,
                false
        );
        products[2] = new Product(
                "Телевизор",
                "2022-06-20",
                "LG",
                "Южная Корея",
                45000.0,
                true
        );
        products[3] = new Product(
                "Холодильник",
                "2023-09-10",
                "Bosch",
                "Германия",
                80000.0,
                false
        );
        products[4] = new Product(
                "Наушники",
                "2024-02-14",
                "Sony",
                "Япония",
                15000.0,
                true
        );

        System.out.println("Список товаров:");
        for (int i = 0; i < products.length; i++) {
            System.out.println("Товар " + (i + 1) + ":");
            products[i].displayInfo();
        }
    }
}