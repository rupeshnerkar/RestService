package com.wipro.consumerestapis.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;
import com.wipro.consumerestapis.retrofit.OkHttp3Downloader;
import com.wipro.consumerestapis.R;
import com.wipro.consumerestapis.platform.models.responseDTO.CountryRow;

import java.io.IOException;
import java.util.List;

public class CountryDetailsListAdapter extends RecyclerView.Adapter<CountryDetailsListAdapter.MyViewHolder> {
    private Context mContext;
    private List<CountryRow> countryRows;

    public CountryDetailsListAdapter(Context mContext, List<CountryRow> countryRows) {
        this.mContext = mContext;
        this.countryRows = countryRows;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rowlayout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        CountryRow newCountryRow = countryRows.get(position);
        holder.title.setText(newCountryRow.getTitle() == null ? "NA" : newCountryRow.getTitle());
        holder.description.setText(newCountryRow.getDescription() == null ? "NA" : newCountryRow.getDescription());
        String imageUrl = newCountryRow.getImageHref();
        if (imageUrl != null)
            imageUrl = imageUrl.trim();
        System.out.println(imageUrl);
        Picasso.Builder picassoBuilder = new Picasso.Builder(mContext);
        picassoBuilder.downloader(new OkHttp3Downloader(mContext));
        final Picasso picasso = picassoBuilder.build();
        final Bitmap[] bitmap = new Bitmap[1];
        final String finalImageUrl = imageUrl;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (finalImageUrl != null) {
                        bitmap[0] = picasso.load(finalImageUrl).get();
                    } else {
                        bitmap[0] = picasso.load(R.drawable.cover_small).get();
                    }

                } catch (IOException e) {
                    System.out.println(e.getMessage() + " " + finalImageUrl);
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        picasso.load(imageUrl).error(R.drawable.ic_launcher_foreground).placeholder(mContext.getDrawable(R.drawable.cover_small)).into(holder.image_to_show);

        holder.image_to_show.setImageBitmap(bitmap[0]);


    }

    @Override
    public int getItemCount() {
        return countryRows.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title, description;
        public ImageView image_to_show;

        MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            image_to_show = (ImageView) itemView.findViewById(R.id.show_image);
        }
    }
}
