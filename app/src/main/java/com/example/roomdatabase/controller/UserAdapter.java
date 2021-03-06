package com.example.roomdatabase.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdatabase.R;
import com.example.roomdatabase.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder>{

    private Context mContext;
    private List<User> mUsers;
    private OnDeleteUser mOnDeleteUser;

    public UserAdapter(Context context, List<User> users, OnDeleteUser onDeleteUser) {
        mContext = context;
        mUsers = users;
        mOnDeleteUser = onDeleteUser;
    }

    public interface OnDeleteUser{
        void onDeleteUser(User user, int position);
    }

    public void setUsers(List<User> users) {
        mUsers = users;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_item, parent, false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        User user = mUsers.get(position);
        holder.bind(user);
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mFirstText, mLastName;
        private User mUser;

        public UserHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mFirstText = itemView.findViewById(R.id.first_name_item);
            mLastName = itemView.findViewById(R.id.last_name_item);
        }

        public void bind(User user){
            mUser = user;
            mFirstText.setText(user.getFirstName());
            mLastName.setText(user.getLastName());
        }

        @Override
        public void onClick(View v) {
            mUsers.remove(getAdapterPosition());
            mOnDeleteUser.onDeleteUser(mUser, getAdapterPosition());
        }
    }
}
