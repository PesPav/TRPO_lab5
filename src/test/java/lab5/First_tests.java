package lab5;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class First_tests {

        private ChromeDriver webdriver;
        @BeforeTest()
        public void setUp() {
            System.setProperty("webdriver.chrome.driver", "C:/trpo_idea/lab5_2/chromedriver.exe");
            webdriver = new ChromeDriver();
            ChromeOptions options = new ChromeOptions();

            options.addArguments("--start-maximized");

            webdriver = new ChromeDriver(options);

            webdriver.get("http://automationpractice.com/");

        }

        @AfterTest()
        public void downUp(){
            webdriver.quit();
        }

    private By formContainer = By.id("create-account_form");
    private By emailAddressInput = By.id("email_create");
    private By createAccountButton = By.id("SubmitCreate");
    private By errorMessage = By.id("create_account_error");

    private static final int BASE_TIMEOUT = 3;

        public void waitForElementDisplayed( By by ) {
            WebDriverWait wait = new WebDriverWait( webdriver, BASE_TIMEOUT);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        }

        public void fillForm() {
            this.waitForElementDisplayed( formContainer );
            webdriver.findElement( emailAddressInput ).sendKeys( "123" );
        }

        public void clickCreateAccountButton() {
            webdriver.findElement( createAccountButton ).click();
        }

        public String getErrorMessage() {
            this.waitForElementDisplayed( errorMessage );
            return webdriver.findElement( errorMessage ).getText();
        }

        @Test()
        public void first() {

            webdriver.findElement(By.className("login")).click();
            fillForm();
            clickCreateAccountButton();

            String PageErrorMassage = getErrorMessage();
            Assert.assertEquals(PageErrorMassage, "Invalid email address.", "error");

        }

        @DataProvider(name = "Abs")
        public Object[][] dataProvMethod1()
        {
            return new Object[][]{{1},{-1},{0},{-444444},{-0}};
        }

        //задание 3.1
        @Test(dataProvider = "Abs")
        public void Abs(int a){
            SoftAssert sa = new SoftAssert();
            int Module = Math.abs(a);
            sa.assertFalse(Module < 0, "Модуль не может быть меньше 0");
            sa.assertAll();

            System.out.println("\nЗадание №1: ");
            System.out.println("abs(mod) = " + Module);
        }

        @DataProvider(name = "AddExact")
        public Object[][] dataProvMethod2()
        {
            return new Object[][] {{-1,10},{0,0},{999999999,999999999},{-30,20},{111,222}};
        }
        //задание 3.2
        @Test(dataProvider = "AddExact")
        public void AddExact(int a, int b)
        {
            try{
                int Sum = Math.addExact(a,b);
                System.out.println("\nЗадание №2: ");
                System.out.println("addExact = " + Sum);
            } catch (ArithmeticException e){
                e.printStackTrace();
            }
        }

        @DataProvider(name = "FloorDiv")
        public Object[][] dataProvMethod3()
        {
            return new Object[][] {{4,4},{4,3},{4,2},{4,1},{4,0}};
        }
        //задание 3.3
        @Test(dataProvider = "FloorDiv")
        public void Task3FloorDiv(int a, int b)
        {
            try{
                int REMAIN = Math.floorDiv(a,b);
                System.out.println("\nЗадание №3: ");
                System.out.println("addExact = " + REMAIN);
            } catch (ArithmeticException e){
                e.printStackTrace();
            }
        }
}
