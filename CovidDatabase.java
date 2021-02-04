// added the necessary import statements in order to program the application.
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.text.NumberFormat;
/**
 * This method connects to the excel file with the covid information.
 *
 * @author (Murtaza Badshah)
 * @version (1.0)
 */
public class CovidDatabase
{
    // instance variables - replace the example below with your own
    private ArrayList <CovidEntry> db;

    // Declaring a Final variable to check if its safe to open the state.
    private static final int SAFE = 5;

    /**
     * Constructor for objects of class CovidDatabase
     */
    public CovidDatabase()
    {
        // initialise Array list instance variables
        db = new ArrayList<CovidEntry>();
    }

    /**
     * This method opens the csv file, takes each of the delimited records
     * and then stores it in the arraylist of objects.
     *
     * @param  filename  This is a string data type (Name of the file)
     */
    public void readCovidData(String filename)
    {
        Scanner inFS = null; 
        FileInputStream fileByteStream = null;

        try{
            // open the File and set delimiters
            fileByteStream = new FileInputStream(filename);
            inFS = new Scanner(fileByteStream);
            inFS.useDelimiter("[,\r\n]+");

            // reads the first line in order to skip it, does not store.
            inFS.nextLine();

            // continue while there is more data to read
            while(inFS.hasNext()) {

                // read data elements from the csv file.
                String state = inFS.next();
                int month = inFS.nextInt();
                int day = inFS.nextInt();
                int dInfections = inFS.nextInt();
                int dDeaths = inFS.nextInt();
                int tInfections = inFS.nextInt();
                int tDeaths = inFS.nextInt();

                // storing the variable in the Covid entry method from the
                // other class
                CovidEntry covid = new CovidEntry(state, month, day, 
                        dInfections, dDeaths, tInfections, tDeaths);

                //Adding the covid object to the arraylist defined above.
                db.add(covid);
            }
            fileByteStream.close();

            // error while reading the file                      
        }catch(IOException error1) {
            System.out.println("Oops! Error related to: " + filename);
        }        
    }

    /*************************ACCESSOR METHODS*************************/
    /**
     * This method returns the total number of entries in the arraylist
     *
     * @return int the total number of entries.
     */
    public int countRecords()
    {
        // returns the number of elements within the ArrayList
        return db.size();
    }

    /**
     * This method returns the total number of deaths in the arraylist
     *
     * @return int the total number of deaths.
     */
    public int getTotalDeaths()
    {
        // initialized a count variable.
        int count = 0;
        // created a for each loop to go through each object of the Arraylist.
        for(CovidEntry covid : db)
        {
            /* adding each month entry of to the count variable by calling
            the getTotalDeaths method.*/
            count += covid.getDailyDeaths();
        }
        // returning the total count after the summation.
        return count;
    }

    /**
     * This method returns the total number of infections in the arraylist
     *
     * @return int the total number of infections.
     */
    public int getTotalInfections()
    {
        // initialized a count variable.
        int count = 0;
        // created a for each loop to go through each object of the Arraylist.
        for(CovidEntry covid : db)
        {
            /* adding each month entry of to the count variable by calling
            the getTotalInfections method.*/
            count += covid.getDailyInfections();
        }
        // returning the total count after the summation.
        return count;
    }

    /**
     * This method returns the total number of deaths in the arraylist based
     * on the specific state and date.
     *
     * @param m Month variable entered by the user.
     * @param d day variable entered by the user.
     * @return int the total number of deaths.
     */
    public int countTotalDeaths(int m, int d)
    {
        // initialized a count variable.
        int count = 0;
        // Created a for each loop to go through each object of the Arraylist.
        for(CovidEntry covid : db)
        {
            /*Adding each month entry of to the count variable by calling
            the getTotalDeaths method.*/
            if(covid.getMonth() == m && covid.getDay() == d)
            {
                count += covid.getDailyDeaths();
            }
        }
        // returning the total count after the summation.
        return count;
    }

    /**
     * This method returns the total number of infection in the arraylist based
     * on the specific state and date.
     *
     * @param m Month variable entered by the user.
     * @param d day variable entered by the user.
     * @return int the total number of infections.
     */
    public int countTotalInfections(int m, int d)
    {
        // initialized a count variable.
        int count = 0;
        // Created a for each loop to go through each object of the Arraylist.
        for(CovidEntry covid : db)
        {
            /*Adding each month entry of to the count variable by calling
            the getTotalInfections method.*/
            if(covid.getMonth() == m && covid.getDay() == d)
            {
                count += covid.getDailyInfections();
            }
        }
        // returning the total count after the summation.
        return count;
    }

