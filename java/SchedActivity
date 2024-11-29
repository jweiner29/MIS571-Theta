package com.example.mis571_finalproject;

import android.app.Activity;
import android.content.Intent;
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

public class SchedActivity extends Activity implements OnClickListener {

    Button registerBtn, mainBtn;
    ScrollView scrollview;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_sched);
        //copy database file
        try{
        DBOperator.copyDB(getBaseContext());
        }catch(Exception e){
            e.printStackTrace();
        }
        registerBtn = (Button) this.findViewById(R.id.RegisterButton);
        registerBtn.setOnClickListener(this);
        mainBtn = (Button) this.findViewById(R.id.GoMain2Button);
        mainBtn.setOnClickListener(this);
        scrollview = (ScrollView) this.findViewById(R.id.scrollview_schedule);

        // Automatically populate scrollvoew with query results
        populateScrollView();
    }

    private void populateScrollView() {
        String sql = SQLCommand.Schedule;
        Cursor cursor = DBOperator.getInstance().execQuery(sql);

        if (cursor != null && cursor.getCount() > 0) {
            scrollview.removeAllViews(); // clear previous views

            //create table layout
            TableLayout tableLayout = new TableLayout(this);
            tableLayout.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT
            ));

            // Add table header row
            TableRow headerRow = new TableRow(this);
            headerRow.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
            headerRow.setPadding(10, 10, 10, 10);

            //Add column headers
            String[] headers = {"Class Date", "Class Time", "Class Name", "Description", "Duration (min)", "Price ($)", "Location", "Instructor", "Remaining Spots"};
            for (String header : headers) {
                TextView textView = new TextView(this);
                textView.setText(header);
                textView.setPadding(10, 10, 10, 10);
                textView.setTextColor(getResources().getColor(android.R.color.white));
                textView.setTypeface(null, Typeface.BOLD);
                headerRow.addView(textView);
            }
            tableLayout.addView(headerRow);

            // Add data rows
            if (cursor.moveToFirst()) {
                do {
                    TableRow dataRow = new TableRow(this);
                    dataRow.setPadding(10, 10, 10, 10);

                    for (int i = 0; i < cursor.getColumnCount(); i++) {
                        TextView textView = new TextView(this);
                        textView.setText(cursor.getString(i));
                        textView.setPadding(10, 10, 10, 10);
                        textView.setTextColor(getResources().getColor(android.R.color.black));
                        dataRow.addView(textView);
                    }
                    tableLayout.addView(dataRow);
                } while (cursor.moveToNext());
            }

            // Add table layout to scrollview
            scrollview.addView(tableLayout);
        } else {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.RegisterButton) {
            Intent intent = new Intent(this, RegisterActivity.class);
            this.startActivity(intent);

        } else if (id == R.id.GoMain2Button) {
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
        }
    }
}

