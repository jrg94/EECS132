public class PhonePlan {
  private String phoneNumber;
  private double monthlyCharge;
  private int minuteLimit;
  private int messageLimit;
  private int megabyteLimit;
  private double minuteRate;
  private double messageRate;
  private double downloadRate;
  private double roamingRate;
  private double internationalRate;
  private double roamingMessageRate;
  private double roamingDownloadRate;
  private boolean active;
  private boolean allowRoaming;
  private boolean allowInternational;
  private double taxRate;
  private double balance;
  private int minuteBalance;
  private int textMessageBalance;
  private int downloadBalance;
  private int anniversaryDate;
  
  
  /* Returns the phone number for this account
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }
  
  /* Returns the monthly charge for this phone plan
   */
  public double getMonthlyCharge() {
    return monthlyCharge;
  } 
  
  /* Sets the monthly charge for this phone plan
   */
  public void setMonthlyCharge(double monthlyCharge) {
    this.monthlyCharge = monthlyCharge;
  }  
  
  /* Returns the amount of minutes available per month as part of the plan
   */
  public int getMonthlyMinutes() {
    return minuteLimit;
  }  
  
  /* Sets the amount of minutes available per month as part of the plan
   */
  public void setMonthlyMinutes(int minuteLimit) {
    this.minuteLimit = minuteLimit;
  }  
  
  /* Returns the number of text messages per month that may be sent on this plan
   */
  public int getMonthlyMessages() {
    return messageLimit;
  }
  
  /* Sets the number of text messages per month that may be sent on this plan
   */
  public void setMonthlyMessages(int messageLimit) {
    this.messageLimit = messageLimit;
  }
  
  /* Returns the number of megabytes that may be downloaded per month on this plan 
   */
  public int getMonthlyDownloads() {
    return megabyteLimit;
  }
  
  /* Sets the number of megabytes that may be downloaded per month on this plan
   */
  public void setMonthlyDownloads(int megabyteLimit) {
    this.megabyteLimit = megabyteLimit;
  }
  
  /* Returns the amount charged per minute for a phone call beyond the monthly allotment 
   */
  public double getMinuteRate() {
    return minuteRate;
  }
  
  /* Sets the amount charged per minute for a phone call beyond the monthly allotment
   */
  public void setMinuteRate(double minuteRate) {
    this.minuteRate = minuteRate;
  }
  
  /* Returns the amount charged per text message if sending more messages than the plan allows per month 
   */
  public double getMessageRate() {
    return messageRate;
  }
  
  /* Sets the amount charged per text message that exceeds the number allowed per month
   */
  public void setMessageRate(double messageRate) {
    this.messageRate = messageRate;
  }  
  
  /* Returns the amount charged per megabyte of download when the amount 
   * downloaded exceeds the plan limit for the month
   */
  public double getDownloadRate() {
    return downloadRate;
  }
  
  /* Sets the amount charged per megabyte of download that exceeds the amount allowed per month
   */
  public void setDownloadRate(double downloadRate) {
    this.downloadRate = downloadRate;
  }
  
  /* Returns the amount charged per minute for a roaming call
   */
  public double getRoamingRate() {
    return roamingRate;
  }  
  
  /* Sets the amount charged per minute for a roaming call
   */
  public void setRoamingRate(double roamingRate) {
    this.roamingRate = roamingRate;
  }
  
  /* Returns the amount per minute for an outgoing international call
   */
  public double getInternationalRate() {
    return internationalRate;
  }
  
  /* Sets the amount per minute for an outgoing international call
   */
  public void setInternationalRate(double internationalRate) {
    this.internationalRate = internationalRate;
  }
  
  /* Gets the amount charged for a text message while roaming
   */
  public double getRoamingMessageRate() {
    return roamingMessageRate;
  }  
  
  /* Sets the amount charged for a text message while roaming
   */
  public void setRoamingMessageRate(double roamingMessageRate) {
    this.roamingMessageRate = roamingMessageRate;
  }  
  
  /* Gets the amount charged per megabyte of download while roaming
   */
  public double getRoamingDownloadRate() {
    return roamingDownloadRate;
  }
  
  /* Sets the amount charged per megabyte of download while roaming
   */
  public void setRoamingDownloadRate(double roamingDownloadRate) {
    this.roamingDownloadRate = roamingDownloadRate;
  }  
  
  /* Returns true if the phone is active
   */
  public boolean isActivated() {
    return active;
  }
  
  /* Sets whether the phone is active or not
   */
  public void setActivated(boolean active) {
    this.active = active;
  }
   
  /* Returns true if the phone may make or recieve calls while roaming
   */
  public boolean allowRoamingCalls() {
    return allowRoaming;
  }
  
  /* Sets whether the phone may make or recieve calls while roaming
   */
  public void setAllowRoamingCalls(boolean allowRoaming) {
    this.allowRoaming = allowRoaming;
  }  
  
  /* Returns true if the phone is allowed to make international calls
   */
  public boolean allowInternationalCalls() {
    return allowInternational;
  }
  
  /* Sets whether the phone may make international calls or not
   */
  public void setAllowInternationalCalls(boolean allowInternational) {
    this.allowInternational = allowInternational;
  }  
  
  /* Returns the tax rate for the plan
   */
  public double getTaxRate() {
    return taxRate;
  }
  
  /* Sets the tax rate for the plan
   */
  public void setTaxRate(double taxRate) {
    this.taxRate = taxRate;
  } 
  
  /* Returns the current cash balance for the the account
   */
  public double getBalance() {
    return balance;
  }
  
  /* Adds amount to account cash balance
   */
  public void pay(double amount) {
    balance = (balance + amount);
  }  
  
  /* Returns the current minute balance for the account
   */
  public int getMinuteBalance() {
    return minuteBalance;
  }  
  
  /* Returns the current message balance for the account
   */
  public int getTextMessageBalance() {
    return textMessageBalance;
  }  
  
  /* Returns the current download balance for the account
   */
  public int getDownloadBalance() {
    return downloadBalance;
  }
  
  /* This method simulates sending or receiving a text messge. Text messages are paid for 
   * by the message balance and/or the cash balance. The method returns true if the text 
   * message was sent (or received) and the balances reduced appropriately, and false if 
   * the message was not sent because there is not enough remaining in the balances to pay for the message.
   */
  public boolean sendTextMessage(boolean isRoaming) {
    if (isRoaming == true) {
      if (allowRoamingCalls() != true) {
        return false;
      }
      else if (getBalance() >= (getRoamingMessageRate() + getMessageRate()) && getTextMessageBalance() == 0) {
        balance = (getBalance() - (getRoamingMessageRate() + getMessageRate()));
        return true;
      }
      else if (getBalance() >= getRoamingMessageRate() && getTextMessageBalance() > 0) {
        balance = (getBalance() - getRoamingMessageRate());
        textMessageBalance--;
        return true;
      }
      else {
        return false;
      }
    }
    else {
      if (getTextMessageBalance() >= 1) {
        textMessageBalance--;
        return true;
      }
      else if (getBalance() >= getMessageRate()) {
        balance = (getBalance() - getMessageRate());
        return true;
      }
      else {
        return false;
      }
    }
  }
  
  /* This method simulates downloading data to the phone. Data downloads are paid 
   * for by the download balance and/or the cash balance. The method returns true 
   * if the desired megabytes were downloaded and the balances reduced appropriately, 
   * and false if the megabytes were not downloaded because there is not enough remaining 
   * in the balances to pay for the message.
   */
  public boolean downloadFile(int megabytes, boolean isRoaming) {
    if (isRoaming == true) {
      if (allowRoamingCalls() != true) {
        return false;
      }
      if (getDownloadBalance() >= megabytes) {
        balance = (getBalance() - (getRoamingDownloadRate() * megabytes));
        downloadBalance = getDownloadBalance() - megabytes;
        return true;
      }
      if (getDownloadBalance() < megabytes && getBalance() > 0) {
        balance = ((getBalance() - ((megabytes * getRoamingRate()) + 
                                    ((megabytes - getDownloadRate()) * getDownloadRate()))));
        downloadBalance = 0;
        return true;
      }
      else {
        return false;
      }
    }
    else {
      if (getDownloadBalance() >= megabytes) {
        downloadBalance = (getDownloadBalance() - megabytes);
        return true;
      }
      if (getDownloadBalance() < megabytes && getBalance() > 0) {
        balance = (getBalance() - (getDownloadRate() * (Math.abs(getDownloadBalance() - megabytes))));
        downloadBalance = 0;
        return true;
      }
      else {
        return false;
      }
    }
  }
  
  /* Returns the length of the longest possible call, given the current minutes balance and cash balance
   */
  public int maxCallLength(boolean isRoaming, boolean isInternational) {
    if (isActivated() != true) {
      return 0;
    }
    else if (isRoaming == true && allowRoamingCalls() != true) {
      return 0;
    }
    else if (isInternational == true && allowInternationalCalls() != true) {
      return 0;
    }
    else if (isRoaming == true && isInternational != true) {
      if((getBalance() / getRoamingRate()) < getMinuteBalance()) {
        return ((int)(getBalance() / getRoamingRate()));
      }
      else {
        return ((int)(((getBalance() - (getMinuteBalance() * getRoamingRate()))/ (getRoamingRate() + getMinuteRate()))
                        + getMinuteBalance()));
      }
    }
    else if (isRoaming == true && isInternational == true) {
      if((balance / (roamingRate + internationalRate)) < minuteBalance) {
        return ((int)(getBalance() / (getRoamingRate() + getInternationalRate())));
      }
      else {
        return ((int)(((getBalance() - (getMinuteBalance() * (getRoamingRate() + getInternationalRate())))/ (getRoamingRate() + getInternationalRate() + getMinuteRate()))
                        + getMinuteBalance()));
      }
    }
    else if (isRoaming == false && isInternational == true) {
      return 0;
    }
    else {
      return ((int)(getBalance() / getMinuteRate()) + getMinuteBalance());
    }
  }
  
  /* This method returns true if a call of length minutes was successfully made, and the 
   * various balances must be adjusted accordingly. If a call of length minutes can not be 
   * made (minutes is greater than the value returned by maxCallLength), there is no adjustment 
   * to the balances, and the method returns false.
   */
  public boolean makeCall(int minutes, boolean isRoaming, boolean isInternational) {
    int callLength = this.maxCallLength(isRoaming, isInternational);
    if (minutes > callLength) {
      return false;
    }
    else if (isRoaming == true && isInternational != true) {
      if((getMinuteBalance() - minutes) >= 0) {
        balance = (getBalance() - (minutes * getRoamingRate()));
        minuteBalance = (getMinuteBalance() - minutes);
        return true;
      }
      else {
        balance = (getBalance() - ((minutes * getRoamingRate()) + ((Math.abs(getMinuteBalance() - minutes)) * 
                                                                     getMinuteRate())));
        minuteBalance = 0;
        return true;
      }
    }
    else if (isRoaming == true && isInternational == true) {
      if((minuteBalance - minutes) >= 0) {
        balance = (getBalance() - ((minutes * getRoamingRate()) + (minutes * getInternationalRate())));
        minuteBalance = (getMinuteBalance() - minutes);
        return true;
      }
      else {
        balance = (getBalance() - ((minutes * getRoamingRate()) + (minutes * getInternationalRate()) + 
                                   ((Math.abs(getMinuteBalance() - minutes)) * getMinuteRate())));
        minuteBalance = 0;
        return true;
      }
    }
    else if ((minuteBalance - minutes) >= 0) {
      minuteBalance = (getMinuteBalance() - minutes);
      return true;
    }
    else {
      balance = (getBalance() - ((Math.abs(getMinuteBalance() - minutes)) * getMinuteRate()));
      minuteBalance = 0;
      return true;
    }
  }
  
  /* This method initializes the month for the plan
   */
  public boolean startMonth(int date) {
    if(balance >= (getMonthlyCharge() + (getMonthlyCharge() * getTaxRate()))) {
      balance = (getBalance() - (getMonthlyCharge() + (getMonthlyCharge() * getTaxRate())));
      minuteBalance = getMonthlyMinutes();
      textMessageBalance = getMonthlyMessages();
      downloadBalance = getMonthlyDownloads();
      if(isActivated() == false) {
        active = true;
        anniversaryDate = date;
      };
      return true;
    }
    else {
      minuteBalance = 0;
      textMessageBalance = 0;
      downloadBalance = 0;
      active = false;
      return false;
    }
  }
  
  /* This method checks to see if the day is the aniversary date
   */
  public void processDay(int day, int month) {
    if ((month == 1 && day == 31 && getAnniversaryDate() < 
         day && isActivated() == true)) {
      startMonth(anniversaryDate);
    }
    else if ((month == 2 && day == 28 && getAnniversaryDate() < 
         day && isActivated() == true) || (day == getAnniversaryDate() && isActivated() == true)) {
      startMonth(anniversaryDate);
    }
    else if ((month == 3 && day == 31 && getAnniversaryDate() < 
         day && isActivated() == true) || (day == getAnniversaryDate() && isActivated() == true)) {
      startMonth(anniversaryDate);
    }
    else if ((month == 4 && day == 30 && getAnniversaryDate() < 
         day && isActivated() == true) || (day == getAnniversaryDate() && isActivated() == true)) {
      startMonth(anniversaryDate);
    }
    else if ((month == 5 && day == 31 && getAnniversaryDate() < 
         day && isActivated() == true) || (day == getAnniversaryDate() && isActivated() == true)) {
      startMonth(anniversaryDate);
    }
    else if ((month == 6 && day == 30 && getAnniversaryDate() < 
         day && isActivated() == true) || (day == getAnniversaryDate() && isActivated() == true)) {
      startMonth(anniversaryDate);
    }
    else if ((month == 7 && day == 31 && getAnniversaryDate() < 
         day && isActivated() == true) || (day == getAnniversaryDate() && isActivated() == true)) {
      startMonth(anniversaryDate);
    }
    else if ((month == 8 && day == 31 && getAnniversaryDate() < 
         day && isActivated() == true) || (day == getAnniversaryDate() && isActivated() == true)) {
      startMonth(anniversaryDate);
    }
    else if ((month == 9 && day == 30 && getAnniversaryDate() < 
         day && isActivated() == true) || (day == getAnniversaryDate() && isActivated() == true)) {
      startMonth(anniversaryDate);
    }
    else if ((month == 10 && day == 31 && getAnniversaryDate() < 
         day && isActivated() == true) || (day == getAnniversaryDate() && isActivated() == true)) {
      startMonth(anniversaryDate);
    }
    else if ((month == 11 && day == 30 && getAnniversaryDate() < 
         day && isActivated() == true) || (day == getAnniversaryDate() && isActivated() == true)) {
      startMonth(anniversaryDate);
    }
    else if ((month == 12 && day == 31 && getAnniversaryDate() < 
         day && isActivated() == true) || (day == getAnniversaryDate() && isActivated() == true)) {
      startMonth(anniversaryDate);
    }
  }
  
  /* Returns the anniversary date of the plan
   */
  public int getAnniversaryDate() {
    return anniversaryDate;
  }
  
  /* A constructor that sets the phone number, monthly charge, monthly minute limit, 
   * monthly message limit, and monthly download limit for a phone plan
   */
  public PhonePlan(String phoneNumber, double monthlyCharge, int monthlyMinutes, 
                   int monthlyMessages, int monthlyDownloads) {
   this.phoneNumber = phoneNumber;
   this.monthlyCharge = monthlyCharge;
   this.minuteLimit = monthlyMinutes;
   this.messageLimit = monthlyMessages;
   this.megabyteLimit = monthlyDownloads;
   minuteRate = .1;
   messageRate = .5;
   downloadRate = .01;
   roamingRate = .1;
   roamingMessageRate = .1;
   roamingDownloadRate = .01;
   internationalRate = .1;
   taxRate = .06;
   active = false;
   allowRoaming = true;
   allowInternational = true;
   balance = 0;
   minuteBalance = getMonthlyMinutes();
   downloadBalance = getMonthlyDownloads();
   textMessageBalance = getMonthlyMessages();
   anniversaryDate = 16;
 }
  
  /* A constructor that sets the minute rate, roaming rate, international rate, 
   * message rate, download rate, roaming message rate, roaming download rate, and tax rate)
   */
  public PhonePlan(String phoneNumber, double monthlyCharge, int monthlyMinutes, int monthlyMessages, 
                   int monthlyDownloads, double minuteRate, double roamingRate, double internationalRate, 
                   double messageRate, double downloadRate, double roamingMessageRate, 
                   double roamingDownloadRate, double taxRate) {
    this(phoneNumber, monthlyCharge, monthlyMinutes, monthlyMessages, monthlyDownloads);
    this.minuteRate = minuteRate;
    this.roamingRate = roamingRate;
    this.internationalRate = internationalRate;
    this.messageRate = messageRate;
    this.downloadRate = downloadRate;
    this.roamingMessageRate = roamingMessageRate;
    this.roamingDownloadRate = roamingDownloadRate;
    this.taxRate = taxRate;
  }
    
}