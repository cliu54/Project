import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class UVicTest {

    WebDriver browser;

    @BeforeEach
    public void setUp() {

        //safari
        browser = new SafariDriver();
        browser.manage().window().maximize();
    }

    @AfterEach
    public void cleanUp() {
        browser.quit();
    }

    @Test
    public void loadPageTest(){
        browser.get("https://www.uvic.ca");
        assertEquals("Home - University of Victoria", browser.getTitle());
    }

    @Test
    public void containSearchButtonTest(){
        browser.get("https://www.uvic.ca");
        WebElement inputBox = browser.findElement(By.xpath("//*[@id=\"search-btn\"]"));
        assertTrue(inputBox.isEnabled());
    }

    @Test
    public void searchButtonAppears(){
        browser.get("https://www.uvic.ca");
        // need a search button

        // find the searchButton by xPath
        WebElement searchButton = browser.findElement(By.xpath("//*[@id=\"search-btn\"]/i"));
        // click on
        searchButton.click();
        // check if inputBox avaliable
        WebElement inputBox = browser.findElement(By.xpath("//*[@id=\"searchUVic\"]"));
        assertTrue(inputBox.isEnabled());
    }


    @Test
    public void cscAppearCorrectnessTest(){
        browser.get("https://www.uvic.ca");
        // need a search button

        // find the searchButton by xPath
        WebElement searchButton = browser.findElement(By.xpath("//*[@id=\"search-btn\"]/i"));
        // click on
        searchButton.click();
        // check if inputBox avaliable
        WebElement inputBox = browser.findElement(By.xpath("//*[@id=\"searchUVic\"]"));
        // type in csc
        inputBox.sendKeys("csc");
        // check whether appear correctly
        assertEquals("csc", inputBox.getAttribute("value"));
    }

    @Test
    public void cscLaunchTest(){
        browser.get("https://www.uvic.ca");
        // need a search button

        // find the searchButton by xPath
        WebElement searchButton = browser.findElement(By.xpath("//*[@id=\"search-btn\"]/i"));
        // click on
        searchButton.click();
        // check if inputBox avaliable
        WebElement inputBox = browser.findElement(By.xpath("//*[@id=\"searchUVic\"]"));
        // type in csc
        inputBox.sendKeys("csc");
        WebElement searchFromInputBox = browser.findElement(By.xpath("//*[@id=\"searchMain\"]/div/div/form/div/button/i"));
        searchFromInputBox.click();
        new WebDriverWait(browser, 5).until(ExpectedConditions.titleIs("Search - University of Victoria"));
        assertEquals("Search - University of Victoria", browser.getTitle());
    }

    @Test
    public void phoneNumberCheck(){
        browser.get("https://www.uvic.ca");
        WebElement contentCheck = browser.findElement(By.xpath("/html/body/footer/div/div[3]/div/div/div[2]/div/div[1]/ul/li[1]/a"));
        assertEquals("tel:1-250-721-7211", contentCheck.getAttribute("href"));

    }
}
