package com.stem.uf11;

public class Cibo {

    private String nome;
    private Posizione posizione;
    private int energia;

    public Cibo(String nome, Posizione p, int energia) throws IllegalArgumentException {
        if (nome.equals("") || energia <= 0 || p == null)
            throw new IllegalArgumentException("Impossibile creare il cibo.");
        else {
            this.nome = nome;
            this.posizione = p;
            this.energia = energia;
        }
    }

    // Setters e getters
    public String getNome() {
        return nome;
    }

    public Posizione getPosizione() {
        return posizione;
    }

    public int getEnergia() {
        return energia;
    }

    public void setPosizione(Posizione p) throws IllegalArgumentException {
        if (p == null)
            throw new IllegalArgumentException("La posizione non può essere null.");
        else
            this.posizione = p;
    }

    public void setEnergia(int energia) throws IllegalArgumentException {
        if (energia <= 0)
            throw new IllegalArgumentException("L'energia deve essere maggiore di 0.");
        else
            this.energia = energia;
    }

    public void setNome(String nome) throws IllegalArgumentException {
        if (nome.equals(""))
            throw new IllegalArgumentException("Il nome non può essere vuoto.");
        else
            this.nome = nome;
    }

    @Override
    public String toString() {
        return "Cibo{" +
                "nome='" + nome + '\'' +
                ", posizione=" + posizione +
                ", energia=" + energia +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cibo cibo = (Cibo) obj;
        return energia == cibo.energia && nome.equals(cibo.nome) 
        &&  posizione.equals(cibo.posizione);
    }

}
