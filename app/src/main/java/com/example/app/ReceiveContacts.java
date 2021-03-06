package com.example.app;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Contacts.Intents;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ReceiveContacts extends Activity implements View.OnClickListener {

    ArrayList<String> rec = new ArrayList<String>();

    TextView tvName;
    TextView tvNumber;
    TextView tvEmail;
    TextView tvNameT;
    TextView tvNumberT;
    TextView tvEmailT;

    Button butSave;
    int i = 2;

    //ImageView iv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragm_receive);

        rec.add("Nuevo");
        rec.add("(697) 789-356");
        rec.add("(697) 789-456");
        rec.add("edgr.seijo@gmail.com");
        rec.add("edgar.seijo@gmail.com");
        rec.add("edgr.seij@gmail.com");

        tvName = (TextView) findViewById(R.id.tv_name);
        tvNumber = (TextView) findViewById(R.id.tv_number);
        tvEmail = (TextView) findViewById(R.id.tv_email);
        tvNameT = (TextView) findViewById(R.id.tv_nameT);
        tvNumberT = (TextView) findViewById(R.id.tv_numberT);
        tvEmailT = (TextView) findViewById(R.id.tv_emailT);

        butSave = (Button) findViewById(R.id.but_save);
        butSave.setOnClickListener(this);

        //iv = (ImageView) getView().findViewById(R.id.imageView);
        //iv.setImageURI();

        tvName.setText(rec.get(0));

        String numbers = rec.get(1);
        while (!rec.get(i).contains("@")){
            numbers = numbers+"\n"+rec.get(i);
            i++;
        }
        tvNumber.setText(numbers);

        String emails = rec.get(i);
        for(int x=i+1;x<rec.size();x++){
            emails = emails+"\n"+rec.get(x);
        }
        tvEmail.setText(emails);
    }

    @Override
    public void onClick(View view) {

        // Creates a new Intent to insert a contact
        Intent intent = new Intent(Intents.Insert.ACTION);
        // Sets the MIME type to match the Contacts Provider
        intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

        String phoneT;
        String phoneN;
        String emailN;

        for(int x = 1;x<i;x++){

            phoneT = x==1 ? Intents.Insert.PHONE_TYPE : x==2 ? Intents.Insert.SECONDARY_PHONE_TYPE : Intents.Insert.TERTIARY_PHONE_TYPE;
            phoneN = x==1 ? Intents.Insert.PHONE : x==2 ? Intents.Insert.SECONDARY_PHONE : Intents.Insert.TERTIARY_PHONE;

            if(rec.get(x).startsWith("(6")){
                intent.putExtra(phoneT, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
            }else if (rec.get(x).startsWith("(9")){
                intent.putExtra(phoneT, ContactsContract.CommonDataKinds.Phone.TYPE_HOME);
            } else{
                intent.putExtra(phoneT, ContactsContract.CommonDataKinds.Phone.TYPE_OTHER);
            }
            intent.putExtra(phoneN, rec.get(x));
        }

        for(int x=i;x<rec.size();x++){
            emailN = (rec.size()-x)==1 ? Intents.Insert.TERTIARY_EMAIL : (rec.size()-x)==2 ? Intents.Insert.SECONDARY_EMAIL : Intents.Insert.EMAIL;
            intent.putExtra(emailN, rec.get(x));
        }
        startActivity(intent);
    }


}
