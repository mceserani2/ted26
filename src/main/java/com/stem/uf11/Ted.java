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

		Personaggio p = new Personaggio("Gandalf", "Valinor", 2019, Personaggio.MAX_VITA, new Posizione(0,0));

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
					if (p.getPosizione().equals(new Posizione(i,j)))
						System.out.print("P");
					else
						System.out.print(".");
				}
				System.out.println();
			}

			// 3. Accetta comando: un carattere da tastiera, senza premere invio
			opt = sc.next().charAt(0);

			// 4. Esegui comando
			try{
				p.siMuove(opt);
			} catch (IllegalArgumentException e) {}

		}while(opt != 'q');

		sc.close();

    }

}