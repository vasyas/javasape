package javasape;

import javax.servlet.http.Cookie;

import org.apache.log4j.BasicConfigurator;

public class Sape {
    private final String sapeUser;
    
    private final SapeConnection sapePageLinkConnection;

    public Sape(String sapeUser, String host, int socketTimeout, int cacheLifeTime) {
        this.sapeUser = sapeUser;
        
        this.sapePageLinkConnection = new SapeConnection(
                "/code.php?user=" + sapeUser + "&host=" + host,
                "SAPE_Client PHP", socketTimeout, cacheLifeTime);
    }
    
    public boolean debug = false;
    
    public SapePageLinks getPageLinks(String requestUri, Cookie[] cookies) {
        return new SapePageLinks(sapePageLinkConnection, sapeUser, requestUri, cookies, debug);
    }
    
    public static void main(String[] args) {
        BasicConfigurator.configure();
        
        Sape sape = new Sape("b4e26b5106ddf32e5e9bc07626d1c029", "www.investportfel.com", 1000, 10);
    
        SapePageLinks pageLinks = sape.getPageLinks("/funds/", null);
        
        System.out.println(pageLinks.render());
    }
}
