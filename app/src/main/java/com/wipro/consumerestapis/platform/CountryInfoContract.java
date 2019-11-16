package com.wipro.consumerestapis.platform;

import com.wipro.consumerestapis.platform.models.responseDTO.CountryRow;

import java.util.List;

public interface CountryInfoContract {

    interface model {
        interface OnFinishedListener {
            void onFinished(List<CountryRow> countryRows,String title);

            void onFailure(Throwable t);
        }

        void getCanadaDetails(OnFinishedListener onFinishedListener);
    }

    interface view {
        void init();

        void setListener();

        void setListViewData(List<CountryRow> countryRows,String title);
    }

    interface Presenter {
        void onDestroy();

        void requestDataFromServer();
    }
}
