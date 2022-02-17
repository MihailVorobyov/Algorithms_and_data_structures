1. Прочитать в книге "Грокаем алгоритмы" или в предложенных материалах про алгоритмы и О-большое.
2. 
3. Определить сложность следующих алгоритмов:
   -. Поиск элемента массива с известным индексом -> O(n)
   -. Дублирование одномерного массива через foreach -> 
   -. Удаление элемента массива с известным индексом без сдвига -> O(1) если массив отсортирован, иначе 
   -. Удаление элемента массива с неизвестным индексом без сдвига -> O(n)
   -. Удаление элемента массива с неизвестным индексом со сдвигом -> O(n ^ 2)
   .

4. Определить сложность следующих алгоритмов. Сколько произойдет итераций?

a)

        int n = 10000;  // O(1)
        List<Integer> arrayList = new ArrayList<>();  // O(1)
        for (int i = 0; i < n; i++) {        // O(1) + O(n), 10000 итераций
            for (int j = 1; j < n; j *= 2) { // O(1) + O(log n), log n итераций
                arrayList.add(i * j);        // O(n * log n + 2)
            }
        }

O(1) + O(1) + O(1) + O(n) + O(1) + O(log n) + O(n * log n + 2) = O(4) + O(n) + O(log n) + O(n * log n + 2) = O(n + log n + n * log n + 2) = O(n + (n + 1) * log n) = O(n + n * log n) =

= O(n * (1 + log n)) = O(n * log n)

Сложность O(n * log n)
10000 * log n  итераций

b)

        int n = 10000;  // O(1)
        List<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < n; i += 2) {
            for (int j = i; j < n; j++) {
                arrayList.add(i * j);           
            }
        }

Сложность O(n ^ 2)

n / 4 итераций 

с)

        int n = 10000;
        List<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < n; i++) { // O(3) + O(n ^ log n), n ^ log n итераций
            for (int j = 0; j < n; j++) { // O(1) + O(n / 2), n / 2 итераций
                arrayList.add(i * j);  // O(2)
                n--; // O(2)
            }
        }

Сложность O(n)
Примерно n итераций


d*)

```
   factorial(BigInteger.valueOf(10000))  // n = 10000;
   
   public static BigInteger factorial(BigInteger n) {
       if (n.equals(BigInteger.ONE)) {
           return n;
       }
       return n.multiply(factorial(n.subtract(BigInteger.valueOf(1))));
   }
   
 
````

Сложность O(n!), 10000 итераций

````
e*)
fib(BigInteger.valueOf(50));

public static BigInteger fib(BigInteger n) {
    if (n.equals(BigInteger.ONE) || n.equals(BigInteger.TWO)) {
        return n;
    }
    return fib(n.subtract(BigInteger.ONE)).add(fib(n.subtract(BigInteger.TWO)));
}
````