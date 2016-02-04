package org.pltw.examples.collegeapp;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by PLTW on 1/7/2016.
 */
public class Sibling extends FamilyMember implements ApplicantData {

    public Sibling() {
        super();
        setFirstName("New");
        setLastName("Sibling");
        setRelation(SIBLING);
    }

    public Sibling(JSONObject json) throws JSONException {
        mFirstName = json.getString(JSON_F_FIRST_NAME);
        mLastName = json.getString(JSON_F_LAST_NAME);

    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_F_FIRST_NAME, mFirstName);
        json.put(JSON_F_LAST_NAME, mLastName);

        return json;
    }

    public Sibling(String firstName, String lastName) {
        super();
        setFirstName(firstName);
        setLastName(lastName);
    }

    public  String toString() {
        return "Sibling: " + mFirstName + " " + mLastName;
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
