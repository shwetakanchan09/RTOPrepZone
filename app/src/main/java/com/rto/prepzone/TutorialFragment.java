package com.rto.prepzone;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rto.prepzone.helpers.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TutorialFragment extends DialogFragment {

    RecyclerView recyclerView;
    VideoAdapter adapter;
    Activity context;
    ProgressDialog progressDoalog;

    @Override
    public int getTheme() {
        return R.style.FullScreenDialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tutorial, container, false);
        context = requireActivity();

        recyclerView = v.findViewById(R.id.recyclerView);
        progressDoalog = new ProgressDialog(requireContext());
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        Call<ArrayList<VideoModel>> call = ApiClient.getRtoPrepZoneInterface().getVideo();
        call.enqueue(new Callback<ArrayList<VideoModel>>() {
            @Override
            public void onResponse(Call<ArrayList<VideoModel>> call, Response<ArrayList<VideoModel>> response) {
                progressDoalog.dismiss();
                if (response.code()==200 && response.body()!=null) {
                    generateVideoList(response.body());
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show();
                   }
                }

            @Override
            public void onFailure(Call<ArrayList<VideoModel>> call, Throwable t) {
                Toast.makeText(requireContext(), "onFailure", Toast.LENGTH_SHORT).show();

            }
        });

        return v;
    }
    private void generateVideoList(List<VideoModel> videoModelList) {
        adapter = new VideoAdapter(requireContext(),videoModelList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

}