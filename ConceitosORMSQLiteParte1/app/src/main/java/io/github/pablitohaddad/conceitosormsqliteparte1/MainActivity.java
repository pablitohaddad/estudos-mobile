package io.github.pablitohaddad.conceitosormsqliteparte1;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.github.pablitohaddad.conceitosormsqliteparte1.controller.ProdutoAdapter;
import io.github.pablitohaddad.conceitosormsqliteparte1.dao.ProdutoDAO;
import io.github.pablitohaddad.conceitosormsqliteparte1.database.AppDatabase;
import io.github.pablitohaddad.conceitosormsqliteparte1.model.Produto;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNome;
    private EditText editTextQuantidade;
    private AppDatabase bancoDeDados; // Instância do banco de dados.
    private ProdutoDAO produtoDao; // DAO para acessar a tabela de produtos.
    private ExecutorService executorService; // Executor para operações de banco de dados em background.
    private RecyclerView recyclerView; // RecyclerView para exibir a lista de produtos.
    private ProdutoAdapter produtoAdapter; // Adaptador para o RecyclerView.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa os componentes da interface
        editTextNome = findViewById(R.id.editTextNomeProduto);
        editTextQuantidade = findViewById(R.id.editTextQuantidade);
        recyclerView = findViewById(R.id.recyclerView);

        // Inicializa o banco de dados e o DAO
        bancoDeDados = AppDatabase.obterBancoDados(this);
        produtoDao = bancoDeDados.produtoDao();

        // Configura o RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        produtoAdapter = new ProdutoAdapter(List.of()); // Inicializa com lista vazia
        recyclerView.setAdapter(produtoAdapter);


        // Inicializa o ExecutorService com um único thread
        executorService = Executors.newSingleThreadExecutor();
        // Carrega produtos do banco de dados
        carregarProdutos();
    }

    // Método chamado quando o botão de adicionar produto é clicado
    public void salvar(View view) {
        final String nome = editTextNome.getText().toString().trim();
        final String quantidadeStr = editTextQuantidade.getText().toString().trim();

        // Valida a entrada do usuário
        if (nome.isEmpty() || quantidadeStr.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
            return; // Retorna sem adicionar o produto.
        }

        int quantidade;
        try {
            // Converte a quantidade para inteiro.
            quantidade = Integer.parseInt(quantidadeStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Quantidade inválida", Toast.LENGTH_SHORT).show();
            return;
        }
        // Cria um novo produto.
        Produto produto = new Produto(nome, quantidade);

        // Adiciona o produto ao banco de dados em uma thread separada
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                produtoDao.inserirProdutos(produto); // Insere o novo produto.
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Produto cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                        // Limpa os campos após salvar
                        editTextNome.setText("");
                        editTextQuantidade.setText("");

                        // Recarrega os produtos
                        carregarProdutos();
                    }
                });
            }
        });
    }

    private void carregarProdutos() {
        executorService.execute(() -> {
            List<Produto> produtos = produtoDao.listarProdutos();
            runOnUiThread(() -> {
                produtoAdapter = new ProdutoAdapter(produtos);
                recyclerView.setAdapter(produtoAdapter);
            });
        });

        //executorService.execute(() -> {
        //  List<Produto> produtos = produtoDao.obterTodosProdutos(); // Obtém todos os produtos.
        //  runOnUiThread(() -> produtoAdapter.listaProdutos = produtos); // Atualiza a lista de produtos no UI thread.
        //});
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown(); // Finaliza o executor quando a atividade for destruída
    }
}