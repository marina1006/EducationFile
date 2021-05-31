import java.util.*;

public class Main {

    public static final String STAFF_TXT = "data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        sortBySalaryAndAlphabet(staff);
        for (Employee employee : staff) {
            System.out.println(employee);
        }

    }

    public static void sortBySalaryAndAlphabet(List<Employee> staff) {
        //TODO Метод должен отсортировать сотрудников по заработной плате и алфавиту.
        Collections.sort(staff, (o1, o2) -> {

            if (o1.getSalary().equals(o2.getSalary())) {

                Comparator.comparing(Employee::getName);
                return o1.getName().compareTo(o2.getName());
            }

            return o1.getSalary().compareTo(o2.getSalary());

        });
    }

}
