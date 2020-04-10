package com.osama.lifeshare;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link search_all_donor.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link search_all_donor#newInstance} factory method to
 * create an instance of this fragment.
 */
public class search_all_donor extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView msearchalldonorlist;
    private DatabaseReference databaseReference;
    private SearchView searchView;
    ArrayList<search_all_donor_fragment_support> list;

    private OnFragmentInteractionListener mListener;

    public search_all_donor() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment search_all_donor.
     */
    // TODO: Rename and change types and number of parameters
    public static search_all_donor newInstance(String param1, String param2) {
        search_all_donor fragment = new search_all_donor();
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
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_search_all_donor, container, false);


        msearchalldonorlist = (RecyclerView) view.findViewById(R.id.search_all_donor_list);
        msearchalldonorlist.setHasFixedSize(true);
        msearchalldonorlist.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users:");
        searchView = view.findViewById(R.id.searchview);

        if (databaseReference != null)
        {
            databaseReference.addValueEventListener(new ValueEventListener()
                                      {
                                          @Override
                                          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                              if (dataSnapshot.exists()){

                                                  list = new ArrayList<>();
                                                  for (DataSnapshot ds : dataSnapshot.getChildren())
                                                  {

                                                      list.add(ds.getValue(search_all_donor_fragment_support.class));
                                                  }
                                                  Adaptor_search_donor adapterclass = new Adaptor_search_donor(list);
                                                  msearchalldonorlist.setAdapter(adapterclass);

                                              }
                                          }

                                          @Override
                                          public void onCancelled(@NonNull DatabaseError databaseError) {

                                              Toast.makeText(getActivity(),databaseError.getMessage(),Toast.LENGTH_SHORT).show();
                                          }
                                      }
            ); }

        if (searchView != null){
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    search(s);
                    return true;
                }

                private void search(String str) {
                    ArrayList<search_all_donor_fragment_support> mylist = new ArrayList<>();
                    for (search_all_donor_fragment_support object : list)
                    {
                        if (object.getBlood_Group().toLowerCase().contains(str.toLowerCase()))
                        {
                            mylist.add(object);
                        }
                    }
                    Adaptor_search_donor adapterclass = new Adaptor_search_donor(mylist);
                    msearchalldonorlist.setAdapter(adapterclass);
                }



            });
        }






        return view;
    }
    public static class searchalldonorViewHolder extends RecyclerView.ViewHolder
    {
        View mView;
        public searchalldonorViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;

        }


        public void setName(String name)
        {
            TextView name_searchalldonor = (TextView) mView.findViewById(R.id.name_searchalldonor);
            name_searchalldonor.setText(name);

        }
        public void setPhone(String phone)
        {
            TextView phone_searchalldonor = (TextView) mView.findViewById(R.id.phone_searchalldonor);
            phone_searchalldonor.setText(phone);
        }
        public void setblood_Group(String blood_Group)
        {
            TextView group_searchalldonor  = (TextView) mView.findViewById(R.id.group_searchalldonor);
            group_searchalldonor.setText(blood_Group);
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
