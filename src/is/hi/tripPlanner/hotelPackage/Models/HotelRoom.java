/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.tripPlanner.hotelPackage.Models;

/**
 *
 * @author drifasoley
 */
public class HotelRoom {
    private int id;
    private String hotelName;
    private int price;
    private String fromAvailability;
    private String toAvailability;
    private String type;
    private String theme;
    private int quality;
    private String location;
    
    public HotelRoom(int id, String hotelName, int price, String fromAvailability, String toAvailability, String type, String theme, int quality, String location) {
        this.setId(id);
        this.setHotelName(hotelName);
        this.setPrice(price);
        this.setFromAvailability(fromAvailability);
        this.setToAvailability(toAvailability);
        this.setType(type);
        this.setTheme(theme);
        this.setQuality(quality);
        this.setLocation(location);
    }

    public HotelRoom() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void dump() {
        System.out.println("id: " + this.getId());
        System.out.println("hotelName: " + this.getHotelName());
        System.out.println("price: " + this.getPrice());
        System.out.println("availability from: " + this.getFromAvailability());
        System.out.println("availability to: " + this.getToAvailability());
        System.out.println("type: " + this.getType());
        System.out.println("theme: " + this.getTheme());
        System.out.println("quality: " + this.getQuality());
        System.out.println("location: " + this.getLocation());
        
    }
    public int getId() {
        return id;
    }
    public String getHotelName() {
        return hotelName;
    }
    public int getPrice() {
        return price;
    }
    public String getFromAvailability() {
        return fromAvailability;
    }
    public String getToAvailability() {
        return toAvailability;
    }
    public String getType() {
        return type;
    }
    public String getTheme() {
        return theme;
    }
    public int getQuality() {
        return quality;
    }
    public String getLocation() {
        return location;
    }
    
    public boolean matchesQuery(HotelRoom query) {
        boolean hotelMatches = this.getHotelName().contains(query.getHotelName());
        boolean priceMatches = (this.getPrice() == query.getPrice() || query.getPrice() == -1);
        boolean locationMatches = this.getLocation().contains(query.getLocation());
        boolean typeMatches = this.getType().contains(query.getType());
        boolean themeMatches = this.getTheme().contains(query.getTheme());
        boolean qualityMatches = this.getQuality() == query.getQuality() || query.getQuality() == -1;
        boolean fromToMatches = this.getFromAvailability().contains(query.getFromAvailability()) && this.getToAvailability().contains(query.getToAvailability());

        return hotelMatches && priceMatches && locationMatches && typeMatches && themeMatches && qualityMatches && fromToMatches;
              
    }

    // Modifications by T group.
    @Override
    public boolean equals(Object object) {
        if (object == null || object.getClass() != getClass()) {
            return false;
        } else {
            HotelRoom hR = (HotelRoom) object;
            return this.getId() == hR.getId();
        }
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setFromAvailability(String fromAvailability) {
        this.fromAvailability = fromAvailability;
    }

    public void setToAvailability(String toAvailability) {
        this.toAvailability = toAvailability;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}


