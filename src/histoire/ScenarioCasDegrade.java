package histoire;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ScenarioCasDegrade {

	
	public static void main(String[] args) {
		
		/*   Test Exception 1 */
		
		Etal etal = new Etal();
		etal.libererEtal();
		System.out.println("Fin du test 1");

		/*   Test Exception 2 */
		
		Village village = new Village("le village des irréductibles", 10, 5);
		Chef abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);

		Gaulois bonemine = new Gaulois("Bonemine", 7);
		try {
			System.out.println(etal.acheterProduit(10, bonemine));
		} catch(IllegalStateException e) {
			e.printStackTrace();
		}
			
		System.out.println(village.installerVendeur(bonemine, "fleurs", 20));
		Etal etalFleur = village.rechercherEtal(bonemine);

		try {
			System.out.println(etalFleur.acheterProduit(-80, abraracourcix));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		System.out.println("fin du test 2");
	}
	
}
