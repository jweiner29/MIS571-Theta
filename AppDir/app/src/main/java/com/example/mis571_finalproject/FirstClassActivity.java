package com.example.mis571_finalproject;

import com.example.mis571_finalproject.constant.SQLCommand;
import com.example.mis571_finalproject.util.DBOperator;
import com.example.mis571_finalproject.util.Pair;
import com.example.mis571_finalproject.view.ChartGenerator;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class FirstClassActivity extends Activity implements OnClickListener {

    EditText firstnameEdit, lastnameEdit, addressEdit, emailEdit, classIdEdit;
    Button joinBtn, mainBtn;
    DatePicker DOBdate;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstclass);
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
        firstnameEdit = (EditText) this.findViewById(R.id.FirstName_edittext);
        lastnameEdit = (EditText) this.findViewById(R.id.LastName_edittext);
        DOBdate = (DatePicker) this.findViewById(R.id.DOBdatePicker);
        emailEdit = (EditText) this.findViewById(R.id.Email_edittext);
        addressEdit = (EditText) this.findViewById(R.id.Address_edittext);
        classIdEdit = (EditText) this.findViewById(R.id.classID_edittext);
        joinBtn = (Button) this.findViewById(R.id.JoinButton);
        mainBtn = (Button) this.findViewById(R.id.GoMain4Button);

        //set click listeners:
        joinBtn.setOnClickListener(this);
        mainBtn.setOnClickListener(this);
    }

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.JoinButton) {
            insertNewAttendee();  // Handle the Join Button click
            //DBOperator.getInstance().execSQL(SQLCommand.AddAttendee,
            // this.getArgs(true));
            //Intent intent = new Intent(this, ThankYouActivity.class);
            //this.startActivity(intent);
        } else if (id == R.id.GoMain4Button) {
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
        }
    }

    private void insertNewAttendee() {
        SQLiteDatabase db = null;
        int newAttendeeId = -1;

        try {
            db = DBOperator.getInstance().getDatabase();

            // Prepare attendee arguments with the generated ID
            String[] attendeeArgs = getAttendeeArgs(true);

            // Execute the AddAttendee SQL command using DBOperator
            String sql = SQLCommand.AddAttendee;
            DBOperator.getInstance().execSQL(sql, attendeeArgs);

            // Generate a new attendee ID (if applicable) or retrieve the next ID
            Cursor cursor = db.rawQuery("SELECT last_insert_rowid()", null);
            if (cursor.moveToFirst()) {
                newAttendeeId = cursor.getInt(0);
            }
            cursor.close();

            if (newAttendeeId == -1) {
                throw new Exception("Failed to generate a new Attendee ID.");
            }

            // Add registration using the new AttendID
            Log.d("FirstClassActivity", "New Attendee ID: " + newAttendeeId);
            addRegistration(newAttendeeId);

            // Navigate to Thank You screen
            Intent intent = new Intent(this, ThankYouActivity.class);
            startActivity(intent);

        } catch (Exception e) {
            Log.e("FirstClassActivity", "Error inserting attendee: " + e.getMessage());
            Toast.makeText(this, "Failed to add attendee. Please try again.", Toast.LENGTH_LONG).show();
        } finally {

        }
    }

    private void addRegistration(int attendeeId) {
        String[] regArgs = getRegistrationArgs(attendeeId);

        SQLiteDatabase db = null;
        try {
            // Execute the AddReg SQL command using DBOperator
            String sql = SQLCommand.AddReg;
            DBOperator.getInstance().execSQL(sql, regArgs);
            Log.d("FirstClassActivity", "Registration added successfully for Attendee ID: " + attendeeId);

        } catch (Exception e) {
            Log.e("FirstClassActivity", "Error adding registration: " + e.getMessage());
            Toast.makeText(this, "Failed to register attendee. Please try again.", Toast.LENGTH_LONG).show();
        } finally {
        }
    }

    /**
     * Get input data including stdID, book Callnum, date and returned state
     *
     * @param isNewAttendee
     * @return
     */
    private String[] getAttendeeArgs(boolean isNewAttendee) {
        String[] args = null;
        if (isNewAttendee) {
            args = new String[5];
            // FirstName
            args[0] = firstnameEdit.getText().toString();
            // LastName
            args[1] = lastnameEdit.getText().toString();
            // Phone
            args[2] = addressEdit.getText().toString();
            // Email
            args[3] = emailEdit.getText().toString();
            // DOB
            int year = DOBdate.getYear();
            int month = DOBdate.getMonth();
            int day = DOBdate.getDayOfMonth();
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);
            // format the date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            args[4] = dateFormat.format(calendar.getTime());

        }
        return args;
    }

    private String[] getRegistrationArgs(int attendeeId) {
        String[] args = new String[3];
        // ClassId
        args[0] = classIdEdit.getText().toString();
        // AttendId
        args[1] = String.valueOf(attendeeId);
        // Status
        args[2] = "Confirmed";
        return args;
    }
}