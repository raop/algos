package com.intvw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * List number of steps taken to reach a wall in a room filled with lazers and prisms
 */
public class LazerMaze {

    public static class Pos {
        int row;
        int col;

        Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public enum DIRECTION {
        NORTH,
        SOUTH,
        EAST,
        WEST,
        ERROR
    }

    public static void main(String [] args) throws IOException {

        char[][] maze = parseInputToMaze();
        printMaze(maze);
        System.out.println(mazeLen(maze));
    }

    private static Pos getStartPos(char[][] maze){
        for(int row=0; row < maze.length; row++){
            for(int col=0; col < maze[0].length; col++){
                if(maze[row][col] == '@'){
                    Pos startPos = new Pos(row, col);
                    return startPos;
                }
            }
        }
        Pos invalidPos = new Pos(-1, -1);
        return invalidPos;
    }

    private static int mazeLen(char[][]maze) {
        Pos currPos = getStartPos(maze);
        Pos prevPos = new Pos(currPos.row, currPos.col -1);

        int row = currPos.row;
        int col = currPos.col;
        int maxRow = maze.length;
        int maxCol = maze[0].length;
        int len = 0;

        Set<String> loopSet = new HashSet<>();

        while(row >= 0 && row < maxRow && col >=0 && col < maxCol) {
            currPos = new Pos(row, col);
            loopSet.add(formKey(prevPos, currPos));

            DIRECTION dir = getDirection(prevPos, currPos);

            char currChar = maze[row][col];
            Pos nextPos = getNextPos(dir, currPos, currChar);

            // Loop detection
            if(loopSet.contains(formKey(currPos, nextPos))) {
                return -1;
            }
            row = nextPos.row;
            col = nextPos.col;
            prevPos = currPos;
            len++;
        }

        return len;
    }

    private static String formKey(Pos from, Pos to) {
        StringBuffer sb = new StringBuffer();
        sb.append(from.row);
        sb.append(",");
        sb.append(from.col);
        sb.append("-");
        sb.append(to.row);
        sb.append(",");
        sb.append(to.col);
        return sb.toString();
    }

    private static Pos getNextPos(DIRECTION dir, Pos currPos, char currChar) {
        Pos nextPos = null;

        switch(currChar) {
            case '@' :
            case '-':
                // Continue in same direction
                nextPos = continueInDirection(dir, currPos);
                break;

            case '>':
                // go right
                nextPos = headEast(currPos);
                break;

            case '<':
                // go left
                nextPos = headWest(currPos);
                break;

            case '^':
                // go up
                nextPos = headNorth(currPos);
                break;

            case 'v':
                // go down
                nextPos = headSouth(currPos);
                break;

            case 'O':
                // reverse
                nextPos = reverseDirection(dir, currPos);
                break;

            case '/':
                // left <-> up, right <-> down
                nextPos = deflect90(dir, currPos);
                break;

            case '\\':
                // right <-> up, left <-> down
                nextPos = deflect270(dir, currPos);
                break;

        }
        return nextPos;
    }

    private static Pos headNorth(Pos currPos) {
        return new Pos(currPos.row -1, currPos.col);
    }

    private static Pos headSouth(Pos currPos) {
        return new Pos(currPos.row +1, currPos.col);
    }

    private static Pos headWest(Pos currPos) {
        return new Pos(currPos.row, currPos.col -1);
    }

    private static Pos headEast(Pos currPos) {
        return new Pos(currPos.row, currPos.col + 1);
    }

    private static Pos continueInDirection(DIRECTION dir, Pos currPos) {
        Pos nextPos = null;
        switch(dir) {
            case EAST:
                nextPos = headEast(currPos);
                break;

            case WEST:
                nextPos = headWest(currPos);
                break;

            case NORTH:
                nextPos = headNorth(currPos);
                break;

            case SOUTH:
                nextPos = headSouth(currPos);
                break;
        }
        return nextPos;
    }

    private static Pos reverseDirection(DIRECTION dir, Pos currPos) {
        Pos nextPos = null;
        switch(dir) {
            case EAST:
                nextPos = headWest(currPos);
                break;

            case WEST:
                nextPos = headEast(currPos);
                break;

            case NORTH:
                nextPos = headSouth(currPos);
                break;

            case SOUTH:
                nextPos = headNorth(currPos);
                break;
        }
        return nextPos;
    }

    /* / */
    private static Pos deflect90(DIRECTION dir, Pos currPos) {
        Pos nextPos = null;
        switch(dir) {
            case EAST:
                nextPos = headNorth(currPos);
                break;

            case WEST:
                nextPos = headSouth(currPos);
                break;

            case NORTH:
                nextPos = headEast(currPos);
                break;

            case SOUTH:
                nextPos = headWest(currPos);
                break;
        }
        return nextPos;
    }

    /* \ */
    private static Pos deflect270(DIRECTION dir, Pos currPos) {
        Pos nextPos = null;
        switch(dir) {
            case EAST:
                nextPos = headSouth(currPos);
                break;

            case WEST:
                nextPos = headNorth(currPos);
                break;

            case NORTH:
                nextPos = headWest(currPos);
                break;

            case SOUTH:
                nextPos = headEast(currPos);
                break;
        }
        return nextPos;
    }

    private static DIRECTION getDirection(Pos from, Pos to){
        if(from.row == to.row) {
            if(from.col < to.col) {
                return DIRECTION.EAST;
            }
            else if(from.col > to.col) {
                return DIRECTION.WEST;
            }
        } else if (from.col == to.col) {
            if (from.row < to.row) {
                return DIRECTION.SOUTH;
            } else if (from.row > to.row) {
                return DIRECTION.NORTH;
            }
        }

        return DIRECTION.ERROR;
    }

    private static char[][] parseInputToMaze() throws IOException {
        BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in));
        String line;
        char[][] init = new char[50][];
        int rowSize = 0;
        while((line = reader.readLine()) != null) {
            if(line.equals("\n") || line.isEmpty()) {
                break;
            }
            init[rowSize++] = line.replaceAll("\\s+", "").toCharArray();
        }

        int colSize = init[0].length;
        char[][] ret = new char[rowSize][colSize];
        for(int row =0; row < rowSize; row++){
            for(int col=0; col < colSize; col++) {
                ret[row][col] = init[row][col];
            }
        }
        return ret;
    }

    private static void printMaze(char[][] maze) {
        for(int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[row].length; col++) {
                System.out.print(maze[row][col]);
            }
            System.out.println();
        }
    }
}
