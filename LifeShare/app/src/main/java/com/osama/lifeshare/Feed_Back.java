package com.osama.lifeshare;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Feed_Back.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Feed_Back#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Feed_Back extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private EditText editTextname;
    private EditText editTextfeedback;
    private Button submitbutton;
    private DatabaseReference databaseReference;

    private FirebaseAuth mAuth;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Feed_Back() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Feed_Back.
     */
    // TODO: Rename and change types and number of parameters
    public static Feed_Back newInstance(String param1, String param2) {
        Feed_Back fragment = new Feed_Back();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_feed__back, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Forum");
        editTextname = (EditText) view.findViewById(R.id.name_edit_text);
        editTextfeedback = (EditText) view.findViewById(R.id.feedback_edit_text);
        submitbutton = (Button) view.findViewById(R.id.feedback_button);

  
        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPosting();
            }
        });



        return view;
    }
    private void startPosting()
    {


        String name = editTextname.getText().toString().trim();
        String feedback = editTextfeedback.getText().toString().trim();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        String userId = firebaseUser.getUid();

        if (!TextUtils.isEmpty(name))
        {
            if (!TextUtils.isEmpty(feedback))
            {
                DatabaseReference forumchilds = databaseReference.push();
                forumchilds.child("Name").setValue(name);
                forumchilds.child("Feedback").setValue(feedback);
                forumchilds.child("User_ID").setValue(userId);
                Toast.makeText(getActivity(), "Successfully Posted Forum", Toast.LENGTH_SHORT).show();
                editTextname.setText("");
                editTextfeedback.setText("");

            }
            else
            {

                Toast.makeText(getActivity(), "Please Enter Your Feedback", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {

            Toast.makeText(getActivity(), "Please Enter Your Name", Toast.LENGTH_SHORT).show();
        }

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
