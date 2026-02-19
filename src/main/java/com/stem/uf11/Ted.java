package com.stem.uf11;

import java.util.Scanner;

public class Ted {
	
	private Ted(){
	}

	/**
	 * 
	 * @param args Command line arguments
	 * @author Matteo Ceserani
	 * @version 0.9 Alpha
	 */
    public static void main(String[] args){

		char opt;

		Scanner sc = new Scanner(System.in);

		Mago M = new Mago("Gandalf", "Valinor", 2019, Personaggio.MAX_VITA, 100, new Posizione(0,0));

		Magia m1 = new Magia("Palla di fuoco", 20, 30);

		Mago nemici[] = new Mago[3];
		nemici[0] = new Mago("Saruman", "Valinor", 2019, Personaggio.MAX_VITA, 100, new Posizione(5,5));
		nemici[1] = new Mago("Oronzo", "Puglia", 50, Personaggio.MAX_VITA, 100, new Posizione(7,7));
		nemici[2] = new Mago("Merlino", "Inghilterra", 2019, Personaggio.MAX_VITA, 100, new Posizione(10,10));

		// Ciclo principale
		do{

			// 1. Clear screen
			// 2. Disegna mappa
			// 3. Accetta comando ('q' per uscire, oppure 'a,s,d,w' per muoversi)
			// 4. Esegui comando

			// 1. Clear screen
			System.out.print("\033[H\033[2J");
			System.out.flush();

			// 2. Disegna mappa
			for (int j = 0; j < Posizione.DIM_Y; j++){
				for(int i = 0; i < Posizione.DIM_X; i++){
					boolean nemico = false;
					for (int k = 0; k < nemici.length ; k++) {
						if (nemici[k].getPosizione().equals(new Posizione(i,j))){
							nemico = true;
							break;
						}
					}
					if (M.getPosizione().equals(new Posizione(i,j)))
						System.out.print("M");
					else if (nemico){
						System.out.print("N");
					}else
						System.out.print(".");
				}
				System.out.println();
			}

			// 3. Accetta comando: un carattere da tastiera, senza premere invio
			opt = sc.next().charAt(0);

			// 4. Esegui comando
			try{
				M.siMuove(opt);
			} catch (IllegalArgumentException e) {}

		}while(opt != 'q');

		sc.close();

    }

}