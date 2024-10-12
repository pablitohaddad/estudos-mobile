package io.github.pablitohaddad.lojaseverino.categoria;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.CategoriaViewHolder> {
    private List<Categoria> categorias;  // Lista de categorias
    private LayoutInflater inflater;

    // Construtor que inicializa o adaptador com uma lista de categorias
    public CategoriaAdapter(Context context, List<Categoria> categorias) {
        this.categorias = categorias;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CategoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infla o layout de item da categoria
        View view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        return new CategoriaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaViewHolder holder, int position) {
        // Vincula os dados da categoria ao item da lista
        Categoria categoria = categorias.get(position);
        holder.nomeCategoriaTextView.setText(categoria.getNome());
    }

    @Override
    public int getItemCount() {
        return categorias.size();  // Retorna o tamanho da lista de categorias
    }

    public static class CategoriaViewHolder extends RecyclerView.ViewHolder {
        TextView nomeCategoriaTextView;

        public CategoriaViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeCategoriaTextView = itemView.findViewById(android.R.id.text1);
        }
    }
}

