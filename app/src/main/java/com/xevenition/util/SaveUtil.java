package io.automile.automilepro.dagger.injectables;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.automile.automilepro.interfaces.Preferenceable;

@Singleton
public class SaveUtil implements Preferenceable {
    private static final String APP_SHARED_PREFS = "io.automile.automilepro";
    public static final String ONBOARDING_MODEL_NOT_SELECTED = "ONBOARDING_MODEL_NOT_SELECTED";
    public static final String ONBOARDING_DESICION_NOT_DONE = "ONBOARDING_DESICION_NOT_DONE";
    public static final String TIP_TRIPS_SHOWN = "TIP_TRIPS_SHOWN";
    public static final String TIP_TASK_SHOWN = "TIP_TASK_SHOWN";
    public static final String TIP_VEHICLES_SHOWN = "TIP_VEHICLES_SHOWN";
    public static final String TIP_CHECKIN_SHOWN = "TIP_CHECKIN_SHOWN";
    public static final String KEY_UPSALE_SHOWN = "KEY_UPSALE_SHOWN";
    public static final String KEY_TRANSFER_INTERVAL = "KEY_TRANSFER_INTERVAL";
    public static final String KEY_SAMPLE_HARSH_EVENTS = "KEY_SAMPLE_HARSH_EVENTS";

    public static final String KEY_HOS_LAST_SYNC_DATE = "KEY_HOS_LAST_SYNC_DATE";
    public static final String KEY_HOS_UNSAVED_CHANGES = "KEY_HOS_UNSAVED_CHANGES";

    public static final String KEY_FILTER_EXPENSE = "KEY_FILTER_EXPENSE";
    public static final String KEY_FILTER_EXPENSE_ORDER = "KEY_FILTER_EXPENSE_ORDER";
    public static final String KEY_FILTER_EXPENSE_VEHICLE = "KEY_FILTER_EXPENSE_VEHICLE";
    public static final String KEY_FILTER_INSPECTION_ORDER = "KEY_FILTER_INSPECTION_ORDER";
    public static final String KEY_FILTER_INSPECTION_VEHICLE = "KEY_FILTER_INSPECTION_VEHICLE";
    public static final String KEY_FILTER_TASK = "KEY_FILTER_TASK";

    //Trip list filter settings
    public static final String KEY_FILTER_TRIP_DAYS = "KEY_FILTER_TRIP_DAYS";
    public static final String KEY_FILTER_TRIP_ORDER = "KEY_FILTER_TRIP_ORDER";
    public static final String KEY_FILTER_TRIP_VEHICLE = "KEY_FILTER_TRIP_VEHICLE";
    public static final String KEY_FILTER_TRIP_DRIVER = "KEY_FILTER_TRIP_DRIVER";
    public static final String KEY_FILTER_TRIP_DETAILS = "KEY_FILTER_TRIP_DETAILS";
    public static final String KEY_FILTER_TRIP_NOTES = "KEY_FILTER_TRIP_NOTES";

    public static final String KEY_FILTER_ORDER_GEO = "KEY_FILTER_ORDER_GEO";
    public static final String KEY_FILTER_ORDER_PLACES = "KEY_FILTER_ORDER_PLACES";

    public static final String KEY_VEHICLE_SORT_BY = "KEY_VEHICLE_SORT_BY";
    public static final String KEY_VEHICLE_ORDER_BY = "KEY_VEHICLE_ORDER_BY";


    public static final String USER_CONTACT_ID = "USER_CONTACT_ID";
    public static final String KEY_COLOR_1 = "KEY_COLOR_1";
    public static final String KEY_COLOR_2 = "KEY_COLOR_2";
    public static final String KEY_COLOR_3 = "KEY_COLOR_3";
    public static final String KEY_COLOR_4 = "KEY_COLOR_4";
    public static final String KEY_COLOR_5 = "KEY_COLOR_5";
    public static final String KEY_COLOR_6 = "KEY_COLOR_6";
    public static final String KEY_COLOR_7 = "KEY_COLOR_7";
    public static final String KEY_COLOR_8 = "KEY_COLOR_8";
    public static final String KEY_COLOR_9 = "KEY_COLOR_9";
    public static final String KEY_COLOR_10 = "KEY_COLOR_10";

