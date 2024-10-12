package io.github.pablitohaddad.recycleviewpt1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    // PASSO 5 - DEFINIR OS CAMPOS CONTEXT E ARRAYLIST
    private final Context context;
    private final ArrayList<Integer> integerArrayList;

    // PASSO 6 - CRIAR CONSTRUTORES
    public MyAdapter(Context context, ArrayList<Integer> integerArrayList) {
        this.context = context;
        this.integerArrayList = integerArrayList;
    }

    // PASSO 1 - Aplicar os metodos obrigatorios
    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // PASSO 9 - INFLAR O LAYOUT
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_image_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        // PASSO 10 - LIGAR DADOS AO VIEW HOLDER
        Glide.with(context)
                .load(integerArrayList.get(position))
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        // PASSO 8 - RETORNAR O NMR DE ITEMS
        return integerArrayList.size();
    }

    // PASSO 2 - Criar a classe interna View Holder
    // PASSO 3 - Extender o Recycle View Holder
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //PASSO 7 ADD COMPONENTES A CLASSE VIEW HOLDER
            // REQUESITO PARA A VIEWHOLDER ENXERGAR A NOSSA IMAGEVIEW
            imageView = itemView.findViewById(R.id.imageView);

        }
    }


}
