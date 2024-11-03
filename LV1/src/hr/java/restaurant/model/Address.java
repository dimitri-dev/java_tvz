package hr.java.restaurant.model;

public class Address {
    private String street;
    private String houseNumber;
    private String city;
    private String postalCode;

    public Address(String street, String houseNumber, String city, String postalCode) {
        setStreet(street);
        setHouseNumber(houseNumber);
        setCity(city);
        setPostalCode(postalCode);
    }

    public void print() throws Exception {
        print(0);
    }

    public void print(int tabCount) throws Exception {
        print(tabCount, true);
    }

    public void print(int tabCount, boolean newLine) throws Exception {
        if (newLine) {
            System.out.println();
        }

        if (tabCount < 0) {
            throw new Exception("sta ne valja sa tobom");
        }

        System.out.println(("\t".repeat(tabCount)) + "Street: " + getStreet());
        System.out.println(("\t".repeat(tabCount)) + "House Number: " + getHouseNumber());
        System.out.println(("\t".repeat(tabCount)) + "City: " + getCity());
        System.out.println(("\t".repeat(tabCount)) + "Postal Code: " + getPostalCode());
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
