package algorithms;

import models.State;

import java.util.Set;
import java.util.TreeSet;


public class IDS {

    public static void solve(State start){
        int limit = 1; //set starting depth to 0
        State current=start; //current node is equal to start
       boolean j ;

        do
        {
            Set<State> visited=new TreeSet<>(); //create an array list of nodes

           j= DLS.solve(current, limit , 0, null);
            limit++;

        }while(!j);

        System.out.println("RESULT");

    }
}
