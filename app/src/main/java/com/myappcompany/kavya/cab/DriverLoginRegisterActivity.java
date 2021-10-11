package com.myappcompany.kavya.cab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class DriverLoginRegisterActivity extends AppCompatActivity {

    private Button DriverLoginButton;
    private Button DriverRegisterButton;
    private TextView DriverRegisterLink;
    private TextView DriverStatus;
    private EditText EmailDriver;
    private EditText PasswordDriver;
    private ProgressDialog loadingBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login_register);
        mAuth = FirebaseAuth.getInstance();

        DriverLoginButton = (Button) findViewById(R.id.driver_login_btn);
        DriverRegisterButton = (Button) findViewById(R.id.driver_register_btn);
        DriverRegisterLink = (TextView) findViewById(R.id.driver_register_link);
        DriverStatus = (TextView) findViewById(R.id.driver_status);
        EmailDriver = (EditText) findViewById(R.id.email_driver);
        PasswordDriver = (EditText) findViewById(R.id.password_driver);
        loadingBar = new ProgressDialog(this);

        DriverRegisterButton.setVisibility(View.INVISIBLE);
        DriverRegisterButton.setEnabled(false);

        DriverRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DriverLoginButton.setVisibility(View.INVISIBLE);
                DriverRegisterLink.setVisibility(view.INVISIBLE);
                DriverStatus.setText("Register Driver");

                DriverRegisterButton.setVisibility(View.VISIBLE);
                DriverRegisterButton.setEnabled(true);
            }
        });

        DriverRegisterButton.setOnClickListener(new View.OnClickListener() { // authentication
            @Override
            public void onClick(View view) {
                String email = EmailDriver.getText().toString();
                String password = PasswordDriver.getText().toString();

                RegisterDriver(email, password);
            }
        });
    }

        private void RegisterDriver(String email, String password)
        {
            if(TextUtils.isEmpty(email))
            {
                Toast.makeText(DriverLoginRegisterActivity.this, "Please write Email..", Toast.LENGTH_SHORT).show();
            }
            if(TextUtils.isEmpty(password))
            {
                Toast.makeText(DriverLoginRegisterActivity.this, "Please write Password..", Toast.LENGTH_SHORT).show();
            }
            else
            {
                loadingBar.setTitle("Driver Registration");
                loadingBar.setMessage("Please wait, while we register your data..");
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(DriverLoginRegisterActivity.this, "Driver Register Successfully..", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                        }
                        else
                        {
                            Toast.makeText(DriverLoginRegisterActivity.this, "Driver Registration Unsuccessful, Please try again", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                        }
                    }
                });
            }
        }

}