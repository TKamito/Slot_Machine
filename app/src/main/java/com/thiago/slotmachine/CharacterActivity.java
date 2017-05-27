package com.thiago.slotmachine;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class CharacterActivity extends AppCompatActivity {

    private Button btPlay;
    private EditText etUsuario;

    private String sexo, QuantFichas;

    private LinearLayout llBackground;

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        btPlay = (Button) findViewById(R.id.btPlay);
        etUsuario = (EditText) findViewById(R.id.etUsuario);
        llBackground = (LinearLayout) findViewById(R.id.activity_character);

        spinner = (Spinner) findViewById(R.id.spFichas);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.quant_fichas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                QuantFichas = spinner.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        QuantFichas = spinner.getSelectedItem().toString();

        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CharacterActivity.this, GameActivity.class);
                intent.putExtra("NOME", etUsuario.getText().toString());

                if (sexo == "Male"){
                    intent.putExtra("MASQ", true);
                } else if (sexo == "Girl"){
                    intent.putExtra("FEMIN", true);
                }

                intent.putExtra("QUANTFICHAS", QuantFichas);
                startActivity(intent);

            }
        });
    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.rbMale:
                if (checked)

                    llBackground.setBackgroundResource(R.drawable.male);
                sexo = "Male";

                break;
            case R.id.rbGirl:
                if (checked)

                    llBackground.setBackgroundResource(R.drawable.girl);
                sexo = "Girl";

                break;
        }
    }

}
