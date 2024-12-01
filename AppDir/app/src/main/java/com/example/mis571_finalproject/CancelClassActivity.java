package com.example.mis571_finalproject;

import android.app.DatePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
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
import com.example.mis571_finalproject.util.DBOpenHelper;
import com.example.mis571_finalproject.util.DBOperator;
import com.example.mis571_finalproject.view.TableView;

import java.io.IOException;

public class CancelClassActivity extends Activity implements OnClickListener {
    EditText editClId;
    Button deleteBtn, mainBtn;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cancel_class);

        //Initialize UI components
        editClId = (EditText) this.findViewById(R.id.editClID);
        deleteBtn = (Button) this.findViewById(R.id.DeleteButton);
        mainBtn = (Button) this.findViewById(R.id.GoMainButton);

        //set click listeners:
        deleteBtn.setOnClickListener(this);
        mainBtn.setOnClickListener(this);
    }
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.DeleteButton) {
            deleteClass();  // Handle the Delete Class Button click

        } else if (id == R.id.GoMainButton) {
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
        }
    }
    private void deleteClass() {
        String[] classArgs = getClassArgs();

        // Validate input
        if (classArgs[0] == null || classArgs[0].isEmpty()) {
            Toast.makeText(this, "Please enter a Class ID.", Toast.LENGTH_LONG).show();
            return;
        }

        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = DBOperator.getInstance().getDatabase();

            // Check if there are any registrations for the given ClID
            String checkRegSql = "SELECT COUNT(*) FROM Registration WHERE ClID = ?";
            cursor = db.rawQuery(checkRegSql, classArgs);

            if (cursor.moveToFirst() && cursor.getInt(0) > 0) {
                // Registrations exist, delete registrations first
                String deleteRegSql = SQLCommand.DeleteReg;
                DBOperator.getInstance().execSQL(deleteRegSql, classArgs);
                Log.d("CancelClassActivity", "Registrations deleted for ClID: " + classArgs[0]);
            }

            // Delete the class regardless of whether registrations existed
            String deleteClassSql = SQLCommand.DeleteClass;
            DBOperator.getInstance().execSQL(deleteClassSql, classArgs);
            Log.d("CancelClassActivity", "Class deleted for ClID: " + classArgs[0]);

            // Navigate to success screen
            Intent intent = new Intent(this, CancelSuccessActivity.class);
            startActivity(intent);
            finish(); // Finish this activity to prevent navigation back

        } catch (Exception e) {
            Log.e("CancelClassActivity", "Error deleting class or registrations: ", e);
            Toast.makeText(this, "Failed to delete. Please try again.", Toast.LENGTH_LONG).show();
        } finally {
            // Close the cursor and database to free resources
            if (cursor != null) {
                cursor.close();
            }
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
    }


    private String[] getClassArgs() {
        // Prepare arguments for the SQL commands
        return new String[]{editClId.getText().toString().trim()};
    }
}
