package br.edu.ifsp.arq.guiadapolticaecidadania;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Adaptador para a lista que vem da pesquisa.
 * Fornece métods para a transformar o arraylist de resultados em itens da lista
 */
public class PesquisaItemAdpater extends BaseAdapter{

    private List<ItemPesquisa> itens;
    private LayoutInflater inflater;

    public PesquisaItemAdpater(Context context, List<ItemPesquisa> itens){
        this.itens = itens;
        this.inflater =LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Object getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view == null){
            view = inflater.inflate(R.layout.pesquisa_item, null);
        }
        ((TextView) view.findViewById(R.id.textview)).setText(itens.get(position).getTitulo());
        return view;
    }
}
