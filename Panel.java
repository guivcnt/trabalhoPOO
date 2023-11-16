package trabalhoPOO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Panel extends JPanel{

    private static final int CAMPO_WIDTH = 800;
    private static final int CAMPO_HEIGHT = 600;
    private static final int ELEMENTO_SIZE = 30;
    private List<Guerreiro> elementosMoveis;

    public Panel() {
        elementosMoveis = new ArrayList<>();

        // Adiciona alguns elementos móveis com posições dispersas e energias aleatórias
        for (int i = 0; i < 10; i++) {
            int x = new Random().nextInt(CAMPO_WIDTH - ELEMENTO_SIZE);
            int y = new Random().nextInt(CAMPO_HEIGHT - ELEMENTO_SIZE);
            int largura = ELEMENTO_SIZE;
            int altura = ELEMENTO_SIZE;
            int velocidadeX = new Random().nextInt(4) + 1; // Velocidade entre 1 e 4
            int velocidadeY = new Random().nextInt(4) + 1;
            int energia = new Random().nextInt(100) + 1; // Energia entre 1 e 100
            int resistencia = new Random().nextInt(20) + 5; // Resistência entre 5 e 25
            int ataque = new Random().nextInt(10) + 5; // Ataque entre 5 e 15

            elementosMoveis.add(new Guerreiro(x, y, largura, altura, velocidadeX, velocidadeY, energia, resistencia, ataque));
        }

        // Inicializa o timer para atualizar o movimento dos elementos móveis
        Timer timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moverElementosMoveis();
                repaint();
            }
        });
        timer.start();
    }

    private void moverElementosMoveis() {
        for (Guerreiro elemento : elementosMoveis) {
    // Verifica e trata colisões com elementos fixos
        for (Rectangle fixo : getElementosFixos()) {
            if (elemento.getBounds().intersects(fixo.getBounds())) {
                // Calcula a colisão com objeto fixo
                elemento.colidirComElementoFixo(fixo);
        }
    }

            // Verifica e trata colisões com outros elementos móveis
            for (Guerreiro outroElemento : elementosMoveis) {
                if (elemento != outroElemento && elemento.getBounds().intersects(outroElemento.getBounds())) {
                    // Calcula a colisão com outro elemento móvel
                    elemento.colidir(outroElemento);
                }
            }

            // Move o elemento após verificar colisões
            elemento.mover();
        }
    }

    private List<Rectangle> getElementosFixos() {
        List<Rectangle> elementosFixos = new ArrayList<>();
        int campoX = (getWidth() - CAMPO_WIDTH) / 2;
        int campoY = (getHeight() - CAMPO_HEIGHT) / 2;

        // Adiciona alguns retângulos como objetos fixos
        elementosFixos.add(new Rectangle(campoX + 50, campoY + 50, 30, 30));
        elementosFixos.add(new Rectangle(campoX + 500, campoY + 400, 80, 80));
        elementosFixos.add(new Rectangle(campoX + 100, campoY + 300, 90, 80));
        elementosFixos.add(new Rectangle(campoX + 700, campoY + 90, 70, 50));
        elementosFixos.add(new Rectangle(campoX + 200, campoY + 100, 60, 60));

        return elementosFixos;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Desenha o campo de batalha menor
        int campoX = (getWidth() - CAMPO_WIDTH) / 2;
        int campoY = (getHeight() - CAMPO_HEIGHT) / 2;
        g.drawRect(campoX, campoY, CAMPO_WIDTH, CAMPO_HEIGHT);

        // Desenha os elementos fixos
        g.setColor(Color.RED);
        for (Rectangle fixo : getElementosFixos()) {
            g.fillRect(fixo.x, fixo.y, fixo.width, fixo.height);
        }

        // Desenha os elementos móveis
        for (Guerreiro elemento : elementosMoveis) {
            // Desenha o elemento móvel
            g.setColor(Color.GREEN);
            g.fillRect(campoX + elemento.x, campoY + elemento.y, elemento.largura, elemento.altura);

            // Exibe a energia do elemento móvel em preto
            g.setColor(Color.BLACK);
            g.drawString(Integer.toString(elemento.energia), campoX + elemento.x, campoY + elemento.y);
        }
    }
}


