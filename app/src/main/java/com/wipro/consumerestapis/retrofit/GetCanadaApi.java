package com.wipro.consumerestapis.retrofit;

import com.wipro.consumerestapis.platform.constants.APIConstants;
import com.wipro.consumerestapis.platform.models.responseDTO.AboutCanadaResponseDTO;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface GetCanadaApi {
    @POST("facts.json")
    Call<AboutCanadaResponseDTO> getCanadaInfo(@Header(APIConstants.CONTENT_TYPE) String contentType, @Header(APIConstants.ACCEPT) String accept);
}
