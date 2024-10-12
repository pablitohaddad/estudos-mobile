package io.github.pablitohaddad.lojaseverino.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import io.github.pablitohaddad.lojaseverino.categoria.Categoria;
import io.github.pablitohaddad.lojaseverino.produto.Produto;

// Classe que gerencia o banco de dados SQLite para as tabelas de Categoria e Produto
public class DatabaseHelper extends SQLiteOpenHelper {

    // Nome do banco de dados e sua versão
    private static final String DB_NAME = "produtos.db";
    private static final int DB_VERSION = 1;

    // Tabela Categoria e suas colunas
    private static final String TABLE_CATEGORIA = "categoria";
    private static final String CATEGORIA_ID = "id";
    private static final String CATEGORIA_NOME = "nome";

    // Tabela Produto e suas colunas
    private static final String TABLE_PRODUTO = "produto";
    private static final String PRODUTO_ID = "id";
    private static final String PRODUTO_NOME = "nome";
    private static final String PRODUTO_PRECO = "preco";
    private static final String PRODUTO_CATEGORIA_ID = "categoria_id";

    // Construtor que chama o super-construtor da classe SQLiteOpenHelper
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // Método chamado para criar as tabelas do banco de dados
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Criação da tabela Categoria
        String createCategoriaTable = "CREATE TABLE " + TABLE_CATEGORIA + " (" +
                CATEGORIA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CATEGORIA_NOME + " TEXT NOT NULL)";
        db.execSQL(createCategoriaTable);

        // Criação da tabela Produto com chave estrangeira para Categoria
        String createProdutoTable = "CREATE TABLE " + TABLE_PRODUTO + " (" +
                PRODUTO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PRODUTO_NOME + " TEXT NOT NULL, " +
                PRODUTO_PRECO + " REAL NOT NULL, " +
                PRODUTO_CATEGORIA_ID + " INTEGER NOT NULL, " +
                "FOREIGN KEY(" + PRODUTO_CATEGORIA_ID + ") REFERENCES " + TABLE_CATEGORIA + "(" + CATEGORIA_ID + "))";
        db.execSQL(createProdutoTable);
    }

    // Método chamado quando há uma atualização no banco de dados (mudança de versão)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Apaga as tabelas antigas e recria as novas
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUTO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIA);
        onCreate(db);
    }

    // Método para inserir uma nova categoria na tabela de Categoria
    public long addCategoria(Categoria categoria) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CATEGORIA_NOME, categoria.getNome());  // Armazena o nome da categoria no ContentValues
        return db.insert(TABLE_CATEGORIA, null, values);  // Insere os valores na tabela e retorna o ID da nova categoria
    }

    // Método para buscar todas as categorias no banco de dados
    public Cursor getAllCategorias() {
        SQLiteDatabase db = this.getReadableDatabase();  // Obtém o banco de dados em modo leitura
        return db.rawQuery("SELECT * FROM " + TABLE_CATEGORIA, null);  // Executa a consulta e retorna um cursor com os resultados
    }

    // Método para inserir um novo produto na tabela de Produto
    public long addProduto(Produto produto) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PRODUTO_NOME, produto.getNome());  // Armazena o nome do produto
        values.put(PRODUTO_PRECO, produto.getPreco());  // Armazena o preço do produto
        values.put(PRODUTO_CATEGORIA_ID, produto.getCategoria().getId());  // Armazena o ID da categoria associada ao produto
        return db.insert(TABLE_PRODUTO, null, values);  // Insere os valores na tabela de Produto e retorna o ID do novo produto
    }

    // Método para buscar todos os produtos e suas categorias associadas
    public Cursor getAllProdutos() {
        SQLiteDatabase db = this.getReadableDatabase();
        // Consulta para buscar o nome do produto, preço e nome da categoria associada
        String query = "SELECT " + TABLE_PRODUTO + "." + PRODUTO_NOME + ", " +
                TABLE_PRODUTO + "." + PRODUTO_PRECO + ", " +
                TABLE_CATEGORIA + "." + CATEGORIA_NOME + " AS categoria_nome " +
                "FROM " + TABLE_PRODUTO +
                " JOIN " + TABLE_CATEGORIA +
                " ON " + TABLE_PRODUTO + "." + PRODUTO_CATEGORIA_ID + " = " + TABLE_CATEGORIA + "." + CATEGORIA_ID;
        return db.rawQuery(query, null);  // Executa a consulta e retorna um cursor com os resultados
    }
}
