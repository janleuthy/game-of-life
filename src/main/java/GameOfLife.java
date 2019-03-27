public class GameOfLife {

    // Konstante für die Länge und Breite unseres Spielfeldes,
    // falls der Anwender keine Werte definiert hat.
    private static final int ROW = 12;
    private static final int COL = 12;

    // Definition unseres Spielfeldes
    private boolean field[][];

    /**
     * Zählen der Nachbarfelder
     */
    private int countNeighbors(int row, int col) {
        int count = 0;

        for (int x = row - 1; x <= row + 1; x++) {

            // Die Position von x muss innerhalb des Feldes sein
            if (x >= 0 && x < ROW) {

                for (int y = col - 1; y <= col + 1; y++) {

                    // Die Position von x muss innerhalb des Feldes sein
                    if (y >= 0 && y < COL && x != row && y != col) {

                        // Unsere eigene Position (row, col) dürfen wir nicht mitzählen
                        if (!(x == row && y == col)) {
                            if (field[x][y]) {
                                count++;
                            }
                        }
                    }
                }
            }
        }

        return count;
    }

    private void nextGeneration() {

        boolean[][] newGeneration = new boolean[ROW][COL];

        for (int x = 0; x < ROW - 1; x++) {
            for (int y = 0; y < COL; y++) {

                int neighbors = countNeighbors(x, y);

                if (neighbors < 2 || neighbors > 3) {
                    newGeneration[x][y] = false;
                } else if (!field[x][y] && neighbors == 3) {
                    newGeneration[x][y] = true;
                } else {
                    newGeneration[x][y] = field[x][y];
                }

            }
        }
        field = newGeneration;

    }


    /**
     * Initialisieren unseres Spielfeldes
     */
    private void initField(int row, int col) {

        this.field = new boolean[row][col];
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < col; y++) {
                this.field[x][y] = Math.random() < 0.5;
            }
        }
    }

    /**
     * Darstellen des Spielfeldes
     */
    private void showField() {
        for (int x = 0; x < ROW; x++) {
            for (int y = 0; y < COL; y++) {
                System.out.print((this.field[x][y]) ? "1 " : "0 ");
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {

        GameOfLife game = new GameOfLife();

        game.initField(ROW, COL);
        game.showField();
        int count = game.countNeighbors(0, 0);

        for (int i = 0; i <= 10; i++) {
            System.out.println();
            game.nextGeneration();
            game.showField();
        }
    }
}
