
package PercolationStats;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

/**
 *
 * @author navid roohi
 */
public class PercolationStats {
    
    /// holds each experiment's percolation threshold result
   private double[] thresholdResults;
   private int T;
    // perform trials independent experiments on an n-by-n grid
   public PercolationStats(int n, int T){       
       if ( n < 0 || T< 0){
           throw new java.lang.IllegalArgumentException();
       }
       this.T = T;
       thresholdResults = new double[T];
       for (int t = 0; t < T; t++)
       {
            Percolation percolation = new Percolation(n);
            int openSites = 0;
            while (!percolation.percolates())
            {
                int i = StdRandom.uniform(1,n+1);
                int j = StdRandom.uniform(1,n+1);
           
                if (!percolation.isOpen(i,j))
                {
                    percolation.open(i,j);
                    openSites += 1;
                }
            }
            double threshold = (double)openSites/(double)(n*n);
           thresholdResults[t] = threshold;
       }
       
   }
   // sample mean of percolation threshold
   public double mean(){
       return StdStats.mean(thresholdResults);
   }      
   // sample standard deviation of percolation threshold
   public double stddev(){
      return StdStats.stddev(thresholdResults);

   }
   // low  endpoint of 95% confidence interval
   public double confidenceLo() {
       return mean() - (1.96*stddev()/Math.sqrt(T));
   }
   // high endpoint of 95% confidence interval
   public double confidenceHi(){
        return mean() + (1.96*stddev()/Math.sqrt(T));
   }                  
//   // test client (described below)
//   public static void main(String[] args){
//       int N = 5;
//       int T = 6;
//       PercolationStats stats = new PercolationStats(N,T);
//       StdOut.println("mean = "+ stats.mean());
//       StdOut.println("standard deviation = "+ stats.stddev());
//       StdOut.println("95% confidence interval = "+ stats.confidenceLo() + " , " + stats.confidenceHi());
//   }    
}