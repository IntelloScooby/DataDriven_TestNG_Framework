package tests;

import initialization.Initialize;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utilities.TestUtils;

public class TestCase1 extends Initialize{
	
	TestUtils util = new TestUtils();
	String testName = "TestCase1";
	boolean toExecuteTest = false;
	
	@BeforeClass
	public void checkRunMode(){
		toExecuteTest = util.isExecutable(testName);
	}
	
	@Test(dataProvider="getData")
	public void sampleTest (Hashtable<String, String> table){
		if (toExecuteTest){
			System.out.println(table.get("Message"));
			appLog.debug("TestCase1 executed");
		}
		else{
			throw new SkipException("TestCase1 is skipped");
		}
		
	}
	
	@DataProvider
	public Object[][] getData (){
	
		return util.getParametersFromExcel(testName);
		
	}
	
}
