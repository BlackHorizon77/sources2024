package main.java.graphe;

import java.util.ArrayList;
import java.util.List;

public class GrapheLArcs extends Graphe {
    private List<String> listeSommets;
    private List<Arc> listeArcs;
    private int nbNoeuds;
    public GrapheLArcs() {
        listeSommets = new ArrayList<>();
        listeArcs = new ArrayList<>();
        nbNoeuds = 0;
    }

    public GrapheLArcs(String str) {
        this();
        this.peupler(str);
    }

    @Override
    public void ajouterSommet(String noeud) {
        if (!contientSommet(noeud)) {
            listeSommets.add(noeud);
            ++nbNoeuds;
        }
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        if (valeur < 0) {
            throw new IllegalArgumentException("Les valuations ne doivent pas etre negatives " + valeur);
        }
        if (contientArc(source,destination)) {
            throw new IllegalArgumentException("Un arc existe déjà entre les sommets : " + source + " et " + destination);
        }
        if (!contientSommet(source)) {
            ajouterSommet(source);
        }
        if (!contientSommet(destination)) {
            ajouterSommet(destination);
        }
        if (!contientArc(source,destination)) {
            listeArcs.add(new Arc(source, destination, valeur));
        }
    }

    @Override
    public void oterSommet(String noeud) {
        if (contientSommet(noeud)) {
            listeSommets.remove(noeud);
            --nbNoeuds;
            for (Arc arc : listeArcs) {
                if (arc.contientSommet(noeud)) {
                    listeArcs.remove(arc);
                }
            }
        }
    }

    @Override
    public void oterArc(String source, String destination) {
        if(contientArc(source,destination)) {
            for (Arc arc : listeArcs) {
                if (arc.est(source, destination)) {
                    listeArcs.remove(arc);
                }
            }
        } else {
            throw new IllegalArgumentException("Aucun arc n'existe entre les sommets : " + source + " et " + destination);
        }
    }

    @Override
    public List<String> getSommets() {
        return listeSommets;
    }

    @Override
    public List<String> getSucc(String sommet) {
        List<String> listeSucc = null;
        if (contientSommet(sommet)) {
            listeSucc = new ArrayList<>();
            for (Arc arc : listeArcs) {
                if (arc.getSource().equals(sommet)) {
                    listeSucc.add(arc.getDestination());
                }
            }
        }
        return listeSucc;
    }

    @Override
    public int getValuation(String src, String dest) {
        for (Arc arc : listeArcs) {
            if (arc.est(src, dest)) {
                return arc.getValuation();
            }
        }
        throw new IllegalArgumentException(("L'arc (" + src + ", " + dest + ") n'existe pas"));
    }

    @Override
    public boolean contientSommet(String sommet) {
        return listeSommets.contains(sommet);
    }

    @Override
    public boolean contientArc(String src, String dest) {
        if (contientSommet(src) && contientSommet(dest)) {
            for (Arc arc : listeArcs) {
                if (arc.est(src, dest)) {
                    return true;
                }
            }
        }
        return false;
    }
}
