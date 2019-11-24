import edu.princeton.cs.algs4.*;

public class PercolationRunner {
    private double[] fractions;

    // Perform trials independent experiments on an n-by-n grid.

    public PercolationRunner(int n, int trials) {
        if (n <= 0) {
            throw new IllegalArgumentException("n <= 0.");
        }
        if (trials <= 0) {
            throw new IllegalArgumentException("trials <= 0.");
        }
        fractions = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            int openedSites = 0;
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(n) + 1;  // base-1
                int col = StdRandom.uniform(n) + 1;  // base-1
                if (!percolation.isOpen(row, col)) {
                    percolation.open(row, col);
                    openedSites++;
                }
            }
            fractions[i] = openedSites * 1.0 / (n * n);
        }
    }


    public double mean() {  // Sample mean of percolation threshold
        return StdStats.mean(fractions);
    }

    public double stddev() { //Sample standard deviation of percolation threshold.
        return StdStats.stddev(fractions);
    }

    public double confidenceLo() { //Low end point of 95% confidence interval.
        return mean() - 1.96 * stddev() / Math.sqrt(fractions.length);
    }

    public double confidenceHi() { // High end point of 95% confidence interval.
        return mean() + 1.96 * stddev() / Math.sqrt(fractions.length);
    }

    public static void main(String[] args) { //using weighted union path compression

        Stopwatch s = new Stopwatch();
        System.out.print("Please input percolation grid size: ");
        int n = n = StdIn.readInt();
        System.out.print("Please input number of cycles to simulate: ");
        int trials = StdIn.readInt();
        PercolationRunner stats = new PercolationRunner(n, trials);

        System.out.println("Average time for each percolation = " + s.elapsedTime()/trials + " seconds.");
        System.out.println("Percolation Probability = " + stats.mean());
        System.out.println("Standard Deviation = " + stats.stddev());
        System.out.println("Low 95% confidence interval = " + stats.confidenceLo());
        System.out.println("High 95% confidence interval = " + stats.confidenceHi());
    }
}
