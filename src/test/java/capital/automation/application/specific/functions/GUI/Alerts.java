package capital.automation.application.specific.functions.GUI;

import capital.automation.generic.functions.DataFunctions;
import capital.automation.generic.functions.Reporting;
import capital.automation.generic.functions.UtilityFunctions;
import capital.automation.generic.functions.commonBase;

public class Alerts extends commonBase {

    public static String sDefaultPathGUI = "src\\test\\java\\capital\\automation\\object\\repository\\GUI\\";
    public static String sPathMain = sDefaultPathGUI + "Alerts.xml";
    public static String sPathCommon = sDefaultPathGUI + "Common.xml";
    public Boolean sStatus;
    public String reportMessage;
    public String sTestOutcome;

    public Alerts(DataFunctions data, UtilityFunctions utils, Reporting repo)
    {
        this.data = data;
        this.utils = utils;
        this.repo = repo;
    }

    public void ClickAlertsMainLabel(String stepNumber) throws Exception
    {
        //Click Alerts Label
        utils.ClickObject("AlertsMainLabel",sPathMain);

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Click Alerts Main Label";

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("NotificationsLabel",sPathMain, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }

    public void ClickAlertsIcon(String stepNumber) throws Exception
    {
        //Click Messages Icon
        utils.HoverOverObject("AlertsMainIcon",sPathMain);

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Click Alerts Icon";

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("AlertsMainLabel",sPathMain, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }
}
