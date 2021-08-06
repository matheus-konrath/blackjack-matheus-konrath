/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

    
    import java.util.ArrayList; //utilização de arrayList/listas
import java.util.Collections;

public class BaralhoFrances {

    private final ArrayList<Carta> cartas;

    // Construtor da classe
    public BaralhoFrances() {
        cartas = new ArrayList<>();
    }

    // Método para criar a quantidade de baralhos especificados
    public void embaralhar(int qtdBaralhos) {
        // Limpar cartas anteriores
        cartas.clear();

        // Adicionar as cartas ao baralho de acordo com a quantidade de baralhos
        for (int i = 0; i < qtdBaralhos; i++) {
            for (NaipeCarta naipe : NaipeCarta.values()) {
                for (NomeCarta nome : NomeCarta.values()) {
                    cartas.add(new Carta(naipe, nome));
                }
            }
        }

        // Embaralhar as cartas
        Collections.shuffle(cartas);
    }

    // Retornar a primeira carta da lista e remove-la para não ser mais utilizada
    public Carta comprarCarta() {
        Carta carta = cartas.get(0);
        cartas.remove(0);
        return carta;
    }

}

