/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.io.IOException;

// Herdando a classe Partida...
public class PartidaMultijogador extends Partida {

    // Sobrescrita do método iniciar para adicionar comportamento do multijogador
    @Override
    public void iniciar() throws IOException {
        // Pedir a quantidade de jogadores
        int qtdJogadores;
        while (true) {
            System.out.print("Informe a quantidade de jogadores: ");
            qtdJogadores = scanner.nextInt();
            scanner.nextLine(); // Limpar o '\n' que o nextInt deixa

            if (qtdJogadores > 1) { // Entrada válida
                break;
            }
            else { // Caso a quantida de jogadores seja <= 1
                System.out.println("A quantidade de jogadores deve ser maior do que 1");
            }
        }

        // Pedir o nome dos jogadores
        for (int i = 0; i < qtdJogadores; i++) {
            System.out.print("Informe o nome do jogador "+ (i + 1) +": ");
            String nome = scanner.nextLine();

            // Adicionar jogador à partida
            adicionarJogador(new Jogador(nome));
        }

        System.out.print("\n\n");
        Blackjack.limpaConsole();
        System.out.println("\tIniciando a partida multijogador...\n");
        System.out.println("-------------------------------\n");

        // Iniciar a partida
        super.iniciar();
    }


}
