package com.example.consumerestapis.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.consumerestapis.R;
import com.example.consumerestapis.callbacks.ImageDownloaderCallback;
import com.example.consumerestapis.platform.models.responseDTO.CountryRow;
import com.example.consumerestapis.retrofit.CountryDetailService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class CountryDetailsListAdapter extends RecyclerView.Adapter<CountryDetailsListAdapter.MyViewHolder> {
    private Context mContext;
    private List<CountryRow> countryRows;
    private CountryDetailService.ImageDownloader imageDownloader;

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
        if (newCountryRow.getImageHref() != null) {
            imageDownloader = new CountryDetailService.ImageDownloader(
                    new ImageDownloaderCallback() {
                        @Override
                        public void getCallback(Bitmap bitmap) {
                            holder.image_to_show.setImageBitmap(bitmap);
                        }
                    }, holder
            );
            imageDownloader.execute(newCountryRow.getImageHref());
        }
    }

    @Override
    public int getItemCount() {
        return countryRows.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title, description;
        public ImageView image_to_show;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            image_to_show = (ImageView) itemView.findViewById(R.id.show_image);
        }
    }
}
