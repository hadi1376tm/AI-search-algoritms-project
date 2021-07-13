package algorithms;

import models.State;

import java.util.*;

public class UCS {

    public static void solve(State start) {

        int count = 0;

        if (start.isFinal()) {
            start.print();
            return;
        }

        Set<State> visitedList = new TreeSet<>();
        PriorityQueue<State> fringe = new PriorityQueue<>(new Comparator<State>() {
            @Override
            public int compare(State o1, State o2) {
                int f1 = o1.getGcost(), f2 = o2.getGcost();
                if (f1 > f2) return 1;
                if (f1 < f2) return -1;
                else return 0;
            }
        });
        start.setGcost(0);
        fringe.add(start);

        while (!fringe.isEmpty()) {
            State temp = fringe.poll();
            if (visitedList.contains(temp)) {
                continue;
            }
            visitedList.add(temp);
            count++;
            if (temp.isFinal()) {
                temp.print();
                 /*  temp2.add(temp);
                while (temp.getParent()!=null){
                    temp2.add(temp.getParent());
                    temp= temp.getParent();
                }
                while (!temp2.empty())
                    temp2.pop().visualPrint();
*/
                System.out.println("node count = " + count);
                System.out.println("goal cost : " +temp.getGcost());
                return;
            }
            ArrayList<State> successor_children = temp.makeChild();
            for (State child : successor_children) {
                if(child.getBoard().getCube().isStand())
                    child.setGcost(temp.getGcost()+3);
                else child.setGcost(temp.getGcost()+1);
                fringe.add(child);
            }

        }

        System.out.println("not found");

    }

}
