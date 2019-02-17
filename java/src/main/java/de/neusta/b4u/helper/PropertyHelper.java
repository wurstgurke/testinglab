package de.neusta.b4u.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by malpen on 08.03.17.
 */
public class PropertyHelper {
    public static String getCustomProperty(String fileName, String property) {
        // define properties file
        File file = new File(fileName + ".properties");
        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties properties = new Properties();

        // load properties file
        try {
            properties.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // return selected property
        return properties.getProperty(property);
    }
}
