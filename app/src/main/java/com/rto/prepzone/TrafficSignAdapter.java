package com.rto.prepzone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TrafficSignAdapter extends RecyclerView.Adapter<TrafficSignAdapter.ViewHolder> {

    Context context;
    List<TrafficSignModel> trafficSignModelList;

    public TrafficSignAdapter(Context context, List<TrafficSignModel> trafficSignModelList) {
        this.context = context;
        this.trafficSignModelList = trafficSignModelList;
    }

    @NonNull
    @Override
    public TrafficSignAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_traffic_sign, parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TrafficSignAdapter.ViewHolder holder, int position) {
       // TrafficSignModel model = trafficSignModelList.get(position);

        holder.signName.setText(trafficSignModelList.get(position).getName());

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(trafficSignModelList.get(position).getImage())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.imgSign);

    }

    @Override
    public int getItemCount() {
        return trafficSignModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView signName;
        ImageView imgSign;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            signName = itemView.findViewById(R.id.signName);
            imgSign = itemView.findViewById(R.id.imgSign);
        }
    }
}
