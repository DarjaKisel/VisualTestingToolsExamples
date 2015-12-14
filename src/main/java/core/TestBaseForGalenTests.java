package core;

import com.galenframework.config.GalenConfig;
import com.galenframework.config.GalenProperty;
import com.galenframework.testng.GalenTestNgTestBase;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;

import java.util.List;

import static helpers.LayoutProvider.*;
import static java.util.Arrays.asList;

public class TestBaseForGalenTests extends GalenTestNgTestBase {
    protected WebDriver driver;

    @Override
    public WebDriver createDriver(Object[] args) {
        driver = new ChromeDriver();


        if (args.length > 0) {
            if (args[0] != null && args[0] instanceof TestDevice) {
                TestDevice device = (TestDevice)args[0];
                if (device.getScreenSize() != null) {
                    driver.manage().window().setSize(device.getScreenSize());
                }
            }
        }
        return driver;
    }

    static {
        GalenConfig.getConfig().setProperty(GalenProperty.SCREENSHOT_FULLPAGE, "true");
    }



    @DataProvider(name = "randomDevices")
    public Object [][] randomDevices () {
        return new Object[][] {
                {new TestDevice("mobile", getRandomResolution(MOBILE), asList("mobile"))},
                {new TestDevice("tablet", getRandomResolution(TABLET), asList("tablet"))},
                {new TestDevice("laptop", getRandomResolution(LAPTOP), asList("laptop"))},
                {new TestDevice("desktop", getRandomResolution(DESKTOP), asList("desktop"))}
        };
    }

    public static class TestDevice {
        private final String name;
        private final Dimension screenSize;
        private final List<String> tags;

        public TestDevice(String name, Dimension screenSize, List<String> tags) {
            this.name = name;
            this.screenSize = screenSize;
            this.tags = tags;
        }

        public String getName() {
            return name;
        }

        public Dimension getScreenSize() {
            return screenSize;
        }

        public List<String> getTags() {
            return tags;
        }
    }
}
