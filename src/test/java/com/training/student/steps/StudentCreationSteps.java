package com.training.student.steps;

import java.util.UUID;

import com.training.student.pages.StudentPage;
import com.training.student.support.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StudentCreationSteps {

    private final TestContext context;
    private StudentPage studentPage;

    public StudentCreationSteps(TestContext context) {
        this.context = context;
    }

    @Given("the user opens the student management page")
    public void openStudentManagementPage() {
        page().open();
    }

    @When("the user enters valid student details")
    public void enterValidStudentDetails() {
        String email = "student-" + UUID.randomUUID() + "@example.test";
        context.setGeneratedEmail(email);
        page().enterStudentDetails(
                "Test",
                "Student",
                email,
                "QA Automation"
        );
    }

    @When("submits the student form")
    public void submitStudentForm() {
        page().submit();
    }

    @Then("the student is created successfully")
    public void verifyStudentCreated() {
        assertTrue(
                page().isSuccessMessageDisplayed(),
                "The success message was not displayed."
        );
    }

    @When("the user refreshes the student list")
    public void refreshStudentList() {
        page().refreshStudentList();
    }

    @Then("the new student appears in the list")
    public void verifyStudentAppearsInList() {
        assertTrue(
                page().isStudentVisibleByEmail(context.getGeneratedEmail()),
                "The created student does not appear in the list."
        );
    }

    private StudentPage page() {
        if (studentPage == null) {
            studentPage = new StudentPage(context.getDriver());
        }
        return studentPage;
    }
}
