package main.java.graphe;

public class Arc {
    private final String source, destination;
    private int valuation;
    public Arc(String source, String destination, int valuation) {
        this.source = source;
        this.destination = destination;
        this.valuation = valuation;
    }


    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getValuation() {
        return valuation;
    }
    public String toString() {
        return "(" + source + ", " + destination + ")";
    }

    public boolean contientSommet(String noeud) {
        return this.source.equals(noeud) || this.destination.equals(noeud);
    }
    public boolean est(String source, String destination) {
        return this.source.equals(source) && this.destination.equals(destination);
    }
}
