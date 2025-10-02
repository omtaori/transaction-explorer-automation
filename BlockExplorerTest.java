import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BlockExplorerTest {

    WebDriver driver;
    BlockExplorerPage explorerPage;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        explorerPage = new BlockExplorerPage(driver);

        driver.get("https://blockstream.info/block/000000000000000000076c306ff5119e5a5a74df77abf64203473364509f7732");
    }

    @Test
    public void validateTransactions() {
        var transactions = explorerPage.getAllTransactions();

        System.out.println("🔎 Checking " + transactions.size() + " transactions...");

        for (var txn : transactions) {
            String hash = explorerPage.getTransactionHash(txn);
            int inputs = explorerPage.getInputCount(txn);
            int outputs = explorerPage.getOutputCount(txn);

            if (inputs == 1 && outputs == 2) {
                System.out.println("✅ Match Found: " + hash);
            } else {
                System.out.println("Skipping txn " + hash + " | Inputs=" + inputs + " Outputs=" + outputs);
            }
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
