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

public class Std_Corporate_Client_Onboarding extends commonBase
{
    //Declarations
    public static String filePath;
    public static String sStatus;

    public static String sDefaultPath;
    public static String sRun;
    public static String sRequirement;
    public static String sScenario;

    public static Boolean StpChk;

    @BeforeClass
    //@Before
    public void beforeMethod() throws Exception
    {
        try
        {
            //Sets file path to Excel Spreadsheet
            filePath = "/TestData/GUI/Std_Corporate_Client_Onboarding.xlsx";

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
    public void Std_Corporate_Client_Onboarding() throws Exception
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
                    //Set sRequirement for the iteration
                    sRequirement = data.getCellData("UserStory", i, sheet, null, null, sDataType);

                    //Set sScenario for the iteration
                    sScenario = data.getCellData("Scenario", i, sheet, null, null, sDataType);

                    //Create an extent test
                    repo.setExtentTest(repo.getExtent().createTest(sRequirement + " : " + sScenario));

                    try
                    {
//                        if(i>=1)
//                        {
                            //Initialize Browser Web Driver
                            initialiseWebDriver(i);

                            //Set sStatus to null for the iteration
                            sStatus = null;

                            //Step 1 - Navigate to URL
                            funcLib0.NavigateToURL(sheet, i, "Step 1");

                            //Sleep
                            Thread.sleep(data.getThinkTimeLow());

                            //Step 2 - Login
                            funcLib0.Login(sheet, i, "Step 2");

                            //Sleep
                            Thread.sleep(data.getThinkTimeHigh());
//                        }


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

                        //Step 6 - Click Create New Application
                        funcLib1.CreateNewApplication("Step 6");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 7 - Answer Applicant Questions
                        StpChk = funcLib1.AnswerApplicantQuestions(sheet, i, "Step 7");

                        if(StpChk)
                        {
                            //Sleep
                            Thread.sleep(data.getThinkTimeLow());

                            //Step 8 - Answer Company Questions
                            funcLib1.AnswerCompanyQuestions(sheet, i, "Step 8");

                            //Sleep
                            Thread.sleep(data.getThinkTimeLow());

                            //Step 9 - Answer Company Structure Questions
                            funcLib1.AnswerCompanyStructureQuestions(sheet, i, "Step 9");

                            //Sleep
                            Thread.sleep(data.getThinkTimeLow());

                            //Step 10 - Answer Company Conduct Questions
                            funcLib1.AnswerCompanyConductQuestions(sheet, i, "Step 10");

                            //Sleep
                            Thread.sleep(data.getThinkTimeLow());

                            //Step 11 - Answer Source Of Wealth Questions
                            funcLib1.AnswerSourceOfWealthQuestions(sheet, i, "Step 11");
                        }

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());


                        //Step 95 - Click Accounts Icon
                        funcLib1.ClickAccountsIcon("Step 95");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //Step 96 - Click Accounts Back Label
                        funcLib1.ClickAccountsBackLabel("Step 96");

                        //Sleep
                        Thread.sleep(data.getThinkTimeLow());

                        //******************************Onboarding Pre-Application End******************************//


                        //******************************Account settings Menu Start******************************//

//                        if (i>=1)
//                        {
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

                            //Quit WebDriver
                            Collapse();
//                        }

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
