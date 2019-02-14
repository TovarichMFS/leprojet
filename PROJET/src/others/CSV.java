/**
 * 
 */
package others;

import static java.nio.file.StandardOpenOption.APPEND;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.CellEditor;

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
	 * @throws IOException 
	 */
	public default Usine chargerCSV() throws IOException {
		Usine u = new Usine();
		ControleurUsine cU = new ControleurUsine(u);
		Path pE = Paths.get("elements.csv");

		ArrayList<String> lignes = (ArrayList<String>) Files.readAllLines(pE);
		lignes.remove(0);
		for (String string : lignes) {
			Element e;
			String[] split = string.split(";");
			String code = split[0];
			String nom = split[1];
			nom = nom.replaceAll("§", "%");
			double quantite = Double.valueOf(split[2]);
			String unite = split[3];
			double achat = 0;
			double vente = 0;
			if(!split[4].equals("NA"))
				achat = Double.valueOf(split[4]);
			if(!split[5].equals("NA"))
				vente = Double.valueOf(split[5]);
			int demande = Integer.valueOf(split[7]);
			if(achat==-1) {
				if(vente==-1)
					e = new Produit(code, nom, quantite, unite, demande);
				else
					e = new Produit(code, nom, quantite, unite, vente, demande);
			}else if(vente==-1) {
				e = new MatierePremiere(code, nom, achat, quantite, unite, demande);
			}else {
				e = new Produit(code, nom, achat, quantite, unite, vente, demande);
			}
			cU.addStock(e);
		}
		
		Path pC = Paths.get("c1.csv");

		lignes = (ArrayList<String>) Files.readAllLines(pC);
		lignes.remove(0);
		for (String string : lignes) {
			ChaineDeProduction c;
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
					if(cU.getStock(string2)!=null)
						try {
							e = (Element) cU.getStock(string2).clone();
						} catch (CloneNotSupportedException e1) {
							e1.printStackTrace();
						}
					else
						e = new Produit(string2, "", 0, "", 0);
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
			int niveau = Integer.parseInt(split[4]);
			cCDP.changeNiveau(niveau);
			cU.addChaine(c);
		}

		return u;
	}
	
	/**
	 * Sauvegarde les Element et les CHaineDeProduction dans un fichier CSV
	 */
	public default void saveCSV(ControleurUsine u) {
		Path p = Paths.get("e1.csv");
		try {
			Files.write(p, String.format("Code;Nom;Quantite;unite;achat;vente;?;demande\n").getBytes());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		for (String cle : u.getStocks().keySet()) {
			Element e = u.getStock(cle);
			String nom = e.getNom().replaceAll("%", "§");
			String entree = e.getCode()+";"+nom+";"+e.getQuantite()+";"+e.getUniteQuantite()+";";
			if(e.getPrixAchat()==0)
				entree += "NA;";
			else
				entree += e.getPrixAchat()+";";
			if(e.getPrixVente()==0)
				entree += "NA;";
			else
				entree += e.getPrixVente()+";";
			String demande = e.getDemande()+"";
			entree+="?;"+demande+"\n";
			try {
				Files.write(p, String.format(entree).getBytes(), APPEND);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		Path p2 = Paths.get("c1.csv");
		try {
			Files.write(p2, String.format("Code;Nom;Entree (code,qte);Sortie (code,qte);niveau\n").getBytes());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		for (ChaineDeProduction c : u.getChaines()) {
			String nom = c.getNom().replaceAll("%", "§");
			String entree = c.getCode()+";"+nom+";";
			int i = 0;
			for (String key : c.getEntrants().keySet()) {
				entree += "("+c.getEntrants().get(key).getCode()+","+c.getEntrants().get(key).getQuantite()+")";
				i++;
				entree+=",";
			}
			entree = entree.substring(0, entree.length()-1);
			entree+=";";
			i = 0;
			for (String key : c.getSortants().keySet()) {
				entree += "("+c.getSortants().get(key).getCode()+","+c.getSortants().get(key).getQuantite()+")";
				i++;
				entree+=",";
			}
			entree = entree.substring(0, entree.length()-1);
			entree+=";"+c.getNiveau()+"\n";
			try {
				Files.write(p2, String.format(entree).getBytes(), APPEND);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

}
