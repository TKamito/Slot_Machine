package com.thiago.slotmachine;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    //Tempo que nosso splashscreen ficará visivel
    private final int SPLASH_DISPLAY_LENGTH = 3500;

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //Executando o método que iniciará nossa animação
        carregar();
    }

    private void carregar() {

        Animation anim = AnimationUtils.loadAnimation(this,
                R.anim.animacao_splash);
        anim.reset();

        //Pegando o nosso objeto criado no layout
        ImageView iv = (ImageView) findViewById(R.id.ivLogo);
        if (iv != null) {
            iv.clearAnimation();
            iv.startAnimation(anim);
        }

        new Handler().postDelayed(new Runnable() {

            @Override

            public void run() {

                // Após o tempo definido irá executar a próxima tela
                Intent intent = new Intent(SplashActivity.this, SplashActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
