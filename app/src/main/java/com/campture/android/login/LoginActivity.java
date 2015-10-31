package com.campture.android.login;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.campture.android.R;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Setup title and navbar colors
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        FragmentManager fragMan = getSupportFragmentManager();
        FragmentTransaction fragTxn = fragMan.beginTransaction();

        ParseUser user = ParseUser.getCurrentUser();
        if (!(user == null) && user.isNew()) {
            fragTxn.replace(R.id.login_container, LoaderFragment.newInstance()).commit();
        } else {
            fragTxn.replace(R.id.login_container, SigninFragment.newInstance()).commit();
        }



    }

}
