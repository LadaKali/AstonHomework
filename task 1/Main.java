abstract class Animal {
    protected String name;
    private static int animalCount = 0;

    public Animal (String name) {
        this.name = name;
        animalCount++;
    }

    public static int getAnimalCount() {
        return animalCount;
    }

    abstract void run (int distance);
    abstract void swim (int distance);
}

class Dog extends Animal {
    private static int dogCount = 0;
    private final int maxRun = 500;
    private final int maxSwim = 10;

    public Dog(String name) {
        super(name);
        dogCount++;

    }

    public static int getDogCount() {
        return dogCount;

    }

    @Override
    void run (int distance) {
        if (distance <= maxRun) {
            System.out.println(name + "пробежал " + distance + " м");}
        else {
            System.out.println(name + " не может проплыть " + distance + "м");
        }
    }
    @Override
    void swim(int distance) {
        if (distance <= maxSwim) {
            System.out.println(name + " проплыл " + distance + " м");}
        else {
            System.out.println(name + " не может проплыть " + distance + " м");
        }
    }
}

class Cat extends Animal {
    private static int catCount = 0;
    private final int maxRun = 200;
    private boolean isFull = false;

    public Cat(String name) {
        super(name);
        catCount++;
    }

    public static int getCatCount() {
        return catCount;
    }

    @Override
    void run(int distance) {
        if (distance <= maxRun) {
            System.out.println(name + "пробежал " + distance + " м");
        } else {
            System.out.println(name + " не может проплыть " + distance + "м");
        }
    }

    @Override
    void swim (int distance) {
        System.out.println(name + " не умеет плавать");
    }
    public boolean eat(Bowl bowl, int amount) {
        if (bowl.getFood() >= amount) {
            bowl.decreaseFood(amount);
            isFull = true;
            System.out.println(name + " покушал " + amount + " еды");
            return true;
        } else {
            System.out.println(name + " не стал есть, так как еды в миске недостаточно");
            return false;
        }
    }
    public boolean isFull() {
        return isFull;
    }
}

class Bowl {
    private int food;

    public Bowl(int food) {
        this.food = Math.max(food, 0);
    }
    public void decreaseFood (int amount) {
        if (amount > 0 && food>= amount) {
            food-=amount;
        }
    }
    public void addFood(int amount) {
        if (amount > 0) {
            food += amount;
            System.out.println("Добавлено " + amount + " еды в миске");
        }
    }

    public int getFood() {
        return food;
    }
}
public class Main {
    public static void main(String[] args) {
        // Создаем животных
        Dog dog = new Dog("Бобик");
        Cat[] cats = {
                new Cat("Мурзик"),
                new Cat("Барсик"),
                new Cat("Васька")
        };

        // Тестируем бег и плавание
        dog.run(400);
        dog.swim(5);
        cats[0].run(150);
        cats[0].swim(10);

        // Создаем миску и тестируем еду
        Bowl bowl = new Bowl(50);
        System.out.println("\nВ миске изначально " + bowl.getFood() + " еды");

        // Коты пытаются покушать
        for (Cat cat : cats) {
            cat.eat(bowl, 20);
        }

        // Выводим информацию о сытости
        System.out.println("\nСытость котов:");
        for (Cat cat : cats) {
            System.out.println(cat.name + " сыт: " + cat.isFull());
        }

        // Добавляем еду в миску
        bowl.addFood(30);
        System.out.println("В миске теперь " + bowl.getFood() + " еды");

        // Выводим статистику
        System.out.println("\nВсего животных: " + Animal.getAnimalCount());
        System.out.println("Собак: " + Dog.getDogCount());
        System.out.println("Котов: " + Cat.getCatCount());
    }
}
