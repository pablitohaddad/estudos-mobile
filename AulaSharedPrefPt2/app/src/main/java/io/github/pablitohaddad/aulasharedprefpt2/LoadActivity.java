package io.github.pablitohaddad.aulasharedprefpt2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class LoadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        TextView nomeView, emailView, opcaoView, stopView;
        nomeView = findViewById(R.id.textView1);
        emailView = findViewById(R.id.textView2);
        opcaoView = findViewById(R.id.textView3);
        stopView = findViewById(R.id.textView4);

        SharedPreferences sp = getApplicationContext().getSharedPreferences("preferences", MODE_PRIVATE);

    }
}