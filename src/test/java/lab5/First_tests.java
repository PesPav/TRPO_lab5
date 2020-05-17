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
            //Вводим в поле Login неверное значение и ожидаем ошибку, сравниваем текст ошибки с ожидаемым
            Assert.assertEquals(PageErrorMassage, "Invalid email address.", "error");

        }

    @DataProvider(name = "FirstModuleData")
    public Object[][] createData1()
    {
        Object[][] objects = new Object[3][1];

        for(int i = 0; i < 3; i++)
        {
            objects[i][0] = (int)(Math.random() * (Integer.MAX_VALUE - 1) + Math.random() * (Integer.MIN_VALUE + 1));
        }
        return objects;
    }

    @DataProvider(name = "SecondModule1Data")
    public Object[][] createData2()
    {
        Object[][] objects = new Object[3][2];

        for(int i = 0; i < 3; i++)
        {
            objects[i][0] = (int)(Math.random() * (Integer.MAX_VALUE - 1));
            objects[i][1] = (int)(Math.random() * (Integer.MIN_VALUE + 1));
        }
        return objects;
    }

    @DataProvider(name = "SecondModule2Data")
    public Object[][] createData3()
    {
        Object[][] objects = new Object[3][1];
        for(int i = 0; i < 3; i++)
        {
            objects[i][0] = (int)(Math.random() * Integer.MAX_VALUE);
        }
        return objects;
    }

    @DataProvider(name = "SecondModule3Data")
    public Object[][] createData4()
    {
        Object[][] objects = new Object[3][1];
        for(int i = 0; i < 3; i++)
        {
            objects[i][0] = (int)(Math.random() * Integer.MIN_VALUE);
        }
        return objects;
    }

    @DataProvider(name = "ThirdModule1Data")
    public Object[][] createData5()
    {
        Object[][] objects = new Object[3][2];

        for(int i = 0; i < 3; i++)
        {
            objects[i][0] = (int)(Math.random() * Integer.MAX_VALUE - 1);
            objects[i][1] = (int)(Math.random() * Integer.MAX_VALUE - 1);
        }
        return objects;
    }


    @Test(dataProvider = "FirstModuleData")
    public void FirstModule(int a)
    {
        System.out.println("Тест 1. Проверка функции Math.abs(a) корректными значениями");

        int b = Math.abs(a);
        System.out.println("a = " + a);
        System.out.println("Модуль a = " + b);
        if (a < 0) Assert.assertEquals(b,a * -1);
        else Assert.assertEquals(b, a);
        System.out.println();
    }

    @Test(dataProvider = "SecondModule1Data")
    public void SecondModule1(int x, int y)
    {
        System.out.println("Тест 2. Проверка функции Math.addExact корректными значениями");

        System.out.println("Первое число = " + x + "; Второе число = " + y);
        int sum = Math.addExact(x,y);
        Assert.assertEquals(sum,x + y);
        System.out.println("Значение с помощью addExact = " + sum);
        System.out.println("Проверочное значение = " + (x + y));
        System.out.println();
    }

    @Test(dataProvider = "SecondModule2Data")
    public void SecondModule2(int y)
    {
        System.out.println("Тест 3. Проверка функции Math.addExact переполнением с положительными числами");

        int x = Integer.MAX_VALUE;
        System.out.println("Первое число = " + x + "; Второе число = " + y);
        try {
            // При попытке выполнения должна возникать ошибка типа RuntimeException, содержащая информацию о переполнении
            Math.addExact(x,y);
        }
        catch (RuntimeException e)
        {
            System.out.println("Ожидаемый текст исключения: java.lang.ArithmeticException: integer overflow");
            System.out.println("Текст исключения: " + e);
            Assert.assertEquals(e.toString(),"java.lang.ArithmeticException: integer overflow");
        }
        System.out.println();
    }

    @Test(dataProvider = "SecondModule3Data")
    public void SecondModule3(int y)
    {
        System.out.println("Тест 4. Проверка функции Math.addExact переполнением с отрицательными числами");

        int x = Integer.MIN_VALUE;
        System.out.println("Первое число = " + x + "; Второе число = " + y);
        try {
            // При попытке выполнения должна возникать ошибка типа RuntimeException, содержащая информацию о переполнении
            Math.addExact(x,y);
        }
        catch (RuntimeException e)
        {
            System.out.println("Ожидаемый текст исключения: java.lang.ArithmeticException: integer overflow");
            System.out.println("Текст исключения: " + e);
            Assert.assertEquals(e.toString(),"java.lang.ArithmeticException: integer overflow");
        }
        System.out.println();
    }

    @Test(dataProvider = "ThirdModule1Data")
    public void ThirdModule1(int x, int y)
    {
        System.out.println("Тест 5. Проверка функции Math.floorDiv c корректными значениями");
        double res = x / y;
        double result = Math.floorDiv(x, y);
        System.out.println("Первое число = " + x + "; Второе число = " + y);
        System.out.println("Результат floorDiv: " + result);
        System.out.println("Проверка: " + res);
        Assert.assertEquals(result, res);
        System.out.println();
    }

    @Test(dataProvider = "FirstModuleData")
    public void ThirdModule2(int x)
    {
        System.out.println("Тест 5. Проверка функции Math.floorDiv c делением на ноль");

        int y = 0;
        try {
            // При попытке выполнения должна возникать ошибка типа RuntimeException, содержащая информацию о делении на 0
            Math.floorDiv(x,y);
        }
        catch (Throwable e)
        {
            System.out.println("Ожидаемый текст исключения: java.lang.ArithmeticException: / by zero");
            System.out.println("Текст исключения: " + e);
            Assert.assertEquals(e.toString(),"java.lang.ArithmeticException: / by zero");
        }
        System.out.println();
    }
}
