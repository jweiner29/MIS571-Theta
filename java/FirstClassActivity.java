package com.example.mis571_finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FirstClassActivity extends Activity implements OnClickListener {

    Button joinBtn, mainBtn;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstclass);
        //copy database file
        //try{
        // DBOperator.copyDB(getBaseContext());
        // }catch(Exception e){
        // e.printStackTrace();
        //}
        joinBtn = (Button) this.findViewById(R.id.JoinButton);
        joinBtn.setOnClickListener(this);
        mainBtn = (Button) this.findViewById(R.id.GoMain4Button);
        mainBtn.setOnClickListener(this);
    }
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.JoinButton) {
            Intent intent = new Intent(this, ThankYouActivity.class);
            this.startActivity(intent);
        } else if (id == R.id.GoMain4Button) {
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
        }
    }
}
