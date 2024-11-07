import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

public class EmployeeDirectoryGUI {
    private final EmployeeDirectory directory;

    // Компоненты GUI
    private JTextField idField;
    private JTextField phoneField;
    private JTextField nameField;
    private JTextField experienceField;
    private JTextArea outputArea;
    private JTextField searchExperienceField;
    private JTextField searchNameField;

    public EmployeeDirectoryGUI() {
        directory = new EmployeeDirectory();
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Телефонный справочник");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(new BorderLayout());

        // Входная панель
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2));

        inputPanel.add(new JLabel("Табельный номер:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Номер телефона:"));
        phoneField = new JTextField();
        inputPanel.add(phoneField);

        inputPanel.add(new JLabel("Имя:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Стаж:"));
        experienceField = new JTextField();
        inputPanel.add(experienceField);

        JButton addButton = new JButton("Добавить сотрудника");
        addButton.addActionListener(new AddEmployeeListener());
        inputPanel.add(addButton);

        frame.add(inputPanel, BorderLayout.NORTH);

        // Панель поиска
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(3, 2));

        searchPanel.add(new JLabel("Стаж для поиска:"));
        searchExperienceField = new JTextField();
        searchPanel.add(searchExperienceField);

        JButton searchExperienceButton = new JButton("Поиск по стажу");
        searchExperienceButton.addActionListener(new SearchExperienceListener());
        searchPanel.add(searchExperienceButton);

        searchPanel.add(new JLabel("Имя для поиска:"));
        searchNameField = new JTextField();
        searchPanel.add(searchNameField);

        JButton searchNameButton = new JButton("Получить телефон");
        searchNameButton.addActionListener(new SearchNameListener());
        searchPanel.add(searchNameButton);

        frame.add(searchPanel, BorderLayout.CENTER);

        // Область вывода
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        frame.add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private class AddEmployeeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String id = idField.getText();
            String phone = phoneField.getText();
            String name = nameField.getText();
            int experience;

            try {
                experience = Integer.parseInt(experienceField.getText());
                directory.addEmployee(id, phone, name, experience);
                outputArea.append("Сотрудник добавлен: " + name + "\n");
            } catch (NumberFormatException ex) {
                outputArea.append("Ошибка: стаж должен быть числом.\n");
            }
        }
    }

    private class SearchExperienceListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int experience;

            try {
                experience = Integer.parseInt(searchExperienceField.getText());
                List<Employee> employees = directory.findByExperience(experience);
                if (employees.isEmpty()) {
                    outputArea.append("Нет сотрудников с таким стажем.\n");
                } else {
                    outputArea.append("Сотрудники с " + experience + " годами стажа:\n");
                    for (Employee employee : employees) {
                        outputArea.append(employee + "\n");
                    }
                }
            } catch (NumberFormatException ex) {
                outputArea.append("Ошибка: стаж должен быть числом.\n");
            }
        }
    }

    private class SearchNameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = searchNameField.getText();
            List<String> phones = directory.getPhoneByName(name);
            if (phones.isEmpty()) {
                outputArea.append("Нет сотрудников с именем " + name + ".\n");
            } else {
                outputArea.append("Номера телефонов для " + name + ":\n");
                for (String phone : phones) {
                    outputArea.append(phone + "\n");
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EmployeeDirectoryGUI::new);
    }
}
