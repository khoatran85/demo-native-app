package Base;

import driver.MobileCapabilityTypeEx;
import driver.PlatformType;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
     String udid;
     String systemPort;
    protected String platformName;
     String platformVersion;

    public AppiumDriver getDriver() {
//        if(driver.get() == null){
//            initDriver(udid, systemPort, platformName, platformVersion);
//        }
//        if(driver.get() == null){
//            throw new RuntimeException("[ERR] Can't establish a connection to test");
//        }
        return driver.get();
    }

    public void setDriver(AppiumDriver driver) {
        this.driver.set(driver);
    }

    @BeforeTest(alwaysRun = true, description = "Init all appium sessions")
    @Parameters({"udid", "systemPort", "platformName", "platformVersion"})
    public void beforeTest(String udid, String systemPort, String platformName, @Optional("platformVersion") String platformVersion) {
        this.platformName = platformName;
        this.udid = udid;
        this.systemPort = systemPort;
        this.platformVersion = platformVersion;
        initDriver(udid, systemPort, platformName, platformVersion);
    }

//    @AfterTest(alwaysRun = true)
    public void afterTest(){
        getDriver().quit();
    }

    private void initDriver(String udid, String systemPort, String platformName, String platformVersion) {
        try {
            PlatformType.valueOf(platformName.toLowerCase());
            System.out.println("platform = " + platformName);
        } catch (Exception e) {
            throw new IllegalArgumentException("We don't support " + platformName);
        }
        boolean isAndroid = platformName.equalsIgnoreCase("android");
        System.out.println("platform = " + isAndroid);
        try {
            // Specify capabilities
            DesiredCapabilities desiredCaps = new DesiredCapabilities();
            desiredCaps.setCapability(MobileCapabilityTypeEx.PLATFORM_NAME, "android");
            desiredCaps.setCapability(MobileCapabilityTypeEx.AUTOMATION_NAME, "uiautomator2");
            desiredCaps.setCapability(MobileCapabilityTypeEx.UDID, "emulator-5554");
//                desiredCaps.setCapability(MobileCapabilityTypeEx.SYSTEM_PORT, Integer.parseInt(systemPort));
            desiredCaps.setCapability(MobileCapabilityTypeEx.APP_PACKAGE, "kingfood.kpos.app.dev");
            desiredCaps.setCapability(MobileCapabilityTypeEx.APP_ACTIVITY, "kingfood.co.kpos.MainActivity");
            desiredCaps.setCapability("chromedriverExecutable", "src/main/resources/browser_driver/");

            URL remoteServer = new URL("http://localhost:4723/wd/hub");
            setDriver(new AppiumDriver(remoteServer, desiredCaps));
            getDriver().manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

