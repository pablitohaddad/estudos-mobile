package io.github.pablitohaddad.conceitoslistview;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    ArrayAdapter<String> adapter;

    String [] array_nomes = {
            "Nome 1", "Nome 2", "Nome 3", "Nome 4", "Nome 5", "Nome 6", "Nome 7", "Nome 8",
            "Nome 1", "Nome 2", "Nome 3", "Nome 4", "Nome 5", "Nome 6", "Nome 7", "Nome 8",
            "Nome 1", "Nome 2", "Nome 3", "Nome 4", "Nome 5", "Nome 6", "Nome 7", "Nome 8",
            "Nome 1", "Nome 2", "Nome 3", "Nome 4", "Nome 5", "Nome 6", "Nome 7", "Nome 8",
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        // Adapter para mostrar os itens do array
         adapter = new ArrayAdapter<>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                array_nomes
        );
        // Definir o adapter no list view
        listView.setAdapter(adapter);
    }
}