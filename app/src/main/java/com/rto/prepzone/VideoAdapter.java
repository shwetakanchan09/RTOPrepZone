package com.rto.prepzone;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    Context context;
    List<VideoModel> videoModelList;

    public VideoAdapter(Context context, List<VideoModel> videoModelList) {
        this.context = context;
        this.videoModelList = videoModelList;
    }

    @NonNull
    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.ViewHolder holder, int position) {
        VideoModel model = videoModelList.get(position);
        holder.txtHeading.setText(model.getTitle());
       // holder.url.setVideoPath(model.getUrl());

      /*  Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(model.getImage())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.imgPic);*/
        holder.url.getYouTubePlayerWhenReady(youTubePlayer -> model.getUrl());
        holder.url.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = "dRadE0kjS_Q";
                youTubePlayer.loadVideo(model.getUrl(), 0f);
            }
        });

    }

    @Override
    public int getItemCount() {
        return videoModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPic;
        TextView txtHeading;
       // VideoView url;
        YouTubePlayerView url;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

         //   imgPic = itemView.findViewById(R.id.imgProfile);
            txtHeading = itemView.findViewById(R.id.txtHeading);
            url = itemView.findViewById(R.id.url);



          /*  String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video;
            Uri uri = Uri.parse(videoPath);
            holder.url.setVideoURI(uri);
            MediaController mediaController = new MediaController(context);
            url.setMediaController(mediaController);
            mediaController.setAnchorView(url);*/

          /*  MediaController mediaController= new MediaController(context);
            mediaController.setAnchorView(url);

            //specify the location of media file
            Uri uri=Uri.parse(Environment.getExternalStorageDirectory().getPath()+"/media/1.mp4");

            //Setting MediaController and URI, then starting the videoView
            url.setMediaController(mediaController);
            url.setVideoURI(uri);
            url.requestFocus();
            url.start();*/

        }

    }
}
