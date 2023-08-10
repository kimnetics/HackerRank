    import java.util.*;
    public class test {
        static int maxCount = 0;

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            Map<Integer, Integer> map = new HashMap<>();
            Deque<Integer> deque = new ArrayDeque<>();
            int n = scanner.nextInt();
            int m = scanner.nextInt();

            for (int i = 0; i < n; i++) {
                if (i >= m) {
                    removeNumFromMap(map, deque);
                }

                int num = scanner.nextInt();
                addNumToMap(map, deque, num);
            }

            System.out.println(maxCount);

            scanner.close();
        }

        private static void addNumToMap(Map<Integer, Integer> map, Deque<Integer> deque, int num) {
            int numCount = 1;
            if (map.containsKey(num)) {
                numCount = map.get(num);
                numCount++;
            }
            map.put(num, numCount);

            deque.addLast(num);

            int mapSize = map.size();
            if (mapSize > maxCount) {
                maxCount = mapSize;
            }
        }

        private static void removeNumFromMap(Map<Integer, Integer> map, Deque<Integer> deque) {
            int num = deque.removeFirst();

            int numCount = map.get(num);
            numCount--;
            if (numCount > 0) {
                map.put(num, numCount);
            } else {
                map.remove(num);
            }
        }
    }
