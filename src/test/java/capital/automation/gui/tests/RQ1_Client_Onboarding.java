package capital.automation.gui.tests;

import capital.automation.generic.functions.commonBase;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//TestNG

//JUnit
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.Assert;

public class RQ1_Client_Onboarding extends commonBase
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
    public void RQ_1() throws Exception
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

                        //Step 5 - Click Account Applications Label
                        funcLib1.ClickAccountApplicationsLabel("Step 5");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());


                        //******************************Accounts Menu End******************************//


                        //******************************Onboarding Pre-Application Start******************************//



                        //******************************Onboarding Pre-Application End******************************//


                        //******************************Account settings Menu Start******************************//

                        //Step 97 - Click Account Settings Label
                        funcLib10.ClickAccountSettingsLabel("Step 97");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 98 - Click Logout Label
                        funcLib10.ClickLogoutLabel("Step 98");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 99 - Click Logout Button
                        funcLib10.ClickLogoutButton("Step 99");

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
