package com.example.mis571_finalproject;

import com.example.mis571_finalproject.util.DBOperator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends android.app.Activity implements OnClickListener{

    Button StudentBtn,InstructorBtn, AdminBtn;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        StudentBtn=(Button)this.findViewById(R.id.StudentButton);
        StudentBtn.setOnClickListener(this);
        InstructorBtn=(Button)this.findViewById(R.id.InstructorButton);
        InstructorBtn.setOnClickListener(this);
        AdminBtn=(Button)this.findViewById(R.id.AdminButton);
        AdminBtn.setOnClickListener(this);
        //copy database file
        //try{
            //DBOperator.copyDB(getBaseContext());
        //}catch(Exception e){
           // e.printStackTrace();
        //}
    }

    public void onClick(View v)
    {
        int id=v.getId();
        if (id==R.id.StudentButton){
            Intent intent = new Intent(this, SchedActivity.class);
            this.startActivity(intent);

        }else if (id==R.id.InstructorButton){
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);

        }else if (id==R.id.AdminButton) {
            Intent intent = new Intent(this, ProfitActivity.class);
            this.startActivity(intent);
        }
        }
    }
//}

