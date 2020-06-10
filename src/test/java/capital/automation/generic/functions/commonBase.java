package capital.automation.generic.functions;

import capital.automation.application.specific.functions.GUI.*;
import com.codoid.products.fillo.Recordset;
import org.apache.poi.ss.usermodel.Sheet;
import java.sql.ResultSet;

public class commonBase {

    protected UtilityFunctions utils = new UtilityFunctions();
    protected Reporting repo = new Reporting();
    protected DataFunctions data = new DataFunctions();

    protected Common funcLib0;
    protected Accounts funcLib1;
    protected Reports funcLib2;
    protected Transactions funcLib3;
    protected Instructions funcLib4;
    protected Authorisations funcLib5;
    protected Alerts funcLib6;
    protected Messages funcLib7;
    protected MyDocuments funcLib8;
    protected Connections funcLib9;
    protected AccountSettings funcLib10;

    public static String sDefaultPath;
    protected String sDataType;
    protected int iRow;
    protected Sheet sheet;
    protected Recordset record;
    protected ResultSet resultset;

    protected void initialiseFunctions() {
        funcLib0 = new Common(data, utils, repo);
        funcLib1 = new Accounts(data, utils, repo);
        funcLib2 = new Reports(data, utils, repo);
        funcLib3 = new Transactions(data, utils, repo);
        funcLib4 = new Instructions(data, utils, repo);
        funcLib5 = new Authorisations(data, utils, repo);
        funcLib6 = new Alerts(data, utils, repo);
        funcLib7 = new Messages(data, utils, repo);
        funcLib8 = new MyDocuments(data, utils, repo);
        funcLib9 = new Connections(data, utils, repo);
        funcLib10 = new AccountSettings(data, utils, repo);
    }

    protected void initialiseWebDriver(int i) throws Exception {
        utils.setWebDriver(utils.initializeWedriver(data.getCellData("Browser",i,sheet,null,null,sDataType), null,  null));

    }

    public void setup(String Location) throws Exception {
        try
        {
            sDefaultPath = System.getProperty("user.dir");
            sDefaultPath = sDefaultPath.replace("batch", "");
            data.GetEnvironmentVariables();
            sDataType = data.getDataType();
            repo.setExtent(repo.initializeExtentReports(data.getReportName(), data.getAppendReport(), utils));

            switch (data.getDataType().toUpperCase())
            {

                case "EXCEL": sheet = data.ReadExcel(sDefaultPath+Location,"Sheet1");
                    iRow = data.rowCount(sheet, record, resultset, sDataType)-1;
                    break;

                case "FILLO": record = data.ConnectFillo(sDefaultPath+Location,"Select * from Sheet1");

                    iRow = data.rowCount(sheet, record, resultset, sDataType);
                    break;

                case "SQLSERVER":resultset = data.ConnectAndQuerySQLServer(data.getDBHost(), data.getDBUsername(),data.getDBPassword(), "Select * from  [BookFlights].[dbo].[BookFlights]");
                    iRow = data.rowCount(sheet, record, resultset, sDataType);
                    resultset = data.ConnectAndQuerySQLServer(data.getDBHost(), data.getDBUsername(),data.getDBPassword(), "Select * from  [BookFlights].[dbo].[BookFlights]");
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void Collapse() throws Exception
    {
        if (utils.getWebdriver()!= null)
            utils.getWebdriver().quit();
    }
}
