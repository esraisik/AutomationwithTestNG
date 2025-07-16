package tests.day15_TestNGAssertions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.ZeroPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.util.List;

public class C03_SoftAssert {
    @Test
            public void test01(){

    //1. “http://zero.webappsecurity.com/” Adresine gidin
    Driver.getDriver().get(ConfigReader.getProperty("zeroUrl"));

    // 2. webbappsecurity ana sayafaya gittiginizi dogrulayin
        SoftAssert softAssert= new SoftAssert();

        String expectedUrl =ConfigReader.getProperty("zeroUrl");
        String actualUrl= Driver.getDriver().getCurrentUrl();

        softAssert.assertEquals(actualUrl,expectedUrl,"zero sayfasına gidilemedi");


        // 3. Sign in butonuna basin
        ZeroPage zeroPage = new ZeroPage();
        zeroPage.anasayfaSignInButton.click();

    // 4. Login kutusuna “username” yazin
        zeroPage.loginKutusu.sendKeys(ConfigReader.getProperty("zeroGecerliUsername"));

    // 5. Password kutusuna “password” yazin
        zeroPage.passwordKutusu.sendKeys(ConfigReader.getProperty("zeroGecerliPassword"));

        // 6. Sign in tusuna basin
        zeroPage.loginPageSignInButton.click();

    // 7. Back tusuna basin
        Driver.getDriver().navigate().back();

    // 8. Giris yapilabildigini dogrulayin
    softAssert.assertTrue(zeroPage.settingsMenu.isDisplayed(),"Giris yapilamadi");

    // 9. Online banking menusunu tiklayin
        zeroPage.onlineBankingMenu.click();

    //10. Pay Bills sayfasina gidin
        zeroPage.payBills.click();

    //11. “Purchase Foreign Currency” tusuna basin
        zeroPage.purchaseForeignCurrency.click();

    //12. Currency dropdown menusunun erisilebilir oldugunu dogrulayin
        softAssert.assertTrue(zeroPage.currencyDdm.isDisplayed(),"Currency dropdown kullanilamiyor");

    //13. “Currency” dropdown menusunden Eurozone’u secin
        Select select = new Select(zeroPage.currencyDdm);
        select.selectByValue("EUR");

    //14. "Eurozone (euro)" secildigini dogrulayin

        String expectedSecim =  "Eurozone (euro)";
        String actualSecim =select.getFirstSelectedOption().getText();

        softAssert.assertEquals(actualSecim,expectedSecim,"Dropdown secimi yanlis");

    //15. Dropdown menude 16 option bulundugunu dogrulayin.
        List<WebElement> dropdownElementleriList = select.getOptions();

        int expectedOptionSayisi =16;
        int actualOptionSyisi= dropdownElementleriList.size();

        softAssert.assertEquals(actualOptionSyisi,expectedOptionSayisi,"Option sayisi 16 degil");

    //16. Dropdown menude "Canada (dollar)" bulunduğunu dogrulayin
        List<String> optionYazilarListesi = ReusableMethods.stringListeyeDonustur(dropdownElementleriList);

        String expectedOption = "Canada (dollar)";

        softAssert.assertTrue(optionYazilarListesi.contains(expectedOption),"dropdown Canada dolari icermiyor");

    //17. Sayfayi kapatin

        softAssert.assertAll();
        Driver.quitDriver();

    }
}