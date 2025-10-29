package testeleandro;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Projeto {

    public static void main(String[] args) throws InterruptedException {
        Scanner entrada = new Scanner(System.in);
        Jogador jogador = new Jogador();
        Inimigo inimigo_alfa = new Inimigo(450, 120, 5, 5, "toxico");

        Torre torre_alfa = new Torre(20, 10, 30, "Bola de fogo", 0, 0);
        Torre torre_beta = new Torre(30, 10, 50, "laser", 0, 0);

      List<Torre> torres = new ArrayList<>();

// Loop principal 
while (true) {
    // Reiniciar vida do inimigo
    inimigo_alfa.vida = 450;
    inimigo_alfa.posicaox = 0;
    inimigo_alfa.posicaoy = 0;

    // Tutorial 
    if (torres.isEmpty()) {
        System.out.println("tutorial: assista o que o inimigo fará caso você não proteja os arredores");
        inimigo_alfa.percurso1vazio();
        inimigo_alfa.percurso2vazio();
        inimigo_alfa.percurso3vazio();
        jogador.creditardinheiro();
        System.out.println("Agora você tem " + jogador.dinheiro + " moedas.");
    }

    // Comprar vender e jogar
    System.out.println("Escolha: 'comprar', 'vender', 'jogar' ou 'sair'.");
    String acao = entrada.nextLine();

    if (acao.equals("sair")) break;

    if (acao.equals("vender")) {
        if (torres.isEmpty()) {
            System.out.println("Não há torres para vender!");
        } else {
            System.out.println("Torres no campo:");
            for (int i = 0; i < torres.size(); i++) {
                Torre t = torres.get(i);
                System.out.println((i + 1) + " - " + t.poder + " em (" + t.posicaox + "," + t.posicaoy + ")");
            }
            System.out.println("Digite o número da torre que deseja vender:");
            int escolha = Integer.parseInt(entrada.nextLine());
            Torre vendida = torres.remove(escolha - 1);
            jogador.dinheiro += vendida.dano_por_segundo * 2; // valor de revenda simplificado
            System.out.println("Torre vendida! Agora você tem " + jogador.dinheiro + " moedas.");
        }
    }

    if (acao.equals("comprar")) {
        System.out.println("Escolha uma torre: alfa (80 moedas) ou beta (200 moedas)");
        String escolhaTorre = entrada.nextLine();
        if ((escolhaTorre.equals("alfa") && jogador.dinheiro < 80) ||
            (escolhaTorre.equals("beta") && jogador.dinheiro < 200)) {
            System.out.println("Dinheiro insuficiente!");
            continue;
        }

        System.out.println("Escolha a posição 1: (10,0), 2: (8,7) ou 3: (15,6)");
        String pos = entrada.nextLine();
        int x = 0, y = 0;
        if (pos.equals("1")) { x = 10; y = 0; }
        else if (pos.equals("2")) { x = 8; y = 7; }
        else if (pos.equals("3")) { x = 15; y = 6; }
        else { System.out.println("Posição inválida"); continue; }

        // Verificar se já existe torre nessa posição
        boolean ocupada = false;
        for (Torre t : torres) {
            if (t.posicaox == x && t.posicaoy == y) {
                ocupada = true;
                break;
            }
        }
        if (ocupada) {
            System.out.println("Já existe uma torre nessa posição! Escolha outra.");
            continue;
        }

        Torre nova = escolhaTorre.equals("alfa") ? new Torre(20,10,30,"Bola de fogo", x, y)
                                                  : new Torre(30,10,50,"laser", x, y);
        torres.add(nova);
        jogador.dinheiro -= escolhaTorre.equals("alfa") ? 80 : 200;
        System.out.println("Torre " + nova.poder + " colocada em (" + x + "," + y + ").");
    }

    // Rodada de ataque
    int[][] percurso = { {0,0}, {1,0}, {2,0}, {3,0}, {4,0}, {5,0}, {6,0}, {7,0}, {8,0}, 
                         {8,1}, {8,2}, {8,3}, {8,4}, {8,5}, {8,6}, {9,6}, {10,6}, {11,6}, {12,6}, {13,6}, {14,6} };

    for (int[] pos : percurso) {
        inimigo_alfa.posicaox = pos[0];
        inimigo_alfa.posicaoy = pos[1];
        System.out.println("Posição inimigo: (" + inimigo_alfa.posicaox + "," + inimigo_alfa.posicaoy + ")");

        // Dano das torres
        for (Torre t : torres) {
            if (Math.abs(t.posicaox - inimigo_alfa.posicaox) <= 3 &&
                Math.abs(t.posicaoy - inimigo_alfa.posicaoy) <= 3) {
                inimigo_alfa.receber_dano(t.dano_por_segundo, t.poder);
                if (inimigo_alfa.vida <= 0) break;
            }
        }
        if (inimigo_alfa.vida <= 0) {
            System.out.println("O inimigo morreu!");
            break;
        }

        Thread.sleep((long)(inimigo_alfa.tempo_de_chegada_segundos * 10));
    }

    if (inimigo_alfa.vida > 0) {
        System.out.println("O inimigo chegou na torre com " + inimigo_alfa.vida + " pontos de vida!");
    }

    // Creditar
    jogador.creditardinheiro();
    System.out.println("Rodada finalizada. Você agora tem " + jogador.dinheiro + " moedas.");
    System.out.println("Torres no campo:");
    for (Torre t : torres) {
        System.out.println("- " + t.poder + " em (" + t.posicaox + "," + t.posicaoy + ")");
    }
}
    }
}