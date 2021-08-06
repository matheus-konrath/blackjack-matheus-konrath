/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;



    
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Classe abstrata partida
public abstract class Partida {

    // Atributos da classe
    protected final Scanner scanner;
    protected final BaralhoFrances baralho;
    protected final Banca banca;
    private final ArrayList<Jogador> jogadores;

    // Construtor da classe com a criação do scanner
    // para ler as inserções do usuário dentro do terminal,
    // e inicialização dos outros atributos
    public Partida() {
        scanner = new Scanner(System.in);
        baralho = new BaralhoFrances();
        banca = new Banca();
        jogadores = new ArrayList<>();
    }

    // Adicionar novo jogador
    protected void adicionarJogador(Jogador jogador) {
        jogadores.add(jogador);
    }

    // Sequência de inicio da partida
    public void iniciar() throws IOException {
        // Distribuir as cartas
        distribuirCartas();

        int maiorPontuacao = 0;

        // Sequência dos jogadores
        for (Jogador jogador : jogadores) {
            System.out.println("\tVez de "+ jogador.getNome() +"\n");
            System.out.println("-------------------------------\n");
            realizarJogadas(jogador);

            int pontosDoJogador = jogador.calcularPontos();

            if (jogador.getParou() && !jogador.possuiUmBlackJack() && pontosDoJogador > maiorPontuacao) {
                maiorPontuacao = pontosDoJogador;
            }
        }

        if (maiorPontuacao > 0) {
            // Vez da banca jogar
            realizarJogadas(maiorPontuacao);
        }

        System.out.println("\tResultados:\n");
        for (Jogador jogador : jogadores) {
            verificarVencedor(banca, jogador);
        }

        // Esperar o jogador pressionar o ENTER
        System.out.print("\nPressione ENTER para voltar ao Menu inicial");
        scanner.nextLine();
    }

    // Sequência de distribuição das cartas
    private void distribuirCartas() {
        // Embaralhar as cartas
        // Será utilizada a quantidade de jogadores para
        // determinar quantos baralhos serão utilizados para embaralhar
        baralho.embaralhar(jogadores.size());

        // Distribuir duas cartas para todos
        for (int i = 0; i < 2; i++) {
            // Adicionar uma carta à todos os jogadores
            for (Jogador jogador : jogadores) {
                jogador.adicionarCarta(baralho.comprarCarta());
            }

            // Adicionar uma carta para a banca
            banca.adicionarCarta(baralho.comprarCarta());
        }
    }

