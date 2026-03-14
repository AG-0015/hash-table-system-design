import java.util.*;

class TokenBucket {

    int tokens;
    int maxTokens;
    long lastRefill;

    TokenBucket(int max) {
        tokens = max;
        maxTokens = max;
        lastRefill = System.currentTimeMillis();
    }
}

class RateLimiter {

    HashMap<String, TokenBucket> clients = new HashMap<>();
    int LIMIT = 1000;

    public boolean checkRateLimit(String clientId) {

        clients.putIfAbsent(clientId, new TokenBucket(LIMIT));
        TokenBucket bucket = clients.get(clientId);

        if (bucket.tokens > 0) {
            bucket.tokens--;
            return true;
        }

        return false;
    }
}