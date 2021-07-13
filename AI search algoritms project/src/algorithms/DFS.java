package algorithms;

import models.State;

import java.util.*;

public class DFS {

    public static void solve(State start) {

        int count = 0;
        if (start.isFinal()) {
            start.print();
            return;
        }


        Set<State> visitedList = new TreeSet<>();
        Stack<State> fringe = new Stack<>();
        Stack<State> temp2 = new Stack<>();
        fringe.add(start);


        while (!fringe.isEmpty()) {
            State temp = fringe.pop();
            if (visitedList.contains(temp)){
                continue;
            }

            count++;
            if (temp.isFinal()) {
                temp.print();
                System.out.println("node count = " + count);

                temp2.add(temp);
                while (temp.getParent()!=null){
                    temp2.add(temp.getParent());
                    temp= temp.getParent();
                }
                while (!temp2.empty()) {
                    temp2.pop().visualPrint();
                }

                return;
            }
            else {
            if(temp != null){
                visitedList.add(temp);
                fringe.addAll(temp.makeChild());

              }

            }
        }

        System.out.println("not found");

    }
    }



