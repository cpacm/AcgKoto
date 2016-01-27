package net.cpacm.acgkoto.ui.login;

import android.os.Bundle;
import android.app.Activity;

import net.cpacm.acgkoto.R;
import net.cpacm.acgkoto.ui.common.AbstractAppActivity;


public class SplashActivity extends AbstractAppActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);;
    }

}
