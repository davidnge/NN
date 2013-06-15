/*
 * This class trains the Nodes in Lattice and calls the LatticeRenderer class
 * to draw on the JPanel
 */
package structs;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import utils.UtilityMethods;
import org.apache.commons.math3.linear.ArrayRealVector;


/**
 *
 * @author ngecheekeen
 */
public class Trainer implements ActionListener{
    
    static int DIMENSIONS_OF_VECTORS = 0; 
    static double [][] tempOUTPUTSOM;
    static int xPositionOfLargestNode = 0;
    static int yPositionOfLargestNode = 0;
    static int countIteration;
    static ArrayRealVector normalizedVector;
    static final int TOTALITERATION = 500;
    
    static Lattice lattice;
    static String file;
    static int numIteration;
    static double learningRate;
    static LatticeRenderer render; 
    
   
    static double rad;  
    static String functionSelected;
    static WinnerNode winner;
    
    
    public Trainer(Lattice a, String b, double c, LatticeRenderer d, String function)
    {
        lattice = a;
        file = b;
        learningRate = c;
        render = d;
        rad = a.getSOMWIDTH()/2;
        functionSelected = function;
        winner = new WinnerNode(lattice);
        countIteration = 1;
    }
    
    /** 
     * Train the Nodes and draw them
     * Perform the following tasks: - Read and Initialize the dimension of input vectors
     *                              - Perform dot product functions
     *                              - Find largest Node
     *                              - Update weight vectors for affected Nodes
     *                              - Draw the Lattice
     * 
     * @param           input file and the LatticeRenderer object
     * @void            draws the Lattice in the GUI
     */
    public static void Train(String FileName, LatticeRenderer r)
    {
        
        ReadDimensions(FileName);
        tempOUTPUTSOM = new double [lattice.getSOMHEIGHT()][lattice.getSOMWIDTH()];
        
        String LINE;
        ArrayRealVector v;
   
            try{
                FileInputStream in = new FileInputStream(FileName);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));  
               
