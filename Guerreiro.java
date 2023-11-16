package TrabalhoPOO;

import java.awt.Rectangle;
import java.util.Random;

public class Guerreiro {

    private static final int CAMPO_WIDTH = 800;
    private static final int CAMPO_HEIGHT = 600;
    int x;
    int y;
    int largura;
    int altura;
    private int velocidadeX, velocidadeY;
    int energia;
    private int resistencia;
    private int ataque;

    public Guerreiro(int x, int y, int largura, int altura, int velocidadeX, int velocidadeY, int energia, int resistencia, int ataque) {
        this.x = x;
        this.y = y;
        this.largura = largura;
        this.altura = altura;
        this.velocidadeX = velocidadeX;
        this.velocidadeY = velocidadeY;
        this.energia = energia;
        this.resistencia = resistencia;
        this.ataque = ataque;
    }

    public void mover() {
        // Salva a posição anterior para reverter em caso de colisão
        int xAntes = x;
        int yAntes = y;

        // Atualiza a posição do elemento
        x += velocidadeX;
        y += velocidadeY;

        // Verifica se o elemento atingiu uma parede do campo de batalha
        if (x < 0 || x + largura > CAMPO_WIDTH) {
            // Reverte a posição em caso de colisão
            x = xAntes;
            y = yAntes;

            // Inverte a direção
            velocidadeX = -velocidadeX;
            // Desconta energia ao atingir a parede
            energia -= 1;
        }

        if (y < 0 || y + altura > CAMPO_HEIGHT) {
            // Reverte a posição em caso de colisão
            x = xAntes;
            y = yAntes;

            // Inverte a direção
            velocidadeY = -velocidadeY;
            // Desconta energia ao atingir a parede
            energia -= 1;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }

    public void colidir(Guerreiro outro) {
        // Cálculo da colisão entre objetos móveis
        energia -= 25;
        if (energia <= 0) {
            // Remover elemento se energia for menor ou igual a zero
            this.x = -1000; // Posição fora do campo de visão
            this.y = -1000;
        }
    }

    public void colidirComElementoFixo(Rectangle objetoFixo) {
        // Cálculo da colisão com objetos fixos
        energia -= 5;
        if (energia <= 0) {
            // Remover elemento se energia for menor ou igual a zero
            this.x = -1000; // Posição fora do campo de visão
            this.y = -1000;
        } else {
            // Calcula o vetor de reflexão usando as bordas do retângulo fixo
            int normalX, normalY;
    
            // Verifica em qual lado do retângulo a colisão ocorreu
            if (x < objetoFixo.x) {
                // Colisão ocorreu à esquerda do retângulo fixo
                normalX = -1;
            } else if (x > objetoFixo.x + objetoFixo.width) {
                // Colisão ocorreu à direita do retângulo fixo
                normalX = 1;
            } else {
                // Colisão ocorreu nas bordas horizontal do retângulo fixo
                normalX = 0;
            }
    
            // Verifica em qual lado do retângulo a colisão ocorreu
            if (y < objetoFixo.y) {
                // Colisão ocorreu acima do retângulo fixo
                normalY = -1;
            } else if (y > objetoFixo.y + objetoFixo.height) {
                // Colisão ocorreu abaixo do retângulo fixo
                normalY = 1;
            } else {
                // Colisão ocorreu nas bordas vertical do retângulo fixo
                normalY = 0;
            }
    
            // Calcula a nova velocidade refletida
            int produtoEscalar = (velocidadeX * normalX + velocidadeY * normalY) * 2;
            velocidadeX -= produtoEscalar * normalX;
            velocidadeY -= produtoEscalar * normalY;
    
            // Move o elemento para fora do objeto fixo
            x += velocidadeX;
            y += velocidadeY;
        }
    }
    
}
