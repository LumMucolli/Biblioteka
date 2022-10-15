package com.probitacademy.biblioteka.ui.util;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import com.probitacademy.biblioteka.domain.User;
public class AppProperties {
public static String username;
public static String password;
public static String DB_USERNAME;
public static String DB_PASSWORD;
public static String DB_HOST;
// useri aktual qe ka hy ne sistem
public static User LOGGED_IN_USER;
static {
//bllok static per inicializim
Properties properties = getProperties();
username = properties.getProperty("username");
password = properties.getProperty("password");
DB_USERNAME = properties.getProperty("DB_USERNAME");
DB_PASSWORD = properties.getProperty("DB_PASSWORD");
DB_HOST = properties.getProperty("DB_HOST");
}
public static Properties getProperties() {
Properties properties = new Properties();
try {
properties.load(new FileReader(new File("settings.ini")));
} catch (IOException e) {
properties = defaultProperties();
}
return properties;
}
public static void storeProperties() {
Properties prop = new Properties();
prop.setProperty("username", username);
prop.setProperty("password", password);
prop.setProperty("DB_USERNAME", DB_USERNAME);
prop.setProperty("DB_PASSWORD", DB_PASSWORD);
prop.setProperty("DB_HOST", DB_HOST);
try {
prop.store(new FileWriter(new File("settings.ini")), "");
} catch (IOException e) {
e.printStackTrace();
}
}
private static Properties defaultProperties() {
Properties prop = new Properties();
prop.put("username", "admin");
prop.put("password", "admin");
prop.put("DB_USERNAME", "root");
prop.put("DB_PASSWORD", "root");
prop.put("DB_HOST", "localhost");
return prop;
}
}