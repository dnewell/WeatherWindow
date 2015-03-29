import java.io.*;


/**
 * Serializes the data.ser file to store user presets
 * 
 * @author Team 22
 */
public class Serialize implements Serializable
{
   
	private static final long serialVersionUID = 1L;

	public Serialize(String loc, int units, float fieldFontSize)
   {	
	 
      SavedData s = new SavedData();
      s.units = units;
      s.location = loc;
      s.fieldFontSize = fieldFontSize;
      try
      {
    	 //URL url = getClass().getResource("data.ser");
         //FileOutputStream fileOut = new FileOutputStream(url.getPath());
    	 FileOutputStream fileOut = new FileOutputStream("data.ser");

         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(s);
         out.close();
         fileOut.close();
         if (WeatherApp.CONSOLE_OUTPUT){
         System.out.println("Serialized data is saved in data.ser");
         }
      
      }catch(IOException i)   
      {
    	  if (WeatherApp.CONSOLE_OUTPUT){
          i.printStackTrace();
    	  }
      }
   }
}