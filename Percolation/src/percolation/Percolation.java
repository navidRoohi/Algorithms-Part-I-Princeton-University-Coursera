
package percolation;

import java.lang.*;
import edu.princeton.cs.algs4.*;
/**
 *
 * @author navidroohibroojeni
 */
// This is a comment
public class Percolation {
    
   public int gridSize; 
   public boolean grid[][];
   public int gridNumbers[][];
   static WeightedQuickUnionUF weightedQuickUnionUF; 

   // create n-by-n grid, with all sites blocked 
   public Percolation(int n){
       this.gridSize = n;
       grid = new boolean[n][n];
       gridNumbers = new int[n][n];
       weightedQuickUnionUF = new WeightedQuickUnionUF(n);
       int num = 0;
       
       for(int i=0; i< n ; i++){
           for(int j=0; j< n ; j++){
               gridNumbers[j][i] = num;
               num++;
            }
       }
   }
          
    // open site (row i, column j) if it is not open already
   public void open(int i, int j){
       chekCornerCases(i,j);
       grid[i][j] = true;
       
         // front
         if(isOpen(i,j) 
                 && (i+1 < gridSize) 
                 && isOpen(i+1,j) 
                 && !weightedQuickUnionUF.connected(gridNumbers[i][j], gridNumbers[i+1][j]) ){
             
                    weightedQuickUnionUF.union(gridNumbers[i][j], gridNumbers[i+1][j]);
         }
         // buttom
         if(isOpen(i,j) 
                 && (j+1 < gridSize) 
                 && isOpen(i,j+1) 
                 && !weightedQuickUnionUF.connected(gridNumbers[i][j], gridNumbers[i][j+1])){
             
                    weightedQuickUnionUF.union(gridNumbers[i][j], gridNumbers[i][j+1]);
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
       boolean result = false;
       
       for(int i=0 ; i < gridSize; i++){
            for(int j=0 ; j < gridSize; j++){
                 
                if( weightedQuickUnionUF.connected( gridNumbers[j][0], gridNumbers[j][gridSize-1])) {
                 result = true;
                }        
            }
           
        
       }
       return result;
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
        
        System.out.println (obj.percolates() );

      
    }
    
}


