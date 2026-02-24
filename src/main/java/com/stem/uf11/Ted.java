package com.stem.uf11;

import java.util.Random;
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

		Random r = new Random(System.currentTimeMillis());

		char opt;

		Scanner sc = new Scanner(System.in);

		Mago M = new Mago("Gandalf", "Valinor", 2019, Personaggio.MAX_VITA, 100, new Posizione(0,0));

		Magia m1 = new Magia("Palla di fuoco", 20, 30);

		Mago nemici[] = new Mago[3];
		nemici[0] = new Mago("Saruman", "Valinor", 2019, Personaggio.MAX_VITA, 100, new Posizione(r.nextInt(Posizione.DIM_X),r.nextInt(Posizione.DIM_Y)));
		nemici[1] = new Mago("Oronzo", "Puglia", 50, Personaggio.MAX_VITA, 100, new Posizione(r.nextInt(Posizione.DIM_X),r.nextInt(Posizione.DIM_Y)));
		nemici[2] = new Mago("Merlino", "Inghilterra", 2019, Personaggio.MAX_VITA, 100, new Posizione(r.nextInt(Posizione.DIM_X),r.nextInt(Posizione.DIM_Y)));

		Pozione pozioni[] = new Pozione[3];
		pozioni[0] = new Pozione("Sacro Graal",new Posizione(r.nextInt(Posizione.DIM_X),r.nextInt(Posizione.DIM_Y)),70);
		pozioni[1] = new Pozione("Acqua di Lourdes",new Posizione(r.nextInt(Posizione.DIM_X),r.nextInt(Posizione.DIM_Y)),25);
		pozioni[2] = new Pozione("Acquavite",new Posizione(r.nextInt(Posizione.DIM_X),r.nextInt(Posizione.DIM_Y)),10);

		Cibo cibi[] = new Cibo[3];
		cibi[0] = new Cibo("Bistecca",new Posizione(r.nextInt(Posizione.DIM_X),r.nextInt(Posizione.DIM_Y)),30);
		cibi[1] = new Cibo("Pizza",new Posizione(r.nextInt(Posizione.DIM_X),r.nextInt(Posizione.DIM_Y)),45);
		cibi[2] = new Cibo("Lasagne",new Posizione(r.nextInt(Posizione.DIM_X),r.nextInt(Posizione.DIM_Y)),50);

		// Ciclo principale
		do{

			// 1. Clear screen
			// 2. Disegna mappa
			// 3. Accetta comando ('q' per uscire, oppure 'a,s,d,w' per muoversi)
			// 4. Esegui comando
			// 5. I nemici si muovono

			// 1. Clear screen
			System.out.print("\033[H\033[2J");
			System.out.flush();

			// 2. Disegna mappa
			for (int j = 0; j < Posizione.DIM_Y; j++){
				for(int i = 0; i < Posizione.DIM_X; i++){
					boolean nemico = false;
					boolean cibo = false;
					boolean pozione = false;
					for (int k = 0;k < nemici.length; k++){
						if (nemici[k].getPosizione().equals(new Posizione(i,j))){
							nemico = true;
							break;
						}
					}
					for (int k = 0;k < cibi.length; k++){
						if (cibi[k].getPosizione().equals(new Posizione(i,j))){
							cibo = true;
							break;
						}
					}
					for (int k = 0;k < pozioni.length; k++){
						if (pozioni[k].getPosizione().equals(new Posizione(i,j))){
							pozione = true;
							break;
						}
					}
					if (M.getPosizione().equals(new Posizione(i,j)))
						System.out.print("M");
					else if (nemico)
						System.out.print("N");
					else if (cibo)
						System.out.print("c");
					else if (pozione)
						System.out.print("p");
					else
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

			// 5. I nemici si muovono
			for (int i = 0;i < nemici.length; i++){
				if (nemici[i].getPosizione().distanza(M.getPosizione())<10){
					int dx = M.getPosizione().getX() - nemici[i].getPosizione().getX();
					int dy = M.getPosizione().getY() - nemici[i].getPosizione().getY();
					if (Math.abs(dx) >= Math.abs(dy)){
						// Ci si muove lungo x
						if (dx < 0)
							nemici[i].siMuove('a');
						else
							nemici[i].siMuove('d');
					}else{
						// Ci si muove lungo y
						if (dy < 0)
							nemici[i].siMuove('w');
						else
							nemici[i].siMuove('s');
					}
				}else{
					int m = r.nextInt(4);
					switch(m){
						case 0: nemici[i].siMuove('w');
								break;
						case 1: nemici[i].siMuove('s');
								break;
						case 2: nemici[i].siMuove('d');
								break;
						case 3: nemici[i].siMuove('a');
								break;
					}
				}
			}

		}while(opt != 'q');

		sc.close();

    }

}