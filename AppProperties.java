import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class AppProperties {
    private String filename;
    private Properties prop;
  
public AppProperties(String filename) {
        super();
        this.filename = filename;
        prop = new Properties();
        
}
public void initialize() throws Exception
{
    InputStream is = new FileInputStream(filename);
    prop.load(is);
}
public String getProperty(String property)
{
    return prop.getProperty(property);
}

/*public static void main(String[] args) throws Exception {
    Properties prop = new Properties();
    String fileName = "CONFIG";
    InputStream is = new FileInputStream(fileName);

    prop.load(is);

    System.out.println(prop.getProperty("SMTP_HOST"));
    System.out.println(prop.getProperty("SMTP_USER_NAME"));

    System.out.println(prop.getProperty("SMTP_USER_PASSWORD"));
  }*/
}
 

