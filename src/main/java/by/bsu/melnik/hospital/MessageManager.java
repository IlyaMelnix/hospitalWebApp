package by.bsu.melnik.hospital;

import java.util.ResourceBundle;

public class MessageManager {
    private final static ResourceBundle resourseBundle =
            ResourceBundle.getBundle("messages");

    // класс извлекает информацию из файла messages.properties
    private MessageManager(){}

    public static String getProperty(String key){
        return resourseBundle.getString(key);
    }
}
