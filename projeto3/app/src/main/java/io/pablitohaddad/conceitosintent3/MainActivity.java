package io.pablitohaddad.conceitosintent3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // DECLARACAO DAS VARIAVEIS PARA OS ELEMENTOS
    // DA INTERFACE DO USUARIO --> xml

    private EditText nomeEdit, emailEdit, phoneEdit;

    private Button enviarBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // DEFINIR O LAYOUT DA ACTIVITY
        setContentView(R.layout.activity_main);
        // ASSOCIAR AS VARIAVEIS AOS ELEMENTOS DA INTERFACE COM BASE EM SEUS id`s
        nomeEdit = findViewById(R.id.edtNome);
        emailEdit = findViewById(R.id.edtEmail);
        phoneEdit = findViewById(R.id.edtPhone);
        enviarBtn = findViewById(R.id.btnEnviar);

        // DEFINIR UM EVENTO DE CLIQUE DO BOTAO
        // DEFINIR UM LISTENER PARA BOTAO "enviarBtn"
        enviarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // CRIAR UM NOVO INTENT PARA INICIAR A ACTIVITY "ListaActivity"
                Intent intencao = new Intent(getApplicationContext(), ListaActivity.class);
                intencao.putExtra("nome", nomeEdit.getText().toString());
                intencao.putExtra("email", emailEdit.getText().toString());
                intencao.putExtra("phone", phoneEdit.getText().toString());
                // INICIALIZAR A LISTA ACTIVITY
                startActivity(intencao);
            }
        });


    }
}