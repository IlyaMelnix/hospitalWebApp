package by.bsu.melnik.hospital.manager;

import java.util.ResourceBundle;

public class ConfigurationManager {
    private static final ResourceBundle resourseBundle =
            ResourceBundle.getBundle("config");

    // Класс извлекает информацию из файла config.properties

    private ConfigurationManager() {}
    public static String getProperty(String key){
        return resourseBundle.getString(key);
    }
}