    /**
     * This method returns object with highest daily death in the 
     * arraylist based on the specific state specified by the user. 
     *
     * @param String State String variable entered by the user.
     * 
     * @return int Highest daily death count for the state.
     */
    public CovidEntry peakDailyDeaths(String st)
    {
        // initialzied a new object variable to called MaxDaily
        CovidEntry maxDaily = null;

        //created a for loop to run through each object in the ArrayList
        for(CovidEntry covid : db)
        {
            /* created an if statement to check if the State in the csv
             * matches the state entered by the user.*/
            if(covid.getState().equalsIgnoreCase(st))
            {
                // created an IF statement to check if the initialzied object
                // is null then we assign covid object to it.
                if(maxDaily == null)
                {
                    // if null then assign covid object to max daily
                    maxDaily = covid; 
                }
                // IF the maxdaily daily death is less than covid DD then
                // assign that object to maxdaily arraylist.
                if(maxDaily.getDailyDeaths() < covid.getDailyDeaths())
                {
                    maxDaily = covid;
                }
            }
        } 
        // returning the maxdaily object. 
        return maxDaily;
    }

    /**
     * This method returns an array list with objects filtered by the Month
     * and day specified by the user. 
     *
     * @param m Month variable entered by the user.
     * @param d day variable entered by the user.
     * @return <ArrayList> all the records in the specific month and day.
     */
    public ArrayList <CovidEntry> getDailyDeaths(int m, int d)
    {
        // initialzied and defined a local CovidEntry ArrayList
        ArrayList <CovidEntry> records = new ArrayList<CovidEntry>();

        // Created a for loop to iterate through the arraylist with DB entries
        for(CovidEntry covid : db)
        {
            // if month and day matches the M and D entered by user then...
            if(covid.getMonth() == m && covid.getDay() == d)
            {
                // add that record of covid ArrayList to the records arraylist
                records.add(covid);
            }
        }
        // return the records array list after processing all the entries.
        return records;
    }

    /**
     * This method returns object with highest daily death in the 
     * arraylist based on the specific month and day specified by the user. 
     *
     * @param Month month int variable entered by the user.
     * @param day day int variable entered by the user.
     * @return int Highest daily death count for the state.
     */
    public CovidEntry peakDailyDeaths(int m, int d)
    {
        // initialzied a new object variable to called MaxDaily
        CovidEntry maxDaily = null;

        //created a for loop to run through each object in the ArrayList
        for(CovidEntry covid : db)
        {
            /* created an if statement to check if the month and day
             * matches the month and day entered by the user.*/

            if(covid.getMonth() == m && covid.getDay() == d)
            {
                // created an IF statement to check if the initialzied object
                // is null then we assign covid object to it.
                if(maxDaily == null)
                {
                    // if null then assign covid object to max daily
                    maxDaily = covid; 
                }
                // IF the maxdaily daily death is less than covid DD then
                // assign that object to maxdaily arraylist.
                if(maxDaily.getDailyDeaths() < covid.getDailyDeaths())
                {
                    maxDaily = covid;
                }

            }
        } 
        // returning the maxdaily object. 
        return maxDaily;
    }

    /**
     * This method returns objects with highest total death in the 
     * arraylist. 
     *
     * @return <arraylist> Object with the highest count of total deaths.
     */
    public CovidEntry mostTotalDeaths()
    {
        // initialzied a new object variable to called MaxDaily
        CovidEntry maxTotal = null;

        //created a for loop to run through each object in the ArrayList
        for(CovidEntry covid : db)
        {
            /* created an if statement to check if the month and day
             * matches the month and day entered by the user.*/

            // created an IF statement to check if the initialzied object
            // is null then we assign covid object to it.
            if(maxTotal == null)
            {
                // if null then assign covid object to max daily
                maxTotal = covid; 
            }
            // IF the maxdaily daily death is less than covid DD then
            // assign that object to maxdaily arraylist.
            if(maxTotal.getTotalDeaths() < covid.getTotalDeaths())
            {
                maxTotal = covid;
            }

        } 
        // returning the maxdaily object. 
        return maxTotal; 
    }

