package io.github.pablitohaddad.conceitosormsqliteparte1.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "produtos")
public class Produto {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nome;

    private int quantidade;

    public Produto(String nome, int quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}


