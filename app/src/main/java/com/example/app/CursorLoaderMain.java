package com.example.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.internal.widget.NativeActionModeAwareLayout;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CursorLoaderMain extends Activity implements View.OnClickListener{

    Button butSel;
    Button butRec;
    TextView tvNameT;
    TextView tvNumberT;
    TextView tvEmailT;
    TextView tvName;
    TextView tvNumber;
    TextView tvEmail;
    TextView tvSend;

    int i = 2;

    ArrayList<String> selected;

    protected static final int REQUEST_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cursorloader_main);

        butSel = (Button) findViewById(R.id.but_sel);
        butSel.setOnClickListener(this);
        butRec = (Button) findViewById(R.id.but_rec);
        butRec.setOnClickListener(this);

        tvNameT = (TextView) findViewById(R.id.tv_nameT);
        tvNumberT = (TextView) findViewById(R.id.tv_numberT);
        tvEmailT = (TextView) findViewById(R.id.tv_emailT);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvNumber = (TextView) findViewById(R.id.tv_number);
        tvEmail = (TextView) findViewById(R.id.tv_email);
        tvSend = (TextView) findViewById(R.id.tv_send);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.contactos, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v == butSel){
            Intent i = new Intent(this, CursorLoaderList.class);
            startActivityForResult(i, REQUEST_CODE);
        } else if (v == butRec){
            Intent i = new Intent(this, ReceiveContacts.class);
            startActivity(i);
        }
    }

    public void onActivityResult(int requestCode,int resultCode,Intent data){
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            selected = data.getStringArrayListExtra("res");
            tvSend.setVisibility(View.VISIBLE);
            tvNumberT.setVisibility(View.VISIBLE);
            tvNameT.setVisibility(View.VISIBLE);
            tvEmailT.setVisibility(View.VISIBLE);

            tvName.setText(selected.get(0));

            String numbers = selected.get(1);
            String emails = "";
            if(i<selected.size()){
                while (!selected.get(i).contains("@")){
                    numbers = numbers+"\n"+selected.get(i);
                    i++;
                }
                emails = selected.get(i);
                for(int x=i+1;x<selected.size();x++){
                    emails = emails+"\n"+selected.get(x);
                }
            }
            tvNumber.setText(numbers);
            tvEmail.setText(emails);
        }
    }
}
