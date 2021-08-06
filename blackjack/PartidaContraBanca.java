/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;


import java.io.IOException;

// Herdando a classe Partida...
public class PartidaContraBanca extends Partida {

    // Sobrescrita do método iniciar para adicionar comportamento específico
    @Override
    public void iniciar() throws IOException {
        // Pedir o nome do jogador
        String nome;
        do {
            System.out.print("Informe o seu nome: ");
            nome = scanner.nextLine();
            ManipuladorArquivo.escritor("\nNome: "+nome);
        }
        while (nome.length() <= 0);

        // Adicionar o jogador à partida
        adicionarJogador(new Jogador(nome));

        System.out.print("\n\n");
        Blackjack.limpaConsole();
        System.out.println("\tIniciando a partida contra a Banca...\n");
        System.out.println("-------------------------------\n");

        // Iniciar a partida
        super.iniciar();
    }
}

