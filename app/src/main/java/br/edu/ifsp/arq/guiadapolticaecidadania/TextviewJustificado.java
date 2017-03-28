package br.edu.ifsp.arq.guiadapolticaecidadania;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.webkit.WebView;


/**
 * Textview personalizado, utilizado para justificar o texto do conteúdo
 * Esta classe implementa uma webview que serve para exibir o conteúdo de maneira justificada, como
 * se fosse um textview.
 */
public class TextviewJustificado extends WebView{

    //Básico da formatação
    private String core = "<html><head><meta charset=\"UTF-8\"></head><body style='text-align:justify;color:rgba(%s);font-size:%dpx;margin: 10px 10px 10px 10px;'>%s</body></html>";

    //Cor do texto
    private String textColor = "0,0,0,255";

    //Texto a ser exibido
    private String text = "";

    //Tamanho do texto
    private int textSize = 14;

    //Cor de fundo
    private int background = Color.TRANSPARENT;


    public TextviewJustificado(Context context){
        super(context);
    }

    public TextviewJustificado(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Altera o texto do textview justificado
     * @param text
     */
    public void setText(String text){
        this.text = text;
        //Recarrega os dados
        reloadData();
    }

    /**
     * Atualiza a webview com os dados informados
     */
    private void reloadData(){

        //Carrega os dados na webview
        this.loadDataWithBaseURL(null, String.format(core, textColor, textSize, text), "text/html", "utf-8", null);

        //Configura a cor de fundo da webview
        super.setBackgroundColor(background);

        // Hardware rendering breaks background color to work as expected.
        // Need to use software renderer in that case.
        if (android.os.Build.VERSION.SDK_INT >= 11)
            this.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
    }

    /**
     * Altera a cor do texto convertendo a cor de hexadecimal em RGBA
     * @param hex
     */
    public void setTextColor(int hex){
        String h = Integer.toHexString(hex);
        int a = Integer.parseInt(h.substring(0, 2), 16);
        int r = Integer.parseInt(h.substring(2, 4), 16);
        int g = Integer.parseInt(h.substring(4, 6), 16);
        int b = Integer.parseInt(h.substring(6, 8), 16);
        textColor = String.format("%d,%d,%d,%d", r, g, b, a);
        reloadData();
    }

    /**
     * Altera a cor de fundo
     * @param hex
     */
    public void setBackgroundColor(int hex) {
        background = hex;
        reloadData();
    }

    /**
     * Altera o tamanho do texto
     * @param textSize
     */
    public void setTextSize(int textSize) {
        this.textSize = textSize;
        reloadData();
    }

    /**
     * Retorna o texto exibido
     * @return
     */
    public String getText(){
        return this.text;
    }

}