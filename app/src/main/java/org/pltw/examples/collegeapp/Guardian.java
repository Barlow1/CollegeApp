package org.pltw.examples.collegeapp;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wdumas on 2/11/2015.
 */
public class Guardian extends FamilyMember implements ApplicantData{

    public Guardian(){
        super();
        setFirstName("New");
        setLastName("Guardian");
        setOccupation("Unknown");
        setRelation(GUARDIAN);
    }

    public Guardian(String firstName, String lastName) {
        super();
        setFirstName(firstName);
        setLastName(lastName);
    }

    public Guardian(String firstName, String lastName, String occupation) {
        super();
        setFirstName(firstName);
        setLastName(lastName);
        setOccupation(occupation);
    }

    public Guardian(JSONObject json) throws JSONException{
        mFirstName = json.getString(JSON_F_FIRST_NAME);
        mLastName = json.getString(JSON_F_LAST_NAME);
        mOccupation = json.getString(JSON_F_OCCUPATION);
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_F_FIRST_NAME, mFirstName);
        json.put(JSON_F_LAST_NAME, mLastName);
        json.put(JSON_F_OCCUPATION, mOccupation);
        return json;
    }

    public  String toString() {
        return "Guardian: " + mFirstName + " " + mLastName + "\nOccupation: " + mOccupation;
    }

    @Override
    public int compareTo(FamilyMember another) {
        if (mFirstName.equals(another.mFirstName) && mLastName.equals(another.mLastName)) {
            return 0;
        } else {
            return 1;
        }
    }


}
