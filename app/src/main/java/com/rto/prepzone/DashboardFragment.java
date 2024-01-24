package com.rto.prepzone;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.viewpager.widget.ViewPager;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.rto.prepzone.helpers.Common;

public class DashboardFragment extends DialogFragment {
    // creating object of ViewPager
    ViewPager mViewPager;
    RelativeLayout questionBank,practice,exam, video;
    Activity context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_dashboard, container, false);
        questionBank = view.findViewById(R.id.questionBank);
        practice = view.findViewById(R.id.practice);
        exam = view.findViewById(R.id.exam);
        video = view.findViewById(R.id.video);
        context = requireActivity();

       /* questionBank.startAnimation(AnimationUtils.loadAnimation(context, R.anim.zoom_in));
        practice.startAnimation(AnimationUtils.loadAnimation(context, R.anim.zoom_in));
        exam.startAnimation(AnimationUtils.loadAnimation(context, R.anim.zoom_in));
        video.startAnimation(AnimationUtils.loadAnimation(context, R.anim.zoom_in));*/

        questionBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Common.openFragment(new QuestionBankFragment());
            }
        });
        practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Common.openFragment(new PracticeFragment());
            }
        });
        exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Common.openFragment(new ExamFragment());
            }
        });
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Common.openFragment(new TutorialFragment());
            }
        });

        return view;
    }

}