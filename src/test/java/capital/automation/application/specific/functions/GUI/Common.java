package capital.automation.application.specific.functions.GUI;

import capital.automation.generic.functions.DataFunctions;
import capital.automation.generic.functions.Reporting;
import capital.automation.generic.functions.UtilityFunctions;
import capital.automation.generic.functions.commonBase;
import org.apache.poi.ss.usermodel.Sheet;

public class Common extends commonBase {

    public static String sDefaultPathGUI = "src\\test\\java\\capital\\automation\\object\\repository\\GUI\\";
    public static String sPathCommon = sDefaultPathGUI + "Common.xml";
    public Boolean sStatus;
    public String reportMessage;
    public String sTestOutcome;

    public String sURL;
    public String sUsername;
    public String sPassword;

    public Common(DataFunctions data, UtilityFunctions utils, Reporting repo)
    {
        this.data = data;
        this.utils = utils;
        this.repo = repo;
    }

    public void NavigateToURL(Sheet sheet, int i, String stepNumber) throws Exception
    {
        //Gets sURL using the URL column in Excel
        sURL = data.getCellData("URL",i,sheet, null,null, data.getDataType());

        //Navigates to URL
        utils.navigate(sURL);

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Navigate To URL";

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("UsernameTextBox",sPathCommon, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }

    public void Login(Sheet sheet, int i, String stepNumber) throws Exception
    {
        //Gets sUsername using the Username column in Excel
        sUsername = data.getCellData("Username",i,sheet, null,null, data.getDataType());

        //Gets sPassword using the Password column in Excel
        sPassword = data.getCellData("Password",i,sheet, null,null, data.getDataType());

        //Gets sTestOutcome using the TestOutcome column in Excel
        sTestOutcome = data.getCellData("TestOutcome",i,sheet, null,null, data.getDataType());

        //Enter Username in "Email or username" field
        utils.EnterText("UsernameTextBox",sUsername,sPathCommon);

        //Enter Password in "Password" field
        utils.EnterText("PasswordTextBox",sPassword,sPathCommon);

        //Click on "Login to account" button
        utils.ClickObject("LoginButton",sPathCommon);

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Login";

        if (Boolean.parseBoolean(sTestOutcome))
        {
            //Report on Step status to ExtentReport
            sStatus = repo.ExtentLogPassFail("SuccessfulLoginMessage",sPathCommon, reportMessage, data.getScreenShotChoice(),utils);
        }
        else
        {
            if (!sUsername.isEmpty() && sPassword.isEmpty())
            {
                //Report on Step status to ExtentReport
                sStatus = repo.ExtentLogPassFailNegative("PasswordRequiredMessage", sPathCommon, reportMessage, data.getScreenShotChoice(), utils);
            }
            else if (sUsername.isEmpty() && !sPassword.isEmpty())
            {
                //Report on Step status to ExtentReport
                sStatus = repo.ExtentLogPassFailNegative("EmailRequiredMessage", sPathCommon, reportMessage, data.getScreenShotChoice(), utils);
            }
            else if (sUsername.isEmpty()|| sPassword.isEmpty())
            {
                //Report on Step status to ExtentReport
                sStatus = repo.ExtentLogPassFailNegative("EmailPasswordRequiredMessage", sPathCommon, reportMessage, data.getScreenShotChoice(), utils);
            }
            else
            {
                //Report on Step status to ExtentReport
                sStatus = repo.ExtentLogPassFailNegative("IncorrectLoginMessage", sPathCommon, reportMessage, data.getScreenShotChoice(), utils);
            }
        }

        //Checks Step status
        utils.CheckStatus(sStatus);
    }

    public void ClickLogoutIcon(String stepNumber) throws Exception
    {
        //Click Logout Icon
        utils.ClickObject("LogoutIcon",sPathCommon);

        data.getThinkTimeLow();

        //Click Logout Icon
        utils.ClickObject("LogoutIcon",sPathCommon);

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Click Logout Icon";

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("LogoutButton",sPathCommon, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }
}
