package algorithms;

import models.Board;
import models.State;

import java.lang.reflect.Array;
import java.util.*;

public class BDS {
    public static void solve(State start){


   /*
        AStar.solve(start);
         if (AStar.getAnswer()==null)
        {
            System.out.println("NOT FOUND");
            return;
        }
        Board goalboard = AStar.getAnswer().getBoard();
        State goal = new State(goalboard);
        //goal.print();
       */

        BFS.solve(start);
        if (BFS.getAnswer()==null)
        {
            System.out.println("NOT FOUND");
            return;
        }
        Board goalboard = BFS.getAnswer().getBoard();
        State goal = new State(goalboard);

        boolean flag= false;
        boolean flag2= true;
        boolean flag3= false;
        State temp1=start;
        State temp2=goal;

        int count = 0;
        int moves_count;
        if (start.isFinal()) {
            start.print();
            return;
        }

        Set<State> visitedList1 = new TreeSet<>();
        Queue<State> fringe1 = new LinkedList<>();
        Set<State> visitedList2 = new TreeSet<>();
        Queue<State> fringe2 = new LinkedList<>();
        fringe1.add(start);
        fringe2.add(goal);

        while (!fringe1.isEmpty() || !fringe1.isEmpty())
        {


            Object [] Arrfringe1 = fringe1.toArray();
            Object [] Arrfringe2 = fringe2.toArray();

            for (Object t : Arrfringe1) {
                for (Object t2 : Arrfringe2){
                    if (t.equals(t2)) {
                        System.out.println("FOUND ");
                        System.out.println("**************************");
                        ((State)t).print();
                        ((State)t2).print_backward();
                        System.out.println("**************************");
                        moves_count=  ((State)t).getMoves_count() + ((State)t2).getMoves_count();
                        System.out.println("MOVES : " +moves_count);
                        System.out.println("node count = " + count);
                        return;
                    }
               }

              /*
                Stack<State> stack = new Stack<>();
                stack.add(t);
                while (t.getParent()!=null){
                    stack.add(t.getParent());
                    t= t.getParent();
                }
                while (!stack.empty()) {
                    stack.pop().visualPrint();
                }
            }*/

                /*     IN CASE OF NOT MATCH UN COMMENR*****
                 if(!flag2)
              {
                  if (flag3) {
                  temp1 = fringe1.peek();
                  while (visitedList1.contains(temp1))
                  {
                      temp1 = fringe1.poll();
                  }
                  count ++;
                  visitedList1.add(temp1);
                  fringe1.addAll(temp1.makeChild());
                  flag3 = false;

              }

                  if (!flag3) {
                      temp2 = fringe2.peek();


                      while (visitedList2.contains(temp2))
                      {
                          temp2 = fringe2.poll();
                      }
                      count ++;
                      // temp2.visualPrint();
                      visitedList2.add(temp2);
                      fringe2.addAll(temp2.makeChild_backward());
                      fringe2.addAll(temp2.makeChild());
                      flag3= true;
                  }
              }
              */
              if(true) {
                  if (!flag) {
                      temp1 = fringe1.poll();
                      while (visitedList1.contains(temp1)) {
                          temp1 = fringe1.poll();
                      }
                      count++;
                      visitedList1.add(temp1);
                      fringe1.addAll(temp1.makeChild());
                      flag = true;

                  }

                  if (flag) {
                      temp2 = fringe2.poll();


                      while (visitedList2.contains(temp2)) {
                          temp2 = fringe2.poll();
                      }
                      count++;

                      visitedList2.add(temp2);
                      fringe2.addAll(temp2.makeChild_backward());
                      fringe2.addAll(temp2.makeChild());
                      flag = false;
                  }
              }



            }


        }}}


// temp2.visualPrint();

   /* if (temp1.isFinal()) {
                temp1.print();
                System.out.println("node count = " + count);
                System.out.println("************************= " + count);
                stack.add(temp1);
                while (temp1.getParent()!=null){
                    stack.add(temp1.getParent());
                    temp1= temp1.getParent();
                }
                while (!stack.empty()) {
                    stack.pop().visualPrint();
                }
                return;
            }*/

 /*   if (temp2.isFinal_backward()) {
                temp2.print();
                System.out.println("node count = " + count);
                System.out.println("************************= " + count);
                stack.add(temp2);
                while (temp2.getParent()!=null){
                    stack.add(temp2.getParent());
                    temp2= temp2.getParent();
                }
                while (!stack.empty()) {
                    stack.pop().visualPrint();
                }
                return;
            }
*/