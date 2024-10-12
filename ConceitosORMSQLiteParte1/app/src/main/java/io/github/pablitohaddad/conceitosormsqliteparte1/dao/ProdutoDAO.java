package io.github.pablitohaddad.conceitosormsqliteparte1.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.github.pablitohaddad.conceitosormsqliteparte1.model.Produto;

@Dao
public interface ProdutoDAO {

    // INSERIR O NOVO PRODUTO NO BANCO
    @Insert
    void inserirProdutos(Produto produto);

    // LISTAR OS PRODUTOS DO BANCO DE DADOS
    @Query("SELECT * FROM produtos")
    List<Produto> listarProdutos();
}
