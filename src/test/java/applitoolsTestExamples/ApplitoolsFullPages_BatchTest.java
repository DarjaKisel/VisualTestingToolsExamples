package applitoolsTestExamples;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.Eyes;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.lang.reflect.Method;

import static helpers.PageInfoProvider.getPageURI;

public class ApplitoolsFullPages_BatchTest {
    private Eyes eyes;
    private WebDriver driver;
    private ChromeDriver browser;
    private static BatchInfo batch;
    private final static String YOUR_APPLITTOLS_API_KEY = "WcgxAse4k106KgbI0WwPdeBNaS2FOkn8HN78Lgj2Ykg7M110";

    @DataProvider(name = "sizes")
    public Object [][] windowSizes () {
        return new Object[][] {
                {new Dimension(320, 600)},
                {new Dimension(960, 640)},
                {new Dimension(1280, 768)}
        };
    }

    @BeforeTest
    public void setBatch() {
        batch = new BatchInfo("Full page screenshots of COMAQA.BY website");
    }

    @BeforeMethod
    public void setup() {
        eyes = new Eyes();
        eyes.setForceFullPageScreenshot(true);
        eyes.setApiKey(YOUR_APPLITTOLS_API_KEY);
        eyes.setBatch(batch);
        browser = new ChromeDriver();
    }

    @Test(dataProvider = "sizes")
    public void pageMain(Dimension size, Method method) {
        driver = eyes.open(browser, "COMAQA website", "Full screen layout of " + method.getName()
                + " in resolution " + size.getWidth() + " x " + size.getHeight());
        driver.get(getPageURI("page.main"));
        driver.findElement(By.cssSelector(".lang-item-ru")).click();
        driver.manage().window().setSize(size);
        eyes.checkWindow();
        eyes.close();
    }

    @Test(dataProvider = "sizes")
    public void pageNews(Dimension size, Method method) {
        driver = eyes.open(browser, "COMAQA website", "Full screen layout of " + method.getName()
                + " in resolution " + size.getWidth() + " x " + size.getHeight());
        driver.get(getPageURI("page.news"));
        eyes.checkWindow();
        eyes.close();
    }

    @AfterMethod
    public void teardown() {
        eyes.abortIfNotClosed();
        driver.quit();
    }

}
