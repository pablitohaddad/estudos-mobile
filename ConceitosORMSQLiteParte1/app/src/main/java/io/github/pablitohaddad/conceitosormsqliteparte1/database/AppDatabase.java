package io.github.pablitohaddad.conceitosormsqliteparte1.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import io.github.pablitohaddad.conceitosormsqliteparte1.dao.ProdutoDAO;
import io.github.pablitohaddad.conceitosormsqliteparte1.model.Produto;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

// Classe abstrata que representa o banco de dados da aplicação.
@Database(entities = {Produto.class}, version = 1)
// Define as entidades do banco e a versão.
public abstract class AppDatabase extends RoomDatabase {

    // Retorna o DAO para a entidade Produto.
    public abstract ProdutoDAO produtoDao();

    // Instância única e volátil do banco de dados.
    // A palavra-chave 'volatile' garante que múltiplas threads mantenham uma visão consistente da instância.
    private static volatile AppDatabase INSTANCIA;

    // Retorna a instância do banco de dados. Utiliza o padrão Singleton para garantir que apenas uma instância exista.
    public static AppDatabase obterBancoDados(final Context context) {
        // Verifica se a instância já existe.
        if (INSTANCIA == null) {
            // Sincroniza para garantir thread-safety.
            synchronized (AppDatabase.class) {
                // Verifica novamente se a instância ainda é nula.
                if (INSTANCIA == null) {
                    INSTANCIA = Room.databaseBuilder(context.getApplicationContext(),
                                    // Cria o banco de dados.
                                    AppDatabase.class, "banco_de_dados_produtos")
                            .build();
                }
            }
        }
        // Retorna a instância do banco de dados.
        return INSTANCIA;
    }
}
