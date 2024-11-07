import java.util.ArrayList;
import java.util.List;

public class EmployeeDirectory {
    private final List<Employee> employees;

    public EmployeeDirectory() {
        employees = new ArrayList<>();
    }

    public void addEmployee(String id, String phone, String name, int experience) {
        employees.add(new Employee(id, phone, name, experience));
    }

    public List<Employee> findByExperience(int experience) {
        List<Employee> foundEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getExperience() == experience) {
                foundEmployees.add(employee);
            }
        }
        return foundEmployees;
    }

    public List<String> getPhoneByName(String name) {
        List<String> phones = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getName().equalsIgnoreCase(name)) {
                phones.add(employee.getPhone());
            }
        }
        return phones;
    }
}
