package br.edu.ifsp.arq.guiadapolticaecidadania;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.AnimationDrawable;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Atividade de pesquisa no banco de dados.
 * Nesta atividade o usuário digita o termo desejado e, se existir no banco de dados é retornado seus valores.
 */
public class PesquisaActivity extends AppCompatActivity {

    private DatabaseConnector databaseConnector; //Conector ao banco, para as buscas
    private ArrayList<ItemPesquisa> resultados; //Dados retornados do banco
    private ListView searchList; //Lista com os resultados das buscas
    private TextviewJustificado textview; //Textview que armazenará o conteúdo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa);
        //Ativa o botão de voltar na barra de atividade
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Deixa o fundo transparente
        findViewById(R.id.back).getBackground().setAlpha(75);
        //Instancia um conector ao banco
        databaseConnector = new DatabaseConnector(this);
        //Lista para exibir os resultados
        searchList = (ListView) findViewById(R.id.search_listview);
        //Textview para exibir o conteúdo desejado sem precisar alterar a activity
        textview = (TextviewJustificado) findViewById(R.id.textjustified);
        //Alterar estilo do textview
        textview.setTextColor(getResources().getColor(R.color.textcolor));
        textview.setTextSize(20);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Infla os itens do menu
        getMenuInflater().inflate(R.menu.search_menu, menu);
        //Caixa de pesquisa
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.search_item));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //Quando a query é submetida
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Limpa a tela
                textview.setVisibility(View.INVISIBLE);
                searchList.setVisibility(View.INVISIBLE);
                if(query.trim().equals("")){
                    Snackbar.make(findViewById(R.id.search_listview), "Pesquise algo", Snackbar.LENGTH_LONG).show();
                    return false;
                }
                //Forçar o teclado a fechar
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
                //Efetuar a pesquisa no banco de dados
                databaseConnector.open();
                /* Separar a query por % para retornar as ocorrências do resultado */
                Cursor c = databaseConnector.search(query.replace(" ", "%"));
                //Se houver resultados, os mostre
                if(c.getCount() > 0){
                    //Inicia uma nova lista de resultados
                    resultados = new ArrayList<>();
                    c.moveToFirst();
                    //Adicionar os resultados ao ArrayList
                    resultados.add(new ItemPesquisa(
                            c.getInt(c.getColumnIndex("Id")),
                            c.getString(c.getColumnIndex("Titulo")),
                            c.getString(c.getColumnIndex("Curto")),
                            c.getString(c.getColumnIndex("Longo"))
                    ));
                    while (c.moveToNext()){
                        ItemPesquisa tmp = new ItemPesquisa();
                        tmp.setId(c.getInt(c.getColumnIndex("Id")));
                        tmp.setTitulo(c.getString(c.getColumnIndex("Titulo")));
                        tmp.setCurto(c.getString(c.getColumnIndex("Curto")));
                        tmp.setLongo(c.getString(c.getColumnIndex("Longo")));
                        resultados.add(tmp);
                    }
                    PesquisaItemAdpater adapter = new PesquisaItemAdpater(PesquisaActivity.this, resultados);
                    searchList.setAdapter(adapter);
                    //Exibe a lista
                    searchList.setVisibility(View.VISIBLE);
                    searchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            if(!resultados.get(position).getCurto().equals(""))
                                textview.setText(resultados.get(position).getCurto());
                            else
                                textview.setText(resultados.get(position).getLongo());
                            textview.setVisibility(View.VISIBLE);
                            searchList.setVisibility(View.INVISIBLE);
                        }
                    });
                }else{
                    Snackbar.make(findViewById(R.id.search_listview), "Não há resultados para tua pesquisa!", Snackbar.LENGTH_SHORT).show();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
