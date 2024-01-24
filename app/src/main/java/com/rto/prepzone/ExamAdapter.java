package com.rto.prepzone;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ViewHolder> {

    Context context;
    List<ExamModel> examModelList;
    OnAnswerListener onAnswerListener;

    public void setOnAnswerListener(OnAnswerListener onAnswerListener) {
        this.onAnswerListener = onAnswerListener;
    }

    public ExamAdapter(Context context, List<ExamModel> examModelList) {
        this.context = context;
        this.examModelList = examModelList;
    }

    @NonNull
    @Override
    public ExamAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_practice, parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExamAdapter.ViewHolder holder, int position) {
        ExamModel model = examModelList.get(position);
        holder.question.setText(model.getQuestion());
        holder.ans.setText(model.getAnswer());
        holder.option1.setText(model.getOption1());
        holder.option2.setText(model.getOption2());
        holder.option3.setText(model.getOption3());
        holder.option1.setBackgroundColor(Color.parseColor("#EBFFE6"));
        holder.option2.setBackgroundColor(Color.parseColor("#EBFFE6"));
        holder.option3.setBackgroundColor(Color.parseColor("#EBFFE6"));
    }

    @Override
    public int getItemCount() {
        return examModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView question,ans,option1,option2,option3;
        LinearLayout llcorrection;
        boolean isAnswered = true;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.txtq);
            ans = itemView.findViewById(R.id.txtAns);
            option1 = itemView.findViewById(R.id.txtAns1);
            option2 = itemView.findViewById(R.id.txtAns2);
            option3 = itemView.findViewById(R.id.txtAns3);

            llcorrection = itemView.findViewById(R.id.llcorrection);

                if (!isAnswered) {
                    option1.setBackgroundColor(Color.parseColor("#EBFFE6"));
                    option2.setBackgroundColor(Color.parseColor("#EBFFE6"));
                    option3.setBackgroundColor(Color.parseColor("#EBFFE6"));
                }
                option1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (isAnswered)
                            selectOption(1);
                    }
                });
                option2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (isAnswered)
                            selectOption(2);
                    }
                });
                option3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (isAnswered)
                            selectOption(3);
                    }
                });

            }


        void selectOption(int option) {
            Log.d("TAG", "selectOption: ");
            isAnswered = true;
            ExamModel model = examModelList.get(getAdapterPosition());
            int answer = Integer.parseInt(model.getAnswer());
            switch (option) {
                case 1:
                    if (option == answer){
                        option1.setBackgroundColor(Color.GREEN);
                        onAnswerListener.onAnswer(true);
                    } else {
                        option1.setBackgroundColor(Color.RED);
                        onAnswerListener.onAnswer(false);
                    }
                    option2.setBackgroundColor(Color.parseColor("#EBFFE6"));
                    option3.setBackgroundColor(Color.parseColor("#EBFFE6"));

                    break;
                case 2:
                    if (option == answer){
                        option2.setBackgroundColor(Color.GREEN);
                        onAnswerListener.onAnswer(true);
                    } else {
                        option2.setBackgroundColor(Color.RED);
                        onAnswerListener.onAnswer(false);
                    }
                    option1.setBackgroundColor(Color.parseColor("#EBFFE6"));
                    option3.setBackgroundColor(Color.parseColor("#EBFFE6"));
                    break;
                case 3:
                    if (option == answer) {
                        option3.setBackgroundColor(Color.GREEN);
                        onAnswerListener.onAnswer(true);
                    } else {
                        option3.setBackgroundColor(Color.RED);
                        onAnswerListener.onAnswer(false);
                    }
                    option1.setBackgroundColor(Color.parseColor("#EBFFE6"));
                    option2.setBackgroundColor(Color.parseColor("#EBFFE6"));
                    break;

            }

            if (answer == 1)
                option1.setBackgroundColor(Color.GREEN);
            if (answer == 2)
                option2.setBackgroundColor(Color.GREEN);
            if (answer == 3)
                option3.setBackgroundColor(Color.GREEN);

        }

    }
    interface OnAnswerListener {
        void onAnswer(boolean isCorrect);
    }
}
