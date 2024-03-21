package villagegaulois;

import histoire.VillageSansChefException;
import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Etal;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;
	
	public Village(String nom, int nbVillageoisMaximum, int nbEtalsMaximum) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		this.marche = new Marche(nbEtalsMaximum);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() throws VillageSansChefException {
		StringBuilder chaine = new StringBuilder();
		if(chef == null) {
			throw new VillageSansChefException("Le village no possède pas de chef");
		}
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les lÃ©gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	
	
	private static class Marche{
		private Etal[] etals;
		
		public Marche(int nombreEtals) {
			this.etals = new Etal[nombreEtals];
			for(int i=0; i<nombreEtals; i++) {
				etals[i] = new Etal();
			}
		}
		
		public void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		
		public int trouverEtalLibre() {
			int i = 0;
			while( i <= etals.length ) {
				if(!etals[i].isEtalOccupe()) {
					return i;
				}
				i++;
			}
			return -1;
		}
		
		public Etal[] trouverEtals(String produit) {
			Etal[] tableau = new Etal[etals.length];
			int i = 0;
			int j = 0;
			while(i < etals.length) {
				if(etals[i].contientProduit(produit)) {
					tableau[j] = etals[i];
					j++;
				}
				i++;
			}
			Etal[] tableau2 = new Etal[j];
			for(int k = 0; k < j; k++) {
				tableau2[k] = tableau[k];
			}
			return tableau2;
		}
		
		public Etal trouverVendeur(Gaulois gaulois) {
			int i = 0;
			while( i <= etals.length ) {
				if(etals[i].getVendeur() == gaulois) {
					return etals[i];
				}
				i++;
			}
			return null;
		}
		
		public String afficherMarche() {
			int i = 0;
			int nbEtalsVide = 0;
			StringBuilder chaine = new StringBuilder();
			while( i < etals.length ) {
				if(etals[i].isEtalOccupe()) {
					chaine.append(etals[i].afficherEtal());
				}
				else {
					nbEtalsVide++;
				}
				i++;
			}
			chaine.append("Il reste " + nbEtalsVide + " non utilisés dans le marché.");
			return chaine.toString();
		}
	
	} // Fin classe Marche //
	
	
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		int rang = this.marche.trouverEtalLibre();
		this.marche.utiliserEtal(rang, vendeur, produit, nbProduit);
		
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + " " + produit + ".\n");
		chaine.append("Le vendeur " + vendeur.getNom() + " vend des fleurs à l'étal nº" + (rang+1) + ".");
		return chaine.toString();
		
	}
	
	public String rechercherVendeursProduit(String produit) {
		Etal[] vendeurs = this.marche.trouverEtals(produit);
		StringBuilder listeVendeurs = new StringBuilder();
		StringBuilder chaine = new StringBuilder();
		int nbVendeurs = vendeurs.length;
		if(nbVendeurs == 0) {
			chaine.append("Il n'y a pas de vendeur qui propose des " + produit + " au marché.");
		}
		else if(nbVendeurs == 1) {
			chaine.append("Seul le vendeur " + vendeurs[0].getVendeur().getNom() + " propose des fleurs au marché.");
		}
		else {
			chaine.append("Les vendeur qui proposent des " + produit + " sont :\n");
			Gaulois v;
			for(int i = 0; i < vendeurs.length; i++) {
				v = vendeurs[i].getVendeur();
				chaine.append(" -" + v.getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur);
	}
	
	public String partirVendeur(Gaulois vendeur) {
		Etal aLiberer = rechercherEtal(vendeur);
		return aLiberer.libererEtal(); 
	}
	
	public String afficherMarche() {
		System.out.println("Le marché du village '" + this.nom + "' possède plusieurs étals:");
		return marche.afficherMarche();
	}
	
	
	
	
	
	
	
}