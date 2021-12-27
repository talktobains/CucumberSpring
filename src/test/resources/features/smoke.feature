Feature: Smoke Tests

  @smoke
  Scenario: Login to application
    Given I click on SignIn button on Homepage
    When I login to application using "iamdeepakbains@gmail.com" and "Selenium1012"
    Then I am able to Login with the given User

  @smoke
  Scenario: Login to application
    Given I click on SignIn button on Homepage
    When I login to application using "iamdeepakbains@gmail.com" and "Selenium101"
    Then I am able to Login with the given User

  @smoke
  Scenario: Order TShirt and Verify Order
    Given I press the T-Shirts button in the home page navigation bar
    When I place and confirm an order for a new TShirt
    Then The corresponding Order gets displayed in Order History

  @smoke
  Scenario Outline: Update Personal Information in My Account
    Given I navigate to MyAccount
    When I update FirstName as "<givenName>"
    Then I verify FirstName has been updated to "<expectedName>"
    Examples:
      | givenName  | expectedName |
      | DeepakABCD | DeepakABCD   |
      | DeepakABCD | DeepakABCDE  |
