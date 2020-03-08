/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotics_assignment_2;
import java.util.ArrayList;
import org.graphstream.algorithm.generator.GridGenerator;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
/**
 *
 * @author Lucas
 */
public class Robotics_Assignment_2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        SingleGraph graph = new SingleGraph("grid");
        GridGenerator gridGenerator = new GridGenerator();
        gridGenerator.addSink(graph);
        gridGenerator.begin();
        int size = 36;
        for (int i = 0; i < size; i++) gridGenerator.nextEvents();
        gridGenerator.end();
        for (Node node : graph.getNodeSet())
        {
            node.addAttribute("passable", true);
        }
    }
    
    public static ArrayList<Node> bug1(Node start, Node goal)
    {
        ArrayList<Node> path = new ArrayList<>();
        path.add(start);
        String mode = "goal"; //The mode is either goal or trace--goal moves toward goal; trace traces obstacle.
        while (!path.get(path.size()-1).equals(goal) && mode.equals("goal")) //while we are not at the goal and not tracing:
        {
            Node current = path.get(path.size() - 1);
            switch(mode)
            {
                case "goal":
                    String id = current.getId();
                    String[] coordinates = id.split("_");
                    String newNodeID = coordinates[0] + "_" + String.valueOf(Integer.parseInt(coordinates[1] + 1));
                    Node newNode = start.getGraph().getNode(newNodeID);
                    if (newNode.getAttribute("passable")) path.add(newNode);
                    else mode = "trace";                  
                    break;
                case "trace":
                    String moving = "west"; //Denotes the direction we are going.
                    Node traceStart = path.get(path.size() - 1); //This is where we started the trace.
                    Node closestNode = traceStart;
                    int closestDistance = distance(current, goal); //distance from traceStart to goal.
                    do
                    {
                        String[] traceCoordinates = path.get(path.size()-1).getId().split("_");
                        //"Scan" all adjacent nodes.
                        Node northernNode start.getGraph().getNode();//Get node to North
                        Node westernNode;//Get node to West
                        Node southernNode;//Get node to South
                        Node easternNode;//Get node to East
                        switch(moving)
                        {
                            //extract coordinates from current node.
                            case "west":
                                if (northernNode.getAttribute("passable"))
                                {
                                    moving = "north";
                                    path.add(northernNode);
                                    int newDistance = distance(northernNode, goal);//calculate new distance.
                                    if (newDistance < closestDistance)
                                    {
                                        closestDistance = newDistance;
                                        closestNode = northernNode;
                                    }
                                }
                                else if (westernNode.getAttribute("passable"))
                                {
                                    path.add(westernNode);
                                    int newDistance = distance(westernNode, goal);
                                    if (newDistance < closestDistance)
                                    {
                                        closestDistance = newDistance;
                                        closestNode = westernNode;
                                    }
                                }
                                break;
                            case "north":
                                if (easternNode.getAttribute("passable"))
                                {
                                    moving = "east";
                                    path.add(easternNode);
                                    int newDistance = distance(easternNode, goal); //calculate
                                    if (newDistance < closestDistance)
                                    {
                                        closestDistance = newDistance;
                                        closestNode = easternNode;
                                    }
                                }
                                else if (northernNode.getAttribute("passable"))
                                {
                                    path.add(northernNode);
                                    int newDistance = distance(northernNode, goal);
                                    if (newDistance < closestDistance)
                                    {
                                        closestDistance = newDistance;
                                        closestNode = northernNode;
                                    }
                                }
                                break;
                            case "east":
                                if (southernNode.getAttribute("passable"))
                                {
                                    moving = "south";
                                    path.add(southernNode);
                                    int newDistance = distance(southernNode, goal);
                                    if (newDistance < closestDistance)
                                    {
                                        closestDistance = newDistance;
                                        closestNode = southernNode;
                                    }
                                }
                                else if (easternNode.getAttribute("passable"))
                                {
                                    path.add(easternNode);
                                    int newDistance = distanceI(easternNode, goal);
                                    if(newDistance < closestDistance)
                                    {
                                        closestDistance = newDistance;
                                        closestNode = eaternNode;
                                    }
                                }
                                break;
                            case "south":
                                if (westernNode.getAttribute("passable"))
                                {
                                    moving = "west";
                                    path.add(westernNode);
                                    int newDistance = distance(westernNode, goal);
                                    if (newDistance < closestDistance)
                                    {
                                        closestDistance = newDistance;
                                        closestNode = westernNode;
                                    }
                                }
                                else if (southernNode.getAttribute("passable"))
                                {
                                    path.add(southernNode);
                                    int newDistance = distance(southernNode, goal);
                                    if (newDistance < closestDistance)
                                    {
                                        closestDistance = newDistance;
                                        closestNode = southernNode;
                                    }
                                }
                                break;
                        }
                    } while (!path.get(path.size()-1).equals(traceStart));
                    ArrayList<Node> pathBack = new ArrayList<>(); //ArrayList for the path back to the closest node.
                    for (int i = path.size() - 2; !pathBack.get(pathBack.size() - 1).equals(closestNode); i--)
                    {
                        pathBack.add(path.get(i));
                    }
                    path.addAll(pathBack);
                    mode = "goal";
                    break;
            }
        }
        return path;
    }
    
    public static ArrayList<Node> bug2(Node start, Node goal) //CHANGE TRACE EXIT CONDITIONS
    {
        ArrayList<Node> path = new ArrayList<>();
        path.add(start);
        String mode = "goal"; //The mode is either goal or trace--goal moves toward goal; trace traces obstacle.
        while (!path.get(path.size()-1).equals(goal) && mode.equals("goal")) //while we are not at the goal and not tracing:
        {
            Node current = path.get(path.size() - 1);
            switch(mode)
            {
                case "goal":
                    String id = current.getId();
                    String[] coordinates = id.split("_");
                    String newNodeID = coordinates[0] + "_" + String.valueOf(Integer.parseInt(coordinates[1] + 1));
                    Node newNode = start.getGraph().getNode(newNodeID);
                    if (newNode.getAttribute("passable")) path.add(newNode);
                    else mode = "trace";                  
                    break;
                case "trace":
                    String moving = "west"; //Denotes the direction we are going.
                    Node traceStart = path.get(path.size() - 1); //This is where we started the trace.
                    Node closestNode = traceStart;
                    int closestDistance; //distance from traceStart to goal.
                    do
                    {
                        String[] traceCoordinates = path.get(path.size()-1).getId().split("_");
                        //"Scan" all adjacent nodes.
                        Node northernNode;
                        Node westernNode;
                        Node southernNode;
                        Node easternNode;
                        switch(moving)
                        {
                            //extract coordinates from current node.
                            case "west":
                                if (northernNode.getAttribute("passable"))
                                {
                                    moving = "north";
                                    path.add(northernNode);
                                    int newDistance;//calculate new distance.
                                    if (newDistance < closestDistance)
                                    {
                                        closestDistance = newDistance;
                                        closestNode = northernNode;
                                    }
                                }
                                else if (westernNode.getAttribute("passable"))
                                {
                                    path.add(westernNode);
                                    int newDistance;
                                    if (newDistance < closestDistance)
                                    {
                                        closestDistance = newDistance;
                                        closestNode = westernNode;
                                    }
                                }
                                break;
                            case "north":
                                if (easternNode.getAttribute("passable"))
                                {
                                    moving = "east";
                                    path.add(easternNode);
                                    int newDistance; //calculate
                                    if (newDistance < closestDistance)
                                    {
                                        closestDistance = newDistance;
                                        closestNode = easternNode;
                                    }
                                }
                                else if (northernNode.getAttribute("passable"))
                                {
                                    path.add(northernNode);
                                    int newDistance = getDistance(northernNode, goal);
                                    if (newDistance < closestDistance)
                                    {
                                        closestDistance = newDistance;
                                        closestNode = northernNode;
                                    }
                                }
                                break;
                            case "east":
                                if (southernNode.getAttribute("passable"))
                                {
                                    
                                }
                                else if (easternNode.getAttribute("passable"))
                                {
                                    
                                }
                                break;
                            case "south":
                                if (WesternNode.getAttribute("passable"))
                                {
                                    
                                }
                                else if (southernNode.getAttribute("passable"))
                                {
                                    
                                }
                                break;
                        }
                    } while (!path.get(path.size()-1).getId().split("_")[0].equals(goal.getId().split("_")[0]) && distance(current, goal) > distance(path.get(path.size()-1)), goal)));
                    mode = "goal";
                    break;
            }
        }
        return path;
    }
    
    public static int distance(Node node1, Node node2) //add goal argument
    {
        String[] coordinate1 = node1.getId().split("_");
        int[] values1 = {Integer.parseInt(coordinate1[0]), Integer.parseInt(coordinate1[1])};
        String[] coordinate2 = node2.getId().split("_");
        int[] values2 = {Integer.parseInt(coordinate2[0]), Integer.parseInt(coordinate2[1])};
        return Math.abs(values1[0] - values2[0]) + Math.abs(values1[1] - values2[1]);
    }
}
