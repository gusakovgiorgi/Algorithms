package com.example.codeforces.strong_city;

import java.io.*;
import java.util.*;

public class StrongCity {

    //-----------PrintWriter for faster output---------------------------------
    public static PrintWriter out;

    public static void main(String[] args) {
        MyScanner sc = new MyScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        int vRoadsCount = sc.nextInt();
        int hRoadsCount = sc.nextInt();
        int queries = sc.nextInt();

        Coord[] vCoords = new Coord[vRoadsCount];
        Coord[] hCoords = new Coord[hRoadsCount];
        DirectedGraph graph = new DirectedGraph(vRoadsCount * hRoadsCount, vRoadsCount * hRoadsCount);
        for (int i = 0; i < vCoords.length; i++) {
            vCoords[i] = new Coord(sc.nextInt(), sc.nextInt() == 1);
        }
        Vertex previousVert = null;
        Vertex[] bottomBoundVerts = new Vertex[vRoadsCount];
        for (int i = 0; i < hCoords.length; i++) {
            Coord hCoord = new Coord(sc.nextInt(), sc.nextInt() == 1);
            previousVert = null;
            for (int j = 0; j < vCoords.length; j++) {
                Vertex vertex = new Vertex(vCoords[j].coordOfAcixCrossing, hCoord.coordOfAcixCrossing);
                // Add horizontal edges
                if (previousVert != null) {
                    int distance = vertex.xCoord - previousVert.xCoord;
                    Edge edge = new Edge(previousVert, vertex, distance);
                    if (hCoord.isForward()) {
                        previousVert.outcomeEdges.add(edge);
                        vertex.incomeEdges.add(edge);
                    } else {
                        previousVert.incomeEdges.add(edge);
                        vertex.outcomeEdges.add(edge);
                    }
                    graph.vertices.putIfAbsent(vertex, vertex);
                    graph.edges.put(edge, edge);
                    previousVert = vertex;
                } else {
                    previousVert = vertex;
                    graph.vertices.putIfAbsent(previousVert, previousVert);
                }

                //Add vertical edges
                if (i == 0) {
                    bottomBoundVerts[j] = previousVert;
                } else {
                    Vertex bottomVertex = bottomBoundVerts[j];
                    int distance = vertex.yCoord - bottomVertex.yCoord;
                    Edge edge = new Edge(bottomVertex, vertex, distance);
                    Coord upperVertCoord = vCoords[j];
                    if (upperVertCoord.isForward()) {
                        bottomVertex.outcomeEdges.add(edge);
                        vertex.incomeEdges.add(edge);
                    } else {
                        bottomVertex.incomeEdges.add(edge);
                        vertex.outcomeEdges.add(edge);
                    }
                    graph.edges.put(edge, edge);
                    bottomBoundVerts[j] = vertex;
                }
            }
        }

        for (int i = 0; i < queries; i++) {
            Vertex vertex1 = new Vertex(sc.nextInt(), sc.nextInt());
            Vertex vertex2 = new Vertex(sc.nextInt(), sc.nextInt());
            Map<Vertex, Integer> distances = DijkstraWithHeap.computePaths(graph, graph.vertices.get(vertex1));
            int distance = distances.get(vertex2);

            if (distance != Integer.MAX_VALUE) {
                out.print(distance);
            } else out.print("-1");

            out.print(" ");
        }
        out.close();
    }

    static class DijkstraWithHeap {


        public static Map<Vertex, Integer> computePaths(DirectedGraph graph, Vertex startVertex) {
            int verticiesSize = graph.vertices.keySet().size();
            Set<Vertex> x = new HashSet<>(verticiesSize);
            Map<Vertex, Integer> distances = new HashMap<>();
            MinHeapForDijkstra<VertexKey> heap = new MinHeapForDijkstra<>(Comparator.comparingInt(o -> o.vertexKey));
            for (Vertex vertex : graph.vertices.keySet()) {
                distances.put(vertex, Integer.MAX_VALUE);
            }
            x.add(startVertex);
            distances.put(startVertex, 0);
            for (Edge edge : startVertex.outcomeEdges) {
                Vertex vertex = !edge.head.equals(startVertex) ? edge.head : edge.tail;
                maintainHeap(new VertexKey(vertex, edge.weight), heap);
            }
            while (heap.size() > 0) {
                VertexKey currentVertexKey = heap.poll();
                Vertex w = currentVertexKey.vertex;

                x.add(w);
                distances.put(w, currentVertexKey.vertexKey);

                for (Edge edge : w.outcomeEdges) {
                    Vertex vVertex = !edge.head.equals(w) ? edge.head : edge.tail;
                    if (x.contains(vVertex)) continue;
                    maintainHeap(new VertexKey(vVertex, distances.get(w) + edge.weight), heap);
                }
            }

            return distances;
        }

        private static void maintainHeap(VertexKey vertexKey, MinHeapForDijkstra<VertexKey> heap) {
            VertexKey containedVertexKey = heap.contains(vertexKey);
            if (containedVertexKey != null) {
                if (vertexKey.vertexKey < containedVertexKey.vertexKey) {
                    heap.remove(containedVertexKey);
                    heap.add(vertexKey);
                }
            } else {
                heap.add(vertexKey);
            }
        }

        static class VertexKey {
            Vertex vertex;
            int vertexKey;

            public VertexKey(Vertex vertex, int vertexKey) {
                this.vertex = vertex;
                this.vertexKey = vertexKey;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                VertexKey vertexKey1 = (VertexKey) o;
                return vertexKey == vertexKey1.vertexKey &&
                        Objects.equals(vertex, vertexKey1.vertex);
            }

            @Override
            public int hashCode() {
                return Objects.hash(vertex, vertexKey);
            }
        }
    }


