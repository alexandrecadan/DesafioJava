package desafiojava;

public class MinhaThread implements Runnable {
    public static int nr = 0;
    private int[][] matrizA;
    private int[][] matrizB;
    private  int[][] matrizC;
    private  int i;

    public MinhaThread(int[][] matrizA, int[][] matrizB, int[][] matrizC, int i) {
        this.matrizA = matrizA;
        this.matrizB = matrizB;
        this.matrizC = matrizC;
        this.i = i;
        //System.out.println("Linha: " + i);
    }

    
    @Override
    public void run() {
        
        for (int j=0; j<matrizA[i].length; j++) {
            matrizC[i][j] = 0;
            for (int k=0; k<matrizA[i].length; k++) {
                matrizC[i][j] += matrizA[i][k] * matrizB[k][j];
            }
        }
        
    }
    
}
