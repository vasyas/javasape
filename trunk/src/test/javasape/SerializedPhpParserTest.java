package javasape;

import java.util.Map;

import junit.framework.TestCase;

public class SerializedPhpParserTest extends TestCase {
    /** Иногда Sape такое вовзращает  */
    public void testParseWrongStringConstants() throws Exception {
        String input = "a:3:{s:18:'__sape_delimiter__';s:0:'';s:16:'__sape_new_url__';s:17:'';s:12:'__sape_ips__';a:5:{i:0;s:13:'217.107.36.73';i:1;s:14:'217.107.36.132';i:2;s:13:'81.177.144.46';i:3;s:13:'87.242.74.101';i:4;s:13:'80.251.136.38';}}";
        SerializedPhpParser serializedPhpParser = new SerializedPhpParser(input);
        Object result = serializedPhpParser.parse();
        
        assertTrue(result instanceof Map);
    }
}
