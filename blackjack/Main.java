/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

    import java.io.IOException;

class Main {
    public static void main(String[] args) throws IOException {
        var jogo = new Blackjack();

        jogo.iniciar();
    }
}
