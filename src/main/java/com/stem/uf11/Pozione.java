package com.stem.uf11;

public class Pozione {

    private String nome;
    private Posizione posizione;
    private int mana;

    public Pozione(String nome, Posizione p, int mana) throws IllegalArgumentException {
        if (nome.equals("") || mana <= 0 || p == null)
            throw new IllegalArgumentException("Impossibile creare la pozione.");
        else {
            this.nome = nome;
            this.posizione = p;
            this.mana = mana;
        }
    }

    // Setters e getters
    public String getNome() {
        return nome;
    }

    public Posizione getPosizione() {
        return posizione;
    }

    public int getMana() {
        return mana;
    }

    public void setPosizione(Posizione p) throws IllegalArgumentException {
        if (p == null)
            throw new IllegalArgumentException("La posizione non può essere null.");
        else
            this.posizione = p;
    }

    public void setMana(int energia) throws IllegalArgumentException {
        if (energia <= 0)
            throw new IllegalArgumentException("L'energia deve essere maggiore di 0.");
        else
            this.mana = energia;
    }

    public void setNome(String nome) throws IllegalArgumentException {
        if (nome.equals(""))
            throw new IllegalArgumentException("Il nome non può essere vuoto.");
        else
            this.nome = nome;
    }

    @Override
    public String toString() {
        return "Pozione{" +
                "nome='" + nome + '\'' +
                ", posizione=" + posizione +
                ", mana=" + mana +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pozione pozione = (Pozione) obj;
        return mana == pozione.mana && nome.equals(pozione.nome) 
        &&  posizione.equals(pozione.posizione);
    }

}
