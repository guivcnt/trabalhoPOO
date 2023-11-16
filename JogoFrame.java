package trabalhoPOO;

import javax.swing.JFrame;

public class JogoFrame extends JFrame {

    public JogoFrame() {
        // Configurações básicas do JFrame
        setTitle("Campo de Batalha");
        setSize(1200, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Adiciona um JPanel personalizado que representa o campo de batalha
        add(new Panel());

        // Exibe o frame
        setVisible(true);
    }
}
