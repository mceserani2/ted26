package com.stem.uf11;

public class Arma {

    private String nome;
    private int energia;
    private int danno;

    public Arma(String nome, int energia, int danno) throws IllegalArgumentException {
        if (nome.equals("") || energia <= 0 || danno <= 0)
            throw new IllegalArgumentException("Impossibile creare l'arma.");
        else {
            this.nome = nome;
            this.energia = energia;
            this.danno = danno;
        }
    }

    // Setters e getters
    public String getNome() {
        return nome;
    }

    public int getEnergia() {
        return energia;
    }

    public int getDanno() {
        return danno;
    }

    public void setEnergia(int mana) throws IllegalArgumentException {
        if (mana <= 0)
            throw new IllegalArgumentException("Il mana deve essere maggiore di 0.");
        else
            this.energia = mana;
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
        return "Arma{" +
                "nome='" + nome + '\'' +
                ", energia=" + energia +
                ", danno=" + danno +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Arma arma = (Arma) obj;
        return energia == arma.energia && danno == arma.danno && nome.equals(arma.nome);
    }

}
