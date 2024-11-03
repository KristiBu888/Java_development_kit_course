public class Main {
    public static void main(String[] args) {
        EmployeeDirectory directory = new EmployeeDirectory();

        // Добавление сотрудников в справочник
        directory.addEmployee("001", "123456789", "Иван", 5);
        directory.addEmployee("002", "987654321", "Анна", 3);
        directory.addEmployee("003", "555666777", "Пётр", 5);
        directory.addEmployee("004", "444555666", "Анна", 10);

        // Поиск сотрудников по стажу
        System.out.println("Сотрудники с 5 годами стажа:");
        System.out.println(directory.findByExperience(5));

        // Получение номеров телефонов по имени
        System.out.println("Телефоны сотрудников с именем Анна:");
        System.out.println(directory.getPhoneByName("Анна"));

        // Поиск сотрудника по табельному номеру
        System.out.println("Сотрудник с табельным номером '001':");
        System.out.println(directory.findById("001"));

        // Печать всего справочника
        System.out.println("\nПолный список сотрудников:");
        directory.printDirectory();
    }
}