                    //read all lines from input file
                    while((LINE = br.readLine()) != null) 
                    {                        
                       //  create ArrayRealVector from input vector 
                       double [] tempVector = InitializeVector(LINE);  
                       v = new ArrayRealVector(tempVector); 
                        
                       //Normalize vector before dot product operation
                       normalizedVector = (new UtilityMethods().normalization(v));

                             // dot product operations, iterate all nodes in lattice
                             for (int i = 0; i<lattice.getSOMHEIGHT(); i++)
                              {
                                  for (int j = 0; j<lattice.getSOMWIDTH(); j++)
                                  { 
                                      tempOUTPUTSOM[i][j] = normalizedVector.dotProduct(lattice.getNode(i, j).getWeights());
                                  }
                              }
                              
                              FindLargestNode(tempOUTPUTSOM);
                              UpdateWeight(learningRate);
                              
                              //visualization for different functions 
                              Function(r,functionSelected);
                    }     
               }catch(Exception e)
              {}
            
            countIteration++;            
    }
    
    /** 
     * Produce the x and y coordinate of the largest node for each iteration
     * 
     * @param           tempOUTPUTSOM: A 2D array of type double that stores the 
     *                  output after dot product (between input vector and all 
     *                  nodes' vector) operation.
     * @return          xPositionOfLargestNode, yPositionOfLargestNode 
     */
    public static void FindLargestNode(double [][] tempOUTPUTSOM)
    {
        double HIGHESTNODE = tempOUTPUTSOM[0][0];
        
        for (int i = 0; i<lattice.getSOMHEIGHT(); i++)
        {
            for (int j = 0; j<lattice.getSOMWIDTH(); j++)
            {
                if (tempOUTPUTSOM[i][j]>HIGHESTNODE)
                {
                    HIGHESTNODE = tempOUTPUTSOM[i][j];
                    xPositionOfLargestNode = i;
                    yPositionOfLargestNode = j;                    
                }
            }
  
        } 
    }
    
    /** 
     * Takes an input vector (from input file) stores them in a temporary array
     * so that they can be converted to ArrayRealVector later
     * 
     * @param           A String of vector values e.g: "1,0,1,0" 
     * @return          An ArrayRealVector e.g: 1,0,1,0
     */
    public static double [] InitializeVector(String line)
    {
        double [] v = new double [DIMENSIONS_OF_VECTORS];
        
        StringTokenizer token = new StringTokenizer(line,",");
        while (token.hasMoreTokens())
        {   
           for (int i = 0; i<DIMENSIONS_OF_VECTORS; i++)
           {
               String t = token.nextToken().toString();               
               v[i] = Double.parseDouble(t);
           }
        }
        return v;
    }
      
    /** 
     * Takes an input file and determine its vector's dimension
     * 
     * @param           A String file address 
     * @void            Initialize DIMENSIONS_OF_VECTORS
     */
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
    
    
    /** 
     * Update the weight of  nodes that fall within the radius defined
     * 
     * @param           Learning rate from user input
     * @void            Initialize and assign new weight vector to affected nodes
     */    
    public static void UpdateWeight(double learningRate)
    {
       
            double upperRad = Math.ceil(rad);
            
            //Parameters are defined to minimize the number of Nodes to iterate.
            int IterateMinHeight, IterateMinWidth, IterateMaxHeight, IterateMaxWidth = 0;
            
            if((xPositionOfLargestNode-upperRad)<0)
            {
                IterateMinHeight = 0;
            }
            else
            {
                IterateMinHeight = xPositionOfLargestNode- (int)upperRad;
            }
            
            if((xPositionOfLargestNode+upperRad)>lattice.getSOMHEIGHT())
            {
                IterateMaxHeight = lattice.getSOMHEIGHT();
            }
            else
            {
                IterateMaxHeight = xPositionOfLargestNode + (int)upperRad;
            }
            
            if((yPositionOfLargestNode-upperRad)<0)
            {
                IterateMinWidth = 0;
            }
            else
            {
                IterateMinWidth = yPositionOfLargestNode - (int)upperRad;
            }
            
            if((yPositionOfLargestNode+upperRad)>lattice.SOMWIDTH)
            {
                IterateMaxWidth = lattice.getSOMWIDTH();
            }
            else
            {
                IterateMaxWidth = yPositionOfLargestNode+ (int)upperRad;
            }
            
            //Iterate and change node's weight vector if node falls under the radius
            for (int k = IterateMinHeight; k< IterateMaxHeight; k++)
            {
                for (int l = IterateMinWidth; l< IterateMaxWidth; l++)
                {
                     if (new UtilityMethods().euclideanDist(xPositionOfLargestNode, k, yPositionOfLargestNode, l) <= rad)
                     {
                         // new weight vector defined
                         double theta = Math.exp((- new UtilityMethods().euclideanDist(xPositionOfLargestNode, k, yPositionOfLargestNode, l))/(2*Math.pow(rad, 2)*countIteration));
                         ArrayRealVector newWeight = lattice.getNode(k, l).getWeights().add((normalizedVector.subtract(lattice.getNode(k, l).getWeights()).mapMultiply(learningRate*theta))); 
                         lattice.getNode(k,l).setWeights(newWeight);
                     }
                }
            }
            
           // Decay radius after each iteration
           rad = rad*Math.exp(-countIteration/(TOTALITERATION/Math.log(Math.max(lattice.getSOMHEIGHT(), lattice.getSOMWIDTH()))));
    }
    
    /** 
     * Determine which visualization method to call
     * 
     * @param           Name of the visualization method or "function"
     * @void            Call LatticeRenderer for different visualization method
     */
    public static void Function(LatticeRenderer r, String function)
    {
        
        if (function.equalsIgnoreCase("UMatrix"))
        {
            UMatrix Umat = new UMatrix();                          
            Umat.renderUMatrix(lattice);
                              
           double [][] tempOUTPUTuMat = new double [Umat.getHeight()][Umat.getWidth()];
                              
            for(int i =0; i<tempOUTPUTuMat.length; i++)
            {
               for (int j=0; j<tempOUTPUTuMat[0].length; j++)
               {
                   tempOUTPUTuMat[i][j] = Umat.getUMatrixElement(i, j);
               }
            }
       
           r.renderMatrix(tempOUTPUTuMat);
        }
        
        else if (function.equalsIgnoreCase("Winner"))
        {
            
            winner.registerWinner(xPositionOfLargestNode, yPositionOfLargestNode);
   
            int [][] tempOUTPUTWinner = new int [lattice.getSOMHEIGHT()][lattice.getSOMWIDTH()];

            for (int i=0; i<lattice.getSOMHEIGHT(); i++)
            {
                for (int j=0; j<lattice.getSOMWIDTH(); j++)
                {           
                     tempOUTPUTWinner[i][j] = winner.getValue(i, j);
                }
            }
            
            r.renderWinner(tempOUTPUTWinner);
        }
        
        else if (function.equalsIgnoreCase("fourN"))
        {
            FourNeighbour four = new FourNeighbour(lattice);
            
            double [][] tempOUTPUTfour = new double[lattice.getSOMHEIGHT()][lattice.getSOMWIDTH()];
            for (int i=0; i<lattice.getSOMHEIGHT(); i++)
            {
                for (int j=0; j<lattice.getSOMWIDTH(); j++)
                {
                    tempOUTPUTfour [i][j] = four.getFourMatrixElement(i, j);
                }
            }
            
            r.renderMatrix(tempOUTPUTfour);
        }
        
        else if (function.equalsIgnoreCase("eightN"))
        {
            EightNeighbour eight = new EightNeighbour(lattice);
            
            double [][] tempOUTPUTeight = new double[lattice.getSOMWIDTH()][lattice.getSOMHEIGHT()];
            for (int i=0; i<lattice.getSOMWIDTH(); i++)
            {
                for (int j=0; j<lattice.getSOMHEIGHT(); j++)
                {
                    tempOUTPUTeight [i][j] = eight.getEightMatrixElement(i, j);
                }
            }
            
            r.renderMatrix(tempOUTPUTeight);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {  
       
           Train(file,render);
           System.out.println(countIteration);
           
       //total iterations = 500
       if (countIteration > TOTALITERATION)
       {
          System.exit(1); 
       }
         
      
    }
    
}
