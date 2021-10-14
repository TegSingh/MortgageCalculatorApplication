package com.example.mortgagecalculatorapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // On Click Listener method for Calculate button
    public void onClickCalculate(View view) {
        System.out.println("Calculate button clicked");

        // Define the variables storing the input values
        EditText edtPrincipal = findViewById(R.id.edtPrincipal);
        EditText edtInterest = findViewById(R.id.edtInterest);
        EditText edtAmtPeriod = findViewById(R.id.edtAmtPeriod);

        // Get the string value and convert it to integer
        String principal_input = edtPrincipal.getText().toString();
        int principal = Integer.parseInt(principal_input);
        System.out.println("Entered principal: " + principal);

        // Get the string value for interest input and convert it to floating point number
        String interest_input = edtInterest.getText().toString();
        float interest = Float.parseFloat(interest_input);
        System.out.println("Entered interest: " + interest);

        // Get the string value for amortization period and convert it into integer
        String amtperiod_input = edtAmtPeriod.getText().toString();
        int amtperiod = Integer.parseInt(amtperiod_input);
        System.out.println("Entered Amortization Period: " + amtperiod);


    }
}