package com.example.consumerestapis.retrofit;

import com.example.consumerestapis.platform.models.responseDTO.AboutCanadaResponseDTO;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface GetCanadaApi {
    @POST("facts.json")
    Call<AboutCanadaResponseDTO> getCanadaInfo(@Header("Content-Type") String contentType, @Header("Accept") String accept);
}
