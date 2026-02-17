package com.stem.uf11;

public class Personaggio {

    private static final int MIN_ETA = 10;
    private static final int MAX_VITA = 100;

    private String nome;
    private String provenienza;
    private int età;
    private int vita;
    private Posizione posizione;

    public Personaggio(String nome, String provenienza, int età, int vita, Posizione p){
        if ( nome.equals("") || provenienza.equals("") || età < MIN_ETA || vita <= 0 || vita > MAX_VITA || p == null ) {
            throw new IllegalArgumentException("Non posso creare il personaggio");
        } else {
            this.nome = nome;
            this.provenienza = provenienza;
            this.età = età;
            this.vita = vita;
            this.posizione = p;
        }
    }

    public void mangia(Cibo c) throws Exception {
        if (this.vita >= MAX_VITA)
            throw new Exception("Non puoi mangiare ora.");
        this.vita += c.getEnergia();
        if (this.vita > MAX_VITA)
            this.vita = MAX_VITA;
    }
}
