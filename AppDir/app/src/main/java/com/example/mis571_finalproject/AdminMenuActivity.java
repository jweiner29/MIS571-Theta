package com.example.mis571_finalproject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
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
import com.example.mis571_finalproject.util.DBOpenHelper;
import com.example.mis571_finalproject.util.DBOperator;
import com.example.mis571_finalproject.view.TableView;

import java.io.IOException;

public class AdminMenuActivity extends Activity implements OnClickListener {

    Button classBtn, regBtn;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_menu);

        //Set UI
        classBtn = (Button) this.findViewById(R.id.ClassesButton);
        regBtn = (Button) this.findViewById(R.id.RegButton);


        //Set on click listeners
        classBtn.setOnClickListener(this);
        regBtn.setOnClickListener(this);

    }

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.ClassesButton) {
            Intent intent = new Intent(this, AllClassActivity.class);
            this.startActivity(intent);

        } else if (id == R.id.RegButton) {
            Intent intent = new Intent(this, AllRegActivity.class);
            this.startActivity(intent);
        }
    }
}

