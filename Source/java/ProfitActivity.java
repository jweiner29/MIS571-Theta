package com.example.mis571_finalproject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ScrollView;
import android.database.Cursor;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


import com.example.mis571_finalproject.constant.SQLCommand;
import com.example.mis571_finalproject.util.DBOperator;
import com.example.mis571_finalproject.view.TableView;

import java.io.IOException;

public class ProfitActivity extends Activity implements OnClickListener {

    Button mainBtn;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profit);
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
        mainBtn = (Button) this.findViewById(R.id.GoMain2Button);
        mainBtn.setOnClickListener(this);
    }
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.GoMain2Button) {
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
        }
    }
}
