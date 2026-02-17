package com.stem.uf11;

public class Magia {

    private String nome;
    private int mana;
    private int danno;

    public Magia(String nome, int mana, int danno) throws IllegalArgumentException {
        if (nome.equals("") || mana <= 0 || danno <= 0)
            throw new IllegalArgumentException("Impossibile creare la magia.");
        else {
            this.nome = nome;
            this.mana = mana;
            this.danno = danno;
        }
    }

    // Setters e getters
    public String getNome() {
        return nome;
    }

    public int getMana() {
        return mana;
    }

    public int getDanno() {
        return danno;
    }

    public void setMana(int mana) throws IllegalArgumentException {
        if (mana <= 0)
            throw new IllegalArgumentException("Il mana deve essere maggiore di 0.");
        else
            this.mana = mana;
    }

    public void setDanno(int danno) throws IllegalArgumentException {
        if (danno <= 0)
            throw new IllegalArgumentException("Il danno deve essere maggiore di 0.");
        else
            this.danno = danno;
    }

    public void setNome(String nome) throws IllegalArgumentException {
        if (nome.equals(""))
            throw new IllegalArgumentException("Il nome non può essere vuoto.");
        else
            this.nome = nome;
    }

    @Override
    public String toString() {
        return "Magia{" +
                "nome='" + nome + '\'' +
                ", mana=" + mana +
                ", danno=" + danno +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Magia magia = (Magia) obj;
        return mana == magia.mana && danno == magia.danno && nome.equals(magia.nome);
    }
    
}
