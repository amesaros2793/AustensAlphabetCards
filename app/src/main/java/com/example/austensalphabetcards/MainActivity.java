/********************************************************
 * MainActivity.java
 * Austen Mesaros
 *
 * This class powers the initial activity screen
 *********************************************************/
package com.example.austensalphabetcards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startJapaneseConfiguration(View view) {
        Intent intent = new Intent(this, JapaneseConfiguration.class);
        startActivity(intent);
    }
}
