package main.java.graphe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GrapheLAdj extends Graphe {
    private HashMap<String, ArrayList<Arc>> listeAdjacence;
    public GrapheLAdj() {
        listeAdjacence = new HashMap<>();
    }

    public GrapheLAdj(String str) {
        this();
        this.peupler(str);
    }

    @Override
    public void ajouterSommet(String noeud) {
        if (!contientSommet(noeud)) {
            listeAdjacence.put(noeud, new ArrayList<>());
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
        if (!contientArc(source, destination)) {
            ArrayList<Arc> listeArcs = listeAdjacence.get(source);
            listeArcs.add(new Arc(source, destination, valeur));
            listeAdjacence.put(source,listeArcs);
        }
    }

    @Override
    public void oterSommet(String noeud) {
        if (contientSommet(noeud)) {
            listeAdjacence.remove(noeud);
            for (String sommet : listeAdjacence.keySet()) {
                ArrayList<Arc> listeArcs = listeAdjacence.get(sommet);
                for (Arc arc : listeArcs) {
                    if (arc.contientSommet(noeud)) {
                        listeArcs.remove(arc);
                    }
                }
                listeAdjacence.put(sommet,listeArcs);
            }
        }
    }

    @Override
    public void oterArc(String source, String destination) {
        if (contientArc(source, destination)) {
            ArrayList<Arc> listeArcs = listeAdjacence.get(source);
            for (Arc arc: listeArcs) {
                if (arc.est(source,destination)) {
                    listeArcs.remove(arc);
                    break;
                }
            }
            listeAdjacence.put(source,listeArcs);
        } else {
            throw new IllegalArgumentException("Aucun arc n'existe entre les sommets : " + source + " et " + destination);
        }
    }

    @Override
    public List<String> getSommets() {
        ArrayList<String> listeSommets = new ArrayList<>();
        for (String sommet : listeAdjacence.keySet()) {
            listeSommets.add(sommet);
        }
        return listeSommets;
    }

    @Override
    public List<String> getSucc(String sommet) {
        List<String> listeSucc = null;
        if (contientSommet(sommet)) {
            listeSucc = new ArrayList<>();
            for (Arc arc : listeAdjacence.get(sommet)) {
                listeSucc.add(arc.getDestination());
            }
        }
        return listeSucc;
    }

    @Override
    public int getValuation(String src, String dest) {
        for (Arc arc : listeAdjacence.get(src)) {
            if (arc.est(src, dest)) {
                return arc.getValuation();
            }
        }
        throw new IllegalArgumentException("L'arc (" + src + ", " + dest + ") n'existe pas");
    }

    @Override
    public boolean contientSommet(String sommet) {
        return listeAdjacence.containsKey(sommet);
    }

    @Override
    public boolean contientArc(String src, String dest) {
        if (contientSommet(src) && contientSommet(dest)) {
            for (Arc arc : listeAdjacence.get(src)) {
                if (arc.est(src, dest)) {
                    return true;
                }
            }
        }
        return false;
    }
}
