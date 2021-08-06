/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;



    // Enum com os tipos de carta
public enum NomeCarta {
    N2(2, "Dois"), N3(3, "Três"), N4(4, "Quatro"),
    N5(5, "Cinco"), N6(6, "Seis"), N7(7, "Sete"),
    N8(8, "Oito"), N9(9, "Nove"), N10(10, "Dez"),
    J(10, "Valete"), Q(10, "Dama"), K(10, "Rei"),
    A(1, "Ás");

    private final int valor; // Valor da carta no jogo
    private final String nome; // Nome da carta

    // Método Construtor
    NomeCarta(int valor, String nome) {
        this.valor = valor;
        this.nome = nome;
    }

    // Getters

    public int getValor() {
        return valor;
    }

    public String getNome() {
        return nome;
    }
}

