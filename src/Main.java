//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println("Задание 1:");
        TaskOne.printThreeWords();
        System.out.println("----");
        System.out.println("Задание 2:");
        TaskTwo.checkSumSign();
        System.out.println("----");
        System.out.println("Задание 3:");
        TaskThree.printColor();
        System.out.println("----");
        System.out.println("Задание 4:");
        TaskFour.compareNumbers();
        System.out.println("----");
        System.out.println("Задание 5:");
        System.out.println(TaskFive.checkSum(8, 2));
        System.out.println("----");
        System.out.println("Задание 6:");
        TaskSix.checkNumber(-5);
        System.out.println("----");
        System.out.println("Задание 7:");
        System.out.println(TaskSeven.checkNumber(-5));
        System.out.println("----");
        System.out.println("Задание 8:");
        TaskEight.stringAndInt("Hello", 3);
        System.out.println("----");
        System.out.println("Задание 9:");
        System.out.println(TaskNine.checkYear(200));
        System.out.println("----");
        System.out.println("Задание 10:");
        TaskTen.changeNumbers();
        System.out.println("----");
        System.out.println("Задание 11:");
        TaskEleven.array100();
        System.out.println("----");
        System.out.println("Задание 12:");
        TaskTwelve.numbersLessSix();
        System.out.println("----");
        System.out.println("Задание 13:");
        TaskThirteen.matrix();
        System.out.println("----");
        System.out.println("Задание 14:");
        System.out.println(Arrays.toString(TaskFourteen.createArray(5,5)));
        System.out.println("----");
    }
}

//Задание 1
class TaskOne {
    public static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }
}


//Задание 2
class TaskTwo {
    public static void checkSumSign() {
        int a = 1;
        int b = -1;

        if ((a + b) >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

}

//Задание 3
class TaskThree {
    public static void printColor() {
        int value = 1;

        if (value <= 0) {
            System.out.println("Красный");
        }
        if (value >= 1) {
            System.out.println("Жёлтый");
        }
        if (value >= 101) {
            System.out.println("Зелёный");
        }
    }
}

//Задание 4
class TaskFour {
    public static void compareNumbers() {
        int a = 1;
        int b = 2;

        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }
}

//Задание 5
class TaskFive {
    public static boolean checkSum(int a, int b) {
        int sum = a + b;
        if (sum >= 10 && sum <= 20) {
            return true;
        } else {
            return false;
        }
    }
}

//Задание 6
class TaskSix {
    public static void checkNumber(int a) {
        if (a >= 0) {
            System.out.println("Число положительное");
        } else {
            System.out.println("Число отрицательное");
        }
    }
}

//Задание 7
class TaskSeven {
    public static boolean checkNumber(int a) {
        if (a <= 0) {
            return true;
        } else {
            return false;
        }
    }
}

//Задание 8
class TaskEight {
    public static void stringAndInt(String text, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(text);
        }
    }
}

//Задание 9
class TaskNine {
    public static boolean checkYear(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            return true;
        } else {
            return false;
        }
    }
}

//Задание 10
class TaskTen {
    public static void changeNumbers() {
        int[] array = {0, 0, 1, 1, 1, 0};

        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                array[i] = 1;
            } else if (array[i] == 1) {
                array[i] = 0;
            }
        }
        System.out.println("Изменённый массив");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

}

//Задание 11
class TaskEleven {
    public static void array100() {
        int[] array = new int[100];

        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}

//Задание 12
class TaskTwelve {
    public static void numbersLessSix() {
        int[] array = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

        for (int i = 0; i < array.length; i++) {
            if (array[i] < 6) {
                array[i] = array[i] * 2;
            }
            System.out.print(array[i] + " ");

        }
    }
}

//Задание 13
class TaskThirteen {
    public static void matrix() {
        int[][] array = new int[5][5];

        for (int i = 0; i < array.length; i++) {
            array[i][i] = 1;
            array[i][5 - 1 - i] = 1;
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}


//Задание 14
class TaskFourteen {
    public static int[] createArray(int len, int initialValue) {
        int[] array = new int[len];
        Arrays.fill(array, initialValue);

        return array;
    }
}