    //-----------MyScanner class for faster input----------
    public static class MyScanner {
        BufferedReader br;
        StringTokenizer st;

        public MyScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

    }
    //--------------------------------------------------------

    static class DirectedGraph {
        int v;
        int e;
        Map<Vertex, Vertex> vertices = new HashMap<>(v, 1.01f);
        Map<Edge, Edge> edges = new HashMap<>(e, 1.01f);


        public DirectedGraph(int v, int e) {
            this.v = v;
            this.e = e;
        }
    }

    static class Vertex {
        int xCoord;
        int yCoord;
        List<Edge> outcomeEdges = new LinkedList<>();
        List<Edge> incomeEdges = new LinkedList<>();


        public Vertex(int xCoord, int yCoord) {
            this.xCoord = xCoord;
            this.yCoord = yCoord;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex vertex = (Vertex) o;
            return xCoord == vertex.xCoord &&
                    yCoord == vertex.yCoord;
        }

        @Override
        public int hashCode() {
            return Objects.hash(xCoord, yCoord);
        }
    }

    static class Edge {
        Vertex tail;
        Vertex head;
        int weight;

        public Edge(Vertex tail, Vertex head, int weight) {
            this.tail = tail;
            this.head = head;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return ((tail == edge.tail && head == edge.head) ||
                    (tail == edge.head && head == edge.tail)) &&
                    weight == edge.weight;
        }

        @Override
        public int hashCode() {
            return Objects.hash(tail, head, weight);
        }
    }

    static class Coord {
        int coordOfAcixCrossing;
        boolean direction;

        public Coord(int coordOfAcixCrossing, boolean direction) {
            this.coordOfAcixCrossing = coordOfAcixCrossing;
            this.direction = direction;
        }

        public boolean isForward() {
            return direction;
        }

        public boolean isBackWard() {
            return !direction;
        }
    }

    static class MinHeapForDijkstra<T> {
        private List<T> items = new ArrayList<>();
        private Map<T, Integer> itemsMapping = new HashMap<>();
        private Comparator<T> comparator;


        public MinHeapForDijkstra(Comparator<T> comparator) {
            this.comparator = comparator;
        }

        private int getLeftChildIndex(int parentIndex) {
            return 2 * parentIndex + 1;
        }

        private int getRightChildIndex(int parentIndex) {
            return 2 * parentIndex + 2;
        }

        private int getParentIndex(int childIndex) {
            return (childIndex - 1) / 2;
        }

        private boolean hasLeftChild(int index) {
            return getLeftChildIndex(index) < items.size();
        }

        private boolean hasRightChild(int index) {
            return getRightChildIndex(index) < items.size();
        }

        private boolean hasParent(int index) {
            return getParentIndex(index) >= 0;
        }

        private T getLeftChild(int index) {
            return items.get(getLeftChildIndex(index));
        }

        private T getRightChild(int index) {
            return items.get(getRightChildIndex(index));
        }

        private T parent(int index) {
            return items.get(getParentIndex(index));
        }

        private void swap(int indexOne, int indexTwo) {
            T temp = items.get(indexOne);
            items.set(indexOne, items.get(indexTwo));
            itemsMapping.put(items.get(indexTwo), indexOne);
            items.set(indexTwo, temp);
            itemsMapping.put(temp, indexTwo);
        }

        public int size() {
            return items.size();
        }

        public T peek() {
            if (items.isEmpty()) throw new IllegalArgumentException();
            return items.get(0);
        }

        public T poll() {
            if (items.isEmpty()) throw new IllegalArgumentException();
            T item = items.get(0);
            if (items.size() == 1) {
                items.remove(0);
                itemsMapping.remove(item);
            } else {
                T lastItem = items.remove(items.size() - 1);
                items.set(0, lastItem);
                itemsMapping.put(lastItem, 0);
                itemsMapping.remove(item);
                heapifyDown();
            }
            return item;
        }

        public void add(T item) {
            items.add(item);
            itemsMapping.put(item, items.size() - 1);
            heapifyUp();
        }

        public T contains(T item) {
            Integer index = itemsMapping.get(item);
            if (index != null) {
                return items.get(index);
            }
            return null;
        }

        public void remove(T item) {
            Integer index = itemsMapping.get(item);
            if (items.size() == 1 || index == items.size() - 1) {
                items.remove(index.intValue());
                itemsMapping.remove(item);
            } else {
                T lastItem = items.remove(items.size() - 1);
                T deletedItem = items.get(index);
                items.set(index, lastItem);
                itemsMapping.put(lastItem, index);
                itemsMapping.remove(deletedItem);
                decideWhichHeapify(index);
            }

        }

        private void decideWhichHeapify(int index) {
            if (!hasParent(index)) {
                heapifyDown();
            } else if (comparator.compare(parent(index), items.get(index)) > 0) {
                heapifyUp(index);
            } else {
                heapifyDown(index);
            }
        }

        private void heapifyUp() {
            heapifyUp(items.size() - 1);
        }

        private void heapifyUp(int index) {
            while (hasParent(index) && comparator.compare(parent(index), items.get(index)) > 0) {
                swap(getParentIndex(index), index);
                index = getParentIndex(index);
            }
        }

        private void heapifyDown() {
            heapifyDown(0);
        }

        private void heapifyDown(int index) {
            while (hasLeftChild(index)) {
                int smallerChildIndex = getLeftChildIndex(index);
                if (hasRightChild(index) && comparator.compare(getRightChild(index), getLeftChild(index)) < 0) {
                    smallerChildIndex = getRightChildIndex(index);
                }
                if (comparator.compare(items.get(index), items.get(smallerChildIndex)) < 0) {
                    break;
                } else {
                    swap(index, smallerChildIndex);
                    index = smallerChildIndex;
                }
            }
        }
    }

}
