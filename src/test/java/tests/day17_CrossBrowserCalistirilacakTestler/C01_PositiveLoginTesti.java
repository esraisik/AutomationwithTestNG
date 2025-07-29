package tests.day17_CrossBrowserCalistirilacakTestler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.TestBaseCross;

public class C01_PositiveLoginTesti extends TestBaseCross {

    @Test
    public void psitiveLoginTesti(){


        //1- https://www.testotomasyonu.com/ anasayfasina gidin
        driver.get(ConfigReader.getProperty("toUrl"));

        //2- account linkine basin
        driver.findElement(By.xpath("(//*[.='Account'])[1]"))
                .click();

        // 3- Kullanici email'i olarak gecerli email girin
        WebElement emailKutusu = driver.findElement(By.id("email"));
        emailKutusu.sendKeys(ConfigReader.getProperty("toGecerliEmail"));

        // 4- Kullanici sifresi olarak gecerli password girin
        WebElement passwordKutusu = driver.findElement(By.id("password"));
        passwordKutusu.sendKeys(ConfigReader.getProperty("toGecerliPassword"));

        // 5- Login butonuna basarak login olun
        driver.findElement(By.id("submitlogin"))
                .click();

        // 6- Basarili olarak giris

        WebElement logoutButonu = driver.findElement(By.xpath("//span[.='Logout']"));

        Assert.assertTrue(logoutButonu.isDisplayed());

        logoutButonu.click();


    }
}
