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
import others.CSV;
import others.CalculException;

/**
 * @author tovarich
 *
 */
public class ControleurUsine implements CSV{

	private Usine u;
	/**
	 * Construit un ControleurUsine sur l'Usine u
	 * @param u
	 */
	public ControleurUsine(Usine u) {
		this.u = u;
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
	public void rmChaine(String code) {
		this.getChaines().remove(this.getChaine(code));
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.u.toString();
	}

}
