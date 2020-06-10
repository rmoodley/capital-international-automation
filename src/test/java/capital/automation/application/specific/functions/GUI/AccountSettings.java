package capital.automation.application.specific.functions.GUI;

import capital.automation.generic.functions.DataFunctions;
import capital.automation.generic.functions.Reporting;
import capital.automation.generic.functions.UtilityFunctions;
import capital.automation.generic.functions.commonBase;

public class AccountSettings extends commonBase {

    public static String sDefaultPathGUI = "src\\test\\java\\capital\\automation\\object\\repository\\GUI\\";
    public static String sPathMain = sDefaultPathGUI + "AccountSettings.xml";
    public static String sPathCommon = sDefaultPathGUI + "Common.xml";
    public Boolean sStatus;
    public String reportMessage;
    public String sTestOutcome;

    public AccountSettings(DataFunctions data, UtilityFunctions utils, Reporting repo)
    {
        this.data = data;
        this.utils = utils;
        this.repo = repo;
    }

    public void ClickAccountSettingsLabel(String stepNumber) throws Exception
    {
        //Click Accounts Label
        utils.ClickObject("AccountSettingsMainLabel",sPathMain);

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Click Account Settings Label";

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("PersonalDetailsLabel",sPathMain, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }

    public void ClickLogoutLabel(String stepNumber) throws Exception
    {
        //Click Logout Label
        utils.ClickObject("LogoutLabel",sPathMain);

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Click Logout Label";

        //Wait for page to load
        utils.WaitForElementToNotBeVisible("LoadingScreen",sPathCommon);

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("LogoutButton",sPathMain, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }

    public void ClickLogoutButton(String stepNumber) throws Exception
    {
        //Click Logout Button
        utils.ClickObject("LogoutButton",sPathMain);

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Click Logout Button";

        //Wait for page to load
        utils.WaitForElementToNotBeVisible("LoadingScreen",sPathCommon);

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("UsernameTextBox",sPathCommon, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }

    public void ClickPersonalDetailsLabel(String stepNumber) throws Exception
    {
        //Click Personal Details Label
        utils.ClickObject("PersonalDetailsLabel",sPathMain);

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Click Personal Details Label";

        //Wait for page to load
        utils.WaitForElementToNotBeVisible("LoadingScreen",sPathCommon);

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("UsernameTitle",sPathMain, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }

    public void ClickAccountSettingsIcon(String stepNumber) throws Exception
    {
        //Click Account Settings Main Icon
        utils.HoverOverObject("AccountSettingsMainIcon",sPathMain);

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Click Account Settings Main Icon";

        //Wait for page to load
        utils.WaitForElementToNotBeVisible("LoadingScreen",sPathCommon);

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("PersonalDetailsLabel",sPathMain, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }

    public void ClickConnectionsLabel(String stepNumber) throws Exception
    {
        //Click Connections Label
        utils.ClickObject("ConnectionsLabel",sPathMain);

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Click Connections Label";

        //Wait for page to load
        utils.WaitForElementToNotBeVisible("LoadingScreen",sPathCommon);

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("ShowButton",sPathCommon, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }

    public void ClickNotificationSettingsLabel(String stepNumber) throws Exception
    {
        //Click Notification Settings Label
        utils.ClickObject("NotificationSettingsLabel",sPathMain);

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Click Notification Settings Label";

        //Wait for page to load
        utils.WaitForElementToNotBeVisible("LoadingScreen",sPathCommon);

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("CancelButton",sPathCommon, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }

    public void ClickChangePasswordLabel(String stepNumber) throws Exception
    {
        //Click Change Password Label
        utils.ClickObject("ChangePasswordLabel",sPathMain);

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Click Change Password Label";

        //Wait for page to load
        utils.WaitForElementToNotBeVisible("LoadingScreen",sPathCommon);

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("ConfirmButton",sPathCommon, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }

    public void ClickChange2FADeviceLabel(String stepNumber) throws Exception
    {
        //Click Change 2FA Device Label
        utils.ClickObject("Change2FaDeviceLabel",sPathMain);

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Click Change 2FA Device Label";

        //Wait for page to load
        utils.WaitForElementToNotBeVisible("LoadingScreen",sPathCommon);

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("Reset2FAButton",sPathMain, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }

    public void ClickPreferencesLabel(String stepNumber) throws Exception
    {
        //Click Preferences Label
        utils.ClickObject("PreferencesLabel",sPathMain);

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Click Preferences Label";

        //Wait for page to load
        utils.WaitForElementToNotBeVisible("LoadingScreen",sPathCommon);

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("CurrencyTitle",sPathMain, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }
}