    public static final String KEY_DEVICE_IDENTIFIER = "KEY_DEVICE_IDENTIFIER";
    public static final String KEY_MOVE_PUSH_SHOWN = "KEY_MOVE_PUSH_SHOWN";
    public static final String KEY_CUSTOM_CATEGORIES_SHOWN = "KEY_CUSTOM_CATEGORIES_SHOWN";

    //Live map filyter settings
    public static final String KEY_FILTER_LIVE_MOVING = "KEY_FILTER_LIVE_MOVING";
    public static final String KEY_FILTER_LIVE_PARKED = "KEY_FILTER_LIVE_PARKED";
    public static final String KEY_FILTER_LIVE_TRACKER = "KEY_FILTER_LIVE_TRACKER";
    public static final String KEY_FILTER_LIVE_VEHICLE = "KEY_FILTER_LIVE_VEHICLE";
    public static final String KEY_FILTER_LIVE_COLOR_1 = "KEY_FILTER_LIVE_COLOR_1";
    public static final String KEY_FILTER_LIVE_COLOR_2 = "KEY_FILTER_LIVE_COLOR_2";
    public static final String KEY_FILTER_LIVE_COLOR_3 = "KEY_FILTER_LIVE_COLOR_3";
    public static final String KEY_FILTER_LIVE_COLOR_4 = "KEY_FILTER_LIVE_COLOR_4";
    public static final String KEY_FILTER_LIVE_COLOR_5 = "KEY_FILTER_LIVE_COLOR_5";
    public static final String KEY_FILTER_LIVE_COLOR_6 = "KEY_FILTER_LIVE_COLOR_6";
    public static final String KEY_FILTER_LIVE_COLOR_7 = "KEY_FILTER_LIVE_COLOR_7";
    public static final String KEY_FILTER_LIVE_COLOR_8 = "KEY_FILTER_LIVE_COLOR_8";
    public static final String KEY_FILTER_LIVE_COLOR_9 = "KEY_FILTER_LIVE_COLOR_9";
    public static final String KEY_FILTER_LIVE_COLOR_10 = "KEY_FILTER_LIVE_COLOR_10";
    public static final String KEY_FILTER_JOB_DAYS = "KEY_FILTER_JOB_DAYS";
    public static final String KEY_FILTER_JOB_STATUS = "KEY_FILTER_JOB_STATUS";
    public static final String KEY_FILTER_JOBS_DRIVER = "KEY_FILTER_JOBS_DRIVER";
    public static final java.lang.String KEY_FILTER_JOB_PREVIOUS = "KEY_FILTER_JOB_PREVIOUS";
    public static final java.lang.String KEY_FILTER_JOB_UNASSIGNED = "KEY_FILTER_JOB_UNASSIGNED";
    public static final String KEY_FILTER_QUOTE_DAYS = "KEY_FILTER_QUOTE_DAYS";
    public static final String KEY_FILTER_QUOTE_STATUS = "KEY_FILTER_QUOTE_STATUS";
    public static final String KEY_FILTER_JOBREPORT_DAYS = "KEY_FILTER_JOBREPORT_DAYS";
    public static final String KEY_FILTER_JOBREPORT_DRIVER = "KEY_FILTER_JOBREPORT_DRIVER";
    public static final String KEY_FILTER_ANYTRACK_NOTIFICATIONS_DAYS = "KEY_FILTER_ANYTRACK_NOTIFICATIONS_DAYS";
    public static final String KEY_FILTER_ANYTRACK_NOTIFICATIONS_TYPE = "KEY_FILTER_ANYTRACK_NOTIFICATIONS_TYPE";

    private static SharedPreferences appSharedPrefs;

    private Editor prefsEditor;
    public static final String KEY_CHECKED_IN_VEHICLE = "KEY_CHECKED_IN_VEHICLE";
    public static final String KEY_ACCESS_TOKEN = "KEY_ACCESS_TOKEN";
    public static final String KEY_CLIENT_TOKEN = "KEY_CLIENT_TOKEN";
    public static final String KEY_REFRESH_TOKEN = "KEY_REFRESH_TOKEN";
    public static final String KEY_LOGIN = "KEY_LOGIN";
    public static final String KEY_USER_ID = "KEY_USER_ID";
    public static final String KEY_USER_CONTACT_ID = "KEY_USER_CONTACT_ID";
    public static final String KEY_AUTHENTICATION_EXPIRATION = "KEY_AUTHENTICATION_EXPIRATION";
    public final static String KEY_NUMBER_OF_STARTUPS = "KEY_NUMBER_OF_STARTUPS";

