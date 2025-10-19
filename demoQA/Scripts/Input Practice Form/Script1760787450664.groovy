import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

TestData excelData = findTestData('Data Files/Practice Form/Input Practice Form')

for(int i = 1; i <= excelData.getRowNumbers(); i++) {
	'Start Process'
	WS.comment('Start Process ke- ' +i)
	
	String firstName = excelData.getValue('firstName', i)
	String lastName = excelData.getValue('lastName', i)
	String userEmail = excelData.getValue('userEmail', i)
	String gender = excelData.getValue('gender', i)
	String phoneNumber = excelData.getValue('phoneNumber', i)
	String date = excelData.getValue('date', i)
	String subject = excelData.getValue('subject', i)
	String hobbies = excelData.getValue('hobbies', i)
	String picture = excelData.getValue('picture', i)
	String address = excelData.getValue('address', i)
	String state = excelData.getValue('state', i)
	String city = excelData.getValue('city', i)
	String flagCase = excelData.getValue('flagCase', i)
		
	CustomKeywords.'co.id.inputPracticeForm.inputForm.inputPracticeForm'(firstName, lastName, 
		userEmail, gender, phoneNumber, date, 
		subject, hobbies, picture, address, state, city, flagCase)
	
}