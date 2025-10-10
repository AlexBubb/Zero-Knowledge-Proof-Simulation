import java.util.*;
import java.io.*;

public class Solver {
    Graph solution;
    Graph lockedSolution;
    Graph unlockedSolution;
    public Solver(String colorFile, String edgeFile) {
        solution = new Graph();
        solution.readData(colorFile, edgeFile);
        genLockedSolution();
    }
    
    
    public void genLockedSolution() {
        //to make this work we need three graphs
        //One is the solution we found
        //One is the locked graph with all the colors with a random number added then hashed
        //One is an unlocked graph to remember what numbers we add to the colors
        Map<String, Vertex> locked = new TreeMap<String, Vertex>(solution.getVertexMap());
        Map<String, Vertex> unlocked = new TreeMap<String, Vertex>(solution.getVertexMap());
        for(String n : locked.keySet()) {
            Vertex v = new Vertex(solution.getVertexMap().get(n));
            Vertex l = new Vertex(solution.getVertexMap().get(n));
            int num = (int)(Math.random()*100+1);
            //get the color and add some randomness
            v.changeColor(v.getColor()+num);
            unlocked.put(n, v);
            //Then hash it
            l.changeColor(String.valueOf(v.getColor().hashCode()));
            locked.put(n, l);
        }
        lockedSolution = new Graph(locked);
        unlockedSolution = new Graph(unlocked);
        //Why do we add randomness? 
        //Because otherwise the locked graph would be the solution
        //The corresponding colors on the solution would generate the same hash
        //This results in if two hashes being the same in the locked graph 
        //Then they are the same color in the solution
        //Additionaly note that get locked solution is the only getter because the Solver should NEVER
        //Tell the Verifier the solution or the unlocked graph because thats also a solution
        //Ok lets head back to the main method
    }
    public Graph getLockedSolution() {
        return lockedSolution;
    }
    public String[] reveal(String v1, String v2) {
        //Now we just give the verifier what was orginialy hashed to create the locked version
        //Remeber this is just a color + a random number
        Vertex vertex1 = unlockedSolution.getVertex(v1);
        Vertex vertex2 = unlockedSolution.getVertex(v2);
        //It's important to confirm that the Verifier acually asked for adjacent vertieces
        //Otherwise we might acidentaly give them useful information about our solution!
        boolean adj = false;
        for (Vertex a : vertex1.getAdjacencies()) {
            if (a.getName().equals(v2)) {
                adj = true;
            }
        }
        if(adj) {
            String[] out = {vertex1.getColor(),vertex2.getColor()};
            //Notice that as soon as we unlock two vertices the unlocked and locked graph get destroyed
            //In addition the colors in our solution get shuffled around
            //You can take a look at shuffleColors if you want but the TLDR is that it doesn't change
            //the solution only the colors that are used so for example all reds become blue and vice
            //versea so the solution doesn't change its just the colors are swaped
            //This is crittical because if you unlocked the same locked graph mutiple times or the colors
            //didn't move between rounds the verifier would be able to slowly piece together our solution
            //Now let's skip the unlock in the verifier to see what they do with the information we give them
            solution.shuffleColors();
            genLockedSolution();
            return out;
        }
        return null;
    }
}