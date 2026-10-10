package service;

import model.Delivery;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveryService {
    // Text files වල නම් (මේවා ව්‍යාපෘතියේ ප්‍රධාන ෆෝල්ඩරයේ ඉබේම හැදෙනවා)
    private static final String DELIVERY_FILE = "deliveries.txt";
    private static final String DRIVER_FILE = "drivers.txt";

    // 1. අලුත් Delivery එකක් Text file එකට Save කිරීම (Write කිරීම)
    public void saveDelivery(Delivery delivery) {
        // FileWriter හි 'true' යෙදීමෙන් පරණ දත්ත මැකෙන්නේ නැතුව අලුත් ඒවා යටින් එකතු වෙනවා
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DELIVERY_FILE, true))) {
            String driverId = (delivery.getAssignedDriver() != null) ?
                    String.valueOf(delivery.getAssignedDriver().getDriverId()) : "Not_Assigned";

            // දත්ත comma (,) වලින් වෙන් කරලා ලියනවා
            bw.write(delivery.getDeliveryId() + "," + delivery.getStatus() + "," + delivery.getEstimatedTime() + "," + driverId);
            bw.newLine();
            System.out.println("Delivery successfully saved!");
        } catch (IOException e) {
            System.out.println("Error saving delivery: " + e.getMessage());
        }
    }

    // 2. Text file එකේ තියෙන Deliveries ඔක්කොම කියවීම (Read කිරීම)
    public List<Delivery> getAllDeliveries() {
        List<Delivery> deliveries = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(DELIVERY_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(","); // කොමා වලින් වෙන් කරලා array එකකට ගන්නවා
                if (parts.length >= 3) {
                    int id = Integer.parseInt(parts[0]);
                    String status = parts[1];
                    int time = Integer.parseInt(parts[2]);

                    Delivery delivery = new Delivery(id, status, time);
                    deliveries.add(delivery);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading deliveries (File might not exist yet): " + e.getMessage());
        }
        return deliveries;
    }

    // 3. Delivery එකක Status එක අලුත් කිරීම (Update කිරීම)
    public void updateDeliveryStatus(int deliveryId, String newStatus) {
        List<Delivery> deliveries = getAllDeliveries(); // මුලින්ම තියෙන ඔක්කොම කියවගන්නවා

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DELIVERY_FILE))) {
            for (Delivery d : deliveries) {
                // අදාළ Delivery ID එක හොයාගෙන ඒකේ status එක වෙනස් කරනවා
                if (d.getDeliveryId() == deliveryId) {
                    d.updateStatus(newStatus);
                }

                String driverId = (d.getAssignedDriver() != null) ?
                        String.valueOf(d.getAssignedDriver().getDriverId()) : "Not_Assigned";

                // වෙනස් කරපු ලිස්ට් එක ආයෙත් මුල ඉඳන් ෆයිල් එකට ලියනවා
                bw.write(d.getDeliveryId() + "," + d.getStatus() + "," + d.getEstimatedTime() + "," + driverId);
                bw.newLine();
            }
            System.out.println("Delivery status updated!");
        } catch (IOException e) {
            System.out.println("Error updating delivery status: " + e.getMessage());
        }
    }
}