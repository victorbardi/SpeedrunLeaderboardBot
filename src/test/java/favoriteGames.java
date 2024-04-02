import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

public class favoriteGames {
    private WebDriver navegador;

    @Before
    public void setup() {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\victo\\Downloads\\chromedriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        navegador.manage().window().maximize();

        class DualPrintStream extends PrintStream{
            private final PrintStream second;
            DualPrintStream(OutputStream main, PrintStream second) {
                super(main);
                this.second = second;
            }

            @Override
            public void write(byte[] b) throws IOException{
                super.write(b);
                second.write(b);
            }
        }
    }

    private void takeSnapShot(WebDriver webDriver) throws Exception {
        TakesScreenshot scrShot = ((TakesScreenshot)webDriver);
        File ScrFile = scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile=new File("C:\\Users\\victo\\IdeaProjects\\SpeedrunLeaderboard\\logs\\favoriteGames\\favoriteGames.png");
        FileUtils.copyFile(ScrFile,DestFile);
    }

    @Test
    public void LoginSRLeaderboard() throws Exception {

        //Abrindo speedrun.com
        navegador.get("https://www.speedrun.com/pt-BR");

        Thread.sleep(5000);

        //Abrindo seleção de login
        navegador.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[4]/div/a[1]/button/div")).click();

        //Inserindo login e senha
        String user = ("SRTestBot64");
        navegador.findElement(By.xpath("//*[@id=\"app-main\"]/div[2]/div[1]/form/div/div/div[1]/div[1]/input")).sendKeys(user);

        String password = ("Ganonfloor10!");
        navegador.findElement(By.xpath("//*[@id=\"app-main\"]/div[2]/div[1]/form/div/div/div[1]/div[2]/input")).sendKeys(password);
        navegador.findElement(By.xpath("//*[@id=\"app-main\"]/div[2]/div[1]/form/div/div/div[2]/div/div/button")).click();

        Thread.sleep(5000);

        //Entrar no perfil e confirmar que o login foi feito
        navegador.findElement(By.xpath("/html/body/div[4]/div/div[1]/div[1]/header/div[1]/div[4]/button[4]/div")).click();

        Thread.sleep(10000);

        //Procurar o jogo que deseja favoritar
        navegador.findElement(By.xpath("/html/body/div[4]/div/div[1]/div[1]/header/div[1]/button[2]/div[2]/div")).click();

        Thread.sleep(8000);

        String jogoSeguido = ("The Legend Of Zelda: Ocarina Of Time (1998)");

        navegador.findElement(By.xpath("/html/body/div[4]/div/div[1]/div[1]/header/div[1]/div[5]/div/div/div/div/input")).sendKeys(jogoSeguido);
        navegador.findElement(By.xpath("/html/body/div[4]/div/div[1]/div[1]/header/div[1]/div[5]/div/div/div[2]/a[2]/button")).click();

        //Favoritar o jogo
        navegador.findElement(By.xpath("/html/body/div[4]/div/div[1]/main/div[4]/div[1]/div/div[1]/div/div[2]/div/button[2]")).click();

        Thread.sleep(4000);

        //Validação do teste
          WebElement validateTest = navegador.findElement(By.xpath("/html/body/div[4]/div/div[1]/main/div[4]/div[1]/div/div[1]/div/div[2]/div/button[2]"));
            System.out.println("\n Jogo " +jogoSeguido+" "+validateTest.getText()+ " ! Teste finalizado!");
            Assert.assertEquals(validateTest.getText() ,"Seguindo");

        this.takeSnapShot(navegador);

        Thread.sleep(8000);

        //Deixar de seguir para o próximo teste ser executado
        navegador.findElement(By.xpath("/html/body/div[4]/div/div[1]/main/div[4]/div[1]/div/div[1]/div/div[2]/div/button[2]")).click();

        navegador.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[586]/div[2]/div/div/div[3]/div/button[1]")).click();

        Thread.sleep(10000);

    }
    @After
    public void tearDown(){
       navegador.quit();
    }
}
