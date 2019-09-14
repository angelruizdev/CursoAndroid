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

import com.example.angelruiz.cursoandroid.InterfazAPI_REST.ICommunicateDialogFmtWithFragmentApiRest;
import com.example.angelruiz.cursoandroid.R;

//for create dialogfragment must be extend of this
public class DialogFragmentEditApiRest extends DialogFragment {
    Context context;
    View view;
    private EditText etEditRegister;
    private Button btCancelEdit, btConfirmEdit;
    private String input;

    //for pass data to fmt main(fragmentapirest) with interface
    private ICommunicateDialogFmtWithFragmentApiRest iCommunicateDialogFmtWithFragmentApiRest;

    //it's called when the fragment it join to the context of the activity content
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        //we cast the fragment destination target to interface, for activity we cast with getActivity()
        iCommunicateDialogFmtWithFragmentApiRest = (ICommunicateDialogFmtWithFragmentApiRest)getTargetFragment();
    }

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

        //cancel dialogfragment and discards
        btCancelEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getDialog() != null){
                    getDialog().cancel();
                }
            }
        });

        //confirm input and pass data to the interface
        btConfirmEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input = etEditRegister.getText().toString();
                if (!input.isEmpty() && getDialog() != null){

                    //we verific if exist interface for pass data to fragmentapirest
                    if (iCommunicateDialogFmtWithFragmentApiRest != null){
                         iCommunicateDialogFmtWithFragmentApiRest.passDataDialogFragment(input);
                     }
                    getDialog().dismiss();
                }
            }
        });
    }

    //the fmt this created, in use for the user
    @Override
    public void onResume() {
        super.onResume();
    }

    //we remove the dialogfragment if no edit and exit with home
    @Override
    public void onPause() {
        super.onPause();
        dismiss();
    }

    //we remove the dialogfragment for what no stay floating
    @Override
    public void onDetach() {
        super.onDetach();
        dismiss();
    }
}
