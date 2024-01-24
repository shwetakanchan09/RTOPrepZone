package com.rto.prepzone;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;

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

public class TrafficSignFragment extends Fragment {

    RecyclerView recyclerView;
    TrafficSignAdapter adapter;
    Activity context;
    ProgressDialog progressDoalog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_traffic_sign, container, false);
        context = requireActivity();

        recyclerView = view.findViewById(R.id.recyclerView);
        progressDoalog = new ProgressDialog(requireContext());
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        Call<ArrayList<TrafficSignModel>> call = ApiClient.getRtoPrepZoneInterface().getTrafficSign();
        call.enqueue(new Callback<ArrayList<TrafficSignModel>>() {
            @Override
            public void onResponse(Call<ArrayList<TrafficSignModel>> call, Response<ArrayList<TrafficSignModel>> response) {
                progressDoalog.dismiss();
                if (response.code()==200 && response.body()!=null) {
                    generateDataList(response.body());
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<ArrayList<TrafficSignModel>> call, Throwable t) {
                Toast.makeText(requireContext(), "onFailure", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
    private void generateDataList(List<TrafficSignModel> photoList) {
        adapter = new TrafficSignAdapter(requireContext(),photoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

}