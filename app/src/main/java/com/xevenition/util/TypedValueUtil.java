package com.xevenition.util;

import android.app.Application;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Bolling1 on 08/01/15.
 */
@Singleton
public class TypedValueUtil {

    private Application app;

    @Inject
    public TypedValueUtil(Application app){
        this.app = app;
    }

    public int dipToPixels(float dipValue) {
        DisplayMetrics metrics = app.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }
}
