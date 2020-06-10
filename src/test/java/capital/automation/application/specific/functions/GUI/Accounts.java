package capital.automation.application.specific.functions.GUI;

import capital.automation.generic.functions.DataFunctions;
import capital.automation.generic.functions.Reporting;
import capital.automation.generic.functions.UtilityFunctions;
import capital.automation.generic.functions.commonBase;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class Accounts extends commonBase {

    public static String sDefaultPathGUI = "src\\test\\java\\capital\\automation\\object\\repository\\GUI\\";
    public static String sPathMain = sDefaultPathGUI + "Accounts.xml";
    public static String sPathCommon = sDefaultPathGUI + "Common.xml";
    public Boolean sStatus;
    public String reportMessage;
    public String sTestOutcome;

    public Boolean StpChk;

    public String sAreYouTheApplicant;
    public String sAreYouA;
    public String sConfirmFaceToFace;

    public String sCompanyListedRegulated;
    public String sCountryOfIncorporation;
    public String sNatureOfBusiness;

    public String sCountryManagedAndControlled;
    public String sCountryCompanyOperate;
    public String sCompanyStructure;
    public String sCountryOfResidence;

    public String sPEP;
    public String sPEPCountry;
    public String sCriminalCharges;

    public String sCountryTransferredFrom;
    public String sCountrySourceOfWealth;
    public String sDerivedSourceOfWealth;

    public Accounts(DataFunctions data, UtilityFunctions utils, Reporting repo)
    {
        this.data = data;
        this.utils = utils;
        this.repo = repo;
    }

    public void ClickAccountsLabel(String stepNumber) throws Exception
    {
        //Click Accounts Label
        utils.ClickObject("AccountsMainLabel",sPathMain);

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Click Accounts Label";

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("AccountsListLabel",sPathMain, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }

    public void ClickAccountsListLabel(String stepNumber) throws Exception
    {
        //Click Accounts List Label
        utils.ClickObject("AccountsListLabel",sPathMain);

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Click Accounts List Label";

        //Wait for page to load
        utils.WaitForElementToNotBeVisible("LoadingScreen",sPathCommon);

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("ShowButton",sPathCommon, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }

    public void ClickAccountsIcon(String stepNumber) throws Exception
    {
        //Click Accounts Icon
        utils.HoverOverObject("AccountsMainIcon",sPathMain);

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Click Accounts Icon";

        //Wait for page to load
        utils.WaitForElementToNotBeVisible("LoadingScreen",sPathCommon);

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("AccountsListLabel",sPathMain, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }

    public void ClickAccountApplicationsLabel(String stepNumber) throws Exception
    {
        //Click Account Application Label
        utils.ClickObject("AccountApplicationsLabel",sPathMain);

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Click Account Applications Label";

        //Wait for page to load
        utils.WaitForElementToNotBeVisible("LoadingScreen",sPathCommon);

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("CreateNewApplicationButton",sPathMain, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }

    public void ClickMandatesLabel(String stepNumber) throws Exception
    {
        //Click Mandates Label
        utils.ClickObject("AccountMandatesLabel",sPathMain);

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Click Mandates Label";

        //Wait for page to load
        utils.WaitForElementToNotBeVisible("LoadingScreen",sPathCommon);

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("ShowButton",sPathCommon, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }

    public void ClickUserManagementLabel(String stepNumber) throws Exception
    {
        //Click User Management Label
        utils.ClickObject("AccountUserManagementLabel",sPathMain);

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Click User Management Label";

        //Wait for page to load
        utils.WaitForElementToNotBeVisible("LoadingScreen",sPathCommon);

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("UserManagementTitle",sPathMain, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }

    public void ClickAccountsBackLabel(String stepNumber) throws Exception
    {
        //Click Back Label
        utils.ClickObject("AccountsBackLabel",sPathMain);

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Click Accounts Back Label";

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("AccountsMainLabel",sPathMain, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }

    public void CreateNewApplication(String stepNumber) throws Exception
    {
        //Click Corporate Business Label
        utils.ClickObject("CorporateBusinessLabel",sPathMain);

        //Wait for page to load
        utils.WaitForElementToNotBeVisible("LoadingScreen",sPathCommon);

        //Click Create New Application Button
        utils.ClickObject("CreateNewApplicationButton",sPathMain);

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Click Create New Application";

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("NextButton",sPathMain, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }

    public boolean AnswerApplicantQuestions(Sheet sheet, int i, String stepNumber) throws Exception
    {
        //Get sAreYouTheApplicant using the AreYouTheApplicant column in Excel
        sAreYouTheApplicant = data.getCellData("AreYouTheApplicant",i,sheet, null,null, data.getDataType());

        //Get sAreYouA using the AreYouA column in Excel
        sAreYouA = data.getCellData("AreYouA",i,sheet, null,null, data.getDataType());

        //Get sConfirmFaceToFace using the ConfirmFaceToFace column in Excel
        sConfirmFaceToFace = data.getCellData("ConfirmFaceToFace",i,sheet, null,null, data.getDataType());

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Answer Applicant Questions";

        if (sAreYouTheApplicant.toUpperCase().contains("YES"))
        {
            //Click Are you the Applicant - Yes
            utils.ClickObject("YesApplicantRadioButton",sPathMain);

            //Click Next Button
            utils.ClickObject("NextButton",sPathMain);

            //Wait for page to load
            utils.WaitForElementToNotBeVisible("LoadingScreen",sPathCommon);

            //Report on Step status to ExtentReport
            sStatus = repo.ExtentLogPassFail("NextButton",sPathMain, reportMessage, data.getScreenShotChoice(),utils);

            //Checks Step status
            utils.CheckStatus(sStatus);

            StpChk = true;
        }
        else if (sAreYouTheApplicant.toUpperCase().contains("NO"))
        {
            //Click Are you A - No
            utils.ClickObject("NoApplicantRadioButton",sPathMain);

            Thread.sleep(data.getThinkTimeLow());

            utils.ClickObjectContainText("AreYouRadioButton", sPathMain, sAreYouA);

            Thread.sleep(data.getThinkTimeMedium());

            if (sConfirmFaceToFace.toUpperCase().contains("YES"))
            {
                //Click Confirm Face To Face - Option
                utils.ClickObject("YesConfirmFaceToFaceRadioButton", sPathMain);

                //Click Next Button
                utils.ClickObject("NextButton",sPathMain);

                //Wait for page to load
                utils.WaitForElementToNotBeVisible("LoadingScreen",sPathCommon);

                //Report on Step status to ExtentReport
                sStatus = repo.ExtentLogPassFail("NextButton",sPathMain, reportMessage, data.getScreenShotChoice(),utils);

                //Checks Step status
                utils.CheckStatus(sStatus);

                StpChk = true;
            }
            else if (sConfirmFaceToFace.toUpperCase().contains("NO"))
            {
                //Click Confirm Face To Face - Option
                utils.ClickObject("NoConfirmFaceToFaceRadioButton", sPathMain);

                //Click Next Button
                utils.ClickObject("NextButton",sPathMain);

                //Wait for page to load
                utils.WaitForElementToNotBeVisible("LoadingScreen",sPathCommon);

                //Report on Step status to ExtentReport
                sStatus = repo.ExtentLogPassFail("RejectPreApp",sPathMain, reportMessage, data.getScreenShotChoice(),utils);

                //Checks Step status
                utils.CheckStatus(sStatus);

                //Click Back Button
                utils.ClickObject("BackButton",sPathMain);

                //Wait for page to load
                utils.WaitForElementToNotBeVisible("LoadingScreen",sPathCommon);

                StpChk = false;
            }

        }
        return StpChk;
    }

    public void AnswerCompanyQuestions(Sheet sheet, int i, String stepNumber) throws Exception
    {
        //Get sCompanyListedRegulated using the CompanyListedRegulated column in Excel
        sCompanyListedRegulated = data.getCellData("CompanyListedRegulated",i,sheet, null,null, data.getDataType());

        //Get sCountryOfIncorporation using the CountryOfIncorporation column in Excel
        sCountryOfIncorporation = data.getCellData("CountryOfIncorporation",i,sheet, null,null, data.getDataType());

        //Get sNatureOfBusiness using the NatureOfBusiness column in Excel
        sNatureOfBusiness = data.getCellData("NatureOfBusiness",i,sheet, null,null, data.getDataType());

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Answer Company Questions";

        //Click Company Listed or Regulated - Option
        utils.ClickObjectContainText("CompanyListedRegulatedRadioButton", sPathMain, sCompanyListedRegulated);

        Thread.sleep(data.getThinkTimeLow());

        //Click Country of Incorporation and Enter Text
        utils.ClickObject("CountryOfIncorporationDropDown",sPathMain);
        utils.EnterText("CountryOfIncorporationDropDown", sCountryOfIncorporation, sPathMain);
        utils.ClickObject("CountryOfIncorporationHighlightDropDown",sPathMain);

        Thread.sleep(data.getThinkTimeLow());

        //Click Nature Of Business and Enter Text
        utils.ClickObject("NatureOfBusinessDropDown",sPathMain);
        utils.EnterText("NatureOfBusinessDropDown", sNatureOfBusiness, sPathMain);
        utils.ClickObject("NatureOfBusinessHighlightDropDown",sPathMain);

        Thread.sleep(data.getThinkTimeLow());

        //Click Next Button
        utils.ClickObject("NextButton",sPathMain);

        //Wait for page to load
        utils.WaitForElementToNotBeVisible("LoadingScreen",sPathCommon);

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("NextButton",sPathMain, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }

    public void AnswerCompanyStructureQuestions(Sheet sheet, int i, String stepNumber) throws Exception
    {
        //Get sCountryManagedAndControlled using the CountryManagedAndControlled column in Excel
        sCountryManagedAndControlled = data.getCellData("CountryManagedAndControlled",i,sheet, null,null, data.getDataType());

        //Get sCountryCompanyOperate using the CountryCompanyOperate column in Excel
        sCountryCompanyOperate = data.getCellData("CountryCompanyOperate",i,sheet, null,null, data.getDataType());

        //Get sCompanyStructure using the CompanyStructure column in Excel
        sCompanyStructure = data.getCellData("CompanyStructure",i,sheet, null,null, data.getDataType());

        //Get sCountryOfResidence using the CountryOfResidence column in Excel
        sCountryOfResidence = data.getCellData("CountryOfResidence",i,sheet, null,null, data.getDataType());

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Answer Company Structure Questions";

        //Click Country Managed And Controlled and Enter Text
        utils.ClickObject("CountryManagedAndControlledDropDown",sPathMain);
        utils.EnterText("CountryManagedAndControlledDropDown", sCountryManagedAndControlled, sPathMain);
        utils.ClickObject("CountryManagedAndControlledHighlightDropDown",sPathMain);

        Thread.sleep(data.getThinkTimeLow());

        //Click Country Company Operate and Enter Text
        utils.ClickObject("CountryCompanyOperateDropDown",sPathMain);
        utils.EnterText("CountryCompanyOperateDropDown", sCountryManagedAndControlled, sPathMain);
        utils.ClickObject("CountryCompanyOperateHighlightDropDown",sPathMain);

        Thread.sleep(data.getThinkTimeLow());

        //Click Company Structure - Option
        utils.ClickObjectContainText("CompanyStructureRadioButton", sPathMain, sCompanyStructure);

        Thread.sleep(data.getThinkTimeLow());

        //Click Country Of Residence and Enter Text
        utils.ClickObject("CountryOfResidenceDropDown",sPathMain);
        utils.EnterText("CountryOfResidenceDropDown", sCountryOfIncorporation, sPathMain);
        utils.ClickObject("CountryOfResidenceHighlightDropDown",sPathMain);

        Thread.sleep(data.getThinkTimeLow());

        //Click Next Button
        utils.ClickObject("NextButton",sPathMain);

        //Wait for page to load
        utils.WaitForElementToNotBeVisible("LoadingScreen",sPathCommon);

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("NextButton",sPathMain, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }

    public void AnswerCompanyConductQuestions(Sheet sheet, int i, String stepNumber) throws Exception
    {
        //Get sPEP using the PEP column in Excel
        sPEP = data.getCellData("PEP",i,sheet, null,null, data.getDataType());

        //Get sPEPCountry using the PEPCountry column in Excel
        sPEPCountry = data.getCellData("PEPCountry",i,sheet, null,null, data.getDataType());

        //Get sCriminalCharges using the CriminalCharges column in Excel
        sCriminalCharges = data.getCellData("CriminalCharges",i,sheet, null,null, data.getDataType());

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Answer Company Conduct Questions";

        Thread.sleep(data.getThinkTimeLow());

        if (sPEP.toUpperCase().contains("YES") || sCriminalCharges.toUpperCase().contains("YES"))
        {
            if (sPEP.toUpperCase().contains("YES"))
            {
                //Click PEP Radio Button - Yes
                utils.ClickObject("YesPEPRadioButton",sPathMain);

                Thread.sleep(data.getThinkTimeLow());

                //Click Country Of PEP and Enter Text
                utils.ClickObject("CountryOfPEPDropDown",sPathMain);
                utils.EnterText("CountryOfPEPDropDown", sPEPCountry, sPathMain);
                utils.ClickObject("CountryOfPEPHighlightDropDown",sPathMain);
            }
            else
            {
                //Click PEP Radio Button - No
                utils.ClickObject("NoPEPRadioButton",sPathMain);
            }

            Thread.sleep(data.getThinkTimeLow());


            if (sCriminalCharges.toUpperCase().contains("YES"))
            {
                //Click Yes Criminal Charges Radio Button - Yes
                utils.ClickObject("YesCriminalChargesRadioButton",sPathMain);
            }
            else
            {
                //Click Yes Criminal Charges Radio Button - No
                utils.ClickObject("NoCriminalChargesRadioButton",sPathMain);
            }

            Thread.sleep(data.getThinkTimeLow());

            //Click Next Button
            utils.ClickObject("NextButton",sPathMain);

            //Wait for page to load
            utils.WaitForElementToNotBeVisible("LoadingScreen",sPathCommon);

            //Report on Step status to ExtentReport
            sStatus = repo.ExtentLogPassFail("RejectPreApp",sPathMain, reportMessage, data.getScreenShotChoice(),utils);

        }
        else
        {
            //Click PEP Radio Button - No
            utils.ClickObject("NoPEPRadioButton",sPathMain);

            Thread.sleep(data.getThinkTimeLow());

            //Click Yes Criminal Charges Radio Button - No
            utils.ClickObject("NoCriminalChargesRadioButton",sPathMain);

            Thread.sleep(data.getThinkTimeLow());

            //Click Next Button
            utils.ClickObject("NextButton",sPathMain);

            //Wait for page to load
            utils.WaitForElementToNotBeVisible("LoadingScreen",sPathCommon);

            //Report on Step status to ExtentReport
            sStatus = repo.ExtentLogPassFail("NextButton",sPathMain, reportMessage, data.getScreenShotChoice(),utils);

        }

        //Checks Step status
        utils.CheckStatus(sStatus);
    }

    public void AnswerSourceOfWealthQuestions(Sheet sheet, int i, String stepNumber) throws Exception
    {
        //Get sCountryTransferredFrom using the CountryTransferredFrom column in Excel
        sCountryTransferredFrom = data.getCellData("CountryTransferredFrom",i,sheet, null,null, data.getDataType());

        //Get sCountrySourceOfWealth using the CountrySourceOfWealth column in Excel
        sCountrySourceOfWealth = data.getCellData("CountrySourceOfWealth",i,sheet, null,null, data.getDataType());

        //Get sDerivedSourceOfWealth using the DerivedSourceOfWealth column in Excel
        sDerivedSourceOfWealth = data.getCellData("DerivedSourceOfWealth",i,sheet, null,null, data.getDataType());

        //Set Step report message
        reportMessage = "";
        reportMessage = stepNumber + " - Answer Source Of Wealth Questions";

        Thread.sleep(data.getThinkTimeLow());

        //Click Country Transferred From and Enter Text
        utils.ClickObject("CountryTransferredFromDropDown",sPathMain);
        utils.EnterText("CountryTransferredFromDropDown", sCountryTransferredFrom, sPathMain);
        utils.ClickObject("CountryTransferredFromHighlightDropDown",sPathMain);

        Thread.sleep(data.getThinkTimeLow());

        //Click Country Source Of Wealth and Enter Text
        utils.ClickObject("CountrySourceOfWealthDropDown",sPathMain);
        utils.EnterText("CountrySourceOfWealthDropDown", sCountrySourceOfWealth, sPathMain);
        utils.ClickObject("CountrySourceOfWealthHighlightDropDown",sPathMain);

        Thread.sleep(data.getThinkTimeLow());

        //Click Derived Source Of Wealth and Enter Text
        utils.ClickObject("DerivedSourceOfWealthDropDown",sPathMain);
        utils.EnterText("DerivedSourceOfWealthDropDown", sDerivedSourceOfWealth, sPathMain);
        utils.ClickObject("DerivedSourceOfWealthHighlightDropDown",sPathMain);

        Thread.sleep(data.getThinkTimeLow());

        //Click Next Button
        utils.ClickObject("NextButton",sPathMain);

        //Wait for page to load
        utils.WaitForElementToNotBeVisible("LoadingScreen",sPathCommon);

        //Report on Step status to ExtentReport
        sStatus = repo.ExtentLogPassFail("SuccessPreApp",sPathMain, reportMessage, data.getScreenShotChoice(),utils);

        //Checks Step status
        utils.CheckStatus(sStatus);
    }
}
