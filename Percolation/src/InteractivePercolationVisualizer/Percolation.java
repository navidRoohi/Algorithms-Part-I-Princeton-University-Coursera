
package InteractivePercolationVisualizer;

import edu.princeton.cs.algs4.*;
/**
 *
 * @author navidroohibroojeni
 */
public class Percolation {
    
   public static int gridSize; 
   public boolean grid[][];
   static WeightedQuickUnionUF quickUnionUF;
   int topVirtualRow, bottomVirtualRow;

   // create n-by-n grid, with all sites blocked 
   public Percolation(int n){
       gridSize = n;
       grid = new boolean[gridSize+2][gridSize+2];
       quickUnionUF = new WeightedQuickUnionUF(n * n + 2);
       topVirtualRow = 0;
       bottomVirtualRow  = n * n + 1;
       for(int i = 1 ; i <= n ; i++){
           quickUnionUF.union(topVirtualRow, mapTwoDToOneD(1 , i));
           quickUnionUF.union(bottomVirtualRow, mapTwoDToOneD(n , i));
       }
   }
   // map 2 dimensional grid to 1 dimensional. 
   public static int mapTwoDToOneD(int a, int b){
        return (b - 1) * gridSize + a ;
   }
          
    // open site (row i, column j) if it is not open already
   public void open(int i, int j){
       chekCornerCases(i,j);
       grid[i][j] = true;
         // front
         if((j+1 < gridSize) && isOpen(i, j+1) ){
                    quickUnionUF.union(mapTwoDToOneD(i,j),mapTwoDToOneD(i,j+1));
         }
         // buttom
         if((i+1 < gridSize) && isOpen(i+1, j) ){
                   quickUnionUF.union(mapTwoDToOneD(i,j),mapTwoDToOneD(i+1,j));
         }
          // Left
         if((j-1 >= 1) && isOpen(i, j-1) ){
                   quickUnionUF.union(mapTwoDToOneD(i,j),mapTwoDToOneD(i,j-1));
         }
          // top
         if((i-1 >= 1) && isOpen(i-1, j) ){
                   quickUnionUF.union(mapTwoDToOneD(i,j),mapTwoDToOneD(i-1,j));
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
       return quickUnionUF.connected(topVirtualRow, bottomVirtualRow );
   }  
   
   private void chekCornerCases(int i, int j){
      if((i <= 0) || (j <=0 )){
          throw (new IllegalArgumentException());
       }
      if((i > gridSize) || ( j  > gridSize )){
         throw new IndexOutOfBoundsException();
       }
   }

//    public static void main(String[] args) {
//        Percolation obj = new Percolation( 1 );
//        obj.open(1, 1);
////        obj.open(2, 2);
////        obj.open(3, 2);
////        obj.open(4, 4);
//       System.out.println ( obj.percolates() );
//       
//       In j = new In();
//       
//    } 
}


