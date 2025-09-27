package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.standardLogin;
import pageObjects.inventoryPage;

public class inventoryExpensiveProducts {

    WebDriver driver;
    standardLogin loginPage;    // Page Object de login
    inventoryPage inventario;   // Page Object de inventario

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
            "C:\\Users\\athzi\\Downloads\\chromedriver-win64-139\\chromedriver-win64\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        
        //Driver get page
        driver.get("https://www.saucedemo.com/");

        // Inicializar Page Objects
        loginPage = new standardLogin(driver);
        inventario = new inventoryPage(driver);
    }

    @Test
    public void comprarProductosMayores15() {
        // 1️⃣ Login
        loginPage.login("standard_user", "secret_sauce");

        // 2️⃣ Aplicar filtro de mayor a menor
        inventario.filtroPrecioMayor();

        // 3️⃣ Agregar productos con precio > 15
        boolean agregados = inventario.seAgregaronProductosMayoresA(15.0);

        // 4️⃣ Verificación simple
        assert agregados : "No se agregó ningún producto mayor a 15$";

        // 5️⃣ Imprimir productos agregados
        inventario.imprimirProductosAgregados();
    }


    /*@AfterClass
    public void tearDown() {
        driver.quit();
    }*/
}
