package org.pltw.examples.collegeapp;


/**
 * Created by PLTW on 1/26/2016.
 */
public class Courses {

    private String mCurrentCourses;

    public String getCurrentCourses() {
        return mCurrentCourses;
    }

    public void setCurrentCourses(String mCourses) {
        this.mCurrentCourses = mCurrentCourses;
    }

    public Courses() {
        mCurrentCourses = "Accounting 101 \nAnatomy and Physiology \nEnglish Composition 102 \nCollege Algebra \nInformation Technology Fundamentals";
    }

}
