package com.example.angelruiz.cursoandroid.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.angelruiz.cursoandroid.R;

public class DialogFragmentEditApiRest extends DialogFragment {
    Context context;
    View view;
    EditText etEditRegister;
    Button btCancelEdit, btConfirmEdit;
    public String input;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialogfragmenteditapirest, container, false);
          etEditRegister = view.findViewById(R.id.etEditRegister);
          btCancelEdit = view.findViewById(R.id.btCancelEdit);
          btConfirmEdit = view.findViewById(R.id.btConfirmEdit);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btCancelEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getDialog() != null){
                    getDialog().cancel();
                }
            }
        });

        btConfirmEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input = etEditRegister.getText().toString();
                if (!input.isEmpty() && getDialog() != null){
                    getDialog().dismiss();

                    FragmentApiRest fragmentApiRest = new FragmentApiRest();
                    Bundle passData = new Bundle();
                    passData.putString("name", input);
                    fragmentApiRest.setArguments(passData);


                }
            }
        });
    }
}
