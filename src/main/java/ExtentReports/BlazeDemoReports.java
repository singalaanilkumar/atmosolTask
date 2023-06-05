package ExtentReports;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BlazeDemoReports implements IReporter {

    private static final String OUTPUT_FOLDER = "BlazeDemoReports";
    private static final String FILE_NAME = "BlazeDemoExtentReport.html";
    public static ExtentReports extent;

    public static void assignCategories(ExtentTest test, ITestResult result) {
        for (String group : result.getMethod().getGroups()) {
            test.assignCategory(group);
        }
    }

    public void generateReport(List xmlSuites, List suites, String outputDirectory) {

        ExtentSparkReporter spark = new ExtentSparkReporter(OUTPUT_FOLDER + "/" + FILE_NAME);
        spark.config().setDocumentTitle("ExtentSparkReports - BlazeDemoAutomationFramework");
        spark.config().setReportName("ExtentSparkReports - BlazeDemo");
        spark.config().setTheme(Theme.STANDARD);
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Application", "BlazeDemo");
        extent.setSystemInfo("Environment", "Prod");
        extent.setSystemInfo("Execution Mode", "Automated");
        extent.setReportUsesManualConfiguration(true);
        for (Object suite : suites) {
            Map<String, ISuiteResult> result = ((ISuite) suite).getResults();
            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();
                if (context != null) {
                    buildTestNodes(context.getPassedTests(), Status.PASS);
                    buildTestNodes(context.getFailedTests(), Status.FAIL);
                    buildTestNodes(context.getSkippedTests(), Status.SKIP);
                }
            }
        }
        for (String s : Reporter.getOutput()) {
            extent.addTestRunnerOutput(s);
        }
        extent.flush();
    }

    private void buildTestNodes(IResultMap tests, Status status) {

        ExtentTest test = null;
        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {

                test = extent.createTest(result.getMethod().getMethodName());

                if (result.getStatus() == ITestResult.SUCCESS) {
                    test.log(status.PASS, "Test Case IS PASSED on " + result.getName() + " Method");
                } else if (result.getStatus() == ITestResult.FAILURE) {
                    test.log(status.FAIL, "TEST CASE IS FAILED on " + result.getName() + " Method");
                    test.log(status, result.getThrowable());

                } else if (result.getStatus() == ITestResult.SKIP) {
                    test.log(status.SKIP, "Test Case IS SKIPPED on " + result.getName() + "Method");
                }

                test.getModel().setStartTime(getTime(result.getStartMillis()));
                test.getModel().setEndTime(getTime(result.getEndMillis()));
                test.getModel().timeTaken();
                assignCategories(test, result);
            }
        }
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();

    }
}

