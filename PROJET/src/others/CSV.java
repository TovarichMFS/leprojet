/**
 * 
 */
package others;

import static java.nio.file.StandardOpenOption.APPEND;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import controleur.ControleurChaineDeProduction;
import controleur.ControleurElement;
import controleur.ControleurUsine;
import modele.ChaineDeProduction;
import modele.Element;
import modele.MatierePremiere;
import modele.Produit;
import modele.Usine;

/**
 * @author tovarich
 *
 */
public interface CSV {
	/**
	 * Charge les Elements et les ChaineDeProduction d'un fichier CSV dans l'objet Usine u
	 */
	public default Usine chargerCSV() {
		Usine u = new Usine();
		ControleurUsine cU = new ControleurUsine(u);
		Path pE = Paths.get("elements.csv");
		try {
			ArrayList<String> lignes = (ArrayList<String>) Files.readAllLines(pE);
			lignes.remove(0);
			for (String string : lignes) {
				Element e;
				String[] split = string.split(";");
				String code = split[0];
				String nom = split[1];
				double quantite = Double.valueOf(split[2]);
				String unite = split[3];
				double achat = -1;
				double vente = -1;
				if(!split[4].equals("NA"))
					achat = Double.valueOf(split[4]);
				if(!split[5].equals("NA"))
					vente = Double.valueOf(split[5]);
				if(achat==-1) {
					if(vente==-1)
						e = new Produit(code, nom, quantite, unite);
					else
						e = new Produit(code, nom, quantite, unite, vente);
				}else if(vente==-1) {
					e = new MatierePremiere(code, nom, achat, quantite, unite);
				}else {
					e = new Produit(code, nom, achat, quantite, unite, vente);
				}
				cU.addStock(e);
			}
		} catch (IOException e) {
			System.out.println("Le fichier elements.csv n'a pas été trouvé!");
		}
		
		Path pC = Paths.get("chaines.csv");
		try {
			ArrayList<String> lignes = (ArrayList<String>) Files.readAllLines(pC);
			lignes.remove(0);
			for (String string : lignes) {
				ChaineDeProduction c;
				string = string.replaceAll("[ ]", "");
				String[] split = string.split(";");
				String code = split[0];
				String nom = split[1];
				c = new ChaineDeProduction(code, nom);
				ControleurChaineDeProduction cCDP = new ControleurChaineDeProduction(c);
				String[] splitE = split[2].split(",");
				String[] splitS = split[3].split(",");
				int inverse = 0;
				Element e = null;
				for (String string2 : splitE) {
					if(inverse==0) {
						string2 = string2.substring(1, string2.length());
						try {
							e = (Element) cU.getStock(string2).clone();
						} catch (CloneNotSupportedException e1) {
							e1.printStackTrace();
						}
						inverse = 1;
					}else {
						string2 = string2.substring(0, string2.length()-1);
						ControleurElement cE = new ControleurElement(e);
						cE.changeQuantite(Double.valueOf(string2));
						inverse = 0;
						cCDP.addEntrant(e);
					}
				}
				inverse = 0;
				for (String string2 : splitS) {
					if(inverse==0) {
						string2 = string2.substring(1, string2.length());
						try {
							e = (Element) cU.getStock(string2).clone();
						} catch (CloneNotSupportedException e1) {
							e1.printStackTrace();
						}
						inverse = 1;
					}else {
						string2 = string2.substring(0, string2.length()-1);
						ControleurElement cE = new ControleurElement(e);
						cE.changeQuantite(Double.valueOf(string2));
						inverse = 0;
						cCDP.addSortant(e);
					}
				}
				cU.addChaine(c);
			}
		} catch (IOException e) {
			System.out.println("Le fichier chaines.csv n'a pas été trouvé!");
		}
		return u;
	}
	
	/**
	 * Sauvegarde les Element et les CHaineDeProduction dans un fichier CSV
	 */
	public default void saveCSV(ControleurUsine u) {
		Path p = Paths.get("e1.csv");
		try {
			Files.write(p, String.format("Code;Nom;Quantite;unite;achat;vente\n").getBytes());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		for (String cle : u.getStocks().keySet()) {
			Element e = u.getStock(cle);
			String entree = e.getCode()+";"+e.getNom()+";"+e.getQuantite()+";"+e.getUniteQuantite()+";";
			if(e.getPrixAchat()==0)
				entree += "NA;";
			else
				entree += e.getPrixAchat()+";";
			if(e.getPrixVente()==0)
				entree += "NA\n";
			else
				entree += e.getPrixVente()+"\n";
			try {
				Files.write(p, String.format(entree).getBytes(), APPEND);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		Path p2 = Paths.get("c1.csv");
		try {
			Files.write(p2, String.format("Code;Nom;Entree (code,qte);Sortie (code,qte)\n").getBytes());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		for (ChaineDeProduction c : u.getChaines()) {
			String entree = c.getCode()+";"+c.getNom()+";";
			int i = 0;
			for (String key : c.getEntrants().keySet()) {
				entree += "("+c.getEntrants().get(key).getCode()+","+c.getEntrants().get(key).getQuantite()+")";
				i++;
				if(i<c.getEntrants().size()-1)
					entree+=",";
				else
					entree+=";";
			}
			i = 0;
			for (String key : c.getSortants().keySet()) {
				entree += "("+c.getSortants().get(key).getCode()+","+c.getSortants().get(key).getQuantite()+")";
				i++;
				if(i<c.getSortants().size()-1)
					entree+=",";
				else
					entree+="\n";
			}
			try {
				Files.write(p2, String.format(entree).getBytes(), APPEND);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

}
