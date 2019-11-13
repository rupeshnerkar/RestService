package com.example.consumerestapis.platform;

import com.example.consumerestapis.platform.models.CountryDetail;
import com.example.consumerestapis.platform.models.responseDTO.CountryRow;

import java.util.List;

public class CountryDetailAppPresenter implements CountryInfoContract.Presenter, CountryInfoContract.model.OnFinishedListener {

    private CountryInfoContract.view canadaInfoView;

    private CountryInfoContract.model canadaInfoModel;

    public CountryDetailAppPresenter(CountryInfoContract.view canadaInfoView) {
        this.canadaInfoView = canadaInfoView;
        canadaInfoModel = new CountryDetail();
    }

    @Override
    public void onDestroy() {
        this.canadaInfoView = null;
    }

    @Override
    public void requestDataFromServer() {
        canadaInfoModel.getCanadaDetails(this);
    }

    @Override
    public void onFinished(List<CountryRow> countryRows) {
        canadaInfoView.setListViewData(countryRows);
    }

    @Override
    public void onFailure(Throwable t) {

    }
}
