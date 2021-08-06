/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;


import java.io.IOException;
import java.util.Scanner;

public class Blackjack {

    private final Scanner scanner;

    // Construtor da classe com a declaração do uso de scanner
    // para ler as inserções do usuário dentro do terminal, durante a execução do código
    public Blackjack() {
        scanner = new Scanner(System.in);
    }

    // Quebra de linhas utilizada para evitar a poluição do terminal
    public static void limpaConsole() {
        for (int i = 0; i <= 100; i++) {
            System.out.print("\n");
        }
    }

    // Apresentação do primeiro menu e sequência com escolha das opções
    public void iniciar() throws IOException {
        boolean continua = true;

        while (continua) {
            limpaConsole();
            System.out.println("\n--------- Menu Inicial --------\n");
            System.out.println("[0] Sair");
            System.out.println("[1] Jogar contra a banca");
            System.out.println("[2] Multijogador");
            System.out.println("[3] Exibir regras do Blackjack");
            System.out.println("[4] Exibir placar");
            System.out.print("\nOpção escolhida: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 0: // Sair
                    continua = false;
                    break;

                case 1: // Jogar contra a banca
                    iniciarPartidaContraBanca();
                    break;

                case 2: // Multijogador
                    iniciarPartidaMultijogador();
                case 3: //sequencia de print p/ exibir regras
                    System.out.println("\nRegras do BlackJack:\n");
                    System.out.println("O objetivo de qualquer " +
                            "mão de Blackjack é derrotar a mesa. Para fazer isso, " +
                            "você deve ter uma mão em que a pontuação seja mais " +
                            "elevada do que a mão do dealer, mas não exceda 21 no " +
                            "valor total. \nComo alternativa, você pode ganhar tendo " +
                            "uma pontuação menor que 22 quando o valor da mão do " +
                            "dealer ultrapassar 21. Quando o valor total da sua mão " +
                            "for 22 ou mais, normalmente conhecido como 'estourar', " +
                            "você vai automaticamente perder\n");
                    try { Thread.sleep (10000); } catch (InterruptedException ex) {}
                    break;
                case 4:
                    System.out.println("\nPartidas jogadas: ");
                    ManipuladorArquivo.leitor();
                    try { Thread.sleep (10000); } catch (InterruptedException ex) {}
                    break;
                default: // Opção inválida
                    System.out.println("Opção inválida");
            }

            System.out.println("-------------------------------\n");
        }
        //quando sair do while do menu, printamos que o programa está encerrando
        //e não redirecionamos para outro lugar, ou seja, o código não tem mais o que executar
        System.out.println("Saindo do jogo");
    }

    // Cria um novo objeto e o inicia a partir de sua classe
    private void iniciarPartidaContraBanca() throws IOException{
        PartidaContraBanca partida = new PartidaContraBanca();
        partida.iniciar();
    }

    // Cria um novo objeto e o inicia a partir de sua classe
    private void iniciarPartidaMultijogador() {
        PartidaMultijogador partida = new PartidaMultijogador();
        try {
            partida.iniciar();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}
