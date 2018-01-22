package com.example.kolot.test_balina_1.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kolot.test_balina_1.R;
import com.example.kolot.test_balina_1.dbModel.Images;
import com.example.kolot.test_balina_1.networking.dto.Images.postImageDto;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kolot on 16.01.2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Images check;

    public void addDates(ArrayList<postImageDto> data) {
        this.dates.addAll(data);
        List<Images> dbModelList = Images.listAll(Images.class);
        Log.e("dates: ", String.valueOf(dates.size()));
        Log.e("db: ", String.valueOf(dbModelList.size()));

        if (dbModelList.size() != dates.size()) {
            Images.deleteAll(Images.class);
            for (int i = 0; i < dates.size(); i++) {

                Images model = new Images(dates.get(i).getId(), dates.get(i).getDate(), dates.get(i).getUrl());

                model.save();
            }
            Log.e("db: ", String.valueOf(dbModelList.size()));

        }


        Log.e("responsePost", dbModelList.toString());

        notifyDataSetChanged();
    }

    public interface ItemClickListener {
        void onClick(String url, int date);
    }

    public interface ItemLongClickListener {
        void onLongClick(int id);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private CardView cardView;
        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.txtViewItem);
            cardView = (CardView) itemView.findViewById(R.id.cardPhotoItem);
            imageView = (ImageView) itemView.findViewById(R.id.imageItem);
        }
    }

    public void setDates(ArrayList<postImageDto> dates) {
        this.dates = dates;
        notifyDataSetChanged();
    }

    public void refreshDates(int id) {
        List<Images> dbModelList = Images.listAll(Images.class);
        Images model;
        for (int i = 0; i < dbModelList.size(); i++) {
            if (dbModelList.get(i).photoId == id) {
                model = Images.findById(Images.class, dbModelList.get(i).getId());
                model.delete();
            }
        }
        notifyDataSetChanged();
    }


    private ArrayList<postImageDto> dates = new ArrayList<>();
    private Context context;


    public RecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    private ItemClickListener itemClickListener;
    private ItemLongClickListener itemLongClickListener;

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        List<Images> dbModelList = Images.listAll(Images.class);


        final String url = dbModelList.get(position).url;
        final int date = dbModelList.get(position).date;
        final int id = dbModelList.get(position).photoId;


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onClick(url, date);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                itemLongClickListener.onLongClick(id);
                return false;
            }
        });


        holder.textView.setText(String.valueOf(dbModelList.get(position).date));
        Picasso.with(context).load(dbModelList.get(position).url).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return dates.size();
    }

    public void setOnLongClickListener(ItemLongClickListener itemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
