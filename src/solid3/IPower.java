package solid3;

import java.util.Map;
import java.util.HashMap;

public interface IPower {
    int toPower(int n, int pow);
}

class PowerSimple implements IPower {
    @Override
    public int toPower(int n, int pow) {
        if (pow == 0) {
            return 1;
        }
        int res = 1;
        for (int i = 0; i < pow; i++) {
            res *= n;
        }
        return res;
    }
}

class PowerCached implements IPower {
    private static Map<Integer, Map<Integer, Integer>> cache = new HashMap<>();

    @Override
    public int toPower(int n, int pow) {
        Map<Integer, Integer> entry = cache.get(n);

        if (entry == null) {
            entry = new HashMap<>();
            cache.put(n, entry);
        }
        return toCachedPower(entry, n, pow);
    }

    private int toCachedPower(Map<Integer, Integer> e, int n, int pow) {
        if (pow == 0) {
            return 1;
        }

        Integer res = e.get(pow);
        if (res != null) {
            return res;
        } else {
            res = n * toCachedPower(e, n, pow - 1);
            e.put(pow, res);
            return res;
        }
    }
}
