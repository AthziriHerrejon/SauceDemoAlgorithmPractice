/*Estructura de página:
 * Librerias
 * Clase + Webdriver
 * Items uno a uno: private By
 * Declarar constructor
 * Declarar métodos
 */

/*
 Objetivos:
1.-Ordena los productos de mayor a menor precio.
2.-Agrega al carrito solo los productos cuyo precio sea mayor a $15.
3.-Verifica que todos los productos agregados cumplan la condición.


*/

package pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;
import java.util.ArrayList;

public class inventoryPage {
    WebDriver driver;

    // Locators
    private By sortProducts = By.className("product_sort_container");  // filtro dropdown
    private By inventoryItems = By.className("inventory_item");       // cada producto como bloque
    private By addButton = By.tagName("button");
    
    

    // Constructor
    public inventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    private List<Double> productosAgregados = new ArrayList<>();

    // 🔹 Filtrar de High to Low
    public void filtroPrecioMayor() {
    	WebElement dropdown = driver.findElement(sortProducts);
        Select select = new Select(dropdown);
        select.selectByVisibleText("Price (high to low)");
    }

    // 🔹 Agregar productos mayores a un precio
    public boolean seAgregaronProductosMayoresA(double precioMinimo) {
        List<WebElement> items = driver.findElements(inventoryItems);
   

        for (WebElement item : items) {
            String precioTexto = item.findElement(By.className("inventory_item_price")).getText();
            double precio = Double.parseDouble(precioTexto.replace("$", "").trim());

            if (precio > precioMinimo) {
                WebElement boton = item.findElement(addButton);
                boton.click();
                productosAgregados.add(precio);
                System.out.println("Producto agregado: $" + precio);
            }
        }

        return !productosAgregados.isEmpty();
    }

    public void imprimirProductosAgregados() {
        System.out.println("Productos agregados al carrito: " + productosAgregados);
    }



  //reto 2 //
    
    public void filtroPrecioMenor() {
    	WebElement dropdown = driver.findElement(sortProducts);
        Select select = new Select(dropdown);
        select.selectByVisibleText("Price (low to high)");
    }

 // En inventoryPage
    public List<Double> agregarTresProductosMasBaratos() {
        List<Double> preciosAgregados = new ArrayList<>();

        // Reutilizamos el locator que ya tienes de inventoryItems
        List<WebElement> items = driver.findElements(inventoryItems);

        // Solo recorremos los 3 primeros elementos o menos si no hay suficientes
        for (int i = 0; i < Math.min(3, items.size()); i++) {
            WebElement item = items.get(i);

            // Tomamos el precio del item usando el locator de precio que ya existe
            double precio = Double.parseDouble(
                item.findElement(By.className("inventory_item_price"))
                    .getText().replace("$", "").trim()
            );

            // Reutilizamos la lógica de click en el botón dentro de cada item
            WebElement boton = item.findElement(By.cssSelector("button.btn_inventory"));
            boton.click();


            preciosAgregados.add(precio);
            System.out.println("Producto agregado: $" + precio);
        }

        return preciosAgregados;
    }
}
