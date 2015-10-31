package com.campture.android.login;

import com.facebook.FacebookActivity;
import com.facebook.internal.PermissionType;
import com.parse.Parse;
import com.parse.ParseFacebookUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by championswimmer on 31/10/15.
 */
public  class LoginUtils {
    public static class Facebook {
        public static final String PUBLIC_PROFILE = "public_profile";
        public static final String USER_FRIENDS = "user_friends";
        public static final String EMAIL = "email";
        public static final String USER_ABOUT_ME = "user_about_me";
        public static final String USER_ACTOINS_BOOKS = "user_actoins.books";
        public static final String USER_ACTIONS_FITNESS = "user_actions.fitness";
        public static final String USER_ACTIONS_MUSIC = "user_actions.music";
        public static final String USER_ACTIONS_NEWS = "user_actions.news";
        public static final String USER_ACTIONS_VIDEO = "user_actions.video";
        public static final String USER_BIRTHDAY = "user_birthday";
        public static final String USER_EDUCATION_HISTORY = "user_education_history";
        public static final String USER_GAMES_ACTIVITY = "user_games_activity";
        public static final String USER_HOMETOWN = "user_hometown";
        public static final String USER_EVENTS = "user_events";
        public static final String USER_LIKES = "user_likes";
        public static final String USER_LOCATION = "user_location";
        public static final String USER_PHOTOS = "user_photos";
        public static final String USER_POSTS = "user_posts";
        public static final String USER_RELATIONSHIPS = "user_relationships";
        public static final String USER_WEBSITE = "user_website";
        public static final String USER_VIDEOS = "user_videos";
        public static final String USER_WORK_HISTORY = "user_work_history";
        public static final String MANAGE_PAGES = "manage_pages";
        public static final String PUBLISH_PAGES = "publish_pages";
        public static final String PUBLISH_ACTIONS = "publish_actions";
        public static final String RSVP_EVENT = "rsvp_event";

        public static final Collection<String> LOGIN_PERMISSIONS = Arrays.asList(
                PUBLIC_PROFILE,
                EMAIL,
                USER_FRIENDS
        );



    }
}
