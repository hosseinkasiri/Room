package com.example.roomdatabase.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.roomdatabase.R;
import com.example.roomdatabase.database.AppDatabase;
import com.example.roomdatabase.model.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String ADD_DIALOG = "com.example.roomDatabase.controller_add";
    private ImageButton mAddButton;
    private RecyclerView mRecyclerView;
    private List<User> mUsers;
    private UserAdapter mUserAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddUserDialog addUserDialog = new AddUserDialog();
                addUserDialog.show(getSupportFragmentManager(), ADD_DIALOG);
                addUserDialog.setOnAddButtonClick(new AddUserDialog.OnAddButtonClick() {
                    @Override
                    public void onAddButton(String firstName, String lastName) {
                        User user = new User();
                        user.setFirstName(firstName);
                        user.setLastName(lastName);
                        AppDatabase.getInstance(getApplicationContext()).mUserDao().insertAll(user);
                        updateUi();
                    }
                });
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        updateUi();
    }

    private void updateUi() {
        mUsers = AppDatabase.getInstance(this).mUserDao().getAll();
        if (mUserAdapter == null) {
            mUserAdapter = new UserAdapter(this, mUsers, new UserAdapter.OnDeleteUser() {
                @Override
                public void onDeleteUser(User user, int position) {
                    AppDatabase.getInstance(MainActivity.this).mUserDao().delete(user);
                    mUserAdapter.notifyItemRemoved(position);
                }
            });
            mRecyclerView.setAdapter(mUserAdapter);
        }else {
            mUserAdapter.setUsers(mUsers);
            mUserAdapter.notifyDataSetChanged();
        }
    }

    private void findViews() {
        mAddButton = findViewById(R.id.add_button);
        mRecyclerView = findViewById(R.id.recycler_view);
    }
}