package com.rto.prepzone;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rto.prepzone.helpers.ApiClient;
import com.rto.prepzone.helpers.Common;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExamFragment extends DialogFragment {
    Activity context;
    TextView correctAns,wrongAns;
    RecyclerView recyclerView;
    ExamAdapter adapter;
    LinearLayout llnext;
    ProgressDialog progressDoalog;
    ViewPager viewPager;
    LinearLayoutManager HorizontalLayout;
    ImageView back;
    int count = 0;
    int correct = 0, wrong = 0;
    @Override
    public int getTheme() {
        return R.style.FullScreenDialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_exam, container, false);
        context = requireActivity();

        llnext = v.findViewById(R.id.llnext);
        recyclerView = v.findViewById(R.id.recyclerView);
        viewPager = v.findViewById(R.id.viewPager);
        back = v.findViewById(R.id.imgback);
        correctAns = v.findViewById(R.id.txtCorrectAns);
        wrongAns = v.findViewById(R.id.txtWrongAns);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backAlert();
            }
        });

        llnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapter.getItemCount()-1 == count) {
                    Common.openFragment(new ResultFragment(wrong,correct));
                } else {
                    recyclerView.scrollToPosition(count);
                    count++;
                }

            }
        });

        getExamQuestionList();
        return v;
    }
    public void backAlert(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Do yoy want to stop exam ?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dismiss();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
    public void getExamQuestionList(){
        progressDoalog = new ProgressDialog(requireContext());
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();
        Call<ArrayList<ExamModel>> call = ApiClient.getRtoPrepZoneInterface().getExamQuestionList();
        call.enqueue(new Callback<ArrayList<ExamModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ExamModel>> call, Response<ArrayList<ExamModel>> response) {
                progressDoalog.dismiss();

                if (response.code()==200 && response.body()!=null) {
//                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
//                    recyclerView.setLayoutManager(linearLayoutManager);

                    HorizontalLayout = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                    recyclerView.setLayoutManager(HorizontalLayout);
                    Log.d("TAG", "onResponse: Size "+response.body().size());

                    adapter = new ExamAdapter(requireContext(),response.body());
                    adapter.setOnAnswerListener(new ExamAdapter.OnAnswerListener() {
                        @Override
                        public void onAnswer(boolean isCorrect) {
                            if (isCorrect){
                                correct = Integer.parseInt(correctAns.getText().toString());
                                correct++;
                                correctAns.setText(String.valueOf(correct));
                            } else  {
                                wrong = Integer.parseInt(wrongAns.getText().toString());
                                wrong++;
                                wrongAns.setText(String.valueOf(wrong));
                            }
                        }
                    });
                    recyclerView.setAdapter(adapter);
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<ArrayList<ExamModel>> call, Throwable t) {
                Toast.makeText(requireContext(), "onFailure", Toast.LENGTH_SHORT).show();

            }
        });
    }
}