package com.example.consumerestapis.platform.models;

import android.util.Log;

import com.example.consumerestapis.platform.CanadaInfoContract;
import com.example.consumerestapis.platform.constants.APIConstants;
import com.example.consumerestapis.platform.models.responseDTO.AboutCanadaResponseDTO;
import com.example.consumerestapis.platform.models.responseDTO.CanadaRows;
import com.example.consumerestapis.retrofit.CanadaService;
import com.example.consumerestapis.retrofit.GetCanadaApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CanadaDetailModel implements CanadaInfoContract.model {
    private GetCanadaApi getCanadaApi;

    @Override
    public void getCanadaDetails(final OnFinishedListener onFinishedListener) {
        getCanadaApi = CanadaService.getClient().create(GetCanadaApi.class);
        Call<AboutCanadaResponseDTO> aboutCanadaResponseDTOCall = getCanadaApi.getCanadaInfo(APIConstants.CONTENT_TYPE,APIConstants.ACCEPT);

        aboutCanadaResponseDTOCall.enqueue(new Callback<AboutCanadaResponseDTO>() {
            @Override
            public void onResponse(Call<AboutCanadaResponseDTO> call, Response<AboutCanadaResponseDTO> response) {
                try{
                    if (response.code() == 200){
                        List<CanadaRows> canadaRows = response.body().getRows();
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
