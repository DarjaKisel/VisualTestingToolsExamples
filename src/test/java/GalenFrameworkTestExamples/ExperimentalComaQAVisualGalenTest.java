package GalenFrameworkTestExamples;

import com.galenframework.testng.GalenTestNgReportsListener;
import core.TestBaseForGalenTests;
import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

import static helpers.PageInfoProvider.getPageSpecFile;
import static helpers.PageInfoProvider.getPageURI;
import static java.util.Arrays.asList;

@Listeners(GalenTestNgReportsListener.class)
public class ExperimentalComaQAVisualGalenTest extends TestBaseForGalenTests{

    @Test(dataProvider = "randomDevices")
    public void checkRandomLayoutOnMainPageTest(TestDevice device) throws IOException {
        load(getPageURI("page.main"));
        checkLayout(getPageSpecFile("page.main"), device.getTags());
    }

    @Test
    public void checkLayoutOnMainPage_onMobileDevice_Test() throws IOException {
        load(getPageURI("page.main"), 320, 600);
        checkLayout(getPageSpecFile("page.main"), asList("mobile"));
    }

    @Test
    public void checkLayoutOnMainPage_onTabletDevice_Test() throws IOException {
        load(getPageURI("page.main"), 750, 1024);
        checkLayout(getPageSpecFile("page.main"), asList("tablet"));
    }

    @Test
    public void checkLayoutOnMainPage_onLaptopDevice_Test() throws IOException {
        load(getPageURI("page.main"), 1210, 768);
        translate();
        checkLayout(getPageSpecFile("page.main"), asList("laptop"));
    }

    @Test
    public void checkLayoutOnMainPage_onDesktopDevice_Test() throws IOException {
        load(getPageURI("page.main"), 1600, 900);
        checkLayout(getPageSpecFile("page.main"), asList("desktop"));
    }

    private void translate() {
        By translationIcon = By.cssSelector(".lang-item-ru");
        By mobileNavigation = By.cssSelector(".fa-bars");
        if (!getDriver().findElement(translationIcon).isDisplayed()) {
            getDriver().findElement(mobileNavigation).click();
        } getDriver().findElement(translationIcon).click();
    }
}
