package algorithms;

import models.State;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class BFS {

    private static State answer = null;

    public static void solve(State start) {

        int count = 0;
        if (start.isFinal()) {
            start.print();
            return;
        }

        Set<State> visitedList = new TreeSet<>();
        Queue<State> fringe = new LinkedList<>();
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
                return;
            }

            fringe.addAll(temp.makeChild());

        }

        System.out.println("not found");

    }
    public static State getAnswer() {

        return answer;
    }
    public static void setAnswer(State answer) {

        BFS.answer = answer;
    }
}