    // Menu de opções para sequência do jogo
    private void exibirMenuOpcoes(Jogador jogador) {
        boolean continua;

        do {
            continua = false;

            // Exibir opções para o jogador
            System.out.println("O que deseja fazer agora?\n");
            System.out.println("[1] Pedir mais uma carta");
            System.out.println("[2] Parar");
            System.out.println("[3] Ajuda\n");

            // Ler a opção escolhida
            System.out.print("Opção escolhida: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o '\n' que o nextInt deixa

            // Realizar a ação de acordo com a opção
            switch (opcao) {
                case 1: // Pedir mais uma carta
                    Carta cartaComprada = baralho.comprarCarta();
                    jogador.adicionarCarta(cartaComprada);

                    System.out.println(jogador.getNome() +" recebeu um(a) " + cartaComprada);
                    break;

                case 2: // Parar
                    jogador.setParou(true);
                    break;

                case 3: // Ajuda
                    exibirAjuda();
                    break;

                default: // Opção inválida
                    System.out.println("Opção inválida\n");
                    continua = true;
            }

            System.out.println("-------------------------------\n");
        }
        while (continua);
    }

    // Sequência de jogadas do jogador
    private void realizarJogadas(Jogador jogador) {
        boolean parar = false;

        while (!parar) {
            // Exibir as cartas da banca
            System.out.println("\tCartas da banca: \n");
            System.out.println(" - " + banca.getCartas().get(0));
            System.out.println(" - *Carta oculta*\n ");
            System.out.println("-------------------------------\n");

            // Exibir as cartas do jogador
            System.out.println("\tCartas de "+ jogador.getNome() +": \n");
            for (var carta : jogador.getCartas()) {
                System.out.println(" - "+ carta);
            }

            // Calcular pontos do jogador
            int pontosDoJogador = jogador.calcularPontos();

            // Exibir os pontos do jogador
            if (jogador.possuiUmBlackJack())
                System.out.println("\n"+ jogador.getNome() +" possui um BLACKJACK! (21 pontos com duas cartas)\n");
            else
                System.out.println("\n" + jogador.getNome() + " está com " + pontosDoJogador + " pontos\n");
            System.out.println("-------------------------------\n");

            // Verificar se o jogador ultrapassou os 21 pontos
            if (pontosDoJogador > 21) {
                jogador.setUltrapassou(true);
                System.out.println(jogador.getNome() +" já perdeu pois ultrapassou os 21 pontos");

                parar = true;
            }
            // Verificar se o jogador conseguiu os 21 pontos
            else if (pontosDoJogador == 21) {
                System.out.println(jogador.getNome() +" conseguiu os 21 pontos\n");
                System.out.println("-------------------------------\n");
                jogador.setParou(true);

                parar = true;
            }
            // Verificar se o jogador decidiu parar
            else if (jogador.getParou()) {
                System.out.println(jogador.getNome() +" decidiu parar\n");
                System.out.println("-------------------------------\n");

                parar = true;
            }

            if (parar) {
                // Esperar o jogador pressionar o ENTER
                System.out.print("\nPressione ENTER para continuar");
                scanner.nextLine();
            }
            else {
                // Exibir as opções da jogada
                exibirMenuOpcoes(jogador);
            }

            // Limpar a tela
            Blackjack.limpaConsole();
        }
    }

    // Sobrecarga do método realizarJogadas
    // Sequência de jogadas da banca
    protected void realizarJogadas(int pontosParaSuperar) {
        while (true) {
            // Mostrar cartas da banca
            System.out.println("Cartas da banca: ");
            for (var carta : banca.getCartas()) {
                System.out.println(" - "+ carta);
            }

            // Calcular os pontos
            int pontosDaBanca = banca.calcularPontos();

            // Exibir os pontos
            if (banca.possuiUmBlackJack())
                System.out.println("\nA banca possui um BLACKJACK! (21 pontos com duas cartas)\n");
            else
                System.out.println("\nA banca está com " + pontosDaBanca + " pontos\n");
            System.out.println("-------------------------------\n");

            // Verificar se a banca ultrapassou os 21 pontos
            if (pontosDaBanca > 21) {
                banca.setUltrapassou(true);
                break;
            }
            // Verificar se a banca possui os 21 pontos ou se superou a maior pontuação dos jogadores
            else if (pontosDaBanca == 21 || pontosDaBanca > pontosParaSuperar) {
                break;
            }
            else {
                // A banca irá comprar carta até alcançar a pontuação do jogador
                Carta cartaComprada = baralho.comprarCarta();
                banca.adicionarCarta(cartaComprada);

                System.out.println("A banca recebeu um(a) " + cartaComprada + "\n");
                try { Thread.sleep(1000); } catch (InterruptedException ignore) { }
            }
        }
    }

    // Verificar vencedor: Banca vs. Jogador
    protected void verificarVencedor(Banca banca, Jogador jogador) throws IOException{
        int pontosDaBanca = banca.calcularPontos();
        int pontosDoJogador = jogador.calcularPontos();

        // Exibir pontuação da Banca e do Jogador
        System.out.print("Banca (");
        System.out.print(banca.possuiUmBlackJack() ? "BLACKJACK" : pontosDaBanca + " pontos");
        System.out.print(") vs. "+ jogador.getNome() +" (");
        System.out.print(jogador.possuiUmBlackJack() ? "BLACKJACK" : pontosDoJogador + " pontos");
        System.out.print("): ");

        // Caso de empate
        if ((jogador.possuiUmBlackJack() && banca.possuiUmBlackJack()) || (pontosDoJogador == pontosDaBanca)) {
            String conteudo="A partida empatou! Tanto a banca quanto " + jogador.getNome() + " possuem " + pontosDoJogador + " pontos.\n";
            ManipuladorArquivo.escritor("Resultado: "+conteudo);
            System.out.println(conteudo);
        }
        // Banca possui um BLACKJACK
        else if (banca.possuiUmBlackJack()) {
            String conteudo = "A banca venceu pois possui um BLACKJACK!\n";
            ManipuladorArquivo.escritor("Resultado: "+conteudo);
            System.out.println(conteudo);
        }
        // Jogador possui um BLACKJACK
        else if (jogador.possuiUmBlackJack()) {
            String conteudo = jogador.getNome() +" venceu pois possui um BLACKJACK!\n";
            ManipuladorArquivo.escritor("Resultado: "+conteudo);
            System.out.println(conteudo);
        }
        // Jogador ultrapassou os 21 pontos
        else if (jogador.getUltrapassou()) {
            String conteudo = "A banca venceu pois o jogador "+ jogador.getNome() +" ultrapassou os 21 pontos!\n";
            ManipuladorArquivo.escritor("Resultado: "+conteudo);
            System.out.println(conteudo);
        }
        // Banca ultrapassou os 21 pontos
        else if (banca.getUltrapassou()) {
            String conteudo = jogador.getNome() +" venceu pois a banca ultrapassou os 21 pontos!\n";
            ManipuladorArquivo.escritor("Resultado: "+conteudo);
            System.out.println(conteudo);
        }
        // Jogador possui mais pontos que a banca
        else if (pontosDoJogador > pontosDaBanca) {
            String conteudo = jogador.getNome() +" venceu pois possui mais pontos que a banca!\n";
            ManipuladorArquivo.escritor("Resultado: "+conteudo);
            System.out.println(conteudo);
        }
        // Banca possui mais pontos que o jogador
        else {
            String conteudo = "A banca venceu pois possui mais pontos que "+ jogador.getNome() +"\n";
            ManipuladorArquivo.escritor("Resultado: "+conteudo);
            System.out.println(conteudo);
        }
    }

    // Menu de ajuda
    protected void exibirAjuda() {
        // Exibir as opções de ajuda
        System.out.println("\n\n\nQue tipo de ajuda deseja acessar?");
        System.out.println("[1] Dica para sua jogada");
        System.out.println("[2] Visualizar regras do jogo");

        // Ler a opção escolhida
        System.out.print("\nOpção escolhida: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar o '\n' que o nextInt deixa

        if (opcao == 1) { // Exibir dica
            System.out.println("\nAJUDA: \n\nVocê tem uma pontuação inferior à 21, então pode continuar. Contudo, " +
                    "existe uma chance de, com a carta que comprar, ultrapassar a pontuação de 21, " +
                    "o que implicaria em derrota.\n");
        }
        else if (opcao == 2) { // Exibir regras
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
        }

        // Esperar o jogador pressionar o ENTER
        System.out.print("Pressione ENTER para continuar jogando");
        scanner.nextLine();
    }
}
