//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Arrays;

public class Main {

    public static void main (String[] args) {
        System.out.println("Задание 1:");
        LessonOne.printThreeWords();
        System.out.println("----");

    }
}

//Задание 1
class LessonOne {
    public static void printThreeWords() {
        System.out.println("Orange");
        System.out.println ("Banana");
        System.out.println("Apple");
    }
//    public static void main (String[] args) {
//        printThreeWords();
//    }
}

//Задание 1
//public class Main {
//    public static void printThreeWords() {
//        System.out.println("Orange");
//        System.out.println ("Banana");
//        System.out.println("Apple");
//    }
//    public static void main (String[] args) {
//        printThreeWords();
//    }
//}


//Задание 2
//public class Main {
//    public static void checkSumSign() {
//        int a = 1;
//        int b = -1;
//
//        if ((a + b) >= 0) {
//            System.out.println("Сумма положительная");
//        } else {
//            System.out.println("Сумма отрицательная");
//        }
//    }
//
//    public static void main(String[] args)
//        {checkSumSign();}
//}


//Задание 3
//public class Main {
//    public static void printColor() {
//        int value = 1;
//
//        if (value <= 0)
//        {System.out.println("Красный");}
//        if (value >= 1)
//        {System.out.println("Жёлтый");}
//        if (value >= 101)
//        {System.out.println("Зелёный");}
//    }
//    public static void main(String[] args)
//    {printColor();}
//}

//Задание 4
//public class Main {
//    public static void compareNumbers(){
//        int a = 1;
//        int b = 2;
//
//        if (a >= b)
//        {System.out.println("a >= b");}
//        else
//        {System.out.println("a < b");}
//    }
//    public static void main(String[] args)
//    {compareNumbers();}
//}

//Задание 5
//public class Main {
//    public static boolean checkSum(int a, int b){
//        int sum = a + b;
//        if (sum >=10 && sum <=20)
//        {return true;}
//        else {return false;}
//    }
//    public static void main(String[] args){
//        System.out.println(checkSum(8, 2));
//    }
//}

//Задание 6
//public class Main {
//    public static void checkNumber(int a) {
//        if (a >= 0)
//        {System.out.println("Число положительное");}
//        else
//        {System.out.println("Число отрицательное");}
//    }
//    public static void main(String[] args) {
//        checkNumber(-5);
//    }
//}

//Задание 7
//public class Main {
//    public static boolean checkNumber2(int a) {
//        if (a <= 0)
//        {return true;}
//        else
//        {return false;}
//    }
//    public static void main(String[] args)
//    {System.out.println(checkNumber2(-5));}
//}

//Задание 8
//public class Main {
//    public static void stringAndInt(String text, int count) {
//        for (int i = 0; i < count; i++) {
//            System.out.println(text);
//        }
//    }
//    public static void main(String[] args) {
//        stringAndInt("Hello", 3);
//    }
//}

//Задание 9
//public class Main {
//    public static boolean checkYear(int year) {
//       if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))
//        {return true;}
//       else
//        {return false;}
//    }
//    public static void main(String[] args)
//    {System.out.println(checkYear(200));}
//}

//Задание 10
//public class Main {
//    public static void changeNumbers() {
//        int[] array = {0, 0, 1, 1, 1, 0};
//
//        for (int i = 0; i < array.length; i++) {
//            if (array[i] == 0) {
//                array[i] = 1;
//            } else if (array[i] == 1) {
//                array[i] = 0;
//            }
//        }
//        System.out.println("Изменённый массив");
//        for (int i = 0; i < array.length; i++) {
//            System.out.print(array[i] + " ");
//        }
//        System.out.println();}
//
//    public static void main(String[] args) {
//        changeNumbers();
//    }
//}

//Задание 11
//public class Main {
//    public static void array100() {
//        int[] array = new int[100];
//
//        for (int i = 0; i < array.length; i++) {
//            array[i] = i + 1;
//            System.out.print(array[i] + " ");
//        }
//        System.out.println();}
//    public static void main(String[] args){
//        array100();
//        }
//    }

//Задание 12
//public class Main {
//    public static void numbersLessSix() {
//        int[] array = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
//
//        for (int i = 0; i < array.length; i++) {
//            if (array[i] < 6) {
//                array[i] = array[i] * 2;
//            }
//            System.out.print(array[i] + " ");
//
//        }
//        }
//    public static void main (String[]args){
//        numbersLessSix();}
//    }

//Задание 13
//public class Main {
//    public static void matrix(){
//        int[][] array = new int[5][5];
//
//        for (int i = 0; i< array.length; i++) {
//            array[i][i] = 1;
//            array[i][5 - 1 - i] = 1;
//        }
//        for (int i = 0; i<array.length; i++) {
//            for (int j = 0; j < array[i].length; j++) {
//                System.out.print(array[i][j] + " ");
//            }
//            System.out.println();
//        }
//    }
//    public static void main(String[] args){
//        matrix();
//    }
//}


//Задание 14
//public class Main {
//    public static int[] createArray(int len, int initialValue) {
//        int[] array = new int[len];
//        Arrays.fill(array, initialValue);
//
//        return array;
//    }
//
//    public static void main(String[] args) {
//        int[] result = createArray(5, 5);
//
//        System.out.print(Arrays.toString(result));
//    }
//}
