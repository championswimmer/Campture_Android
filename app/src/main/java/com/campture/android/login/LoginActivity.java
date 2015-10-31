package com.campture.android.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.campture.android.R;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Setup title and navbar colors
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        ParseUser user = ParseUser.getCurrentUser();
        if (!(user == null) && user.isNew()) {
            getSupportFragmentManager().beginTransaction().replace(R.id.login_container, LoaderFragment.newInstance()).commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.login_container, SigninFragment.newInstance()).commit();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);
    }

    public void handleFacebookLogin() {
        ParseFacebookUtils.logInWithReadPermissionsInBackground(this, LoginUtils.Facebook.LOGIN_PERMISSIONS, new LogInCallback() {
            @Override
            public void done(final ParseUser user, ParseException err) {
                if (user == null) {
                    Log.d(TAG, "Uh oh. The user cancelled the Facebook login.");
                    Toast.makeText(LoginActivity.this, "Oops ! Could not log you in!", Toast.LENGTH_SHORT).show();
                } else if (user.isNew()) {
                    Log.d(TAG, "User signed up and logged in through Facebook!");
                    GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject fbUser, GraphResponse response) {
                            user.put("name", fbUser.optString("name"));
                            user.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    // User signup done
                                    proceedToApp();
                                }
                            });
                        }
                    });
                } else {
                    Log.d(TAG, "User logged in through Facebook!");
                    proceedToApp();
                }
            }
        });
    }

    public void proceedToApp () {
        getSupportFragmentManager().beginTransaction().replace(R.id.login_container, LoaderFragment.newInstance()).commit();
    }


}
