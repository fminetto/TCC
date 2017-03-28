package br.edu.ifsp.arq.guiadapolticaecidadania;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

/***
 *   Esta Activity apresenta o menu inicial do aplicativo ao usuário. Através desta tela, o usuário
 * pode decidir se deseja ir para o Guia, para o Questionário ou ver o Sobre.
 *   Esta Activity implementa o método OnClick de View, para manipular o clique de um item da tela.
 */
public class TelaInicial extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
        //Alterar a transparência do fundo sem alterar do restante dos objetos
        RelativeLayout back = (RelativeLayout) findViewById(R.id.back);
        back.getBackground().setAlpha(75);
        //Atribuir o método OnClick aos itens
        findViewById(R.id.guia_item).setOnClickListener(this);
        findViewById(R.id.sobre_item).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //Uma animação rápida ao clicar no item
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.clickitem_animation));
        //Intent para abrir uma nova atividade
        Intent newActivity = getIntent();
        switch (view.getId()){
            case R.id.guia_item:
                newActivity = new Intent(this, TopicosActivity.class);
                break;
            case R.id.sobre_item:
                newActivity = new Intent(this, SobreActivity.class);
                break;
        }
        startActivity(newActivity);
    }
}
