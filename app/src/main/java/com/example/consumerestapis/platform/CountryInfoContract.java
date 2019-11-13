package com.example.consumerestapis.platform;

import com.example.consumerestapis.platform.models.responseDTO.CountryRow;

import java.util.List;

public interface CountryInfoContract {

    interface model {
        interface OnFinishedListener {
            void onFinished(List<CountryRow> countryRows);

            void onFailure(Throwable t);
        }

        void getCanadaDetails(OnFinishedListener onFinishedListener);
    }

    interface view {
        void init();

        void setListener();

        void setListViewData(List<CountryRow> countryRows);
    }

    interface Presenter {
        void onDestroy();

        void requestDataFromServer();
    }
}
