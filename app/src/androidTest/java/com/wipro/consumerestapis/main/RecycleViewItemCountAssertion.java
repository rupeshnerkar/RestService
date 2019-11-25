package com.wipro.consumerestapis.main;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.hamcrest.Matcher;

import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static org.hamcrest.Matchers.is;

public class RecycleViewItemCountAssertion implements ViewAssertion {
    private final Matcher<Integer> matcher;

    public static RecycleViewItemCountAssertion withItemCount(int expectedCount) {
        return withItemCount(is(expectedCount));
    }

    public static RecycleViewItemCountAssertion withItemCount(Matcher<Integer> matcher) {
        return new RecycleViewItemCountAssertion(matcher);
    }

    private RecycleViewItemCountAssertion(Matcher<Integer> matcher) {
        this.matcher = matcher;
    }

    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {
        if (noViewFoundException != null) {
            throw noViewFoundException;
        }

        RecyclerView recyclerView = (RecyclerView) view;
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        assertThat(adapter.getItemCount(), matcher);
    }
}
