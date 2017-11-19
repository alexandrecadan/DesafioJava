package desafiojava;

public class Matrizes {
    public static final int MAX = 3000;

    public static void ler(int[][] matriz) {
        for (int i=0; i<matriz.length; i++) {
            for (int j=0; j<matriz[i].length; j++) {
                matriz[i][j] = 1;
            }
        }
    }
    public static void mostrar(int[][] matriz) {
        for (int i=0; i<matriz.length; i++) {
            for (int j=0; j<matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void multiplicar1(int[][] matrizA, int[][] matrizB, int[][] matrizC) {
        
        for (int i=0; i<matrizA.length; i++) {
            //System.out.printf("Linha %d\n", i);
            for (int j=0; j<matrizA[i].length; j++) {
                matrizC[i][j] = 0;
                for (int k=0; k<matrizA[i].length; k++) {
                    matrizC[i][j] += matrizA[i][k] * matrizB[k][j];
                }
            }
        }
    }
    public static void multiplicar2(int[][] matrizA, int[][] matrizB, int[][] matrizC, int numeroThreads) {
        Thread[] threads = new Thread[numeroThreads];
        
        try {
            int n = 0;
            for (int i=0; i<matrizA.length; i++) {
                MinhaThread m = new MinhaThread(matrizA, matrizB, matrizC, i);
                Thread t = new Thread(m);
                threads[n] = t;
                n++;
                t.start();

                if ((i+1) % numeroThreads==0) {
                    // chegou no número máximo de threads
                    // faz join de todas
                    for(int j=0; j<threads.length; j++) {
                        threads[j].join();
                    }
                    // reseta o vetor de threads
                    n = 0;
                }
            }
        }
        catch( InterruptedException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
    
    public static void main(String[] args) {
        
        int numeroThreads = Integer.parseInt(args[0]);
        int[][] matrizA = new int[MAX][MAX];
        int[][] matrizB = new int[MAX][MAX];
        int[][] matrizC = new int[MAX][MAX];
        long startTime;
        long endTime;
        long duracaoSeq;
        long duracaoThread;
        
        ler(matrizA);
        ler(matrizB);
        
        if (numeroThreads == 0) {
            startTime = System.nanoTime();
            multiplicar1(matrizA, matrizB, matrizC);
            endTime = System.nanoTime();
            duracaoSeq = (endTime - startTime);
            System.out.printf("Mult (seq) = %f s\n", ((double)duracaoSeq) / 1E9);
        }
        else {
            startTime = System.nanoTime();
            multiplicar2(matrizA, matrizB, matrizC, numeroThreads);
            endTime = System.nanoTime();
            duracaoThread = (endTime - startTime);  

            System.out.printf("Mult (%d threads) = %f s\n", numeroThreads, ((double)duracaoThread) / 1E9);
        }
       
    }
    
}
