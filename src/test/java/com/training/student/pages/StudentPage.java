package com.training.student.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StudentPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By firstNameInput = By.id("firstName");
    private final By lastNameInput = By.id("lastName");
    private final By emailInput = By.id("email");
    private final By classroomInput = By.id("classroom");
    private final By submitButton = By.cssSelector("button[type='submit']");
    private final By successMessage = By.cssSelector("p.success");
    private final By refreshButton = By.xpath("//button[normalize-space()='Actualiser']");

    public StudentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:4200");
        driver.get(baseUrl);
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
    }

    public void enterStudentDetails(
            String firstName,
            String lastName,
            String email,
            String classroom
    ) {
        replaceValue(firstNameInput, firstName);
        replaceValue(lastNameInput, lastName);
        replaceValue(emailInput, email);
        replaceValue(classroomInput, classroom);
    }

    public void submit() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

    public boolean isSuccessMessageDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(successMessage)
        ).isDisplayed();
    }

    public void refreshStudentList() {
        wait.until(ExpectedConditions.elementToBeClickable(refreshButton)).click();
    }

    public boolean isStudentVisibleByEmail(String email) {
        By emailCell = By.xpath(
                "//table//tbody/tr/td[4][normalize-space()='" + email + "']"
        );
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(emailCell)
        );
        return element.isDisplayed();
    }

    private void replaceValue(By locator, String value) {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
        element.clear();
        element.sendKeys(value);
    }
}
