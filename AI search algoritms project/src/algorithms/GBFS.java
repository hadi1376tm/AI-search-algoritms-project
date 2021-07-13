package algorithms;

import models.State;

import java.util.*;

public class GBFS {

    public static void solve(State start) {

        int count = 0;


        Set<State> visitedList = new TreeSet<>();
        PriorityQueue<State> fringe = new PriorityQueue<>(new Comparator<State>() {
            @Override
            public int compare(State o1, State o2) {
                int f1 = o1.getHcost(), f2 = o2.getHcost();
                if (f1 > f2) return 1;
                if (f1 < f2) return -1;
                else return 0;
            }
        });
        if (start.isFinal()) {
            start.print();
            return;
        }
        fringe.add(start);
        while (!fringe.isEmpty())
        {
            State temp = fringe.poll();

            if (visitedList.contains(temp)) {
                continue;
            }
            visitedList.add(temp);
            count++;

            if (temp.isFinal()) {
                temp.print();
                System.out.println("node count = " + count);
                return;
            }

            fringe.addAll(temp.makeChild());
        }

        System.out.println("not found");

    }
}

