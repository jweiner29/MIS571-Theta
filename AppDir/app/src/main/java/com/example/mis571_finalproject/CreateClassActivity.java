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

public class CreateClassActivity extends Activity implements OnClickListener {
    EditText editClID, editDate, editTime, editSpots,editAddress, editCT, editInst;
    Button createBtn, mainBtn;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_class);

        //Initialize UI components
        editClID = (EditText) this.findViewById(R.id.editClassID);
        editDate = (EditText) this.findViewById(R.id.editDate);
        editTime = (EditText) this.findViewById(R.id.editTime);
        editSpots = (EditText) this.findViewById(R.id.editSpots);
        editAddress = (EditText) this.findViewById(R.id.editAddress);
        editCT = (EditText) this.findViewById(R.id.editCTID);
        editInst = (EditText) this.findViewById(R.id.editInstID);
        createBtn = (Button) this.findViewById(R.id.AddClassButton);
        mainBtn = (Button) this.findViewById(R.id.GoMainButton);

        //set click listeners:
        createBtn.setOnClickListener(this);
        mainBtn.setOnClickListener(this);
    }
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.AddClassButton) {
                insertNewClass();
                //insertNewClass();  // Handle the Add Class Button click

            } else if (id == R.id.GoMainButton) {
                Intent intent = new Intent(this, MainActivity.class);
                this.startActivity(intent);
            }
        }



    private void insertNewClass() {

        String[] classArgs = getClassArgs();
        SQLiteDatabase db = null;
        try {
            // Execute the AddClass SQL command using DBOperator
            String sql = SQLCommand.AddClass;
            DBOperator.getInstance().execSQL(sql, classArgs);

            // Navigate to Thank You screen
            Intent intent = new Intent(this, CreateSuccessActivity.class);
            startActivity(intent);

        } catch (Exception e) {
            Log.e("CreateClassActivity", "Error inserting class: " + e.getMessage());
            Toast.makeText(this, "Failed to create. Please try again.", Toast.LENGTH_LONG).show();
        }
    }

    private String[] getClassArgs() {
        String[] ClassArgs = new String[7];
        // ClID
        ClassArgs[0] = editClID.getText().toString();
        // ClassDate
        ClassArgs[1] = editDate.getText().toString();
        // ClassTime
        ClassArgs[2] = editTime.getText().toString();
        // Spots
        ClassArgs[3] = editSpots.getText().toString();
        // Address
        ClassArgs[4] = editAddress.getText().toString();
        // CTID
        ClassArgs[5] = editCT.getText().toString();
        // InstID
        ClassArgs[6] = editInst.getText().toString();
        return ClassArgs;
    }
}
