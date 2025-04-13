import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        runTask1();
        runTask2();
    }

    public static void runTask1() {
        Set<Student> students = new HashSet<>();

        Map<String, Integer> grades1 = new HashMap<>();
        grades1.put("Математика", 4);
        grades1.put("Физика", 3);
        students.add(new Student("Иван Иванов", "ГР-1", 1, grades1));

        Map<String, Integer> grades2 = new HashMap<>();
        grades2.put("Математика", 2);
        grades2.put("Физика", 2);
        students.add(new Student("Петр Петров", "ГР-1", 1, grades2));

        Map<String, Integer> grades3 = new HashMap<>();
        grades3.put("Математика", 5);
        grades3.put("Физика", 4);
        students.add(new Student("Мария Сидорова", "ГР-2", 2, grades3));

        System.out.println("До обработки:");
        Student.printStudents(students, 1);

        Student.removeLowPerformers(students);

        Student.promoteStudents(students);

        System.out.println("\nПосле обработки:");
        Student.printStudents(students, 2);
    }

    public static void runTask2() {
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.add("Иванов", "+79123456789");
        phoneBook.add("Иванов", "+79876543210");
        phoneBook.add("Петров", "+79001234567");
        phoneBook.add("Сидоров", "+79998887766");

        System.out.println("Номера для Иванов: " + phoneBook.get("Иванов"));
        System.out.println("Номера для Петров: " + phoneBook.get("Петров"));
        System.out.println("Номера для Сидоров: " + phoneBook.get("Сидоров"));
        System.out.println("Номера для Смирнов: " + phoneBook.get("Смирнов"));
    }
}
