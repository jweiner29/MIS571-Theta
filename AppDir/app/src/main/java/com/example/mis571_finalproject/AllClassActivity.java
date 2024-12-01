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

public class AllClassActivity extends Activity implements OnClickListener {

    Button mainBtn;
    ScrollView scrollview;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_classes);

        mainBtn = (Button) this.findViewById(R.id.GoMainButton);
        mainBtn.setOnClickListener(this);
        scrollview = (ScrollView) this.findViewById(R.id.scrollview_all);

        // Automatically populate scrollview with query results
        populateScrollView();
    }

    private void populateScrollView() {
        String sql = SQLCommand.AllClasses;
        Cursor cursor = DBOperator.getInstance().execQuery(sql);
        try {

            if (cursor != null && cursor.getCount() > 0) {
                scrollview.removeAllViews(); // Clear previous views

                // Create table layout
                TableLayout tableLayout = new TableLayout(this);
                tableLayout.setLayoutParams(new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT
                ));

                // Add table header row
                TableRow headerRow = new TableRow(this);
                headerRow.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                headerRow.setPadding(10, 10, 10, 10);

                // Add column headers
                String[] headers = {"Class ID", "Class Date", "Class Time", "Spots Avail", "Location", "CT", "Inst"};
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
        } catch (Exception e) {
            Log.e("AllClassActivity", "Error populating scrollview: ", e);
            Toast.makeText(this, "Failed to load data. Please try again.", Toast.LENGTH_LONG).show();
        } finally {
            // Ensure the cursor is closed
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
    }

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.GoMainButton) {
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
        }
    }
}

