import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class sclogin {
    private WebDriver navegador;

    @Before
    public void setup() {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\victo\\Downloads\\chromedriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        navegador.manage().window().maximize();

    }
    @Test
    public void LoginSRLeaderboard() throws InterruptedException {

        //Abrindo speedrun.com
        navegador.get("https://www.speedrun.com/pt-BR");

        Thread.sleep(5000);

        //Abrindo seleção de login
        navegador.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[4]/div/a[1]/button/div")).click();

        //Inserindo login e senha
        String user = ("SRTestBot64");
        navegador.findElement(By.xpath("//*[@id=\"app-main\"]/div[2]/div[1]/form/div,/div/div[1]/div[1]/input")).sendKeys(user);

        String password = ("Ganonfloor10!");
        navegador.findElement(By.xpath("//*[@id=\"app-main\"]/div[2]/div[1]/form/div/div/div[1]/div[2]/input")).sendKeys(password);
        navegador.findElement(By.xpath("//*[@id=\"app-main\"]/div[2]/div[1]/form/div/div/div[2]/div/div/button")).click();

        Thread.sleep(5000);

        //Entrar no perfil e confirmar que o login foi feito
        navegador.findElement(By.xpath("/html/body/div[4]/div/div[1]/div[1]/header/div[1]/div[4]/button[4]/div")).click();

        Thread.sleep(5000);

        //Validação do teste
        WebElement validateUser = navegador.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[11]/div/div/div/div/button[1]/a/div/span"));
        System.out.println("\n Login " +validateUser.getText()+ " feito com sucesso! Teste finalizado!");
        Assert.assertEquals(validateUser.getText(), user);

        Thread.sleep(10000);

    }
    @After
    public void tearDown(){
        navegador.quit();
    }
}
