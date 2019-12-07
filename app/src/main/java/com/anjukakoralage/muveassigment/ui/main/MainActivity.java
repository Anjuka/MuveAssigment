package com.anjukakoralage.muveassigment.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.anjukakoralage.muveassigment.R;
import com.anjukakoralage.muveassigment.ui.splash.SplashFragment;
import com.google.android.gms.maps.MapView;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        if (findViewById(R.id.fragment_container) != null){

            //to avoid over lapping fragments
            if (savedInstanceState != null){
                return;
            }

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            SplashFragment splashFragment = new SplashFragment();

            fragmentTransaction.add(R.id.fragment_container, splashFragment, null);
            fragmentTransaction.commit();
        }

    }
}
