import java.util.*;
import java.io.*;
public class Graph {
   private Map<String, Vertex> vertexMap = new TreeMap<String, Vertex>();
   private int edgeCount;
   private int vertexCount;
   public Graph(Map<String, Vertex> g) {
            vertexMap = g;
    }
   public Graph() {
       boolean a = true;
   }
   public void readData(String colors, String edges) {
       Scanner c = new Scanner(System.in);
       Scanner e = new Scanner(System.in);
       try{
         c = new Scanner(new File(colors));
         e = new Scanner(new File(edges));
       }
       catch (FileNotFoundException ex) {
           System.exit(0);
       }
        String co;
        String ed;
        
        while (c.hasNextLine()) {
            co = c.nextLine();
            String[] words = co.split("\\s+");
            addVertex(words[0],words[1]);
            vertexCount++;
        }
        while (e.hasNextLine()) {
            ed = e.nextLine();
            String[] words = ed.split("\\s+");
            vertexMap.get(words[0]).addAdjacent(vertexMap.get(words[1]));
            edgeCount++;
        }
   }
   public void shuffleColors() {
       ArrayList<String> colors = new ArrayList<String>();
       colors.add("red");
       colors.add("green");
       colors.add("blue");
       Map<String, String> colorShuffle = new TreeMap<String, String>();
       Random random = new Random();
       int randomIndex = random.nextInt(colors.size());
       String randomColor = colors.get(randomIndex);
       colorShuffle.put("red", randomColor);
       colors.remove(randomIndex);
       randomIndex = random.nextInt(colors.size());
       randomColor = colors.get(randomIndex);
       colorShuffle.put("green", randomColor);
       colors.remove(randomIndex);
       randomIndex = random.nextInt(colors.size());
       randomColor = colors.get(randomIndex);
       colorShuffle.put("blue", randomColor);
       for (String n : vertexMap.keySet()) {
           String curr = vertexMap.get(n).getColor();
           vertexMap.get(n).changeColor(colorShuffle.get(curr));
       }
   }
   public int edgeCount() {
       return edgeCount;
   }
   public int vertexCount() {
       return vertexCount/2;
   }
   //return Vertex associated with vName from vertexMap
   public Vertex getVertex(String vName){
       return vertexMap.get(vName);
   }

   //create a set of vertices from vertexMap
   public Set<Vertex> getVertices(){
       TreeSet<Vertex> out = new TreeSet<Vertex>();
       for (String s: vertexMap.keySet()) {
           out.add(vertexMap.get(s));
       }
       return out;
   }

   //getter for vertexMap
   public Map<String, Vertex> getVertexMap() {
       return vertexMap;
   }

   /*      
      postcondition: if a Vertex with the name v exists, then the map is unchanged
                     otherwise create a new vertex and add it to the vertexMap
   */
   public void addVertex(String vName, String vColor){
       if(!vertexMap.keySet().contains(vName)) {
           vertexMap.put(vName, new Vertex(vName, vColor));
           vertexCount++;
       }
   }

   /*
      precondition:  both Vertexes, source and target, are already stored in the graph.
      postcondition:  addEdge should work in O(1)
   */
   public void addEdge(String source, String target){
       Vertex s = vertexMap.get(source);
       Vertex t = vertexMap.get(target);
       s.addAdjacent(t);
       edgeCount++;
   } 
   
   /*
       returns the whole graph as one string, e.g.:
       A [C]
       B [A]
       C [C D]
       D [C A]
     */
   public String toString(){
       String out = "";
       for (String n : vertexMap.keySet()) {
           out += vertexMap.get(n) + "\n";
       }
       return out;
   } 
}