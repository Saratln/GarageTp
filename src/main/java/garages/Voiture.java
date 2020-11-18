package garages;

import java.io.PrintStream;
import java.util.*;

public class Voiture {

    private final String immatriculation;
    private final List<Stationnement> myStationnements = new LinkedList<>();

    public Voiture(String i) {
        if (null == i) {
            throw new IllegalArgumentException("Une voiture doit avoir une immatriculation");
        }

        immatriculation = i;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    /**
     * Fait rentrer la voiture au garage Précondition : la voiture ne doit pas
     * être déjà dans un garage
     *
     * @param g le garage où la voiture va stationner
     * @throws java.lang.Exception Si déjà dans un garage
     */
    public void entreAuGarage(Garage g) throws Exception {
        // Et si la voiture est déjà dans un garage ?
        if (this.estDansUnGarage()) {
            throw new IllegalArgumentException("La voiture est déjà dans un garage");
        }
        Stationnement s = new Stationnement(this, g);
        myStationnements.add(s);
    }

    /**
     * Fait sortir la voiture du garage Précondition : la voiture doit être dans
     * un garage
     *
     * @throws java.lang.Exception si la voiture n'est pas dans un garage
     */
    public void sortDuGarage() throws Exception {
        if (!this.estDansUnGarage()) {
            throw new IllegalArgumentException("La voiture doit être dans le garage pour en sortir");
        }
        if (this.estDansUnGarage()) {
            Stationnement dernierS = myStationnements.get(myStationnements.size()-1);
            dernierS.terminer();
        }

    }
    // Trouver le dernier stationnement de la voiture
    // Terminer ce stationnement

/**
 * @return l'ensemble des garages visités par cette voiture
 */
public Set<Garage> garagesVisites() {
        Set<Garage> garageVisites = new HashSet();
        myStationnements.forEach(s -> {
            garageVisites.add(s.getGarage());
        });
        return garageVisites;
    }

    /**
     * @return vrai si la voiture est dans un garage, faux sinon
     */
    public boolean estDansUnGarage() {
        if (myStationnements.size() - 1 == -1) {
            return false;
        }
        // on récupère le dernier stationnement effectué
        Stationnement dernierS = myStationnements.get(myStationnements.size() - 1);
        if (dernierS.estEnCours()) {
            return true;
        } else {
            return false;
        }
    }
        /**
         * Pour chaque garage visité, imprime le nom de ce garage suivi de la
         * liste des dates d'entrée / sortie dans ce garage
         * <br>Exemple :
         * <pre>
         * Garage Castres:
         *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
         *		Stationnement{ entree=28/01/2019, en cours }
         *  Garage Albi:
         *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
         * </pre>
         *
         * @param out l'endroit où imprimer (ex: System.out)
         */
    public void imprimeStationnements(PrintStream out) {
        List<Stationnement> stationnements = new LinkedList<>(myStationnements);

        for (int i = 0; i < stationnements.size(); i++) {
            String garage = stationnements.get(i).getGarage().toString();
            out.append(garage + "\n");
            out.append(stationnements.get(i).toString() + "\n");

            for (int j = i + 1; j < stationnements.size(); j++) {
                if (stationnements.get(j).getGarage() == stationnements.get(i).getGarage()) {
                    out.append(stationnements.get(j).toString() + "\n");
                    stationnements.remove(stationnements.get(j));
                }
            }
        }
    }

}
