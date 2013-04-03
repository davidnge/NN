/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package structs;

/**
 *
 * @author ngecheekeen
 */
public class SOM {
    
    Node NODE;
    Node [][] SOM;
    
    public SOM (int x, int y, int Dimension)
    {
        SOM = new Node[x][y];
        for (int i = 0; i<x; i++)
        {
            for (int j = 0; j<y; j++)
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
}
