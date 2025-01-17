import java.text.NumberFormat;
/**
 * We are building a Covid dahsboard using Java GUI. This class contains
 * the code to read the covid csv file and show results of the output from
 * based on certain conditions.
 *
 * @author (Murtaza Badshah)
 * @version (1.0)
 */
public class CovidEntry implements Comparable 
{
    // instance variables defined below.
    private String state; // defined the state variable
    private int month; //defined the month variable
    private int day; //defined the day variable
    private int dailyDeaths; //defined the daily death count variable
    private int dailyInfections; // defined the daily infections
    private int totalDeaths; // defined the total deaths.
    private int totalInfections; // defined the total infections

    /**
     * Constructor for objects of class CovidEntry
     * @param  st  state
     * @param  m  month
     * @param  d  day
     * @param  di  daily infections
     * @param  dd  daily deaths
     * @param  ti  total infections
     * @param  td  total deaths
     * 
     */
    public CovidEntry(String st, int m, int d, int di, int dd, int ti, int td)
    {
        // initialise instance variables
        state = st; // defined state with st input paramter
        month = m; // defined month with m input paramter
        day = d; // defined day with s input paramter
        dailyInfections = di; // defined daily infections with di input paramter
        dailyDeaths = dd; // defined daily deaths with dd input paramter
        totalDeaths = td; // defined total deaths with td input paramter
        totalInfections = ti; // defined total infections with ti input paramter
    }

    /**
     * gets the month.
     * 
     * @return    the month variable
     */
    public int getMonth()
    {
        // return the month variable
        return month;
    }
    
    /**
     * gets the day.
     * 
     * @return    the day variable
     */
    public int getDay()
    {
        // return the day variable
        return day;
    }
    
    /**
     * gets the state string.
     * 
     * @return    the state value
     */
    public String getState()
    {
        // return the state variable
        return state;
    }
    
    /**
     * gets the daily infections numbers of a particular state, month and day.
     * 
     * @return    the daily infections total
     */
    public int getDailyInfections()
    {
        // return the daily infections variable
        return dailyInfections;
    }
    
    /**
     * gets the daily death numbers of a particular state, month and day.
     * 
     * @return    the daily death totals
     */
    public int getDailyDeaths()
    {
        // return the daily deaths variable
        return dailyDeaths;
    }
    
    /**
     * gets the total infection numbers of a particular state, month and day.
     * 
     * @return    the total infections
     */
    public int getTotalInfections()
    {
        // return the total infections variable
        return totalInfections;
    }
    
    /**
     * gets the total death numbers of a particular state, month and day.
     * 
     * @return    the total deaths
     */
    public int getTotalDeaths()
    {
        // return the total deaths variable
        return totalDeaths;
    }
    
    /**
     * Returns the results of the query in a string format.
     * 
     * @return    results in a string format.
     */
    public String toString()
    {
        NumberFormat myFormat = NumberFormat.getInstance();
        // return the information in string format
        return state + " " + month + "/" + day + " " + myFormat.format(dailyInfections) +
        " infections, " + myFormat.format(dailyDeaths) + " deaths \n";
    }
    
    /**********************END OF PHASE 1*****************************/
    
    /********************* ADDITIONAL CHANGES ****************************/
    
    public int compareTo(Object other){
        CovidEntry c = (CovidEntry) other;
        return c.dailyDeaths - dailyDeaths;
     }
    
}
