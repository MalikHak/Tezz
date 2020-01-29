package app.com.tezz.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.dynamic.IFragmentWrapper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import app.com.tezz.R;

public class RegisterWithPhoneActivity extends AppCompatActivity {

public static final String TAG=RegisterWithPhoneActivity.class.getSimpleName();


    FirebaseAuth mAuth;

    FirebaseUser currentUser;

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    EditText etCountryCode,etPhoneNumber;

    ProgressBar progressBar;

    TextView tvFeedback;

    TextView tvGuestUser;



    Button btnCOntinue;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_with_phone);




        Button crashButton = new Button(this);
        crashButton.setText("Crash!");
        crashButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                throw new RuntimeException("Test Crash"); // Force a crash
            }
        });

        addContentView(crashButton, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        mAuth=FirebaseAuth.getInstance();

        currentUser=mAuth.getCurrentUser();


        tvGuestUser=findViewById(R.id.tvGuestUser);


        etCountryCode=findViewById(R.id.etCountryCode);
        etPhoneNumber=findViewById(R.id.etPhoneNumber);

        progressBar=findViewById(R.id.login_progress_bar);
        tvFeedback=findViewById(R.id.tvFeedback);






        tvGuestUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                guestUserSigning();
            }
        });



        btnCOntinue=findViewById(R.id.btnContinue);

         btnCOntinue.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 String countryCode=etCountryCode.getText().toString();
                 String phoneNumber=etPhoneNumber.getText().toString();

                 String completeNumber="+"+countryCode+phoneNumber;

                 if(validation(countryCode,phoneNumber)){

                     btnCOntinue.setEnabled(false);

                     progressBar.setVisibility(View.VISIBLE);


                     PhoneAuthProvider.getInstance().verifyPhoneNumber(
                             completeNumber,        // Phone number to verify
                             60,                 // Timeout duration
                             TimeUnit.SECONDS,   // Unit of timeout
                             RegisterWithPhoneActivity.this,               // Activity (for callback binding)
                             mCallbacks);        // OnVerificationStateChangedCallbacks


                 }



             }
         });
    mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {


            mAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(RegisterWithPhoneActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()){
                        goToMainActivity();
                    }
                    else {

                        tvFeedback.setText("Oh Sorry a problem occured while sending otp");

                    }

                    progressBar.setVisibility(View.INVISIBLE);
                    btnCOntinue.setEnabled(true);


                }
            });

        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

            Log.d(TAG, "onVerificationFailed: "+e);

            tvFeedback.setText("Failed Please Try Again!");
            tvFeedback.setVisibility(View.VISIBLE);
            btnCOntinue.setEnabled(true);
            progressBar.setVisibility(View.INVISIBLE);

        }

        @Override
        public void onCodeSent(@NonNull final String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);


            new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent=new Intent(RegisterWithPhoneActivity.this,VerficationOTPActivity.class);
                    intent.putExtra("AuthCredintials",s);
                    startActivity(intent);

                }
            },10000);




        }
    };

    }

    private void guestUserSigning() {


        mAuth.signInAnonymously().addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if (task.isSuccessful()){

                    goToMainActivity();


                }else {

                    Toast.makeText(RegisterWithPhoneActivity.this, "Failed Sign In", Toast.LENGTH_SHORT).show();
                }


            }

        });
    }


    private boolean validation(String countryCode, String phoneNumber) {

        if (countryCode.isEmpty() || phoneNumber.isEmpty()){

            tvFeedback.setText("Please enter phoneNumber and Country Code");
            tvFeedback.setVisibility(View.VISIBLE);
            return false;
        }
        else
            return true;
    }

    private void goToMainActivity() {


        Intent intent=new Intent(RegisterWithPhoneActivity.this,MainFoodsActivity.class);
        startActivity(intent);
        finish();


    }


    @Override
    protected void onStart() {
        super.onStart();


        if (currentUser!=null){
            goToMainActivity();

        }
    }
}
