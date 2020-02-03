package app.com.tezz.application;

import android.content.res.Configuration;
import android.util.DisplayMetrics;

import java.util.Locale;

import app.com.tezz.receivers.ConnectivityReceiver;
import app.com.tezz.utilities.SessionManager;

public class Application extends android.app.Application {


    private static Application myAppInstance;
    SessionManager sessionManager;


    public static String LOCAL_EN = "en";
    public static String LOCAL_FA = "fa";
    public static String LOCAL_PS = "ps";
    @Override
    public void onCreate() {
        super.onCreate();
        myAppInstance=this;
        sessionManager=new SessionManager(getApplicationContext());
    }

    public static Application getInstance(){

        return myAppInstance;
    }


    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListerener listener){
      ConnectivityReceiver.ConnectivityReceiverListerener listerener =listener;
    }

    public void setLanguage(String language) {

        if (language.equalsIgnoreCase(LOCAL_FA)) {
            Locale.setDefault(new Locale("fa", "af"));
        } else if (language.equalsIgnoreCase(LOCAL_EN)) {
            Locale.setDefault(Locale.ENGLISH);
        } else if (language.equals(LOCAL_PS)) {
            Locale.setDefault(new Locale("ps", "af"));
        }

        Configuration config = getResources().getConfiguration();
//
//        if (Utils.checkForApi17()) {
//            config.setLayoutDirection(Locale.getDefault());
//        }


        config.locale = Locale.getDefault();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        getResources().updateConfiguration(config, metrics);
        sessionManager.setLanguage(language);
    }

}
