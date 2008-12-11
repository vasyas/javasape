package javasape;

import junit.framework.TestCase;

import org.apache.log4j.BasicConfigurator;

public class SapeConnectionTest extends TestCase {
    String data;
    
    public void testCache() throws Exception {
        SapeConnection connection = new SapeConnection("1", "2", 0, 1) {
            @Override
            protected String fetchRemoteFile(String host, String path) {
                return data;
            }
        };
        
        data = "a:1:{s:1:'a';s:1:'a'}";
        
        assertEquals("a", connection.getData().get("a"));
        
        data = "a:1:{s:1:'b';s:1:'b'}";
        
        assertEquals("a", connection.getData().get("a"));
        
        Thread.sleep(1500);
        
        assertEquals("b", connection.getData().get("b"));
    }
    
    public static void main(String[] args) {
        BasicConfigurator.configure();
        
        String sapeUser = "sape-user-id";
        
        Sape sape = new Sape(sapeUser, "localhost", 1000, 10);
        
        sape.debug = true;
        
        SapePageLinks pageLinks = sape.getPageLinks("/", null);
        
        System.out.println(pageLinks.render());
    }
}
