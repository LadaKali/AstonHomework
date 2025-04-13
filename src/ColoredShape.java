interface ColoredShape extends Shape {
    default void printColorInfo() {
        System.out.println("Цвет заливки: " + getFillColor());
        System.out.println("Цвет границы: " + getBorderColor());
    }
}
