package app.com.tezz.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import app.com.tezz.R;
import app.com.tezz.application.Application;
import app.com.tezz.utilities.LocaleManager;
import app.com.tezz.utilities.SessionManager;
import de.hdodenhof.circleimageview.CircleImageView;

import static app.com.tezz.application.Application.LOCAL_EN;
import static app.com.tezz.application.Application.LOCAL_FA;

public class EditProfileUserActivity extends AppCompatActivity {

    public static String[] languageStrings = {"English", "دری", "پشتو"};

    private static final String TAG=EditProfileUserActivity.class.getSimpleName();

    Application myApp;

    AlertDialog levelDialog;

    EditText etname, etemail, etage;
    Button btUpdate;
    CircleImageView civPicture;
    SessionManager sesion;

    Button btnChangeLanguage;
    Intent intent ;
    public  static final int RequestPermissionCodeCamera  = 1 ;
    public  static final int RequestPermissionCodeGallery  = 1 ;


    @Override
    protected void attachBaseContext(Context newBase) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            super.attachBaseContext(LocaleManager.setLocale(newBase));
        } else {
            super.attachBaseContext(newBase);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleManager.setLocale(this);
        //  Log.d(TAG, getString(R.string.config_changed) + newConfig.locale.getLanguage());
        sesion.setLanguage(newConfig.locale.getLanguage());
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_user);


        btnChangeLanguage=findViewById(R.id.buttonLang);

        myApp=Application.getInstance();
        sesion = SessionManager.getInstance(this);
        etname = findViewById(R.id.etName);
        etemail =findViewById(R.id.etEmail);
        etage = findViewById(R.id.etAge);
        btUpdate= findViewById(R.id.btnUpdate);
        civPicture = findViewById(R.id.profile_image);

        EnableRuntimePermission();


        btnChangeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLanguageDialog(getLanguageIndex());
            }
        });
        
        civPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent, 7);

            }
        });










    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 7 && resultCode == RESULT_OK) {

            Bitmap bitmap = (Bitmap) data.getExtras().get("data");

            civPicture.setImageBitmap(bitmap);
        }
    }

    public void EnableRuntimePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(EditProfileUserActivity.this,
                Manifest.permission.CAMERA))
        {
            Toast.makeText(EditProfileUserActivity.this,"CAMERA permission allows us to Access CAMERA app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(EditProfileUserActivity.this,new String[]{
                    Manifest.permission.CAMERA}, RequestPermissionCodeCamera);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestPermissionCode, String per[], int[] PResult) {

        switch (requestPermissionCode) {

            case RequestPermissionCodeCamera:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(EditProfileUserActivity.this,"Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(EditProfileUserActivity.this,"Permission Canceled, Now your application cannot access CAMERA.", Toast.LENGTH_LONG).show();

                }
                break;
        }



}


    public void showLanguageDialog(int selectedIndex) {

        // Creating and Building the Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Language");
        builder.setSingleChoiceItems(languageStrings, selectedIndex, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {

                if (getLanguageIndex() != item) {
                    switch (item) {
                        case 0:
                            myApp.setLanguage("en");
                            setLanguage("en");
                            break;
                        case 1:
                            myApp.setLanguage("fa");
                            setLanguage("fa");
                            break;
                        case 2:
                            myApp.setLanguage("ps");
                            setLanguage("ps");
                            break;
                        case 4:
                            myApp.setLanguage("es");
                            setLanguage("es");
                            break;
                        case 5:
                            myApp.setLanguage("hi");
                            setLanguage("in");
                            break;
                    }
                }
                levelDialog.dismiss();
            }
        });
        levelDialog = builder.create();
        levelDialog.show();
    }

    private void setLanguage(String lang) {
        LocaleManager.setNewLocale(this, lang);
        Intent intent = new Intent(this, EditProfileUserActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }



    private int getLanguageIndex() {
        String lang = sesion.getLanguage();
        Log.d(TAG, lang);
        if (lang.equalsIgnoreCase(LOCAL_EN)) {
            return 0;
        } else if (lang.equalsIgnoreCase(LOCAL_FA)) {
            return 1;
        } else {
            return 2;
        }
    }

}
