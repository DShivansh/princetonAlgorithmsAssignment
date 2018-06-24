import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;
import java.lang.*;

public class PercolationStats {
    private Percolation pr;
    private double[] fractions;
    private int numberOfTrials;

    public PercolationStats(int n, int trials){
        numberOfTrials = trials;
        if(n<=0 || trials<=0){
            throw new IllegalArgumentException("Given n <= 0 || trials <= 0");
        }
        fractions = new double[numberOfTrials];
        for(int i = 0; i < numberOfTrials; i++){
            int open = 0;
            pr = new Percolation(n);
            while(!pr.percolates()){
                int row = StdRandom.uniform(1,n+1);
                int col = StdRandom.uniform(1, n+1);
                if(!pr.isOpen(row, col)){
                    pr.open(row, col);
                    open+=1.0;
                }
            }
            double fract = (double)open/(n*n);
            fractions[i] = fract;
        }
    }    // perform trials independent experiments on an n-by-n grid
    public double mean(){
        return StdStats.mean(fractions);
    }                          // sample mean of percolation threshold
    public double stddev(){
        return StdStats.stddev(fractions);
    }                        // sample standard deviation of percolation threshold
    public double confidenceLo() {
        return mean()-(stddev()*1.96)/Math.sqrt(numberOfTrials);
    }                 // low  endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean()+(stddev()*1.96)/Math.sqrt(numberOfTrials);
    }                 // high endpoint of 95% confidence interval

    public static void main(String[] args){
        int N = Integer.parseInt("2");
        int T = Integer.parseInt("10000");
        PercolationStats ps = new PercolationStats(N, T);

        String confidence = ps.confidenceLo() + ", " + ps.confidenceHi();
        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = " + confidence);
    }        // test client (described below)
}