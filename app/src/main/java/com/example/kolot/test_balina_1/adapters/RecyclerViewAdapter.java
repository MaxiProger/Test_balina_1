package com.example.kolot.test_balina_1.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kolot.test_balina_1.R;

import java.util.ArrayList;

/**
 * Created by kolot on 16.01.2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    public interface ItemClickListener {
        void onClick(String date);
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;
        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.txtViewItem);
            imageView = (ImageView) itemView.findViewById(R.id.imageItem);
        }
    }

    private ArrayList<String> dates = new ArrayList<>();


    public RecyclerViewAdapter(ArrayList <String> dates){
        this.dates=dates;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }
    private ItemClickListener itemClickListener;
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String date = dates.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onClick(date);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Log.d("long click", "loooong");
                return false;
            }
        });
        holder.textView.setText(date);
        holder.imageView.setImageResource(R.drawable.ic_menu_gallery);
    }

    @Override
    public int getItemCount() {
        return dates.size();
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
