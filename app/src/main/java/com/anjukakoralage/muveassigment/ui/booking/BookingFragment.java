package com.anjukakoralage.muveassigment.ui.booking;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anjukakoralage.muveassigment.R;

/**
 * Created by  on 07,December,2019
 */
public class BookingFragment extends Fragment {

    public BookingFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_booking, container, false);
    }
}
