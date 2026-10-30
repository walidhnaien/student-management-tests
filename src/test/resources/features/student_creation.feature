# language: en
@ui @creation @STUA1-1
Feature: Student creation

  Scenario: Create a student with valid information
    Given the user opens the student management page
    When the user enters valid student details
    And submits the student form
    Then the student is created successfully
    When the user refreshes the student list
    Then the new student appears in the list
