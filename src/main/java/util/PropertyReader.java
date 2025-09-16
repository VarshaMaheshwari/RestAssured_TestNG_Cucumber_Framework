package util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    Properties prop = new Properties();
    String propFilePath="./src/test/resources/properties/config.properties";

    public Properties readPropertyFile() {
        try {
            FileInputStream fis= new FileInputStream(propFilePath);
            prop.load(fis);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("File is missing, FileNotFoundException exception occurred");
        }
        return prop;
    }


}
