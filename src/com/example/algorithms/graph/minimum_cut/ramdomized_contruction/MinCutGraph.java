package com.example.algorithms.graph.minimum_cut.ramdomized_contruction;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * We have list of vertices and list of edges
 * each edge point to iths endpoints
 * each vertex points to edges incident on it
 */
public class MinCutGraph implements Serializable {
    private Map<Vertex, Vertex> vertices = new HashMap<>();

    public static MinCutGraph deepCopy(MinCutGraph graph) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream ous = new ObjectOutputStream(baos);
        ous.writeObject(graph);
        ous.close();
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        return (MinCutGraph) ois.readObject();
    }

    public void addVertexWithNeighbours(Vertex vertex, List<Vertex> neighbours) {
        vertex = addIfNotExistAndGet(vertex);

        for (Vertex neighbour : neighbours) {
            neighbour = addIfNotExistAndGet(neighbour);
            vertex.getConnectedVertices().add(neighbour);
//            neighbour.getConnectedVertices().add(vertex);
        }
    }

    public int getSize() {
        return vertices.size();
    }

    public List<Vertex> getAllVertices() {
        return new ArrayList<>(vertices.keySet());
    }

    public Vertex getVertex(int num) {
        return vertices.get(new Vertex(num));
    }

    public void connect(Vertex first, Vertex second) {
        first.getConnectedVertices().add(second);
        second.getConnectedVertices().add(first);
    }

    public void contraction(Vertex first, Vertex second) {
        List<Vertex> transferVertices = second.getConnectedVertices();
        for (Vertex vertex : transferVertices) {
            if (!vertex.equals(first)) {
                first.getConnectedVertices().add(vertex);
                vertex.getConnectedVertices().add(first);
            }
        }
        deleteVertex(second);
    }

    public void deleteVertex(Vertex vertex) {
        Vertex deletedVertex = vertices.get(vertex);
        removeAllEdgesPointToDeleted(deletedVertex);
        deletedVertex.getConnectedVertices().clear();
        vertices.remove(vertex);
    }

    private void removeAllEdgesPointToDeleted(Vertex deletedVertex) {
        List<Vertex> verticesPointToDeleted = deletedVertex.getConnectedVertices();
        for (Vertex vertex : verticesPointToDeleted) {
            vertex.getConnectedVertices().remove(deletedVertex);
        }
    }

    private Vertex addIfNotExistAndGet(Vertex vert) {
        if (!vertices.containsKey(vert)) {
            vertices.put(vert, vert);
        }
        return vertices.get(vert);
    }
}
