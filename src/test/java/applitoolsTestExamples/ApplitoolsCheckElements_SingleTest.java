package applitoolsTestExamples;

import com.applitools.eyes.Eyes;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static helpers.PageInfoProvider.getPageURI;

public class ApplitoolsCheckElements_SingleTest {
    private Eyes eyes;
    private WebDriver driver;
    private FirefoxDriver browser;
    private final static String YOUR_APPLITTOLS_API_KEY = "WcgxAse4k106KgbI0WwPdeBNaS2FOkn8HN78Lgj2Ykg7M110";

    @DataProvider(name = "pages")
    public Object [][] testPages () {
        return new Object[][] {
                {getPageURI("page.main")},
                {getPageURI("page.news")},
                {getPageURI("page.events")},
                {getPageURI("page.our-events")},
                {getPageURI("page.education")},
                {getPageURI("page.video")},
                {getPageURI("page.aboutUs")},
                {getPageURI("page.contactUs")}
        };
    }

    @BeforeMethod
    public void setup(Method method) {
        eyes = new Eyes();
        eyes.setForceFullPageScreenshot(true);
        eyes.setApiKey(YOUR_APPLITTOLS_API_KEY);
        browser = new FirefoxDriver();
    }

    @Test(dataProvider = "pages")
    public void designShouldLookGoodOnLargeScreen_1280x768_test(String testPage) {
        driver = eyes.open(browser, "COMAQA website", "Check layout of all COMAQA pages on Large Screen");
        driver.get(testPage);
        driver.manage().window().setSize(new Dimension(1280, 768));
        eyes.checkWindow();
        eyes.close();
    }

    @Test(dataProvider = "pages")
    public void designShouldLookGoodOnMediumScreen_960x640_test(String testPage) {
        driver = eyes.open(browser, "COMAQA website", "Check layout of all COMAQA pages on Medium Screen");
        driver.get(testPage);
        driver.manage().window().setSize(new Dimension(960, 640));
        eyes.checkWindow();
        eyes.close();
    }

    @Test(dataProvider = "pages")
    public void designShouldLookGoodOnSmallScreen_320x600_test(String testPage) {
        driver = eyes.open(browser, "COMAQA website", "Check layout of all COMAQA pages on Small Screen");
        driver.get(testPage);
        driver.manage().window().setSize(new Dimension(320, 600));
        eyes.checkWindow();
        eyes.close();
    }

    @AfterMethod
    public void teardown() {
        eyes.abortIfNotClosed();
        driver.quit();
    }
}
