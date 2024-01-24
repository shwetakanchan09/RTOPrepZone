package com.rto.prepzone;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ResultFragment extends DialogFragment {

    TextView txtCrrect,txtWrong;
    @Override
    public int getTheme() {
        return R.style.FullScreenDialog;
    }
    int wrong = 0, correct = 0;
    public ResultFragment(int wrong, int correct) {
        this.correct = correct;
        this.wrong = wrong;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        txtCrrect = view.findViewById(R.id.txtCorrect);
        txtWrong = view.findViewById(R.id.txtWrong);

        txtCrrect.setText(String.valueOf(correct));
        txtWrong.setText(String.valueOf(wrong));

        return view;
    }
}