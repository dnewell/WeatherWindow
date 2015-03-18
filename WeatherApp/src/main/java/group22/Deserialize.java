import java.io.*;
import java.io.Serializable;

public class Deserialize implements Serializable
{
	private static SavedData s;
	public Deserialize() {	
   {
      s = null;
      try
      {
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
      System.out.println("Deserialized Data...");
      System.out.println("Units: " + s.units);
      System.out.println("Location: " + s.location);
    }
}
	
	public SavedData getData(){
		return s;
	}
}