package is.hi.tripPlanner.tripPlannerPackage.storage;

public class Purchaser {
    private String name;
    private String email;
    private String phone;
    private String ssn;

    public Purchaser(String name, String email, String phone, String ssn) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.ssn = ssn;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSsn() {
        return ssn;
    }
}
