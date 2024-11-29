package com.example.mis571_finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class RegisterActivity extends Activity implements OnClickListener {

    Button firstclassBtn, signupBtn, mainBtn;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        //copy database file
        //try{
        // DBOperator.copyDB(getBaseContext());
        // }catch(Exception e){
        // e.printStackTrace();
        //}
        firstclassBtn = (Button) this.findViewById(R.id.FirstClassButton);
        firstclassBtn.setOnClickListener(this);
        signupBtn = (Button) this.findViewById(R.id.SignUpButton);
        signupBtn.setOnClickListener(this);
        mainBtn = (Button) this.findViewById(R.id.GoMain3Button);
        mainBtn.setOnClickListener(this);
    }
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.FirstClassButton) {
            Intent intent = new Intent(this, FirstClassActivity.class);
            this.startActivity(intent);

        } else if (id == R.id.SignUpButton) {
            Intent intent = new Intent(this, ThankYouActivity.class);
            this.startActivity(intent);
        } else if (id == R.id.GoMain3Button) {
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
        }
    }
}
