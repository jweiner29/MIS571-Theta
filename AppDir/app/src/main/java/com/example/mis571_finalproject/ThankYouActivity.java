package com.example.mis571_finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ThankYouActivity extends Activity implements OnClickListener {

    Button mainBtn;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thankyou);
        //copy database file
        //try{
        // DBOperator.copyDB(getBaseContext());
        // }catch(Exception e){
        // e.printStackTrace();
        //}
        mainBtn = (Button) this.findViewById(R.id.GoMain5Button);
        mainBtn.setOnClickListener(this);
    }
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.GoMain5Button) {
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);

        }
    }
}