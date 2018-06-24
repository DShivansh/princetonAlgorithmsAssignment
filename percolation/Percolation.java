import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.*;

public class Percolation {
    private boolean arr[][];
    private WeightedQuickUnionUF qt;
    private int count;
    private int top = 0;
    private int bottom;
    public Percolation(int n)  {
        count = n;
        bottom = n*n+1;
        arr = new boolean[n][n];
        qt = new WeightedQuickUnionUF(n*n+2);
    }              // create n-by-n grid, with all sites blocked
    public void open(int row, int col) {
        arr[row-1][col-1] = true;
        if(row == 1){
            qt.union(getSize(row,col), top);
        }
        if(row == count){
            qt.union(getSize(row,col), bottom);
        }
        if(col > 1 && isOpen(row, col-1)){
            qt.union(getSize(row,col), getSize(row,col-1));
        }
        if(col < count && isOpen(row, col+1)){
            qt.union(getSize(row,col),getSize(row,col+1));
        }
        if(row > 1 && isOpen(row-1,col)){
            qt.union(getSize(row, col),getSize(row-1,col));
        }
        if(row < count && isOpen(row+1,col)){
            qt.union(getSize(row,col), getSize(row+1,col));
        }

    }   // open site (row, col) if it is not open already

    public boolean isOpen(int row, int col){
        return arr[row-1][col-1];
    }  // is site (row, col) open?

    public boolean isFull(int row, int col){
        if(row>0 && row<=count && col>0 && col<=count){
            return qt.connected(top, getSize(row,col));
        }else{
            throw new IndexOutOfBoundsException();
        }
    }  // is site (row, col) full?
    public int numberOfOpenSites()   {
        int sum = 0;
        for(boolean[] i: arr){
            for(boolean j : i){
                if(j){
                    sum+=1;
                }
            }
        }
        return sum;
    }    // number of open sites
    public boolean percolates(){
        return qt.connected(top,bottom);
    }            // does the system percolate?

    private int getSize(int row, int col){
        return (count*(row-1))+col;
    }

//    public static void main(String[] args)
}
