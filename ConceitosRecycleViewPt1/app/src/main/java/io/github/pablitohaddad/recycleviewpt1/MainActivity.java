package io.github.pablitohaddad.recycleviewpt1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.carousel.CarouselLayoutManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // PASSO 1 - DECLARAR COMPONENTES DA CLASSE
    private RecyclerView recyclerView;
    private ArrayList<Integer> integerArrayList;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // PASSO 2 - INSTANCIAR O ARRAY LIST
        integerArrayList = new ArrayList<>();
        // PASSO 2 - INSTANCIAR RECYCLEVIEW E O ADAPTER
        recyclerView = findViewById(R.id.recycler_view);
        myAdapter = new MyAdapter(this, integerArrayList);

        // PASSO 4 - DEFINIR LAYOUTMANEGER
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // PASSO 5 - CONFIGURAR O ADAPTER NO RECYCLE VIEW
        recyclerView.setAdapter(myAdapter);

        // PASSO 6 - ADICIONAR OS ITENS NA LISTA
        integerArrayList.add(R.drawable.araras);
        integerArrayList.add(R.drawable.casal_onca);
        integerArrayList.add(R.drawable.jacare);
        integerArrayList.add(R.drawable.javare_zoom);
        integerArrayList.add(R.drawable.onca);
        integerArrayList.add(R.drawable.onca_praia);
        integerArrayList.add(R.drawable.por_sol);
        integerArrayList.add(R.drawable.rio);
        integerArrayList.add(R.drawable.sunset);
        integerArrayList.add(R.drawable.sunset_rio);
    }
}