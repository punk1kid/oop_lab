import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.print.PrinterException;
import javax.swing.JTable;
import javax.swing.JOptionPane;

public class Actions {
    private JFrame adminStore; // Передаем ссылку на главный компонент
    private JComboBox colours; //выпадающий список
    private JTextField product; //текстовое поле для ввода
    private SearchException exception = new SearchException("Ошибка ввода");
    private DefaultTableModel model;
    private JTable clothing;

    public Actions(JFrame adminStore, JComboBox colours, JTextField product, DefaultTableModel model, JTable clothing) {
        this.adminStore = adminStore;
        this.colours = colours;
        this.product = product;
        this.model = model;
        this.clothing = clothing;
    }

    public void showSearchResult() throws SearchException {
        try {
            //Проверка на исключения
            exception.checkSearch(product, colours);
            //Логика поиска
            JOptionPane.showMessageDialog(adminStore, "Результат поиска (тест)");
        } catch (SearchException ex) { //высвечивание окон с ошибкой
            JOptionPane.showMessageDialog(adminStore, ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(adminStore, "Произошла ошибка: " + e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void saveAction() {
        /**
         * Ниже закомментирован НЕиспользуемый код для сохранения в txt
         */
        /*
        FileDialog save = new FileDialog(adminStore, "Сохранение данных", FileDialog.SAVE);
        save.setFile("*.txt"); // Тип файла для сохранения
        save.setDirectory("src"); // Директория по умолчанию
        save.setVisible(true); // Отобразить запрос пользователю
        String fileName = save.getDirectory() + save.getFile();// Выбранный каталог и имя файла
        if(fileName == null) return;// Если пользователь нажал отмена

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            for(int i=0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    writer.write((String) model.getValueAt(i, j));
                    writer.write("\t");
                }
                writer.write("\n");
            }
            writer.close();

        } catch (IOException e) { //ошибка записи в файл
            e.printStackTrace();
            JOptionPane.showMessageDialog(adminStore, "Ошибка сохранения файла");
        }
         */

        FileDialog save = new FileDialog(adminStore, "Сохранение данных", FileDialog.SAVE);
        save.setFile("*.xml"); // Тип файла для сохранения
        save.setDirectory("src"); // Директория по умолчанию
        save.setVisible(true); // Отобразить запрос пользователю
        String fileName = save.getDirectory() + save.getFile();// Выбранный каталог и имя файла
        if (fileName == null) return;// Если пользователь нажал отмена

        soxml savef = new soxml(model, fileName);
        savef.saveToXml();

    }

    public void openFile() {
        /**
         * Ниже закомментирован НЕиспользуемый код для открытия txt
         */
        /*
        FileDialog open = new FileDialog(adminStore, "Открыть файл", FileDialog.LOAD);
        open.setFile("*.txt");
        open.setDirectory("src");
        open.setVisible(true);
        String fileName = open.getDirectory() + open.getFile();
        if (fileName == null) return;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            int rows = model.getRowCount();
            for (int i = 0; i < rows; i++) {
                model.removeRow(0);
            }
            String line;
            while ((line = reader.readLine()) != null) {
                String[] rowData = line.split("\t");
                model.addRow(rowData);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(adminStore, "Файл не найден.");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(adminStore, "Ошибка открытия файла");
        }

 */

        TestThreads aaa = new TestThreads();
        aaa.main();



        FileDialog open = new FileDialog(adminStore, "Открыть файл", FileDialog.LOAD);
        open.setFile("*.xml"); // Тип файла для сохранения
        open.setDirectory("src"); // Директория по умолчанию
        open.setVisible(true); // Отобразить запрос пользователю
        String fileName = open.getDirectory() + open.getFile();// Выбранный каталог и имя файла
        if (fileName == null) return;// Если пользователь нажал отмена

        soxml openf = new soxml(model, fileName);
        openf.openXml();


    }

    public void addField() {
        JTextField typeField = new JTextField();
        JTextField colourField = new JTextField();
        JTextField availabilityField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.add(new JLabel("Тип одежды:")); panel.add(typeField);
        panel.add(new JLabel("Цвет:")); panel.add(colourField);
        panel.add(new JLabel("Наличие (Да/Нет):")); panel.add(availabilityField);

        if (JOptionPane.showConfirmDialog(null, panel, "Ввод данных", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            String type = typeField.getText(), colour = colourField.getText(), availability = availabilityField.getText();
            if (!type.isEmpty() && !colour.isEmpty() && !availability.isEmpty())
                model.addRow(new String[]{type, colour, availability});
            else
                JOptionPane.showMessageDialog(null, "Все поля должны быть заполнены!", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void editField() {
        int selectedRow = clothing.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) clothing.getModel();

            // Получаем текущие значения строки
            String currentType = (String) model.getValueAt(selectedRow, 0);
            String currentColour = (String) model.getValueAt(selectedRow, 1);
            String currentAvailability = (String) model.getValueAt(selectedRow, 2);

            // Создаем поля для редактирования с текущими значениями
            JTextField typeField = new JTextField(currentType);
            JTextField colourField = new JTextField(currentColour);
            JTextField availabilityField = new JTextField(currentAvailability);

            // Создаем панель для размещения полей
            JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
            panel.add(new JLabel("Тип одежды:"));
            panel.add(typeField);
            panel.add(new JLabel("Цвет:"));
            panel.add(colourField);
            panel.add(new JLabel("Наличие (Да/Нет):"));
            panel.add(availabilityField);

            // Показываем диалоговое окно для редактирования
            int result = JOptionPane.showConfirmDialog(null, panel, "Редактирование данных", JOptionPane.OK_CANCEL_OPTION);

            // Если пользователь нажал "ОК"
            if (result == JOptionPane.OK_OPTION) {
                String newType = typeField.getText();
                String newColour = colourField.getText();
                String newAvailability = availabilityField.getText();

                // Проверяем, что все поля заполнены
                if (!newType.isEmpty() && !newColour.isEmpty() && !newAvailability.isEmpty()) {
                    // Обновляем данные в модели таблицы
                    model.setValueAt(newType, selectedRow, 0);
                    model.setValueAt(newColour, selectedRow, 1);
                    model.setValueAt(newAvailability, selectedRow, 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Все поля должны быть заполнены!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Выберите строку для редактирования!", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteField()   {
        int selectedRow = clothing.getSelectedRow(); // Получаем индекс выбранной строки

        if (selectedRow != -1) {
            // Показываем окно подтверждения
            int confirmation = JOptionPane.showConfirmDialog(null, "Вы уверены, что хотите удалить выбранную строку?",
                    "Подтверждение удаления", JOptionPane.YES_NO_OPTION);

            // Если пользователь нажал "Да"
            if (confirmation == JOptionPane.YES_OPTION) {
                // Удаляем строку из модели таблицы
                ((DefaultTableModel) clothing.getModel()).removeRow(selectedRow);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Выберите строку для удаления!", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void printWindow() {
        try {
            // Вызываем метод печати таблицы
            boolean complete = clothing.print(JTable.PrintMode.FIT_WIDTH);

            // Проверяем, завершена ли печать успешно
            if (complete) {
                JOptionPane.showMessageDialog(null, "Печать завершена успешно!", "Печать", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Печать была отменена", "Печать", JOptionPane.WARNING_MESSAGE);
            }
        } catch (PrinterException e) {
            // Обработка исключений при печати
            JOptionPane.showMessageDialog(null, "Ошибка при печати: " + e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

}


