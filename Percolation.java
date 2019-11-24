public class Percolation{

        private final WeightedQuickUnionPathCompression normalQU;
        private final WeightedQuickUnionPathCompression backwashQU;
        private final boolean[] isOpen;
        private final int topIndex;
        private final int btmIndex;
        private final int n;

        public Percolation(int n) { //Create n-by-n grid, with all sites blocked
            if (n <= 0) {
                throw new IllegalArgumentException("n must be greater than 0.");
            }
            this.n = n;
            topIndex = 0;
            btmIndex = n * n + 1;
            backwashQU = new WeightedQuickUnionPathCompression(n * n + 2);
            normalQU = new WeightedQuickUnionPathCompression(n * n + 1);  // without bottom index
            isOpen = new boolean[n * n + 2];
            isOpen[topIndex] = true;
            isOpen[btmIndex] = true;
        }

        private int indexOf(int row, int col) {  //Convert a 2D coordinate to 1D.
            // check bounds
            if (row < 1 || row > n) {
                throw new IndexOutOfBoundsException("Row is out of bounds.");
            }
            if (col < 1 || col > n) {
                throw new IndexOutOfBoundsException("Column is out of bounds.");
            }
            return (row - 1) * n + col;
        }

        public void open(int row, int col) {   // Open site (row, col) if it is not open already
            int currIndex = indexOf(row, col);
            isOpen[currIndex] = true;

            if (row == 1) {
                backwashQU.union(currIndex, topIndex);  // Top
                normalQU.union(currIndex, topIndex);
            }
            if (row == n) {
                backwashQU.union(currIndex, btmIndex);  // Bottom
            }
            tryUnion(row, col, row - 1, col);  // North
            tryUnion(row, col, row + 1, col);  // South
            tryUnion(row, col, row, col - 1);  // West
            tryUnion(row, col, row, col + 1);  // East
        }

        private void tryUnion(int rowA, int colA, int rowB, int colB) {
            if (0 < rowB && rowB <= n && 0 < colB && colB <= n
                    && isOpen(rowB, colB)) {
                backwashQU.union(indexOf(rowA, colA), indexOf(rowB, colB));
                normalQU.union(indexOf(rowA, colA), indexOf(rowB, colB));
            }
        }



        public boolean isOpen(int row, int col) { // Is site (row, col) open?

            return isOpen[indexOf(row, col)];
        }

        public boolean isFull(int row, int col) { //Is site (row, col) full?
            return normalQU.connected(topIndex, indexOf(row, col));
        }

        public boolean percolates() { //Does the system percolate?
            return backwashQU.connected(topIndex, btmIndex);
        }

        public static void main(String[] args) {
            System.out.println("Please run PercolationRunner instead.");
        }
}