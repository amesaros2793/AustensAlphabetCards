/********************************************************
 * FlashCardActivity.java
 * Austen Mesaros
 *
 * This class powers the flash card activity
 *********************************************************/
package com.example.austensalphabetcards;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FlashCardActivity extends AppCompatActivity {

    // Japanese Katakana Unicode Characters
    private final static char[] KATAKANA =
            {'\u30A2', '\u30A4', '\u30A6', '\u30A8', '\u30AA', '\u30AB', '\u30AC', '\u30AD', '\u30AE', '\u30AF',
             '\u30B0', '\u30B1', '\u30B2', '\u30B3', '\u30B4', '\u30B5', '\u30B6', '\u30B7', '\u30B8', '\u30B9',
             '\u30BA', '\u30B9', '\u30BA', '\u30BB', '\u30BC', '\u30BC', '\u30BD', '\u30BE', '\u30BF', '\u30BF',
             '\u30C0', '\u30C1', '\u30C2', '\u30C4', '\u30C5', '\u30C6', '\u30C7', '\u30C8', '\u30C9', '\u30CA',
             '\u30CB', '\u30CC', '\u30CD', '\u30CE', '\u30CF', '\u30D0', '\u30D1', '\u30D2', '\u30D3', '\u30D4',
             '\u30D5', '\u30D6', '\u30D7', '\u30D8', '\u30D9', '\u30DA', '\u30DB', '\u30DC', '\u30DD', '\u30DE',
             '\u30DF', '\u30E0', '\u30E2', '\u30E4', '\u30E6', '\u30E8', '\u30E9', '\u30EA', '\u30EB', '\u30EC',
             '\u30ED', '\u30EF', '\u30F1', '\u30F2', '\u30F3', '\u30F4', '\u30F7', '\u30F9', '\u30FA'};

    // Japanese Hiragana Unicode Characters
    private final static char[] HIRAGANA =
            {'\u3042', '\u3044', '\u3046', '\u3048', '\u304A', '\u304B', '\u304C', '\u304D', '\u304E', '\u304F',
             '\u3050', '\u3051', '\u3052', '\u3053', '\u3054', '\u3055', '\u3056', '\u3057', '\u3058', '\u3059',
             '\u305A', '\u305B', '\u305C', '\u305D', '\u305E', '\u305F', '\u3060', '\u3061', '\u3062', '\u3064',
             '\u3065', '\u3066', '\u3067', '\u3068', '\u3069', '\u306A', '\u306B', '\u306C', '\u306D', '\u306E',
             '\u306F', '\u3080', '\u3081', '\u3082', '\u3083', '\u3084', '\u3085', '\u3086', '\u3087', '\u3088',
             '\u3089', '\u308A', '\u308B', '\u308C', '\u308D', '\u308E', '\u308F', '\u3090', '\u3091', '\u3092',
             '\u3093', '\u3094', '\u3095', '\u3096'};

    // Views
    private TextView currentSymbol;
    private TextView alphabetName;
    private EditText inputEditText;
    private String languageMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card);

        // get language mode
        languageMode = getIntent().getStringExtra("LANGUAGE_MODE");

        // acquire views
        currentSymbol = findViewById(R.id.symbol_container);
        inputEditText = findViewById(R.id.answer);
        alphabetName = findViewById(R.id.alphabet_name);

        // set view properties
        inputEditText.setFilters(new InputFilter[] {new InputFilter.AllCaps(), new InputFilter.LengthFilter(5)});

        // pick starting symbol
        pickSymbol(languageMode);
    }

    public void pickSymbol(String languageMode)
    {
        int randomSymbol;
        switch(languageMode) {
            case "JP_Katakana":
                // pick a random symbol
                randomSymbol = (int) (Math.random() * KATAKANA.length);

                // set the current display symbol to the random symbol
                currentSymbol.setText(String.format("%s", KATAKANA[randomSymbol]));

                // update the alphabet label (for mixed alphabet mode)
                alphabetName.setText(R.string.katakana_label);
                break;
            case "JP_Hiragana":
                // pick a random symbol
                randomSymbol = (int) (Math.random() * HIRAGANA.length);

                // set the current display symbol to the random symbol
                currentSymbol.setText(String.format("%s", HIRAGANA[randomSymbol]));

                // update the alphabet label (for mixed alphabet mode)
                alphabetName.setText(R.string.hiragana_label);
                break;
        }
    }

    // method for enter answer button
    public void enterAnswer(View view)
    {
        // focus on the text input box
        inputEditText.requestFocus();

        // activate the keyboard
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(inputEditText, InputMethodManager.SHOW_FORCED);
    }

    public void checkAnswer()
    {
        View view;
        String charName = Character.getName(currentSymbol.getText().toString().charAt(0));
        String[] charNameParts;

        if(charName != null) {
            // split charName and store into charNameParts
            charNameParts = charName.split(" ");
        }
        else
        {
            // in case the character doesn't exist, store "A" in charNameParts
            charNameParts = new String[] {"A"};
        }

        // if user input matches the name of the ASCII character
        if(inputEditText.getText().toString().equals(charNameParts[charNameParts.length - 1]))
        {
            // create a toast
            Toast toast = Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT);

            // acquire the toast
            view = toast.getView();

            // change toast color
            view.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);

            // display the toast
            toast.show();

            // pick a new symbol from the same language mode
            pickSymbol(languageMode);
        }
        else
        {
            // create a toast
            Toast toast = Toast.makeText(getApplicationContext(), "Incorrect!", Toast.LENGTH_SHORT);

            // acquire the toast
            view = toast.getView();

            // change toast color
            view.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);

            // display the toast
            toast.show();
        }

        // clear the text input box
        inputEditText.setText("");
    }
}
