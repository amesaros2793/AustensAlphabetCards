/********************************************************
 * JapaneseConfiguration.java
 * Austen Mesaros
 *
 * This class powers the Japanese language configuration page
 *********************************************************/
package com.example.austensalphabetcards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class JapaneseConfiguration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_japanese_configuration);
    }

    public void startFlashCardActivity(View view) {
        Intent intent = new Intent(this, FlashCardActivity.class);

        // pass along the language mode to the next activity
        String language_mode = view.getTag().toString();
        intent.putExtra("LANGUAGE_MODE", language_mode);

        startActivity(intent);
    }
}
