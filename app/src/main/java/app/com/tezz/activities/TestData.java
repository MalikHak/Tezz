package app.com.tezz.activities;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class TestData {
//
//    package app.bestquotes.af;
//
//import android.Manifest;
//import android.app.DatePickerDialog;
//import android.app.ProgressDialog;
//import android.app.TimePickerDialog;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.content.pm.PackageManager;
//import android.content.res.Configuration;
//import android.graphics.Bitmap;
//import android.graphics.Color;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.support.annotation.NonNull;
//import android.support.v4.app.ActivityCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.PopupMenu;
//import android.text.Editable;
//import android.text.TextUtils;
//import android.text.TextWatcher;
//import android.util.Log;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.Button;
//import android.widget.DatePicker;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.TimePicker;
//import android.widget.Toast;
//
//import com.allyants.notifyme.NotifyMe;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.firestore.CollectionReference;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.EventListener;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.FirebaseFirestoreException;
//import com.google.firebase.firestore.FirebaseFirestoreSettings;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;
//import com.mikhaellopez.circularimageview.CircularImageView;
//import com.squareup.picasso.Callback;
//import com.squareup.picasso.Picasso;
//
//import java.io.ByteArrayOutputStream;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.annotation.Nullable;
//
//import app.goals.af.goals.R;
//import app.goals.af.goals.models.User;
//import app.goals.af.goals.tools.LocaleManager;
//import app.goals.af.goals.tools.PrefManager;
//
//import static app.goals.af.goals.tools.Constants.GET_USER_DATA;
//import static com.facebook.FacebookSdk.getApplicationContext;
//
//    public class EditUserInfo extends AppCompatActivity implements View.OnClickListener {
//
//        private static final String TAG = "EditUserInfo";
//        private static final int REQUEST_EXTERNAL_STORAGE = 1012;
//        private static final int REQUEST_CAMERA = 1013;
//        private static final int CODE_CHANGE_MY_PROFILE_PIC_GALLERY = 1002;
//        private static final int CODE_CHANGE_MY_PROFILE_PIC_CAMERA = 1003;
//
//        String permissionStorage = Manifest.permission.WRITE_EXTERNAL_STORAGE;
//        String permissionCamera = Manifest.permission.CAMERA;
//        CircularImageView ivProfileImage, ivLicecnecePhoto;
//        Uri imageUri = null;
//        int SELECT_IMAGE_TYPE = 0;
//        int PROFILE_IMAGE_TYPE = 3;
//        FirebaseAuth mauth;
//        DocumentReference userRef;
//        User user;
//        PrefManager session;
//        LinearLayout llEmailAddress;
//        EditText etUserName, etEmailAddressUser, etCountryUser, etQuoteUser;
//        private FirebaseFirestore db = FirebaseFirestore.getInstance();
//        private CollectionReference friendsDb = db.collection("usersShortPath");
//        TextView etDobUser;
//        Button btnSave;
//        ProgressDialog progressDialog;
//        Calendar date;
//        long dobUser=0;
//
//        @Override
//        protected void attachBaseContext(Context newBase) {
//
//            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
//                super.attachBaseContext(LocaleManager.setLocale(newBase));
//            } else {
//                super.attachBaseContext(newBase);
//            }
//        }
//
//        @Override
//        public void onConfigurationChanged(Configuration newConfig) {
//            super.onConfigurationChanged(newConfig);
//            LocaleManager.setLocale(this);
//            Log.d("EditUserInfo", "onConfigurationChanged: " + newConfig.locale.getLanguage());
//            session.setLanguage(newConfig.locale.getLanguage());
//        }
//
//        BroadcastReceiver updateViews = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                if (intent.getAction().equals(GET_USER_DATA)) {
//
//                    if (user != null) {
//                        if (user.getAuthType() == 2 || user.getAuthType() == 1) {
//                            llEmailAddress.setVisibility(View.GONE);
//                        }
//                        etUserName.setText(user.getUserName());
//                        etCountryUser.setText(session.getCountry());
//                        etEmailAddressUser.setText(user.getUserEmail());
//                        etDobUser.setText(user.getDoBirth() + "");
//                        etQuoteUser.setText(user.getQuoteUser());
//
//                        if (user.getProfileUser().length() > 0) {
//                            Picasso.get().load(user.getProfileUser()).placeholder(R.drawable.ic_add_profile_pic).into(ivProfileImage, new Callback() {
//                                @Override
//                                public void onSuccess() {
//                                    Log.d("Profile", "Succeed  Profile");
//
//                                }
//
//                                @Override
//                                public void onError(Exception e) {
//                                    Log.d("Profile", "Failed  Profile");
//
//                                }
//                            });
//                        }
//                    }
//                }
//            }
//        };
//        private StorageReference storageReference;
//        private Button btnLogoutUser;
//        //    private FirebaseFirestore db = FirebaseFirestore.getInstance();
//        CollectionReference colRef = db.collection("Users");
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_edit_user_info);
//            mauth = FirebaseAuth.getInstance();
//            userRef = colRef.document(mauth.getInstance().getCurrentUser().getUid());
//            session = PrefManager.getInstance(EditUserInfo.this);
//
//            if (Build.VERSION.SDK_INT == 21) {
//                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//                // edited here
//                this.getWindow().setStatusBarColor(Color.WHITE);
//            }
//            storageReference = FirebaseStorage.getInstance().getReference();
//            ivProfileImage = findViewById(R.id.ivProfileUseredit);
//            etCountryUser = findViewById(R.id.countryUser);
//            etDobUser = findViewById(R.id.dobUserEdit);
//            etEmailAddressUser = findViewById(R.id.etUserNameEmailEdit);
//            etUserName = findViewById(R.id.etUserNameEdit);
//            btnSave = findViewById(R.id.btnSaveUserInfoEdit);
//            etQuoteUser = findViewById(R.id.quoteUserEdit);
//            llEmailAddress = findViewById(R.id.llUserNameSignUpEdit);
//
//            btnSave.setOnClickListener(this);
//            etDobUser.setOnClickListener(this);
//            ivProfileImage.setOnClickListener(this);
//
//            etUserName.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                }
//
//                @Override
//                public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                }
//
//                @Override
//                public void afterTextChanged(Editable s) {
//                    if (s.length() > 0) {
//                        btnSave.setEnabled(true);
//                    }
//
//                }
//            });
//            FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
//                    .setPersistenceEnabled(true)
//                    .build();
//            db.setFirestoreSettings(settings);
//
//        }
//
//        private void getUserDate() {
//
//            userRef.addSnapshotListener(EditUserInfo.this, new EventListener<DocumentSnapshot>() {
//                @Override
//                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
//                    if (documentSnapshot.exists()) {
//                        user = documentSnapshot.toObject(User.class);
//                        Log.d(TAG, "onEvent: "+user.getUserID());
//                        sendBroadcast(new Intent(GET_USER_DATA));
//                    } else {
//                        Toast.makeText(EditUserInfo.this, R.string.user_not_exist, Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//        }
//
//
//        @Override
//        protected void onStop() {
//            super.onStop();
//            unregisterReceiver(updateViews);
//        }
//
//        @Override
//        protected void onStart() {
//            super.onStart();
//            getUserDate();
//            IntentFilter intentFilter = new IntentFilter(GET_USER_DATA);
//            registerReceiver(updateViews, intentFilter);
//
//        }
//
//
//        /**
//         * Called when a view has been clicked.
//         *
//         * @param v The view that was clicked.
//         */
//        @Override
//        public void onClick(View v) {
//
//            switch (v.getId()) {
//
//                case R.id.btnSaveUserInfoEdit:
//                    updateUserInfoDatabase();
//                    break;
//                case R.id.dobUserEdit:
//                    timePickerDialog();
//
//                    break;
//                case R.id.ivProfileUseredit:
//                    SELECT_IMAGE_TYPE = PROFILE_IMAGE_TYPE;
//                    showPopupWindow(ivProfileImage);
//                    break;
//            }
//
//        }
//        private long timePickerDialog() {
//            final Calendar currentDate = Calendar.getInstance();
//            date = Calendar.getInstance();
//
//            DatePickerDialog datePickerDialog = new DatePickerDialog(EditUserInfo.this, new DatePickerDialog.OnDateSetListener() {
//                @Override
//                public void onDateSet(final DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
//                    date.set(year, monthOfYear, dayOfMonth);
//
//                    dobUser=date.getTimeInMillis();
//
//                    etDobUser.setText(changeMilisecondToDate(date.getTimeInMillis()+""));
//                }
//            }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE));
//            datePickerDialog.getDatePicker().setMaxDate(currentDate.getTimeInMillis());
//            datePickerDialog.show();
//
//            return date.getTimeInMillis();
//        }
//
//
//        private void updateUserInfoDatabase() {
//            showProgressDialog(getString(R.string.updating_user_profile));
//
//            String name = etUserName.getText().toString();
//            String email = etEmailAddressUser.getText().toString();
//            String country = etCountryUser.getText().toString();
//            String qUser = etQuoteUser.getText().toString();
//
//
//            Map<String, Object> userInfo = new HashMap<>();
//            Map<String, Object> userInfoName = new HashMap<>();
//            userInfoName.put("id", mauth.getCurrentUser().getUid());
//            userInfo.put("userName", name);
//            userInfo.put("userEmail", email);
//            userInfo.put("country", country);
//
//            if (dobUser!=0){
//                String dob = dobUser+"";
//                userInfo.put("doBirth", changeMilisecondToDate(String.valueOf(dobUser))+"");
//            }
//            if (user.getUserName()!= null){
//                friendsDb.document(user.getUserName()).delete();
//
//                Log.d(TAG, "User Name is  null: "+user.getUserName());
//
//                friendsDb.document(TextUtils.isEmpty(name)?mauth.getCurrentUser().getUid():name.replace(" ","_")).set(userInfoName).addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        hideProgressDialog();
//                        // Sign in success, update UI with the signed-in user's information
//                        Log.d(TAG, "createUserWithEmail:success");
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        hideProgressDialog();
//                        Toast.makeText(EditUserInfo.this, R.string.registerd_error_in_db, Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//
//            }else {
//                Log.d(TAG, "User name is not null: "+name);
//                if (name!=null){
//                    friendsDb.document(TextUtils.isEmpty(name)?mauth.getCurrentUser().getUid():name.replace(" ","_")).set(userInfoName).addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
//                            hideProgressDialog();
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "createUserWithEmail:success");
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            hideProgressDialog();
//                            Toast.makeText(EditUserInfo.this, R.string.registerd_error_in_db, Toast.LENGTH_SHORT).show();
//
//                        }
//                    });
//
//                }
//
//
//            }
//
//            Log.d(TAG, "updateUserInfoDatabase: "+changeMilisecondToDate(String.valueOf(dobUser)));
//
//            userInfo.put("quoteUser", qUser);
//
//            if (email.length() > 0) {
//                mauth.getCurrentUser().updateEmail(email);
//            }
//
//            userRef.update(userInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
//                @Override
//                public void onSuccess(Void aVoid) {
//                    Toast.makeText(EditUserInfo.this, R.string.profile_updated, Toast.LENGTH_SHORT).show();
//                    hideProgressDialog();
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(EditUserInfo.this, R.string.error_while_uploading, Toast.LENGTH_SHORT).show();
//                    hideProgressDialog();
//                }
//            });
//
//        }
//
//        public void showProgressDialog(String textProgress) {
//
//
//            if (progressDialog == null || !progressDialog.isShowing()) {
//                progressDialog = new ProgressDialog(EditUserInfo.this);
//                progressDialog.setCancelable(false);
//                progressDialog.setMessage(textProgress);
//                progressDialog.setCanceledOnTouchOutside(false);
//                progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//                    @Override
//                    public void onCancel(DialogInterface dialogInterface) {
//                        finish();
//                    }
//                });
//                progressDialog.show();
//
//            }
//        }
//
//        public void hideProgressDialog() {
//
//            if (progressDialog != null && progressDialog.isShowing()) {
//
//                progressDialog.dismiss();
//            }
//        }
//
//        @Override
//        public void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//            switch (requestCode) {
//                case CODE_CHANGE_MY_PROFILE_PIC_GALLERY:
//                    if (resultCode == RESULT_OK && data != null) {
//                        showProgressDialog(getString(R.string.loading_data));
//                        imageUri = data.getData();
//                        ivProfileImage.setImageURI(imageUri);
//                        if (imageUri != null) {
//                            final StorageReference filePath_profielpic = storageReference.child("Users").child("profile_pics").child(mauth.getCurrentUser().getUid()).child(imageUri.getLastPathSegment());
//                            filePath_profielpic.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//
//                                @Override
//                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                                    Log.d("image", "Succeed");
//
//                                    filePath_profielpic.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                                        @Override
//                                        public void onSuccess(Uri uri) {
//                                            hideProgressDialog();
//                                            Log.d("imageLink", "onSuccess: uri= " + uri.toString());
//                                            userRef.update("profileUser", uri.toString() + "");
//                                        }
//                                    }).addOnFailureListener(new OnFailureListener() {
//                                        @Override
//                                        public void onFailure(@NonNull Exception e) {
//                                            hideProgressDialog();
//                                            Log.d("imageLink", "onFailed: uri= ");
//                                        }
//                                    });
//
//                                }
//
//                            }).addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
//                                    hideProgressDialog();
//
//                                    Log.d("image", "Failed " + e);
//
//
//                                }
//                            });
//
//
//                        } else {
//                            Toast.makeText(this, "NothingP", Toast.LENGTH_SHORT).show();
//                            //  newPost.child("profilePic").setValue("");
//                            Log.d("nophoto", " profileempty");
//                        }
//                    }
//                    break;
//
//                case CODE_CHANGE_MY_PROFILE_PIC_CAMERA:
//                    if (resultCode == RESULT_OK && data != null) {
//                        showProgressDialog(getString(R.string.loading_data));
//                        Bitmap photo = (Bitmap) data.getExtras().get("data");
//                        imageUri = getImageUri(EditUserInfo.this, photo);
//                        ivProfileImage.setImageBitmap(photo);
//
//                        Log.d("EditUserInfo", "onActivityResult: " + imageUri);
//                        // ivProfileImage.setImageURI(imageUri);
//
//                        if (imageUri != null) {
//                            final StorageReference filePath_profielpic = storageReference.child("Users").child("profile_pics").child(mauth.getCurrentUser().getUid()).child(imageUri.getLastPathSegment());
//                            filePath_profielpic.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//
//                                @Override
//                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                                    Log.d("image", "Succeed");
//
//                                    filePath_profielpic.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                                        @Override
//                                        public void onSuccess(Uri uri) {
//                                            hideProgressDialog();
//                                            Log.d("imageLink", "onSuccess: uri= " + uri.toString());
//                                            userRef.update("profileUser", uri.toString() + "");
//                                        }
//                                    }).addOnFailureListener(new OnFailureListener() {
//                                        @Override
//                                        public void onFailure(@NonNull Exception e) {
//                                            hideProgressDialog();
//                                            Log.d("imageLink", "onFailed: uri= ");
//
//                                        }
//                                    });
//
//                                }
//
//                            }).addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
//                                    hideProgressDialog();
//                                    Log.d("image", "Failed " + e);
//                                }
//                            });
//
//                        } else {
//                            hideProgressDialog();
//                            Toast.makeText(this, "NothingP", Toast.LENGTH_SHORT).show();
//                            //  newPost.child("profilePic").setValue("");
//                            Log.d("nophoto", " profileempty");
//                        }
//
//                    }
//                    break;
//            }
//            super.onActivityResult(requestCode, resultCode, data);
//
//        }
//
//
//        private void pickImageFromCamera() {
//            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            if (Build.VERSION.SDK_INT >= 23) {
//                if (ActivityCompat.checkSelfPermission(EditUserInfo.this, permissionStorage) != PackageManager.PERMISSION_GRANTED) {
//                    requestPermission(permissionStorage, REQUEST_EXTERNAL_STORAGE);
//                } else if (ActivityCompat.checkSelfPermission(EditUserInfo.this, permissionCamera) != PackageManager.PERMISSION_GRANTED) {
//                    requestPermission(permissionCamera, REQUEST_CAMERA);
//                } else {
//                    startActivityForResult(i, CODE_CHANGE_MY_PROFILE_PIC_CAMERA);
//                }
//            } else {
//                startActivityForResult(i, CODE_CHANGE_MY_PROFILE_PIC_CAMERA);
//            }
//        }
//
//
//
//        private String changeMilisecondToDate(String milliSeconds) {
//            String dateFormat = "E, MMM d, yyyy";
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTimeInMillis(Long.parseLong(milliSeconds));
//            return simpleDateFormat.format(calendar.getTime());
//        }
//
//        void showPopupWindow(View view) {
//            PopupMenu popup = new PopupMenu(EditUserInfo.this, view);
//            try {
//                Field[] fields = popup.getClass().getDeclaredFields();
//                for (Field field : fields) {
//                    if ("mPopup".equals(field.getName())) {
//                        field.setAccessible(true);
//                        Object menuPopupHelper = field.get(popup);
//                        Class<?> classPopupHelper = Class.forName(menuPopupHelper.getClass().getName());
//                        Method setForceIcons = classPopupHelper.getMethod("setForceShowIcon", boolean.class);
//                        setForceIcons.invoke(menuPopupHelper, true);
//                        break;
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            popup.getMenuInflater().inflate(R.menu.menu_pick_profile_image, popup.getMenu());
//
//            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//
//                public boolean onMenuItemClick(MenuItem item) {
//                    if (item.getItemId() == R.id.iPickCamera) {
//                        pickImageFromCamera();
//
//                    }
//                    if (item.getItemId() == R.id.iPickGallery) {
//                        pickImageFromGallery();
//
//                    }
//                    return false;
//                }
//            });
//            popup.show();
//        }
//
//
//        public Uri getImageUri(Context inContext, Bitmap inImage) {
//            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//            inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//            String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
//            return Uri.parse(path);
//        }
//
//        private void pickImageFromGallery() {
//            Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//
//            if (Build.VERSION.SDK_INT >= 23) {
//                if (ActivityCompat.checkSelfPermission(EditUserInfo.this, permissionStorage) != PackageManager.PERMISSION_GRANTED) {
//                    requestPermission(permissionStorage, REQUEST_EXTERNAL_STORAGE);
//                } else {
//                    startActivityForResult(i, CODE_CHANGE_MY_PROFILE_PIC_GALLERY);
//                }
//            } else {
//                startActivityForResult(i, CODE_CHANGE_MY_PROFILE_PIC_GALLERY);
//            }
//        }
//
//        private void requestPermission(String permission, int requstCode) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(EditUserInfo.this, permission)) {
//                ActivityCompat.requestPermissions(EditUserInfo.this, new String[]{permission}, requstCode);
//            } else {
//                ActivityCompat.requestPermissions(EditUserInfo.this, new String[]{permission}, requstCode);
//            }
//        }
//    }


}
