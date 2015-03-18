import java.io.*;
import java.io.Serializable;


public class Serialize implements Serializable
{
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Serialize(String loc, int units)
   {	
	 
      SavedData s = new SavedData();
      s.units = units;
      s.location= loc;
      try
      {
         FileOutputStream fileOut =
         new FileOutputStream("data.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(s);
         out.close();
         fileOut.close();
         System.out.printf("Serialized data is saved in /src/data.ser");
      }catch(IOException i)
      {
          i.printStackTrace();
      }
   }
}