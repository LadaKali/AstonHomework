class Bowl {
    private int food;

    public Bowl(int food) {
        this.food = Math.max(food, 0);
    }

    public void decreaseFood(int amount) {
        if (amount > 0 && food >= amount) {
            food -= amount;
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
