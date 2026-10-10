package model;

public class DeliveryPerson {
    private int driverId;
    private String name;
    private String phone;
    private boolean available;

    public DeliveryPerson(int driverId, String name, String phone, boolean available) {
        this.driverId = driverId;
        this.name = name;
        this.phone = phone;
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // Getters and Setters
    public int getDriverId() { return driverId; }
    public void setDriverId(int driverId) { this.driverId = driverId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}