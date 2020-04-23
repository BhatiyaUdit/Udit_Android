package loanapp.udit.com.loanapplication;

import android.app.Application;
import android.content.Context;

public class CommonApplicationUtil extends Application {
    public static Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();
    }
}
