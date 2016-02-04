package org.pltw.examples.collegeapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by wdumas on 2/18/2015.
 */
public class GuardianFragment extends Fragment implements FamilyMemberFragment{

    private static final String TAG = "GuardianFragment";
    private final String FILENAME = "guardian.json";

    private Guardian mGuardian;
    private TextView mFirstName;
    private TextView mLastName;
    private TextView mOccupation;
    private EditText mEnterOccupation;
    private EditText mEnterFirstName;
    private EditText mEnterLastName;
    private Context mAppContext;
    GuardianJSONStorer mStorer;
    private static final String KEY_FIRST_NAME = "firstname";

    public GuardianFragment() {
        //Attempts to load information from the Profile. If information cannot be found
        //catch the exception, create a new Profile, and print out the error code
        try {
            mGuardian = mStorer.load();
        } catch (Exception e) {
            mGuardian = new Guardian();
            Log.e(TAG, "Error loading Guardian: " + FILENAME, e);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGuardian = new Guardian();

        if (savedInstanceState != null) {
            mGuardian.setFirstName(savedInstanceState.getString(KEY_FIRST_NAME));
            Log.i(TAG, "The name is " + mGuardian.getFirstName());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_guardian, container, false);

        mGuardian = (Guardian)Family.get().getFamily().get(getActivity().
                getIntent().getIntExtra(FamilyMember.EXTRA_INDEX,0));

        mFirstName = (TextView)rootView.findViewById(R.id.first_name);


        mGuardian = new Guardian();

        mFirstName = (TextView)rootView.findViewById(R.id.first_name);
        mOccupation = (TextView)rootView.findViewById(R.id.occupation);
        mEnterOccupation = (EditText)rootView.findViewById(R.id.enter_occupation);
        mEnterFirstName = (EditText)rootView.findViewById(R.id.enter_first_name);
        mLastName = (TextView)rootView.findViewById(R.id.last_name);
        mEnterLastName = (EditText)rootView.findViewById(R.id.enter_last_name);

        mFirstName.setText(mGuardian.getFirstName());
        mLastName.setText(mGuardian.getLastName());
        mOccupation.setText(mGuardian.getOccupation());

        FirstNameTextChanger firstNameTextChanger = new FirstNameTextChanger();
        LastNameTextChanger lastNameTextChanger = new LastNameTextChanger();
        OccupationTextChanger occupationTextChanger = new OccupationTextChanger();

        mEnterFirstName.addTextChangedListener(firstNameTextChanger);
        mEnterLastName.addTextChangedListener(lastNameTextChanger);
        mEnterOccupation.addTextChangedListener(occupationTextChanger);

        mAppContext = this.getActivity();
        Log.d(TAG, "Context: " + mAppContext);
        mStorer = new GuardianJSONStorer(mAppContext, FILENAME);

        return rootView;
    }

    private class FirstNameTextChanger implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mGuardian.setFirstName(s.toString());
            ArrayList<FamilyMember> family = Family.get().getFamily();
            int index = getActivity().getIntent().getIntExtra(FamilyMember.EXTRA_INDEX,0);
            family.set(index, (FamilyMember) mGuardian);
        }

        @Override
        public void afterTextChanged(Editable s) {
            mFirstName.setText(mGuardian.getFirstName());
        }



    }
    private class OccupationTextChanger implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mGuardian.setOccupation(s.toString());
            ArrayList<FamilyMember> family = Family.get().getFamily();
            int index = getActivity().getIntent().getIntExtra(FamilyMember.EXTRA_INDEX,0);
            family.set(index, (FamilyMember) mGuardian);
        }

        @Override
        public void afterTextChanged(Editable s) {
            mOccupation.setText(mGuardian.getFirstName());
        }



    }

    private class LastNameTextChanger implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mGuardian.setLastName(s.toString());
            ArrayList<FamilyMember> family = Family.get().getFamily();
            int index = getActivity().getIntent().getIntExtra(FamilyMember.EXTRA_INDEX,0);
            family.set(index, (FamilyMember)mGuardian);
        }


        @Override
        public void afterTextChanged(Editable s) {
            mLastName.setText(mGuardian.getLastName());
        }
    }

    public boolean saveGuardian() {
        try {
            mStorer.save(mGuardian);
            Log.d(TAG, "Guardian saved to file.");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving Guardian: ", e);
            return false;
        }
    }

    public boolean loadGuardian() {
        try {
            mGuardian = mStorer.load();
            Log.d(TAG, "Loaded " + mGuardian.getFirstName());
            mFirstName.setText(mGuardian.getFirstName());
            mLastName.setText(mGuardian.getLastName());
            mOccupation.setText(mGuardian.getOccupation());
            return true;
        } catch (Exception e) {
            mGuardian = new Guardian();
            Log.e(TAG, "Error loading Guardian from: " + FILENAME, e);
            return false;
        }
    }

    @Override
    public void onStart() {
        /* Logs that the fragment has been started upon creation */
        super.onStart();
        Log.d(TAG, "Fragment started.");
    }

    @Override
    public void onPause() {
        //Saves the information for Profile anytime the fragment is paused
        super.onPause();
        saveGuardian();
        Log.d(TAG, "Fragment paused.");
    }

    @Override
    public void onResume() {
        //When the information is stored, upon resume, load the information and catch
        //the exception if the program cannot properly load the file
        super.onResume();
        try {
            mGuardian = mStorer.load();
            Log.d(TAG, "Loaded " + mGuardian.getFirstName());
            mFirstName.setText(mGuardian.getFirstName());
            mLastName.setText(mGuardian.getLastName());
            mOccupation.setText(mGuardian.getOccupation());
        } catch (Exception e) {
            mGuardian = new Guardian();
            Log.e(TAG, "Error loading Guardian from: " + FILENAME, e);
        }

        Log.d(TAG, "Fragment resumed.");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "Fragment stopped.");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Fragment destroyed.");
    }



}
