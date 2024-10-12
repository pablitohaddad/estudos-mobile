package io.github.pablitohaddad.aulasharedprefpt2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    EditText nome, email;
    Button buttonSalva, buttonCarrega;
    RadioButton radioButtonOpcao;

    Switch switchParar;
    SharedPreferences sp;
    String nomeString, emailString;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = findViewById(R.id.mNome);
        email = findViewById(R.id.mEmail);
        buttonSalva = findViewById(R.id.mSalvar);
        buttonCarrega = findViewById(R.id.mCarregar);
        radioButtonOpcao = findViewById(R.id.radioButtonAprender);
        switchParar = findViewById(R.id.switch1);

        sp = getSharedPreferences("preferences", MODE_PRIVATE);

        // Verificacao do switch
        boolean isChecked = sp.getBoolean("switchState", false);
        switchParar.setChecked(isChecked);

        // Verifica a mudanca de estado do switch
        switchParar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                // Salvar o novo estado do switch quando ele eh alterado
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("switchState", isChecked);
                editor.commit();
            }
        });
    }
}