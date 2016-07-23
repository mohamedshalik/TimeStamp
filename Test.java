import java.io.*;
import java.util.*;

public class Test
{

public static void main(String[] arg){
 int timeDifHours=0,timeDifMinutes=0,timeDifSeconds=0;
 Scanner s=new Scanner(System.in);
String test="Start";
       while (test=="Start") {

           timeDifSeconds++;
           if (timeDifSeconds>60){
               timeDifMinutes++;
               timeDifSeconds=0;
           }
           else if(timeDifMinutes>60){
               timeDifHours++;
               timeDifMinutes=0;
           }

           System.out.println("You have taken " + timeDifHours + " hour " + timeDifMinutes + " mins " + timeDifSeconds + "secs");
		   test=s.nextLine();
       }
	   
	}
}