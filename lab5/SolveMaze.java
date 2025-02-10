import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SolveMaze {
    private int[][] maze;
    private int startRow, startCol;
    private int endRow, endCol;
    private ArrayList<int[]> path = new ArrayList<>();

    private void loadMaze() {
        String filePath = "maze_txt.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            ArrayList<int[]> data = new ArrayList<>();
            int row = 0;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\t");
                int[] intValues = new int[values.length];
                for (int col = 0; col < values.length; col++) {
                    switch (values[col]) {
                        case "W":
                            intValues[col] = -1;
                            break;
                        case "C":
                            intValues[col] = 0;
                            break;
                        case "S":
                            intValues[col] = 1;
                            startRow = row;
                            startCol = col;
                            break;
                        case "F":
                            intValues[col] = -2;
                            endRow = row;
                            endCol = col;
                            break;
                    }
                }
                data.add(intValues);
                row++;
            }
            maze = data.toArray(new int[0][]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void numberAdjacent() {
        ArrayList<int[]> queue = new ArrayList<>();
        queue.add(new int[]{startRow, startCol});
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        int index = 0;

        while (index < queue.size()) {
            int[] cell = queue.get(index++);
            int row = cell[0];
            int col = cell[1];
            for (int i = 0; i < 4; i++) {
                int newRow = row + dr[i];
                int newCol = col + dc[i];
                if (newRow >= 0 && newRow < maze.length && newCol >= 0 && newCol < maze[0].length) {
                    if (maze[newRow][newCol] == 0 || maze[newRow][newCol] == -2) {
                        maze[newRow][newCol] = maze[row][col] + 1;
                        queue.add(new int[]{newRow, newCol});
                    }
                }
            }
        }
    }

    public void makePaths() {
        numberAdjacent();
    }

    public void backtrack() {
        int row = endRow;
        int col = endCol;
        path.add(new int[]{row, col});
        
        int steps = maze[endRow][endCol];
        while (steps > 1) {
            if (row > 0 && maze[row - 1][col] == steps - 1) {
                row--;
            } else if (row < maze.length - 1 && maze[row + 1][col] == steps - 1) {
                row++;
            } else if (col > 0 && maze[row][col - 1] == steps - 1) {
                col--;
            } else if (col < maze[0].length - 1 && maze[row][col + 1] == steps - 1) {
                col++;
            }
            path.add(new int[]{row, col});
            steps = maze[row][col];
        }
    }

    private void displayMaze() {
        for (int[] row : maze) {
            for (int cell : row) {
                if (cell == -1) System.out.print("W ");
                else if (cell == 1) System.out.print("S ");
                else if (cell == -2) System.out.print("F ");
                else if (cell > 1) System.out.print(". ");
                else System.out.print("C ");
            }
            System.out.println();
        }
    }

    private void displayPath() {
        for (int[] step : path) {
            maze[step[0]][step[1]] = '*';
        }
        for (int[] row : maze) {
            for (int cell : row) {
                if (cell == '*') System.out.print("* ");
                else if (cell == -1) System.out.print("W ");
                else System.out.print("  "); 
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        SolveMaze sm = new SolveMaze();
        sm.loadMaze();
        sm.makePaths();
        sm.backtrack();
        sm.displayMaze();
        sm.displayPath();
    }
}
