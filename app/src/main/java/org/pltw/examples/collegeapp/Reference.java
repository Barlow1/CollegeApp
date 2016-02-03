package org.pltw.examples.collegeapp;


import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by PLTW on 1/28/2016.
 */
public class Reference implements ApplicantData {

        protected static final String JSON_FIRST_NAME = "firstName";
        protected static final String JSON_LAST_NAME = "lastName";
        protected String mFirstName;
        protected String mLastName;
        private String mSubmitText;

        protected String getFirstName() {
            return mFirstName;
        }

        protected void setFirstName(String firstName) {
            mFirstName = firstName;
        }

        protected String getLastName() {
            return mLastName;
        }

        protected void setLastName(String lastName) {
            mLastName = lastName;
        }

        public String getSubmitText() {
        return mSubmitText;
    }

        public void setSubmitText(String submitText) {
        mSubmitText = submitText;
    }


    public Reference() {
        setFirstName("James");
        setLastName("Wilson");
    }

    public Reference(JSONObject json) throws JSONException {
        mFirstName = json.getString(JSON_FIRST_NAME);
        mLastName = json.getString(JSON_LAST_NAME);

    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_FIRST_NAME, mFirstName);
        json.put(JSON_LAST_NAME, mLastName);

        return json;
    }

    public Reference(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }

    public  String toString() {
        return "Reference: " + mFirstName + " " + mLastName;
    }
}
