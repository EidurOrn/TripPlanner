package is.hi.tripPlanner.tripPlannerPackage;

import is.hi.tripPlanner.dayTourPackage.Trip;
import is.hi.tripPlanner.dayTourPackage.SearchModel;
import is.hi.tripPlanner.dayTourPackage.mockObjects.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Valdi on 28.3.2017.
 * testing
 */
public class MetaSearchTest {

    /**
     * MetaSearch object being tested
     */
    MetaSearch searchTest;
    /**
     * SearchParameters; What goes into getDayTourInfo(), the search object from the DayTour program
     */
    SearchModel searchObject;

    /**
     * THe search results
     */
    Trip[] searchResults;
    /**
     * the mockResults
     */
    Trip[] mockResults;

    @Before
    public void setUp() throws Exception {
        searchTest = new MetaSearch(new ThreeDayTourMock());

        // create mock data for the mock object
        Trip t1 = new Trip();
        t1.setTripdId(1);
        t1.setDateBegin(new Date(2017,3,25,14,30));
        t1.setDateEnd(new Date(2017,3,25,16,0));
        t1.setTripName("Tunglferðin leiðinlega");
        t1.setDescription("Þetta er hundleiðinleg ferð, þú ættir ekki að fara í hana! En þetta er samt ævintýri!");
        t1.setLocation("Tunglinu");
        t1.setPrice(99999);
        t1.setMinPeople(1);
        t1.setMaxPeople(1);

        Trip t2 = new Trip();
        t2.setTripdId(1);
        t2.setDateBegin(new Date(2017,3,28,14,30));
        t2.setDateEnd(new Date(2017,3,25,16,0));
        t2.setTripName("Hellaskoðun Akureyrar");
        t2.setDescription("Hella skoðun á hinum fallegustu hellum í kringum Akureyri. Svakalega skemmtileg ævintýraferð");
        t2.setLocation("Akureyri");
        t2.setPrice(5400);
        t2.setMinPeople(5);
        t2.setMaxPeople(20);

        Trip t3 = new Trip();
        t3.setTripdId(1);
        t3.setDateBegin(new Date(2018,5,8,8,30));
        t3.setDateEnd(new Date(2018,5,25,12,0));
        t3.setTripName("Vestmanneyjar safnskynning");
        t3.setDescription("Menningaferð um sögu vestmanneyja");
        t3.setLocation("Vestmanneyjar");
        t3.setPrice(4200);
        t3.setMinPeople(8);
        t3.setMaxPeople(16);

        mockResults = new Trip[] {t1, t2,t3};

        // create search parameters object that searches for day tour t1
        searchObject = new SearchModel(t1.getTripName(), t1.getDateBegin(), t1.getDateEnd(), t1.getLocation(),t1.getPrice());

    }

    @After
    public void tearDown() throws Exception {
        mockResults = null;
        searchTest = null;
        searchObject = null;
    }

    /**
     * test if search method in MetaSearchClass returns what has been searched for
     */
    @Test
    public void testGetDayTourInfo() {

        searchResults = searchTest.getDayTourInfo(searchObject);

        compareTwoTripArrays(searchResults,mockResults);
    }

    /**
     * tests
     */
    @Test
    // Test whether a weird search is handled correctly.
    public void testGetDayTourInfoNoResults() {
        searchTest = new MetaSearch(new NoDayTourMock());
        SearchModel bullSearch = new SearchModel("x_x", new Date(2015,3,28,14,30), new Date(2015,3,28,15,30), "Zimbabve",1200);
        assertEquals(searchTest.getDayTourInfo(bullSearch), new Trip[0]);
    }

    @Test
    // Test whether it works to search with a SearchModel that has null in some of it's parameters (this is allowed, gets given default values).
    public void testGetDayTourInfoWithSomeNullParam() {
        searchObject.setLocation(null);
        searchObject.setDateEnd(null);
        searchResults = searchTest.getDayTourInfo(searchObject);

        compareTwoTripArrays(searchResults,mockResults);
    }

    @Test
    // Test whether it works to search with a SearchModel that has null all of it's parameters (this is allowed, gets given default values).
    public void testGetDayTourInfoWithAllNullParam() {
        searchObject.setTripName(null);
        searchObject.setLocation(null);
        searchObject.setDateBegin(null);
        searchObject.setDateEnd(null);
        searchObject.setPrice(0); // int can't be null thus it has to be given a different value.

        searchResults = searchTest.getDayTourInfo(searchObject);

        compareTwoTripArrays(searchResults,mockResults);
    }

    /**
     * tests if badly formed search entries (e.g. price as a negative number) return what they should
     */
    @Test
    // Test whether price is < 0 is handled correctly.
    public void testBadSearchEntriesForPrice(){
        searchObject.setPrice(-10);
        assertEquals(searchTest.getDayTourInfo(searchObject), new Trip[0]);
    }
    @Test
    // Test reversed dates (endDate happens before beginDate) and whether it's handled correctly.
    public void testBadSearchEntriesForReverseDate(){
        Date tmp = searchObject.getDateBegin();
        searchObject.setDateBegin(searchObject.getDateEnd());
        searchObject.setDateEnd(tmp);

        Trip[] t = searchTest.getDayTourInfo(searchObject);

        assertEquals(searchTest.getDayTourInfo(searchObject), new Trip[0]);
    }

    // Checks whether all of the paramaters from the trips are the same in both arrays.
    private void compareTwoTripArrays(Trip[] s, Trip[] m){
        for(int i = 0; i < s.length; i++){
            assertEquals(s[i].getTripdId(),m[i].getTripdId());
            assertEquals(s[i].getTripName(),m[i].getTripName());
            assertEquals(s[i].getDateBegin(),m[i].getDateBegin());
            assertEquals(s[i].getDateEnd(),m[i].getDateEnd());
            assertEquals(s[i].getLocation(),m[i].getLocation());
            assertEquals(s[i].getMinPeople(),m[i].getMinPeople());
            assertEquals(s[i].getMaxPeople(),m[i].getMaxPeople());
            assertEquals(s[i].getPrice(),m[i].getPrice());
        }
    }
}