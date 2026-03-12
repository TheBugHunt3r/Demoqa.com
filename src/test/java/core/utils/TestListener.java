package core.utils;

import core.base.UiBaseTest;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();
        if (testClass instanceof UiBaseTest) {
            ((UiBaseTest) testClass).saveScreenshot();
        }
    }
}
