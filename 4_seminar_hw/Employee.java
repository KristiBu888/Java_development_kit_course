public class Employee {
    private final String id; // Табельный номер
    private final String phone; // Номер телефона
    private final String name; // Имя
    private final int experience; // Стаж

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
        return "Сотрудник{" +
                "ID='" + id + '\'' +
                ", Имя='" + name + '\'' +
                ", Стаж=" + experience +
                '}';
    }
}
