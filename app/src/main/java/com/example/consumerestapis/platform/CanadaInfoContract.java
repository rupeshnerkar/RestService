package com.example.consumerestapis.platform;

import com.example.consumerestapis.platform.models.responseDTO.CanadaRows;

import java.util.List;

public interface CanadaInfoContract {

    interface model {
        interface OnFinishedListener {
            void onFinished(List<CanadaRows> canadaRows);

            void onFailure(Throwable t);
        }
        void getCanadaDetails(OnFinishedListener onFinishedListener);
}

    interface view {
        void init();

        void setListener();

        void setListViewData(List<CanadaRows> canadaRows);
    }

    interface Presenter {
        void onDestroy();

        void requestDataFromServer();
    }
}
