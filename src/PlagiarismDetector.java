import java.util.*;

class PlagiarismDetector {

    private HashMap<String, Set<String>> ngramIndex = new HashMap<>();

    public void addDocument(String docId, String text) {

        String[] words = text.split(" ");

        for (int i = 0; i < words.length - 4; i++) {

            String gram = words[i] + " " + words[i+1] + " " +
                    words[i+2] + " " + words[i+3] + " " + words[i+4];

            ngramIndex.putIfAbsent(gram, new HashSet<>());
            ngramIndex.get(gram).add(docId);
        }
    }

    public void checkDocument(String docId, String text) {

        String[] words = text.split(" ");
        HashMap<String, Integer> similarity = new HashMap<>();

        for (int i = 0; i < words.length - 4; i++) {

            String gram = words[i] + " " + words[i+1] + " " +
                    words[i+2] + " " + words[i+3] + " " + words[i+4];

            if (ngramIndex.containsKey(gram)) {

                for (String d : ngramIndex.get(gram)) {

                    similarity.put(d,
                            similarity.getOrDefault(d, 0) + 1);
                }
            }
        }

        System.out.println(similarity);
    }
}