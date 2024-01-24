package com.rto.prepzone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuestionAnsAdapter extends RecyclerView.Adapter<QuestionAnsAdapter.ViewHolder> {

    Context context;
    List<QuestionAnsModel> questionAnsModelList;

    public QuestionAnsAdapter(Context context, List<QuestionAnsModel> questionAnsModelList) {
        this.context = context;
        this.questionAnsModelList = questionAnsModelList;
    }

    @NonNull
    @Override
    public QuestionAnsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question_ans, parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionAnsAdapter.ViewHolder holder, int position) {
        QuestionAnsModel model = questionAnsModelList.get(position);
        holder.questions.setText(model.getQuestion());
        holder.ans.setText(model.getAnswer());

    }

    @Override
    public int getItemCount() {
        return questionAnsModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView questions,ans;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            questions = itemView.findViewById(R.id.questions);
            ans = itemView.findViewById(R.id.answer);
        }
    }
}
