import java.util.*;

class MultiLevelCache {

    LinkedHashMap<String, String> L1 =
            new LinkedHashMap<>(10000, 0.75f, true);

    HashMap<String, String> L2 = new HashMap<>();

    HashMap<String, Integer> accessCount = new HashMap<>();

    public String getVideo(String id) {

        if (L1.containsKey(id)) {
            return "L1 HIT";
        }

        if (L2.containsKey(id)) {

            accessCount.put(id,
                    accessCount.getOrDefault(id, 0) + 1);

            if (accessCount.get(id) > 5) {
                L1.put(id, L2.get(id));
            }

            return "L2 HIT";
        }

        return "L3 DATABASE HIT";
    }
}