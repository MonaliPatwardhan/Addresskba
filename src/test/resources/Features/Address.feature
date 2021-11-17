#Please Do not change Feature Templet
Feature: Address Book
   
  #Please Do not change Scenario Templet
  
  @AddAddress
  Scenario: Add addresses
    #Please Do not change Given Templet
    Given Launch the application 
    When  address details are added
#    | Rum      | Ramya       | HCL     | Coimbatore | India  | Recipient |
    Then all addresses should be displayed to the right
#    | Rum      | Ramya       | HCL     | Coimbatore | India  | Recipient |
		And addresses are deleted
		| laa      | Lavanya     | Skava   | Banglore   | India  | Sender    |
		
		
		@DeleteAddress
		Scenario: Delete addresses
    #Please Do not change Given Templet
    Given Launch the application 
    When  address details are added
    | laa      | Lavanya     | Skava   | Banglore   | India  | Sender    |
		And addresses are deleted
		| laa      | Lavanya     | Skava   | Banglore   | India  | Sender    |