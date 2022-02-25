package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
    AppiumDriver<MobileElement> driver;

    public AppiumDriver<MobileElement> getDriver(String udid, String systemPort, String platformName, String platformVersion) {
        initDriver(udid, systemPort, platformName, platformVersion);
        return driver;
    }

    private void initDriver(String udid, String systemPort, String platformName, String platformVersion) {
        try {
            PlatformType.valueOf(platformName.toLowerCase());
        } catch (Exception e){
            throw new IllegalArgumentException("We don't support " + platformName);
        }
        boolean isAndroid = platformName.equalsIgnoreCase("android");
        System.out.println("platform = " + isAndroid);
        try {
            // Specify capabilities
            DesiredCapabilities desiredCaps = new DesiredCapabilities();
            desiredCaps.setCapability(MobileCapabilityTypeEx.PLATFORM_NAME, platformName);

            if(isAndroid){
                desiredCaps.setCapability(MobileCapabilityTypeEx.AUTOMATION_NAME, "uiautomator2");
                desiredCaps.setCapability(MobileCapabilityTypeEx.UDID, udid);
                desiredCaps.setCapability(MobileCapabilityTypeEx.SYSTEM_PORT, Integer.parseInt(systemPort));
                desiredCaps.setCapability(MobileCapabilityTypeEx.APP_PACKAGE, "com.wdiodemoapp");
                desiredCaps.setCapability(MobileCapabilityTypeEx.APP_ACTIVITY, "com.wdiodemoapp.MainActivity");
            } else {
                desiredCaps.setCapability(MobileCapabilityTypeEx.WDA_LOCAL_PORT, Integer.parseInt(systemPort));
                desiredCaps.setCapability(MobileCapabilityTypeEx.DEVICE_NAME, udid);
                desiredCaps.setCapability(MobileCapabilityTypeEx.PLATFORM_VERSION, platformVersion);
                desiredCaps.setCapability(MobileCapabilityTypeEx.BUNDLE_ID, "org.wdioNativeDemoApp");
                desiredCaps.setCapability(MobileCapabilityTypeEx.NO_RESET, true);
            }

            URL remoteServer = new URL("http://localhost:4444/wd/hub");
//            String hub = System.getProperty("hub") != null ? System.getProperty("hub") : System.getenv("hub");
//            System.out.println("hub =" + hub);
//            if(hub != null){
//                remoteServer = new URL("http://localhost:4444/wd/hub");}
//            else {
//                remoteServer = new URL("http://localhost:4723/wd/hub");
//            }
//            System.out.println("remoreServer = " + remoteServer);
            driver = new AndroidDriver<>(remoteServer, desiredCaps);
            driver.manage().timeouts().implicitlyWait(3L, TimeUnit.SECONDS);

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

    public AppiumDriver<MobileElement> getDriver() {
        return driver;
    }
}
