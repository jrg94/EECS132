public class PhoneAccount {
  
  private String firstName;
  private String lastName;
  private String ccNumber;
  private String address;
  private String city;
  private String state;
  private String zipCode;
  private PhonePlan businessPlan;
  private PhonePlan personalPlan;
  private int month;
  private int day;
  
  /* Sets the first name on the account
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  
  /* Returns the first name on the account
   */
  public String getFirstName() {
    return firstName;
  }
  
  /* Sets the last name on the account
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  
  /* Returns the last name on the account
   */
  public String getLastName() {
    return lastName;
  }
  
  /* Sets the Credit Card Number on the account
   */
  public void setCCNumber(String ccNumber) {
    this.ccNumber = ccNumber;
  }
  
  /* Returns the Credit Card Number on the account
   */
  public String getCCNumber() {
    return ccNumber;
  }
  
  /* Sets the street address on the account
   */
  public void setStreetAddress(String address) {
    this.address = address;
  }
  
  /* Returns the street address on the account
   */
  public String getStreetAddress() {
    return address;
  }
  
  /* Sets the city for the account's address
   */
  public void setCity(String city) {
    this.city = city;
  }
  
  /* Returns the city for the acount's address
   */
  public String getCity() {
    return city;
  }
  
  /* Sets the state for the account's address
   */
  public void setState(String state) {
    this.state = state;
  }
  
  /* Returns the state for the account's address
   */
  public String getState() {
    return state;
  }
  
  /* Sets the zip code for the account's address
   */
  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }
  
  /* Returns the zip code for the account's address
   */
  public String getZipCode() {
    return zipCode;
  }
  
  /* Sets the personal phone plan for the account
   */
  public void setPersonalPlan(PhonePlan plan) {
    this.personalPlan = plan;
  }
  
  /* Returns the personal phone plan for the account
   */
  public PhonePlan getPersonalPlan() {
    return personalPlan;
  }
  
  /* Sets the business phone plan for the account
   */
  public void setBusinessPlan(PhonePlan plan) {
    this.businessPlan = plan;
  }
  
  /* Returns the business phone plan for the account
   */
  public PhonePlan getBusinessPlan() {
    return businessPlan;
  }
  
  /* Sets the current month and day
   */
  public void setDate(int month, int day) {
    this.month = month;
    this.day = day;
  }
  
  /* Returns the current month
   */
  public int getMonth() {
    return month;
  }
  
  /* Returns the current day
   */
  public int getDay() {
    return day;
  }
  
  /* Adds 1 to the current day
   */
  public void incrementDate() {
    if ((month == 1) && (day == 31)) {
      month = 2;
      day = 1;
    }
    else if ((month == 2) && (day == 28)) {
      month = 3;
      day = 1;
    }
    else if ((month == 3) && (day == 31)) {
      month = 4;
      day = 1;
    }
    else if ((month == 4) && (day == 30)) {
      month = 5;
      day = 1;
    }
    else if ((month == 5) && (day == 31)) {
      month = 6;
      day = 1;
    }
    else if ((month == 6) && (day == 30)) {
      month = 7;
      day = 1;
    }
    else if ((month == 7) && (day == 31)) {
      month = 8;
      day = 1;
    }
    else if ((month == 8) && (day == 31)) {
      month = 9;
      day = 1;
    }
    else if ((month == 9) && (day == 30)) {
      month = 10;
      day = 1;
    }
    else if ((month == 10) && (day == 31)) {
      month = 11;
      day = 1;
    }
    else if ((month == 11) && (day == 30)) {
      month = 12;
      day = 1;
    }
    else if ((month == 12) && (day == 31)) {
      month = 1;
      day = 1;
    }
    else {
      day = (day + 1);
    }
    getBusinessPlan().processDay(day, month);
    getPersonalPlan().processDay(day, month);
  }
  
  /* A constructor that sets the first name, last name, and credit card number for an account
   */
  public PhoneAccount(String firstName, String lastName, String ccNumber) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.ccNumber = ccNumber;
  }
    
}  