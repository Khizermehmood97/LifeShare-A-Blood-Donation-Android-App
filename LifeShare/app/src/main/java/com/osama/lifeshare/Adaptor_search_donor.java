package com.osama.lifeshare;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptor_search_donor extends RecyclerView.Adapter<Adaptor_search_donor.MyViewHolder>


{

    ArrayList<search_all_donor_fragment_support> list;
    public Adaptor_search_donor(ArrayList<search_all_donor_fragment_support> list)
    {
        this.list = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rows_for_search_all_donor,viewGroup,false);


           return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.name_searchalldonor.setText(list.get(i).getName());
        myViewHolder.phone_searchalldonor.setText(list.get(i).getPhone());
        myViewHolder.blood_Group_searchalldonor.setText(list.get(i).getBlood_Group());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView name_searchalldonor,blood_Group_searchalldonor,phone_searchalldonor;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name_searchalldonor = itemView.findViewById(R.id.name_searchalldonor);
            blood_Group_searchalldonor = itemView.findViewById(R.id.group_searchalldonor);
            phone_searchalldonor = itemView.findViewById(R.id.phone_searchalldonor);
        }
    }
}
