OVERVIEW:
This is a zero knowledge proof for graph coloring
What is graph coloring?
It is a computer science problem where you need to color each of the vertices of a graph such that no two adjacent nodes have the same color.
What is a zero knowledge proof?
Say I have a solution to a graph coloring problem that took me a long time to find and I want to prove to you I solved it. The issue is I don't want to give you any information that would help you solve it yourself!
I need a zero knowledge proof protocol!
This program simulates one such protocol as a conversation between person1 (the person who solved the coloring problem) and person 2 who is checking the solution
This protocol itself is outlined in this video by polylog here: https://www.youtube.com/watch?v=Otvcbw6k4eo
How does my simulation work?
There are two classes simulating conversation facilitated by MyProgram. The program is designed such that the two classes can only request specific types of data from each other outlined in the algorithm. Although both classes are run locally it would not be challenging to implement over the internet since they only communicate through the middleman MyProgram

DEMO GUIDE:
To run the demo you must have a version of java installed. Download and extract files. Then in the directory from the terminal run java -jar MENACEtictactoe.jar
colors.txt + edges.txt is a valid graph coloring solution
wrongcolors.txt + edges.txt is an invalid solution

CREATE YOUR OWN GRAPHS:
colors.txt: 
each line corresponds to a vertex the format is "name color"
For example to set vertex 1 to blue:
1 blue
edges.txt: each line corresponds to an edge format is "source target"
make sure all edges are bidirectional so for example if I want to connect 5 and 6 then I would put
5 6
and
6 5
on a separate line.
I recommend drawing the graph on paper first when testing a new solution.
