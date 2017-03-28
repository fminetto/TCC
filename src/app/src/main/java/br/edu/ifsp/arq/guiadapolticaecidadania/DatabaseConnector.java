package br.edu.ifsp.arq.guiadapolticaecidadania;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Classe responsável por fazer a ponte entre a interface do aplicativo com a base de dados
 * disponível dentro do dispositivo. Seu papel é realizar consultas do coneteúdo pelo ID e pesquisa
 * por termo.
 */
public class DatabaseConnector {

    private final String db_name = "GuiaPolitica"; //Nome do banco de dados
    private DatabaseOpenHelper databaseOpenHelper; //Para abrir o banco de dados
    private SQLiteDatabase database; //Interagir com o banco de dados

    public DatabaseConnector(Context ctx){
        databaseOpenHelper = new DatabaseOpenHelper(ctx, db_name, null, 1);
    }

    /**
     * Cria ou abre o banco de dados para leitura
     * @throws SQLException Exceção lançada se, por algum motivo, o banco não puder ser recuperado
     */
    public void open() throws SQLException{
        database = databaseOpenHelper.getReadableDatabase();
    }

    /**
     * Fecha o banco de dados da aplicação
     */
    public void close(){
        if(database != null)
            database.close();
    }

    /**
     * Retorna o conteúdo de acordo com seu ID
     * @param index Indice do conteúdo
     * @return Um objeto do tipo ItemPesquisa com os dados
     */
    public ItemPesquisa getConteudo(int index){
        ItemPesquisa conteudo = new ItemPesquisa();
        String sql = "SELECT c._id as ID, c.conteudo as Curto, l.conteudo as Longo, r.referencia as Referencias\n" +
                "FROM curto c, longo l, referencia r\n" +
                "WHERE c._id = l._id_curto and r._id_longo = l._id and c._id = "+index+";";
        Cursor c = database.rawQuery(sql, null);
        c.moveToFirst();
        conteudo.setCurto(c.getString(c.getColumnIndex("Curto")));
        conteudo.setLongo(c.getString(c.getColumnIndex("Longo")));
        conteudo.setReferencias(c.getString(c.getColumnIndex("Referencias")));
        conteudo.setId(c.getInt(c.getColumnIndex("ID")));
        return  conteudo;
    }

    /**
     * Retorna um cursor com os ids eos titulos das pesquisas do banco de dados
     * @param termo Termo a ser pesquisado
     * @return Cursor
     */
    public Cursor search(String termo){
        String sql = "select c._id as Id, c.titulo as Titulo, c.conteudo as Curto, l.conteudo as Longo \n" +
                "from curto c inner join longo l\n" +
                "on c._id = l._id_curto \n" +
                "where l.conteudo like '%"+termo+"%' or c.conteudo like '%"+termo+"%' or c.titulo like '%"+termo+"%';";
        return database.rawQuery(sql,null);
    }
}
