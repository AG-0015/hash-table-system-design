import java.util.*;

class DNSEntry {

    String ip;
    long expiryTime;

    DNSEntry(String ip, int ttlSeconds) {
        this.ip = ip;
        this.expiryTime = System.currentTimeMillis() + ttlSeconds * 1000;
    }
}

class DNSCache {

    private HashMap<String, DNSEntry> cache = new HashMap<>();
    int hits = 0;
    int misses = 0;

    public String resolve(String domain) {

        if (cache.containsKey(domain)) {

            DNSEntry entry = cache.get(domain);

            if (System.currentTimeMillis() < entry.expiryTime) {
                hits++;
                return "Cache HIT → " + entry.ip;
            } else {
                cache.remove(domain);
            }
        }

        misses++;

        String ip = queryUpstreamDNS(domain);
        cache.put(domain, new DNSEntry(ip, 300));

        return "Cache MISS → " + ip;
    }

    private String queryUpstreamDNS(String domain) {
        return "172.217.14." + new Random().nextInt(255);
    }

    public void getStats() {

        int total = hits + misses;

        System.out.println("Hit Rate: " + (hits * 100.0 / total) + "%");
    }

    public static void main(String[] args) {

        DNSCache dns = new DNSCache();

        System.out.println(dns.resolve("google.com"));
        System.out.println(dns.resolve("google.com"));

        dns.getStats();
    }
}