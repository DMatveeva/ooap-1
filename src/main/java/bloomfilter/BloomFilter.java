package bloomfilter;

public class BloomFilter {

    private int filter_len;
    private int bit_array;

    public BloomFilter(int f_len) {
        filter_len = f_len;
        bit_array = 0;
    }

    // хэш-функции
    public int hash1(String str1) {
        // 17
        int res = 0;
        for (int i = 0; i < str1.length(); i++) {
            int code = (int) str1.charAt(i);
            res = (res * 17 + code) % this.filter_len;
        }
        return (int) Math.pow(2, res);
    }

    public int hash2(String str1) {
        // 223
        int res = 0;
        for (int i = 0; i < str1.length(); i++) {
            int code = (int) str1.charAt(i);
            res = (res * 223 + code) % this.filter_len;
        }
        return (int) Math.pow(2, res);
    }

    public void add(String str1) {
        int hs1 = this.hash1(str1);
        int hs2 = this.hash2(str1);
        this.bit_array = this.bit_array | hs1;
        this.bit_array = this.bit_array | hs2;
    }

    public boolean isValue(String str1) {
        int hs1 = this.hash1(str1);
        int hs2 = this.hash2(str1);
        boolean check_first = (this.bit_array & hs1) != 0;
        boolean check_second = (this.bit_array & hs2) != 0;
        return check_first && check_second;
    }
}
