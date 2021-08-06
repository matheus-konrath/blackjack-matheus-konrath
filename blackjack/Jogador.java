/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.util.ArrayList;

public class Jogador {
    private final String nome;
    private final ArrayList<Carta> cartas; //baralho
    private boolean parou; //true quando o usuário decidir parar
    private boolean ultrapassou; //true quando o usuário ultrapassar os 21 pontos

    // Construtor
    public Jogador(String nome) {
        this.nome = nome;
        cartas = new ArrayList<>();
        parou = false;
        ultrapassou = false;
    }

    // Getters e Setters

    public String getNome() {
        return nome;
    }

    public boolean getParou() {
        return parou;
    }
    public void setParou(boolean parou) {
        this.parou = parou;
    }

    public boolean getUltrapassou() {
        return ultrapassou;
    }
    public void setUltrapassou(boolean ultrapassou) {
        this.ultrapassou = ultrapassou;
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }
    public void adicionarCarta(Carta carta) {
        cartas.add(carta);
    }

    // Calcular pontos do jogador
    public int calcularPontos() {
        int qtdAs = 0;
        int soma = 0;

        for (Carta carta : cartas) {
            // Verificar se o jogador possui um Ás
            if (carta.getNomeCarta() == NomeCarta.A)
                qtdAs++; // Incrementa a quantidade de Ás

            // Calcular a soma
            soma += carta.getValor();
        }

        // Fazer o cálculo considerando o Ás como 11
        // Para não ultrapassar os 21 pontos é necessário verificar se o jogador possui no máximo 11 pontos (21 - 10)
        while (qtdAs > 0 && soma <= (21 - 10)) {
            // Descontar o valor do Ás que é 1 e somar 11
            soma += 10; // -1 + 11 = 10
            qtdAs--;
        }

        // Retornar a soma
        return soma;
    }

    // Verifica se possui um BLACKJACK
    public boolean possuiUmBlackJack() {
        // Quando o jogador possui 21 pontos com duas cartas
        return cartas.size() == 2 && calcularPontos() == 21;
    }
}
