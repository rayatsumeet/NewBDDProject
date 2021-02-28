package Basesetup;





import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;


public class Base {
	
	public   Logger logger;
	
	
	
	@BeforeClass
	public void setup()
	{
		try {
			System.out.print("setting log");
			logger=		Logger.getLogger("Basesetup");
			
			PropertyConfigurator.configure(System.getProperty("user.dir")+ "/log4j.properties");
			logger.setLevel(Level.DEBUG);
			
		} catch (Exception e) {
		e.printStackTrace();
		}
		
		
	
		
	}

}
