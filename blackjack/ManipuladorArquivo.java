/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.io.*;
import java.util.Scanner;

public class ManipuladorArquivo {
    //String endereco="C:\\Users\\Lucas PC\\Desktop\\BlackJack 26_06\\src\\placar.txt";

    public static void leitor() throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader("C:\\Users\\Public\\BlackJack 26_06\\src\\placar.txt"));
        String linha = "";
        while (true) {
            if (linha != null) {
                System.out.println(linha);

            } else
                break;
            linha = buffRead.readLine();
        }
        buffRead.close();
    }

    public static void escritor(String conteudo) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter("C:\\Users\\Public\\BlackJack 26_06\\src\\placar.txt"));
        String linha = conteudo;
        buffWrite.write(linha + "\n");
        buffWrite.close();
    }
}

//C:\Users\Public\BlackJack 26_06\src
