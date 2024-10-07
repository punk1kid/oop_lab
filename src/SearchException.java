import javax.swing.*;

public class SearchException extends RuntimeException {
    public SearchException(String message) {
        super(message);
    }

    public void checkSearch(JTextField prod, JComboBox selCol) throws SearchException, NullPointerException {
        String SelectedProduct = prod.getText();
        String selectedColor = (String) selCol.getSelectedItem();
        // Проверка, если поле содержит текст по умолчанию или пустое
        if (SelectedProduct.contains("Тип одежды")) {
            throw new SearchException("Вы не ввели тип одежды для поиска");
        }
        if (SelectedProduct.length() == 0) {
            throw new NullPointerException("Поле не должно быть пустым");
        }
        // Проверка, если выбранный цвет является значением по умолчанию
        if (selectedColor.equals("Цвет")) {
            throw new SearchException("Вы не выбрали цвет для поиска");
        }
    }
}
