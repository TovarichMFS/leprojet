/**
 * 
 */
package others;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import controleur.ControleurUsine;
import modele.ChaineDeProduction;
import modele.Element;
import modele.Stockage;
import modele.Usine;

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
	 * @throws StockageException 
	 */
	@SuppressWarnings("unchecked")
	public default double calculerProduction(ControleurUsine u) throws CalculException, CloneNotSupportedException, StockageException, NullPointerException {
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
				Stockage st = u.getStockage(e.getStockage());
				st.modifRemplissage(-(int) (e.getQuantite()*c.getNiveau()));
				cpStocks.get(e.getCode()).setQuantite(cpStocks.get(e.getCode()).getQuantite() - (e.getQuantite()*c.getNiveau()));
			}
			for (String key : c.getSortants().keySet()) {
				Element s = c.getSortants().get(key);
				Stockage st = u.getStockage(s.getStockage());
				if((int)(st.getRemplissage()+(s.getQuantite()*c.getNiveau()))<(st.getCapacite()*st.getQuantiteDispo())) {
					st.modifRemplissage((int) (s.getQuantite()*c.getNiveau()));
					cpStocks.get(s.getCode()).setQuantite(cpStocks.get(s.getCode()).getQuantite() + (s.getQuantite()*c.getNiveau()));
				}else {
					throw new StockageException(st.getCode());
				}
			}
		}
		for (String key : cpStocks.keySet()) {
			if(cpStocks.get(key).getQuantite()<0) {
				if(cpStocks.get(key).getPrixAchat()==0)
					throw new CalculException(key);
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
		u.setStocks(cpStocks);
		u.setListeAchats(cpAchats);
		return montant;
	}
	
	public default HashMap<String,Double> calculResultatDemande(ControleurUsine u){
		HashMap<String,Double> res = new HashMap<String,Double>();
		for (ChaineDeProduction c : u.getChaines()) {
			for (String key : c.getSortants().keySet()) {
				Element s = u.getStocks().get(key);
				double percent;
				if(s.getDemande()==0)
					percent = 100;
				else {
					percent = (Double.valueOf(s.getQuantite()) + Double.valueOf((c.getSortants().get(key).getQuantite()*c.getNiveau())))/Double.valueOf(s.getDemande());
					percent*=100;
					if(percent>100) {
						percent=100;
					}
					if(percent==Double.POSITIVE_INFINITY)
						percent = 0;
				}
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
	
	public default double calculerProductionSemaines(ControleurUsine u, int nbSemaines, HashMap<String,String[]> bestPrix) throws CalculException, CloneNotSupportedException, StockageException, IOException {
		ControleurUsine nU = new ControleurUsine(u.chargerCSV(nbSemaines));
		double total = 0;
		Random r = new Random();
		for(int i=0;i<nbSemaines;i++) {
			total+=u.calculerProduction(u);
			for (String key : u.getStocks().keySet()) {
				if(u.getStock(key).getPrixAchat()!=0) {
					u.getStock(key).setPrixAchat((r.nextDouble()*100)%20);
				}
				if(u.getListeAchats().containsKey(key))
					u.getAchat(key).setPrixAchat(u.getStock(key).getPrixAchat());
			}
			for (String key : u.getListeAchats().keySet()) {
				String[] ligne = {u.getStock(key).getPrixAchat()+"",nbSemaines+""};
				if(bestPrix.get(key)==null)
					bestPrix.put(key,ligne);
				else if(u.getStock(key).getPrixAchat()!=0 && u.getStock(key).getPrixAchat()<Double.parseDouble(bestPrix.get(key)[0]))
					bestPrix.put(key,ligne);
			}
		}
		return total;
	}
	
	public default HashMap<String,Double> calculResultatDemandeSemaine(ControleurUsine u,int nbSemaine) throws IOException{
		u = new ControleurUsine(u.chargerCSV(nbSemaine));
		HashMap<String,Double> res = new HashMap<String,Double>();
		res = calculResultatDemande(u);
		HashMap<String,Double> tmp = new HashMap<String,Double>();
		for(int i=0;i<nbSemaine-1;i++) {
			tmp = calculResultatDemande(u);
			for(String key : tmp.keySet()) {
				double valeurP = res.get(key);
				valeurP+=tmp.get(key);
				valeurP/=2;
				res.put(key, valeurP);
			}
		}
		return res;
	}

}
