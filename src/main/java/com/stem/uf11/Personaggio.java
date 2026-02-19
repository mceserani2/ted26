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

    public void siMuove(char dir) throws IllegalArgumentException{
        if (dir != 'a' && dir != 's' && dir != 'd' && dir != 'w')
            throw new IllegalArgumentException("Direzione non valida");
        switch(dir){
            case 'a': try {
                        this.posizione.setX(this.posizione.getX()-1);
                      } catch (IllegalArgumentException e) {}
                      break;
            case 's': try {
                        this.posizione.setY(this.posizione.getY()+1);
                      } catch (IllegalArgumentException e) {}
                      break;
            case 'd': try {
                        this.posizione.setX(this.posizione.getX()+1);
                      } catch (IllegalArgumentException e) {}
                      break;
            case 'w': try {
                        this.posizione.setY(this.posizione.getY()-1);
                      } catch (IllegalArgumentException e) {}
                      break;
        }
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getProvenienza() {
        return provenienza;
    }

    public int getEtà() {
        return età;
    }

    public int getVita() {
        return vita;
    }

    public Posizione getPosizione() {
        return posizione;
    }

    public void setVita(int vita) throws IllegalArgumentException {
        if (vita < 0 || vita > MAX_VITA)
            throw new IllegalArgumentException("Vita non valida");
        this.vita = vita;
    }

    @Override
    public String toString() {
        return "Personaggio{" +
                "nome='" + nome + '\'' +
                ", provenienza='" + provenienza + '\'' +
                ", età=" + età +
                ", vita=" + vita +
                ", posizione=" + posizione +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Personaggio other = (Personaggio) obj;
        return this.età == other.età && this.nome.equals(other.nome) 
        && this.provenienza.equals(other.provenienza);
    }

}
