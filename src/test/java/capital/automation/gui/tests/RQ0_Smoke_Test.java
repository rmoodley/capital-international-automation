package capital.automation.gui.tests;

import capital.automation.generic.functions.commonBase;

//TestNG
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;

//JUnit
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.Assert;

public class RQ0_Smoke_Test extends commonBase
{
    //Declarations
    public static String filePath;
    public static String sStatus;

    public static String sDefaultPath;
    public static String sRun;
    public static String sRequirement;
    public static String sScenario;

    @BeforeClass
    //@Before
    public void beforeMethod() throws Exception
    {
        try
        {
            //Sets file path to Excel Spreadsheet
            filePath = "/TestData/GUI/RQ0_Smoke_Test.xlsx";

            //Initialize Spreadsheet and WebDriver
            setup(filePath);
        }
        catch(Exception e)
        {
            //Outputs error message
            System.out.println("Unable to initilise the driver");
        }
    }

    @Test
    public void RQ_0() throws Exception
    {
        try
        {
            //Initialize Function Library
            initialiseFunctions();

            //Get the total number of rows from the Excel Spreadsheet (excluding headers)
            iRow = data.rowCount(sheet, record, resultset, data.getDataType())-1;

            //Get Framework file path
            sDefaultPath = System.getProperty("user.dir");
            sDefaultPath = sDefaultPath.replace("batch", "");

            //Run a For-Do loop, starting at 1, which will run until the end of the Excel Spreadsheet
            for (int i = 1 ;i<=iRow;i++)
            {
                //Set sRun for the iteration
                sRun = data.getCellData("Run",i,sheet, null,null, sDataType);

                //Confirms if Test Case is required to Run
                if (sRun.toUpperCase().contains("YES"))
                {
                    //Initialize Browser Web Driver
                    initialiseWebDriver(i);

                    //Set sStatus to null for the iteration
                    sStatus = null;

                    //Set sRequirement for the iteration
                    sRequirement = data.getCellData("Requirement", i, sheet, null, null, sDataType);

                    //Set sScenario for the iteration
                    sScenario = data.getCellData("Scenario", i, sheet, null, null, sDataType);

                    //Create an extent test
                    repo.setExtentTest(repo.getExtent().createTest(sRequirement + " : " + sScenario));

                    try
                    {
                        //Step 1 - Navigate to URL
                        funcLib0.NavigateToURL(sheet, i, "Step 1");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 2 - Login
                        funcLib0.Login(sheet, i, "Step 2");

                        //Sleep
                        Thread.sleep(data.getThinkTimeHigh());


                        //******************************Accounts Menu Start******************************//

                        //Step 4 - Click Accounts Label
                        funcLib1.ClickAccountsLabel("Step 4");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 5 - Click Accounts List Label
                        funcLib1.ClickAccountsListLabel("Step 5");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 6 - Click Accounts Icon
                        funcLib1.ClickAccountsIcon("Step 6");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 7 - Click Account Applications Label
                        funcLib1.ClickAccountApplicationsLabel("Step 7");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 8 - Click Accounts Icon
                        funcLib1.ClickAccountsIcon("Step 8");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 9 - Click Account Mandates Label
                        funcLib1.ClickMandatesLabel("Step 9");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 10 - Click Accounts Icon
                        funcLib1.ClickAccountsIcon("Step 10");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 11 - Click User Management Label
                        funcLib1.ClickUserManagementLabel("Step 11");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 12 - Click Accounts Icon
                        funcLib1.ClickAccountsIcon("Step 12");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 13 - Click Accounts Back Label
                        funcLib1.ClickAccountsBackLabel("Step 13");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //******************************Accounts Menu End******************************//


                        //******************************Reports Menu Start******************************//

                        //Step 14 - Click Reports Label
                        funcLib2.ClickReportsLabel("Step 14");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 15 - Click Statements Label
                        funcLib2.ClickStatementLabel("Step 15");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 16 - Click Reports Icon
                        funcLib2.ClickReportsIcon("Step 16");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 17 - Click Reports Back Label
                        funcLib2.ClickReportsBackLabel("Step 17");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //******************************Reports Menu End******************************//


                        //******************************Transactions Menu Start******************************//

                        //Step 18 - Click Transactions Label
                        funcLib3.ClickTransactionsLabel("Step 18");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 19 - Click All Transactions Label
                        funcLib3.ClickAllTransactionsLabel("Step 19");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 20 - Click Transactions Icon
                        funcLib3.ClickTransactionsIcon("Step 20");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 21 - Click FX History Label
                        funcLib3.ClickFxHistoryLabel("Step 21");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 22 - Click Transactions Icon
                        funcLib3.ClickTransactionsIcon("Step 22");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 23 - Click Payments History Label
                        funcLib3.ClickPaymentsHistoryLabel("Step 23");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 24 - Click Transactions Icon
                        funcLib3.ClickTransactionsIcon("Step 24");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 25 - Click Transactions Back Label
                        funcLib3.ClickTransactionsBackLabel("Step 25");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //******************************Transactions Menu End******************************//


                        //******************************Instructions Menu Start******************************//

                        //Step 26 - Click Instructions Label
                        funcLib4.ClickInstructionsLabel("Step 26");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 27 - Click FX Label
                        funcLib4.ClickFXLabel("Step 27");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 28 - Click Instructions Icon
                        funcLib4.ClickInstructionsIcon("Step 28");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 29 - Click Payments Label
                        funcLib4.ClickPaymentsLabel("Step 29");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 30 - Click Instructions Icon
                        funcLib4.ClickInstructionsIcon("Step 30");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 31 - Click Beneficiaries Label
                        funcLib4.ClickBeneficiariesLabel("Step 31");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 32 - Click Instructions Icon
                        funcLib4.ClickInstructionsIcon("Step 32");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 33 - Click Scheduled Payments Label
                        funcLib4.ClickScheduledPaymentsLabel("Step 33");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 34 - Click Instructions Icon
                        funcLib4.ClickInstructionsIcon("Step 34");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 35 - Click Bulk Imports Label
                        funcLib4.ClickBulkImportsLabel("Step 35");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 36 - Click Instructions Icon
                        funcLib4.ClickInstructionsIcon("Step 36");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 37 - Click Instructions Back Label
                        funcLib4.ClickInstructionsBackLabel("Step 37");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //******************************Instructions Menu End******************************//


                        //******************************Authorisations Menu Start******************************//

                        //Step 38 - Click Authorisations Label
                        funcLib5.ClickAuthorisationsLabel("Step 38");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 39 - Click History Label
                        funcLib5.ClickHistoryLabel("Step 39");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 40 - Click Authorisations Icon
                        funcLib5.ClickAuthorisationsIcon("Step 40");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

//                        //Step 41 - Click Outstanding Label
//                        funcLib5.ClickOutstandingLabel("Step 41");
//
//                        //Sleep
//                        Thread.sleep(data.getThinkTimeLow());
//
//                        //Step 42 - Click Authorisations Icon
//                        funcLib5.ClickAuthorisationsIcon("Step 42");
//
//                        //Sleep
//                        Thread.sleep(data.getThinkTimeLow());

                        //Step 43 - Click Status Of Requests Label
                        funcLib5.ClickStatusOfRequestsLabel("Step 43");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 44 - Click Authorisations Icon
                        funcLib5.ClickAuthorisationsIcon("Step 44");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 45 - Click History Of Requests Label
                        funcLib5.ClickHistoryOfRequestsLabel("Step 45");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 46 - Click Authorisations Icon
                        funcLib5.ClickAuthorisationsIcon("Step 46");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 47 - Click Authorisations Back Label
                        funcLib5.ClickAuthorisationsBackLabel("Step 47");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //******************************Authorisations Menu End******************************//


                        //******************************Alerts Menu Start******************************//

                        //Step 48 - Click Alerts Label
                        funcLib6.ClickAlertsMainLabel("Step 48");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 49 - Click Alerts Icon
                        funcLib6.ClickAlertsIcon("Step 49");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //******************************Alerts Menu End******************************//


                        //******************************Messages Menu Start******************************//

                        //Step 50 - Click Messages Main Label
                        funcLib7.ClickMessagesMainLabel("Step 50");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 51 - Click Messages Label
                        funcLib7.ClickMessagesLabel("Step 51");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 52 - Click Messages Icon
                        funcLib7.ClickMessagesIcon("Step 52");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 53 - Click Notices Label
                        funcLib7.ClickNoticesLabel("Step 53");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 54 - Click Messages Icon
                        funcLib7.ClickMessagesIcon("Step 54");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 55 - Click Messages Back Label
                        funcLib7.ClickMessagesBackLabel("Step 55");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //******************************Messages Menu End******************************//


                        //******************************Account settings Menu Start******************************//

                        //Step 56 - Click Account Settings Label
                        funcLib10.ClickAccountSettingsLabel("Step 56");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 56 - Click Personal Details Label
                        funcLib10.ClickPersonalDetailsLabel("Step 56");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 57 - Click Account Settings Icon
                        funcLib10.ClickAccountSettingsIcon("Step 57");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 58 - Click Connections Label
                        funcLib10.ClickConnectionsLabel("Step 58");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 59 - Click Account Settings Icon
                        funcLib10.ClickAccountSettingsIcon("Step 59");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 60 - Click Notification Settings Label
                        funcLib10.ClickNotificationSettingsLabel("Step 60");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 61 - Click Account Settings Icon
                        funcLib10.ClickAccountSettingsIcon("Step 61");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 62 - Click Change Password Label
                        funcLib10.ClickChangePasswordLabel("Step 62");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 63 - Click Account Settings Icon
                        funcLib10.ClickAccountSettingsIcon("Step 63");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 64 - Click Change 2FA Device Label
                        funcLib10.ClickChange2FADeviceLabel("Step 64");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 65 - Click Account Settings Icon
                        funcLib10.ClickAccountSettingsIcon("Step 65");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 66 - Click Preferences Label
                        funcLib10.ClickPreferencesLabel("Step 66");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 67 - Click Account Settings Icon
                        funcLib10.ClickAccountSettingsIcon("Step 67");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 68 - Click Logout Label
                        funcLib10.ClickLogoutLabel("Step 68");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 69 - Click Logout Button
                        funcLib10.ClickLogoutButton("Step 69");

                        //******************************Account settings Menu End******************************//

                        //Sets Test Status to Passed
                        sStatus = "Passed";

                        //Updates the Excel spreadsheet Status column to Pass
                        repo.WriteExcelTestStatus(sStatus, i, sDefaultPath + filePath, data);
                    }
                    catch (Exception e)
                    {
                        //Sets Test Status to Failed
                        sStatus = "Failed";

                        //Updates the Excel spreadsheet Status column to Failed
                        repo.WriteExcelTestStatus(sStatus, i, sDefaultPath + filePath, data);

                        //Updates the Extent Report with Exception Error message
                        repo.getExtentTest().fail(e);

                        //Outputs Exception error message to the test run
                        System.out.print(e.getMessage());

                        //Continues the Test run
                        continue;
                    }
                    //Quit WebDriver
                    Collapse();
                }
            }
            if (sStatus.contains("Failed"))
            {
                //Sends an Assert fail to the Test Framework
                Assert.fail();
            }
        }
        catch(Exception e)
        {
            //Outputs Exception error message to the report
            repo.getExtentTest().fail(e);

            //Outputs Exception error message to the test run
            System.out.print(e.getMessage());
        }
    }

    @AfterMethod
    //@After
    public void afterMethod() throws Exception
    {
        //Close the Extent Report
        repo.getExtent().flush();
    }
}
