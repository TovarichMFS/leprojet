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
import others.CalculException;

/**
 * @author tovarich
 *
 */
public class ControleurUsine {

	private Usine u;
	/**
	 * Construit un ControleurUsine sur l'Usine u
	 * @param u
	 */
	public ControleurUsine(Usine u) {
		this.u = u;
	}
	
	/**
	 * Retourne le nom de l'Usine u
	 * @return String
	 */
	public String getNom(){
		return this.u.getNom();
	}
	
	/**
	 * Modifie le nom de l'Usine u
	 * @param nom
	 */
	public void setNom(String nom){
		this.u.setNom(nom);
	}
	
	/**
	 * Ajoute un Element e dans le stock de l'Usine u
	 * @param e
	 */
	public void addStock(Element e) {
		ControleurElement cE = new ControleurElement(e);
		this.u.getStocks().put(cE.getCode(), e);
	}
	
	/**
	 * Retourne l'Element du stock dont le code est passé en paramètre
	 * @param code
	 * @return Element
	 */
	public Element getStock(String code) {
		return this.getStocks().get(code);
	}
	
	/**
	 * Retourne le stock de l'Usine u
	 * @return HashMap<String,Element>
	 */
	public HashMap<String,Element> getStocks(){
		return this.u.getStocks();
	}
	
	/**
	 * Retire l'Element e du stock de l'Usine u
	 * @param code
	 */
	public void rmStock(String code) {
		this.getStocks().remove(code);
	}
	
	/**
	 * Ajoute la ChaineDeProduction c à la liste des chaines de l'Usine u
	 * @param c
	 */
	public void addChaine(ChaineDeProduction c) {
		this.u.getChaines().add(c);
	}
	
	/**
	 * Retourne la ChaineDeProduction correspondante au code passé en paramètre
	 * @param code
	 * @return ChaineDeProduction
	 */
	public ChaineDeProduction getChaine(String code) {
		for (ChaineDeProduction c : this.getChaines()) {
			ControleurChaineDeProduction cCDP = new ControleurChaineDeProduction(c);
			if(code.equals(cCDP.getCode()))
				return c;
		}
		return null;
	}
	
	/**
	 * Retourne la liste des ChaineDeProduction de l'Usine u
	 * @return ArrayList<ChaineDeProduction>
	 */
	public ArrayList<ChaineDeProduction> getChaines() {
		return this.u.getChaines();
	}
	
	/**
	 * Retire la ChaineDeProduction c de la liste des chaines de l'Usine u
	 * @param c
	 */
	public void rmChaine(ChaineDeProduction c) {
		this.getChaines().remove(c);
	}
	
	/**
	 * Retourne la liste des achats de l'Usine u
	 * @return listeAchats
	 */
	public HashMap<String, Element> getListeAchats(){
		return this.u.getListeAchats();
	}
	
	/**
	 * Retourne l'Elément de la liste d'achats selon son code
	 * @param code
	 * @return Element
	 */
	public Element getAchat(String code) {
		return this.getListeAchats().get(code);
	}
	
	/**
	 * Ajoute l'Element e à la liste d'achats
	 * @param e
	 */
	public void addAchat(Element e) {
		this.getListeAchats().put(e.getCode(),e);
	}
	
	/**
	 * Retire un Element de la liste d'achats selon son code
	 * @param code
	 */
	public void rmAchat(String code) {
		this.getListeAchats().remove(code);
	}
	
	/**
	 * @param listeAchats the listeAchats to set
	 */
	public void setListeAchats(HashMap<String, Element> listeAchats) {
		this.u.setListeAchats(listeAchats);
	}
	
	/**
	 * Calcule les revenus/coûts de la production prévue. Retourne une exception si la production est impossible.
	 * @return double
	 * @throws CalculException 
	 * @throws CloneNotSupportedException 
	 */
	@SuppressWarnings("unchecked")
	public double calculerProduction() throws CalculException, CloneNotSupportedException {
		HashMap<String,Element> cpStocks = new HashMap<String, Element>();
		for (String key : this.getStocks().keySet()) {
			cpStocks.put(key, this.getStocks().get(key).clone());
		}
		HashMap<String,Element> cpAchats = new HashMap<String, Element>();
		for (String key : this.getListeAchats().keySet()) {
			cpAchats.put(key, this.getListeAchats().get(key).clone());
		}
		double montant = 0;
		for (ChaineDeProduction c : this.getChaines()) {
			for (String key : c.getEntrants().keySet()) {
				Element e = c.getEntrants().get(key);
				cpStocks.get(e.getCode()).setQuantite(cpStocks.get(e.getCode()).getQuantite() - (e.getQuantite()*c.getNiveau()));
			}
			for (String key : c.getSortants().keySet()) {
				Element s = c.getSortants().get(key);
				cpStocks.get(s.getCode()).setQuantite(cpStocks.get(s.getCode()).getQuantite() + (s.getQuantite()*c.getNiveau()));
			}
		}
		for (String key : cpStocks.keySet()) {
			if(cpStocks.get(key).getQuantite()<0) {
				if(cpStocks.get(key).getPrixAchat()==0)
					throw new CalculException();
				else{
					if(cpAchats.containsKey(key)) {
						cpAchats.get(key).setQuantite(cpAchats.get(key).getQuantite() - cpStocks.get(key).getQuantite());
						montant-=cpAchats.get(key).getPrixAchat()*cpAchats.get(key).getQuantite();
					}else {
						Element tmp = cpStocks.get(key);
						tmp.setQuantite(-cpStocks.get(key).getQuantite());
						montant-=tmp.getPrixAchat()*tmp.getQuantite();
						cpAchats.put(key, tmp);
					}
				}
			}else {
				if(cpStocks.get(key).getPrixVente()!=0) {
					montant += cpStocks.get(key).getPrixVente() * cpStocks.get(key).getQuantite();
				}
			}
		}
		this.setListeAchats(cpAchats);
		return montant;
	}
	
	/**
	 * Charge les Elements et les ChaineDeProduction d'un fichier CSV dans l'objet Usine u
	 */
	public void chargerCSV() {
		this.u = new Usine(u.getNom());
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
	
	/**
	 * Sauvegarde les Element et les CHaineDeProduction dans un fichier CSV
	 */
	public void saveCSV() {
		Path p = Paths.get("e1.csv");
		try {
			Files.write(p, String.format("Code;Nom;Quantite;unite;achat;vente\n").getBytes());
		} catch (IOException e1) {
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
				e1.printStackTrace();
			}
		}
		
		Path p2 = Paths.get("c1.csv");
		try {
			Files.write(p2, String.format("Code;Nom;Entree (code,qte);Sortie (code,qte)\n").getBytes());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		for (ChaineDeProduction c : this.getChaines()) {
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.u.toString();
	}

}
