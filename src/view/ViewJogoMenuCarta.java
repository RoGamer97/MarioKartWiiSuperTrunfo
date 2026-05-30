package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
import modelo.EstadoJogo;
import modelo.Mao;
import modelo.TipoJogador;

public class ViewJogoMenuCarta extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ViewJogo viewJogo;

	public ViewJogoMenuCarta(ViewJogo viewJogo, ControleJogo controleJogo, ControleMao controleMao, ControleCarta controleCarta, String rodadaAtual, String totalRodadas, String pontosHumano, String pontosMaquina) 
	{
		this.viewJogo = viewJogo;

		setTitle("Super Mario Kart Trunfo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		
		List<Carta> listaCartas = controleMao.getCartasMao(TipoJogador.HUMANO); 
		
		int totalLinhas = (int) Math.ceil((double) listaCartas.size() / 4);
		int larguraJanela = Math.max(550, (4 * 175) + 40);
		int alturaJanela = (totalLinhas * 220) + 170;
		int larguraUtil = larguraJanela - 36;
		
		setBounds(150, 150, larguraJanela, alturaJanela);
		
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

		String pontosTexto = "Pontos Partida -> Você: " + pontosHumano + "  |  Máquina: " + pontosMaquina;
		JLabel lblPoints = new JLabel(pontosTexto, SwingConstants.CENTER);
		lblPoints.setBounds(10, 65, larguraUtil, 20);
		contentPane.add(lblPoints);

		JPanel panelCartasContainer = new JPanel();
		panelCartasContainer.setLayout(null);
		panelCartasContainer.setBounds(10, 100, larguraUtil, alturaJanela - 145);
		contentPane.add(panelCartasContainer);

		for (int i = 0; i < listaCartas.size(); i++)
		{
			Carta carta = listaCartas.get(i);
			
			int posX = 10 + (i % 4) * 175;
			int posY = 10 + (i / 4) * 220;
			
			JPanel panelCarta = new JPanel();
			panelCarta.setBorder(new LineBorder(Color.BLACK, 2));
			panelCarta.setBounds(posX, posY, 150, 200);
			panelCarta.setLayout(null);
			
			JLabel lblNome = new JLabel(carta.getNome(), SwingConstants.CENTER);
			lblNome.setBounds(10, 11, 130, 14);
			panelCarta.add(lblNome);
			
			String statsTexto = "Speed: " + carta.getSpeed() + "\n"
					+ "Weight: " + carta.getWeight() + "\n"
					+ "Acceleration: " + carta.getAcceleration() + "\n"
					+ "Handling: " + carta.getHandling() + "\n"
					+ "Drift: " + carta.getDrift() + "\n"
					+ "Off-Road: " + carta.getOffroad() + "\n"
					+ "Mini-Turbo: " + carta.getMiniturbo();
					
			JTextArea txtStats = new JTextArea(statsTexto);
			txtStats.setBounds(20, 35, 120, 115);
			txtStats.setEditable(false);
			txtStats.setOpaque(false);
			txtStats.setBackground(new Color(0, 0, 0, 0));
			panelCarta.add(txtStats);
			
			JButton btnEscolher = new JButton("Escolher");
			btnEscolher.setBounds(25, 160, 100, 25);
			panelCarta.add(btnEscolher);
			
			btnEscolher.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					Mao maoJogador = controleMao.getMaoPorTipoJogador(TipoJogador.HUMANO);
					maoJogador.setCartaEscolhida(carta);
					
					controleJogo.setEstadoJogo(EstadoJogo.CARTA_ESCOLHIDA);
					viewJogo.setIsBtnJogarEnabled(true);
	
					viewJogo.atualizarTextoCartaHumano();
					viewJogo.atualizarTextoAtributosHumano();
					
					dispose();
				}
			});

			panelCartasContainer.add(panelCarta);
		}
		
		setLocationRelativeTo(null);
	}
}