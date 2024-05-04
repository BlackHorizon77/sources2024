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
        assert (valeur > 0);
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
        } else {
            throw new IllegalArgumentException(("Le sommet " + noeud + " n'existe pas"));
        }
    }

    @Override
    public void oterArc(String source, String destination) {
        boolean existeArc = false;
        for (Arc arc : listeArcs) {
            if (arc.est(source, destination)) {
                listeArcs.remove(arc);
                existeArc = true;
            }
        }
        if (!existeArc) {
            throw new IllegalArgumentException(("L'arc (" + source + ", " + destination + ") n'existe pas"));
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
        for (Arc arc : listeArcs) {
            if (arc.est(src,dest)) {
                return true;
            }
        }
        return false;
    }
}
