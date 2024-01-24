package com.rto.prepzone.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rto.prepzone.ExamModel;
import com.rto.prepzone.PracticeModel;
import com.rto.prepzone.QuestionAnsModel;
import com.rto.prepzone.TrafficSignModel;
import com.rto.prepzone.VideoModel;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ApiClient {
    public static RtoPrepZoneInterface rtoPrepZoneInterface;

    public static RtoPrepZoneInterface getRtoPrepZoneInterface() {
        if (rtoPrepZoneInterface == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(80, TimeUnit.SECONDS)
                    .connectTimeout(80, TimeUnit.SECONDS)
                    .build();
            Gson gson = new GsonBuilder()
                    .setLenient().create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://mapi.trycatchtech.com")
                    // .addConverterFactory(ScalerConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
            rtoPrepZoneInterface = retrofit.create(RtoPrepZoneInterface.class);

        }
        return rtoPrepZoneInterface;
    }

    public interface RtoPrepZoneInterface {
        @GET("/v3/rto/text_question_list")
        Call<ArrayList<QuestionAnsModel>> getQuestionAnsList();
        @GET("/v3/rto/image_question_list")
        Call<ArrayList<TrafficSignModel>> getTrafficSign();
        @GET("/v3/rto/practice_question_list")
        Call<ArrayList<PracticeModel>> getPracticeQuestion();
        @GET("/v3/rto/exam_question_list")
        Call<ArrayList<ExamModel>> getExamQuestionList();
        @GET("/v3/rto/all_video_tutorials")
        Call<ArrayList<VideoModel>> getVideo();

    }

}
