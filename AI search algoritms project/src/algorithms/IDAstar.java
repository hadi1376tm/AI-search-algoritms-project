package algorithms;


import models.State;

import java.util.*;

public class IDAstar {


    public static void solve(State start, int cutOff) {

        int count = 0;
        int minFcost = Integer.MAX_VALUE;

        Set<State> visitedList = new TreeSet<>();
        ArrayList<State> children;
        PriorityQueue<State> fringe = new PriorityQueue<>(new Comparator<State>() {
            @Override
            public int compare(State o1, State o2) {
                int f1 = o1.getFcost(), f2 = o2.getFcost();
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
            int tempFcost = temp.getFcost();
            if (temp.isFinal()) {
                temp.print();
                System.out.println("node count = " + count);
                return;
            } else if (tempFcost <= cutOff) {
                 children = temp.makeChild();
                for (State child : children) {
                    child.setGcost(temp.getGcost() + 1);
                }
                fringe.addAll(children);
            }
            else{
                if(tempFcost <= minFcost)
                {
                    minFcost= tempFcost; //new cut_off
                }
            }
        }
        solve(start,minFcost);
    }


}
