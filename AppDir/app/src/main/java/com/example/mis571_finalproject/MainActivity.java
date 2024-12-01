package com.example.mis571_finalproject;

import com.example.mis571_finalproject.util.DBOperator;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends android.app.Activity implements OnClickListener{

    Button StudentBtn,InstructorBtn, AdminBtn;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

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

        StudentBtn = (Button) this.findViewById(R.id.StudentButton);
        StudentBtn.setOnClickListener(this);
        InstructorBtn = (Button) this.findViewById(R.id.InstructorButton);
        InstructorBtn.setOnClickListener(this);
        AdminBtn = (Button) this.findViewById(R.id.AdminButton);
        AdminBtn.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        int id=v.getId();
        if (id==R.id.StudentButton){
            Intent intent = new Intent(this, SchedActivity.class);
            this.startActivity(intent);

        }else if (id==R.id.InstructorButton){
            Intent intent = new Intent(this, InstMenuActivity.class);
            this.startActivity(intent);

        }else if (id==R.id.AdminButton) {
            Intent intent = new Intent(this, AdminMenuActivity.class);
            this.startActivity(intent);
        }
    }
}