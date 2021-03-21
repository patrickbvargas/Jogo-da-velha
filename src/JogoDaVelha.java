public class JogoDaVelha {

    private int[][] tabuleiro; // Optei por representar em int os simbolos 'X' e 'O' -> X = 1 O = -1

    public JogoDaVelha(int dimensao) {
        tabuleiro = new int[dimensao][dimensao];
    }

    public int[][] getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(int[][] tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public boolean realizarJogada(int lin, int col, char simbolo) {

        try {
            if (tabuleiro[lin][col] == 0) {
                tabuleiro[lin][col] = simbolo == 'X' ? 1 : -1;
                return true;
            } else {
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }

    }

    public void resetarTabuleiro(){       
        for (int i = 0; i < tabuleiro.length; i++)
            for (int j = 0; j < tabuleiro[i].length; j++)
                tabuleiro[i][j] = 0;                    
    }

    public void imprimirTabuleiro() {
        System.out.print("\n *  ");
        for (int i = 0; i < tabuleiro[0].length; i++)
            System.out.print(" [" + (i) + "]");
        System.out.print("\n");
        for (int i = 0; i < tabuleiro.length; i++) {
            System.out.print("[" + (i) + "]");
            for (int j = 0; j < tabuleiro[i].length; j++) {
                System.out.print(" | " + (tabuleiro[i][j] == 0 ? " " : tabuleiro[i][j] == 1 ? "X" : "O"));
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public boolean existeVencedor() {

        int totalLin = 0;
        int totalCol = 0;
        int totalDiP = 0;
        int totalDiS = 0;

        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                totalLin += tabuleiro[i][j];
                totalCol += tabuleiro[j][i];
            }

            totalDiP += tabuleiro[i][i];
            totalDiS += tabuleiro[i][tabuleiro[i].length - 1 - i];

            // Confere resultado linha
            if (totalLin == tabuleiro.length || totalLin == tabuleiro.length * (-1))
                return true;
            else
                totalLin = 0; // Reset para próxima iteração de i (não podem acumular)

            // Confere resultado coluna
            if (totalCol == tabuleiro.length || totalCol == tabuleiro.length * (-1))
                return true;
            else
                totalCol = 0; // Reset para próxima iteração de i (não podem acumular)
        }

        // Confere resultado diagonais
        if (totalDiP == tabuleiro.length || totalDiP == tabuleiro.length * (-1) || totalDiS == tabuleiro.length
                || totalDiS == tabuleiro.length * (-1))
            return true;
        else
            return false;

    }

    public boolean existeEmpate() {
        int qtdJogadasDisp = 0;
        for (int i = 0; i < tabuleiro.length;i++)
            for (int j = 0; j < tabuleiro[i].length;j++)
                if (tabuleiro[i][j] == 0)
                    qtdJogadasDisp++;

        return qtdJogadasDisp == 0? true:false;
    }

}