import java.util.*;

class FlashSaleInventory {

    private HashMap<String, Integer> stock = new HashMap<>();
    private HashMap<String, Queue<Integer>> waitingList = new HashMap<>();

    public void addProduct(String productId, int quantity) {
        stock.put(productId, quantity);
        waitingList.put(productId, new LinkedList<>());
    }

    public synchronized String purchaseItem(String productId, int userId) {

        int currentStock = stock.getOrDefault(productId, 0);

        if (currentStock > 0) {
            stock.put(productId, currentStock - 1);
            return "Success, remaining: " + (currentStock - 1);
        }

        waitingList.get(productId).add(userId);
        return "Added to waiting list. Position: "
                + waitingList.get(productId).size();
    }

    public int checkStock(String productId) {
        return stock.getOrDefault(productId, 0);
    }

    public static void main(String[] args) {

        FlashSaleInventory sale = new FlashSaleInventory();

        sale.addProduct("IPHONE15", 2);

        System.out.println(sale.purchaseItem("IPHONE15", 1));
        System.out.println(sale.purchaseItem("IPHONE15", 2));
        System.out.println(sale.purchaseItem("IPHONE15", 3));
    }
}