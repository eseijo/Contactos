package com.example.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class CursorLoaderMain extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cursorloader_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.contactos, menu);
        return true;
    }

}
