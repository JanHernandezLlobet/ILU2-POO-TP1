package villagegaulois;

import personnages.Gaulois;

public class Etal {
	private Gaulois vendeur;
	private String produit;
	private int quantiteDebutMarche;
	private int quantite;
	private boolean etalOccupe = false;

	public boolean isEtalOccupe() {
		return etalOccupe;
	}

	public Gaulois getVendeur() {
		return vendeur;
	}

	public void occuperEtal(Gaulois vendeur, String produit, int quantite) {
		this.vendeur = vendeur;
		this.produit = produit;
		this.quantite = quantite;
		quantiteDebutMarche = quantite;
		etalOccupe = true;
	}

	public String libererEtal() {
		StringBuilder chaine = new StringBuilder();
		try {
			etalOccupe = false;
			chaine.append("Le vendeur " + vendeur.getNom() + " quitte son Ã©tal, ");
			int produitVendu = quantiteDebutMarche - quantite;
			if (produitVendu > 0) {
				chaine.append(
						"il a vendu " + produitVendu + " " + produit + " parmi les "+ quantiteDebutMarche + " qu'il voulait vendre.\n");
			} else {
				chaine.append("il n'a malheureusement rien vendu.\n");
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return chaine.toString();
	}

	public String afficherEtal() {
		if (etalOccupe) {
			return "L'Etal de " + vendeur.getNom() + " est garni de " + quantite
					+ " " + produit + "\n";
		}
		return "L'Ã©tal est libre";
	}

	public String acheterProduit(int quantiteAcheter, Gaulois acheteur) {
		String client = "";
		StringBuilder chaine = new StringBuilder();
		try {
			client = acheteur.getNom();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		if(quantiteAcheter < 1) {
			throw new IllegalArgumentException("Tentative d'achat avec quantité inférieure à 1");
		}
		else if(!etalOccupe) {
			throw new IllegalStateException("Tentative d'achat sur un etal vide");
		}
		else {
			chaine.append(client + " veut acheter " + quantiteAcheter
					+ " " + produit + " Ã  " + vendeur.getNom());
			if (quantite == 0) {
				chaine.append(", malheureusement il n'y en a plus !");
				quantiteAcheter = 0;
			}
			if (quantiteAcheter > quantite) {
				chaine.append(", comme il n'y en a plus que " + quantite + ", "
						+ client + " vide l'Ã©tal de "
						+ vendeur.getNom() + ".\n");
				quantiteAcheter -= quantite;
				quantite = 0;
			}
			if (quantite != 0) {
				quantite -= quantiteAcheter;
				chaine.append(". " + client
						+ ", est ravi de tout trouver sur l'Ã©tal de "
						+ vendeur.getNom() + "\n");
			}
		}
		return chaine.toString();

		//return "";
	}

	public boolean contientProduit(String Pproduit) {
		return (this.produit == Pproduit);
	}

}
