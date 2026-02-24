package com.stem.uf11;

public class Posizione {

    public static final int DIM_X = 50;
    public static final int DIM_Y = 25;

    private int x;
    private int y;

    public Posizione(int x, int y) throws IllegalArgumentException {
        if (checkXY(x,y)) {
            this.x = x;
            this.y = y;
        } else {
            throw new IllegalArgumentException("Il punto non appartiene alla mappa");
        }
    }

    public void setX(int x) throws IllegalArgumentException {
        if (checkXY(x, 0))
            this.x = x;
        else
            throw new IllegalArgumentException("Valore di X non valido");
    }

    public void setY(int y) throws IllegalArgumentException {
        if (checkXY(0, y))
            this.y = y;
        else
            throw new IllegalArgumentException("Valore di Y non valido");
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int distanza(Posizione p){
        return Math.abs(this.x - p.x) + Math.abs(this.y - p.y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Posizione posizione = (Posizione) obj;
        return x == posizione.x && y == posizione.y;
    }

    public static boolean checkXY(int x, int y){
        if (x == -1 && y == -1)
            return true;
        return (x >= 0 && x < DIM_X) && (y >= 0 && y < DIM_Y);
    }

    @Override
    public String toString() {
        return "Posizione{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

}
