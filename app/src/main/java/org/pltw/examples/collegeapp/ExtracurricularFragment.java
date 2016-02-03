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

/**
 * Created by PLTW on 1/25/2016.
 */
public class ExtracurricularFragment extends Fragment {
    private Extracurricular mExtracurricular;

    private TextView mActivities;
    private EditText mEnterActivities;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_extracurricular, container, false);
        mExtracurricular = new Extracurricular("Professional Ping Pong\nCurling\nCompetitive Ice Fishing");

        mActivities = (TextView)rootView.findViewById(R.id.activities);
        mEnterActivities = (EditText)rootView.findViewById(R.id.enter_activities);

        mActivities.setText(mExtracurricular.getActivities());

        ActivitiesTextChanger activitiesTextChanger = new ActivitiesTextChanger();

        mEnterActivities.addTextChangedListener(activitiesTextChanger);

        return rootView;
    }

    private class ActivitiesTextChanger implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mExtracurricular.setActivities(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {
            mActivities.setText(mExtracurricular.getActivities());

        }
    }
}