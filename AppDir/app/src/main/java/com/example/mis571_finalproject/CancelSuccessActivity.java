package com.example.mis571_finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CancelSuccessActivity extends Activity implements OnClickListener {

    Button mainBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success_cancel);

        // Initialize the main button
        mainBtn = findViewById(R.id.GoMainButton);

        // Set click listener to navigate to MainActivity
        mainBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish(); // Finish this activity to prevent returning to it
        });
    }
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.GoMainButton) {
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);

        }
    }
}