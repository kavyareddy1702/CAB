package com.myappcompany.kavya.cab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CustomerLoginRegisterActivity extends AppCompatActivity {

    private Button CustomerLoginButton;
    private Button CustomerRegisterButton;
    private TextView CustomerRegisterLink;
    private TextView CustomerStatus;
    private EditText EmailCustomer;
    private EditText PasswordCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login_register);

        CustomerLoginButton = (Button) findViewById(R.id.customer_login_btn);
        CustomerRegisterButton = (Button) findViewById(R.id.customer_register_btn);
        CustomerRegisterLink = (TextView) findViewById(R.id.register_customer_link);
        CustomerStatus = (TextView) findViewById(R.id.customer_status);
        EmailCustomer = (EditText) findViewById(R.id.email_customer);
        PasswordCustomer = (EditText) findViewById(R.id.password_customer);

        CustomerRegisterButton.setVisibility(View.INVISIBLE);
        CustomerRegisterButton.setEnabled(false);

        CustomerRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                CustomerLoginButton.setVisibility(View.INVISIBLE);
                CustomerRegisterLink.setVisibility(view.INVISIBLE);
                CustomerStatus.setText("Register Customer");

                CustomerRegisterButton.setVisibility(View.VISIBLE);
                CustomerRegisterButton.setEnabled(true);
            }
        });
    }
}