    /**
     * This method returns arraylist of objects greater than the specified 
     * minimum number of infections on a specified month and day. 
     *
     * @param m month int variable entered by the user.
     * @param d day int variable entered by the user.
     * @param min min value of infections entered by the user.
     * @return <ArrayList> records of objects with Min daily infections.
     */
    public ArrayList <CovidEntry> listMinimumDailyInfections(int m, int d, int min)
    {
        // initialzied and defined a local CovidEntry ArrayList
        ArrayList <CovidEntry> records = new ArrayList<CovidEntry>();

        // Created a for loop to iterate through the arraylist with DB entries
        for(CovidEntry covid : db)
        {
            // if month/day matches the M and D and greater than min value
            // entered by user then...
            if(covid.getMonth() == m && covid.getDay() == d &&
            covid.getDailyInfections() >= min)
            {
                // add that record of covid ArrayList to the records arraylist
                records.add(covid);
            }
        }
        // return the records array list after processing all the entries.
        return records;
    }

    /************************SAFE TO OPEN SECTION**************************/

    /**
     * This method returns arraylist of objects greater than the specified 
     * minimum number of infections on a specified month and day. 
     *
     * @param m month int variable entered by the user.
     * @param d day int variable entered by the user.
     * @param min min value of infections entered by the user.
     * @return <ArrayList> records of objects with Min daily infections.
     */
    public ArrayList <CovidEntry> safeToOpen (String st)
    {
        /* initialzied and defined a local CovidEntry ArrayList to store
        the state objects*/
        ArrayList <CovidEntry> records = new ArrayList<CovidEntry>();

        /*initialzied and defined a local CovidEntry ArrayList to store
        the objects that are decreasing in infections rates. */
        ArrayList <CovidEntry> safeToOpen = new ArrayList<CovidEntry>();

        // added a counter variable to check against the records.
        int counter = 1;

        // created a for loop to go from 0 to end of the arraylist

        for(int i = 0; i < db.size(); i++)
        {
            // condition to check if the state matches the St value
            if(db.get(i).getState().equalsIgnoreCase(st))
            {
                //if true then we add it to the local records ArrayList
                records.add(db.get(i));
            }
        }

        // created another for loop to go from 0 to the size of the records
        // arraylist
        for(int i = 0; i < records.size(); i++)
        {
            // If condition to check if the second value is less 
            // than the first in the list
            if(records.get(i+1).getDailyInfections() < records.get(i).getDailyInfections())
            {
                // incrementing the counter value if the condition is true.
                counter++;
                // adding the record to the new local variable created by me.
                safeToOpen.add(records.get(i));
                // created an if to check if the counter is equal to static 
                // SAFE variable. 
                if(counter == SAFE)
                {
                    // if the counter is equal we will add the final
                    // object to the safe to open array list
                    safeToOpen.add(records.get(i+1));
                    // then run the break statement to get out of the loop.
                    break;
                }
            }
            else 
            {
                // if the condition fails then we reset the counter to 1
                counter = 1;
                // we then clear all the fields in the Array list.
                safeToOpen.clear();
            }
        }

        //created an if to check if the safe to open arraylist if empty.
        if(safeToOpen.size() == 0)
        {
            //if true then we just return null.
            return null;
        }
        else 
        {
            // otherwise we return the 5 element arraylist.
            return safeToOpen;
        }
    }

    /***********************END OF SAFE TO OPEN SECTION*********************/

    /****************TOP TEN LIST SECTION***********************************/
    /**
     * This method requires sorting an arraylist of covid entry objects in
     * descending order by number of daily deaths.
     *
     * @param m month int variable entered by the user.
     * @param d day int variable entered by the user.
     * 
     * @return <ArrayList> records of objects with top ten deaths.
     */
    public ArrayList <CovidEntry> topTenDeaths (int m, int d)
    {
        /* initialzied and defined a local CovidEntry ArrayList to store
        the results of the getDailyDeaths method*/
        ArrayList <CovidEntry> records = getDailyDeaths(m,d);
        //Used the collections code to sort the list.
        Collections.sort(records);
        // created a new local arraylist to store the top 10 objects
        ArrayList <CovidEntry> topTen = new ArrayList <CovidEntry>();
        //used a for loop to iterate and store the first 10 from the list
        if(records.size() > 0){
            for(int i = 0; i < 10; i++)
            {
                //adding each record object into the new local arraylist.
                topTen.add(records.get(i));
            }
        }
        // return the newly created arraylist of top 10 objects.
        return topTen;

    }
}