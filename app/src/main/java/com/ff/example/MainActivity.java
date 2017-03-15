package com.ff.example;

import com.fast.framework.base.SplashActivity;
import com.fast.framework.FastManager;
import com.fast.framework.support.FastConf;
import com.fast.framework.support.L;
import com.fast.framework.util.PrefUtil;
import com.ff.example.fragment.FCONFIG;
import com.ff.example.fragment.HomeFragment;
import com.ff.example.fragment.HomeFragment1;
import com.ff.example.fragment.HomeFragment3;
import com.ff.example.fragment.WelcomeFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends SplashActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View.inflate(this, R.layout.activity_main, mRelativeLayout);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == FCONFIG.F_REQ_CODE.WELCOME) {
            if (resultCode == Activity.RESULT_OK) {
                PrefUtil.setPref(this, FastConf.FAST_GUIDE_STATUS, true);
                this.into();
            } else if (resultCode == Activity.RESULT_CANCELED) {
                // Not used
            }
            finish();
        }
    }

    @Override
    protected void into() {

        if (!PrefUtil.getBooleanPref(this, FastConf.FAST_GUIDE_STATUS)) {
            Intent intent = FastManager.getIntent(this, WelcomeFragment.class);
            startActivityForResult(intent, FCONFIG.F_REQ_CODE.WELCOME);
        } else {
            Intent intent = FastManager.getIntent(this, HomeFragment.class);
            startActivity(intent);
            finish();
        }
        L.v("fast app v test");
        L.d("fast app d test");
        L.i("fast app i test");
        L.w("fast app w test");
        L.e("fast app e test");
    }

}
