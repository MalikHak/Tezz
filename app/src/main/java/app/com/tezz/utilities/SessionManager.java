package app.com.tezz.utilities;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static String NAME_PREFERENCE;
    int PRIVATE_MODE=0;

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;

    private static SessionManager instance;

    private static final String LANGUAGE = "App_Language";
    private static final String  IS_ONBOARDINGSHOWN="isONboardingshow";
    private  static final String HIFH_SCORE="highscore_user";
    private static  final String FIRST_VISIT="first_visit";



    public SessionManager(Context context) {
        this.context = context;
        pref=context.getSharedPreferences(NAME_PREFERENCE,PRIVATE_MODE);
        editor=pref.edit();

    }



    public static SessionManager getInstance(Context context) {
        if (instance == null) {
            instance = new SessionManager(context);
        }
        return instance;
    }

    public void  setHighScore(int higNumber){
        editor.putInt(HIFH_SCORE,higNumber);
        editor.commit();
    }

    public int getHighScore(){

        return pref.getInt(HIFH_SCORE,0);
    }


    public void setIsOnboardingshown(boolean isFirstTime){

        editor.putBoolean(IS_ONBOARDINGSHOWN,isFirstTime);
        editor.commit();

    }

    public  boolean getIsOnboardingshown() {

        return pref.getBoolean(IS_ONBOARDINGSHOWN,true
        );
    }


    public String getLanguage() {
        return pref.getString(LANGUAGE, "en");
    }

    public void setLanguage(String language) {
        editor.putString(LANGUAGE, language);
        editor.commit();
    }


    public void  setFirstVisit(boolean isFirstVisit){
        editor.putBoolean(FIRST_VISIT,isFirstVisit);
        editor.commit();
    }


    public boolean getFirstVisit(){

        return pref.getBoolean(FIRST_VISIT,false);
    }







}
