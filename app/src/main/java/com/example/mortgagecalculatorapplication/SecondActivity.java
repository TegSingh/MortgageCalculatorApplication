package com.example.mortgagecalculatorapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Get the intent and the data passed to it as extras
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        double[] intent_message = extras.getDoubleArray("results");

        // Get the TextViews and set their values based on intent extras
        TextView messageMonthly = findViewById(R.id.messageMonthlyMortgage);
        messageMonthly.setText("Calculated Monthly Payment: $"+ String.format("%.0f", intent_message[0]));

        TextView messagePayBack = findViewById(R.id.messagePayBack);
        Double years_double = new Double(intent_message[1]);
        int years_int = years_double.intValue();
        String years = Integer.toString(years_int);
        messagePayBack.setText("For: " + years + " years");

    }

    public void goBackToLauncher(View view) {
        System.out.println("Go back button clicked");
        // End the activity and go back to parent using intent
        finish();
    }
}