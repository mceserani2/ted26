package com.stem.uf11;

import java.util.Random;

public class Mago extends Personaggio{

    private static final int MAX_MANA = 100;

    private int mana;
    private Random dado;

    public Mago(String nome, String provenienza, int età, int vita, int mana, Posizione p){
        super(nome,provenienza,età,vita,p);
        if (mana < 0 || mana > MAX_MANA)
            throw new IllegalArgumentException("Valore di mana non valido");
        this.mana = mana;
        // Utilizza come seed il timestamp Unix
        this.dado = new Random(System.currentTimeMillis());
    }

    public int getMana(){
        return this.mana;
    }

    public void bevePozione(Pozione p) throws Exception{
        if (this.mana >= MAX_MANA)
            throw new Exception("Non puoi bere la pozione ora.");
        this.mana += p.getMana();
        if (this.mana > MAX_MANA)
            this.mana = MAX_MANA;
    }

    public boolean lanciaMagia(Magia m, Personaggio p) throws Exception {
        if (this.mana < m.getMana())
            throw new Exception("Non hai mana sufficiente!");
        this.mana -= m.getMana();
        if (dado.nextInt(10) + 1 > 3){
            try{
                p.setVita(p.getVita()-m.getDanno());
            } catch (IllegalArgumentException e) {
                p.setVita(0);
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Mago{" +
                "nome='" + getNome() + '\'' +
                ", provenienza='" + getProvenienza() + '\'' +
                ", età=" + getEtà() +
                ", vita=" + getVita() +
                ", posizione=" + getPosizione() +
                ", mana=" + mana +
                '}';
    }

}
