/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package structs;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author ngecheekeen
 */
public class Node {

    Vector WEIGHT_VECTOR = null;

    public Node(int Dimension)
    {
        WEIGHT_VECTOR = new Vector(Dimension);
        WEIGHT_VECTOR.initializeWeights();  
    }
 
    public Vector getWeights()
    {
        return this.WEIGHT_VECTOR;
    }
    
    
}
