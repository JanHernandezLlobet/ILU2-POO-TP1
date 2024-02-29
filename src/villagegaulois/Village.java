package villagegaulois;

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

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
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
			while( i <= etals.length ) {
				if(etals[i].contientProduit(produit)) {
					tableau[j] = etals[i];
					j++;
				}
				i++;
			}
			return tableau;
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
		
		public void afficherMarche() {
			int i = 0;
			int nbEtalsVide = 0;
			while( i <= etals.length ) {
				if(etals[i] != null) {
					etals[i].afficherEtal();
				}
				else {
					nbEtalsVide++;
				}
				i++;
			}
			System.out.println("Il reste " + nbEtalsVide + " non utilisés dans le marché.");			
		}
	
	} // Fin classe Marche //
	
	
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		int rang = this.marche.trouverEtalLibre();
		this.marche.utiliserEtal(rang, vendeur, produit, nbProduit);
		
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + " " + produit + ".\n");
		chaine.append("Le vendeur " + vendeur.getNom() + " vend des fleurs à l'étal nº1.");
		return chaine.toString();
		
	}
	
	public String rechercherVendeursProduit(String produit) {
		int taille = this.marche.
		this.marche.trouverEtals(produit));
	}
	
	
	
	
	
	
	
	
	
	
	
	
}