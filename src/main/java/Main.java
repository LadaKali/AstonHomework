public class Main {
    public static void main(String[] args) {
        System.out.println("Факториал из 5: " + Factorial.calculateFactorial(5));

        System.out.println("Площадь треугольника (3,4,5): " + Triangle.calculateArea(3, 4, 5));

        System.out.println("Сложение 2+3: " + Arithmetic.add(2, 3)); // 5
        System.out.println("Вычитание 2-3: " + Arithmetic.subtract(2, 3));
        System.out.println("Умножение 2*3: " + Arithmetic.multiply(2, 3));
        System.out.println("Деление 6/3: " + Arithmetic.divide(6, 3));

        System.out.println("Сравнение 5,3: " + NumberComparison.compare(5, 3));
    }
}