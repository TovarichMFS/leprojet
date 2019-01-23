/**
 * 
 */
package controleur;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import static java.nio.file.StandardOpenOption.APPEND;

import modele.ChaineDeProduction;
import modele.Element;
import modele.MatierePremiere;
import modele.Produit;
import modele.Usine;

/**
 * @author tovarich
 *
 */
public class ControleurUsine {

	/**
	 * 
	 */
	private Usine u;
	public ControleurUsine(Usine u) {
		this.u = u;
	}
	
	public void addStock(Element e) {
		ControleurElement cE = new ControleurElement(e);
		this.u.getStocks().put(cE.getCode(), e);
	}
	
	public Element getStock(String code) {
		return this.getStocks().get(code);
	}
	
	public HashMap<String,Element> getStocks(){
		return this.u.getStocks();
	}
	
	public void rmStock(Element e) {
		this.getStocks().remove(e);
	}
	
	public void addChaine(ChaineDeProduction c) {
		this.u.getChaines().add(c);
	}
	
	public ChaineDeProduction getChaine(String code) {
		for (ChaineDeProduction c : this.getChaines()) {
			ControleurChaineDeProduction cCDP = new ControleurChaineDeProduction(c);
			if(code.equals(cCDP.getCode()))
				return c;
		}
		return null;
	}
	
	public ArrayList<ChaineDeProduction> getChaines() {
		return this.u.getChaines();
	}
	
	public void rmChaine(ChaineDeProduction c) {
		this.getChaines().remove(c);
	}
	
	public double calculerProduction() {
		return 0;
	}
	
	public void chargerCSV() {
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
				System.out.println(e.toString());
				this.addStock(e);
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
							e = (Element) this.getStock(string2).clone();
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
							e = (Element) this.getStock(string2).clone();
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
				this.addChaine(c);
			}
		} catch (IOException e) {
			System.out.println("Le fichier chaines.csv n'a pas été trouvé!");
		}
	}
	
	public void saveCSV() {
		Path p = Paths.get("e1.csv");
		try {
			Files.write(p, String.format("Code;Nom;Quantite;unite;achat;vente\n").getBytes());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (String cle : this.getStocks().keySet()) {
			Element e = this.getStock(cle);
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
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		Path p2 = Paths.get("c1.csv");
		try {
			Files.write(p2, String.format("Code;Nom;Entree (code,qte);Sortie (code,qte)\n").getBytes());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (ChaineDeProduction c : this.getChaines()) {
			String entree = c.getCode()+";"+c.getNom()+";";
			for (int i=0;i<c.getEntrants().size();i++) {
				entree += "("+c.getEntrants().get(i).getCode()+","+c.getEntrants().get(i).getQuantite()+")";
				if(i<c.getEntrants().size()-1)
					entree+=",";
				else
					entree+=";";
			}
			for (int i=0;i<c.getSortants().size();i++) {
				entree += "("+c.getSortants().get(i).getCode()+","+c.getSortants().get(i).getQuantite()+")";
				if(i<c.getSortants().size()-1)
					entree+=",";
				else
					entree+="\n";
			}
			try {
				Files.write(p2, String.format(entree).getBytes(), APPEND);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public String toString() {
		return this.u.toString();
	}

}
