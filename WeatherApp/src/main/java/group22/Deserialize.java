import java.io.*;
import java.net.URL;

public class Deserialize implements Serializable
{
	private static SavedData s;
	public Deserialize() {	
   {
      s = null;
      try
      {
         //InputStream fileIn = getClass().getResourceAsStream("data.ser");
    	 FileInputStream fileIn = new FileInputStream("data.ser");
    	 ObjectInputStream in = new ObjectInputStream(fileIn);
         s = (SavedData) in.readObject();
         in.close();
         fileIn.close();
      }catch(IOException i)
      {
         i.printStackTrace();
         return;
      }catch(ClassNotFoundException c)
      {
         System.out.println("SavedData class not found");
         c.printStackTrace();
         return;
      }
      
  		String units_text = "Metric";
		if(s.units==1)
			units_text = "Imperial";

		System.out.println("Deserialized Data...");
		System.out.println("Units: " + units_text);
		System.out.println("Location: " + s.location);
    }
}
	
	public SavedData getData(){
		return s;
	}
}