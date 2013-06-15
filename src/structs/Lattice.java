/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package structs;

/**
 *
 * @author ngecheekeen
 */
public class Lattice {
    
    Node NODE;
    Node [][] SOM;
    int SOMWIDTH,SOMHEIGHT = 0;
    
    public Lattice (int width, int height, int Dimension)
    {
        
        SOM = new Node[height][width];
        SOMHEIGHT = height;
        SOMWIDTH = width;
        
        for (int i = 0; i<height; i++)
        {
            for (int j = 0; j<width; j++)
            {  
                 
                SOM[i][j] = new Node(Dimension); 
            }
        }
    }
    
    public void setNode(int x, int y, Node a)
    {
        SOM[x][y] = a;
    }
    
    public Node getNode(int x, int y)
    {
        return SOM[x][y];
    }
    
    public int getSOMHEIGHT()
    {
        return SOMHEIGHT;
    }
    
    public int getSOMWIDTH()
    {
        return SOMWIDTH;
    }
    
}
