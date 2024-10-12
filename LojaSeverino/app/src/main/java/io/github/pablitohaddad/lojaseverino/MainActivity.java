package io.github.pablitohaddad.lojaseverino;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import io.github.pablitohaddad.lojaseverino.categoria.Categoria;
import io.github.pablitohaddad.lojaseverino.produto.Produto;
import io.github.pablitohaddad.lojaseverino.produto.ProdutoAdapter;
import io.github.pablitohaddad.lojaseverino.repositorio.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    // Componentes da interface
    private EditText etCategoriaNome, etProdutoNome, etProdutoPreco;
    private Button btnAdicionarCategoria, btnAdicionarProduto, btnVerProdutos;
    private Spinner spinnerCategoria;
    private RecyclerView recyclerViewProdutos;
    private ProdutoAdapter produtoAdapter;
    private DatabaseHelper dbHelper;

    // Lista que armazena as categorias carregadas do banco de dados
    private ArrayList<Categoria> listaCategorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa os componentes da interface
        etCategoriaNome = findViewById(R.id.etCategoriaNome);
        etProdutoNome = findViewById(R.id.etProdutoNome);
        etProdutoPreco = findViewById(R.id.etProdutoPreco);
        btnAdicionarCategoria = findViewById(R.id.btnAdicionarCategoria);
        btnAdicionarProduto = findViewById(R.id.btnAdicionarProduto);
        btnVerProdutos = findViewById(R.id.btnVerProdutos);
        spinnerCategoria = findViewById(R.id.spinnerCategoria);
        recyclerViewProdutos = findViewById(R.id.recyclerViewProdutos);

        dbHelper = new DatabaseHelper(this);  // Inicializa o DatabaseHelper para acessar o banco de dados
        listaCategorias = new ArrayList<>();  // Inicializa a lista de categorias

        recyclerViewProdutos.setLayoutManager(new LinearLayoutManager(this));  // Configura o RecyclerView para usar um layout linear

        // Adiciona a funcionalidade do botão para adicionar uma nova categoria
        btnAdicionarCategoria.setOnClickListener(v -> {
            String categoriaNome = etCategoriaNome.getText().toString();  // Obtém o nome da categoria digitado pelo usuário
            if (!categoriaNome.isEmpty()) {  // Verifica se o campo não está vazio
                dbHelper.addCategoria(new Categoria(0, categoriaNome));  // Adiciona a nova categoria ao banco de dados
                Toast.makeText(MainActivity.this, "Categoria adicionada", Toast.LENGTH_SHORT).show();
                carregarCategoriasNoSpinner();  // Atualiza o spinner com as categorias disponíveis
                etCategoriaNome.setText("");  // Limpa o campo de texto
            }
        });

        // Adiciona a funcionalidade do botão para adicionar um novo produto
        btnAdicionarProduto.setOnClickListener(v -> {
            String produtoNome = etProdutoNome.getText().toString();  // Obtém o nome do produto digitado
            String precoStr = etProdutoPreco.getText().toString();  // Obtém o preço digitado
            int categoriaPosition = spinnerCategoria.getSelectedItemPosition();  // Obtém a posição da categoria selecionada no spinner

            if (!produtoNome.isEmpty() && !precoStr.isEmpty() && categoriaPosition >= 0) {  // Verifica se os campos estão preenchidos
                Categoria categoriaSelecionada = listaCategorias.get(categoriaPosition);  // Obtém a categoria selecionada com base na posição
                double preco = Double.parseDouble(precoStr);  // Converte o preço para double
                dbHelper.addProduto(new Produto(0, produtoNome, preco, categoriaSelecionada));  // Adiciona o novo produto ao banco de dados
                Toast.makeText(MainActivity.this, "Produto adicionado", Toast.LENGTH_SHORT).show();
                etProdutoNome.setText("");  // Limpa o campo de nome do produto
                etProdutoPreco.setText("");  // Limpa o campo de preço do produto
            }
        });

        // Adiciona a funcionalidade do botão para exibir os produtos cadastrados
        btnVerProdutos.setOnClickListener(v -> carregarProdutosNaRecyclerView());
        carregarCategoriasNoSpinner();  // Carrega as categorias para o spinner na inicialização da tela
    }

    // Método para carregar as categorias no spinner
    private void carregarCategoriasNoSpinner() {
        Cursor cursor = dbHelper.getAllCategorias();  // Obtém todas as categorias do banco de dados
        listaCategorias.clear();  // Limpa a lista de categorias para evitar duplicações
        ArrayList<String> nomesCategorias = new ArrayList<>();  // Lista de nomes de categorias para exibir no spinner

        while (cursor.moveToNext()) {
            @SuppressLint("Range") long id = cursor.getLong(cursor.getColumnIndex("id"));  // Obtém o ID da categoria
            @SuppressLint("Range") String nome = cursor.getString(cursor.getColumnIndex("nome"));  // Obtém o nome da categoria
            Categoria categoria = new Categoria(id, nome);  // Cria um objeto Categoria
            listaCategorias.add(categoria);  // Adiciona a categoria à lista completa
            nomesCategorias.add(nome);  // Adiciona apenas o nome para exibir no spinner
        }

        // Configura o ArrayAdapter para exibir as categorias no spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nomesCategorias);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adapter);  // Associa o adaptador ao spinner
    }

    // Método para carregar os produtos cadastrados e exibi-los no RecyclerView
    private void carregarProdutosNaRecyclerView() {
        Cursor cursor = dbHelper.getAllProdutos();  // Obtém todos os produtos e suas categorias associadas

        if (cursor.getCount() == 0) {  // Verifica se há produtos cadastrados
            Toast.makeText(MainActivity.this, "Nenhum produto encontrado", Toast.LENGTH_SHORT).show();
        } else {
            ArrayList<Produto> produtos = new ArrayList<>();  // Lista para armazenar os produtos carregados
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String nome = cursor.getString(cursor.getColumnIndex("nome"));  // Obtém o nome do produto
                @SuppressLint("Range") double preco = cursor.getDouble(cursor.getColumnIndex("preco"));  // Obtém o preço do produto
                @SuppressLint("Range") String categoriaNome = cursor.getString(cursor.getColumnIndex("categoria_nome"));  // Obtém o nome da categoria associada

                Categoria categoria = new Categoria(0, categoriaNome);  // Cria uma categoria com o nome obtido
                Produto produto = new Produto(0, nome, preco, categoria);  // Cria um produto com os dados obtidos
                produtos.add(produto);  // Adiciona o produto à lista
            }

            // Configura o adapter e associa a lista de produtos ao RecyclerView
            produtoAdapter = new ProdutoAdapter(this, produtos);
            recyclerViewProdutos.setAdapter(produtoAdapter);
            produtoAdapter.notifyDataSetChanged();  // Notifica o adapter que os dados foram alterados
        }
    }
}