package com.vivy.test.searchmydoctor;

import android.support.multidex.MultiDexApplication;

import com.vivy.test.searchmydoctor.Module.ApplicationModule;

public class SearchApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationModule.setsApplication(this);
    }
}
