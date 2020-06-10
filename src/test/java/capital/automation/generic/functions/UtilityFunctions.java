package capital.automation.generic.functions;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import capital.automation.generic.functions.DataFunctions;

import static java.time.Duration.*;


public class UtilityFunctions {

    private String processName;
    public static WebDriver driver;

    public WebDriver getWebdriver() {
        return driver;
    }

    public void setWebDriver(WebDriver DriverTest) {
        driver = DriverTest;
    }

    public WebDriver initializeWedriver(String sdriverName, String strURL, DesiredCapabilities capabilities)
    {
        try
        {
            switch (sdriverName.toUpperCase())
            {
                case "CHROME":
                    try
                    {
                        System.setProperty("webdriver.chrome.driver","drivers/ChromeDriver.exe");
                        driver = new ChromeDriver();
                    }
                    catch(Exception e)
                    {
                        System.out.print("not a Mac machine");
                        System.setProperty("webdriver.chrome.driver","drivers/Chromedriver.exe");
                        ChromeOptions options = new ChromeOptions();
                        options.addArguments("start-maximized");
                        driver = new ChromeDriver(options);
                    }
                    break;

                case "FIREFOX":
                    //System.setProperty("webdriver.firefox.marionette","drivers/geckodriver.exe");
                    System.setProperty("webdriver.gecko.driver","drivers/geckodriver.exe");
                    driver =  new FirefoxDriver();
                    break;

                case "IE":
                    System.setProperty("webdriver.ie.driver","drivers/IEDriverServer.exe");
                    driver = new InternetExplorerDriver();
                    break;

                case "IOS":
                    driver = new IOSDriver(new URL(strURL),capabilities);
                    break;

                case "ANDROID":
                    driver = new AndroidDriver(new URL(strURL), capabilities);
                    break;

                case "KOBITON":
                    driver = new AndroidDriver<WebElement>(new URL(strURL), capabilities);
                    break;

            }
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        }catch(Exception e)
        {
            System.out.print(e.getMessage());
        }
        return driver;
    }


    /*******************************************************************************General Function Area***********************************************************************/
    public void WindowsProcess(String processName)
    {
        this.processName = processName;
    }

    public void CloseRunningProcess() throws Exception
    {
        if (isRunning())
        {
            getRuntime().exec("taskkill /F /IM " + processName);
        }
    }

    private boolean isRunning() throws Exception
    {
        Process listTasksProcess = getRuntime().exec("tasklist");
        BufferedReader tasksListReader = new BufferedReader(new InputStreamReader(listTasksProcess.getInputStream()));

        String tasksLine;

        while ((tasksLine = tasksListReader.readLine()) != null)
        {
            if (tasksLine.contains(processName))
            {
                return true;
            }
        }
        return false;
    }

    private Runtime getRuntime()
    {
        return Runtime.getRuntime();
    }

    public void navigate( String baseUrl)
    {
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    public String getCurrentTimeStamp()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return sdf.format(timestamp);

    }

    public  void SendEmail(String sfrom, String sto, String sReportName) {
        // Recipient's email ID needs to be mentioned.
        String to = sto;

        // Sender's email ID needs to be mentioned
        String from = sfrom;

        final String username = "";//change accordingly
        final String password = "";//change accordingly

        // Assuming you are sending email through relay.jangosmtp.net
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "25");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("Please see attached automation results");

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setText("This is message body");

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            String filename = "report/"+sReportName+".html";
            DataSource source = new FileDataSource(filename);

            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully to: "+sto);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


    /********************************************************************************************************************************************
     Selenium Area
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException */

