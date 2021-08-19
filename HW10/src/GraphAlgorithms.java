import java.util.ArrayList;
import java.util.Queue;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Your implementation of various different graph algorithms.
 *
 * @author Gabriel Chen
 * @userid gchen337
 * @GTID 903561077
 * @version 1.0
 */
public class GraphAlgorithms {

    /**
     * Performs a breadth first search (bfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * You may import/use java.util.Set, java.util.List, java.util.Queue, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     *
     * The only instance of java.util.Map that you may use is the
     * adjacency list from graph. DO NOT create new instances of Map
     * for BFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the bfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph
     */
    public static <T> List<Vertex<T>> bfs(Vertex<T> start, Graph<T> graph) {
        if (start == null) {
            throw new IllegalArgumentException("Starting vertex cannot be null");
        } else if (graph == null) {
            throw new IllegalArgumentException("Graph cannot be null");
        } else if (!graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("Starting vertex is not in the graph");
        }

        Set<Vertex<T>> visited = new HashSet<>();
        Queue<Vertex<T>> queue = new LinkedList<>();
        List<Vertex<T>> result = new ArrayList<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            Vertex<T> v = queue.poll();
            result.add(v);
            for (VertexDistance<T> vd : graph.getAdjList().get(v)) {
                if (!visited.contains(vd.getVertex())) {
                    visited.add(vd.getVertex());
                    queue.add(vd.getVertex());
                }
            }
        }
        return result;
    }

    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * *NOTE* You MUST implement this method recursively, or else you will lose
     * all points for this method.
     *
     * You may import/use java.util.Set, java.util.List, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     *
     * The only instance of java.util.Map that you may use is the
     * adjacency list from graph. DO NOT create new instances of Map
     * for DFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph
     */
    public static <T> List<Vertex<T>> dfs(Vertex<T> start, Graph<T> graph) {
        if (start == null) {
            throw new IllegalArgumentException("Starting vertex cannot be null");
        } else if (graph == null) {
            throw new IllegalArgumentException("Graph cannot be null");
        } else if (!graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("Starting vertex is not in the graph");
        }
        Set<Vertex<T>> visited = new HashSet<>();
        List<Vertex<T>> result = new ArrayList<>();

        dfs(start, graph, visited, result);
        return result;
    }

    /**
     * Recursive helper for dfs method.
     *
     * @param v the vertex to start the dfs on
     * @param graph the graph being searched through
     * @param visited the set of visited vertices
     * @param result the list that is returned
     * @param <T> the generic typing of the data
     */
    private static <T> void dfs(Vertex<T> v, Graph<T> graph, Set<Vertex<T>> visited,
                                List<Vertex<T>> result) {
        result.add(v);
        visited.add(v);

        for (VertexDistance<T> vd : graph.getAdjList().get(v)) {
            if (!visited.contains(vd.getVertex())) {
                dfs(vd.getVertex(), graph, visited, result);
            }
        }
    }

    /**
     * Finds the single-source shortest distance between the start vertex and
     * all vertices given a weighted graph (you may assume non-negative edge
     * weights).
     *
     * Return a map of the shortest distances such that the key of each entry
     * is a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing
     * infinity) if no path exists.
     *
     * You may import/use java.util.PriorityQueue,
     * java.util.Map, and java.util.Set and any class that
     * implements the aforementioned interfaces, as long as your use of it
     * is efficient as possible.
     *
     * You should implement the version of Dijkstra's where you use two
     * termination conditions in conjunction.
     *
     * 1) Check if all of the vertices have been visited.
     * 2) Check if the PQ is empty.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the Dijkstra's on (source)
     * @param graph the graph we are applying Dijkstra's to
     * @return a map of the shortest distances from start to every
     * other node in the graph
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph.
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
                                                        Graph<T> graph) {
        if (start == null) {
            throw new IllegalArgumentException("Starting vertex cannot be null");
        } else if (graph == null) {
            throw new IllegalArgumentException("Graph cannot be null");
        } else if (!graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("Starting vertex is not in the graph");
        }
        //Set<Vertex<T>> visited = new HashSet<>();
        Map<Vertex<T>, Integer> distanceMap = new HashMap<>();
        Queue<VertexDistance<T>> pq = new PriorityQueue<>();

        for (Vertex<T> v : graph.getAdjList().keySet()) {
            if (v.equals(start)) {
                distanceMap.put(v, 0);
            } else {
                distanceMap.put(v, Integer.MAX_VALUE);
            }
        }

        pq.add(new VertexDistance<>(start, 0));
        while (!pq.isEmpty()) {
            VertexDistance<T> v = pq.poll();
            for (VertexDistance<T> vd : graph.getAdjList().get(v.getVertex())) {
                int distance = v.getDistance() + vd.getDistance();
                if (distanceMap.get(vd.getVertex()) > distance) {
                    distanceMap.put(vd.getVertex(), distance);
                    pq.add(new VertexDistance<>(vd.getVertex(), distance));
                }
            }
        }

        return distanceMap;
    }

    /**
     * Runs Prim's algorithm on the given graph and returns the Minimum
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     *
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     *
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * reverse edge to the set as well. This is for testing purposes. This
     * reverse edge does not need to be the one from the graph itself; you can
     * just make a new edge object representing the reverse edge.
     *
     * You may assume that there will only be one valid MST that can be formed.
     *
     * You should NOT allow self-loops or parallel edges in the MST.
     *
     * You may import/use PriorityQueue, java.util.Set, and any class that 
     * implements the aforementioned interface.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * The only instance of java.util.Map that you may use is the
     * adjacency list from graph. DO NOT create new instances of Map
     * for this method (storing the adjacency list in a variable is fine).
     *
     * @param <T> the generic typing of the data
     * @param start the vertex to begin Prims on
     * @param graph the graph we are applying Prims to
     * @return the MST of the graph or null if there is no valid MST
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph.
     */
    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {
        if (start == null) {
            throw new IllegalArgumentException("Starting vertex cannot be null");
        } else if (graph == null) {
            throw new IllegalArgumentException("Graph cannot be null");
        } else if (!graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("Starting vertex is not in the graph");
        }

        List<Vertex<T>> visited = new LinkedList<>();
        Set<Edge<T>> mst = new HashSet<>();
        PriorityQueue<Edge<T>> pq = new PriorityQueue<>();

        for (VertexDistance<T> vd : graph.getAdjList().get(start)) {
            pq.add(new Edge<T>(start, vd.getVertex(), vd.getDistance()));
        }

        visited.add(start);

        while (!pq.isEmpty() && visited.size() != graph.getVertices().size()) {
            Edge<T> e = pq.poll();
            if (!visited.contains(e.getV()) || !visited.contains(e.getU())) {
                visited.add(e.getV());
                mst.add(e);
                mst.add(new Edge<>(e.getV(), e.getU(), e.getWeight()));

                for (VertexDistance<T> vd : graph.getAdjList().get(e.getV())) {
                    if (!visited.contains(vd.getVertex())) {
                        pq.add(new Edge<T>(e.getV(), vd.getVertex(), vd.getDistance()));
                    }
                }
            }
        }
        if (visited.size() != graph.getVertices().size()) {
            return null;
        }
        return mst;
    }
}