import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class BlockExplorerPage {

    WebDriver driver;

    // Locators
    private By transactionList = By.xpath("//div[contains(@class,'transaction-box')]");
    private By transactionHash = By.xpath("//div[@class='header']//a");
    private By inputs = By.xpath("//div[@class='ins-and-outs']//div[@class='vin-header']");
    private By outputs = By.xpath("//div[@class='ins-and-outs']//div[@class='vout-header']");

    public BlockExplorerPage(WebDriver driver) {
        this.driver = driver;
    }

    // Get all transactions on the page (first 25 by default)
    public List<WebElement> getAllTransactions() {
        return driver.findElements(transactionList);
    }

    // Get transaction hash
    public String getTransactionHash(WebElement transaction) {
        try {
            return transaction.findElement(transactionHash).getText();
        } catch (Exception e) {
            return "Transaction Hash Not Found";
        }
    }

    // Get input count
    public int getInputCount(WebElement transaction) {
        try {
            return transaction.findElements(inputs).size();
        } catch (Exception e) {
            return 0;
        }
    }

    // Get output count
    public int getOutputCount(WebElement transaction) {
        try {
            return transaction.findElements(outputs).size();
        } catch (Exception e) {
            return 0;
        }
    }

}
