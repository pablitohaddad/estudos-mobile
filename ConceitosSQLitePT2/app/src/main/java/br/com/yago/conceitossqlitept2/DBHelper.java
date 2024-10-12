package br.com.yago.conceitossqlitept2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "testefa.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Correção: Usar a tabela correta 'Participante' ao invés de 'Userdetails'
        db.execSQL("CREATE TABLE Participante (nome TEXT PRIMARY KEY, cpf TEXT, telefone TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Correção: Usar a tabela correta 'Participante'
        db.execSQL("DROP TABLE IF EXISTS Participante");
        onCreate(db);
    }

    // Método para inserir dados na tabela 'Participante'
    public boolean insertUserData(String nome, String cpf, String telefone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", nome);
        contentValues.put("cpf", cpf);
        contentValues.put("telefone", telefone);

        // Correção: Usar a tabela correta 'Participante'
        long result = db.insert("Participante", null, contentValues);

        // Verificar se a inserção foi bem-sucedida
        return result != -1;
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Correção: Usar a tabela correta 'Participante'
        return db.rawQuery("SELECT * FROM Participante", null);
    }

    public Cursor getUserByName(String nome) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Correção: Usar a tabela correta 'Participante'
        return db.rawQuery("SELECT * FROM Participante WHERE nome = ?", new String[]{nome});
    }
}
