package com.example.mortgagecalculatorapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Method to get monthly payments
    private double calculateMonthlyPayments(int principal, float interest, int amtperiod) {

        System.out.println("Calculate monthly payments method called");

        // Determine effective monthly interest rate
        float effective_interest = interest/12;

        // Calculate (1+i)^n and store it in a variable
        double interest_power = Math.pow(1+effective_interest/100, amtperiod*12);

        // Calculate mortgage = P(i(1+i)^n/((1+i)^n - 1)
        double mortgage = principal*((effective_interest/100)*interest_power/(interest_power - 1));
        return mortgage;
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

        // Calculate monthly payments using the provided input
        double mortgage = calculateMonthlyPayments(principal, interest, amtperiod);
        System.out.println("Calculated Monthly Payments: " +  mortgage);

        // Add intent to move to the second activity
        Intent i = new Intent(this, SecondActivity.class);


    }
}