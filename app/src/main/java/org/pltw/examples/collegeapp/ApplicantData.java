package org.pltw.examples.collegeapp;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by PLTW on 12/4/2015.
 */
public interface ApplicantData {

    public static final int PROFILE = 0;
    public static final int FAMILY = 1;
    public static final int EXTRACURRICULAR = 2;
    public static final int COURSES = 3;
    public static final int REFERENCE = 4;
    public JSONObject toJSON() throws JSONException;
}
