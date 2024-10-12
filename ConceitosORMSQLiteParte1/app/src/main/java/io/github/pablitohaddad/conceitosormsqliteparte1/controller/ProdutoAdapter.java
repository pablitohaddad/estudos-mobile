package io.github.pablitohaddad.conceitosormsqliteparte1.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.github.pablitohaddad.conceitosormsqliteparte1.R;
import io.github.pablitohaddad.conceitosormsqliteparte1.model.Produto;

// Adapter para o RecyclerView que exibe os produtos.
public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ViewHolder> {

    private List<Produto> listaProdutos; // Lista de produtos a ser exibida.

    // Construtor do adaptador que recebe a lista de produtos.
    public ProdutoAdapter(List<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos; // Inicializa a lista de produtos.
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_produto, parent, false); // Infla o layout do item do produto.
        return new ViewHolder(view); // Retorna o ViewHolder.
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Produto produto = listaProdutos.get(position); // Obtém o produto da lista na posição atual.
        holder.textViewNome.setText(produto.getNome()); // Define o nome do produto no TextView.
        holder.textViewQuantidade.setText("Quantidade: " + produto.getQuantidade()); // Define a quantidade do produto.
    }

    @Override
    public int getItemCount() {
        return listaProdutos.size(); // Retorna o número de itens na lista de produtos.
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNome; // TextView para exibir o nome do produto.
        TextView textViewQuantidade; // TextView para exibir a quantidade do produto.

        ViewHolder(View itemView) {
            super(itemView);
            textViewNome = itemView.findViewById(R.id.textViewNome); // Inicializa o TextView do nome.
            textViewQuantidade = itemView.findViewById(R.id.textViewQuantidade); // Inicializa o TextView da quantidade.
        }
    }
}