    @Inject
    public SaveUtil(Application app){
            appSharedPrefs = app.getSharedPreferences(APP_SHARED_PREFS, Activity.MODE_PRIVATE);
    }

    public void deleteAllBasedata(){
        prefsEditor = appSharedPrefs.edit();
        prefsEditor.remove(KEY_FILTER_ORDER_GEO);
        prefsEditor.remove(KEY_FILTER_ORDER_PLACES);
        prefsEditor.remove(KEY_FILTER_TRIP_DAYS);
        prefsEditor.remove(KEY_FILTER_TRIP_NOTES);
        prefsEditor.remove(KEY_FILTER_TRIP_VEHICLE);
        prefsEditor.remove(KEY_FILTER_TRIP_ORDER);
        prefsEditor.remove(KEY_FILTER_TASK);
        prefsEditor.remove(KEY_CHECKED_IN_VEHICLE);
        prefsEditor.remove(KEY_FILTER_EXPENSE);
        prefsEditor.remove(KEY_FILTER_EXPENSE_ORDER);
        prefsEditor.remove(KEY_FILTER_EXPENSE_VEHICLE);
        prefsEditor.remove(KEY_FILTER_INSPECTION_ORDER);
        prefsEditor.remove(KEY_FILTER_INSPECTION_VEHICLE);
        prefsEditor.remove(KEY_ACCESS_TOKEN);
        prefsEditor.remove(KEY_REFRESH_TOKEN);
        prefsEditor.remove(KEY_CLIENT_TOKEN);
        prefsEditor.remove(KEY_LOGIN);
        prefsEditor.remove(KEY_USER_ID);
        prefsEditor.remove(KEY_AUTHENTICATION_EXPIRATION);
        prefsEditor.remove(ONBOARDING_MODEL_NOT_SELECTED);
        prefsEditor.remove(KEY_VEHICLE_ORDER_BY);
        prefsEditor.remove(KEY_VEHICLE_SORT_BY);
        prefsEditor.remove(KEY_MOVE_PUSH_SHOWN);
        prefsEditor.remove(KEY_FILTER_JOB_DAYS);
        prefsEditor.remove(KEY_FILTER_JOB_STATUS);
        prefsEditor.remove(KEY_FILTER_JOBS_DRIVER);
        prefsEditor.remove(KEY_FILTER_JOB_PREVIOUS);
        prefsEditor.remove(KEY_FILTER_JOB_UNASSIGNED);
        prefsEditor.remove(KEY_FILTER_QUOTE_STATUS);
        prefsEditor.remove(KEY_FILTER_QUOTE_DAYS);
        prefsEditor.remove(KEY_FILTER_JOBREPORT_DAYS);
        prefsEditor.remove(KEY_FILTER_JOBREPORT_DRIVER);
        prefsEditor.remove(KEY_FILTER_ANYTRACK_NOTIFICATIONS_DAYS);
        prefsEditor.remove(KEY_FILTER_ANYTRACK_NOTIFICATIONS_TYPE);
        prefsEditor.commit();
    }

