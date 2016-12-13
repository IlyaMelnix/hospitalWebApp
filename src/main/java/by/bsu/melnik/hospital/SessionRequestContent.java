package by.bsu.melnik.hospital;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class SessionRequestContent {
    private HashMap<String, Object> requestAttributes;
    private HashMap<String, String[]> requestParameters;
    private HashMap<String, Object> sessionAttributes;

    public void extractValues(HttpServletRequest req){
        //
    }

    public void insertAttributes(HttpServletRequest req){
        //
    }
}
