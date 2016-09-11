
package percolation;

import java.lang.*;
import edu.princeton.cs.algs4.*;
/**
 *
 * @author navidroohibroojeni
 */
public class Percolation {
    
   public static int gridSize; 
   public boolean grid[][];
   static WeightedQuickUnionUF weightedQuickUnionUF;
   int topVirtualRow, bottomVirtualRow;

   // create n-by-n grid, with all sites blocked 
   public Percolation(int n){
       gridSize = n;
       grid = new boolean[n][n];
       weightedQuickUnionUF = new WeightedQuickUnionUF(n * n + 2);
       for(int i = 1 ; i <= n ; i++){
           topVirtualRow = 0;
           bottomVirtualRow  = n * n + 1;
           weightedQuickUnionUF.union(topVirtualRow, mapTwoDGridToOneDGrid(i , 1));
           weightedQuickUnionUF.union(bottomVirtualRow, mapTwoDGridToOneDGrid(i , n));
       }
   }
   // map 2 dimensional grid to 1 dimensional. 
   public static int mapTwoDGridToOneDGrid(int a, int b){
        return (a - 1) * gridSize + b ;
   }
          
    // open site (row i, column j) if it is not open already
   public void open(int i, int j){
        chekCornerCases(i,j);
       grid[i][j] = true;
         // front
         if((i+1 < gridSize) 
                 && isOpen(i + 1, j) 
                 && !weightedQuickUnionUF.connected( mapTwoDGridToOneDGrid(i, j)  , mapTwoDGridToOneDGrid(i+1, j)) ){
                    weightedQuickUnionUF.union(mapTwoDGridToOneDGrid(i, j), mapTwoDGridToOneDGrid(i+1, j));
         }
         // buttom
         if((j+1 < gridSize) 
                 && isOpen(i, j + 1) 
                 && !weightedQuickUnionUF.connected(mapTwoDGridToOneDGrid(i, j), mapTwoDGridToOneDGrid(i, j+1))){
                   weightedQuickUnionUF.union(mapTwoDGridToOneDGrid(i, j), mapTwoDGridToOneDGrid(i, j+1 ));
         }
          // Left
         if((j+1 < gridSize) 
                 && isOpen(i - 1, j) 
                 && !weightedQuickUnionUF.connected(mapTwoDGridToOneDGrid(i, j), mapTwoDGridToOneDGrid(i, j+1))){
                   weightedQuickUnionUF.union(mapTwoDGridToOneDGrid(i, j), mapTwoDGridToOneDGrid(i, j+1 ));
         }
   }
   
   // is site (row i, column j) open?
   public boolean isOpen(int i, int j){
       chekCornerCases(i,j);
       return grid[i][j];
   }
   // is site (row i, column j) full?
   public boolean isFull(int i, int j){
       chekCornerCases(i,j);
       return !grid[i][j];

   }
   // does the system percolate?
   public boolean percolates(){
       return weightedQuickUnionUF.connected(topVirtualRow, bottomVirtualRow );
   }  
   
   private void chekCornerCases(int i, int j){
      if((i <= 0) || (j <=0 )){
          throw (new IllegalArgumentException());
       }else if((i > gridSize) || (j  > gridSize )){
         throw new IndexOutOfBoundsException();
       }
   }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Percolation obj = new Percolation( 5 );
      //  System.out.println ( obj.isOpen(4, 4) );
       // obj.open(1, 2);
       // obj.open(2, 2);

        System.out.println ( obj.percolates() );
        
       // System.out.println ( weightedQuickUnionUF.connected(mapTwoDGridToOneDGrid(1,2), mapTwoDGridToOneDGrid(2,2)) );

        

    } 
}


