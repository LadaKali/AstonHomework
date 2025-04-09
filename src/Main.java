public class Main {
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

        Park amusementPark = new Park("Веселый Город", 3);

        amusementPark.addAttraction(0, "Колесо обозрения", "10:00-20:00", 500.0);
        amusementPark.addAttraction(1, "Американские горки", "11:00-19:00", 700.0);
        amusementPark.addAttraction(2, "Башня падения", "12:00-18:00", 600.0);

        amusementPark.displayParkInfo();
    }
}
