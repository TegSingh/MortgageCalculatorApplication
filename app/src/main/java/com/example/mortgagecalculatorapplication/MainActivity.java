package com.example.mortgagecalculatorapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String input = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    // Method to get monthly payments
    private double calculateMonthlyPayments(int principal, float interest, int amtperiod, float downpayment) {

        System.out.println("Calculate monthly payments method called");

        // Determine effective monthly interest rate
        float effective_interest = interest/12;

        // Calculate (1+i)^n and store it in a variable
        double interest_power = Math.pow(1+effective_interest/100, amtperiod*12);

        // Calculate mortgage = P(i(1+i)^n/((1+i)^n - 1)
        double mortgage = (principal-downpayment)*((effective_interest/100)*interest_power/(interest_power - 1));
        return mortgage;
    }

    // Method to check if the input provided contains digits and not alphabets or special characters
    private static boolean validateInput(String s) {
        System.out.println("Validate input method called");
        int i = 0;
        while (i < s.length()) {
            System.out.print(s.charAt(i));
            if (!(s.charAt(i) >= '0' && s.charAt(i) <= '9')) {
                return false;
            }
            i++;
        }
        System.out.println();
        return true;
    }

    // On Click Listener method for Calculate button
    public void onClickCalculate(View view) {
        System.out.println("Calculate button clicked");

        // Define the variables storing the input values
        EditText edtPrincipal = findViewById(R.id.edtPrincipal);
        EditText edtInterest = findViewById(R.id.edtInterest);
        EditText edtAmtPeriod = findViewById(R.id.edtAmtPeriod);
        EditText edtDownPayment = findViewById(R.id.edtDownPayment);

        // Instantiate the Radio group
        RadioGroup radioGroupDownPayment = findViewById(R.id.radioGroupDownPayment);

        // Handle event in case the value for principal has not been entered
        if (edtPrincipal.getText().toString().matches("")) {
            System.out.println("Principal not entered");
            Toast.makeText(MainActivity.this, "Enter a value for Principal", Toast.LENGTH_SHORT).show();
            return;
        }

        // Handle event in case the value for interest has not been entered
        if (edtInterest.getText().toString().matches("")) {
            System.out.println("Interest value not entered");
            Toast.makeText(MainActivity.this, "Enter a value for Interest", Toast.LENGTH_SHORT).show();
            return;
        }

        // Handle event in case the value for amortization period has not been entered
        if (edtAmtPeriod.getText().toString().matches("")) {
            System.out.println("Amortization Period not entered");
            Toast.makeText(MainActivity.this, "Enter a value for Amortization Period", Toast.LENGTH_SHORT).show();
            return;
        }

        // Handle event in case the value for downpayment has not been entered
        if (edtDownPayment.getText().toString().matches("")) {
            System.out.println("Down Payment not entered");
            Toast.makeText(MainActivity.this, "Enter a value for DownPayment", Toast.LENGTH_SHORT).show();
            return;
        }

//        // Check if the values entered are valid integer values
//        if (validateInput(edtPrincipal.getText().toString())) {
//            Toast.makeText(MainActivity.this, "Enter a valid integer value for Principal", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (validateInput(edtInterest.getText().toString())) {
//            Toast.makeText(MainActivity.this, "Enter a valid floating point value for Interest", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (validateInput(edtAmtPeriod.getText().toString())) {
//            Toast.makeText(MainActivity.this, "Enter a valid integer for amortization period", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (validateInput(edtDownPayment.getText().toString())) {
//            Toast.makeText(MainActivity.this, "Enter a value for Down Payment", Toast.LENGTH_SHORT).show();
//            return;
//        }


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

        // Get the string value for down payment and convert it to float
        String downpayment_input = edtDownPayment.getText().toString();

        // Get the values for radio buttons
        RadioButton dollar = findViewById(R.id.dollarDown);
        RadioButton percent = findViewById(R.id.percentDown);
        float downpayment = 0;

        // Get the selected radio button values and calculate downpayment
        if (dollar.isChecked()) {
            System.out.println("$ button checked");
            downpayment = Float.parseFloat(downpayment_input);
            System.out.println("Entered Down Payment: " + downpayment);
        } else if (percent.isChecked()) {
            System.out.println("% button checked");
            downpayment = Float.parseFloat(downpayment_input)*principal/100;
            System.out.println("Entered Down Payment: " + downpayment);
        } else {
            System.out.println("No Radio button was checked");
            Toast.makeText(MainActivity.this, "Assuming Downpayment to be in dollars", Toast.LENGTH_SHORT).show();
        }

        // Calculate monthly payments using the provided input
        double mortgage = calculateMonthlyPayments(principal, interest, amtperiod, downpayment);
        System.out.println("Calculated Monthly Payments: " +  mortgage);

        // Add intent to move to the second activity
        double[] message = new double[2];
        message[0] = mortgage;
        message[1] = amtperiod * 1.0;

        Intent i = new Intent(this, SecondActivity.class);
        i.putExtra("results", message);
        startActivity(i);


    }
}