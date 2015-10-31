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
import com.parse.SignUpCallback;

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
        if ((!(user == null)) && !user.isNew()) {
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
                    GraphRequest fbRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject fbUser, GraphResponse response) {
                            ParseUser parseUser = ParseUser.getCurrentUser();
                            parseUser.put("name", fbUser.optString("name"));
                            parseUser.put("email", fbUser.optString("email"));
                            parseUser.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    // User signup done
                                    proceedToApp();
                                }
                            });
                        }
                    });
                    Bundle parameters = new Bundle();
                    parameters.putString("fields", "id,name,email");
                    fbRequest.setParameters(parameters);
                    fbRequest.executeAsync();
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

    public void proceedToSignup () {
        getSupportFragmentManager().beginTransaction().replace(R.id.login_container, SignupFragment.newInstance()).commit();
    }

    public void handleEmailSignup(String email, String password, String name) {
        ParseUser parseUser = new ParseUser();
        parseUser.put("username", email);
        parseUser.put("email", email);
        parseUser.put("password", password);
        parseUser.put("name", name);

        parseUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    proceedToApp();
                } else {
                    Toast.makeText(LoginActivity.this, "There was an error signing up", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        });
    }

    public void handleEmailSignin(String email, String password) {
        ParseUser.logInInBackground(email, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
                    Toast.makeText(LoginActivity.this, "Error in logging in", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                } else if (user.isAuthenticated()) {
                    proceedToApp();
                }
            }
        });
    }


}
