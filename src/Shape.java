interface Shape {
    double calculateArea();
    double calculatePerimeter();

    default void printInfo() {
        System.out.println("Площадь: " + calculateArea());
        System.out.println("Периметр: " + calculatePerimeter());
    }

    String getFillColor();
    String getBorderColor();
}