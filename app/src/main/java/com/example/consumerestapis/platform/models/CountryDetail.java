package com.example.consumerestapis.platform.models;

import android.util.Log;

import com.example.consumerestapis.platform.CountryInfoContract;
import com.example.consumerestapis.platform.constants.APIConstants;
import com.example.consumerestapis.platform.models.responseDTO.AboutCanadaResponseDTO;
import com.example.consumerestapis.platform.models.responseDTO.CountryRow;
import com.example.consumerestapis.retrofit.CountryDetailService;
import com.example.consumerestapis.retrofit.GetCanadaApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryDetail implements CountryInfoContract.model {

    @Override
    public void getCanadaDetails(final OnFinishedListener onFinishedListener) {
        GetCanadaApi getCanadaApi = CountryDetailService.getClient().create(GetCanadaApi.class);
        Call<AboutCanadaResponseDTO> aboutCanadaResponseDTOCall = getCanadaApi.getCanadaInfo(APIConstants.CONTENT_TYPE,APIConstants.ACCEPT);

        aboutCanadaResponseDTOCall.enqueue(new Callback<AboutCanadaResponseDTO>() {
            @Override
            public void onResponse(Call<AboutCanadaResponseDTO> call, Response<AboutCanadaResponseDTO> response) {
                try{
                    if (response.code() == 200 && null != response.body()){
                        List<CountryRow> canadaRows = response.body().getRows();
                        Log.e("Hi", "Number of detail received: " + canadaRows.size());
                        onFinishedListener.onFinished(canadaRows);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<AboutCanadaResponseDTO> call, Throwable t) {

            }
        });
    }
}
