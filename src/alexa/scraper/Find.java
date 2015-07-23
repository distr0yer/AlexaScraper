package alexa.scraper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.io.*;

 
public class Find 
{
    int size;
    public void data1(){
        data();
    }
    private void data()
    {
        //int i = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("Are you searching multiple sites (y/n)");
        String ans = in.nextLine();
        String data;
        if (ans.equalsIgnoreCase("no") || ans.equalsIgnoreCase("n"))
        {
            size = 1;
            System.out.println("Enter Site: ");
            data = in.nextLine();
            String [] thisArray = new String[1];
            thisArray[0] = data;
            int i = 0;
            find(thisArray, i);
            
        }
        if (ans.equalsIgnoreCase("yes") || ans.equalsIgnoreCase("y"))
        {
            String [] thisArray ;
            System.out.println("Please enter each website. Separate using commas: ");
            data = in.nextLine();
            boolean comma = false;
            boolean spaces = false;
            try {
                if (data.indexOf(",") != -1)
                {
                    thisArray = data.split("\\s*,\\s*");
                    size = thisArray.length;
                    int i = 0;
                    find(thisArray, i);
                    comma = true;
                }
                if (data.indexOf(" ") != -1 && comma == false)
                {
                    thisArray = data.split(" ");
                    size = thisArray.length;
                    int i = 0;
                    find(thisArray, i);
                    spaces = true;
                }
                if (spaces == false && comma == false)
                {
                    System.out.println("\nCheck your input and  try again!\n"); 
                    data();
                }
            }
            catch (Exception e)
                {
                    System.out.println(e);
                }
        }
    }
    private void find(String thisArray[], int i){
            String partAddress = "http://alexa.com/siteinfo/";
            String address;
            while( i < size)
            {
                boolean USA = false;
                boolean yesterday = false;
                address = partAddress.concat(thisArray[i]);
                
                try  
                {
                        URL u = new URL(address);
                        BufferedReader reader = new BufferedReader(new InputStreamReader(u.openStream()));
                        String in;
                        while((in = reader.readLine()) != null)
                       {
                           if(in.contains("b2383bed-44cb-4edb-b1c5-dda06e67bfeb")&& in.contains("Global"))
                            {   
                                   int nuetral = in.indexOf(thisArray[i]) + 1;
                                   int start = in.indexOf('>', nuetral) + 1;
                                   int end = in.indexOf('<', start);
                                   String Global = in.substring(start,end);
                                   if(i > 0)
                                       writeToFile("\n\n");
                                   writeToFile(thisArray[i]);
                                   writeToFile("\nGlobal Ranking: " + Global);
                            }
                           if(in.contains("/topsites/countries/US") && in.contains("span class=\'\'"))
                            {
                                   int  nuetral = in.lastIndexOf("span class=\'\'");
                                   int start = in.indexOf('>', nuetral) + 1;
                                   int end = in.indexOf('<', start);
                                   String US = in.substring(start,end);     
                                   writeToFile("\nUS Ranking: " + US);
                                   USA = true;
                            }
                           if(in.contains("<th>Yesterday</th>"))
                           {
                                in = reader.readLine();
                                int  nuetral = in.lastIndexOf("avg ");
                                int start = in.indexOf('>', nuetral) + 1;
                                int end = in.indexOf('<', start);
                                String yes = in.substring(start,end);
                                System.out.println();      
                                writeToFile("\nYesterday: " + yes);
                                yesterday = true;
                                if (yesterday == true)
                                  break;
                           }
                       }
                        if(USA == false)
                           {
                               writeToFile("\nNo US data");
                           }
                        if(yesterday == false)
                           {
                               writeToFile("\nNo traffic Rank for yesterday...");
                           }
                        i++;
                        System.out.println("Information has been printed to Output.txt...");
                        System.out.println("Would you like to check domain tools for the same sites? ");
                        Scanner keyboard = new Scanner(System.in);
                        String ans = keyboard.nextLine();
                        if (ans.equalsIgnoreCase("no") || ans.equalsIgnoreCase("n"))
                        {
                            break;            
                        }
                        if (ans.equalsIgnoreCase("yes") || ans.equalsIgnoreCase("y"))
                        {
                            DomainTools data = new DomainTools();
                            data.start(thisArray, size);
                        }
                }        
                    catch(MalformedURLException e)
                    {
                        System.out.println(e);
                    }
                    catch (IOException e)
                    {
                        System.out.println(e);
                    }
                catch (Exception e)
                {
                    System.out.println(e);
                }
            }
                
    }
    
    private void writeToFile(String string)
    {
        try(FileWriter out = new FileWriter("Output.txt", true))
        {
            out.write(string);
            out.close();                
        }
        catch(FileNotFoundException e)
        {
            System.out.println(e);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        catch (Exception e)
                {
                    System.out.println(e);
                }
    }
}

