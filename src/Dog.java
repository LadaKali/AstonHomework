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
    void run(int distance) {
        if (distance <= maxRun) {
            System.out.println(name + "пробежал " + distance + " м");
        } else {
            System.out.println(name + " не может проплыть " + distance + "м");
        }
    }

    @Override
    void swim(int distance) {
        if (distance <= maxSwim) {
            System.out.println(name + " проплыл " + distance + " м");
        } else {
            System.out.println(name + " не может проплыть " + distance + " м");
        }
    }
}
