package ua.opnu;

import java.util.*;

public class Task {

    // --- Завдання 1 ---
    public static void removeShorterStrings(List<String> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            String first = list.get(i);
            String second = list.get(i + 1);

            if (first.length() <= second.length()) {
                list.remove(i);
            } else {
                list.remove(i + 1);
            }
            // Після видалення індекси зміщуються, тому i++ у циклі
            // перекине нас через один елемент до наступної пари.
            // Додаткових дій з i тут не потрібно.
        }
    }

    // --- Завдання 2 ---
    public static void stutter(List<String> list) {
        // Ітеруємось з кінця, щоб не збивати індекси при додаванні
        for (int i = list.size() - 1; i >= 0; i--) {
            list.add(i + 1, list.get(i));
        }
    }

    // --- Завдання 3 ---
    public static void switchPairs(List<String> list) {
        for (int i = 0; i < list.size() - 1; i += 2) {
            String temp = list.get(i);
            list.set(i, list.get(i + 1));
            list.set(i + 1, temp);
        }
    }

    // --- Завдання 4 ---
    public static void removeDuplicates(List<String> list) {
        if (list.isEmpty()) return;

        // Ітеруємось з кінця до 1
        for (int i = list.size() - 1; i > 0; i--) {
            if (list.get(i).equals(list.get(i - 1))) {
                list.remove(i);
            }
        }
    }

    // --- Завдання 5 ---
    public static void markLength4(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).length() == 4) {
                list.add(i, "****");
                i++; // Перестрибуємо через оригінальний рядок
            }
        }
    }

    // --- Завдання 6 ---
    public static boolean isPalindrome(Queue<Integer> q) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int size = q.size();
        boolean result = true;

        // 1. Заповнюємо стек і відновлюємо чергу (циклічний зсув)
        for (int i = 0; i < size; i++) {
            int val = q.poll();
            stack.push(val);
            q.offer(val);
        }

        // 2. Порівнюємо чергу зі стеком (стек дає зворотний порядок)
        for (int i = 0; i < size; i++) {
            int valQ = q.poll();
            int valS = stack.pop();

            if (valQ != valS) {
                result = false;
            }
            // Відновлюємо чергу
            q.offer(valQ);
        }

        return result;
    }

    // --- Завдання 7 ---
    public static void reorder(Queue<Integer> q) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int size = q.size();

        // 1. Всі елементи в стек (верхівка стеку - найменші за модулем)
        for (int i = 0; i < size; i++) {
            stack.push(q.poll());
        }
        // 2. Стек у чергу (отримуємо зворотний порядок: 1, 2, 4, -5, ...)
        for (int i = 0; i < size; i++) {
            q.offer(stack.pop());
        }

        // 3. Від'ємні залишаємо (вони підуть у кінець), додатні в стек
        for (int i = 0; i < size; i++) {
            int val = q.poll();
            if (val >= 0) {
                stack.push(val);
            } else {
                q.offer(val);
            }
        }

        // 4. Повертаємо додатні зі стеку (вони розвернуться у правильний порядок)
        // Зараз у черзі тільки від'ємні. Додаємо додатні.
        while (!stack.isEmpty()) {
            q.offer(stack.pop());
        }

        // Порядок зараз: від'ємні (правильно), додатні (правильно)
    }

    // --- Завдання 8 ---
    public static void rearrange(Queue<Integer> q) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int size = q.size();

        // 1. Парні - в кінець черги, Непарні - в стек
        for (int i = 0; i < size; i++) {
            int val = q.poll();
            if (val % 2 == 0) {
                q.offer(val);
            } else {
                stack.push(val);
            }
        }

        // Зараз у черзі тільки парні. У стеку - непарні (у зворотному порядку).

        // 2. Перекидаємо стек у чергу (тепер непарні у черзі, але перевернуті)
        int oddCount = stack.size();
        while (!stack.isEmpty()) {
            q.offer(stack.pop());
        }

        // Черга: [Парні..., Непарні(reversed)...]

        // 3. Переміщуємо парні в кінець, щоб дістатися до непарних
        int evenCount = size - oddCount;
        for (int i = 0; i < evenCount; i++) {
            q.offer(q.poll());
        }

        // Черга: [Непарні(reversed)..., Парні...]

        // 4. Забираємо непарні в стек (щоб розвернути їх назад у правильний порядок)
        for (int i = 0; i < oddCount; i++) {
            stack.push(q.poll());
        }

        // 5. Повертаємо парні на початок (вони зараз в кінці, треба прокрутити)
        // Але парні вже стоять правильно відносно один одного.
        // Зараз черга містить [Парні...], а стек [Непарні (правильний порядок на вихід)]

        // 6. Повертаємо непарні зі стеку в кінець черги
        while (!stack.isEmpty()) {
            q.offer(stack.pop());
        }
    }

    // --- Завдання 9 ---
    public static int maxLength(Set<String> set) {
        int max = 0;
        for (String s : set) {
            if (s.length() > max) {
                max = s.length();
            }
        }
        return max;
    }

    // --- Завдання 10 ---
    public static void removeEvenLength(Set<String> set) {
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            if (s.length() % 2 == 0) {
                iterator.remove();
            }
        }
    }

    // --- Завдання 11 ---
    public static int numInCommon(List<Integer> list1, List<Integer> list2) {
        Set<Integer> set1 = new HashSet<>(list1);
        Set<Integer> set2 = new HashSet<>(list2);

        // Залишаємо в set1 тільки ті, що є в set2
        set1.retainAll(set2);

        return set1.size();
    }

    // --- Завдання 12 ---
    public static boolean isUnique(Map<String, String> map) {
        Set<String> values = new HashSet<>();
        for (String value : map.values()) {
            if (!values.add(value)) {
                // Якщо add повернув false, значення вже було -> дублікат
                return false;
            }
        }
        return true;
    }

    // --- Завдання 13 ---
    public static Map<String, Integer> intersect(Map<String, Integer> map1, Map<String, Integer> map2) {
        Map<String, Integer> result = new HashMap<>();

        for (String key : map1.keySet()) {
            if (map2.containsKey(key)) {
                Integer val1 = map1.get(key);
                Integer val2 = map2.get(key);

                if (Objects.equals(val1, val2)) {
                    result.put(key, val1);
                }
            }
        }
        return result;
    }

    // --- Завдання 14 ---
    public static Map<String, Integer> reverse(Map<Integer, String> map) {
        Map<String, Integer> result = new HashMap<>();
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            // put перезапише значення, якщо ключ вже є
            result.put(entry.getValue(), entry.getKey());
        }
        return result;
    }

    // --- Завдання 15 ---
    public static int rarest(Map<String, Integer> map) {
        if (map.isEmpty()) throw new IllegalArgumentException("Map is empty");

        Map<Integer, Integer> counts = new TreeMap<>(); // TreeMap для сортування ключів (значень)

        // Рахуємо частоту кожного числа
        for (Integer value : map.values()) {
            counts.put(value, counts.getOrDefault(value, 0) + 1);
        }

        int minFreq = Integer.MAX_VALUE;
        int rarestValue = Integer.MAX_VALUE;

        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            int value = entry.getKey();
            int freq = entry.getValue();

            if (freq < minFreq) {
                minFreq = freq;
                rarestValue = value;
            } else if (freq == minFreq) {
                // Якщо частота однакова, обираємо менше значення
                if (value < rarestValue) {
                    rarestValue = value;
                }
            }
        }

        return rarestValue;
    }

    // --- Завдання 16 ---
    public static int maxOccurrences(List<Integer> list) {
        if (list.isEmpty()) return 0;

        Map<Integer, Integer> counts = new HashMap<>();
        for (Integer num : list) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        int maxOccur = 0;
        for (int count : counts.values()) {
            if (count > maxOccur) {
                maxOccur = count;
            }
        }
        return maxOccur;
    }
}