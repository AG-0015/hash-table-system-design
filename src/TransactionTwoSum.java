import java.util.*;

class Transaction {

    int id;
    int amount;

    Transaction(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }
}

class TwoSumDetector {

    public void findTwoSum(List<Transaction> list, int target) {

        HashMap<Integer, Transaction> map = new HashMap<>();

        for (Transaction t : list) {

            int complement = target - t.amount;

            if (map.containsKey(complement)) {

                System.out.println(
                        map.get(complement).id + " + " + t.id);
            }

            map.put(t.amount, t);
        }
    }
}