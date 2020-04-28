package loanapp.udit.com.loanapplication;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CommonApplicationUtil extends Application {
    public static final String ENVIRONMENT_KEY= "ENVIRONMENT";
    public static final String BASE_URL_KEY ="BASE_URL";
    public static final String SERVICE_USER_KEY ="SERVICE_ACCESS_USER_NAME";
    public static final String SERVICE_USER_PASSWORD ="SERVICE_ACCESS_PASSWORD";
    public static final String propertyFileExtn = ".properties";
    public static Context applicationContext;
    public static Properties applicationProperty;

    public static String Environment="";
    public static String BaseUrl ="";
    public static String ServiceUser ="";
    public static String ServicePassword ="";

    public static String ErrorMessage="";

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();

        AssetManager assetManager =applicationContext.getAssets();
        Environment = getEnvironment(assetManager);
        BaseUrl= getDataFromEnvFile(assetManager, BASE_URL_KEY);
        ServiceUser= getDataFromEnvFile(assetManager,SERVICE_USER_KEY);
        ServicePassword = getDataFromEnvFile(assetManager,SERVICE_USER_PASSWORD);
    }

    private String getDataFromEnvFile(AssetManager assetManager, String key) {
        applicationProperty = new Properties();
        String valueFromProperty = "";
        if(Environment!=null && !Environment.equals("")){
                    try {
                        InputStream applicationPropertyIS = assetManager.open(ENVIRONMENT_KEY+"_"+Environment+propertyFileExtn);
                        applicationProperty.load(applicationPropertyIS);
                        valueFromProperty = applicationProperty.getProperty(key);
                        applicationPropertyIS.close();
                        return valueFromProperty;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        return null;
    }

    private String getEnvironment(AssetManager assetManager) {
        applicationProperty = new Properties();
        String EnviromentFromProperty = "";
        try {
            InputStream applicationPropertyIS = assetManager.open("Application.properties");
            applicationProperty.load(applicationPropertyIS);
            EnviromentFromProperty = applicationProperty.getProperty(ENVIRONMENT_KEY);
            applicationPropertyIS.close();
        } catch (IOException e) {
            EnviromentFromProperty="NA";
            e.printStackTrace();
        }
        return EnviromentFromProperty;
    }
}
