package com.rto.prepzone;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PracticeAdapter extends RecyclerView.Adapter<PracticeAdapter.ViewHolder> {
    Context context;
    List<PracticeModel> practiceModelList;

    public PracticeAdapter(Context context, List<PracticeModel> practiceModelList) {
        this.context = context;
        this.practiceModelList = practiceModelList;
    }

    @NonNull
    @Override
    public PracticeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_practice, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PracticeAdapter.ViewHolder holder, int position) {

        PracticeModel model = practiceModelList.get(position);
        holder.question.setText(model.getQuestion());
        holder.option1.setText(model.getOption1());
        holder.option2.setText(model.getOption2());
        holder.option3.setText(model.getOption3());
        holder.option1.setBackgroundColor(Color.parseColor("#EBFFE6"));
        holder.option2.setBackgroundColor(Color.parseColor("#EBFFE6"));
        holder.option3.setBackgroundColor(Color.parseColor("#EBFFE6"));
        holder.ans.setText(model.getAnswer());

    }

    @Override
    public int getItemCount() {
        return practiceModelList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView question, ans, option1, option2, option3;
        boolean isAnswered = true;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.txtq);
            option1 = itemView.findViewById(R.id.txtAns1);
            option2 = itemView.findViewById(R.id.txtAns2);
            option3 = itemView.findViewById(R.id.txtAns3);
            ans = itemView.findViewById(R.id.txtAns);


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
            PracticeModel model = practiceModelList.get(getAdapterPosition());
            int answer = Integer.parseInt(model.getAnswer());
            switch (option) {
                case 1:
                    if (option == answer)
                        option1.setBackgroundColor(Color.GREEN);
                    else
                        option1.setBackgroundColor(Color.RED);
                    option2.setBackgroundColor(Color.parseColor("#EBFFE6"));
                    option3.setBackgroundColor(Color.parseColor("#EBFFE6"));

                    break;
                case 2:
                    if (option == answer)
                        option2.setBackgroundColor(Color.GREEN);
                    else
                        option2.setBackgroundColor(Color.RED);
                    option1.setBackgroundColor(Color.parseColor("#EBFFE6"));
                    option3.setBackgroundColor(Color.parseColor("#EBFFE6"));
                    break;
                case 3:
                    if (option == answer)
                        option3.setBackgroundColor(Color.GREEN);
                    else
                        option3.setBackgroundColor(Color.RED);
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


}
