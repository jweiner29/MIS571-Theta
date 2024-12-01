package com.example.mis571_finalproject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mis571_finalproject.constant.SQLCommand;
import com.example.mis571_finalproject.util.DBOperator;

import java.io.IOException;

public class RegisterActivity extends Activity implements OnClickListener {

    EditText stIDEdit, clIDEdit;
    Button firstclassBtn, signupBtn, mainBtn;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        // Check if the database has already been copied
        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        boolean isDBCopied = prefs.getBoolean("isDBCopied", false);

        if (!isDBCopied) {
            try {
                DBOperator.copyDB(getBaseContext());

                // Mark the database as copied
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("isDBCopied", true);
                editor.apply();

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error copying database", Toast.LENGTH_LONG).show();
            }
        }

        //Initialize UI components
        stIDEdit = (EditText) this.findViewById(R.id.studentID_edittext);
        clIDEdit = (EditText) this.findViewById(R.id.classID_edittext);
        firstclassBtn = (Button) this.findViewById(R.id.FirstClassButton);
        signupBtn = (Button) this.findViewById(R.id.SignUpButton);
        mainBtn = (Button) this.findViewById(R.id.GoMain3Button);

        //set click listeners:
        firstclassBtn.setOnClickListener(this);
        signupBtn.setOnClickListener(this);
        mainBtn.setOnClickListener(this);
    }

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.FirstClassButton) {
            Intent intent = new Intent(this, FirstClassActivity.class);
            this.startActivity(intent);

        } else if (id == R.id.SignUpButton) {
            registerForClass(); // Handle the "Sign Up" button click
        } else if (id == R.id.GoMain3Button) {
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
        }
    }

    private void registerForClass() {
        String classID = clIDEdit.getText().toString().trim();
        String studentID = stIDEdit.getText().toString().trim();

        // Validate input
        if (classID.isEmpty() || studentID.isEmpty()) {
            Toast.makeText(this, "Please enter both Class ID and Student ID.", Toast.LENGTH_LONG).show();
            return;
        }

        String[] regArgs = getregArgs();

        SQLiteDatabase db = null;
        try {
            // Execute the AddReg SQL command using DBOperator
            String sql = SQLCommand.AddReg;
            DBOperator.getInstance().execSQL(sql, regArgs);

            // Navigate to Thank You screen
            Intent intent = new Intent(this, ThankYouActivity.class);
            startActivity(intent);

        } catch (Exception e) {
            Log.e("RegisterActivity", "Error registering for class: " + e.getMessage());
            Toast.makeText(this, "Failed to register. Please try again.", Toast.LENGTH_LONG).show();
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    private String[] getregArgs() {
        String[] regArgs = new String[3];
        // ClassId
        regArgs[0] = clIDEdit.getText().toString();
        // AttendId
        regArgs[1] = stIDEdit.getText().toString();
        // Status
        regArgs[2] = "Confirmed";
        return regArgs;
    }
}