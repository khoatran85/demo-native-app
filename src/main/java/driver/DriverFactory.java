package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
    AppiumDriver<MobileElement> driver;

    public AppiumDriver<MobileElement> getDriver(String platformName, String udid, String systemPort) {
        initDriver(platformName, udid, systemPort);
        return driver;
    }

    public void initDriver(String platformName, String udid, String systemPort) {
        try {
            DesiredCapabilities des = new DesiredCapabilities();
            des.setCapability(MobileCapabilityTypeEx.PLATFORM_NAME, platformName);
            des.setCapability(MobileCapabilityTypeEx.AUTOMATION_NAME, "uiautomator2");
            des.setCapability(MobileCapabilityTypeEx.UDID, udid);
            des.setCapability(MobileCapabilityTypeEx.SYSTEM_PORT, systemPort);
            des.setCapability(MobileCapabilityTypeEx.APP_PACKAGE, "com.wdiodemoapp");
            des.setCapability(MobileCapabilityTypeEx.APP_ACTIVITY, "com.wdiodemoapp.MainActivity");

            URL remoteServer = new URL("http://localhost:4723/wd/hub");
            driver = new AppiumDriver<>(remoteServer, des);
            driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
