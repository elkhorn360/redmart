/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ski;

import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author Elkhorn
 */
public class Ski {
    
    
    static final String FILENAME = "map";
    static final String IN       = FILENAME + ".txt";
    static PrintStream         out      = System.out;
    static int[][] map;
    
    static int l;
    static int w;
    
    //initiate max and lagest drop.
    static int maxNodes = 0;
    static int lagestDrop = 0;
    static String way = "";

    private static void solve() throws Exception {
  
        //iterate through every node.        
        for (int x = 0; x < l; x++){
            for (int y = 0; y < w; y++){ 
                //out.println("visiting x:"+x+"  y:"+y);                
                way = "";
                visitNode(map[x][y], x, y,0);
                
            }
        }
        //visitNode(map[1][0], 1, 0,0);
        
        out.println("length: "+ maxNodes + " largestDrop: "+lagestDrop+" mail: "+maxNodes+lagestDrop+"@redmart.com");
            
    }
    
    
    public static void visitNode(int start, int x, int y, int nodeCount) {
        
        nodeCount++;
        
        
        
        int current = map[x][y];
        

        boolean deadend = true;
       
        //north
        if(x-1 >= 0 && (map[x-1][y] < current)){            
            way += current+" ^ ";
            visitNode(start, x-1, y,nodeCount);
            deadend = false;
        }
        
        //east
        if(y+1 < w && (map[x][y+1] < current)){            
            way += current+" > ";
            visitNode(start,x, y+1,nodeCount);
            deadend = false;
        }
        
        //south
        if(x+1 < l && (map[x+1][y] < current)){            
            way += current+" v ";
            visitNode(start,x+1, y,nodeCount);
            deadend = false;
        }
        
        //west
        if(y-1 >= 0 && (map[x][y-1] < current)){            
            way += current+" < ";
            visitNode(start,x, y-1,nodeCount);
            deadend = false;
        }
        
        if(deadend){            
            if(maxNodes < nodeCount){
                maxNodes = nodeCount;
                lagestDrop = start-map[x][y];
            }
            if(maxNodes == nodeCount){                               
                if(lagestDrop < (start-map[x][y])){                    
                    lagestDrop = start-map[x][y];                    
                }                
            }
        }        
    }
    
    public static void main(String[] args) throws Exception {
       Scanner sc = new Scanner(new File("map.txt"));
       //get the slength and width
        l = sc.nextInt();
        w = sc.nextInt();
        map = new int[l][w];
        
        
        // read all the values to 2d array.
        for (int x = 0; x < l; x++){
            for (int y = 0; y < w; y++){
                map[x][y] = sc.nextInt();
            }
        }
       
       
        solve();
        
        sc.close();
        out.close();
    }
    
}
