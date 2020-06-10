package capital.automation.application.specific.functions.GUI;

import capital.automation.generic.functions.DataFunctions;
import capital.automation.generic.functions.Reporting;
import capital.automation.generic.functions.UtilityFunctions;
import capital.automation.generic.functions.commonBase;

public class Messages extends commonBase {

    public static String sDefaultPathGUI = "src\\test\\java\\capital\\automation\\object\\repository\\GUI\\";
    public static String sPathMain = sDefaultPathGUI + "Messages.xml";
    public static String sPathCommon = sDefaultPathGUI + "Common.xml";
    public Boolean sStatus;
    public String reportMessage;
    public String sTestOutcome;

    public Messages(DataFunctions data, UtilityFunctions utils, Reporting repo)
    {
        this.data = data;
        this.utils = utils;
        this.repo = repo;
    }

    public void ClickMessagesMainLabel(String stepNumber) throws Exception
    {
        //Click Messages Label
        utils.ClickObject("MessagesMainIcon",sPathMain);

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Click Messages Main Label";

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("MessagesLabel",sPathMain, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }

    public void ClickMessagesLabel(String stepNumber) throws Exception
    {
        //Click Messages Label
        utils.ClickObject("MessagesLabel",sPathMain);

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Click Messages Label";

        //Wait for page to load
        utils.WaitForElementToNotBeVisible("LoadingScreen",sPathCommon);

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("CreateButton",sPathCommon, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }

    public void ClickMessagesIcon(String stepNumber) throws Exception
    {
        //Click Messages Icon
        utils.HoverOverObject("MessagesMainIcon",sPathMain);

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Click Messages Icon";

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("MessagesLabel",sPathMain, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }

    public void ClickNoticesLabel(String stepNumber) throws Exception
    {
        //Click Notices Label
        utils.ClickObject("NoticesLabel",sPathMain);

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Click Notices Label";

        //Wait for page to load
        utils.WaitForElementToNotBeVisible("LoadingScreen",sPathCommon);

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("ShowButton",sPathCommon, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }

    public void ClickMessagesBackLabel(String stepNumber) throws Exception
    {
        //Click Back Label
        utils.ClickObject("MessagesBackLabel",sPathMain);

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Click Messages Back Label";

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("MessagesMainLabel",sPathMain, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }
}
