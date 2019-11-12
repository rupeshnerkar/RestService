package com.example.consumerestapis.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.consumerestapis.R;
import com.example.consumerestapis.platform.models.responseDTO.CanadaRows;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {
    private Context mContext;
    private List<CanadaRows> canadaRows;

    public ListAdapter(Context mContext, List<CanadaRows> canadaRows) {
        this.mContext = mContext;
        this.canadaRows = canadaRows;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rowlayout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CanadaRows newCanadaRows = canadaRows.get(position);
        Log.e("Again",newCanadaRows.getTitle());
        holder.title.setText(newCanadaRows.getTitle());

    }

    @Override
    public int getItemCount() {
        return canadaRows.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title, description;
        private ImageView image_to_show;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            image_to_show = (ImageView) itemView.findViewById(R.id.show_image);
        }
    }
}
