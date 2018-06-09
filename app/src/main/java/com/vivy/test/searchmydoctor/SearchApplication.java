package com.vivy.test.searchmydoctor;

import android.app.Application;
import android.content.res.Resources;

import com.vivy.test.searchmydoctor.Module.ApplicationModule;

public class SearchApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationModule.setsApplication(this);
    }
}
