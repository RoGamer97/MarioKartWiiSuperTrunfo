package view;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
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
	private ViewJogo viewJogo;

	public ViewJogoMenuCarta(ViewJogo viewJogo, ControleJogo controleJogo, ControleMao controleMao, ControleCarta controleCarta, String rodadaAtual, String totalRodadas, String pontosHumano, String pontosMaquina) 
	{
		this.viewJogo = viewJogo;

		setTitle("Mario Kart Wii Super Trunfo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		
		List<Carta> listaCartas = controleMao.getCartasMao(TipoJogador.HUMANO); 
		
		int cartasPorLinha = 6;
		int totalLinhas = (int) Math.ceil((double) listaCartas.size() / cartasPorLinha);
		
		int larguraJanela = (cartasPorLinha * 175) + 40; 
		int alturaJanela = (totalLinhas * 270) + 180;
		int larguraUtil = larguraJanela - 36;
		
		setBounds(50, 50, larguraJanela, alturaJanela);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("Escolha sua carta", SwingConstants.CENTER);
		lblTitulo.setBounds(10, 11, larguraUtil, 25);
		contentPane.add(lblTitulo);

		JLabel lblRodadaTexto = new JLabel("Rodada: " + rodadaAtual + " / " + totalRodadas, SwingConstants.CENTER);
		lblRodadaTexto.setBounds(10, 42, larguraUtil, 20);
		contentPane.add(lblRodadaTexto);

		JLabel lblPontosTitulo = new JLabel("Pontos da Partida", SwingConstants.CENTER);
		lblPontosTitulo.setBounds(10, 65, larguraUtil, 20);
		contentPane.add(lblPontosTitulo);

		String placarTexto = "Humano " + pontosHumano + "  -   " + pontosMaquina + " Maquina";
		JLabel lblPlacar = new JLabel(placarTexto, SwingConstants.CENTER);
		lblPlacar.setBounds(10, 85, larguraUtil, 20);
		contentPane.add(lblPlacar);

		JPanel panelCartasContainer = new JPanel();
		panelCartasContainer.setLayout(null);

		panelCartasContainer.setBounds(10, 115, larguraUtil, alturaJanela - 160);
		contentPane.add(panelCartasContainer);

		for (int i = 0; i < listaCartas.size(); i++)
		{
			Carta carta = listaCartas.get(i);
			
			int posX = 10 + (i % cartasPorLinha) * 175;
			int posY = 10 + (i / cartasPorLinha) * 270;
			
			JPanel panelCarta = new JPanel();
			panelCarta.setBorder(new LineBorder(Color.BLACK, 2));
			panelCarta.setBounds(posX, posY, 150, 250);
			panelCarta.setLayout(null);
			
			JLabel lblNome = new JLabel(carta.getNome(), SwingConstants.CENTER);
			lblNome.setBounds(10, 5, 130, 14);
			panelCarta.add(lblNome);
			
			JLabel lblImagem = new JLabel("", SwingConstants.CENTER);
			lblImagem.setBounds(10, 23, 130, 60); 
			
			byte[] imagemBytes = carta.getImagem(); 
			
			if (imagemBytes != null && imagemBytes.length > 0) 
			{
				try 
				{
					ByteArrayInputStream bis = new ByteArrayInputStream(imagemBytes);
					Image img = ImageIO.read(bis);
					Image imgRedimensionada = img.getScaledInstance(130, 60, Image.SCALE_SMOOTH);
					lblImagem.setIcon(new ImageIcon(imgRedimensionada));
				} 
				catch (Exception e) 
				{
					lblImagem.setIcon(null);
				}
			} 
			else 
			{
				lblImagem.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
			}
			panelCarta.add(lblImagem);
			
			String statsTexto = "Speed: " + carta.getSpeed() + "\n"
					+ "Weight: " + carta.getWeight() + "\n"
					+ "Acceleration: " + carta.getAcceleration() + "\n"
					+ "Handling: " + carta.getHandling() + "\n"
					+ "Drift: " + carta.getDrift() + "\n"
					+ "Off-Road: " + carta.getOffroad() + "\n"
					+ "Mini-Turbo: " + carta.getMiniturbo();
					
			JTextArea txtStats = new JTextArea(statsTexto);
			txtStats.setBounds(15, 88, 125, 115); 
			txtStats.setEditable(false);
			txtStats.setOpaque(false);
			txtStats.setBackground(new Color(0, 0, 0, 0));
			panelCarta.add(txtStats);
			
			JButton btnEscolher = new JButton("Escolher");
			btnEscolher.setBounds(25, 215, 100, 25); 
			panelCarta.add(btnEscolher);
			
			btnEscolher.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					controleJogo.selecionarCarta(carta);
					dispose();
				}
			});

			panelCartasContainer.add(panelCarta);
		}
		
		setLocationRelativeTo(null);
	}
}