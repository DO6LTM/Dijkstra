package main;

import java.io.*;

class Dijkstra {
    private String line = null;
    private String fileUrl = null;
    private int nodes;
    private Graph graph;

    public Dijkstra(String[] args) {
        if(args.length == 1) {
            fileUrl = args[0];
            try (BufferedReader bf = new BufferedReader(new FileReader(fileUrl))) {
                while ((line = bf.readLine()) != null) {
                    executeCommand(line);
                }
            } catch (IOException e) {
                System.out.println("Fehler beim Lesen der Datei");
            } catch (Exception e) {
                System.out.println("Ein unerwarteter Fehler ist aufgetreten");
                System.out.println(e);
            }
        }
        else {
            System.exit(1);
        }

        graph.dijkstra(0);
        printDijkstra(graph.getDistance(), graph.getNodes());
    }

    public void executeCommand(String command) {
        if(command.startsWith("#")) {
            return;
        }
        else if(command.startsWith("n = ")) {
            nodes = Integer.parseInt(command.substring(4));
            graph = new Graph(nodes);
        }
        else {
            int index = command.indexOf(':') - 1;
            int nodeKey = Integer.parseInt(command.substring(0, index));
            String edges = command.substring(index + 3);
            while(edges.length() > 0) {
                edges = instertFirstEdgeFromString(nodeKey, edges);
            }
        }
    }

    private String instertFirstEdgeFromString(int key, String text) {
        String res = "";
        int edgeindex;

        if(text.length() > 0) {
            edgeindex = text.indexOf(' ');
            if(edgeindex > -1) {
                    String edge = text.substring(0, edgeindex);
                    text = text.substring(edgeindex + 1);
                    graph.insertNode(key, edge);
                    res = text;
            }
            else {
                graph.insertNode(key, text);
                res = "";
            }
        }
        return res;
    }

    public void printDijkstra(int[] distance, int nodes) {
        String res = "0 :";

        for (int i = 0; i < distance.length; i++) {
            if(distance[i] < 999999999) {
                res = res + " " + i + "w" + distance[i];
            }
        }

        System.out.println("n = " + nodes);
        System.out.println(res);
    }

    public static void main(String[] args) {
        new Dijkstra(args);
    }
}