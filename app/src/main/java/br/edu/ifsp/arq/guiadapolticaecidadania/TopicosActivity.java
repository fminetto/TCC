package br.edu.ifsp.arq.guiadapolticaecidadania;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Atividade que exibe os tópicos para o usuário e retorna o valor do item desejado
 */
public class TopicosActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextviewJustificado textviewJustificado; //Textview do conteúdo
    private TextView textView; //Textview Da justificativa
    private ItemPesquisa conteudo = null; //Conteúdo curto, longo e referências
    private DatabaseConnector databaseConnector; //Conexão com o banco de dados

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topicos);
        //EXibir o texto de boas vindas
        textviewJustificado = (TextviewJustificado) findViewById(R.id.textjustified);
        textviewJustificado.setText(getString(R.string.welcome_string));
        //Alterar estilo
        textviewJustificado.setTextColor(getResources().getColor(R.color.textcolor));
        textviewJustificado.setTextSize(20);
        //Alterar o alpha da activity
        findViewById(R.id.back).getBackground().setAlpha(75);
        //Abrir o textview das referencias
        textView = (TextView) findViewById(R.id.referencia);
        //Abrir o banco de dados
        databaseConnector = new DatabaseConnector(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Botão Saiba mais
        findViewById(R.id.saibamais).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(conteudo != null){
                    if(!conteudo.getLongo().equals("")){
                        textviewJustificado.setText(conteudo.getLongo());
                        findViewById(R.id.saibamais).setVisibility(View.INVISIBLE);
                    }
                }else{
                    Snackbar.make(textView, "Selecione algum item", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        //Desenho do menu lateral
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        //Fecha o menu se estiver aberto ou fecha a atividade
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.topicos, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.pesquisa){
            startActivity(new Intent(this, PesquisaActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Método chamado quando ocorre o evento de seleção de um item do menu lateral
     * @param item
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        try {
            databaseConnector.open();
            //Alterar o título da barra de ações
            getSupportActionBar().setTitle(item.getTitle());
            //Obter o conteúdo de acordo com o item selecionado
            switch (item.getItemId()){
                case R.id.nav_sispol1:
                    conteudo = databaseConnector.getConteudo(1);
                    break;
                case R.id.nav_sispol2:
                    conteudo = databaseConnector.getConteudo(2);
                    break;
                case R.id.nav_sispol3:
                    conteudo = databaseConnector.getConteudo(3);
                    break;
                case R.id.nav_sispol4:
                    conteudo = databaseConnector.getConteudo(4);
                    break;
                case R.id.nav_sispol5:
                    conteudo = databaseConnector.getConteudo(5);
                    break;
                case R.id.nav_sispol6:
                    conteudo = databaseConnector.getConteudo(6);
                    break;
                case R.id.nav_sispol7:
                    conteudo = databaseConnector.getConteudo(7);
                    break;
                case R.id.nav_sispol8:
                    conteudo = databaseConnector.getConteudo(8);
                    break;
                case R.id.nav_sispol9:
                    conteudo = databaseConnector.getConteudo(9);
                    break;
                case R.id.nav_sispol10:
                    conteudo = databaseConnector.getConteudo(10);
                    break;
                case R.id.nav_sispol11:
                    conteudo = databaseConnector.getConteudo(11);
                    break;
                case R.id.nav_sispol12:
                    conteudo = databaseConnector.getConteudo(12);
                    break;
                case R.id.nav_sispol13:
                    conteudo = databaseConnector.getConteudo(13);
                    break;
                case R.id.nav_trespo1:
                    conteudo = databaseConnector.getConteudo(14);
                    break;
                case R.id.nav_trespo2:
                    conteudo = databaseConnector.getConteudo(15);
                    break;
                case R.id.nav_trespo3:
                    conteudo = databaseConnector.getConteudo(16);
                    break;
                case R.id.nav_trespo4:
                    conteudo = databaseConnector.getConteudo(17);
                    break;
                case R.id.nav_repre1:
                    conteudo = databaseConnector.getConteudo(18);
                    break;
                case R.id.nav_repre2:
                    conteudo = databaseConnector.getConteudo(19);
                    break;
                case R.id.nav_repre3:
                    conteudo = databaseConnector.getConteudo(20);
                    break;
                case R.id.nav_repre4:
                    conteudo = databaseConnector.getConteudo(21);
                    break;
                case R.id.nav_repre5:
                    conteudo = databaseConnector.getConteudo(22);
                    break;
                case R.id.nav_partidos1:
                    conteudo = databaseConnector.getConteudo(23);
                    break;
                case R.id.nav_partidos2:
                    conteudo = databaseConnector.getConteudo(24);
                    break;
                case R.id.nav_partidos3:
                    conteudo = databaseConnector.getConteudo(25);
                    break;
                case R.id.nav_partidos4:
                    conteudo = databaseConnector.getConteudo(26);
                    break;
                case R.id.nav_partidos5:
                    conteudo = databaseConnector.getConteudo(27);
                    break;
                case R.id.nav_partidos6:
                    conteudo = databaseConnector.getConteudo(28);
                    break;
                case R.id.nav_partidos7:
                    conteudo = databaseConnector.getConteudo(29);
                    break;
                case R.id.nav_partidos8:
                    conteudo = databaseConnector.getConteudo(30);
                    break;
                case R.id.nav_sisele1:
                    conteudo = databaseConnector.getConteudo(31);
                    break;
                case R.id.nav_sisele2:
                    conteudo = databaseConnector.getConteudo(32);
                    break;
                case R.id.nav_sisele3:
                    conteudo = databaseConnector.getConteudo(33);
                    break;
                case R.id.nav_sisele4:
                    conteudo = databaseConnector.getConteudo(34);
                    break;
                case R.id.nav_sisele5:
                    conteudo = databaseConnector.getConteudo(35);
                    break;
                case R.id.nav_sisele6:
                    conteudo = databaseConnector.getConteudo(36);
                    break;
                case R.id.nav_sisele7:
                    conteudo = databaseConnector.getConteudo(37);
                    break;
                case R.id.nav_sisele8:
                    conteudo = databaseConnector.getConteudo(38);
                    break;
                case R.id.nav_sisele9:
                    conteudo = databaseConnector.getConteudo(39);
                    break;
                case R.id.nav_sisele10:
                    conteudo = databaseConnector.getConteudo(40);
                    break;
                case R.id.nav_culpol1:
                    conteudo = databaseConnector.getConteudo(41);
                    break;
                case R.id.nav_culpol2:
                    conteudo = databaseConnector.getConteudo(42);
                    break;
                case R.id.nav_culpol3:
                    conteudo = databaseConnector.getConteudo(43);
                    break;
                case R.id.nav_culpol4:
                    conteudo = databaseConnector.getConteudo(44);
                    break;
                case R.id.nav_culpol5:
                    conteudo = databaseConnector.getConteudo(45);
                    break;
                case R.id.nav_cidadania1:
                    conteudo = databaseConnector.getConteudo(46);
                    break;
                case R.id.nav_cidadania2:
                    conteudo = databaseConnector.getConteudo(47);
                    break;
                case R.id.nav_cidadania3:
                    conteudo = databaseConnector.getConteudo(48);
                    break;
                case R.id.nav_cidadania4:
                    conteudo = databaseConnector.getConteudo(49);
                    break;
                case R.id.nav_cidadania5:
                    conteudo = databaseConnector.getConteudo(50);
                    break;
                case R.id.nav_cidadania6:
                    conteudo = databaseConnector.getConteudo(51);
                    break;
                case R.id.nav_cidadania7:
                    conteudo = databaseConnector.getConteudo(52);
                    break;
                case R.id.nav_cidadania8:
                    conteudo = databaseConnector.getConteudo(53);
                    break;
                case R.id.nav_cidadania9:
                    conteudo = databaseConnector.getConteudo(54);
                    break;
                case R.id.nav_cidadania10:
                    conteudo = databaseConnector.getConteudo(55);
                    break;
                case R.id.nav_cidadania11:
                    conteudo = databaseConnector.getConteudo(56);
                    break;
                case R.id.nav_cidadania12:
                    conteudo = databaseConnector.getConteudo(57);
                    break;
                case R.id.nav_cidadania13:
                    conteudo = databaseConnector.getConteudo(58);
                    break;
                case R.id.nav_cidadania14:
                    conteudo = databaseConnector.getConteudo(59);
                    break;
                case R.id.nav_cidadania15:
                    conteudo = databaseConnector.getConteudo(60);
                    break;
                case R.id.nav_cidadania16:
                    conteudo = databaseConnector.getConteudo(61);
                    break;
                case R.id.nav_cidadania17:
                    conteudo = databaseConnector.getConteudo(62);
                    break;
                case R.id.nav_cidadania18:
                    conteudo = databaseConnector.getConteudo(63);
                    break;
                case R.id.nav_cidadania19:
                    conteudo = databaseConnector.getConteudo(64);
                    break;
                case R.id.nav_dirtra1:
                    conteudo = databaseConnector.getConteudo(65);
                    break;
                case R.id.nav_dirtra2:
                    conteudo = databaseConnector.getConteudo(66);
                    break;
                case R.id.nav_dirtra3:
                    conteudo = databaseConnector.getConteudo(67);
                    break;
                case R.id.nav_dirtra4:
                    conteudo = databaseConnector.getConteudo(68);
                    break;
                case R.id.nav_dirtra5:
                    conteudo = databaseConnector.getConteudo(69);
                    break;
                case R.id.nav_imposto1:
                    conteudo = databaseConnector.getConteudo(70);
                    break;
                case R.id.nav_imposto2:
                    conteudo = databaseConnector.getConteudo(71);
                    break;
                case R.id.nav_imposto3:
                    conteudo = databaseConnector.getConteudo(72);
                    break;
                case R.id.nav_imposto4:
                    conteudo = databaseConnector.getConteudo(73);
                    break;
                case R.id.nav_imposto5:
                    conteudo = databaseConnector.getConteudo(74);
                    break;
                case R.id.nav_imposto6:
                    conteudo = databaseConnector.getConteudo(75);
                    break;
                case R.id.nav_imposto7:
                    conteudo = databaseConnector.getConteudo(76);
                    break;
                case R.id.nav_imposto8:
                    conteudo = databaseConnector.getConteudo(77);
                    break;
                case R.id.nav_imposto9:
                    conteudo = databaseConnector.getConteudo(78);
                    break;
                case R.id.nav_imposto10:
                    conteudo = databaseConnector.getConteudo(79);
                    break;
            }
            /*
                Se tiver conteúdo o textview recebe a versão resumida se houver, caso não tenha um resumo,
                recebe o conteúdo completo diretamente.
             */
            if (conteudo != null)
            {
                if(!conteudo.getCurto().equals("")){
                    textviewJustificado.setText(conteudo.getCurto());
                }else{
                    textviewJustificado.setText(conteudo.getLongo());
                }
                textView.setText(conteudo.getReferencias());
            }
            //Oculta o textview que aparece o deslize para o lado
            TextView deslize = (TextView) findViewById(R.id.deslize);
            deslize.setVisibility(View.INVISIBLE);
            deslize.setText("");
            textView.setVisibility(View.VISIBLE);
            //Exibir ou não o botão saiba mais
            if(!conteudo.getCurto().equals("") && !conteudo.getLongo().equals("")){
                findViewById(R.id.saibamais).setVisibility(View.VISIBLE);
            }else{
                findViewById(R.id.saibamais).setVisibility(View.INVISIBLE);
            }
        }catch (Exception exc){
            // Ahhh ocorreu um erro :c Que pena!
            final Exception exception = exc; //Cópia da exceão lançada
            //Pede permissão para enviar um email se houve um erro
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Opps! Um erro ocorreu :'(");
            builder.setMessage("Deseja informar o desenvolvedor?");
            //O usuário deseja informar, então vamos mandar um email para o desenvolvedor
            builder.setPositiveButton("Por mim, tudo bem! :D", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO,
                                        Uri.fromParts("mailto", "fminetto44@gmail.com", null));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "ERROR");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Erro de banco de dados: " + exception.getMessage());
                    startActivity(Intent.createChooser(emailIntent, "Enviar email..."));
                }
            });
            //O usuário não quis, paciência, poderia melhorar o app :c
            builder.setNegativeButton("Não, obrigado! -_-", null);
            AlertDialog dialog = builder.create();
            dialog.show();
            return false;
        }finally{
            databaseConnector.close();
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
    }
}
