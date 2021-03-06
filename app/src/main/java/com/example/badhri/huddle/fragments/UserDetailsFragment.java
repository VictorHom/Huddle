package com.example.badhri.huddle.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.badhri.huddle.HuddleApplication;
import com.example.badhri.huddle.R;
import com.example.badhri.huddle.activities.DashboardActivity;
import com.example.badhri.huddle.models.UserNonParse;
import com.example.badhri.huddle.parseModels.User;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link UserDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserDetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_USER_OBJECTID = "userObjectId";

    // TODO: Rename and change types of parameters
    private String userObjectId;

    @BindView(R.id.etEmail)
    EditText evEmail;

    @BindView(R.id.tvPhoneNumber)
    TextView tvPhoneNumber;

    @BindView(R.id.et_bio)
    EditText etBio;


    private Unbinder unbinder;
    User profileUser;

    //private OnFragmentInteractionListener mListener;

    public UserDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param userObjectId objectId of the user
     * @return A new instance of fragment UserDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserDetailsFragment newInstance(String userObjectId) {
        UserDetailsFragment fragment = new UserDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USER_OBJECTID, userObjectId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userObjectId = getArguments().getString(ARG_USER_OBJECTID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user_details, container, false);
        unbinder = ButterKnife.bind(this, v);

        ParseQuery<User> query = ParseQuery.getQuery(User.class);
        query.whereEqualTo("objectId", userObjectId);
        query.findInBackground(new FindCallback<User>() {
            public void done(List<User> user, ParseException e) {
                if (e == null) {
                    profileUser = user.get(0);
                    tvPhoneNumber.setText(Long.toString(user.get(0).getPhoneNumber()));
                    evEmail.setText(user.get(0).getEmail());

                    String bio = user.get(0).getBio();

                    if (bio != null && bio.length() > 0) {
                        etBio.setText(user.get(0).getBio());
                    }
                } else {
                    Log.d(HuddleApplication.TAG, "Fetching user object failed");
                    e.printStackTrace();
                }
            }
        });

        return v;
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    // TODO: Rename method, update argument and hook method into UI event
    /*public void onButtonPressed(Uri uri) {
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
    /*public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    } */

    @OnClick(R.id.save_profile)
    public void saveProfile(Button btn) {

        if (etBio.getText().length() > 0) {
            profileUser.setBio(etBio.getText().toString());
        }

        if (evEmail.getText().length() > 0) {
            profileUser.setEmail(evEmail.getText().toString());
        }
        profileUser.saveInBackground();
        Intent myIntent = new Intent(getActivity(), DashboardActivity.class);
        myIntent.putExtra("user", UserNonParse.fromUser(profileUser));
        startActivity(myIntent);

    }

}
