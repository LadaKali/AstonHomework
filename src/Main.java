public class Main {

    public static void main(String[] args) {
//Задание 1
        Dog dog = new Dog("Бобик");
        Cat[] cats = {
                new Cat("Мурзик"),
                new Cat("Барсик"),
                new Cat("Васька")
        };

        dog.run(400);
        dog.swim(5);
        cats[0].run(150);
        cats[0].swim(10);

        Bowl bowl = new Bowl(50);
        System.out.println("\nВ миске изначально " + bowl.getFood() + " еды");

        for (Cat cat : cats) {
            cat.eat(bowl, 20);
        }

        System.out.println("\nСытость котов:");
        for (Cat cat : cats) {
            System.out.println(cat.name + " сыт: " + cat.isFull());
        }

        bowl.addFood(30);
        System.out.println("В миске теперь " + bowl.getFood() + " еды");

        System.out.println("\nВсего животных: " + Animal.getAnimalCount());
        System.out.println("Собак: " + Dog.getDogCount());
        System.out.println("Котов: " + Cat.getCatCount());


//Задание 2
        Circle circle = new Circle(5, "Красный", "Черный");
        Rectangle rectangle = new Rectangle(4, 6, "Синий", "Белый");
        Triangle triangle = new Triangle(3, 4, 5, "Зеленый", "Желтый");

        System.out.println("Круг:");
        circle.printInfo();
        circle.printColorInfo();
        System.out.println();

        System.out.println("Прямоугольник:");
        rectangle.printInfo();
        rectangle.printColorInfo();
        System.out.println();

        System.out.println("Треугольник:");
        triangle.printInfo();
        triangle.printColorInfo();
    }
}
