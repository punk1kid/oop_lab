import java.awt.*;
import java.awt.event.*; //Импорт библиотеки обрабатывающей события
//Подключение графических библиотек
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class Main {
    //Объявление графических компонентов
    private JFrame adminStore;
    private DefaultTableModel model; //Модель данных для таблицы, которая хранит отображаемые данные
    private JButton save; //кнопка сохранить
    private JButton open;
    private JButton add;
    private JButton edit;
    private JButton delete;
    private JButton print;
    ////
    private JToolBar toolBar; //панель инструментов
    private JScrollPane scroll; //прокрутка страницы
    private JTable clothing; //сама таблица
    private JComboBox colours; //выпадающий список
    private JTextField product; //текстовое поле для ввода
    private JButton filter; //кнопка для применения фильтра на основе введенных данных
    public void show() {
        //Создание окна
        adminStore = new JFrame("Список товаров"); // название окна
        adminStore.setSize(500,300); //размер окна
        adminStore.setLocation(400,200); //отступ от верхнего левого угла экрана, где создастся новое окно
        adminStore.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //действие при нажатии на крестик - выход из программы
        //Создание кнопок и прикрепление иконок

        save = new JButton(new ImageIcon("src\\save.png"));
        open = new JButton(new ImageIcon("src\\open.png"));
        add = new JButton(new ImageIcon("src\\add.png"));
        edit = new JButton(new ImageIcon("src\\edit.png"));
        delete = new JButton(new ImageIcon("src\\delete.png"));
        print = new JButton(new ImageIcon("src\\print.png"));

        //Настройка подсказок для кнопок
        save.setToolTipText("Сохранить (Ctrl+S)");
        open.setToolTipText("Открыть из файла (Ctrl+O)");
        add.setToolTipText("Добавить новую позицию (Ctrl+A)");
        edit.setToolTipText("Редактировать (Ctrl+E)");
        delete.setToolTipText("Удалить позицию (Ctrl+D)");
        print.setToolTipText("Распечатать таблицу (Ctrl+P)");

        //Добавление кнопок на панель инструментов
        toolBar = new JToolBar("Панель инструментов");
        toolBar.add(save);
        toolBar.add(open);
        toolBar.add(add);
        toolBar.add(edit);
        toolBar.add(delete);
        toolBar.add(print);

        //Размещение панели инструментов
        adminStore.setLayout(new BorderLayout());
        adminStore.add(toolBar,BorderLayout.NORTH);

        //Создание таблицы с данными
        String[] columns = {"Тип", "Цвет", "Наличие"};
        String[][] data = {};
        model = new DefaultTableModel(data,columns);
        clothing = new JTable(model);
        scroll = new JScrollPane(clothing);

        //размещение таблицы с данными
        adminStore.add(scroll,BorderLayout.CENTER);

        //Подготовка компонентов списка
        colours = new JComboBox(new String[]{"Цвет","Синий","Красный"});
        product = new JTextField("Тип одежды");
        filter = new JButton("Поиск");
        //Добавление компонентов на панель
        JPanel filterPanel = new JPanel();
        filterPanel.add(colours);
        filterPanel.add(product);
        filterPanel.add(filter);

        //Размещение панели поиска в нижней части окна
        adminStore.add(filterPanel, BorderLayout.SOUTH);

        Listeners();


        //Визуализация экранной формы
        adminStore.setVisible(true);
    }


    private void Listeners() {
        Actions buttonActions = new Actions(adminStore, colours, product, model, clothing);
        filter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                buttonActions.showSearchResult();
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                buttonActions.saveAction();
            }
        });

        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                buttonActions.openFile();
            }
        });

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                buttonActions.addField();
            }
        });

        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                buttonActions.editField();
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                buttonActions.deleteField();
            }
        });

        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                buttonActions.printWindow();
            }
        });

        // Привязка горячих клавиш
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_PRESSED) {
                    if (e.getKeyCode() == KeyEvent.VK_S && e.isControlDown()) {
                        buttonActions.saveAction();
                        return true;
                    }
                    if (e.getKeyCode() == KeyEvent.VK_O && e.isControlDown()) {
                        buttonActions.openFile();
                        return true;
                    }
                    if (e.getKeyCode() == KeyEvent.VK_A && e.isControlDown()) {
                        buttonActions.addField();
                        return true;
                    }
                    if (e.getKeyCode() == KeyEvent.VK_E && e.isControlDown()) {
                        buttonActions.editField();
                        return true;
                    }
                    if (e.getKeyCode() == KeyEvent.VK_D && e.isControlDown()) {
                        buttonActions.deleteField();
                        return true;
                    }
                    if (e.getKeyCode() == KeyEvent.VK_P && e.isControlDown()) {
                        buttonActions.printWindow();
                        return true;
                    }
                    if (e.getKeyCode() == KeyEvent.VK_F && e.isControlDown()) {
                        buttonActions.showSearchResult();
                        return true;
                    }
                }
                return false;
            }
        });

    }

    public static void main(String[] args){
        //Создание и отображение экранной формы
        new Main().show();
    }
}

