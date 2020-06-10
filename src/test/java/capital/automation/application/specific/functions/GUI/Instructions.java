package capital.automation.application.specific.functions.GUI;

import capital.automation.generic.functions.DataFunctions;
import capital.automation.generic.functions.Reporting;
import capital.automation.generic.functions.UtilityFunctions;
import capital.automation.generic.functions.commonBase;

public class Instructions extends commonBase {

    public static String sDefaultPathGUI = "src\\test\\java\\capital\\automation\\object\\repository\\GUI\\";
    public static String sPathMain = sDefaultPathGUI + "Instructions.xml";
    public static String sPathCommon = sDefaultPathGUI + "Common.xml";
    public Boolean sStatus;
    public String reportMessage;
    public String sTestOutcome;

    public Instructions(DataFunctions data, UtilityFunctions utils, Reporting repo)
    {
        this.data = data;
        this.utils = utils;
        this.repo = repo;
    }

    public void ClickInstructionsLabel(String stepNumber) throws Exception
    {
        //Click Instructions Label
        utils.ClickObject("InstructionsMainLabel",sPathMain);

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Click Instructions Label";

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("FXLabel",sPathMain, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }

    public void ClickFXLabel(String stepNumber) throws Exception
    {
        //Click FX Label
        utils.ClickObject("FXLabel",sPathMain);

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Click FX Label";

        //Wait for page to load
        utils.WaitForElementToNotBeVisible("LoadingScreen",sPathCommon);

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("ClearButton",sPathCommon, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }

    public void ClickInstructionsIcon(String stepNumber) throws Exception
    {
        //Click Instructions Icon
        utils.HoverOverObject("InstructionsMainIcon",sPathMain);

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Click Instructions Icon";

        //Wait for page to load
        utils.WaitForElementToNotBeVisible("LoadingScreen",sPathCommon);

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("FXLabel",sPathMain, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }

    public void ClickPaymentsLabel(String stepNumber) throws Exception
    {
        //Click Payments Label
        utils.ClickObject("PaymentsLabel",sPathMain);

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Click Payments Label";

        //Wait for page to load
        utils.WaitForElementToNotBeVisible("LoadingScreen",sPathCommon);

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("ClearButton",sPathCommon, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }

    public void ClickBeneficiariesLabel(String stepNumber) throws Exception
    {
        //Click Beneficiaries Label
        utils.ClickObject("BeneficiariesMainLabel",sPathMain);

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Click Beneficiaries Label";

        //Wait for page to load
        utils.WaitForElementToNotBeVisible("LoadingScreen",sPathCommon);

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("ResetButton",sPathCommon, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }

    public void ClickScheduledPaymentsLabel(String stepNumber) throws Exception
    {
        //Click Scheduled Payments Label
        utils.ClickObject("ScheduledPaymentsLabel",sPathMain);

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Click Scheduled Payments Label";

        //Wait for page to load
        utils.WaitForElementToNotBeVisible("LoadingScreen",sPathCommon);

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("ShowButton",sPathCommon, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }

    public void ClickBulkImportsLabel(String stepNumber) throws Exception
    {
        //Click Bulk Imports Label
        utils.ClickObject("BulkImportsLabel",sPathMain);

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Click Bulk Imports Label";

        //Wait for page to load
        utils.WaitForElementToNotBeVisible("LoadingScreen",sPathCommon);

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("UploadAndAddButton",sPathMain, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }

    public void ClickInstructionsBackLabel(String stepNumber) throws Exception
    {
        //Click Back Label
        utils.ClickObject("InstructionsBackLabel",sPathMain);

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Click Instructions Back Label";

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("InstructionsMainLabel",sPathMain, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }
}
