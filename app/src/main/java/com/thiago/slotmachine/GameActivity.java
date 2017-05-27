package com.thiago.slotmachine;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    private ImageView ivSlot1, ivSlot2, ivSlot3;
    private Roda slot1, slot2, slot3;
    private Button btPlay;

    private RelativeLayout background;

    private TextView tvNome, tvFichas, tvTimer;

    private int fichas;

    private int count = 0;

    public static final Random RANDOM = new Random();

    public static long randomLong(long lower, long upper) {
        return lower + RANDOM.nextLong() * (upper - lower);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ivSlot1 = (ImageView) findViewById(R.id.ivSlot1);
        ivSlot2 = (ImageView) findViewById(R.id.ivSlot2);
        ivSlot3 = (ImageView) findViewById(R.id.ivSlot3);

        tvNome = (TextView) findViewById(R.id.tvNomeGame);
        tvFichas = (TextView) findViewById(R.id.tvQtdFichas);
        tvTimer = (TextView) findViewById(R.id.timer);

        btPlay = (Button) findViewById(R.id.btPlay);

        String nome = getIntent().getStringExtra("NOME");
        tvNome.setText(nome);

        String QuantFichas = getIntent().getStringExtra("QUANTICHAS");
        fichas = Integer.parseInt(QuantFichas);
        tvFichas.setText(String.valueOf(fichas));

        background = (RelativeLayout) findViewById(R.id.activity_game);

        boolean masq = getIntent().getExtras().getBoolean("MASQ");
        boolean femin = getIntent().getExtras().getBoolean("FEMIN");

        if (masq){
            background.setBackgroundResource(R.drawable.male);
        }

        if (femin){
            background.setBackgroundResource(R.drawable.girl);
        }

        Timer T = new Timer();
        T.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        tvTimer.setText("tempo: " + count);
                        count++;
                    }
                });
            }
        }, 1000, 1000);


    }

    public void play() {

        tvFichas.setText(String.valueOf(fichas));

        if(fichas > 0){

            fichas--;

            rodarSlot1();
            rodarSlot2();
            rodarSlot3();

            btPlay.setEnabled(false);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    slot1.pararDeRodar();
                    slot2.pararDeRodar();
                    slot3.pararDeRodar();

                    exibeResultado();

                    btPlay.setEnabled(true);
                }
            }, 3000);

        } else {
            Toast.makeText(this, "Acabaram suas fichas :(", Toast.LENGTH_LONG).show();

        }

    }

    private void exibeResultado() {
        if (slot1.indiceAtual == slot2.indiceAtual && slot2.indiceAtual == slot3.indiceAtual) {
            Toast.makeText(this, "YOU WIN!", Toast.LENGTH_SHORT).show();
        } else if (slot1.indiceAtual == slot2.indiceAtual || slot2.indiceAtual == slot3.indiceAtual || slot1.indiceAtual == slot3.indiceAtual) {
            Toast.makeText(this, "SMALL AWARDS!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "YOU LOSE!", Toast.LENGTH_SHORT).show();
        }
    }

    private void rodarSlot1() {
        slot1 = new Roda(new Roda.RodaListener() {
            @Override
            public void novaImagem(final int resourceID) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ivSlot1.setImageResource(resourceID);
                    }
                });
            }
        }, 200, randomLong(0, 200));
        slot1.start();
    }

    private void rodarSlot2() {
        slot2 = new Roda(new Roda.RodaListener() {
            @Override
            public void novaImagem(final int resourceID) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ivSlot2.setImageResource(resourceID);
                    }
                });
            }
        }, 200, randomLong(150, 400));
        slot2.start();
    }

    private void rodarSlot3() {
        slot3 = new Roda(new Roda.RodaListener() {
            @Override
            public void novaImagem(final int resourceID) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ivSlot3.setImageResource(resourceID);
                    }
                });
            }
        }, 200, randomLong(300, 600));
        slot3.start();
    }
}
