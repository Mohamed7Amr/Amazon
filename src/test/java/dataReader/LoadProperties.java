package dataReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoadProperties {

    private static Properties loadPropertiesData(String propertiesFilePath) throws IOException {
        FileInputStream fis = new FileInputStream(propertiesFilePath);
        Properties pro = new Properties();
        pro.load(fis);
        System.out.println(pro.getProperty("browser"));
        return pro;
    }

    public static Properties envConfig;

    static {
        try {
            envConfig = loadPropertiesData("src/main/java/properties/environmentConfig.properties");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Properties userTestData;

    static {
        try {
            userTestData = loadPropertiesData("src/main/java/properties/userData.properties");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
