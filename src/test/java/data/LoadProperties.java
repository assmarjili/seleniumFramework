package data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class LoadProperties {
	
	private static String path=System.getProperty("user.dir")+"\\src\\main\\java\\properties\\userData.properties";
	
	public static Properties userdata =loadProperties(path);
	
	public static Properties loadProperties(String path) {
		Properties pro = new Properties();
		try {
			FileInputStream stream = new FileInputStream(path);
			pro.load(stream);
		} catch (FileNotFoundException e) {
			System.out.println("error occured " + e.getMessage());		
		} catch (IOException e) {
			System.out.println("error occured " + e.getMessage());		
		}
		
		return pro;
		
	}

}
