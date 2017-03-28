package br.edu.ifsp.arq.guiadapolticaecidadania;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class SobreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Configuração do textviewjustificado
        TextviewJustificado textviewJustificado = (TextviewJustificado) findViewById(R.id.sobre_textviewjustificado);
        textviewJustificado.setText(getString(R.string.sobre_large_text));
        //Alterar estilo
        textviewJustificado.setTextColor(getResources().getColor(R.color.textcolor));
        textviewJustificado.setTextSize(20);
        //Alterar o alpha da activity
        findViewById(R.id.back).getBackground().setAlpha(75);
    }

}
