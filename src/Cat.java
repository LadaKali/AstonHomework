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
    void swim(int distance) {
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
