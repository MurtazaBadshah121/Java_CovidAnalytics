public class CovidDatabaseTest {
    
    public static void main () {
        System.out.println ("Testing starts");
        System.out.println("");
        CovidDatabase db = new CovidDatabase() ;
        db.readCovidData("covid_data.csv");

        // check number of records, total infections, and total deaths
        assert db.countRecords() == 10346 : "database should have 10,346"; 
        assert db.getTotalDeaths() == 196696 : "Total deaths should be: 196,696"; 
        assert db.getTotalInfections() ==  7032090 : "infections should be: 7,032,090"; 
        
        


        // check peak daily deaths for 5/5
        CovidEntry mostDeaths = db.peakDailyDeaths(5, 5);
        assert mostDeaths.getState().equals("PA") : "State with most deaths for 5/5 is PA";
        assert mostDeaths.getDailyDeaths() ==  554 : "Deaths for 5/5 is PA: 554";
        
        // test other methods
        assert db.countTotalDeaths(5,5) == 2444 : "Total deaths on 5/5 should be 2444";
        assert db.countTotalInfections(5,5) == 22215 : "Total infections on 5/5 should be 22215";
        
        // top ten deaths test
        System.out.println("Top ten deaths for 5/5");
        System.out.println("");
        System.out.println(db.topTenDeaths(5,5));
        System.out.println("");
        
        // safe to open method test.
        System.out.println("Safe to open for MI");
        System.out.println("");
        System.out.println(db.safeToOpen("MI"));
        System.out.println("");
         
        // Testing the list of min daily infections
        System.out.println("List minimum daily infections");
        System.out.println("");
        System.out.println(db.listMinimumDailyInfections(6,12,1000));
        
        
        
    
        
        
        
        
        
        
       
        System.out.println ("Testing ends");
    }
}
