package helpers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PageInfoProvider {
    private static final Properties PAGE_INFO;
    private static final String BASE_URL = "https://comaqa.by";

    static {
        PAGE_INFO = new Properties();
        InputStream in = PageInfoProvider.class.getResourceAsStream("/linksAndSpecs.properties");
        try {
            PAGE_INFO.load(in);
        } catch (IOException ex) {
            System.out.println("Could not load info from related property file" + ex.getMessage());
        }
    }

    public static String getPageURI(String testPage) {
        String[] pageURI = PAGE_INFO.getProperty(testPage).split("=", 3);
        return (BASE_URL + pageURI[0]);
    }

    public static String getPageSpecFile(String testPage) {
        String[] specs = PAGE_INFO.getProperty(testPage).split("=", 3);
        return specs[1];
    }
}
