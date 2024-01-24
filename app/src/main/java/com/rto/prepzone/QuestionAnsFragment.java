package com.rto.prepzone;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.rto.prepzone.helpers.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionAnsFragment extends Fragment {

    Activity context;
    RecyclerView recyclerView;
    QuestionAnsAdapter adapter;
    ImageView back;
    LinearLayout screen;
    ProgressDialog progressDoalog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_question_ans, container, false);
        context = requireActivity();

        recyclerView = v.findViewById(R.id.recyclerView);
       // back = v.findViewById(R.id.icon);
        screen = v.findViewById(R.id.llQuestBank);
      //  screen.startAnimation(AnimationUtils.loadAnimation(context,R.anim.side_anim));

        getQuestionAnsList();
        return v;
    }
    private void getQuestionAnsList(){
        progressDoalog = new ProgressDialog(requireContext());
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        Call<ArrayList<QuestionAnsModel>> call = ApiClient.getRtoPrepZoneInterface().getQuestionAnsList();
        call.enqueue(new Callback<ArrayList<QuestionAnsModel>>() {
            @Override
            public void onResponse(Call<ArrayList<QuestionAnsModel>> call, Response<ArrayList<QuestionAnsModel>> response) {
                progressDoalog.dismiss();
                if (response.code()==200 && response.body()!=null) {
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    adapter = new QuestionAnsAdapter(requireContext(),response.body());
                    recyclerView.setAdapter(adapter);
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ArrayList<QuestionAnsModel>> call, Throwable t) {
                Toast.makeText(requireContext(), "onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}