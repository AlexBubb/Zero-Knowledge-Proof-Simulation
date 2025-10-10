import java.util.*;
import java.io.*;

class Vertex implements Comparable<Vertex> //2 vertexes are equal if and only if they have the same name
{
   private String color;
   private final String name;
   private HashSet<Vertex> adjacencies;
   public Vertex (Vertex v) {
       name = new String(v.getName());
       color = new String(v.getColor());
       adjacencies = new HashSet<Vertex>(v.getAdjacencies());
   }
   public Vertex (String n, String c) {
       name = n;
       color = c;
       adjacencies = new HashSet<Vertex>();
   }
   public String getName() {
       return name;
   }
   public String getColor() {
       return color;
   }
   public void changeColor(String c) {
       color = c;
   }
   public void addAdjacent(Vertex v) {
       adjacencies.add(v);
   }
   public Vertex getRandomVertex() {
       List<Vertex> list = new ArrayList<>(adjacencies);
       Random random = new Random();
       int randomIndex = random.nextInt(list.size());
       return list.get(randomIndex);
   }
   public HashSet<Vertex> getAdjacencies() {
       return adjacencies;
   }
   public String toString() {
        String x = name + " " + color + " [";
        for(Vertex v: adjacencies){
            x += v.getName() + " ";
        }
        return x.trim() + "]";
   }
   public int compareTo(Vertex v) {
       return name.compareTo(v.getName());
   }
   public boolean equals(Vertex v) {
       return name.equals(v.getName());
   }
   public int hashCode() {
       return name.hashCode();
   }
}