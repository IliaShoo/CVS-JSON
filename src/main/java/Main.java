import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};

        String fileName = "data.csv";

        CSVReader reader = new CSVReader(new FileReader(fileName));
        ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
        strategy.setType(Employee.class);
        strategy.setColumnMapping(columnMapping);

        CsvToBean<Employee> parseCSV = new CsvToBeanBuilder<Employee>(reader)
                .withMappingStrategy(strategy)
                .build()
;
        List<Employee> list = parseCSV.parse();
        reader.close();

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Type listType = new TypeToken<List<Employee>>() {}.getType();
        String json = gson.toJson(list, listType);

        File file =new File("C:/Users/HP/Desktop/Java/CSV,XML,JSON","data.json");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(json);
        fileWriter.close();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder1 = factory.newDocumentBuilder();
        Document doc = builder1.parse(new File("C:/Users/HP/Desktop/Java/CSV,XML,JSON","data.json"));

        Node root = doc.getDocumentElement();
        NodeList nodeList = root.getChildNodes();
        for(int i =0; i< nodeList.getLength(); i++){
            Node node_ = nodeList.item(i);
            if(Node.ELEMENT_NODE == node_.getNodeType()){
                Element element = (Element) node_;
                NamedNodeMap map = element.getAttributes();
                for(int a =0; a< map.getLength();a++){

                }
            }
        }



    }


}
