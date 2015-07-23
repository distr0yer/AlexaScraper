package alexa.scraper;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class DomainTools {
    
    //updates needed for this module.
    public void start(String [] anArray, int size){
        int i = 0;
        //n
        //System.out.println(url.getProtocol());
        try
        { 
            while (i < size)
            {
                String partURL = "http://whois.domaintools.com/";
                URL url = new URL(partURL + anArray[i]);
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                String in;
                
                System.out.println(url.getProtocol());
                while((in = reader.readLine()) != null)
                {
                    System.out.println(in);
                   /**if(in.contains("b2383bed-44cb-4edb-b1c5-dda06e67bfeb")&& in.contains("Global"))
                   {   
                           int nuetral = in.indexOf(thisArray[i]) + 1;
                           int start = in.indexOf('>', nuetral) + 1;
                           int end = in.indexOf('<', start);
                           String Global = in.substring(start,end);
                           if(i > 0)
                               writeToFile("\n\n");
                           writeToFile(thisArray[i]);
                           writeToFile("\nGlobal Ranking: " + Global);
                   }*/
                }
            }
            i++;  
        }
        
        catch(MalformedURLException e){
            System.out.println(e);
        }
        catch (IOException e){
            System.out.println(e);
        }
        
        
    }
    
}
