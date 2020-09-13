package com.example.roomdatabase.controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.roomdatabase.R;

public class AddUserDialog extends DialogFragment {

    private EditText mFirstName, mLastName;
    private OnAddButtonClick mOnAddButtonClick;

    public interface OnAddButtonClick{
        void onAddButton(String firstName, String lastName);
    }

    public void setOnAddButtonClick(OnAddButtonClick onAddButtonClick) {
        mOnAddButtonClick = onAddButtonClick;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_user, null);
        findViews(view);
        return new AlertDialog.Builder(getContext())
                .setView(view)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mOnAddButtonClick.onAddButton(mFirstName.getText().toString(), mLastName.getText().toString());
                        dismiss();
                    }
                }).create();
    }

    private void findViews(View view) {
        mFirstName = view.findViewById(R.id.first_name_dialog);
        mLastName = view.findViewById(R.id.last_name_dialog);
    }
}
