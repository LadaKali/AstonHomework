public class Main{
    public static void main(String[] args) {

    String[][] correctArray = {
            {"1", "2", "3", "4"},
            {"5", "6", "7", "8"},
            {"9", "10", "11", "12"},
            {"13", "14", "15", "16"}
    };

    String[][] wrongDataArray = {
            {"1", "2", "3", "4"},
            {"5", "abc", "7", "8"},
            {"9", "10", "11", "12"},
            {"13", "14", "15", "16"}
    };

    String[][] wrongSizeArray = {
            {"1", "2", "3"},
            {"4", "5", "6"},
            {"7", "8", "9"}
    };

    try {
        System.out.println("Сумма элементов корректного массива: " + ArrayProcessor.processArray(correctArray));
    } catch (MyArraySizeException e) {
        System.out.println("Ошибка размера: " + e.getMessage());
    } catch (MyArrayDataException e) {
        System.out.println("Ошибка данных: " + e.getMessage());
    }

    try {
        System.out.println("Сумма элементов массива с неверными данными: " + ArrayProcessor.processArray(wrongDataArray));
    } catch (MyArraySizeException e) {
        System.out.println("Ошибка размера: " + e.getMessage());
    } catch (MyArrayDataException e) {
        System.out.println("Ошибка данных: " + e.getMessage());
    }

    try {
        System.out.println("Сумма элементов массива неверного размера: " + ArrayProcessor.processArray(wrongSizeArray));
    } catch (MyArraySizeException e) {
        System.out.println("Ошибка размера: " + e.getMessage());
    } catch (MyArrayDataException e) {
        System.out.println("Ошибка данных: " + e.getMessage());
    }

    try {
        String[][] array = new String[4][4];
        System.out.println(array[5][0]);
    } catch (ArrayIndexOutOfBoundsException e) {
        System.out.println("Ошибка доступа к массиву: " + e.getMessage());
    }
}
}