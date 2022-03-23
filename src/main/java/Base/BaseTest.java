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

    @AfterTest(alwaysRun = true)
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
            desiredCaps.setCapability(MobileCapabilityTypeEx.PLATFORM_NAME, platformName);

            if (isAndroid) {
                desiredCaps.setCapability(MobileCapabilityTypeEx.AUTOMATION_NAME, "uiautomator2");
                desiredCaps.setCapability(MobileCapabilityTypeEx.UDID, udid);
                desiredCaps.setCapability(MobileCapabilityTypeEx.SYSTEM_PORT, Integer.parseInt(systemPort));
                desiredCaps.setCapability(MobileCapabilityTypeEx.APP_PACKAGE, "com.wdiodemoapp");
                desiredCaps.setCapability(MobileCapabilityTypeEx.APP_ACTIVITY, "com.wdiodemoapp.MainActivity");
                desiredCaps.setCapability("chromedriverExecutable", "src/main/resources/browser_driver/chromedriver");
            } else {
                desiredCaps.setCapability(MobileCapabilityTypeEx.AUTOMATION_NAME, "XCUITest");
                desiredCaps.setCapability(MobileCapabilityTypeEx.WDA_LOCAL_PORT, Integer.parseInt(systemPort));
                desiredCaps.setCapability(MobileCapabilityTypeEx.DEVICE_NAME, udid);
                desiredCaps.setCapability(MobileCapabilityTypeEx.PLATFORM_VERSION, platformVersion);
                desiredCaps.setCapability(MobileCapabilityTypeEx.BUNDLE_ID, "org.wdioNativeDemoApp");
                desiredCaps.setCapability(MobileCapabilityTypeEx.NO_RESET, false);
                desiredCaps.setCapability("chromedriverExecutable", "src/main/resources/browser_driver/chromedriver");

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
            setDriver(new AppiumDriver(remoteServer, desiredCaps));
            getDriver().manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

