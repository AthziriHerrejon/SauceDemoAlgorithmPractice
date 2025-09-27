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

public class MostCheapProducts {

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
    public void agregarTresProductosMasBaratosTest() {
        // 1️⃣ Login
        loginPage.login("standard_user", "secret_sauce");

        // 2️⃣ Filtrar de menor a mayor
        inventario.filtroPrecioMenor(); // Suponiendo que ya tengas este método en tu Page Object

        // 3️⃣ Agregar los 3 productos más baratos
        List<Double> preciosAgregados = inventario.agregarTresProductosMasBaratos();

        // 4️⃣ Verificación: que se hayan agregado productos
        assert !preciosAgregados.isEmpty() : "No se agregaron productos al carrito";

        // 5️⃣ Imprimir los precios agregados
        System.out.println("Precios de los 3 productos más baratos agregados: " + preciosAgregados);
    }

    /*@AfterClass
    public void tearDown() {
        driver.quit();
    }*/
}
