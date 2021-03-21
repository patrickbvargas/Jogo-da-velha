



import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        Jogador[] jogador;
        JogoDaVelha jogo;
        boolean jogoCriado;
        boolean finalizarJogo;
        String nome;
        int dimTabul;
        int lin;
        int col;
        int numJogador;
        boolean jogadaFeita;
        Scanner sc = new Scanner(System.in);

        // --------------------------------------------------

        jogador = new Jogador[2];
        jogo = new JogoDaVelha(0); // Pré inicialização
        jogoCriado = false;
        lin = -1;
        col = -1;
        numJogador = 0;
        jogadaFeita = false;
        finalizarJogo = false;

        // -------------------------------------------------- Criar jogadores

        for (int i = 0; i < jogador.length; i++) {
            do {
                do {
                    System.out.print("Informar nome do(a) jogador(a) " + (i + 1) + ": ");
                    nome = sc.nextLine();

                    if (nome.isEmpty())
                        System.out.println("Favor informar um nome.");
                } while (nome.isEmpty());

                try {
                    jogador[i] = new Jogador(nome, i == 0 ? 'X' : 'O');
                } catch (Exception e) {
                    System.out.println("Não foi possível criar jogador(a) " + (i + 1) + ". Tentaremos novamente.");
                }
            } while (!isValid(jogador[i]));
        }

        System.out.println("");

        // -------------------------------------------------- Criar tabuleiro

        do {
            try {
                System.out.print("Informe a dimensão do tabuleiro [>0]: ");
                dimTabul = Integer.parseInt(sc.nextLine());
                if (dimTabul <= 0){
                    System.out.println("Dimensão inválida. Deve ser um inteiro maior que 0.");
                }else{
                    jogo = new JogoDaVelha(dimTabul);
                    jogoCriado = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Deve-se informar um número inteiro maior que 0.");
            }
        } while (!jogoCriado);

        System.out.println("Jogo criado!\n");
        jogo.imprimirTabuleiro();

        // -------------------------------------------------- Partida

        do {

            do {

                System.out.println("Vez do(a) jogador(a) " + jogador[numJogador].getNome() + " ["
                        + jogador[numJogador].getSimbolo() + "]:");

                try {

                    System.out.print("Informe a linha: ");
                    lin = Integer.parseInt(sc.nextLine());
                    System.out.print("Informe a coluna: ");
                    col = Integer.parseInt(sc.nextLine());

                    if (jogo.realizarJogada(lin, col, jogador[numJogador].getSimbolo()))
                        jogadaFeita = true;
                    else
                        System.out.println("Jogada inválida! Tente novamente.");

                } catch (NumberFormatException e) {
                    System.out.println("Deve-se informar um número inteiro >= 0.");
                }
            } while (!jogadaFeita);

            jogo.imprimirTabuleiro();

            // Conferir situação atual do jogo
            if (jogo.existeVencedor()) {
                System.out.println("Jogador(a) " + jogador[numJogador].getNome() + " vence a partida!");
                jogador[numJogador].registraPonto();
                finalizarJogo = true;
            } else if (jogo.existeEmpate()) {
                System.out.println("Empate! Nenhum(a) jogador(a) vence a partida!");
                finalizarJogo = true;
            } else {
                numJogador = numJogador == 0 ? 1 : 0; // Alterna entre os jogadores
                jogadaFeita = false; // reset
            }

            // Conferir novo jogo, quando necessário
            if (finalizarJogo) {
                if (jogarNovamente(sc)) {
                    jogo.resetarTabuleiro();
                    numJogador = 0;
                    finalizarJogo = false;
                    System.out.println("Iniciando nova partida...");
                    jogo.imprimirTabuleiro();
                }
            }

        } while (!finalizarJogo);

        // -------------------------------------------------- Pós partida

        if (jogador[0].getPontos() == jogador[1].getPontos()) {
            System.out.println("\nJOGADORES(AS) TERMINARAM EMPATADOS(AS)!");
        } else {
            numJogador = jogador[0].getPontos() > jogador[1].getPontos() ? 0 : 1;
            System.out.println("\nJOGADOR(A) " + jogador[numJogador].getNome().toUpperCase() + " VENCE A DISPUTA!");
            System.out.println(jogador[numJogador].toString());
        }

        sc.close();

    }

    // Verifica se um objeto é nulo
    public static boolean isValid(Object obj) {
        return obj != null;
    }

    // Verifica desejo de jogar novamente
    public static boolean jogarNovamente(Scanner sc) {
        String resposta;
        do {
            System.out.print("\nDeseja jogar novamente [S/N]? ");
            resposta = sc.nextLine();
            if (!resposta.equalsIgnoreCase("S") && !resposta.equalsIgnoreCase("N"))
                System.out.println("Resposta inválida.");
        } while (!resposta.equalsIgnoreCase("S") && !resposta.equalsIgnoreCase("N"));

        return resposta.equalsIgnoreCase("S") ? true : false;
    }

}
