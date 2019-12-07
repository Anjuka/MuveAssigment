package com.anjukakoralage.muveassigment.ui.splash;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anjukakoralage.muveassigment.R;
import com.anjukakoralage.muveassigment.ui.booking.BookingFragment;
import com.anjukakoralage.muveassigment.ui.main.MainActivity;

/**
 * Created by  on 07,December,2019
 */
public class SplashFragment extends Fragment {

    private final int SPLASH_DISPLAY_LENGTH = 4000;

    public SplashFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        screenChange();

        return inflater.inflate(R.layout.fragment_splash, container, false);



    }


    public void screenChange(){
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new BookingFragment(), null).commit();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
