package com.example.consumerestapis.platform;

import com.example.consumerestapis.platform.models.CanadaDetailModel;
import com.example.consumerestapis.platform.models.responseDTO.CanadaRows;

import java.util.List;

public class CanadaDetailPresenter implements CanadaInfoContract.Presenter,CanadaInfoContract.model.OnFinishedListener {

    private CanadaInfoContract.view canadaInfoView;

    private CanadaInfoContract.model canadaInfoModel;

    public CanadaDetailPresenter(CanadaInfoContract.view canadaInfoView) {
        this.canadaInfoView = canadaInfoView;
        canadaInfoModel = new CanadaDetailModel();
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
    public void onFinished(List<CanadaRows> canadaRows) {
        canadaInfoView.setListViewData(canadaRows);
    }

    @Override
    public void onFailure(Throwable t) {

    }
}
