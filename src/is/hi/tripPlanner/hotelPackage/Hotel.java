package is.hi.tripPlanner.hotelPackage;
// TODO DELETE the Hotel class when we're certain it's no longer needed at all. (It's replaced by HotelRoom).
/* ================================================================================ */
/*                                                                                  */
/*                                                                                  */
/*                                                                                  */
/*                                   OBSOLETE                                       */
/*                                                                                  */
/*                                                                                  */
/*                                                                                  */
/* ================================================================================ */
public class Hotel {
    private int hotelId;
    private String language;
    private float quality;
    private float currency;
    private int price;
    private float popularity;
    private String location;

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public float getQuality() {
        return quality;
    }

    public void setQuality(float quality) {
        this.quality = quality;
    }

    public float getCurrency() {
        return currency;
    }

    public void setCurrency(float currency) {
        this.currency = currency;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
