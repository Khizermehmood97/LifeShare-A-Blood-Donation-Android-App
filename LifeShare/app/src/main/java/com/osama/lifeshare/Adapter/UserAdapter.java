package com.osama.lifeshare.Adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.osama.lifeshare.R;
import com.osama.lifeshare.User;

import java.util.List;



public class UserAdapter extends RecyclerView.Adapter <UserAdapter.ViewHolder> {

    private Context mContext;
    private List<User> mUsers;

    public UserAdapter(Context mContext, List<User> mUsers) {
        this.mContext = mContext;
        this.mUsers = mUsers;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_item, parent, false);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        User user = mUsers.get(position);
        holder.username.setText(user.getName());
        holder.phonenumber.setText(user.getPhone());
        holder.bloodgroup.setText(user.getBlood_Group());
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(mContext).inflate(R.layout.user_item, parent, false);
//        return new UserAdapter.ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(com.example.khizermehmood.signup.UserAdapter.ViewHolder holder, int position) {
//
//        User user = mUsers.get(position);
//        holder.username.setText(user.getName());
//        holder.phonenumber.setText(user.getPhone());
//        holder.bloodgroup.setText(user.getBlood_Group());
//    }
//
//    @Override
//    public int getItemCount() {
//        return mUsers.size();
//    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView username;
        public TextView phonenumber;
        public TextView bloodgroup;

        public ViewHolder(View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.username);
            phonenumber = itemView.findViewById(R.id.phonenumber);
            bloodgroup = itemView.findViewById(R.id.bloodgroup);
        }
    }
}
