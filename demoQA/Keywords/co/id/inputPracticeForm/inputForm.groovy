package co.id.inputPracticeForm

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ConditionType




import internal.GlobalVariable

public class inputForm {	
	@Keyword
	def inputPracticeForm(String firstName, String lastName, String userEmail, String gender, String phoneNumber, String date, String subject, String hobbies, String picture,
		 String address, String state, String city, String flagCase) {

		try {
			WebUI.openBrowser(GlobalVariable.url)
			WebUI.maximizeWindow()

			// Hapus iklan (Google Ads)
			WebUI.executeJavaScript('document.querySelectorAll("iframe, [id^=google_ads_iframe], .adsbygoogle").forEach(e => e.remove());', null)
			WebUI.delay(1)

			// Input Form Field			
			WebUI.scrollToElement(findTestObject('Object Repository/practiceForm/01.inptByIdDynamic',[('text'): 'firstName']), 0)
			WebUI.setText(findTestObject('Object Repository/practiceForm/01.inptByIdDynamic',[('text'): 'firstName']), firstName)
			WebUI.setText(findTestObject('Object Repository/practiceForm/01.inptByIdDynamic',[('text'): 'lastName']), lastName)
			WebUI.verifyElementPresent(findTestObject('Object Repository/practiceForm/01.inptByIdDynamic',[('text'):'firstName']), 0)
		
			//Input Email
			WebUI.setText(findTestObject('Object Repository/practiceForm/01.inptByIdDynamic',[('text'): 'userEmail']), userEmail)

			
			//radio button Gender
			WebUI.click(findTestObject('Object Repository/practiceForm/02.radioBtnGenderByValueDynamic',[('label'): gender]))

			//Input Number Phone
			WebUI.setText(findTestObject('Object Repository/practiceForm/01.inptByIdDynamic',[('text'): 'userNumber']), phoneNumber)

			//Input Date Picker
			TestObject dateField = findTestObject('Object Repository/practiceForm/03.divDatePicker')
			WebUI.click(dateField)
			WebUI.sendKeys(dateField, Keys.chord(Keys.CONTROL, 'a'))
			WebUI.sendKeys(dateField, Keys.chord(Keys.SPACE))
			WebUI.setText(dateField, date)
			WebUI.sendKeys(dateField, Keys.chord(Keys.ENTER))


			// Subject
			if (subject != null && subject.trim() != '') {
				List<String> subjectList = subject.split(',').collect { it.trim() }
				for (String subject1 : subjectList) {
					WebUI.click(findTestObject('practiceForm/05.inpSubjects'))
					WebUI.setText(findTestObject('practiceForm/05.inpSubjects'), subject1)
					WebUI.sendKeys(findTestObject('practiceForm/05.inpSubjects'), Keys.chord(Keys.ENTER))
				}
			}

			// Hobbies
			if (hobbies != null && hobbies.trim() != '') {
				List<String> hobbiesList = hobbies.split(',').collect { it.trim() }
				hobbiesList.each { hobby ->
					TestObject checkbox = new TestObject("hobby_${hobby}")
					checkbox.addProperty("xpath", ConditionType.EQUALS, "//label[normalize-space(text())='${hobby}']")
					WebUI.scrollToElement(checkbox, 3)
					WebUI.waitForElementClickable(checkbox, 5)
					WebUI.click(checkbox)
				}
			}

			// Upload Picture
			if (picture != null && picture.trim() != '') {
				String filePath = "/Data Files/" + picture
				String fullFilePath = RunConfiguration.getProjectDir() + filePath
				WebUI.uploadFile(findTestObject('Object Repository/practiceForm/01.inptByIdDynamic', [('text'):'uploadPicture']), fullFilePath)
			}

			//Text Area address
			WebUI.setText(findTestObject('Object Repository/practiceForm/06.TextAreabyIdDynamic',[('textArea'):'currentAddress']), address)
			WebUI.takeScreenshot()

			// State and City
			WebUI.scrollToElement(findTestObject('practiceForm/04.divByIdDynamic', [('text'):'state']), 0)
			WebUI.click(findTestObject('practiceForm/04.divByIdDynamic', [('text'):'state']), FailureHandling.OPTIONAL)
			WebUI.click(findTestObject('practiceForm/07.divByTextDynamic', [('text'): state]), FailureHandling.OPTIONAL)
			WebUI.click(findTestObject('practiceForm/04.divByIdDynamic', [('text'):'city']), FailureHandling.OPTIONAL)
			WebUI.click(findTestObject('practiceForm/07.divByTextDynamic', [('text'): city]), FailureHandling.OPTIONAL)

			WebUI.takeScreenshot()

			// ==== SUBMIT FORM ====
			WebUI.scrollToPosition(0, 2000)
			WebUI.click(findTestObject('practiceForm/08.btnByIdDynamic', [('button'):'submit']))

			// ==== VALIDASI BERDASARKAN FLAG ====
			if (flagCase.equalsIgnoreCase('Y')) {
				// ✅ Positive Case
				WebUI.waitForElementPresent(findTestObject('practiceForm/10.vrfPopUpPresent'), 10)
				WebUI.verifyElementPresent(findTestObject('practiceForm/10.vrfPopUpPresent'), 0)
				WebUI.comment("✅ Positive case PASSED - Popup muncul sesuai ekspektasi")
				WebUI.takeScreenshot()

				// Close modal
				WebUI.executeJavaScript("document.querySelectorAll('iframe, [id^=\"google_ads_iframe\"], [id*=\"Ad.Plus\"]').forEach(e => e.remove());", null)
				WebUI.delay(1)
				WebUI.scrollToElement(findTestObject('practiceForm/08.btnByIdDynamic', [('button'):'closeLargeModal']), 3)
				WebUI.click(findTestObject('practiceForm/08.btnByIdDynamic', [('button'):'closeLargeModal']))
				WebUI.takeScreenshot()
			}
			else if (flagCase.equalsIgnoreCase('N')) {
				// ❌ Negative Case
				boolean popupNotPresent = WebUI.verifyElementNotPresent(findTestObject('practiceForm/10.vrfPopUpPresent'), 5, FailureHandling.OPTIONAL)
				if (popupNotPresent) {
					WebUI.comment("✅ Negative case PASSED - Popup tidak muncul karena input invalid")
					WebUI.takeScreenshot()
				} else {
					WebUI.comment("❌ Negative case FAILED - Popup muncul padahal input invalid")
					WebUI.takeScreenshot()
					assert false : "Negative test failed: Popup muncul padahal input invalid"
				}
			}

		} catch (Exception e) {
			WebUI.comment("⚠️ Terjadi error: ${e.message}")
			WebUI.takeScreenshot()
		} finally {
			WebUI.delay(2)
			WebUI.closeBrowser()
		}
	}
	
}
