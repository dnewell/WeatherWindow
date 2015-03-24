import java.io.*;
import java.net.URL;


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
    	 //URL url = getClass().getResource("data.ser");
         //FileOutputStream fileOut = new FileOutputStream(url.getPath());
    	 FileOutputStream fileOut = new FileOutputStream("data.ser");

         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(s);
         out.close();
         fileOut.close();
         System.out.println("Serialized data is saved in data.ser");
      }catch(IOException i)
      {
          i.printStackTrace();
      }
   }
}