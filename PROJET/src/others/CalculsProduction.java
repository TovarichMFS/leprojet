/**
 * 
 */
package others;

import java.util.HashMap;

import controleur.ControleurUsine;
import modele.ChaineDeProduction;
import modele.Element;

/**
 * @author tovarich
 *
 */
public interface CalculsProduction {
	/**
	 * Calcule les revenus/coûts de la production prévue. Retourne une exception si la production est impossible.
	 * @return double
	 * @throws CalculException 
	 * @throws CloneNotSupportedException 
	 */
	@SuppressWarnings("unchecked")
	public default double calculerProduction(ControleurUsine u) throws CalculException, CloneNotSupportedException {
		HashMap<String,Element> cpStocks = new HashMap<String, Element>();
		for (String key : u.getStocks().keySet()) {
			cpStocks.put(key, u.getStocks().get(key).clone());
		}
		HashMap<String,Element> cpAchats = new HashMap<String, Element>();
		for (String key : u.getListeAchats().keySet()) {
			cpAchats.put(key, u.getListeAchats().get(key).clone());
		}
		double montant = 0;
		for (ChaineDeProduction c : u.getChaines()) {
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
		u.setListeAchats(cpAchats);
		return montant;
	}
	
	public default HashMap<String,Double> calculResultatDemande(ControleurUsine u){
		HashMap<String,Double> res = new HashMap<String,Double>();
		for (ChaineDeProduction c : u.getChaines()) {
			for (String key : c.getSortants().keySet()) {
				Element s = c.getSortants().get(key);
				double percent = (c.getSortants().get(s.getCode()).getQuantite() + (s.getQuantite()*c.getNiveau()))/s.getDemande();
				percent*=100;
				if(res.containsKey(key))
					res.put(s.getCode(), (res.get(key) + percent)/2);
				else
					res.put(s.getCode(), percent);
			}
		}
		double tot = 0;
		for(String key : res.keySet()) {
			tot+=res.get(key);
		}
		tot/=res.keySet().size();
		res.put(null, tot);
		return res;
	}

}
