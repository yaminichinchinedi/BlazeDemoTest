package Utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class extentReport implements ITestListener {

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("Test Method Failed: " + result.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {

        System.out.println("Test Method Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        System.out.println("Success of test method and its details : " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Failure of test method and its details : " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Skip of test method and its details : " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("The Test Class started: " + context.getName());
        System.out.println("The Test Class started Time is : " + context.getStartDate());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("The Test Class Ended: " + context.getName());
        System.out.println("The Test Class Ended Time is : " + context.getEndDate());
        System.out.println("The Total no of Tests are: " + context.getAllTestMethods().length);
        System.out.println("The Total no of Passed Tests are: " + context.getPassedTests().size());
        System.out.println("The Total no of Failed Tests are: " + context.getFailedTests().size());
        System.out.println("The STotal no of kipped Tests are: " + context.getSkippedTests().size());
    }
}
