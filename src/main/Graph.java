package main;

import java.util.*;

public class Graph {
    private int n;
    private List<Node>[] adjList;
    private int[] distance;
    private PriorityQueue queue;
    private Set<Integer> besucht;

    public Graph(int nodes) {
        n = nodes;
        distance = new int[nodes];
        queue = new PriorityQueue(nodes);
        adjList = new ArrayList[nodes];
        besucht = new HashSet<>();
        for(int i = 0; i < adjList.length; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    public boolean insertNode(int key, String edge) {
        int nodeindex = edge.indexOf('w');
        int destkey = Integer.parseInt(edge.substring(0, nodeindex));
        int weight = Integer.parseInt(edge.substring(nodeindex + 1));

        Node node = new Node(destkey, weight);

        if(key < adjList.length) {
            if(!adjList[key].contains(node)) {
                adjList[key].add(node);
                return true;
            }
        }
        return false;
    }

    public int getNodes() { return n; }

    public int[] getDistance() {
        return distance;
    }

    public void dijkstra(int start) {
        for(int i = 0; i < distance.length; i++) {
            distance[i] = 999999999;
            queue.insert(new Node(i, 0));
        }

        distance[start] = 0;

        while(!queue.isEmpty()) {
            Node u = queue.deleteMin();
            besucht.add(u.getKey());

            List<Node> lst = adjList[u.getKey()];

            for(Node n : lst) {
                if(queue.findKey(n.getKey()) > 0) {
                    int newdistance = distance[u.getKey()] + n.getWeight();
                    if (distance[n.getKey()] > newdistance && newdistance > 0) {
                        distance[n.getKey()] = newdistance;
                        int index = queue.findKey(n.getKey());
                        if(index > 0) {
                            queue.decreaseKey(index, newdistance);
                        }
                    }
                }
            }
        }
    }
}
