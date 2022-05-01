package BaseClass;

import java.io.FileInputStream;
import java.util.Properties;

public class TestBase {
	public static Properties prop;

	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					"C:\\Selenium_Workspace\\RestAssured\\src\\main\\java\\Utillities\\config.properties");
			prop.load(ip);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
