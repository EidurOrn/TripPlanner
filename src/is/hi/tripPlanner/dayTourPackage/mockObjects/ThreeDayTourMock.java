package is.hi.tripPlanner.dayTourPackage.mockObjects;

import is.hi.tripPlanner.dayTourPackage.SearchModel;
import is.hi.tripPlanner.dayTourPackage.Trip;

import java.util.Date;
public class ThreeDayTourMock implements DayTourFetching {

    public Trip[] findResults(SearchModel s){
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

        return new Trip[]{t1, t2, t3};
    }
}
