package algorithms;

import models.State;

import java.util.*;

public class AStar {

    private static State answer = null;


    public static void solve(State start) {

        int count = 0;
        if (start.isFinal()) {
            start.print();
            return;
        }

        Set<State> visitedList = new TreeSet<>();
        Stack<State> temp2 = new Stack<>();
        PriorityQueue<State> fringe = new PriorityQueue<>(new Comparator<State>() {
            @Override
            public int compare(State o1, State o2) {
                int f1 = o1.getFcost(),f2=o2.getFcost();
                if(f1>f2) return 1;
                if(f1<f2) return -1;
                else return 0;
            }
        });
        start.setGcost(0);
        start.calculateHCost();
        fringe.add(start);
        while (!fringe.isEmpty()) {
            State temp = fringe.poll();
            if (visitedList.contains(temp)){
                continue;
            }
            visitedList.add(temp);
            count++;
            if (temp.isFinal()) {
                temp.print();
                System.out.println("node count = " + count);
                setAnswer(temp);
              /*  temp2.add(temp);
                while (temp.getParent()!=null){
                    temp2.add(temp.getParent());
                    temp= temp.getParent();
                }
                while (!temp2.empty())
                    temp2.pop().visualPrint();
*/
                return;
            }
            else {
                if (temp != null) {
                    ArrayList<State> children = temp.makeChild();
                    for (State child : children) {
                        child.setGcost(temp.getGcost() + 1);
                        child.calculateHCost();
                    }
                    //temp.visualPrint();
                    fringe.addAll(children);
                }
            }
        }
        System.out.println("not found");

    }

    public static State getAnswer() {
        return answer;
    }

    public static void setAnswer(State answer) {
        AStar.answer = answer;
    }
}
