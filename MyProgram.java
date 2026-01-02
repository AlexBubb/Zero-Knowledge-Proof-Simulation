import java.util.*;
import java.io.*;
public class MyProgram
{
    public static void main(String[] args)throws FileNotFoundException
    {
     Graph graph = new Graph();
     //CHANGE THE FILENAME HERE FOR NEW GRAPH
    Scanner scan = new Scanner(System.in);
    System.out.println("Enter the name of the node file: ");
    String nodes = scan.nextLine();
    System.out.println("Enter the name of the edges file: ");
    String edges = scan.nextLine();
    Solver person1 = new Solver(nodes,edges);
    Verifier person2 = new Verifier();
    //We need to run this test multiple times to increase the odds of catching a cheater
    //Right now it's set to 100 but this number can be changed 
    //Higher numbers mean a higher chance to catch cheaters
    //Currently with the given graph the odds that a cheater gets past are 6/(7^100) or 2*10^-7
    for (int x = 0; x<100; x++) {
        //Step one is to generate a graph with all the vertices locked up
        //Let's take a look how that works (go to genLockedGraph in Solver)
        person2.giveLockedGraph(person1.getLockedSolution());
        //Now the verifier has a locked graph which isn't helpful to him
        //The verifier is allowed to ask to unlock two adjacent vertices
        //Let's go to the request method in verifier
        String[] person2Request = person2.request();
        //Now the verifier informs the solver of thier choice and the solver gives them what they need
        //To unlock the vertices
        //Let's take a look at how that happens
        String[] person1Response = person1.reveal(person2Request[0],person2Request[1]);
        if(person2.unlock(person1Response[0],person1Response[1]) == false) {
            System.out.println("This solution is not correct. >:(");
            System.exit(0);
        }
    }
    System.out.println("Congrats on your solution!");
    }
    //If they pass the tests they can be very sure we have a real solution without us ever giving any useful information
}
