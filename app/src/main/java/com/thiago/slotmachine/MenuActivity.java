package com.thiago.slotmachine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button btInfo, btGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btInfo = (Button) findViewById(R.id.btInfo);
        btGame = (Button) findViewById(R.id.btGame);

        btGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, CharacterActivity.class);
                startActivity(intent);
            }
        });

        btInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, InfoActivity.class);
                startActivity(intent);
            }
        });
    }
}
