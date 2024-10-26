import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class soxml {
    private DefaultTableModel model;
    private String file;

    public soxml (DefaultTableModel model, String file){
        this.model = model;
        this.file = file;
    }

    public void saveToXml() {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.newDocument();
            // Создание корневого элемента clothinglist и добавление его в документ
            Node clothinglist = doc.createElement("clothinglist");
            doc.appendChild(clothinglist);
            // Создание дочерних элементов clothing и присвоение значений атрибутам
            for (int i = 0; i < model.getRowCount(); i++) {
                Element clothing = doc.createElement("clothing");
                clothinglist.appendChild(clothing);
                clothing.setAttribute("type", (String) model.getValueAt(i, 0));
                clothing.setAttribute("colour", (String) model.getValueAt(i, 1));
                clothing.setAttribute("availability", (String) model.getValueAt(i, 2));
            }

            //Создание преобразователя документа
            Transformer trans = TransformerFactory.newInstance().newTransformer();
            //Создание файла для записи документа
            java.io.FileWriter fw = new FileWriter(file);
            //Запись документа в файл
            trans.transform(new DOMSource(doc), new StreamResult(fw));

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void openXml() {
        int rows = model.getRowCount();
        for (int i = 0; i < rows; i++) {
            model.removeRow(0);
        }
        try {
            //Создание парсера документа
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.parse(new File(file));
            //Нормализация документа
            doc.getDocumentElement().normalize();
            //Получение списка элементов с именем clothes
            NodeList nlClothes = doc.getElementsByTagName("clothing");
            //Цикл просмотра списка элементов и запись данных в таблицу
            for (int temp = 0; temp < nlClothes.getLength(); temp++) {
                //Выбор очередного элемента списка
                Node elem = nlClothes.item(temp);
                //Получение списка атрибутов элемента
                NamedNodeMap attrs = elem.getAttributes();
                //Чтение атрибутов
                String type = attrs.getNamedItem("type").getNodeValue();
                String colour = attrs.getNamedItem("colour").getNodeValue();
                String availability = attrs.getNamedItem("availability").getNodeValue();

                model.addRow(new String[]{type,colour,availability});
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        // Обработка ошибки парсера при чтении данных из XML-файла
        catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}