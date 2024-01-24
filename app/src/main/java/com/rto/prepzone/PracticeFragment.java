package com.rto.prepzone;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.rto.prepzone.helpers.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PracticeFragment extends DialogFragment {
    Activity context;
    RecyclerView recyclerView;
    PracticeAdapter adapter;
    LinearLayout llnext;
    ProgressDialog progressDoalog;
    ViewPager viewPager;
    LinearLayoutManager HorizontalLayout;
    ImageView back;
    int count = 0;

    @Override
    public int getTheme() {
        return R.style.FullScreenDialog;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_practice, container, false);

        context = requireActivity();

        llnext = v.findViewById(R.id.llnext);
        recyclerView = v.findViewById(R.id.recyclerView);
        viewPager = v.findViewById(R.id.viewPager);
        back = v.findViewById(R.id.imgback);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        llnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                recyclerView.scrollToPosition(count);
            }
        });

        progressDoalog = new ProgressDialog(requireContext());
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();
        Call<ArrayList<PracticeModel>> call = ApiClient.getRtoPrepZoneInterface().getPracticeQuestion();
        call.enqueue(new Callback<ArrayList<PracticeModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PracticeModel>> call, Response<ArrayList<PracticeModel>> response) {
                progressDoalog.dismiss();
                if (response.code() == 200 && response.body() != null) {
//                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
//                    recyclerView.setLayoutManager(linearLayoutManager);

                    HorizontalLayout = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                    recyclerView.setLayoutManager(HorizontalLayout);


                    adapter = new PracticeAdapter(requireContext(), response.body());
                    viewPager.setOffscreenPageLimit(response.body().size());
                    recyclerView.setAdapter(adapter);
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<ArrayList<PracticeModel>> call, Throwable t) {
                Toast.makeText(requireContext(), "onFailure", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }

}