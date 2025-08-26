package util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    Properties prop = new Properties();

    String propFilePath="src/test/resources/properties/config.properties";

    public void readPropertyFile() {
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream("properties/config.properties");
            prop.load(input);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("File is missing, FileNotFoundException exception occurred");
        }
    }
        public String getPropertyval(String key){
        String val= prop.getProperty(key);
        return val;
    }

}
