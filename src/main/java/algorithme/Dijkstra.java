package main.java.algorithme;


import main.java.graphe.IGrapheConst;

import java.util.Map;
import java.util.PriorityQueue;

public class Dijkstra {


    public static void dijkstra(IGrapheConst g, String source, Map<String, Integer> dist, Map<String, String> prev) {
        PriorityQueue<String> sommetsRestants = new PriorityQueue<>();
        String sommetCourant = source;
        sommetsRestants.add(sommetCourant);
        dist.put(sommetCourant, 0);
        while (!sommetsRestants.isEmpty()) {
            sommetCourant = sommetsRestants.poll();
            for (String succ : g.getSucc(sommetCourant)) {
                int distance = g.getValuation(sommetCourant, succ) + dist.get(sommetCourant);
                if (!dist.containsKey(succ) || distance < dist.get(succ)) {
                    dist.put(succ, distance);
                    prev.put(succ, sommetCourant);
                    sommetsRestants.remove(succ);
                    sommetsRestants.add(succ);
                }
            }
        }
    }
}
