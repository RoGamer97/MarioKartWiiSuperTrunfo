package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import controle.ControleCarta;
import controle.ControleJogo;
import controle.ControleMao;
import modelo.Carta;
import modelo.TipoJogador;

public class ViewJogoMenuCarta extends JFrame
{
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel panelCartasContainer;
    private JScrollPane scrollPane;
    private JLabel lblRodadaValores;
    private JLabel lblPlacar;
    private JLabel lblTextoRodada;
    private ViewJogo viewJogo;
    private ControleJogo controleJogo;
    private ControleMao controleMao;
    private ControleCarta controleCarta;

    public ViewJogoMenuCarta(ViewJogo viewJogo, ControleJogo controleJogo, ControleMao controleMao, ControleCarta controleCarta)
    {
        this.viewJogo = viewJogo;
        this.controleJogo = controleJogo;
        this.controleMao = controleMao;
        this.controleCarta = controleCarta;

        setTitle("Mario Kart Wii Super Trunfo");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(50, 50, 1150, 700);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Escolha sua carta", SwingConstants.CENTER);
        lblTitulo.setBounds(10, 11, 1100, 25);
        contentPane.add(lblTitulo);

        lblTextoRodada = new JLabel("Rodada", SwingConstants.CENTER);
        lblTextoRodada.setBounds(10, 32, 1100, 20);
        contentPane.add(lblTextoRodada);

        lblRodadaValores = new JLabel("", SwingConstants.CENTER);
        lblRodadaValores.setBounds(10, 50, 1100, 20);
        contentPane.add(lblRodadaValores);

        lblPlacar = new JLabel("", SwingConstants.CENTER);
        lblPlacar.setBounds(10, 75, 1100, 20);
        contentPane.add(lblPlacar);

        panelCartasContainer = new JPanel();
        panelCartasContainer.setLayout(null);

        scrollPane = new JScrollPane(panelCartasContainer);
        scrollPane.setBounds(10, 105, 1115, 545);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        contentPane.add(scrollPane);

        setLocationRelativeTo(null);
    }

    public void atualizarMostrarMenu()
    {
        lblRodadaValores.setText(controleJogo.getRodadaAtual() + " / " + controleJogo.getTotalRodadas());
        lblPlacar.setText("Humano " + controleJogo.getPontosPartida(TipoJogador.HUMANO) + " - " + controleJogo.getPontosPartida(TipoJogador.MAQUINA) + " Maquina");

        panelCartasContainer.removeAll();

        List<Carta> listaCartas = controleMao.getCartasMao(TipoJogador.HUMANO);
        int cartasPorLinha = 6;
        int totalLinhas = (int) Math.ceil(listaCartas.size() / (double)cartasPorLinha);

        int novaAltura = (totalLinhas * 270) + 40;
        panelCartasContainer.setPreferredSize(new Dimension(1000, novaAltura));

        for (int i = 0; i < listaCartas.size(); i++)
        {
            Carta carta = listaCartas.get(i);
            int posX = 10 + (i % cartasPorLinha) * 175;
            int posY = 10 + (i / cartasPorLinha) * 270;

            JPanel panelCarta = criarPainelCarta(carta, posX, posY);
            panelCartasContainer.add(panelCarta);
        }

        panelCartasContainer.revalidate();
        panelCartasContainer.repaint();
        scrollPane.revalidate();

        SwingUtilities.invokeLater(() ->
        {
            JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
            verticalBar.setValue(verticalBar.getMinimum());
        });

        setVisible(true);
    }

    private JPanel criarPainelCarta(Carta carta, int x, int y)
    {
        JPanel panelCarta = new JPanel();
        panelCarta.setBorder(new LineBorder(Color.BLACK, 2));
        panelCarta.setBounds(x, y, 160, 250);
        panelCarta.setLayout(null);

        JLabel lblNome = new JLabel(carta.getNome(), SwingConstants.CENTER);
        lblNome.setBounds(5, 5, 150, 14);
        panelCarta.add(lblNome);

        JLabel lblImagem = new JLabel("", SwingConstants.CENTER);
        lblImagem.setBounds(10, 23, 140, 60);

        byte[] imagemBytes = carta.getImagem();
        if (imagemBytes != null && imagemBytes.length > 0)
        {
            try
            {
                ByteArrayInputStream bis = new ByteArrayInputStream(imagemBytes);
                Image img = ImageIO.read(bis);
                lblImagem.setIcon(new ImageIcon(img.getScaledInstance(140, 60, Image.SCALE_SMOOTH)));
            }
            catch (Exception e)
            {
                lblImagem.setIcon(null);
            }
        }
        panelCarta.add(lblImagem);

        JTextArea txtStats = new JTextArea(
            "Speed: " + carta.getSpeed() + "\n" +
            "Weight: " + carta.getWeight() + "\n" +
            "Acceleration: " + carta.getAcceleration() + "\n" +
            "Handling: " + carta.getHandling() + "\n" +
            "Drift: " + carta.getDrift() + "\n" +
            "Offroad: " + carta.getOffroad() + "\n" +
            "Mini-Turbo: " + carta.getMiniturbo()
        );
        txtStats.setBounds(10, 88, 140, 115);
        txtStats.setEditable(false);
        txtStats.setOpaque(false);
        panelCarta.add(txtStats);

        JButton btnEscolher = new JButton("Escolher");
        btnEscolher.setBounds(30, 210, 100, 25);
        btnEscolher.addActionListener(e ->
        {
            controleJogo.selecionarCarta(carta);
            dispose();
        });
        panelCarta.add(btnEscolher);

        return panelCarta;
    }
}