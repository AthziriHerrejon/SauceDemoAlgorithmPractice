package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pageObjects.standardLogin;

public class standardLoginTest {

    WebDriver driver;
    standardLogin loginPage;  // variable clara

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
            "C:\\Users\\athzi\\Downloads\\chromedriver-win64-139\\chromedriver-win64\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        
        //Driver get page
        driver.get("https://www.saucedemo.com/");

        // Inicializar Page Object
        loginPage = new standardLogin(driver);
    }

    @Test
    public void loginTest() {
        loginPage.login("standard_user", "secret_sauce");
        
        //Validar página de inventario
        String expectedUrl ="https://www.saucedemo.com/inventory.html";
        String currentUrl= driver.getCurrentUrl();
        
        if (expectedUrl.equals(currentUrl)) {
            System.out.println("La url es correcta " + currentUrl); 
        } else {
                System.out.println("La url es incorrecta" + currentUrl);

            }


    }
}
