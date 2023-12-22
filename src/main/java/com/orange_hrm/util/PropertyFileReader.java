package com.orange_hrm.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader {
    static Properties prop = null;
    public static void main(String args[]) throws IOException {
        Properties prop = readPropertiesFile("credentials.properties");
        System.out.println("username: "+ prop.getProperty("username"));
        System.out.println("password: "+ prop.getProperty("password"));
    }

    public static Properties readPropertiesFile(String fileName) throws IOException {
        FileInputStream fis = null;
        try {
            if(prop == null) {
                fis = new FileInputStream(fileName);
                prop = new Properties();
                prop.load(fis);
            }
        } catch(FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        } finally {
            fis.close();
        }
        return prop;
    }
    public static String getProperty(String key){
        return prop.getProperty(key);
    }
}
