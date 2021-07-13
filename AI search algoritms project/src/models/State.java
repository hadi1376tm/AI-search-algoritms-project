package models;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

public class State implements Comparable<State> {

    private int depth;
    private Board board;
    private State parent;
    private int action;
    private int gcost;
    private int hcost;
    private int fcost;
    private int cost=1;
    private int moves_count;

    //0 -> up
    //1 -> down
    //2 -> left
    //3 -> right

    public State(Board board) {
        this.board = board;
        this.depth = 0;
        this.parent = null;
    }

    private State(int depth, Board board, State parent, int action) {
        this.depth = depth + 1;
        this.board = board;
        this.parent = parent;
        this.action = action;
    }

    public ArrayList<State> makeChild() {
        ArrayList<State> states = new ArrayList<>();
        if (board.getCube().isStand()) {
            if (board.getCube().getX() >= 2) {
                Board tempBoard = board.moveUp();
                if (tempBoard != null)
                    states.add(new State(depth, tempBoard, this, 0));
            }
            if (board.getCube().getX() <= board.getHeight() - 3) {
                Board tempBoard = board.moveDown();
                if (tempBoard != null)
                    states.add(new State(depth, tempBoard, this, 1));
            }
            if (board.getCube().getY() >= 2) {
                Board tempBoard = board.moveLeft();
                if (tempBoard != null)
                    states.add(new State(depth, tempBoard, this, 2));
            }
            if (board.getCube().getY() <= board.getLength() - 3) {
                Board tempBoard = board.moveRight();
                if (tempBoard != null)
                    states.add(new State(depth, tempBoard, this, 3));
            }
        } else {
            if (board.getCube().isHorizontal()) {
                if (board.getCube().getX() >= 1) {
                    Board tempBoard = board.moveUp();
                    if (tempBoard != null)
                        states.add(new State(depth, tempBoard, this, 0));
                }
                if (board.getCube().getY() <= board.getLength() - 3) {
                    Board tempBoard = board.moveRight();
                    if (tempBoard != null)
                        states.add(new State(depth, tempBoard, this, 3));
                }
            } else {
                if (board.getCube().getX() >= 2) {
                    Board tempBoard = board.moveUp();
                    if (tempBoard != null)
                        states.add(new State(depth, tempBoard, this, 0));
                }
                if (board.getCube().getY() <= board.getLength() - 2) {
                    Board tempBoard = board.moveRight();
                    if (tempBoard != null)
                        states.add(new State(depth, tempBoard, this, 3));
                }
            }
            if (board.getCube().getX() <= board.getHeight() - 2) {
                Board tempBoard = board.moveDown();
                if (tempBoard != null)
                    states.add(new State(depth, tempBoard, this, 1));
            }
            if (board.getCube().getY() >= 1) {
                Board tempBoard = board.moveLeft();
                if (tempBoard != null)
                    states.add(new State(depth, tempBoard, this, 2));
            }

        }


        return states;
    }

    public boolean isFinal() {
        return board.isFinal();
    }
    public boolean isFinal_backward() {
        return board.isFinal_backward();
    }

