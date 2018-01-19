package com.example.kolot.test_balina_1.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kolot.test_balina_1.R;

import java.util.ArrayList;

/**
 * Created by kolot on 17.01.2018.
 */

public class DetailInfoRVAdapter extends RecyclerView.Adapter <DetailInfoRVAdapter.DetailViewHolder> {

   ArrayList<String> info = new ArrayList<>();

    public DetailInfoRVAdapter(ArrayList<String> info) {
        this.info = info;
    }

    @Override
    public DetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_info_rv_item, parent,false);
        v.setOnLongClickListener(new MyOnClickListener());
        return new DetailViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DetailViewHolder holder, int position) {
        holder.comment.setText("Comment!");
        holder.date.setText(info.get(position));
    }

    @Override
    public int getItemCount() {
        return info.size();
    }

    public class DetailViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        private TextView comment, date;

        public DetailViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cardView_detail_item);
            comment = (TextView) itemView.findViewById(R.id.detail_comment_item);
            date = (TextView) itemView.findViewById(R.id.detail_date_item);
        }
    }
    public static class MyOnClickListener implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View view) {
            return false;
        }
    }
}

