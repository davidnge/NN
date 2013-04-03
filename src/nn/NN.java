/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nn;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.*;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import structs.Node;
import structs.SOM;
import structs.Vector;
import utils.UtilityMethods;

/**
 *
 * @author ngecheekeen
 */
public class NN {
    
    
    static int DIMENSIONS_OF_VECTORS = 0;
    static int NUMOFNODES = 0;
    static SOM som;
    static int HEIGHT,WIDTH = 0;
    
    
    public static void main(String[] args) {
        // TODO code application logic here

        NN nn = new NN();
        
        String temp=null;
        
         try {
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
             System.out.println("Enter height of SOM: ");
             temp = bufferedReader.readLine();
             HEIGHT = Integer.parseInt(temp);
             System.out.println("Enter width of SOM: ");
             temp = bufferedReader.readLine();
             WIDTH = Integer.parseInt(temp);
         } catch (NumberFormatException ex) {
         System.out.println("Not a number !");
         } catch (IOException e) {
            e.printStackTrace();
         }
        
       
        nn.ReadDimensions("/Users/ngecheekeen/NetBeansProjects/NeuralPrac/src/neuralprac/NeuralPrac_input.txt");
        som = new SOM(HEIGHT,WIDTH, DIMENSIONS_OF_VECTORS);
        NUMOFNODES = HEIGHT*WIDTH;
        nn.PerformOperations("/Users/ngecheekeen/NetBeansProjects/NeuralPrac/src/neuralprac/NeuralPrac_input.txt");

        
    }
    
    //read & initialize dimensions 
    public static void ReadDimensions(String FileName)
    {
        String line;
        
        try
        {
            FileReader in = new FileReader(FileName);
            BufferedReader br = new BufferedReader(in);
            
            if((line = br.readLine()) != null)
            {
                StringTokenizer token = new StringTokenizer(line, ",");
                DIMENSIONS_OF_VECTORS = token.countTokens();
                
            }
        }
        catch(Exception e)
        {
            
        }  
    }
    
    //create vectors for input file
    public static Vector InitializeVector(String line)
    {
        Vector v = new Vector(DIMENSIONS_OF_VECTORS);
        
        StringTokenizer token = new StringTokenizer(line,",");
        while (token.hasMoreTokens())
        {   
           for (int i = 0; i<DIMENSIONS_OF_VECTORS; i++)
           {
               String t = token.nextToken().toString();               
               v.ELEMENTS[i] = Double.parseDouble(t);
           }
        }
        return v;
    }

    //Initialize nodes in SOM
    /**public static void InitializeNode(int x, int y, String FileName)
    {
        SOM = new SOM();
        ReadDimensions(FileName);
 
        for (int i=0; i<numOfNodes; i++){
            SOM[i] = new Node(DIMENSIONS_OF_VECTORS);
                    
        }  
    }**/
    
    // performs normalization and randomization to vectors and weights for all nodes; per iteration
    public static void PerformOperations(String FileName)
    {
        String LINE;
        Vector x;   
        // array to show output after each iteration
        double [] output = new double[NUMOFNODES];
  
            try{
                FileInputStream in = new FileInputStream(FileName);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));  
                    while((LINE = br.readLine()) != null) //for each line, multiply by all nodes
                    {
                       x = InitializeVector(LINE);  //x = initializa vector for each line in input
                       Vector temp = (new UtilityMethods()).normalization(x); // normalize x
                       int outputCount = 0;
                           while (outputCount<NUMOFNODES)
                           { 
                              for (int i = 0; i<HEIGHT; i++)
                              {
                                  for (int j = 0; j<WIDTH; j++)
                                  {   
                                      output[outputCount] = (new UtilityMethods()).multiply(som.getNode(i,j).getWeights(),temp);
                                      outputCount++;
                                  }
                              }
                           } 
                           for (int e = 0; e<NUMOFNODES; e++)
                           {
                               System.out.print(output[e] +" ||");
                           }
                            
                           
                           //print Node with highest Value
                           System.out.print("Highest Node is :" + FindLargestNode(output));
                           System.out.print("\n"); 
                    }     
               }catch(Exception e)
              {}
 
    }
    
    public static double FindLargestNode(double [] d)
    {
        double HIGHESTNODE = d[0];
        for (int i = 0; i<NUMOFNODES; i++)
        {
           if (d[i]>HIGHESTNODE)
           {
               HIGHESTNODE = d[i];
           }
        }
        return HIGHESTNODE;
    }

}
