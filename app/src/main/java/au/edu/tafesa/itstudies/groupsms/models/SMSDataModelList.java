package au.edu.tafesa.itstudies.groupsms.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import au.edu.tafesa.itstudies.groupsms.models.SMSDataModelFullException;
import au.edu.tafesa.itstudies.groupsms.models.SMSDataModelDuplicateException;

public class SMSDataModelList implements SMSDataModelInterface, Serializable {

        public static final String FULL = "FULL";
        public static final String DUPLICATE = "DUPLICATE";
        private static final int DEFAULT_MAX_NUM_PHONE_NUMBERS = -1;

        private String message;         //The SMS message
        private int maxPhoneNumbers;
        private List<String> phoneNumbers;  //The collection of phone numbers

        public SMSDataModelList() {
            this("");
        }

        public SMSDataModelList(String initialMessage) {
            this(initialMessage, DEFAULT_MAX_NUM_PHONE_NUMBERS);
        }

        public SMSDataModelList(String initialMessage, int maxPhoneNumbers) {
            this.message = initialMessage;
            this.maxPhoneNumbers = maxPhoneNumbers;

            this.phoneNumbers = new ArrayList<>();
        }

        @Override
        public String addPhoneNumber(String newPhoneNumber) {
            String result;
            // if the amount of phone numbers will go over the limit, or there is no limit, throw an exception
            if (isFull()) {
                return newPhoneNumber;
            }

            if (phoneNumbers.contains(newPhoneNumber)) {
                result = DUPLICATE;
            } else {
                phoneNumbers.add(newPhoneNumber);
                result = newPhoneNumber;
            }
            return result;
        }

        public int findPhoneNumberIndex(String targetNumber) {
            return this.phoneNumbers.indexOf(targetNumber);
        }

        public String updatePhoneNumber(String newPhoneNumber, int i) {
            String result;

            if (i < 0 || i >= phoneNumbers.size()) {
                result = null;
            } else if (phoneNumbers.contains(newPhoneNumber)) {
//                throw new SMSDataModelDuplicateException(newPhoneNumber);
                result = DUPLICATE;
            } else {
//                result = phoneNumbers.get(i); // list methods  (return)
//                phoneNumbers.set(i, newPhoneNumber);

                result = phoneNumbers.set(i, newPhoneNumber);
            }
            return result;
        }

        public String getPhoneNumber(int i) {
            String result;

            if (i < 0 || i >= phoneNumbers.size()) {
                result = null;
            } else {
                result = phoneNumbers.get(i);
            }
            return result;
        }

        public String deleteNumber(int i) {
            String result;
            if (i < 0 || i >= phoneNumbers.size()) {
                result = null;
            } else {
                // result = phoneNumbers.get(i);
                result = phoneNumbers.remove(i);
            }
            return result;
        }

        public int getMaxNumPhoneNumbers() {
            return maxPhoneNumbers;
        }

        public boolean isFull() {
            return phoneNumbers.size() == maxPhoneNumbers;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String newMessage) {
            this.message = newMessage;
        }

        public String[] getPhoneNumbers() {
            return (String[]) this.phoneNumbers.toArray();
        }

        public int getNumPhoneNumbers() {
            return phoneNumbers.size();
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("SMSDataModel [message=");
            sb.append(this.message);
            sb.append(", phoneNumbers={");
            for (String s : phoneNumbers) {
                sb.append(s);
                sb.append(",");
            }
            sb.append("}, numPhoneNumbers=");
            sb.append(phoneNumbers.size());
            return sb.toString();
        }

        public void sortNumbers() {
            Collections.sort(phoneNumbers);

        }

}
