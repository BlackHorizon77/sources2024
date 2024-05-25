package main.java.graphe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GrapheLAdj extends Graphe {
    HashMap<String,HashMap<String, Integer>> listeAdjacence;
    ArrayList<String> listeSommets;
    public GrapheLAdj() {
        listeAdjacence = new HashMap<>();
        listeSommets = new ArrayList<>();
    }

    public GrapheLAdj(String str) {
        this();
        this.peupler(str);
    }

    @Override
    public void ajouterSommet(String noeud) {
        if (!contientSommet(noeud)) {
            listeSommets.add(noeud);
            listeAdjacence.put(noeud, new HashMap<>());
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
            HashMap<String,Integer> mapAdjSommet = listeAdjacence.get(source);
            mapAdjSommet.put(destination,valeur);
            listeAdjacence.put(source, mapAdjSommet);
        }
    }

    @Override
    public void oterSommet(String noeud) {
        if (contientSommet(noeud)) {
            listeSommets.remove(noeud);
            listeAdjacence.remove(noeud);
            for (String sommet : listeSommets) {
                HashMap<String,Integer> mapAdjSommet = listeAdjacence.get(sommet);
                if (mapAdjSommet.containsKey(noeud)) {
                    mapAdjSommet.remove(noeud);
                }
                listeAdjacence.put(sommet,mapAdjSommet);
            }
        } else {
            throw new IllegalArgumentException(("Le sommet " + noeud + " n'existe pas"));
        }
    }

    @Override
    public void oterArc(String source, String destination) {
        boolean existeArc = false;
        HashMap<String,Integer> mapAdjSommet = listeAdjacence.get(source);
        if (mapAdjSommet.containsKey(destination)) {
            mapAdjSommet.remove(destination);
        }
        listeAdjacence.put(source,mapAdjSommet);
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
        ArrayList<String> listeSucc = null;
        if (contientSommet(sommet)) {
            listeSucc = new ArrayList<>();
            for (String noeud : listeAdjacence.get(sommet).keySet()) {
                listeSucc.add(noeud);
            }
        }
        return listeSucc;
    }

    @Override
    public int getValuation(String src, String dest) {
        if (contientArc(src,dest)) {
            return listeAdjacence.get(src).get(dest);
        }
        throw new IllegalArgumentException(("L'arc (" + src + ", " + dest + ") n'existe pas"));
    }

    @Override
    public boolean contientSommet(String sommet) {
        return listeSommets.contains(sommet);
    }

    @Override
    public boolean contientArc(String src, String dest) {
        return (listeAdjacence.get(src).containsKey(dest));
    }
}
