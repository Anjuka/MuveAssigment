package com.anjukakoralage.muveassigment.ui.main;

import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.anjukakoralage.muveassigment.R;
import com.anjukakoralage.muveassigment.ui.splash.SplashFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.MapView;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static FragmentManager fragmentManager;
    private static final int ERROR_DIALOG_REQUEST = 9001;

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

    public boolean isServiceOK(){

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getApplicationContext());

        if (available == ConnectionResult.SUCCESS){
            return true;
        }
        else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(this,available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }
        else {
            //cannot make map request
        }
        return false;
    }

    private void init(){

    }
}
