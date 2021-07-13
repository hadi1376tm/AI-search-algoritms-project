
package algorithms;

import models.State;

import java.util.*;

public class RBFS {
/*
    public static void solve(State start) {
        int count = 0;
        int Gvalue , Hvalue;
        start.setGcost(0);
        if (start.isFinal()) {
            start.print();
            return;
        }

        Set<State> visitedList = new TreeSet<>();
        Queue<State> fringe = new LinkedList<>();
        fringe.add(start);
      //  Stack<State> successor = new Stack<>();
        ArrayList<State> successor = new ArrayList<>();
        successor.addAll(start.makeChild());

        if (start.isFinal()) {
            start.print();
            return;
        }

        if (start != null) {
            ArrayList<State> children = start.makeChild();
            for (State child : children) {
               child.setGcost(start.getGcost() + 1);
                child.calculateHCost();
                Gvalue=child.getGcost();
                Hvalue=child.getHcost();
                child.setFcost(find_max((Gvalue+Hvalue) , child.getFcost()));
            }
           // fringe.addAll(children);
        }
        if (successor.isEmpty())
        {
            System.out.println("NOT FOUND");
            return;
        }
        int min_Fvalue= successor.get(0).getFcost();

        for(State temp : successor ){
            if(temp.getFcost()< min_Fvalue)
                min_Fvalue= temp.getFcost();
                State min= temp;
        }

    }

    private static int find_max(int childf,int nodef) {
        if (childf>=nodef)
            return childf;
        else
            return nodef;

    }

  /*  private static int mind_Fcost(ArrayList<int> nodelist) {
        int  min_fval =  nodelist[0].f;
        int min_fval_node_index=0;

    }*/


}
