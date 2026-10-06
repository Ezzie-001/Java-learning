public class TwoSum {
    public static void main(String[] args) {

        // The shelf of prices. This is an ARRAY: a list of numbers in one variable.
        // Spot (index):   0  1  2   3
        int[] prices =   { 2, 7, 11, 15 };

        // How much money we have to spend
        int target = 18;

        // prices.length = how many items are on the shelf (4)
        System.out.println("Looking for two prices that add up to " + target);
        System.out.println();

        // OUTER LOOP: pick the first item (spot i)
        for (int i = 0; i < prices.length; i++) {

            // INNER LOOP: try pairing it with every item AFTER it (spot j)
            // j starts at i + 1 so we never pick the same item twice
            for (int j = i + 1; j < prices.length; j++) {

                // prices[i] means "the price sitting at spot i"
                int sum = prices[i] + prices[j];

                System.out.println("Trying spot " + i + " (" + prices[i] + ") + spot "
                        + j + " (" + prices[j] + ") = " + sum);

                if (sum == target) {
                    System.out.println();
                    System.out.println("Found it! Spots " + i + " and " + j);
                    return; // stop the whole program, we're done
                }
            }
        }

        System.out.println("No pair adds up to " + target);
    }
}
