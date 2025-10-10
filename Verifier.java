import java.util.*;
import java.io.*;
public class Verifier {
    Graph lockedGraph;
    String[] requests;
    public Verifier () {
        boolean a = true;
    }
    public void giveLockedGraph(Graph g) {
        lockedGraph = g;
    }
    public String[] request() {
        //This method is pretty simple just need to randomly pick two adjacent vertices
        int vertexCount = lockedGraph.getVertexMap().keySet().size();
        int v1 = (int)(Math.random()*vertexCount)+1;
        String vertex1 = String.valueOf(v1);
        String v2 = lockedGraph.getVertex(vertex1).getRandomVertex().getName();
        String[] out = {vertex1, v2};
        requests = out;
        return out;
    }
    public boolean unlock (String v1, String v2) {
        //Now we neeed to confirm that they gave us the real orginial plaintext
        //Compare the hash of the string they gave to the hash of the locked graph
        if(!String.valueOf(v1.hashCode()).equals(lockedGraph.getVertex(requests[0]).getColor())) {
            return false;
        }
        if(!String.valueOf(v2.hashCode()).equals(lockedGraph.getVertex(requests[1]).getColor())) {
            return false;
        }
        //Now we need to check if they are in fact different
        if(v1.replaceAll("\\d","").equals(v2.replaceAll("\\d",""))) {
            return false;
        }
        //if all looks good they declare that we passed the test and that they are ready for another round
        //Go back to main for a conclusion and customizeing
        return true;
    }
}