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

public class InstMenuActivity extends Activity implements OnClickListener {

    Button createBtn, cancelBtn, fbBtn;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructor_menu);

        //Set UI
        createBtn = (Button) this.findViewById(R.id.CreateButton);
        cancelBtn = (Button) this.findViewById(R.id.CancelButton);
        fbBtn = (Button) this.findViewById(R.id.FeedbackButton);

        //Set on click listeners
        createBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
        fbBtn.setOnClickListener(this);

    }

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.CreateButton) {
            Intent intent = new Intent(this, CreateClassActivity.class);
            this.startActivity(intent);

        } else if (id == R.id.CancelButton) {
            Intent intent = new Intent(this, CancelClassActivity.class);
            this.startActivity(intent);

        }else if (id == R.id.FeedbackButton) {
            Intent intent = new Intent(this, FeedbackActivity.class);
            this.startActivity(intent);
        }
    }
}

