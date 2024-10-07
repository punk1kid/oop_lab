import javax.swing.*;

public class Actions {
    private JFrame adminStore; // Передаем ссылку на главный компонент
    private JComboBox colours; //выпадающий список
    private JTextField product; //текстовое поле для ввода
    private SearchException exception = new SearchException("Ошибка ввода");

    public Actions(JFrame adminStore,JComboBox colours,JTextField product) {
        this.adminStore = adminStore;
        this.colours = colours;
        this.product = product;

    }

    public void showSearchResult() throws SearchException {
            try {
                //Проверка на исключения
                exception.checkSearch(product, colours);
                //Логика поиска
                JOptionPane.showMessageDialog(adminStore, "Результат поиска (тест)");
            } catch (SearchException ex){
                JOptionPane.showMessageDialog(adminStore, ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(adminStore, "Произошла ошибка: " + e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
    }


    public void saveAction() {
        JOptionPane.showMessageDialog(adminStore, "Сохранено успешно (тест)");
    }

    public void openFile() {
        JOptionPane.showMessageDialog(adminStore, "Выберите файл (тест)");
    }

    public void addField() {
        JOptionPane.showMessageDialog(adminStore, "Поле для ввода (тест)");
    }

    public void editField() {
        JOptionPane.showMessageDialog(adminStore, "Редактирование успешно (тест)");
    }

    public void deleteField() {
        JOptionPane.showMessageDialog(adminStore, "Удалено успешно (тест)");
    }

    public void printWindow() {
        JOptionPane.showMessageDialog(adminStore, "Окно печати (тест)");
    }


}


