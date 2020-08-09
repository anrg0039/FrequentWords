package in.anuraggupta;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.CharConversionException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.*; 
import java.lang.*; 
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.portable.InputStream;

public class AddServelet extends HttpServlet
{
public void doPost(HttpServletRequest req, HttpServletResponse res ) throws IOException,  ServletException
{
	int i;
	if(req.getParameter("num1")=="")
		i=0;
	else
	i= Integer.parseInt(req.getParameter("num1"));
	

    res.setContentType("text/html");

   
    String root = getInitParameter("root");

  
    
    ServletOutputStream out = res.getOutputStream();
    out. println("<!doctype html>");
    out.println("<head><link rel = 'stylesheet' href = 'Style.css'> <title>Terribly Tiny Tales</title></head>");
    out.println("<body><h1 id='title'>Data of the File:</h1>");
    
    
    URL url = new URL("https://terriblytinytales.com/test.txt");
    
    BufferedInputStream st = new BufferedInputStream(url.openStream());
    
    
    BufferedReader in = null;
    
    	
        in = new BufferedReader((new InputStreamReader(st)));
        
        Scanner sc = new Scanner(in);
   //     out.println("hello");
        int ch;
        String word;
        
        HashMap<String,Integer> hm = new HashMap<String,Integer>();
        
        while (sc.hasNext()) {
        	
           
        	
        	try{
        	String s = sc.next();
     	
			
      	out.println(s);
     	
if(hm.containsKey(s))
 		{
			hm.put(s,hm.get(s)+1);
 		}else
 		{
			hm.put(s,1);
 		}
        	
        	
        }
        	
        
     	catch(CharConversionException e){}
        }
        int n = hm.size();
    out.println("<br><br><p id='title'>Output");
    if(i>n)
    {
    	 out.println(": Oh! File contains only "+n+" words. Showing all the words with frequency");	
    }else
    {
    out.println(": Showing Top "+i+" Most frequent Words out of "+hm.size());
    }
    
   
    List<Map.Entry<String, Integer> > list = 
            new LinkedList<Map.Entry<String, Integer> >(hm.entrySet()); 

     // Sort the list 
     Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() { 
         public int compare(Map.Entry<String, Integer> o1,  
                            Map.Entry<String, Integer> o2) 
         { 
             return (o2.getValue()).compareTo(o1.getValue()); 
         } 
     }); 
       
     // put data from sorted list to hashmap  
     HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>(); 
     for (Map.Entry<String, Integer> aa : list) { 
         temp.put(aa.getKey(), aa.getValue()); 
     } 
     
     int k = 0;
     out.println("<div style ='overflow-x:auto;'>");
     out.println("<table style = 'width:100%'><tr><th id ='output'>No.</th><th id ='output'>Frequency</th><th id ='output'>Word</th></tr>");
        for(Map.Entry entry : temp.entrySet()){
        	if(k>=i)
        		break; 
        	k++;
        	out.println("<tr><td id ='output'>");
        	
        	out.println(k+".");
        	out.println("</td><td id ='output'>");
        	
        	out.println(entry.getValue()+"");
        	out.println("</td><td id ='output'>");
        	out.println(entry.getKey()+"");
        	out.println("</td></tr>");
        	
        	 
        }
        out.println("</table></div>");
        
        
    out.println("</body></html>");
}
}
