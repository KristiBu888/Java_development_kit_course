import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDirectory {
    // Коллекция сотрудников, где ключом является табельный номер, а значением — объект Employee
    private final Map<String, Employee> employees = new HashMap<>();

    // Метод добавления нового сотрудника
    public void addEmployee(String id, String phone, String name, int experience) {
        Employee employee = new Employee(id, phone, name, experience);
        employees.put(id, employee);
    }

    // Метод для поиска сотрудников по стажу
    public List<Employee> findByExperience(int experience) {
        List<Employee> result = new ArrayList<>();
        for (Employee employee : employees.values()) {
            if (employee.getExperience() == experience) {
                result.add(employee);
            }
        }
        return result;
    }

    // Метод для получения номера телефона по имени
    public List<String> getPhoneByName(String name) {
        List<String> phones = new ArrayList<>();
        for (Employee employee : employees.values()) {
            if (employee.getName().equals(name)) {
                phones.add(employee.getPhone());
            }
        }
        return phones;
    }

    // Метод для поиска сотрудника по табельному номеру
    public Employee findById(String id) {
        return employees.get(id);
    }

    // Вспомогательный метод для печати справочника сотрудников
    public void printDirectory() {
        employees.values().forEach(System.out::println);
    }

    // Внутренний класс Employee, представляющий данные о сотруднике
    private static class Employee {
        private final String id;
        private final String phone;
        private final String name;
        private final int experience;

        public Employee(String id, String phone, String name, int experience) {
            this.id = id;
            this.phone = phone;
            this.name = name;
            this.experience = experience;
        }

        public String getId() {
            return id;
        }

        public String getPhone() {
            return phone;
        }

        public String getName() {
            return name;
        }

        public int getExperience() {
            return experience;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "id='" + id + '\'' +
                    ", phone='" + phone + '\'' +
                    ", name='" + name + '\'' +
                    ", experience=" + experience +
                    '}';
        }
    }
}
