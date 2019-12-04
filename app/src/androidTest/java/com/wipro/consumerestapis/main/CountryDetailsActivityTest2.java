package com.wipro.consumerestapis.main;


import android.app.Activity;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.wipro.consumerestapis.R;
import com.wipro.consumerestapis.adapter.CountryDetailsListAdapter;
import com.wipro.consumerestapis.platform.CountryDetailAppPresenter;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.wipro.consumerestapis.main.RecycleViewItemCountAssertion.withItemCount;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CountryDetailsActivityTest2 {

    int totalCount = 0;
    CountryDetailsListAdapter countryDetailsListAdapter;
    CountryDetailAppPresenter countryDetailAppPresenter;

    @Rule
    public ActivityTestRule<CountryDetailsActivity> mActivityTestRule = new ActivityTestRule<>(CountryDetailsActivity.class, true);

    @Before
    public void initre() {
        CountryDetailsActivity countryDetailsActivity = new CountryDetailsActivity(new Activity());

        countryDetailsActivity.init();

        countryDetailAppPresenter = new CountryDetailAppPresenter(mActivityTestRule.getActivity());
        countryDetailAppPresenter.requestDataFromServer();
    }

    public int myTest() {
        RecyclerView recyclerView = mActivityTestRule.getActivity().findViewById(R.id.main_recyclerview);
        totalCount = recyclerView.getAdapter().getItemCount();
        return totalCount;
    }



    @Test
    public void chkDataRecycleView() {
        onView(withId(R.id.main_recyclerview)).check(withItemCount(myTest()));
    }
}
