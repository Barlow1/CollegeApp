package org.pltw.examples.collegeapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by PLTW on 1/26/2016.
 */
public class CoursesFragment extends Fragment {
    private Courses mCourses;
    private TextView mCurrentCourses;
    private EditText mEnterCurrentCourses;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_courses, container, false);

        mCourses = new Courses();

        mCurrentCourses = (TextView)rootView.findViewById(R.id.current_courses);
        mEnterCurrentCourses = (EditText)rootView.findViewById(R.id.enter_current_courses);

        mCurrentCourses.setText(mCourses.getCurrentCourses());

        CurrentCoursesTextChanger currentCoursesTextChanger = new CurrentCoursesTextChanger();

        mEnterCurrentCourses.addTextChangedListener(currentCoursesTextChanger);

    return rootView;
    }
    private class CurrentCoursesTextChanger implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mCourses.setCurrentCourses(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {
            mCurrentCourses.setText(mCourses.getCurrentCourses());

        }
    }

    }
