import java.util.*;

class AutocompleteSystem {

    HashMap<String, Integer> queryFreq = new HashMap<>();

    public void addQuery(String query) {

        queryFreq.put(query,
                queryFreq.getOrDefault(query, 0) + 1);
    }

    public List<String> search(String prefix) {

        PriorityQueue<String> pq =
                new PriorityQueue<>((a,b) ->
                        queryFreq.get(b) - queryFreq.get(a));

        for (String q : queryFreq.keySet()) {

            if (q.startsWith(prefix))
                pq.add(q);
        }

        List<String> result = new ArrayList<>();

        for (int i = 0; i < 10 && !pq.isEmpty(); i++)
            result.add(pq.poll());

        return result;
    }
}