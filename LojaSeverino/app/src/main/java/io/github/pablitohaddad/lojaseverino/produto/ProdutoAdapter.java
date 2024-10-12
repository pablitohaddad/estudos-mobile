package io.github.pablitohaddad.lojaseverino.produto;

import static android.os.Build.VERSION_CODES.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import io.github.pablitohaddad.lojaseverino.R;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder> {
    private List<Produto> produtos;   // Lista de produtos
    private LayoutInflater inflater;

    // Construtor que inicializa o adaptador com uma lista de produtos
    public ProdutoAdapter(Context context, List<Produto> produtos) {
        this.produtos = produtos;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infla o layout de item de produto
        View view = inflater.inflate(io.github.pablitohaddad.lojaseverino.R.layout.produto_item, parent, false);
        return new ProdutoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoViewHolder holder, int position) {
        // Vincula os dados do produto ao item da lista
        Produto produto = produtos.get(position);
        holder.nomeProdutoTextView.setText(produto.getNome());
        holder.precoProdutoTextView.setText("R$ " + produto.getPreco());
        holder.categoriaProdutoTextView.setText(produto.getCategoria().getNome());
    }

    @Override
    public int getItemCount() {
        return produtos.size();  // Retorna o tamanho da lista de produtos
    }

    public static class ProdutoViewHolder extends RecyclerView.ViewHolder {
        TextView nomeProdutoTextView, precoProdutoTextView, categoriaProdutoTextView;

        public ProdutoViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeProdutoTextView = itemView.findViewById(io.github.pablitohaddad.lojaseverino.R.id.tvProdutoNome);
            precoProdutoTextView = itemView.findViewById(io.github.pablitohaddad.lojaseverino.R.id.tvProdutoPreco);
            categoriaProdutoTextView = itemView.findViewById(io.github.pablitohaddad.lojaseverino.R.id.tvProdutoCategoria);
        }
    }
}
