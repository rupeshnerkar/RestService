package com.example.consumerestapis.retrofit;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.example.consumerestapis.adapter.CountryDetailsListAdapter;
import com.example.consumerestapis.callbacks.ImageDownloaderCallback;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountryDetailService {

    private static final String BASE_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor).build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }

    public static class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
        private ImageDownloaderCallback imageDownloaderCallback;
        private CountryDetailsListAdapter.MyViewHolder holder;

        public ImageDownloader(ImageDownloaderCallback imageDownloaderCallback, CountryDetailsListAdapter.MyViewHolder holder) {
            this.imageDownloaderCallback = imageDownloaderCallback;
            this.holder = holder;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            URL url = null;
            try {
                url = new URL(strings[0]);
                return BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null)
                holder.image_to_show.setImageBitmap(bitmap);
        }
    }
//    public static class ImageDownloader
}

