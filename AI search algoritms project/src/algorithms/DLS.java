package algorithms;

import models.State;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class DLS {

    public static boolean solve(State current, int limit, int depth, Set<State> visitedList){
        visitedList = new TreeSet<>();
        Queue<State> fringe = new LinkedList<>();
        fringe.add(current);
       // int count = 0;
        boolean found= false;
        while(depth<=limit){
            depth++;
            if (current.isFinal()) {
                current.print();
                found = true;
                return found;

            }else{
               if (!fringe.contains(current))
               {fringe.add(current);
}

                State temp = fringe.poll();
                //count++;
                while (!fringe.isEmpty()) {

                    if (visitedList.contains(temp)){
                        continue;
                    }

                    if(temp != null && !visitedList.contains(temp)){
                         visitedList.add(temp);
                         fringe.addAll(temp.makeChild());
                         solve(temp, limit, depth+1, visitedList);
                    }

                }
            }
        }
            System.out.println("NOT FOUND");
            return found ;

    }
}
