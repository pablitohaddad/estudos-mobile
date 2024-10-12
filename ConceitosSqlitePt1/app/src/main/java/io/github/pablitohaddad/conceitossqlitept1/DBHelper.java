package io.github.pablitohaddad.conceitossqlitept1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "appUsuarios.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "usuarios";

    // COLUNAS
    private static final String ID_COL = "id";
    private static final String NOME_COL = "nome";
    private static final String EMAIL_COL = "email";
    private static final String SENHA_COL = "senha";
    private static final String TERMO_COL = "termos";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // COMANDO SQL PARA CRIAR A TABELA USUARIOS
        String query = "CREATE TABLE " + TABLE_NAME + "("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NOME_COL + " TEXT, "
                + EMAIL_COL + " TEXT, "
                + SENHA_COL + " TEXT, "
                + TERMO_COL + " INTEGER);";
        sqLiteDatabase.execSQL(query);
    }

    // METODO PARA ADD UM NEW USER
    public void addNewUser(String nome, String email, String senha, boolean termos){
        // instanciar o banco em modo escrita
        SQLiteDatabase banco = this.getWritableDatabase();
        // cria um conjunto de valores que serao inseridos no bd
        ContentValues dados = new ContentValues();
        dados.put(NOME_COL, nome);
        dados.put(EMAIL_COL, email);
        dados.put(SENHA_COL, senha);
        dados.put(TERMO_COL, termos ? 1 : 0);

        banco.insert(TABLE_NAME, null, dados);

        // fechar bd
        banco.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
