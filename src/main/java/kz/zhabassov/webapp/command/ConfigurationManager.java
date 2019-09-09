package kz.zhabassov.webapp.command;

import java.util.ResourceBundle;

public class ConfigurationManager {
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("resourses.config");
    private ConfigurationManager(){ }
    public static String getString(String key){
        return RESOURCE_BUNDLE.getString(key);
    }
}
