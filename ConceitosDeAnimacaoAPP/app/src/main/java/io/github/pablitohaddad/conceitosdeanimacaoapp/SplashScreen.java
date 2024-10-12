package io.github.pablitohaddad.conceitosdeanimacaoapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreen extends AppCompatActivity {

    // CRIAR HANDLE
    Handler handler = new Handler();
    // CRIAR RUNNABLE
    Runnable runnable;

    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);

        lottieAnimationView = findViewById(R.id.lottie);
        lottieAnimationView
                .animate()
                .translationX(2000)
                .setDuration(2700)
                .setStartDelay(0);

        // DEFINIR O RUNNABLE COM A TAREFA QUE SERA EXECUTADA APOS O TIME (DELAY)

        runnable = new Runnable() {
            @Override
            public void run() {
                Intent intencao = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intencao);
                finish(); // "desempilha"
            }
        };

        // DEFINIR O TEMPO DE EXECUCAO
        handler.postDelayed(runnable, 3000);
    }
}