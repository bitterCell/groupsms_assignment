package au.edu.tafesa.itstudies.groupsms.models;

public class SMSDataModelDuplicateException extends Exception {
    private String phoneNumber;

    public SMSDataModelDuplicateException(String phoneNumber) {
        super("SMSDataModel already has phone number");
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
