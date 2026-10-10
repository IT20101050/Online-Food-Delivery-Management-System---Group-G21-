package model;

public class Delivery {
    private int deliveryId;
    private String status;
    private int estimatedMinutes;
    private DeliveryPerson assignedDriver; // Association with DeliveryPerson

    public Delivery(int deliveryId, String status, int estimatedMinutes) {
        this.deliveryId = deliveryId;
        this.status = status;
        this.estimatedMinutes = estimatedMinutes;
    }

    public void assignDriver(DeliveryPerson driver) {
        this.assignedDriver = driver;
        if(driver != null) {
            driver.setAvailable(false); // Driver is no longer available
        }
    }

    public void updateStatus(String status) {
        this.status = status;
    }

    public int getEstimatedTime() {
        return estimatedMinutes;
    }

    // Getters and Setters
    public int getDeliveryId() { return deliveryId; }
    public void setDeliveryId(int deliveryId) { this.deliveryId = deliveryId; }
    public String getStatus() { return status; }
    public void setEstimatedMinutes(int estimatedMinutes) { this.estimatedMinutes = estimatedMinutes; }
    public DeliveryPerson getAssignedDriver() { return assignedDriver; }
}