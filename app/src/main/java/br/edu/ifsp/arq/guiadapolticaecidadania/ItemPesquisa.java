package br.edu.ifsp.arq.guiadapolticaecidadania;

/**
 * Armazena os resultados da pesquisa do banco de dados
 */
public class ItemPesquisa {

    private int Id;

    private String titulo, curto, longo, referencias;

    public ItemPesquisa() {
    }

    public ItemPesquisa(int id, String titulo, String curto, String longo) {
        Id = id;
        this.titulo = titulo;
        this.curto = curto;
        this.longo = longo;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCurto() {
        return curto;
    }

    public void setCurto(String curto) {
        this.curto = curto;
    }

    public String getLongo() {
        return longo;
    }

    public void setLongo(String longo) {
        this.longo = longo;
    }

    public String getReferencias() {
        return referencias;
    }

    public void setReferencias(String referencias) {
        this.referencias = referencias;
    }



    @Override
    public String toString() {
        return titulo;
    }
}
