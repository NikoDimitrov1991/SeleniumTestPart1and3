import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistationTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Browser Drivers for Selenium\\chromedriver.exe");
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.wait = new WebDriverWait(this.driver, 5L);
        this.driver.get("http://shop.pragmatic.bg");
    }

    @AfterMethod
    public void tearDown() {
        this.driver.quit();
    }

    @Test
    public void userRegiste()  {
        WebElement myAccount = this.driver.findElement(By.xpath("(//span[normalize-space()='My Account'])[1]"));
        myAccount.click();
        WebElement userRegister = this.wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Register']")));
        userRegister.click();
        WebElement firstName = this.wait.until(ExpectedConditions.elementToBeClickable(By.id("input-firstname")));
        firstName.sendKeys("Nikolay");
        WebElement lastName = this.driver.findElement(By.id("input-lastname"));
        lastName.sendKeys("Dimitrov");

        String mail = RandomStringUtils.randomAlphanumeric(10);
        String randomMail = mail + "@yahoo.com";

        WebElement email = this.driver.findElement(By.id("input-email"));
        email.sendKeys(randomMail);

        String phoneNumberGenerator = RandomStringUtils.randomNumeric(8);
        String randomTelephone = "08" + phoneNumberGenerator;
        WebElement telephone = this.driver.findElement(By.id("input-telephone"));
        telephone.sendKeys(randomTelephone);


        WebElement newPassword = this.driver.findElement(By.id("input-password"));
        newPassword.sendKeys("111parola@@@");

        WebElement newPasswordConfirmation = this.wait.until(ExpectedConditions.elementToBeClickable(By.id("input-confirm")));
        newPasswordConfirmation.sendKeys("111parola@@@");

        WebElement termsAndConditionsAgree = this.wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='agree']")));
        if (!termsAndConditionsAgree.isSelected()) {
            termsAndConditionsAgree.click();
        }
        WebElement continueButton = this.driver.findElement(By.cssSelector("input[value='Continue']"));
        continueButton.click();

        WebElement newRegistrationCheck = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[normalize-space()='Your Account Has Been Created!']")));
        Assert.assertEquals(newRegistrationCheck.getText(),"Your Account Has Been Created!");
    }
}
