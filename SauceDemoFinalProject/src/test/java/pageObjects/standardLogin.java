package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;


public class standardLogin {
	WebDriver driver;
	
	private By usernamefield = By.id("user-name");
	private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");

    
    //Declarar constructor
    public standardLogin(WebDriver driver) {
    	this.driver = driver; 
    }
 
    
    //Métodos individuales
    
    public void enterUsername (String username) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernamefield));
    	driver.findElement(usernamefield).clear();
    	driver.findElement(usernamefield).sendKeys(username);
    	
    }
    
    public void enterPassword (String password) {
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	 wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
    	driver.findElement(passwordField).clear();
    	driver.findElement(passwordField).sendKeys(password);
    }
    	
    	public void clickLoginbutton() {
    		driver.findElement(loginButton).click();
    	}
    
    	   // Método full login
        public void login(String username, String password) {
            enterUsername(username);
            enterPassword(password);
            clickLoginbutton();
        }
    }
