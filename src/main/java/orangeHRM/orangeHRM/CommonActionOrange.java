package orangeHRM.orangeHRM;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonActionOrange {
	public WebDriver driver;
	public WebDriverWait wait;
	public ReadConfigOrange props;
	public final int WAIT_UNTIL_MAX_TIME = 60; //sec

	public CommonActionOrange(WebDriver webDriverInput, ReadConfigOrange props) {
		this.driver = webDriverInput;
		this.props = props;
	}

	/**
	 * Click on any web element
	 * 
	 * @param element
	 *            Web element to click on
	 * @since 9/5/2014
	 */
	public void clickElement(WebElement element) {
		waitUntilAjaxCallsAreDone(20000);
		waitUntilIsClickable(element, WAIT_UNTIL_MAX_TIME);
		if (isExistingElement(element))
		{
			if(props.getPathFF().equals("firefox")){
				element.click();
			}
			else
				element.click();			
		}
		waitUntilAjaxCallsAreDone(20000);
	}
	
	public void clickElementJS(WebElement element) {
	    
	    if (isExistingElement(element)) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("$(arguments[0]).trigger('click')",element);
	    }
	    waitUntilAjaxCallsAreDone(20000);
	    waitUntilDOMisReady(20000);
	}

	/**
	 * This method is used to get a value from a web element
	 * 
	 * @param element
	 *            Web element used to get the value
	 * @return <code>Attribute value</code> Returns the web element value
	 *         <P>
	 * @since 10/8/2014
	 */
	public String getValue(WebElement element) throws InterruptedException {
		isExistingElement(element);
		return element.getAttribute("value");
	}

	/**
	 * This method is used to get a text value of a web element
	 * 
	 * @param element
	 *            Web element used to get the text
	 * @return <code>Text value</code> Returns the text of web element used
	 *         <P>
	 * @since 10/8/2014
	 */
	public String getElementText(WebElement element) throws InterruptedException {
		isExistingElement(element);
		return element.getText();
	}
	
	/**
	 * For input elements, the displayed text is not wrapped by the <input> tag, instead it's inside the value attribute.
	 * @param element
	 * @return text value of a web element
	 * @throws InterruptedException
	 */
	public String getElementValue(WebElement element) throws InterruptedException {
		isExistingElement(element);
		return element.getAttribute("value").toString();
	}
	
	/**
	 * This method is used to find a value inside a web table
	 * 
	 * @param table
	 *            Web table used to do the search
	 * @param getValue
	 *            Get the value for any web table
	 * @return <code>Table element value</code> Returns the value of any web
	 *         table
	 *         <P>
	 * @since 10/8/2014
	 */
	public boolean findTableElement(WebElement table, String getValue) {
		try {
			String current_value;

			if (!isExistingElement(table)) {
				System.out.println("table does not exist");
				return false;
			}
			List<WebElement> allRows = table.findElements(By.tagName("tr"));
			for (WebElement row : allRows) {
				highlightElement(row);
				List<WebElement> columns = row.findElements(By.tagName("td"));
				for (WebElement column : columns) {
					highlightElement(column);
					current_value = column.getText();
					if (current_value.equals(getValue)) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Exception in findTableElement: " + e.getMessage());
		}
		return false;
	}

	/**
	 * This method is used to find if all grids columns are checked
	 * 
	 * @param table
	 *            Web table used to do the search
	 * @param checkState
	 *            true if we validate that all elements should be checked or
	 *            unchecked
	 * @return <boolean> True if all elements are checked
	 *         <P>
	 * @since 01/26/2015
	 */
	public boolean verifyTableElementCheckBoxState(WebElement table, boolean checkState) {
		boolean isselected = false;
		isExistingElement(table);
		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		System.out.println("tr number =" + allRows.size());

		if (checkState) {
			for (WebElement row : allRows) {
				WebElement col = row.findElement(By.className("text-center"));

				if (!col.findElement(By.tagName("input")).isSelected())
					return isselected;
			}
			isselected = true;
		}

		else {
			for (WebElement row : allRows) {
				WebElement col = row.findElement(By.className("text-center"));

				if (col.findElement(By.tagName("input")).isSelected())
					return isselected;
			}
			isselected = true;
		}
		return isselected;
	}


	/**
	 * This method is used to find if all div inputs are checked
	 * 
	 * @param div
	 *            Web div element used to do the search
	 * @param checkState
	 *            true if we validate that all elements should be checked or
	 *            unchecked
	 * @return <boolean> True if all elements are checked
	 *         <P>
	 * @since 01/26/2015
	 */
	public boolean verifyDivElementCheckBoxState(WebElement div, boolean checkState) {
		boolean isselected = false;
		isExistingElement(div);
		List<WebElement> allRows = div.findElements(By.tagName("input"));
		System.out.println("input number =" + allRows.size());

		if (checkState) {
			for (WebElement row : allRows) {
				if (!row.isSelected())
					return isselected;
			}
			isselected = true;
		}

		else {
			for (WebElement row : allRows) {
				if (row.isSelected())
					return isselected;
			}
			isselected = true;
		}
		return isselected;
	}

	/**
	 * This method is used to get the total rows of a table
	 * 
	 * @param table
	 *            Web table used to get the total of rows
	 * @return <code>Rows</code> Total of rows in a table
	 *         <P>
	 * @since 10/8/2014
	 */
	public int TotaltGridRows(WebElement table) {
		isExistingElement(table);
		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		return allRows.size() - 1;

	}

	/**
	 * This method is used to know if an element is displayed
	 * 
	 * @param webElement
	 *            Web element used to validate if displayed or not
	 * @return <code>true</code> Element is displayed in the page
	 *         <P>
	 * @return <code>false</code> Element is not displayed in the page
	 *         <P>
	 * @since 10/8/2014
	 */
	public boolean isElementDisplayed(WebElement webElement) {
		try {
			wait(webElement, 10);
		} catch (Exception e) {
			System.out.println("Exception in isElementDisplayed: " + e.getMessage());
			return false;
		}
		return webElement.isDisplayed() && webElement.isEnabled();
	}

	/**
	 * This method is used to know if an element is displayed
	 * 
	 * @param webElement
	 *            Web element used to validate if displayed or not
	 * @return <code>true</code> Element is displayed in the page
	 *         <P>
	 * @return <code>false</code> Element is not displayed in the page
	 *         <P>
	 * @since 10/8/2014
	 */
	public boolean isElementNotDisplayed(WebElement webElement) {
		boolean isElementNotDisplayed = false;
		if (!(webElement.isDisplayed())) {
			isElementNotDisplayed = true;
		}

		return isElementNotDisplayed;
	}

	/**
	 * This method is used to edit a web element
	 * 
	 * @param edit
	 *            Web element to set a value
	 * @param inputText
	 *            Text to enter in the web element
	 * @since 10/8/2014
	 */
	public void setEdit(WebElement edit, String inputText) {
	    wait(edit, 15);
	    if (edit.isEnabled()) {
		edit.clear();
		edit.sendKeys(inputText);
	    }
	    waitUntilAjaxCallsAreDone(20000);	
	}
	
	/**
	 * This method is used to edit a web element
	 * 
	 * @param edit
	 *            Web element to set a value
	 * @param inputText
	 *            Text to enter in the web element
	 * @since 10/8/2014
	 */
	public void setValueForNotEditableElement(WebElement edit, String inputText) {
		wait(edit, 15);			
		edit.sendKeys(inputText);
		
	}

	/**
	 * This method is used to know if an element exist in the page
	 * 
	 * @param element
	 *            Web element used to know if exist
	 * @return <code>true</code> Element exist in the page
	 *         <P>
	 * @return <code>false</code> Element does not exist in the page
	 *         <P>
	 * @since 10/8/2014
	 */
	public boolean isExistingElement(WebElement element) {
		boolean exist = false;
		try {
			wait(element, 3);
			exist = element.isDisplayed();
			if (exist) {
			    	scrollInViewForElement(element);
				highlightElement(element);
			}
		} catch (Exception e) {
			return false;
		}
		return exist;
	}

    public boolean isElementDisabled(WebElement element) {
	boolean disabledStatus = false;
	try {
	    wait(element, 3);
	    if (isExistingElement(element)) {
		highlightElement(element);
		if (element.isEnabled())
		    disabledStatus = false;
		else
		    disabledStatus = true;
		return disabledStatus;
	    }
	} catch (Exception e) {
	}
	return disabledStatus;
    }
	
	/**
	 * This method is used to highlight an element in the page
	 * 
	 * @param element
	 *            Web element to be highlighted
	 * @throws InterruptedException
	 * @since 10/8/2014
	 */
	public void highlightElement(WebElement element) throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: red; border: 2px solid red;");

		Thread.sleep(300);

		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");

	}

	
	/**
	 * This method is used to select a checkBox element using JavaScript 
	 * @param element
	 *            Web element to be set	 *           
	 * @throws InterruptedException
	 * @since 29/6/2017
	 */
	public void selectCheckBoxJS(WebElement element) throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("$(arguments[0]).trigger('click')",element);	

	}
	/**
	 * This method is used to wait for any element to be visible in any page
	 * 
	 * @param element
	 *            Web element to wait for visibility
	 * @param timer
	 *            Time in seconds used for waiting the object given
	 * @since 10/16/2014
	 */
	public void wait(WebElement element, int timer) {
		wait = new WebDriverWait(driver, timer);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * Wait until element is clickable
	 * 
	 * @param element
	 * @param timer seconds
	 */
	public void waitUntilIsClickable(WebElement element, int timer) {
		wait = new WebDriverWait(driver, timer);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void waitForPageToLoad(long maxTime){
	    boolean pageLoaded = false;
	    long currentTime = System.currentTimeMillis();
	    long repeatUntilTime = currentTime + maxTime;
	    try {    
		while (!pageLoaded && (currentTime < repeatUntilTime)) {
		    WebElement loadingSign = driver.findElement(By.cssSelector("div.loadingcontainer"));    
		    if (!isExistingElement(loadingSign)) {
			pageLoaded = true;
		    } else {
			Thread.sleep(500);
		    }
		    currentTime = System.currentTimeMillis();
		} 
	    } catch (InterruptedException e) {}
	}
	
	public WebElement findDynamicElement(By by, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		return element;
	}

	/**
	 * This method is used to select a specific element from a drop down list
	 * 
	 * @param dropdown
	 *            - drop down list id
	 * @param searchValue
	 *            - DD element to select
	 * @since 09/16/2014
	 */
	public void dropDownSelectElement(WebElement dropdown, String searchValue) {
		List<WebElement> allValues = dropdown.findElements(By.xpath(".//li"));
		searchValue = searchValue.trim().toLowerCase();

		for (WebElement we : allValues) {
			try {
				String litext = we.getAttribute("innerText").trim().toLowerCase();
				if (litext.equals(searchValue) || dropdown.getText().equals(searchValue)) {
					clickElement(we);
					break;
				}
			} catch (Exception e) {
				System.out.println("Exception in dropDownSelectElement: " + e.getMessage());
			}
		}
	}


	/**
	 * This method is used to select first element from a drop down list
	 * 
	 * @param dropdown
	 *            -drop down list id
	 * @since 03/05/2014
	 */
	public void dropDownSelectFirstElement(WebElement dropdown) {
		wait(dropdown, 10);

		List<WebElement> allValues = dropdown.findElements(By.xpath(".//li"));
		System.out.println("first value= "+allValues.get(1).getText());
		clickElement(allValues.get(1));

	}




	/**
	 * Return the elements which is visible from a list of elements with same
	 * selector
	 * 
	 * @param elementsWithSameSelector
	 * @return
	 */
	public WebElement getVisibleElementFromList(List<WebElement> elementsWithSameSelector) {
		for (WebElement elem : elementsWithSameSelector) {
			if (elem.isDisplayed()) {
				return elem;
			}
		}
		return null;
	}

	/**
	 * Wait until all ajax calls are done, in maximum maxTime millis
	 * 
	 * @param maxTime
	 */
	public void waitUntilAjaxCallsAreDone(long maxTime) {
		long currentTime = System.currentTimeMillis();
		long repeatUntilTime = currentTime + maxTime;
		boolean runningAjax = true;

		while (runningAjax && (currentTime < repeatUntilTime)) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			boolean ajaxFinished = (Boolean) jsExecutor.executeScript("return jQuery.active == 0");
			if (ajaxFinished) {
				runningAjax = false;
			} else {
				try {
					Thread.sleep(600);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			currentTime = System.currentTimeMillis();
		}
	}
	
	public void waitUntilDOMisReady(long maxTime){	    
	    long currentTime = System.currentTimeMillis();
	    long repeatUntilTime = currentTime + maxTime;
	    boolean DOMnotReady = true;
	    
	    while (DOMnotReady && (currentTime < repeatUntilTime)) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean isDOMready = (Boolean) jsExecutor.executeScript("return document.readyState").equals("complete");
		if (isDOMready) {
		    DOMnotReady = false;
		} else {
			try {
				Thread.sleep(600);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		currentTime = System.currentTimeMillis();
	    }
	}

	/**
	 * Return the number of elements that have a specific path
	 * 
	 * @return <code>String with error message</code> if error found
	 *         <code>number of elements found </code> if no error found
	 * @since 03/11/2015
	 */
	public int getNumberOfElementsFound(By by) {
		return driver.findElements(by).size();
	}

	/**
	 * Return the element from a list from a given position
	 * 
	 * @return <code>String with error message</code> if error found
	 *         <code>WebElement </code> if no error found
	 * @since 03/11/2015
	 */
	public WebElement getElementWithIndex(By by, int pos) {
		return driver.findElements(by).get(pos);
	}

	/**
	 * Return the absolute path of a file
	 * 
	 * @return <code>String with error message</code> if error found
	 *         <code>full path</code> if not error found
	 * @since 03/11/2015
	 */
	public String getImageAbsolutePath(String relativePath) {

		File f = new File(relativePath);
		return f.getAbsolutePath();
	}

	/**
	 * Validate that error div notify is empty Div error is empty when no error
	 * was returned after an action was executed
	 * 
	 * @return <code>String with error message</code> if error found
	 *         <code>true</code> if not error found and div is empty
	 * @since 03/11/2015
	 */
	public boolean validateDivErrorContent(WebElement divError) {
		String divContent = divError.getText().trim();

		if (divContent.equals("") || divContent.startsWith("info")) {
			return true;
		}
		return false;
	}

	/**
	 * Drag and drop an element to another element
	 * 
	 * @param fromElement
	 * @param toElement
	 */
	@SuppressWarnings("deprecation")
	public void performDragAndDrop(WebElement fromElement, WebElement toElement) {
		Robot robot;
		try {
			robot = new Robot();
			robot.mouseMove(fromElement.getLocation().getX(), fromElement.getLocation().getY());
		} catch (AWTException e) {
			e.printStackTrace();
		}

		Actions builder = new Actions(driver);

		Action dragAndDrop;

		if (fromElement.getLocation().getY() > toElement.getLocation().getY()) {
			dragAndDrop = builder.clickAndHold(fromElement).moveToElement(toElement, 0, 1).pause(1000)
					.moveToElement(toElement, 0, 2).pause(3000).release(toElement).build();
		} else {
			dragAndDrop = builder.clickAndHold(fromElement)
					.moveToElement(toElement, 0, fromElement.getSize().getHeight() * 2 + 2).pause(1000)
					.moveToElement(toElement, 0, fromElement.getSize().getHeight() * 2 + 1).pause(3000).release(toElement)
					.build();
		}

		dragAndDrop.perform();
	}

	/**
	 * Scroll in view for an element; Must be provided the element css path
	 * 
	 * @param cssPath
	 */
	public void scrollInViewForElement(String cssPath) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("$('" + cssPath + "')[0].scrollIntoView(true);");
	}

	/**
	 * Get the n-th item form a grid
	 * 
	 * @param gridId
	 * @param position
	 * @return
	 */
	public WebElement getNthRowFromGrid(String gridId, int position) {
		try {
			List<WebElement> rows = (List<WebElement>) driver.findElements(By.cssSelector("#" + gridId + " tr"));
			return rows.get(position);
		} catch (Exception e) {
			System.out.println("Exception in getNthRowFromGrid(): " + e.getMessage());
		}
		return null;
	}

	/**
	 * Get the n-th item form a grid
	 * 
	 * @param grid
	 * @param position
	 * @return
	 */
	public WebElement getNthRowFromGrid(WebElement grid, int position) {
		try {
			List<WebElement> rows = (List<WebElement>) grid.findElements(By.cssSelector("tr"));
			return rows.get(position);
		} catch (Exception e) {
			System.out.println("Exception in getNthRowFromGrid(): " + e.getMessage());
		}
		return null;
	}	

	/**
	 * Get the first item for a grid
	 * 
	 * @param gridId
	 * @return
	 */
	public WebElement getFirstRowFromGrid(String gridId) {
		return getNthRowFromGrid(gridId, 1);
	}

	/**
	 * Get the first item for a grid
	 * 
	 * @param grid
	 * @return
	 */
	public WebElement getFirstRowFromGrid(WebElement grid) {
		return getNthRowFromGrid(grid, 1);
	}

	/**
	 * Open the edit page for a grid item
	 * 
	 * @param gridId
	 * @param position
	 *            : First row is '1'
	 */
	public void openEditItemFromGrid(String gridId, int position) {
		try {
			WebElement selectedItem = getNthRowFromGrid(gridId, position);

			clickElement(selectedItem.findElement(By.className("glyphicon-edit")));
		} catch (Exception e) {
			System.out.println("Exception in openEditItemFromGrid: " + e.getMessage());
		}
	}

	public void openEditItemFromAppointmentTable(WebElement gridId, int position) {
		try {
			WebElement selectedItem = getNthRowFromGrid(gridId, position);

			clickElement(selectedItem.findElement(By.cssSelector("[ng-click='editAppoiment']")));
		} catch (Exception e) {
			System.out.println("Exception in openEditItemFromGrid: " + e.getMessage());
		}
	}

	public void openEditItemFromContactsGrid(WebElement gridId, int position) {
		try {
			WebElement selectedItem = getNthRowFromGrid(gridId, position);

			clickElement(selectedItem.findElement(By.xpath("//span[@class='glyphicon glyphicon-edit cursorPointer']")));
		} catch (Exception e) {
			System.out.println("Exception in openEditItemFromGrid: " + e.getMessage());
		}
	}


	/**
	 * Scroll in view for an element list; Must be provided the element css path
	 * and index
	 * 
	 * @param cssPath
	 * @param index
	 */
	public void scrollInViewForElementList(String cssPath, int index) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String command = "$('" + cssPath + "')[" + index + "].scrollIntoView(true);";
		js.executeScript(command);
	}

	/**
	 * Scroll in view for an element; Must be provided the web element
	 * 
	 * @param cssPath
	 */
	public void scrollInViewForElement(WebElement element) {
		int heightPos = element.getLocation().getY();
		JavascriptExecutor js = (JavascriptExecutor) driver;

		String command = "window.scrollTo(0, " + heightPos + "-(window.innerHeight / 2));";
		js.executeScript(command);
	}

	/**
	 * Expand a kendo multi select (need to provide the select id)
	 * 
	 * @param multiSelectId
	 */
	public void expandKendoMultiSelectById(String multiSelectId) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String command = "$('#" + multiSelectId + "').data('kendoMultiSelect').open()";
		js.executeScript(command);
	}

	/**
	 * Close a kendo multi select (need to provide the select id)
	 * 
	 * @param multiSelectId
	 */
	public void closeKendoMultiSelectById(String multiSelectId) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String command = "$('#" + multiSelectId + "').data('kendoMultiSelect').close()";
		js.executeScript(command);
	}

	/**
	 * Expands the dropdown and select a value; If the value is not found the
	 * dropdown is closed
	 * 
	 * @param expandDD
	 * @param dropdown
	 * @param selectValue
	 */
	public void expandDropDownAndSelectElement(WebElement expandDD, WebElement dropdown, String selectValue) {
	    try {
		boolean isExistingValue = false;
		clickElement(expandDD);
		List<WebElement> allValues = dropdown.findElements(By.xpath(".//li"));
		selectValue = selectValue.trim().toLowerCase();
		for (WebElement we : allValues) {
		    String litext = we.getAttribute("innerText").trim().toLowerCase();
		    if (litext.equals(selectValue) || dropdown.getText().equals(selectValue)) {
			clickElement(we);
			isExistingValue = true;
			break;
		    }
		}
		if (!isExistingValue) {
		    clickElement(expandDD);
		}
		
	    } catch (Exception e) {
		System.out.println("Exception in dropDownSelectElement: " + e.getMessage());
	    }
	    waitUntilAjaxCallsAreDone(20000);
	}

	/**
	 * Expands the dropdown and select a value [option list]; If the value is not found the
	 * dropdown is closed
	 * 
	 * @param expandDD
	 * @param dropdownOption
	 * @param selectValue
	 */
	public void expandDropDownAndSelectElementOption(WebElement expandDD, WebElement dropdownOption, String selectValue) {
	    try {
		boolean isExistingValue = false;
		clickElement(expandDD);
		List<WebElement> allValues = dropdownOption.findElements(By.xpath(".//option"));
		selectValue = selectValue.trim().toLowerCase();

		for (WebElement we : allValues) {
		    String litext = we.getAttribute("innerText").trim().toLowerCase();
		    if (litext.equals(selectValue) || dropdownOption.getText().equals(selectValue)) {
			clickElement(we);
			isExistingValue = true;
			break;
		    }
		}
		if (!isExistingValue) {
		    clickElement(expandDD);
		}

	    } catch (Exception e) {
		System.out.println("Exception in dropDownSelectElement: " + e.getMessage());
	    }
	    waitUntilAjaxCallsAreDone(20000);
	}
	
	/**
	 * Expands the dropdown and hovers over a value to make available the submenu it contains; 
	 * If the value is not found the dropdown is closed.
	 * @param expandDD
	 * @param dropdown
	 * @param hoverOverValue
	 */
	public void expandDropDownAndHoverOverElement(WebElement expandDD, WebElement dropdown, String hoverOverValue) {
	    try {
		Actions builder = new Actions(driver);
		boolean isExistingValue = false;
		clickElement(expandDD);
		List<WebElement> allValues = dropdown.findElements(By.xpath(".//li"));
		hoverOverValue = hoverOverValue.trim().toLowerCase();
		for (WebElement we : allValues) {
		    String litext = we.getAttribute("innerText").trim().toLowerCase();
		    if (litext.equals(hoverOverValue) || dropdown.getText().equals(hoverOverValue)) {
			builder.moveToElement(we).perform();
			isExistingValue = true;
			break;
		    }
		}
		if (!isExistingValue) {
		    clickElement(expandDD);
		}
		
	    } catch (Exception e) {
		System.out.println("Exception in dropDownSelectElement: " + e.getMessage());
	    }
	}
	
	
	/**
	 * Expands the kendo multiselect and select a value; If the value is not
	 * found the multiselect is closed
	 * 
	 * @param multiSelectId
	 * @param dropdown
	 * @param selectValue
	 */
	public void expandMultiselectAndSelectElement(String multiSelectId, String selectValue) {
		try {
			WebElement dropdown = driver.findElement(By.id(multiSelectId + "-list"));

			boolean isExistingValue = false;
			expandKendoMultiSelectById(multiSelectId);

			List<WebElement> allValues = dropdown.findElements(By.xpath(".//li"));

			selectValue = selectValue.trim().toLowerCase();

			for (WebElement we : allValues) {

				String litext = we.getAttribute("innerText").trim().toLowerCase();
				if ((litext.equals(selectValue) || dropdown.getText().equals(selectValue)) && we.isDisplayed()) {
					clickElement(we);
					isExistingValue = true;
					break;
				}

			}

			if (!isExistingValue) {
				closeKendoMultiSelectById(multiSelectId);
			}

			waitUntilAjaxCallsAreDone(40000);
		} catch (Exception e) {
			System.out.println("Exception in KendoMultiSelect: " + e.getMessage());
		}
	}

	/**
	 * Expands the dropdown and select a value; If the value is not found the
	 * dropdown is closed
	 * 
	 * @param expandDD
	 * @param dropdown
	 * @param selectValue
	 */
	public String getSelectedDDElement(WebElement expandDD, WebElement dropdown) {
		String selectValue="";
		try {
			boolean isExistingValue = false;
			clickElement(expandDD);
			List<WebElement> allValues = dropdown.findElements(By.xpath(".//li"));


			for (WebElement we : allValues) {

				String litext = we.getAttribute("aria-selected").trim().toLowerCase();
				if (litext.equals(selectValue) || dropdown.getText().equals(selectValue)) {
					clickElement(we);
					isExistingValue = true;
					break;
				}

			}

			if (!isExistingValue) {
				clickElement(expandDD);
			}

		} catch (Exception e) {
			System.out.println("Exception in dropDownSelectElement: " + e.getMessage());
		}
		return selectValue;
	}
	/**
	 * Count the number of occurrence of a string in another string
	 * 
	 * @param sourceString
	 * @param searchedString
	 * return -the number of occurrence of searchedString in sourceString
	 */
	public int countOccuranceOfChar(String sourceString, String searchedString){
		int index = sourceString.indexOf(searchedString);
		int count = 0;
		while (index != -1) {
			count++;
			sourceString = sourceString.substring(index + 1);
			index = sourceString.indexOf(searchedString);
		}
		System.out.println("No of *is* in the input is : " + count);
		return count;
	}


	public void searcheElement(By by, String inputText) {

		//	edit.clear();
		//edit.sendKeys(inputText);
	}

	public void setEdit(WebElement element, Keys key) {
		wait(element, 2);
		if (element.isEnabled()) {
			element.sendKeys(key);
		}

	}
    /**
     * Get the text value from a table cell based on row number and column number 
     * @param table
     * @param rowNo
     * @param colNo
     * @return cell text value
     * @throws InterruptedException 
     */
    public String getTableCellValue(WebElement table, int rowNo, int colNo) throws InterruptedException {
	if (isExistingElement(table)){
	    WebElement tableBody = table.findElement(By.tagName("tbody"));
	    List<WebElement> rows = tableBody.findElements(By.tagName("tr"));
	    WebElement row = rows.get(rowNo);
	    List<WebElement> cols = row.findElements(By.tagName("td"));
	    WebElement col = cols.get(colNo);
	    highlightElement(col);
	    //System.out.println("Cell value is: " + col.getText().toString());
	    return col.getText();
	} else
	    System.err.println("Table not found.");
	return null;
    }
    
    /**
     * validate that the text in a cell is the same as the provided search value
     * count starts from 0
     * @param table
     * @param rowNo
     * @param colNo
     * @param searchValue
     * @return true if values match
     * @throws InterruptedException
     */
    public boolean validateTableSearchResult(WebElement table, int rowNo, int colNo, String searchValue) throws InterruptedException {
	boolean status = false;
	try {
	    if (getTableCellValue(table, rowNo, colNo).equalsIgnoreCase(searchValue))
	        status = true;
	    return status;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return status;
    }

    /**
     *  validate that the text in a cell contains the provided search value 
     * (not case sensitive)
     * @param table
     * @param rowNo
     * @param colNo
     * @param searchValue
     * @return boolean
     * @throws Exception
     */
    public boolean validateTableSearchResultContainsValue(WebElement table, int rowNo, int colNo, String searchValue) throws Exception {
	boolean status = false;
//	System.out.println("get cell value: " + getTableCellValue(table, rowNo, colNo).toLowerCase());
//	System.out.println("contains value: " + searchValue.toLowerCase());
	try {
	    if (getTableCellValue(table, rowNo, colNo).toLowerCase().contains(searchValue.toLowerCase()))
	        status = true;
	    return status;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return status;
    }
    
    public boolean validateTableColumnValues(WebElement table, int colNo, String searchValue) throws InterruptedException {
	boolean status = false;
	int counter = 0;
	try {
	    WebElement tableBody = table.findElement(By.tagName("tbody"));
	    List<WebElement> rows = tableBody.findElements(By.tagName("tr"));
	    for (WebElement row : rows) {
		List<WebElement> cols = row.findElements(By.tagName("td"));
		WebElement col = cols.get(colNo);
		highlightElement(col);
		//System.out.println("-> value is: " + col.getText());
		if (col.getText().equalsIgnoreCase(searchValue) || col.getText().toLowerCase().contains(searchValue.toLowerCase())) {
		    counter++;
		}
	    }
	    System.out.println("Value found in " + counter + " row(s) out of " + rows.size()+".");
	    if (counter == rows.size())
		status = true;

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return status;
    }
    
    
    /**
     * verify that 'No data available' message is displayed in the table
     * 
     * @return true if no records are returned
     * @throws InterruptedException
     */
    public boolean validateTableSearchNoResult(WebElement noDataAvailable, String noDataAvailableMsg) throws InterruptedException {
	boolean status = false;
	if (getElementText(noDataAvailable).equalsIgnoreCase(noDataAvailableMsg))
	    status = true;
	return status;
    }
    
    /**
     * search in table body
     * @param table
     * @param rowNo
     * @param colNo
     * @return WebElement
     * @throws Exception
     */
    public WebElement getTableCellElement(WebElement table, int rowNo, int colNo) throws Exception {
	if (!isExistingElement(table))
	    System.err.println("Table not found.");

	WebElement tableBody = table.findElement(By.tagName("tbody"));
	List<WebElement> rows = tableBody.findElements(By.tagName("tr"));
	WebElement row = rows.get(rowNo);
	List<WebElement> cols = row.findElements(By.tagName("td"));
	WebElement col = cols.get(colNo);
	//highlightElement(col);
	return col;
    }
    
    /**
     * count starts at 1
     * @param tableId
     * @param colNo
     * @return list of WebElements (column rows)
     * @throws Exception
     */
    public List<WebElement> getTableCellElementForColumn(WebElement table, int colNo) throws Exception {
	List<WebElement> colRows = table.findElements(By.cssSelector("tbody>tr>td:nth-child("+colNo+")"));
	return colRows;
    }
        
    public boolean isValueInTable(WebElement table, String searchValue) throws InterruptedException {
	WebElement tableBody = table.findElement(By.tagName("tbody"));
	List<WebElement> rows = tableBody.findElements(By.tagName("tr"));

	for (WebElement row : rows) {
	    List<WebElement> cols = row.findElements(By.tagName("td"));
	    for (WebElement col : cols) {
		if (col.getText().equalsIgnoreCase(searchValue)) {
		    highlightElement(col);
		    System.out.println("Cell value is: " + col.getText());
		    return true;
		}
	    }
	}
	return false;
    }
      
    public boolean isElementSelected(WebElement element){
	boolean isSelected = false;
	try {
	    highlightElement(element);
	    if(element.isSelected()){
		isSelected = true;
	    }
	    return isSelected;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return isSelected;
    }
    
    /**
     * method used to click on a gender radio button based on genderType provided (male or female)
     * @param genderType
     * @param male
     * @param female
     */
    public void setGenderRadioBtn(String genderType, WebElement male, WebElement female){
	try {
	    if (genderType.equalsIgnoreCase("Male")) {
	        clickElement(male);
	        //System.out.println("selected gender is Male");
	    } else if (genderType.equalsIgnoreCase("Female")) {
	        clickElement(female);
	        //System.out.println("selected gender is Female");
	    } else {
	        System.out.println("gender is not defined");
	    }
	    waitUntilAjaxCallsAreDone(3000);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    /**
     * method used to click on a gender radio button based on genderType provided (male, female or either)
     * @param genderType
     * @param male
     * @param female
     * @param either
     */
    public void setGenderRadioBtn(String genderType, WebElement male, WebElement female, WebElement either){
	try {
	    if (genderType.equalsIgnoreCase("Male")) {
	        clickElement(male);
	        System.out.println("selected gender is Male");
	    } else if (genderType.equalsIgnoreCase("Female")) {
	        clickElement(female);
	        System.out.println("selected gender is Female");
	    } else {
	        clickElement(either);
	        System.out.println("selected gender is Either");
	    }
	    waitUntilAjaxCallsAreDone(3000);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    /**
     * provide the month number to get the month name
     * @param monthNumber
     * @return month name
     */
    public String getMonthName(String monthNumber) {
	String monthName;
	switch (monthNumber) {
	case "01":
	    monthName = "January";
	    break;
	case "02":
	    monthName = "February";
	    break;
	case "03":
	    monthName = "March";
	    break;
	case "04":
	    monthName = "April";
	    break;
	case "05":
	    monthName = "May";
	    break;
	case "06":
	    monthName = "June";
	    break;
	case "07":
	    monthName = "July";
	    break;
	case "08":
	    monthName = "August";
	    break;
	case "09":
	    monthName = "September";
	    break;
	case "10":
	    monthName = "October";
	    break;
	case "11":
	    monthName = "November";
	    break;
	case "12":
	    monthName = "December";
	    break;
	default:
	    monthName = "Invalid month";
	    break;
	}
	System.out.println("The month is: " + monthName);
	return monthName;
    }
    
    public WebElement getConfirmBtnInPopUp(){
	WebElement confirmBtn = driver.findElement(By.cssSelector("button.md-confirm"));
	return confirmBtn;
    }
    
    public WebElement getCloseBtnInPopUp(){
	WebElement closeBtn = driver.findElement(By.cssSelector("button.md-close"));
	return closeBtn;
    }

    public void rightClickElement(WebElement element) {
	Actions Action = new Actions(driver);
	if (isExistingElement(element)) {
	    waitUntilIsClickable(element, 2);
	    Action.moveToElement(element);
	    Action.contextClick(element).build().perform();
	}
	waitUntilAjaxCallsAreDone(5000);
    }
    
    public void rightClickSelectOption(WebElement element, String optionTxt) {
	Actions Action = new Actions(driver);
	if (isExistingElement(element)) {
	    waitUntilIsClickable(element, 2);
	    Action.moveToElement(element);
	    Action.contextClick(element).build().perform();
	}
	WebElement rcOption = driver.findElement(By.linkText(optionTxt));
	clickElement(rcOption);
    }
    
    public int getTableRowCount(WebElement table) throws Exception {
	if (!isExistingElement(table))
	    System.err.println("Table not found.");
	WebElement tableBody = table.findElement(By.tagName("tbody"));
	List<WebElement> rows = tableBody.findElements(By.tagName("tr"));
	int rowCount = rows.size();
	return rowCount;
    }
    
    public WebElement getTableRowElement(WebElement table, int rowNo) throws Exception {
	if (!isExistingElement(table))
	    System.err.println("Table not found.");

	WebElement tableBody = table.findElement(By.tagName("tbody"));
	List<WebElement> rows = tableBody.findElements(By.tagName("tr"));
	WebElement row = rows.get(rowNo);
	highlightElement(row);
	return row;
    }
    
    /**
     * Get current date with format: 'MM/dd/yyyy'
     * @return date toString
     */
    public String getCurrentDate(){
	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	Date currentDate = new Date();   
        return dateFormat.format(currentDate).toString();
    }
    
    /**
     * Add or substract days from given date
     * @param String 'date'
     * @param int 'days'
     * @return date as string in 'MM/dd/yyyy' format
     * @throws ParseException
     */
    public String addDays(String date, int days) throws ParseException {
	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	Calendar cal = Calendar.getInstance();
	cal.setTime(dateFormat.parse(date));
	cal.add(Calendar.DATE, days); // minus number would decrement the days
	return dateFormat.format(cal.getTime());
    }
       
    /**
     * format time from 12h to 24h format
     * @param startTime
     * @return time as string
     * @throws Exception
     */
    public String formatTime(String time) throws Exception{
	DateFormat timeFormat12 = new SimpleDateFormat("hh:mm a");
	DateFormat timeFormat24 = new SimpleDateFormat("HH:mm");	
	Date getTime = timeFormat12.parse(time);
	String formatTime = timeFormat24.format(getTime);
	return formatTime;
    }
    
    /**
     * convert duration to number of minutes; 
     * sample formats: '1 h 30 m', '30m', '2 h', "60"
     * @param duration 
     * @return int timeUnits
     * @throws Exception 
     */
    public int formatDuration(String duration) throws Exception {
	int timeUnits;
	if (duration.contains("h")) {
	    String hours = duration.substring(0, duration.indexOf("h")).trim();
	    timeUnits = (int) ((Integer.parseInt(hours) / 0.5) * 30);
	    if (duration.contains("m"))
		timeUnits = timeUnits + 30;
	} else if (duration.contains("m")){
	    timeUnits = Integer.parseInt(duration.replaceAll("[^0-9]", ""));
	} else if (Integer.parseInt(duration.trim()) % 30 == 0){
	    timeUnits = Integer.parseInt(duration.trim());
	} else {
	    throw (new Exception("Invalid duration format."));
	}
	return timeUnits;
    }
	
}
