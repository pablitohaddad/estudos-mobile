package br.com.yago.conceitossqlitept2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import android.database.Cursor;
import android.widget.ArrayAdapter;

public class MainActivity extends AppCompatActivity {

    EditText nome, cpf, telefone;
    Button btnInserir, btnConsultar;
    ListView listaUsuarios;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = findViewById(R.id.nome);
        cpf = findViewById(R.id.cpf);
        telefone = findViewById(R.id.telefone);
        btnInserir = findViewById(R.id.btnInserir);
        btnConsultar = findViewById(R.id.btnConsultar);
        listaUsuarios = findViewById(R.id.listViewUsers);
        db = new DBHelper(this);

        // Método para inserir dados
        btnInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeTXT = nome.getText().toString();
                String cpfTXT = cpf.getText().toString();
                String telefoneTXT = telefone.getText().toString();

                boolean inserirDados = db.insertUserData(nomeTXT, cpfTXT, telefoneTXT);
                if (inserirDados) {
                    Toast.makeText(MainActivity.this, "Dados inseridos com sucesso", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Falha ao inserir dados", Toast.LENGTH_SHORT).show();
                }
                limparCampos();
            }

            private void limparCampos() {
                nome.setText("");  // Limpa o campo de nome
                cpf.setText("");   // Limpa o campo de CPF
                telefone.setText("");  // Limpa o campo de telefone
            }
        });

        // Método para consultar dados
        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> listaDeUsuarios = new ArrayList<>();
                Cursor cursor = db.getData();

                // Verifica se o cursor contém dados
                if (cursor.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "Nenhum dado encontrado", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Itera sobre os dados do cursor e adiciona ao ArrayList
                while (cursor.moveToNext()) {
                    listaDeUsuarios.add("Nome: " + cursor.getString(0) + ", Telefone: " + cursor.getString(2));
                }

                // Configura o adaptador para exibir os dados no ListView
                ArrayAdapter<String> adaptador = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, listaDeUsuarios);
                listaUsuarios.setAdapter(adaptador);
                cursor.close();

            }

        });

    }
}

