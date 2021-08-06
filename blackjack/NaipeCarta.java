/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

    // Enum para representar os quatro naipes
public enum NaipeCarta {
    PAUS, OUROS, COPAS, ESPADAS;

    // Retornar o nome do naipe apenas com a primeira letra maiúscula
    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}


