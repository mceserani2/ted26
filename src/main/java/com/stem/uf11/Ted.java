package com.stem.uf11;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import tools.jackson.databind.ObjectMapper;

public class Ted {
	
	private Ted(){
	}

	/**
	 * 
	 * @param args Command line arguments
	 * @author Matteo Ceserani
	 * @version 1.0
	 */
    public static void main(String[] args){

		Mago M = null;
		Mago[] nemici  = null;;
		Cibo[] cibi  = null;
		Pozione[] pozioni = null;
		Magia[] magie = null;

		ObjectMapper mapper = new ObjectMapper();

		Random r = new Random(System.currentTimeMillis());

		char opt;

		Scanner sc = new Scanner(System.in);

		System.out.println("Benvenuto in Ted's Adventure!");
		System.out.print("Vuoi caricare una partita precedente? (y/n)");
		char load = sc.next().charAt(0);

		if (load == 'y' || load == 'Y'){
			try{
				// Carica stato del mago da file JSON
				M = mapper.readValue(new File("./mago.json"), Mago.class);
				// Carica stato dei nemici da file JSON
				nemici = mapper.readValue(new File("./nemici.json"), Mago[].class);
				// Carica stato dei cibi da file JSON
				cibi = mapper.readValue(new File("./cibi.json"), Cibo[].class);
				// Carica stato delle pozioni da file JSON
				pozioni = mapper.readValue(new File("./pozioni.json"), Pozione[].class);
				// Carica stato delle magie da file JSON
				magie = mapper.readValue(new File("./magie.json"), Magia[].class);
			} catch (Exception e){
				System.out.println("Errore nel caricamento della partita!");
				sc.close();
				return;
			}
		} else {
			M = new Mago("Gandalf", "Valinor", 2019, Personaggio.MAX_VITA, 100, new Posizione(0,0));

			magie = new Magia[3];
			magie[0] = new Magia("Fulmine", 20, 30);
			magie[1] = new Magia("Palla di Fuoco", 30, 50);
			magie[2] = new Magia("Raggio di Gelo", 15, 20);

			nemici = new Mago[3];
			nemici[0] = new Mago("Saruman", "Valinor", 2019, Personaggio.MAX_VITA, 100, new Posizione(r.nextInt(Posizione.DIM_X),r.nextInt(Posizione.DIM_Y)));
			nemici[1] = new Mago("Oronzo", "Puglia", 50, Personaggio.MAX_VITA, 100, new Posizione(r.nextInt(Posizione.DIM_X),r.nextInt(Posizione.DIM_Y)));
			nemici[2] = new Mago("Merlino", "Inghilterra", 2019, Personaggio.MAX_VITA, 100, new Posizione(r.nextInt(Posizione.DIM_X),r.nextInt(Posizione.DIM_Y)));

			pozioni = new Pozione[3];
			pozioni[0] = new Pozione("Sacro Graal",new Posizione(r.nextInt(Posizione.DIM_X),r.nextInt(Posizione.DIM_Y)),70);
			pozioni[1] = new Pozione("Acqua di Lourdes",new Posizione(r.nextInt(Posizione.DIM_X),r.nextInt(Posizione.DIM_Y)),25);
			pozioni[2] = new Pozione("Acquavite",new Posizione(r.nextInt(Posizione.DIM_X),r.nextInt(Posizione.DIM_Y)),10);

			cibi = new Cibo[3];
			cibi[0] = new Cibo("Bistecca",new Posizione(r.nextInt(Posizione.DIM_X),r.nextInt(Posizione.DIM_Y)),30);
			cibi[1] = new Cibo("Pizza",new Posizione(r.nextInt(Posizione.DIM_X),r.nextInt(Posizione.DIM_Y)),45);
			cibi[2] = new Cibo("Lasagne",new Posizione(r.nextInt(Posizione.DIM_X),r.nextInt(Posizione.DIM_Y)),50);
		}

		// Clear screen
		System.out.print("\033[H\033[2J");
		System.out.flush();

		// Disegna mappa
		disegnaMappa(nemici, cibi, pozioni, M);

		// Ciclo principale
		do{

			// 1. Accetta comando ('q' per uscire, oppure 'a,s,d,w' per muoversi)
			// 2. Il mago si muove
			// 3. I nemici si muovono
			// 4. Clear screen
			// 5. Disegna mappa
			// 6. Eventuali combattimenti
			// 7. Eventuali raccolte di cibo
			// 8. Eventuali raccolte di pozioni

			// 1. Accetta comando: un carattere da tastiera, senza premere invio
			System.out.print("Comando (q per uscire): ");
			opt = sc.next().charAt(0);

			// 2. Esegui comando
			Posizione oldPos = M.getPosizione();
			try{
				M.siMuove(opt);
			} catch (IllegalArgumentException e) {}

			// 3. I nemici si muovono
			for (int i = 0;i < nemici.length; i++){
				if (nemici[i].getVita() > 0 && nemici[i].getPosizione().distanza(oldPos) > 1){
					if (nemici[i].getPosizione().distanza(oldPos)<10){
						int dx = oldPos.getX() - nemici[i].getPosizione().getX();
						int dy = oldPos.getY() - nemici[i].getPosizione().getY();
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
			}

			// 4. Clear screen
			System.out.print("\033[H\033[2J");
			System.out.flush();

			// 5. Disegna mappa
			disegnaMappa(nemici, cibi, pozioni, M);

			// 6. Eventuali combattimenti
			for (int k = 0;k < nemici.length; k++){
				if (nemici[k].getPosizione().equals(M.getPosizione())){
					System.out.println("Devi combattere con il nemico: " + nemici[k].getNome());
					System.out.println("Scegli la magia:");
					for (int u = 0; u < magie.length; u++){
						System.out.println(u + ". " + magie[u].getNome());
					}
					int m;
					do{
						System.out.print(":>");
						try{
							m = sc.nextInt();
							sc.nextLine();
						} catch (InputMismatchException e){
							m = -1;
							sc.nextLine();
						}
						if (m < 0 || m >= magie.length)
							System.out.println("Errore!");
					}while(m < 0 || m >= magie.length);
					try{
						if (M.lanciaMagia(magie[m], nemici[k]))
							System.out.println("Hai colpito il nemico!");
						else
							System.out.println("Non hai colpito il nemico!");							
					} catch (Exception e){
						System.out.println("Non hai mana sufficiente!");
					}
					if (nemici[k].getVita() == 0){
						System.out.println(nemici[k].getNome() + " è morto!");
						try{
							nemici[k].muore();
						} catch(Exception e){}
						// Controlla se hai vinto
						boolean vinto = true;
						for (int u = 0; u < nemici.length; u++){
							if (nemici[u].getVita() > 0){
								vinto = false;
								break;
							}
						}
						if (vinto){
							System.out.println("Hai vinto!");
							sc.close();
							return;
						}
					} else {
						try{
							if (nemici[k].lanciaMagia(magie[r.nextInt(magie.length)], M))
								System.out.println("Il nemico ti ha colpito!");
							else
								System.out.println("Il nemico non ti ha colpito !");							
						} catch (Exception e){
							System.out.println("Il nemico non ti ha colpito!");
						}
						if (M.getVita() == 0){
							System.out.println("Sei morto!");
							System.out.println("E' stato bello finché è durato.");
							sc.close();
							return;
						}
					}
					System.out.println("Vita: " + M.getVita() + " Mana: " + M.getMana());
					System.out.print("Premi invio per continuare...");
					sc.nextLine();
				}
			}
			
			// 7. Eventuali raccolte di cibo
			for(int k = 0; k < cibi.length; k++){
				if (cibi[k].getPosizione().equals(M.getPosizione())){
					System.out.print("Desideri mangiare " + cibi[k].getNome() + "? (y/n)");
					char c = sc.next().charAt(0);
					if (c == 'y' || c == 'Y'){
						try{
							M.mangia(cibi[k]);
							cibi[k].setPosizione(new Posizione(r.nextInt(Posizione.DIM_X), r.nextInt(Posizione.DIM_Y)));
						} catch (Exception e){
							System.out.println("Non hai fame!");
						}
					}
					System.out.println("Vita: " + M.getVita() + " Mana: " + M.getMana());
					System.out.print("Premi invio per continuare...");
					sc.nextLine();
				}
			}

			// 8. Eventuali raccolte di pozioni
			for(int k = 0; k < pozioni.length; k++){
				if (pozioni[k].getPosizione().equals(M.getPosizione())){
					System.out.print("Desideri prendere la pozione " + pozioni[k].getNome() + "? (y/n)");
					char c = sc.next().charAt(0);
					if (c == 'y' || c == 'Y'){
						try{
							M.bevePozione(pozioni[k]);
							pozioni[k].setPosizione(new Posizione(r.nextInt(Posizione.DIM_X), r.nextInt(Posizione.DIM_Y)));
						} catch (Exception e){
							System.out.println("Non hai sete!");
						}
					}
					System.out.println("Vita: " + M.getVita() + " Mana: " + M.getMana());
					System.out.print("Premi invio per continuare...");
					sc.nextLine();
				}
			}

		}while(opt != 'q');

		// Salva stato del mago su file JSON
		mapper.writeValue(new File("./mago.json"), M);
		// Salva stato dei nemici su file JSON
		mapper.writeValue(new File("./nemici.json"), nemici);
		// Salva stato dei cibi su file JSON
		mapper.writeValue(new File("./cibi.json"), cibi);
		// Salva stato delle pozioni su file JSON
		mapper.writeValue(new File("./pozioni.json"), pozioni);
		// Salva stato delle magie su file JSON
		mapper.writeValue(new File("./magie.json"), magie);

		sc.close();
		
    }

	private static void disegnaMappa(Mago[] nemici, Cibo[] cibi, Pozione[] pozioni, Mago M){
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
	}

}