    public void clearAllFiltersAtAppStart() {
        prefsEditor = appSharedPrefs.edit();
        prefsEditor.remove(KEY_FILTER_EXPENSE);
        prefsEditor.remove(KEY_FILTER_EXPENSE_ORDER);
        prefsEditor.remove(KEY_FILTER_EXPENSE_VEHICLE);
        prefsEditor.remove(KEY_FILTER_INSPECTION_ORDER);
        prefsEditor.remove(KEY_FILTER_INSPECTION_VEHICLE);
        prefsEditor.remove(KEY_FILTER_TRIP_DAYS);
        prefsEditor.remove(KEY_FILTER_TRIP_NOTES);
        prefsEditor.remove(KEY_FILTER_TRIP_ORDER);
        prefsEditor.remove(KEY_FILTER_ORDER_GEO);
        prefsEditor.remove(KEY_FILTER_ORDER_PLACES);
        prefsEditor.remove(KEY_FILTER_TRIP_VEHICLE);
        prefsEditor.remove(KEY_FILTER_TASK);
        prefsEditor.remove(KEY_VEHICLE_SORT_BY);
        prefsEditor.remove(KEY_VEHICLE_ORDER_BY);
        prefsEditor.remove(KEY_COLOR_1);
        prefsEditor.remove(KEY_COLOR_2);
        prefsEditor.remove(KEY_COLOR_3);
        prefsEditor.remove(KEY_COLOR_4);
        prefsEditor.remove(KEY_COLOR_5);
        prefsEditor.remove(KEY_COLOR_6);
        prefsEditor.remove(KEY_COLOR_7);
        prefsEditor.remove(KEY_COLOR_8);
        prefsEditor.remove(KEY_COLOR_9);
        prefsEditor.remove(KEY_COLOR_10);
        prefsEditor.remove(KEY_FILTER_JOB_DAYS);
        prefsEditor.remove(KEY_FILTER_JOB_STATUS);
        prefsEditor.remove(KEY_FILTER_JOBS_DRIVER);
        prefsEditor.remove(KEY_FILTER_JOB_PREVIOUS);
        prefsEditor.remove(KEY_FILTER_JOB_UNASSIGNED);
        prefsEditor.remove(KEY_FILTER_QUOTE_STATUS);
        prefsEditor.remove(KEY_FILTER_QUOTE_DAYS);
        prefsEditor.remove(KEY_FILTER_JOBREPORT_DAYS);
        prefsEditor.remove(KEY_FILTER_JOBREPORT_DRIVER);
        prefsEditor.remove(KEY_FILTER_ANYTRACK_NOTIFICATIONS_DAYS);
        prefsEditor.remove(KEY_FILTER_ANYTRACK_NOTIFICATIONS_TYPE);
        prefsEditor.apply();
    }

    public void deleteDataForNewSignUp(){
        prefsEditor = appSharedPrefs.edit();
        prefsEditor.remove(TIP_TRIPS_SHOWN);
        prefsEditor.remove(TIP_TASK_SHOWN);
        prefsEditor.remove(TIP_VEHICLES_SHOWN);
        prefsEditor.remove(TIP_CHECKIN_SHOWN);
        prefsEditor.remove(KEY_UPSALE_SHOWN);
        prefsEditor.commit();
    }

    @Override
    public String getString(String key) {
        return appSharedPrefs.getString(key, "");
    }

    @Override
    public long getLong(String key) {
        return 0;
    }

    @Override
    public void saveString(String key, String content) {
        prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString(key, content);
        prefsEditor.commit();
    }

    @Override
    public boolean getBoolean(String key) {
        return appSharedPrefs.getBoolean(key, false);
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        return appSharedPrefs.getBoolean(key, defaultValue);
    }

    @Override
    public void saveBoolean(String key, boolean content) {
        prefsEditor = appSharedPrefs.edit();
        prefsEditor.putBoolean(key, content);
        prefsEditor.commit();
    }

    @Override
    public int getInt(String key, int defaultValue) {
        return appSharedPrefs.getInt(key, defaultValue);
    }

    @Override
    public double getDouble(String key) {
        return 0;
    }

    @Override
    public void saveInt(String key, int content) {
        prefsEditor = appSharedPrefs.edit();
        prefsEditor.putInt(key, content);
        prefsEditor.commit();
    }

    @Override
    public void saveDouble(String key, double value) {

    }

    @Override
    public void saveLong(String key, long content){
        prefsEditor = appSharedPrefs.edit();
        prefsEditor.putLong(key, content);
        prefsEditor.commit();
    }

    @Override
    public long getLong(String key, long defaultValue) {
        return appSharedPrefs.getLong(key, defaultValue);
    }

    @Override
    public void saveList(String key, ArrayList<String> list) {
        Set<String> set = new HashSet<String>();
        set.addAll(list);
        prefsEditor = appSharedPrefs.edit();
        prefsEditor.putStringSet(key, set);
        prefsEditor.commit();
    }

    @Override
    public ArrayList<String> getList(String key) {
        Set<String> set = appSharedPrefs.getStringSet(key, null);
        if (set == null)
            return null;
        return new ArrayList<String>(set);
    }
}
