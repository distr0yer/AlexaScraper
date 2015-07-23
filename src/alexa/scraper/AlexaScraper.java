package alexa.scraper;

import java.io.FileNotFoundException;

/**
 *
 * @author Kelly Santiago
 */
public class AlexaScraper {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException 
    {      
        System.out.println("This program searches alexa.com for specific \ninformation regarding a website.\n");
        Find n = new Find();
        n.data1();
    }
}
