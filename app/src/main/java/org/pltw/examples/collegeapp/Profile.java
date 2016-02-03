package org.pltw.examples.collegeapp;



import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by wdumas on 12/22/2014.
 */
public class Profile implements ApplicantData {

    private static final String JSON_FIRST_NAME = "firstName";
    private static final String JSON_LAST_NAME = "lastName";
    private static final String JSON_DOB = "dob";
    private static final String JSON_TEST_SCORES = "testscores";
    private String mFirstName;
    private String mLastName;
    private Date mDateOfBirth;
    private String mTestScores;

    public Profile(JSONObject json) throws JSONException {
        mFirstName = json.getString(JSON_FIRST_NAME);
        mDateOfBirth = new Date(json.getLong(JSON_DOB));
        mLastName = json.getString(JSON_LAST_NAME);
        mTestScores = json.getString(JSON_TEST_SCORES);
    }

    public String getFirstName() {
        return mFirstName;
    }
    public void setFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }
    public String getLastName() {
        return mLastName;
    }
    public void setLastName(String mLastName) {
        this.mLastName = mLastName;
    }
    public Date getDateOfBirth() {
        return mDateOfBirth;
    }
    public String getTestScores() {
        return mTestScores;
    }

    public void setTestScores(String mTestScores) {
        this.mTestScores = mTestScores;
    }


    public String dobToString() {
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        return df.format(mDateOfBirth.getTime());
    }

    public void setDateOfBirth(Date mDateOfBirth) {
        this.mDateOfBirth = mDateOfBirth;
    }

    public Profile() {
        mFirstName = new String("New");
        mLastName = new String("Guardian");
        mDateOfBirth = new Date(83, 0, 24);
        mTestScores = new String ("ACT: 36\nSAT: 2400\nPingPong Entrance Exam: 100");
    }

    public String toString() {
        return mFirstName + " " + mLastName + " " +  mTestScores + " " + mDateOfBirth.getTime();
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_FIRST_NAME, mFirstName);
        json.put(JSON_LAST_NAME, mLastName);
        json.put(JSON_TEST_SCORES, mTestScores);
        json.put(JSON_DOB, mDateOfBirth.getTime());
        System.out.println("Date of Birth Saved: " + mDateOfBirth);
        return json;
    }



}
