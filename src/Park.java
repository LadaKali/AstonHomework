// Задание 3
public class Park {
    private String parkName;
    private Attraction[] attractions;

    public Park(String parkName, int numberOfAttractions) {
        this.parkName = parkName;
        this.attractions = new Attraction[numberOfAttractions];
    }

    public void addAttraction(int index, String name, String workingHours, double price) {
        if (index >= 0 && index < attractions.length) {
            attractions[index] = new Attraction(name, workingHours, price);
        } else {
            System.out.println("Ошибка: неверный индекс для добавления аттракциона");
        }
    }

    public void displayParkInfo() {
        System.out.println("Парк: " + parkName);
        System.out.println("Аттракционы:");
        for (int i = 0; i < attractions.length; i++) {
            if (attractions[i] != null) {
                System.out.println("Аттракцион " + (i + 1) + ":");
                attractions[i].displayInfo();
            }
        }
    }

    public class Attraction {
        private String name;
        private String workingHours;
        private double price;

        public Attraction(String name, String workingHours, double price) {
            this.name = name;
            this.workingHours = workingHours;
            this.price = price;
        }

        public void displayInfo() {
            System.out.println("  Название: " + name);
            System.out.println("  Время работы: " + workingHours);
            System.out.println("  Стоимость: " + price);
            System.out.println("  -------------------");
        }
    }


}