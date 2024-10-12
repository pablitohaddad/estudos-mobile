package io.pablitohaddad.conceitosintent3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ListaActivity extends AppCompatActivity {

    private TextView nomeTv, emailTv, phoneTv;

    private Button voltarBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nomeTv = findViewById(R.id.tvNome);
        emailTv = findViewById(R.id.tvEmail);
        phoneTv = findViewById(R.id.tvPhone);
        voltarBtn = findViewById(R.id.btnVoltar);
        setContentView(R.layout.activity_lista);

        // RECEBER INTENCOES
        Intent recebeIntent = getIntent();
        String nomeLocal = recebeIntent.getStringExtra("nome");
        String emailLocal = recebeIntent.getStringExtra("email");
        String phoneLocal = recebeIntent.getStringExtra("phone");

        nomeTv.setText("Nome: " + nomeLocal);
        emailTv.setText("Email: " + emailLocal);
        nomeTv.setText("Phone: " + phoneLocal);

        voltarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}