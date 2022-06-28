package main;

public class Node {

    private int key;
    private int weight;

    public Node(int key, int weight) {
        this.key = key;
        this.weight = weight;
    }

    public int getKey() { return key; }

    public void setKey(int key) { this.key = key; }

    public int getWeight() { return weight; }

    public void setWeight(int weight) { this.weight = weight; }

    public static int compare(Node n1, Node n2) {
        if(n1 == null && n2 == null) {
            return 0;
        }
        else if(n1 == null && n2 != null) {
            return -1;
        }
        else if(n1 != null && n2 == null) {
            return 1;
        }
        else {
            return n1.getKey() - n2.getKey();
        }
    }

    public static int compareWeight(Node n1, Node n2) {
        if(n1 == null && n2 == null) {
            return 0;
        }
        else if(n1 == null && n2 != null) {
            return -1;
        }
        else if(n1 != null && n2 == null) {
            return 1;
        }
        else {
            return n1.getWeight() - n2.getWeight();
        }
    }
}

