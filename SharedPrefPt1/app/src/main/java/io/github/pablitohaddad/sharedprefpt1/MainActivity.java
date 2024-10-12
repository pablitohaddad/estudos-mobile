package io.github.pablitohaddad.sharedprefpt1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    Button button;

    // DECLACACAO DOS OBJ - SHAREDPREFERENCES
    //CLASSE SHARED
    SharedPreferences sharedPreferences;
    //CLASSE EDITOR
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editEntrada);
        textView = findViewById(R.id.tvDado);
        button = findViewById(R.id.button);

        // Obter a instancia de shared preferences com a chave definida
        sharedPreferences = getSharedPreferences(getString(R.string.key), MODE_PRIVATE);

        // Recupera o valor salvo anteriormente no sharedpreferences usando a chave
        String donoCasa = sharedPreferences.getString(getString(R.string.key), "");

        textView.setText("Bem vindo " + donoCasa);

        // Definicao da acao a ser inicializada pelo botao

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Inicializa o editor para usar o shared
                editor = sharedPreferences.edit();

                // o editor salva o conteudo do edit texto no sharedpreferences
                // usando a chave definida anteriormente
                editor.putString(getString(R.string.key), editText.getText().toString());

                //confirmar a gravacao das mudancas no sharedpreferences
                editor.apply();

                textView.setText("Bem vindo " + editText.getText().toString());

            }
        });


    }
}