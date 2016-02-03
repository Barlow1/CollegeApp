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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by PLTW on 1/28/2016.
 */
public class ReferenceFragment extends Fragment {
    private static final String TAG = "ReferenceFragment";
    private final String FILENAME = "reference.json";
    private static final String KEY_FIRST_NAME = "firstname";

    private Reference mReference;
    private TextView mFirstName;
    private TextView mLastName;
    private EditText mEnterFirstName;
    private EditText mEnterLastName;
    private Context mAppContext;
    ReferenceJSONStorer mStorer;
    private TextView mSubmitText;
    private Button mSubmit;


    public ReferenceFragment() {
        /*Attempts to load information from the Profile. If information cannot be found
        catch the exception, create a new Profile, and print out the error code*/
        try {
            mReference = mStorer.load();
        } catch (Exception e) {
            mReference = new Reference();
            Log.e(TAG, "Error loading profile: " + FILENAME, e);
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mReference = new Reference();

        if (savedInstanceState != null) {
            mReference.setFirstName(savedInstanceState.getString(KEY_FIRST_NAME));
            Log.i(TAG, "The name is " + mReference.getFirstName());
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reference, container, false);
        Button c = (Button)rootView.findViewById(R.id.submit);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSubmitText.setText("Your Letter of Reference has been submitted!");
            }
        });

        mFirstName = (TextView)rootView.findViewById(R.id.first_name);
        mEnterFirstName = (EditText)rootView.findViewById(R.id.enter_first_name);
        mLastName = (TextView)rootView.findViewById(R.id.last_name);
        mEnterLastName = (EditText)rootView.findViewById(R.id.enter_last_name);
        mSubmitText = (TextView)rootView.findViewById(R.id.submit_text);
        mSubmit = (Button)rootView.findViewById(R.id.submit);

        mFirstName.setText(mReference.getFirstName());
        mLastName.setText(mReference.getLastName());

        FirstNameTextChanger firstNameTextChanger = new FirstNameTextChanger();
        LastNameTextChanger lastNameTextChanger = new LastNameTextChanger();

        mEnterFirstName.addTextChangedListener(firstNameTextChanger);
        mEnterLastName.addTextChangedListener(lastNameTextChanger);

        mAppContext = this.getActivity();
        Log.d(TAG, "Context: " + mAppContext);

        mStorer = new ReferenceJSONStorer(mAppContext, FILENAME);

        return rootView;

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState got called and it was a BLAST!!!1: " + mReference.getFirstName());
        savedInstanceState.putString(KEY_FIRST_NAME, mReference.getFirstName());
    }

    private class FirstNameTextChanger implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mReference.setFirstName(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {
            mFirstName.setText(mReference.getFirstName());
        }
    }

    private class LastNameTextChanger implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mReference.setLastName(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {
            mLastName.setText(mReference.getLastName());
        }
    }

    public boolean saveReference() {
        try {
            mStorer.save(mReference);
            Log.d(TAG, "reference saved to file.");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving reference: ", e);
            return false;
        }
    }

    public boolean loadReference() {
        try {
            mReference = mStorer.load();
            Log.d(TAG, "Loaded " + mReference.getFirstName());
            mFirstName.setText(mReference.getFirstName());
            mLastName.setText(mReference.getLastName());
            return true;
        } catch (Exception e) {
            mReference = new Reference();
            Log.e(TAG, "Error loading profile from: " + FILENAME, e);
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
        /*Saves the information for Profile anytime the fragment is paused*/
        super.onPause();
        saveReference();
        Log.d(TAG, "Fragment paused.");
    }

    @Override
    public void onResume() {
        /*When the information is stored, upon resume, load the information and catch
        the exception if the program cannot properly load the file*/
        super.onResume();
        try {
            mReference = mStorer.load();
            Log.d(TAG, "Loaded " + mReference.getFirstName());
            mFirstName.setText(mReference.getFirstName());
            mLastName.setText(mReference.getLastName());

        } catch (Exception e) {
            mReference = new Reference();
            Log.e(TAG, "Error loading profile from: " + FILENAME, e);
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
