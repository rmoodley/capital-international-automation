package capital.automation.application.specific.functions.GUI;

import capital.automation.generic.functions.DataFunctions;
import capital.automation.generic.functions.Reporting;
import capital.automation.generic.functions.UtilityFunctions;
import capital.automation.generic.functions.commonBase;

public class Transactions extends commonBase {

    public static String sDefaultPathGUI = "src\\test\\java\\capital\\automation\\object\\repository\\GUI\\";
    public static String sPathMain = sDefaultPathGUI + "Transactions.xml";
    public static String sPathCommon = sDefaultPathGUI + "Common.xml";
    public Boolean sStatus;
    public String reportMessage;
    public String sTestOutcome;

    public Transactions(DataFunctions data, UtilityFunctions utils, Reporting repo)
    {
        this.data = data;
        this.utils = utils;
        this.repo = repo;
    }

    public void ClickTransactionsLabel(String stepNumber) throws Exception
    {
        //Click Transactions Label
        utils.ClickObject("TransactionsMainLabel",sPathMain);

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Click Transactions Label";

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("AllTransactionsLabel",sPathMain, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }

    public void ClickAllTransactionsLabel(String stepNumber) throws Exception
    {
        //Click All Transactions Label
        utils.ClickObject("AllTransactionsLabel",sPathMain);

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Click All Transactions Label";

        //Wait for page to load
        utils.WaitForElementToNotBeVisible("LoadingScreen",sPathCommon);

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("ShowButton",sPathCommon, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }

    public void ClickTransactionsIcon(String stepNumber) throws Exception
    {
        //Click Transactions Icon
        utils.HoverOverObject("TransactionsMainIcon",sPathMain);

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Click Transactions Icon";

        //Wait for page to load
        utils.WaitForElementToNotBeVisible("LoadingScreen",sPathCommon);

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("AllTransactionsLabel",sPathMain, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }

    public void ClickFxHistoryLabel(String stepNumber) throws Exception
    {
        //Click FX History Label
        utils.ClickObject("FxHistoryLabel",sPathMain);

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Click FX History Label";

        //Wait for page to load
        utils.WaitForElementToNotBeVisible("LoadingScreen",sPathCommon);

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("FxHistoryTable",sPathMain, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }

    public void ClickPaymentsHistoryLabel(String stepNumber) throws Exception
    {
        //Click Payments History Label
        utils.ClickObject("PaymentsHistoryLabel",sPathMain);

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Click Payments History Label";

        //Wait for page to load
        utils.WaitForElementToNotBeVisible("LoadingScreen",sPathCommon);

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("ShowButton",sPathCommon, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }

    public void ClickTransactionsBackLabel(String stepNumber) throws Exception
    {
        //Click Back Label
        utils.ClickObject("TransactionsBackLabel",sPathMain);

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Click Transactions Back Label";

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("TransactionsMainLabel",sPathMain, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }
}