    public void WaitForElement( String property, String path, int intWait) throws SAXException, IOException, ParserConfigurationException
    {
        String[] element = xmlParser(path, property);
        try
        {
            WebDriverWait wait = new WebDriverWait(driver,intWait);
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(element[1])));
        }
        catch(Exception e)
        {
            System.out.println("Element "+element[1]+ " NOT found.");
        }
    }

    public void WaitForElementToNotBeVisible( String property, String path) throws SAXException, IOException, ParserConfigurationException
    {
        boolean elementCheck = true;
        int iCount = 1;

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        String[] element = xmlParser(path, property);
//        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver,1)
//                .ignoring(NoSuchElementException.class)
//                .withTimeout(Duration.ofSeconds(5))
//                .pollingEvery(Duration.ofSeconds(1));

        while(elementCheck  && iCount<=600)
        {
            try
            {
                //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(element[1])));
                elementCheck = driver.findElements(By.xpath(element[1])).size() > 0;
                Thread.sleep(100);
                iCount++;
            }
            catch(Exception e)
            {
                //elementCheck = false;
            }
        }
        if(iCount>=600)
        {
            System.out.println("Page is still loading");
        }
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    /*****************************************************************************
     Function Name: ClickObject
     Description:	click an object in an application using either xpath, ID, Name, linktext and CssSelector and maximum wait time
     Date Created:	05/05/2020
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     ******************************************************************************/
    public void ClickObject( String property, String path) throws SAXException, IOException, ParserConfigurationException
    {
        //get object properties from the xml file repository
        String[] element = xmlParser(path, property);
        switch (element[0].toUpperCase())
        {
            case "XPATH":
                driver.findElement(By.xpath(element[1])).click();
                break;

            case "ID":
                driver.findElement(By.id(element[1])).click();
                break;

            case "NAME":
                driver.findElement(By.name(element[1])).click();
                break;

            case "LINKTEXT":
                driver.findElement(By.linkText(element[1])).click();
                break;

            case "CSSSELECTOR":
                driver.findElement(By.cssSelector(element[1])).click();
                break;
        }
    }

    /*****************************************************************************
     Function Name: ClickObjectWithWait
     Description:	click an object in an application using either xpath, ID, Name, linktext and CssSelector and maximum wait time
     Date Created:	05/05/2020
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     ******************************************************************************/
    public void ClickObjectWithWait( String property, String path) throws SAXException, IOException, ParserConfigurationException
    {
        WebElement myDynamicElement;
        //get object properties from the xml file repository
        String[] element = xmlParser(path, property);
        switch (element[0].toUpperCase())
        {
            case "XPATH":
                //driver.
                driver.findElement(By.xpath(element[1])).click();
                break;

            case "ID":
                driver.findElement(By.id(element[1])).click();
                break;

            case "NAME":
                driver.findElement(By.name(element[1])).click();
                break;

            case "LINKTEXT":
                driver.findElement(By.linkText(element[1])).click();
                break;

            case "CSSSELECTOR":
                driver.findElement(By.cssSelector(element[1])).click();
                break;
        }
    }

    public void ClickObjectContainsText( String property, String path, String uniqueValue) throws SAXException, IOException, ParserConfigurationException
    {
        //get object properties from the xml file repository
        String[] element = xmlParser(path, property);
        switch (element[0].toUpperCase())
        {
            case "XPATH":
                String replaceString = element[1].replace("#uniqueValue#", uniqueValue);
                driver.findElement(By.xpath(replaceString)).click();
                break;
        }
    }

    public void ClickObjectContainText( String property, String path, String sText) throws SAXException, IOException, ParserConfigurationException
    {
        //get object properties from the xml file repository
        String[] element = xmlParser(path, property);
        switch (element[0].toUpperCase())
        {
            case "XPATH":
                driver.findElement(By.xpath(element[1]+"[contains(./text(),'"+sText+"')]")).click();
                break;
        }
    }

    public void CheckIfObjectTextExist( String strElement, String strExpected,String strPass, String strFail, ExtentTest logger) throws Exception
    {
        String strText = driver.findElement(By.className(strElement)).getText();

        if(strText.contains(strExpected))
        {
            //repo.ExtentLogPass( strPass, true,utils);
        }
        else
        {
            //repo.ExtentLogFail( strFail, true, utils);
        }
    }

    public void HoverAndClickObject( String property1, String property2, String path) throws SAXException, IOException, ParserConfigurationException
    {
        //get object properties from the xml file repository
        Actions action = new Actions(driver);
        String[] element1 = xmlParser(path, property1);
        String[] element2 = xmlParser(path, property2);
        switch (element1[0].toUpperCase())
        {
            case "XPATH":
                driver.findElement(By.xpath(element1[1])).click();
                action.moveToElement(driver.findElement(By.xpath(element2[1]))).click().build().perform();
                break;

            case "ID":
                driver.findElement(By.id(element1[1])).click();
                break;

            case "NAME":
                driver.findElement(By.name(element1[1])).click();
                break;

            case "LINKTEXT":
                driver.findElement(By.linkText(element1[1])).click();
                break;

            case "CSSSELECTOR":
                driver.findElement(By.cssSelector(element1[1])).click();
                break;
        }
    }

    public void HoverOverObject( String property, String path) throws SAXException, IOException, ParserConfigurationException
    {
        //get object properties from the xml file repository
        Actions action = new Actions(driver);
        String[] element = xmlParser(path, property);
        switch (element[0].toUpperCase())
        {
            case "XPATH":
                action.moveToElement(driver.findElement(By.xpath(element[1]))).build().perform();
                break;

            case "ID":
                driver.findElement(By.id(element[1])).click();
                break;

            case "NAME":
                driver.findElement(By.name(element[1])).click();
                break;

            case "LINKTEXT":
                driver.findElement(By.linkText(element[1])).click();
                break;

            case "CSSSELECTOR":
                driver.findElement(By.cssSelector(element[1])).click();
                break;
        }
    }

    /*****************************************************************************
     Function Name: ClickObjectUsingAction
     Description:	click on the application using action builder using either xpath, ID, Name, linktext and CssSelector and maximum wait time
     Date Created:	05/05/2020
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     ******************************************************************************/
    public void ClickObjectUsingAction( String property, String path) throws SAXException, IOException, ParserConfigurationException
    {
        Actions action = new Actions(driver);
        //get object properties from the xml file repository
        String[] element = xmlParser(path, property);
        switch (element[0].toUpperCase())
        {
            case "XPATH":
                action.moveToElement(driver.findElement(By.name(element[1]))).click().build().perform();
                break;

            case "ID":
                action.moveToElement(driver.findElement(By.name(element[1]))).click().build().perform();
                break;

            case "NAME":
                action.moveToElement(driver.findElement(By.name(element[1]))).click().build().perform();
                break;

            case "LINKTEXT":
                action.moveToElement(driver.findElement(By.name(element[1]))).click().build().perform();
                break;

            case "CSSSELECTOR":
                action.moveToElement(driver.findElement(By.name(element[1]))).click().build().perform();
                break;
        }
    }

    /*****************************************************************************
     Function Name: DoubleClickObjectUsingActionBuilder
     Description:	double click on the application using action builder using either xpath, ID, Name, linktext and CssSelector and maximum wait time
     Date Created:	05/05/2020
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     ******************************************************************************/
    public void DoubleClickObjectUsingActionBuilder( String property, String path) throws SAXException, IOException, ParserConfigurationException
    {
        Actions action = new Actions(driver);
        //get object properties from the xml file repository
        String[] element = xmlParser(path, property);
        switch (element[0].toUpperCase())
        {
            case "XPATH":
                action.moveToElement(driver.findElement(By.name(element[1]))).doubleClick().build().perform();
                break;

            case "ID":
                action.moveToElement(driver.findElement(By.name(element[1]))).doubleClick().build().perform();
                break;

            case "NAME":
                action.moveToElement(driver.findElement(By.name(element[1]))).doubleClick().build().perform();
                break;

            case "LINKTEXT":
                action.moveToElement(driver.findElement(By.name(element[1]))).doubleClick().build().perform();
                break;

            case "CSSSELECTOR":
                action.moveToElement(driver.findElement(By.name(element[1]))).doubleClick().build().perform();
                break;
        }
    }

    /*****************************************************************************
     Function Name: EnterText
     Description:	Enter text to the application using either xpath, ID, Name, linktext and CssSelector and maximum wait time
     Date Created:	05/05/2020
     ******************************************************************************/
    public void EnterText( String property, String sText,String path) throws SAXException, IOException, ParserConfigurationException
    {
        //get object properties from the xml file repository
        String[] element = xmlParser(path, property);
        switch (element[0].toUpperCase())
        {
            case "XPATH":
                driver.findElement(By.xpath(element[1])).sendKeys(sText);
                break;

            case "ID":
                driver.findElement(By.id(element[1])).sendKeys(sText);
                break;

            case "NAME":
                driver.findElement(By.name(element[1])).sendKeys(sText);
                break;

            case "LINKTEXT":
                driver.findElement(By.linkText(element[1])).sendKeys(sText);
                break;

            case "CSSSELECTOR":
                driver.findElement(By.cssSelector(element[1])).sendKeys(sText);
                break;
        }
    }

    /*****************************************************************************
     Function Name: ObjectPressKey
     Date Created:	05/05/2020
     ******************************************************************************/
    public void ObjectPressKey( String property,String path, Keys key) throws SAXException, IOException, ParserConfigurationException
    {
        //get object properties from the xml file repository
        String[] element = xmlParser(path, property);
        switch (element[0].toUpperCase())
        {
            case "XPATH":
                driver.findElement(By.xpath(element[1])).sendKeys(key);
                break;

            case "ID":
                driver.findElement(By.id(element[1])).sendKeys(key);
                break;

            case "NAME":
                driver.findElement(By.name(element[1])).sendKeys(key);
                break;

            case "LINKTEXT":
                driver.findElement(By.linkText(element[1])).sendKeys(key);
                break;

            case "CSSSELECTOR":
                driver.findElement(By.cssSelector(element[1])).sendKeys(key);
                break;
        }
    }

    //driver.switchTo().parentFrame();
    //Swtiching to another frame
//    public void SwitchToiFrame( String property,String path) throws SAXException, IOException, ParserConfigurationException
//    {
//        //get object properties from the xml file repository
//        String[] element = xmlParser(path, property);
//        switch (element[0].toUpperCase())
//        {
//            case "XPATH":
//                driver.switchTo().frame(driver.findElement(By.xpath(element[1])));
//
//                break;
//
//            case "ID":
//                driver.switchTo().frame(driver.findElement(By.id(element[1])));
//                break;
//
//            case "NAME":
//                driver.switchTo().frame(driver.findElement(By.name(element[1])));
//                break;
//
//            case "LINKTEXT":
//                driver.switchTo().frame(driver.findElement(By.linkText(element[1])));
//                break;
//
//            case "CSSSELECTOR":
//                driver.switchTo().frame(driver.findElement(By.cssSelector(element[1])));
//                break;
//        }
//    }

    /*****************************************************************************
     Function Name: SelectTextByUsingValue
     Description:	Select text using value from the dropdown using either xpath, ID, Name, linktext and CssSelector and maximum wait time
     Date Created:	05/05/2020
     ******************************************************************************/
    public void SelectTextUsingValue( String property, String sText, String path) throws SAXException, IOException, ParserConfigurationException
    {
        //get object properties from the xml file repository
        String[] element = xmlParser(path, property);
        Select oSelect = null;
        switch (element[0].toUpperCase())
        {
            case "XPATH":
                oSelect = new Select(driver.findElement(By.xpath(element[1])));
                break;

            case "ID":
                oSelect = new Select(driver.findElement(By.name(element[1])));
                break;

            case "NAME":
                oSelect = new Select(driver.findElement(By.name(element[1])));
                break;

            case "LINKTEXT":
                oSelect = new Select(driver.findElement(By.name(element[1])));
                break;

            case "CSSSELECTOR":
                oSelect = new Select(driver.findElement(By.name(element[1])));
                break;
        }
        oSelect.selectByValue(sText);
    }

    /*****************************************************************************
     Function Name: SelectTextByUsingIndex
     Description:	Select text using an index from the dropdown using either xpath, ID, Name, linktext and CssSelector and maximum wait time
     Date Created:	05/05/2020
     ******************************************************************************/
    public void SelectTextUsingIndex( String property, int iIndex,String path) throws SAXException, IOException, ParserConfigurationException
    {
        //get object properties from the xml file repository
        String[] element = xmlParser(path, property);
        Select oSelect = null;
        switch (element[0].toUpperCase())
        {
            case "XPATH":
                oSelect = new Select(driver.findElement(By.name(element[1])));
                break;

            case "ID":
                oSelect = new Select(driver.findElement(By.name(element[1])));
                break;

            case "NAME":
                oSelect = new Select(driver.findElement(By.name(element[1])));
                break;

            case "LINKTEXT":
                oSelect = new Select(driver.findElement(By.name(element[1])));
                break;

            case "CSSSELECTOR":
                oSelect = new Select(driver.findElement(By.name(element[1])));
                break;
        }
        oSelect.selectByIndex(iIndex);
    }

    /*****************************************************************************
     Function Name: SelectTextByUsingVisibeText
     Description:	Select text using a visible text from the dropdown using either xpath, ID, Name, linktext and CssSelector and maximum wait time
     Date Created:	05/05/2020
     ******************************************************************************/
    public void SelectTextUsingVisibeText( String property, String sText, String path) throws SAXException, IOException, ParserConfigurationException
    {
        //get object properties from the xml file repository
        String[] element = xmlParser(path, property);
        Select oSelect = null;
        switch (element[0].toUpperCase())
        {
            case "XPATH":
                oSelect = new Select(driver.findElement(By.xpath(element[1])));
                break;

            case "ID":
                oSelect = new Select(driver.findElement(By.name(element[1])));
                break;

            case "NAME":
                oSelect = new Select(driver.findElement(By.name(element[1])));
                break;

            case "LINKTEXT":
                oSelect = new Select(driver.findElement(By.name(element[1])));
                break;

            case "CSSSELECTOR":
                oSelect = new Select(driver.findElement(By.name(element[1])));
                break;
        }
        oSelect.selectByVisibleText(sText);
    }

    /*****************************************************************************
     Function Name: waitforProperty
     Description:	wait for the property to appear using either xpath, ID, Name, linktext and CssSelector and maximum wait time
     Date Created:	05/05/2020
     ******************************************************************************/
    public void waitforProperty( String property, int sWait, String path) throws SAXException, IOException, ParserConfigurationException
    {
        WebDriverWait wait = new WebDriverWait(driver,sWait);
        //get object properties from the xml file repository
        String[] element = xmlParser(path, property);
        switch (element[0].toUpperCase())
        {
            case "XPATH":
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element[1])));
                break;

            case "ID":
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(element[1])));
                break;

            case "NAME":
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(element[1])));
                break;

            case "LINKTEXT":
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(element[1])));
                break;

            case "CSSSELECTOR":
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(element[1])));
                break;
        }
    }

    //Close Windows Pop Up
    public void CloseWindowsPopUp()
    {
        for(int a = driver.getWindowHandles().size() -1 ; a > 0 ; a--)
        {
            String winHandle = driver.getWindowHandles().toArray()[a].toString();

            driver.switchTo().window(winHandle);

            driver.close();
        }
    }

    //Generate Random String
    public String GenerateRandomString(int count)
    {
        return RandomStringUtils.randomAlphabetic(count).toLowerCase();
    }

    //Generate Random Integer
    public String GenerateRandomInt(int count)
    {
        return RandomStringUtils.randomNumeric(count);
    }

    //Generate Random Alpha Numeric
    public String GenerateAlphaNumeric(int count)
    {
        return RandomStringUtils.randomAlphanumeric(10);
    }

    /*****************************************************************************
     Function Name: GetText
     Description:	get text from the application using either xpath, ID, Name, linktext and CssSelector
     Date Created:	05/05/2020
     ******************************************************************************/
    public String GetText( String property, String path) throws SAXException, IOException, ParserConfigurationException
    {
        String strTextToReturn = null;
        //get object properties from the xml file repository
        String[] element = xmlParser(path, property);
        switch (element[0].toUpperCase())
        {
            case "XPATH":
                strTextToReturn = driver.findElement(By.name(element[1])).getText();
                break;

            case "ID":
                strTextToReturn = driver.findElement(By.name(element[1])).getText();
                break;

            case "NAME":
                strTextToReturn = driver.findElement(By.name(element[1])).getText();
                break;

            case "LINKTEXT":
                strTextToReturn = driver.findElement(By.name(element[1])).getText();
                break;

            case "CSSSELECTOR":
                strTextToReturn = driver.findElement(By.cssSelector(element[1])).getText();
                break;
        }
        return strTextToReturn;
    }

    /*****************************************************************************
     Function Name: GetAttributeValue
     Description:	get an attribute value from the application using either xpath, ID, Name, linktext and CssSelector
     Date Created:	05/05/2020
     ******************************************************************************/
    public String GetAttributeValue( String property, String attribute, String path) throws SAXException, IOException, ParserConfigurationException
    {
        String strTextToReturn = null;
        //get object properties from the xml file repository
        String[] element = xmlParser(path, property);
        switch (element[0].toUpperCase())
        {
            case "XPATH":
                strTextToReturn = driver.findElement(By.name(element[1])).getAttribute(attribute);
                break;

            case "ID":
                strTextToReturn = driver.findElement(By.name(element[1])).getAttribute(attribute);
                break;

            case "NAME":
                strTextToReturn = driver.findElement(By.name(element[1])).getAttribute(attribute);
                break;

            case "LINKTEXT":
                strTextToReturn = driver.findElement(By.name(element[1])).getAttribute(attribute);
                break;

            case "CSSSELECTOR":
                strTextToReturn = driver.findElement(By.cssSelector(element[1])).getAttribute(attribute);
                break;
        }
        return strTextToReturn;
    }

    /*****************************************************************************
     Function Name: ClearObject
     Description:	Clear object on the application using either xpath, ID, Name, linktext and CssSelector
     Date Created:	05/05/2020
     ******************************************************************************/
    public void ClearObject( String property, String path) throws SAXException, IOException, ParserConfigurationException
    {
        //get object properties from the xml file repository
        String[] element = xmlParser(path, property);
        switch (element[0].toUpperCase())
        {
            case "XPATH":
                driver.findElement(By.name(element[1])).clear();
                break;

            case "ID":
                driver.findElement(By.name(element[1])).clear();
                break;

            case "NAME":
                driver.findElement(By.name(element[1])).clear();
                break;

            case "LINKTEXT":
                driver.findElement(By.name(element[1])).clear();
                break;

            case "CSSSELECTOR":
                driver.findElement(By.cssSelector(element[1])).clear();
                break;
        }
    }

    /*****************************************************************************
     Function Name: checkIfObjectExists
     Description:	Checks if an object exists using either an xpath, ID or a Name
     Date Created:	05/05/2020
    *******************************************************************************/
    public boolean checkIfObjectExists( String property, String path)
    {
        boolean exists = false;
        try
        {
            //get object properties from the xml file repository
            String[] element = xmlParser(path, property);
            switch (element[0].toUpperCase())
            {
                case "XPATH":
                    exists = (driver.findElement(By.xpath(element[1])) != null) || (driver.findElements(By.xpath(element[1])).isEmpty());
                    break;

                case "ID":
                    exists = (driver.findElement(By.id(element[1])) != null) || (driver.findElements(By.id(element[1])).isEmpty());
                    break;

                case "NAME":
                    exists = (driver.findElement(By.name(element[1])) != null) || (driver.findElements(By.name(element[1])).isEmpty());
                    break;
                case "LINKTEXT":
                    exists = (driver.findElement(By.linkText(element[1])) != null) || (driver.findElements(By.linkText(element[1])).isEmpty());
                    break;
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            exists=false;
        }
        return exists;
    }

    /*****************************************************************************
     Function Name: checkIfObjectIsDisplayed
     Description:	Checks if an object is displayed using either an xpath, ID or a Name
     Date Created:	05/05/2020
    *******************************************************************************/
    public boolean checkIfObjectIsDisplayed( String property, String path)
    {
        boolean exists = false;
        try
        {
            //get object properties from the xml file repository
            String[] element = xmlParser(path, property);
            switch (element[0].toUpperCase())
            {
                case "XPATH":
                    exists = driver.findElements(By.xpath(element[1])).size() > 0;
                    //exists = driver.findElement(By.xpath(element[1])).isDisplayed() == true;
                    break;

                case "ID":
                    exists = driver.findElements(By.id(element[1])).size() > 0;
                    //exists = driver.findElement(By.id(element[1])).isDisplayed() == true;
                    break;

                case "NAME":
                    exists = driver.findElements(By.name(element[1])).size() > 0;
                    //exists = driver.findElement(By.name(element[1])).isDisplayed() == true;
                    break;
                case "LINKTEXT":
                    exists = driver.findElements(By.linkText(element[1])).size() > 0;
                    //exists = driver.findElement(By.linkText(element[1])).isDisplayed() == true;
                    break;
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            exists=false;
        }
        return exists;
    }

    /*****************************************************************************
     Function Name: checkIfObjectIsDisplayedWithMessage
     Description:	Checks if an object is displayed with a message using either an xpath, ID or a Name
     Date Created:	05/05/2020
     *******************************************************************************/
    public boolean checkIfObjectIsDisplayedWithMessage( String property, String path)
    {
        boolean exists = false;
        String messageCompare;
        String messageElement;

        try
        {
            //get object properties from the xml file repository
            String[] element = xmlParserWithMessage(path, property);
            switch (element[0].toUpperCase())
            {
                case "XPATH":
                    if(driver.findElement(By.xpath(element[1])).isDisplayed())
                    {
                        messageCompare = element[2].toUpperCase();
                        messageElement = driver.findElement(By.xpath(element[1])).getText().toUpperCase();
                        exists = messageElement.contains(messageCompare);
                    }
                    else
                    {
                        System.out.println("Object not found");
                    }
                    break;

                case "ID":
                    if(driver.findElement(By.id(element[1])).isDisplayed())
                    {
                        messageCompare = element[2].toUpperCase();
                        messageElement = driver.findElement(By.id(element[1])).getText().toUpperCase();
                        exists = messageElement.contains(messageCompare);
                    }
                    else
                    {
                        System.out.println("Object not found");
                    }
                    break;

                case "NAME":
                    if(driver.findElement(By.name(element[1])).isDisplayed())
                    {
                        messageCompare = element[2].toUpperCase();
                        messageElement = driver.findElement(By.name(element[1])).getText().toUpperCase();
                        exists = messageElement.contains(messageCompare);
                    }
                    else
                    {
                        System.out.println("Object not found");
                    }
                    break;

                case "LINKTEXT":
                    if(driver.findElement(By.linkText(element[1])).isDisplayed())
                    {
                        messageCompare = element[2].toUpperCase();
                        messageElement = driver.findElement(By.linkText(element[1])).getText().toUpperCase();
                        exists = messageElement.contains(messageCompare);
                    }
                    else
                    {
                        System.out.println("Object not found");
                    }
                    break;
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Message does not match");
            exists=false;
        }
        return exists;
    }

    /*****************************************************************************
     Function Name: checkIfObjectEnabled
     Description:	Checks if an object is enabled using either an xpath, ID or a Name
     Date Created:	05/05/2020
     //* @param sDefaultPath
     ******************************************************************************/
    public boolean checkIfObjectEnabled( String property, String path)
    {

        boolean exists = false;
        try
        {
            String[] element = xmlParser(path, property);
            switch (element[0].toUpperCase())
            {
                case "XPATH":
                    exists = driver.findElement(By.xpath(element[1])).isEnabled() == true;
                    break;

                case "ID":
                    exists = driver.findElement(By.id(element[1])).isEnabled() == true;
                    break;

                case "NAME":
                    exists = driver.findElement(By.name(element[1])).isEnabled() == true;
                    break;
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            exists=false;
        }
        return exists;
    }

    //xmlParser(String xmlPath, String tagName, String fieldName);

    /************************************************************end Selenium***************************************************************************************/

    /*****************************************************robot******************************************************************************************************/

    //Press Shift and Tab
    public void PressEnter(int iteration) throws InterruptedException, AWTException
    {
        int i=1;
        while(i<=iteration)
        {
            Thread.sleep(1000);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            i++;
        }
    }

    //Press Down Key on a page
    public void PressDownKey() throws InterruptedException, AWTException
    {
        Thread.sleep(5000);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_PAGE_DOWN);
        robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
    }

    //Press Down Key on a page
    public void PressUpKey() throws InterruptedException, AWTException
    {
        Thread.sleep(5000);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_PAGE_UP);
        robot.keyRelease(KeyEvent.VK_PAGE_UP);
    }

    //Press Down Key on a page
    public void RefreshPage() throws InterruptedException, AWTException
    {
        Thread.sleep(5000);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_F5);
        robot.keyRelease(KeyEvent.VK_F5);
    }

    //Press Shift and Tab
    public void PressShiftTab(int iteration) throws InterruptedException, AWTException
    {
        int i=1;
        while(i<=iteration)
        {
            Thread.sleep(1000);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_SHIFT);
            robot.keyRelease(KeyEvent.VK_TAB);
            i++;
        }
    }

    //Press Shift and Tab
    public void PressLeftArrow(int iteration) throws InterruptedException, AWTException
    {
        int i=1;
        while(i<=iteration)
        {
            Thread.sleep(1000);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_LEFT);
            robot.keyRelease(KeyEvent.VK_LEFT);
            i++;
        }
    }

    public void pressTAB()throws AWTException, InterruptedException
    {

        Robot r = new Robot();
        r.keyPress(KeyEvent.VK_TAB);
        r.keyRelease(KeyEvent.VK_TAB);
        Thread.sleep(1000);
    }

    public void pressF2()throws AWTException, InterruptedException
    {

        Robot r = new Robot();
        r.keyPress(KeyEvent.VK_F2);
        r.keyRelease(KeyEvent.VK_F2);
        Thread.sleep(1000);
    }

    public void pressA()throws AWTException, InterruptedException
    {

        Robot r = new Robot();
        r.keyPress(KeyEvent.VK_A);
        r.keyRelease(KeyEvent.VK_A);
        Thread.sleep(1000);
    }

    public void pressTAB(int iterations)throws AWTException, InterruptedException
    {

        int i=1;
        while(i<=iterations)
        {
            Robot r = new Robot();
            r.keyPress(KeyEvent.VK_TAB);
            r.keyRelease(KeyEvent.VK_TAB);
            Thread.sleep(1000);
            i++;
        }
    }

    //Press Down Key on a page
    public void pressCtrlShiftA() throws InterruptedException, AWTException
    {
        Thread.sleep(5000);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_A);

    }

    /*****************************************************************end robot*************************************************************************************/

    public String[] xmlParser(String xmlPath, String tagName) throws SAXException, IOException, ParserConfigurationException {

        String[] element2 = new String[2];
        File fXmlFile = new File(xmlPath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName(tagName);

        for (int temp = 0; temp < nList.getLength(); temp++)
        {
            Node nNode = nList.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE)
            {
                Element eElement = (Element) nNode;

                String element = eElement.getElementsByTagName("identifier").item(0).getTextContent();
                String element1 = eElement.getElementsByTagName("Element").item(0).getTextContent();
                element2[0] = element;
                element2[1] = element1;
            } // end if
        } // end for loop
        return element2;
    } // end function
    public String[] xmlParserWithMessage(String xmlPath, String tagName) throws SAXException, IOException, ParserConfigurationException {

        String[] element3 = new String[3];
        File fXmlFile = new File(xmlPath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName(tagName);

        for (int temp = 0; temp < nList.getLength(); temp++)
        {
            Node nNode = nList.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE)
            {
                Element eElement = (Element) nNode;

                String element = eElement.getElementsByTagName("identifier").item(0).getTextContent();
                String element1 = eElement.getElementsByTagName("Element").item(0).getTextContent();
                String element2 = eElement.getElementsByTagName("Message").item(0).getTextContent();
                element3[0] = element;
                element3[1] = element1;
                element3[2] = element2;
            } // end if
        } // end for loop
        return element3;
    } // end function


    public void VerifyElement( String property, String path, int intWait) throws Exception
    {
        String[] element = xmlParser(path, property);

        WebDriverWait wait = new WebDriverWait(driver, intWait);

        switch (element[0].toUpperCase())
        {
            case "XPATH":
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element[1])));
                break;

            case "ID":
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(element[1])));
                break;

            case "NAME":
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(element[1])));
                break;

            case "LINKTEXT":
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(element[1])));
                break;

            case "CSSSELECTOR":
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(element[1])));
                break;
        }
    }

    public void VerifyElementNotDisplayed( String property, String path, int intWait) throws Exception
    {
        String[] element = xmlParser(path, property);

        WebDriverWait wait = new WebDriverWait(driver, intWait);

        switch (element[0].toUpperCase())
        {
            case "XPATH":
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(element[1])));
                break;

            case "ID":
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(element[1])));
                break;

            case "NAME":
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.name(element[1])));
                break;

            case "LINKTEXT":
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.linkText(element[1])));
                break;

            case "CSSSELECTOR":
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(element[1])));
                break;
        }
    }

    public void VerifyWebElementDisplayed( WebElement Object, int intWait) throws Exception
    {
        WebDriverWait wait = new WebDriverWait(driver, intWait);
        wait.until(ExpectedConditions.visibilityOf(Object));
    }

    public void VerifyWebElementNotDisplayed( WebElement Object, int intWait) throws Exception
    {
        WebDriverWait wait = new WebDriverWait(driver, intWait);
        wait.until(ExpectedConditions.invisibilityOf(Object));
    }

    public void ClickWebElement( WebElement Object) throws SAXException, IOException, ParserConfigurationException
    {
        Object.click();
    }


    public void CheckStatus(Boolean StepStatus) throws Exception {
        if(!StepStatus)
        {
            throw new Exception("Object not found");
        }
    }

    /*****************************************************************************
     Function Name: CompareStatus
     Description:	Compares Status
     Date Created:	05/05/2020
     ******************************************************************************/
    public boolean CompareStatus(int sStatusCode)
    {
        boolean status = false;
        try
        {
            if (sStatusCode==200)
            {
                status = true;
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            status=false;
        }
        return status;
    }
}

