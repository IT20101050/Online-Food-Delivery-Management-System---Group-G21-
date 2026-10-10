import model.Delivery;
import service.DeliveryService;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // DeliveryService එකෙන් object එකක් හදාගන්නවා
        DeliveryService service = new DeliveryService();

        System.out.println("--- 1. Testing Save Delivery ---");
        // අලුත් Delivery එකක් හදලා ඒක save කරනවා
        Delivery delivery1 = new Delivery(101, "Pending", 30);
        service.saveDelivery(delivery1);

        // තව එකක් දාමු
        Delivery delivery2 = new Delivery(102, "Preparing", 15);
        service.saveDelivery(delivery2);

        System.out.println("\n--- 2. Testing Read Deliveries ---");
        // Save කරපු ඔක්කොම deliveries කියවලා print කරනවා
        List<Delivery> allDeliveries = service.getAllDeliveries();
        for (Delivery d : allDeliveries) {
            System.out.println("ID: " + d.getDeliveryId() + " | Status: " + d.getStatus() + " | Time: " + d.getEstimatedTime() + " mins");
        }

        System.out.println("\n--- 3. Testing Update Status ---");
        // 101 කියන ID එක තියෙන Delivery එකේ status එක "Delivered" කියලා වෙනස් කරනවා
        service.updateDeliveryStatus(101, "Delivered");

        // වෙනස් කරාට පස්සේ ආයෙත් print කරලා බලනවා
        System.out.println("After Update:");
        List<Delivery> updatedDeliveries = service.getAllDeliveries();
        for (Delivery d : updatedDeliveries) {
            System.out.println("ID: " + d.getDeliveryId() + " | Status: " + d.getStatus());
        }
    }
}