    public void print() {
        Stack<Integer> actions = new Stack<>();
        State temp = this;
        while (temp.parent != null) {
            actions.push(temp.action);
            temp = temp.parent;
        }
        System.out.println(actions.size());
        moves_count= actions.size();
        while (!actions.isEmpty()) {
            switch (actions.pop()) {
                case 0:
                    System.out.println("up");
                    break;
                case 1:
                    System.out.println("down");
                    break;
                case 3:
                    System.out.println("right");
                    break;
                case 2:
                    System.out.println("left");
                    break;
            }
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return board.equals(state.board);
    }
    public int fcompareTo(State other) {
        if (this.fcost > other.fcost) {
            return 1;
        } else if (this.fcost < other.fcost) {
            return -1;
        } else {
            return 0;
        }
    }



    public void calculateHCost(){
        this.setHcost(heuristic());
    }


    private int heuristic(){
        int white = 0;
        int h ;
        int[][] map = this.board.getMap();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 0) {
                    white ++;
                }
            }
        }
        h=white/2;
        return h;
    }
    @Override
    public int compareTo(State state) {
        if (this.equals(state))
            return 0;
        return 1;
    }

    public int getGcost() {
        return gcost;
    }

    public int getHcost() {
        return hcost;
    }

    public int getFcost() {
        fcost= gcost+hcost;
        return fcost;
    }

    public void setGcost(int gcost) {
        this.gcost = gcost;
    }

    public void setHcost(int hcost) {
        this.hcost = hcost;
    }

    public void setFcost(int fcost) {
        this.fcost = fcost;
    }

    public void setParent(State parent) {
        this.parent = parent;
    }

    public int getCost() {
        return cost;
    }

    public int getDepth() {
        return depth;
    }

    public State getParent() {
        return parent;
    }



    public void visualPrint() {
        System.out.println("---------------------------------");
        System.out.println("Depth: " + this.depth);
        for (int i = 0; i < this.board.getHeight(); i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < this.board.getLength(); j++) {
                if (this.board.map[i][j] == -1) row.append("\u274C");
                else if (this.board.map[i][j] == 0) row.append("\u25AF");
                else if (this.board.map[i][j] == 1) row.append("\u25AE");
                else row.append("\u271A");
            }
            System.out.println(row);
        }
    }


    public ArrayList<State> makeChild_backward() {
        ArrayList<State> states = new ArrayList<>();
        if (board.getCube().isStand()) {
            if (board.getCube().getX() >= 2) {
                Board tempBoard = board.moveUp_backward();
                if (tempBoard != null)
                    states.add(new State(depth, tempBoard, this, 0));
            }
            if (board.getCube().getX() <= board.getHeight() - 3) {
                Board tempBoard = board.moveDown_backward();
                if (tempBoard != null)
                    states.add(new State(depth, tempBoard, this, 1));
            }
            if (board.getCube().getY() >= 2) {
                Board tempBoard = board.moveLeft_backward();
                if (tempBoard != null)
                    states.add(new State(depth, tempBoard, this, 2));
            }
            if (board.getCube().getY() <= board.getLength() - 3) {
                Board tempBoard = board.moveRight_backward();
                if (tempBoard != null)
                    states.add(new State(depth, tempBoard, this, 3));
            }
        } else {
            if (board.getCube().isHorizontal()) {
                if (board.getCube().getX() >= 1) {
                    Board tempBoard = board.moveUp_backward();
                    if (tempBoard != null)
                        states.add(new State(depth, tempBoard, this, 0));
                }
                if (board.getCube().getY() <= board.getLength() - 3) {
                    Board tempBoard = board.moveRight_backward();
                    if (tempBoard != null)
                        states.add(new State(depth, tempBoard, this, 3));
                }
            } else {
                if (board.getCube().getX() >= 2) {
                    Board tempBoard = board.moveUp_backward();
                    if (tempBoard != null)
                        states.add(new State(depth, tempBoard, this, 0));
                }
                if (board.getCube().getY() <= board.getLength() - 2) {
                    Board tempBoard = board.moveRight_backward();
                    if (tempBoard != null)
                        states.add(new State(depth, tempBoard, this, 3));
                }
            }
            if (board.getCube().getX() <= board.getHeight() - 2) {
                Board tempBoard = board.moveDown_backward();
                if (tempBoard != null)
                    states.add(new State(depth, tempBoard, this, 1));
            }
            if (board.getCube().getY() >= 1) {
                Board tempBoard = board.moveLeft_backward();
                if (tempBoard != null)
                    states.add(new State(depth, tempBoard, this, 2));
            }

        }



        return states;
    }


    public void print_backward() {
        Stack<String> actions_backward = new Stack<>();
        Stack<Integer> actions = new Stack<>();
        State temp = this;
        while (temp.parent != null) {
            actions.push(temp.action);
            temp = temp.parent;
        }
        System.out.println(actions.size());
        moves_count=actions.size();
        while (!actions.isEmpty()) {
            switch (actions.pop()) {
                case 0:
                   actions_backward.add("down");
                    break;
                case 1:
                    actions_backward.add("up");
                    break;
                case 3:
                    actions_backward.add("left");
                    break;
                case 2:
                    actions_backward.add("right");
                    break;
            }
        }
        while(!actions_backward.isEmpty()){
            System.out.println(actions_backward.pop());
        }

    }

    public Board getBoard() {
        return board;
    }

    public int getMoves_count() {
        return moves_count;
    }
}
