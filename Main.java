package aed;

class Main {
    int fibonacci(int n) {
        int res;

        if (n == 0) {
            res = 0;
        } else if (n == 1) {
            res = 1;
        } else {
            res = fibonacci(n - 1) + fibonacci(n - 2);
        }

        return res;
    }

    int fibonacciReturn(int n) {
        // Se pueden unificar estos if?
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fibonacciReturn(n - 1) + fibonacciReturn(n - 2);
    }

    // Revisar, no pasa los tests
    double maximo(double[] xs) {
        double res = 0;

        for (double x : xs) {
            // Si encuentro un elemento mayor al que tenía guardado, me lo guardo
            if (x > res) {
                res = x;
            }
        }

        return res;
    }

    int[] sumarArreglos(int[] seq1, int[] seq2) {
        int[] res = new int[seq1.length];

        for (int i = 0; i < seq1.length; i++) {
            // Revisar el error
            res[i] = seq1[i] + seq2[i];
        }

        return res;
    }

    String iniciales(String texto) {
        String res = "";
        for (int i = 0; i < texto.length(); i++) {
            if (i == 0 || (texto.charAt(i-1) == ' ' && texto.charAt(i) != ' ')) {
                res += texto.charAt(i);
            }
        }
        return res;
    }

    //BONUS TRACK

    public static long fibonacciIterativo(int n) {
        throw new UnsupportedOperationException("Method not implemented yet.");
    }

    // public static long fibonacciIterativo(int n) {
    //     long res;
    //     if (n < 2) {
    //         res = n;
    //     } else {
    //         res = 1;
    //         long fn_1 = 1;
    //         long fn_2 = 0;
    //         for (long i = 2; i <= n; i++) {
    //             res = fn_1 + fn_2;
    //             fn_2 = fn_1;
    //             fn_1 = res;
    //         }
    //     }
    //     return res;
    // }

    // public static void main(String[] args) {
    //     for (int i = 0; i < 90; i++) {
    //         System.out.println(i + ": " + fibonacciIterativo(i));
    //     }

    // }
}