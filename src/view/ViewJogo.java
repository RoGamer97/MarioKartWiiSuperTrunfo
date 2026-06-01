package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controle.ControleBaralho;
import controle.ControleCarta;
import controle.ControleJogo;
import controle.ControleMao;
import modelo.Debug;
import modelo.EstadoJogo;
import modelo.TipoJogador;

public class ViewJogo extends JFrame 
{	
	private ViewMenuPrincipal viewMenuPrincipal;
	private ControleBaralho controleBaralho = new ControleBaralho();
	private ControleMao controleMao = new ControleMao(controleBaralho);
	private ControleCarta controleCarta = new ControleCarta(controleMao);
	private ControleJogo controleJogo = new ControleJogo(this, controleBaralho, controleCarta, controleMao);
	
	private ViewJogoDebugMenu viewJogoDebugMenu = null;
	private ViewJogoMenuCarta viewJogoMenuCarta = null; 
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JLabel labelSpeedHumano;
	private JLabel labelWeightHumano;
	private JLabel labelAccelerationHumano;
	private JLabel labelHandlingHumano;
	private JLabel labelDriftHumano;
	private JLabel labelOffroadHumano;
	private JLabel labelMTHumano;
	
	private JLabel labelSpeedMaquina;
	private JLabel labelWeightMaquina;
	private JLabel labelAccelerationMaquina;
	private JLabel labelHandlingMaquina;
	private JLabel labelDriftMaquina;
	private JLabel labelOffroadMaquina;
	private JLabel labelMTMaquina;
	
	private JLabel lblIconSpeedHumano;
	private JLabel lblIconWeightHumano;
	private JLabel lblIconAccelerationHumano;
	private JLabel lblIconHandlingHumano;
	private JLabel lblIconDriftHumano;
	private JLabel lblIconOffroadHumano;
	private JLabel lblIconMTHumano;

	private JLabel lblIconSpeedMaquina;
	private JLabel lblIconWeightMaquina;
	private JLabel lblIconAccelerationMaquina;
	private JLabel lblIconHandlingMaquina;
	private JLabel lblIconDriftMaquina;
	private JLabel lblIconOffroadMaquina;
	private JLabel lblIconMTMaquina;
	
	private JLabel textTotalRodadas;
	private JLabel textLabelRodadaAtual;
	private JLabel textFim;
	private JLabel textVencedor;
	private JButton btnJogar;
	
	private JLabel textPontosPartidaHumano;
	private JLabel textPontosPartidaMaquina;
	private JLabel textPontosRodadaHumano;
	private JLabel textPontosRodadaMaquina;
	
	private JButton btnAbandonarPartida;
	private JButton btnTrocarCarta;
	
	private JLabel textNomeCartaHumano;
	private JLabel textNomeCartaMaquina;
	private JLabel textLabelDirty;
	
	private JLabel imagemCartaHumano;
	private JLabel imagemCartaMaquina;
	
	private JLabel textEasterEgg;
	
	private JLabel crownHumano;
	private JLabel crownMaquina;
	
	private JLabel labelPontoMaisMaquina;
	private JLabel labelPontoMaisHumano;

	public ViewJogo(ViewMenuPrincipal viewMenuPrincipal, int totalRodadas, boolean mostrarCartasMaquina) 
	{
		controleJogo.setEstadoJogo(EstadoJogo.CARTA_NAO_ESCOLHIDA);
		controleJogo.setTotalRodadas(totalRodadas);
		controleJogo.setMostrarCartaMaquina(mostrarCartasMaquina);
		viewMenuPrincipal.setVisible(false);
		
		controleBaralho.setTotalCartas(totalRodadas);
		controleBaralho.prepararBaralho();
		controleMao.distribuirCartasMao(TipoJogador.HUMANO);
		controleMao.distribuirCartasMao(TipoJogador.MAQUINA);
		
		this.viewMenuPrincipal = viewMenuPrincipal;
		
		if (Debug.DEBUG_MENU_ENABLED)
		{
			viewJogoDebugMenu = new ViewJogoDebugMenu(this, controleJogo);
			viewJogoDebugMenu.setVisible(true);
		}
		
		final Font FONTE_TITULO = new Font(Font.SANS_SERIF, Font.BOLD, 14);
		final Font FONTE_ATRIBUTO = new Font(Font.SANS_SERIF, Font.PLAIN, 13);
		final Font FONTE_VALOR = new Font(Font.SANS_SERIF, Font.BOLD, 13);
		final Font FONTE_FIM = new Font(Font.SANS_SERIF, Font.BOLD, 22);
		final Font FONTE_VENCEDOR = new Font(Font.SANS_SERIF, Font.BOLD, 16);
		
		setTitle("Mario Kart Wii Super Trunfo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel textLabelRodada = new JLabel("Rodada", SwingConstants.CENTER);
		textLabelRodada.setFont(FONTE_TITULO);
		textLabelRodada.setBounds(340, 11, 120, 18);
		contentPane.add(textLabelRodada);
		
		textLabelRodadaAtual = new JLabel("1", SwingConstants.RIGHT);
		textLabelRodadaAtual.setFont(FONTE_TITULO);
		textLabelRodadaAtual.setBounds(340, 32, 46, 18);
		contentPane.add(textLabelRodadaAtual);
		
		JLabel textDash = new JLabel("/", SwingConstants.CENTER);
		textDash.setFont(FONTE_TITULO);
		textDash.setBounds(386, 32, 28, 18);
		contentPane.add(textDash);
		
		textTotalRodadas = new JLabel(Integer.toString(totalRodadas), SwingConstants.LEFT);
		textTotalRodadas.setFont(FONTE_TITULO);
		textTotalRodadas.setBounds(414, 32, 46, 18);
		contentPane.add(textTotalRodadas);
		
		textFim = new JLabel("FIM!", SwingConstants.CENTER);
		textFim.setFont(FONTE_FIM);
		textFim.setBounds(340, 175, 120, 28);
		contentPane.add(textFim);
		textFim.setVisible(false);
		
		textVencedor = new JLabel("(Tipo Jogador Vencedor) ganhou!", SwingConstants.CENTER);
		textVencedor.setFont(FONTE_VENCEDOR);
		textVencedor.setBounds(250, 210, 300, 24);
		contentPane.add(textVencedor);
		textVencedor.setVisible(false);
		
		textEasterEgg = new JLabel("(Texto de Easter Egg)", SwingConstants.CENTER);
		textEasterEgg.setFont(FONTE_ATRIBUTO);
		textEasterEgg.setBounds(280, 240, 240, 26);
		contentPane.add(textEasterEgg);
		textEasterEgg.setVisible(false);

		if (Debug.DEBUG_MENU_ENABLED)
		{
			textLabelDirty = new JLabel("(D)");
			textLabelDirty.setFont(FONTE_ATRIBUTO);
			textLabelDirty.setForeground(new Color(255, 0, 0));
			textLabelDirty.setBounds(10, 0, 30, 25);
			contentPane.add(textLabelDirty);
			textLabelDirty.setVisible(false);
		}
		
		JLabel lblTituloVoce = new JLabel("Você", SwingConstants.CENTER);
		lblTituloVoce.setFont(FONTE_TITULO);
		lblTituloVoce.setBounds(40, 15, 170, 20);
		contentPane.add(lblTituloVoce);
		
		JLabel lblTituloMaquina = new JLabel("Máquina", SwingConstants.CENTER);
		lblTituloMaquina.setFont(FONTE_TITULO);
		lblTituloMaquina.setBounds(570, 15, 170, 20);
		contentPane.add(lblTituloMaquina);

		textNomeCartaHumano = new JLabel("(Nome Carta Humano)", SwingConstants.CENTER);
		textNomeCartaHumano.setFont(FONTE_TITULO);
		textNomeCartaHumano.setBounds(40, 50, 170, 20);
		contentPane.add(textNomeCartaHumano);
		
		imagemCartaHumano = new JLabel("(Imagem Carta Humano)");
		imagemCartaHumano.setHorizontalAlignment(SwingConstants.CENTER);
		imagemCartaHumano.setBounds(50, 75, 150, 75);
		contentPane.add(imagemCartaHumano);
		
		JLabel textSpeedHumano = new JLabel("Speed");
		textSpeedHumano.setFont(FONTE_ATRIBUTO);
		textSpeedHumano.setBounds(30, 170, 85, 24);
		contentPane.add(textSpeedHumano);
		
		labelSpeedHumano = new JLabel("", SwingConstants.CENTER);
		labelSpeedHumano.setFont(FONTE_VALOR);
		labelSpeedHumano.setBounds(120, 170, 100, 24);
		contentPane.add(labelSpeedHumano);
		
		lblIconSpeedHumano = new JLabel("[S]");
		lblIconSpeedHumano.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconSpeedHumano.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblIconSpeedHumano.setBounds(230, 170, 24, 24);
		lblIconSpeedHumano.setVisible(false);
		contentPane.add(lblIconSpeedHumano);
		
		JLabel textWeightHumano = new JLabel("Weight");
		textWeightHumano.setFont(FONTE_ATRIBUTO);
		textWeightHumano.setBounds(30, 205, 85, 24);
		contentPane.add(textWeightHumano);
		
		labelWeightHumano = new JLabel("", SwingConstants.CENTER);
		labelWeightHumano.setFont(FONTE_VALOR);
		labelWeightHumano.setBounds(120, 205, 100, 24);
		contentPane.add(labelWeightHumano);
		
		lblIconWeightHumano = new JLabel("[W]");
		lblIconWeightHumano.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconWeightHumano.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblIconWeightHumano.setBounds(230, 205, 24, 24);
		lblIconWeightHumano.setVisible(false);
		contentPane.add(lblIconWeightHumano);
		
		JLabel textAccelerationHumano = new JLabel("Acceleration");
		textAccelerationHumano.setFont(FONTE_ATRIBUTO);
		textAccelerationHumano.setBounds(30, 240, 85, 24);
		contentPane.add(textAccelerationHumano);
		
		labelAccelerationHumano = new JLabel("", SwingConstants.CENTER);
		labelAccelerationHumano.setFont(FONTE_VALOR);
		labelAccelerationHumano.setBounds(120, 240, 100, 24);
		contentPane.add(labelAccelerationHumano);
		
		lblIconAccelerationHumano = new JLabel("[A]");
		lblIconAccelerationHumano.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconAccelerationHumano.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblIconAccelerationHumano.setBounds(230, 240, 24, 24);
		lblIconAccelerationHumano.setVisible(false);
		contentPane.add(lblIconAccelerationHumano);
		
		JLabel textHandlingHumano = new JLabel("Handling");
		textHandlingHumano.setFont(FONTE_ATRIBUTO);
		textHandlingHumano.setBounds(30, 275, 85, 24);
		contentPane.add(textHandlingHumano);
		
		labelHandlingHumano = new JLabel("", SwingConstants.CENTER);
		labelHandlingHumano.setFont(FONTE_VALOR);
		labelHandlingHumano.setBounds(120, 275, 100, 24);
		contentPane.add(labelHandlingHumano);
		
		lblIconHandlingHumano = new JLabel("[H]");
		lblIconHandlingHumano.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconHandlingHumano.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblIconHandlingHumano.setBounds(230, 275, 24, 24);
		lblIconHandlingHumano.setVisible(false);
		contentPane.add(lblIconHandlingHumano);
		
		JLabel textDriftHumano = new JLabel("Drift");
		textDriftHumano.setFont(FONTE_ATRIBUTO);
		textDriftHumano.setBounds(30, 310, 85, 24);
		contentPane.add(textDriftHumano);
		
		labelDriftHumano = new JLabel("", SwingConstants.CENTER);
		labelDriftHumano.setFont(FONTE_VALOR);
		labelDriftHumano.setBounds(120, 310, 100, 24);
		contentPane.add(labelDriftHumano);
		
		lblIconDriftHumano = new JLabel("[D]");
		lblIconDriftHumano.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconDriftHumano.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblIconDriftHumano.setBounds(230, 310, 24, 24);
		lblIconDriftHumano.setVisible(false);
		contentPane.add(lblIconDriftHumano);
		
		JLabel textOffRoadHumano = new JLabel("Off-Road");
		textOffRoadHumano.setFont(FONTE_ATRIBUTO);
		textOffRoadHumano.setBounds(30, 345, 85, 24);
		contentPane.add(textOffRoadHumano);
		
		labelOffroadHumano = new JLabel("", SwingConstants.CENTER);
		labelOffroadHumano.setFont(FONTE_VALOR);
		labelOffroadHumano.setBounds(120, 345, 100, 24);
		contentPane.add(labelOffroadHumano);
		
		lblIconOffroadHumano = new JLabel("[O]");
		lblIconOffroadHumano.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconOffroadHumano.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblIconOffroadHumano.setBounds(230, 345, 24, 24);
		lblIconOffroadHumano.setVisible(false);
		contentPane.add(lblIconOffroadHumano);
		
		JLabel textMTHumano = new JLabel("Mini-Turbo");
		textMTHumano.setFont(FONTE_ATRIBUTO);
		textMTHumano.setBounds(30, 380, 85, 24);
		contentPane.add(textMTHumano);
		
		labelMTHumano = new JLabel("", SwingConstants.CENTER);
		labelMTHumano.setFont(FONTE_VALOR);
		labelMTHumano.setBounds(120, 380, 100, 24);
		contentPane.add(labelMTHumano);
		
		lblIconMTHumano = new JLabel("[M]");
		lblIconMTHumano.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconMTHumano.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblIconMTHumano.setBounds(230, 380, 24, 24);
		lblIconMTHumano.setVisible(false);
		contentPane.add(lblIconMTHumano);
		
		crownHumano = new JLabel("");
		crownHumano.setHorizontalAlignment(SwingConstants.CENTER);
		crownHumano.setBounds(78, 430, 60, 45);
		contentPane.add(crownHumano);
		
		textNomeCartaMaquina = new JLabel("(Nome Carta Maquina)", SwingConstants.CENTER);
		textNomeCartaMaquina.setFont(FONTE_TITULO);
		textNomeCartaMaquina.setBounds(570, 50, 170, 20);
		contentPane.add(textNomeCartaMaquina);
		
		imagemCartaMaquina = new JLabel("(Imagem Carta Maquina)");
		imagemCartaMaquina.setHorizontalAlignment(SwingConstants.CENTER);
		imagemCartaMaquina.setBounds(580, 75, 150, 75);
		contentPane.add(imagemCartaMaquina);
		
		lblIconSpeedMaquina = new JLabel("[S]");
		lblIconSpeedMaquina.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconSpeedMaquina.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblIconSpeedMaquina.setBounds(530, 170, 24, 24);
		lblIconSpeedMaquina.setVisible(false);
		contentPane.add(lblIconSpeedMaquina);

		labelSpeedMaquina = new JLabel("", SwingConstants.CENTER);
		labelSpeedMaquina.setFont(FONTE_VALOR);
		labelSpeedMaquina.setBounds(564, 170, 100, 24);
		contentPane.add(labelSpeedMaquina);

		JLabel textSpeedMaquina = new JLabel("Speed");
		textSpeedMaquina.setFont(FONTE_ATRIBUTO);
		textSpeedMaquina.setBounds(674, 170, 85, 24);
		contentPane.add(textSpeedMaquina);
		
		lblIconWeightMaquina = new JLabel("[W]");
		lblIconWeightMaquina.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconWeightMaquina.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblIconWeightMaquina.setBounds(530, 205, 24, 24);
		lblIconWeightMaquina.setVisible(false);
		contentPane.add(lblIconWeightMaquina);

		labelWeightMaquina = new JLabel("", SwingConstants.CENTER);
		labelWeightMaquina.setFont(FONTE_VALOR);
		labelWeightMaquina.setBounds(564, 205, 100, 24);
		contentPane.add(labelWeightMaquina);

		JLabel textWeightMaquina = new JLabel("Weight");
		textWeightMaquina.setFont(FONTE_ATRIBUTO);
		textWeightMaquina.setBounds(674, 205, 85, 24);
		contentPane.add(textWeightMaquina);
		
		lblIconAccelerationMaquina = new JLabel("[A]");
		lblIconAccelerationMaquina.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconAccelerationMaquina.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblIconAccelerationMaquina.setBounds(530, 240, 24, 24);
		lblIconAccelerationMaquina.setVisible(false);
		contentPane.add(lblIconAccelerationMaquina);

		labelAccelerationMaquina = new JLabel("", SwingConstants.CENTER);
		labelAccelerationMaquina.setFont(FONTE_VALOR);
		labelAccelerationMaquina.setBounds(564, 240, 100, 24);
		contentPane.add(labelAccelerationMaquina);

		JLabel textAccelerationMaquina = new JLabel("Acceleration");
		textAccelerationMaquina.setFont(FONTE_ATRIBUTO);
		textAccelerationMaquina.setBounds(674, 240, 85, 24);
		contentPane.add(textAccelerationMaquina);
		
		lblIconHandlingMaquina = new JLabel("[H]");
		lblIconHandlingMaquina.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconHandlingMaquina.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblIconHandlingMaquina.setBounds(530, 275, 24, 24);
		lblIconHandlingMaquina.setVisible(false);
		contentPane.add(lblIconHandlingMaquina);

		labelHandlingMaquina = new JLabel("", SwingConstants.CENTER);
		labelHandlingMaquina.setFont(FONTE_VALOR);
		labelHandlingMaquina.setBounds(564, 275, 100, 24);
		contentPane.add(labelHandlingMaquina);

		JLabel textHandlingMaquina = new JLabel("Handling");
		textHandlingMaquina.setFont(FONTE_ATRIBUTO);
		textHandlingMaquina.setBounds(674, 275, 85, 24);
		contentPane.add(textHandlingMaquina);
		
		lblIconDriftMaquina = new JLabel("[D]");
		lblIconDriftMaquina.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconDriftMaquina.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblIconDriftMaquina.setBounds(530, 310, 24, 24);
		lblIconDriftMaquina.setVisible(false);
		contentPane.add(lblIconDriftMaquina);

		labelDriftMaquina = new JLabel("", SwingConstants.CENTER);
		labelDriftMaquina.setFont(FONTE_VALOR);
		labelDriftMaquina.setBounds(564, 310, 100, 24);
		contentPane.add(labelDriftMaquina);

		JLabel textDriftMaquina = new JLabel("Drift");
		textDriftMaquina.setFont(FONTE_ATRIBUTO);
		textDriftMaquina.setBounds(674, 310, 85, 24);
		contentPane.add(textDriftMaquina);
		
		lblIconOffroadMaquina = new JLabel("[O]");
		lblIconOffroadMaquina.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconOffroadMaquina.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblIconOffroadMaquina.setBounds(530, 345, 24, 24);
		lblIconOffroadMaquina.setVisible(false);
		contentPane.add(lblIconOffroadMaquina);

		labelOffroadMaquina = new JLabel("", SwingConstants.CENTER);
		labelOffroadMaquina.setFont(FONTE_VALOR);
		labelOffroadMaquina.setBounds(564, 345, 100, 24);
		contentPane.add(labelOffroadMaquina);

		JLabel textOffroadMaquina = new JLabel("Off-Road");
		textOffroadMaquina.setFont(FONTE_ATRIBUTO);
		textOffroadMaquina.setBounds(674, 345, 85, 24);
		contentPane.add(textOffroadMaquina);
		
		lblIconMTMaquina = new JLabel("[M]");
		lblIconMTMaquina.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconMTMaquina.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblIconMTMaquina.setBounds(530, 380, 24, 24);
		lblIconMTMaquina.setVisible(false);
		contentPane.add(lblIconMTMaquina);

		labelMTMaquina = new JLabel("", SwingConstants.CENTER);
		labelMTMaquina.setFont(FONTE_VALOR);
		labelMTMaquina.setBounds(564, 380, 100, 24);
		contentPane.add(labelMTMaquina);

		JLabel textMTMaquina = new JLabel("Mini-Turbo");
		textMTMaquina.setFont(FONTE_ATRIBUTO);
		textMTMaquina.setBounds(674, 380, 85, 24);
		contentPane.add(textMTMaquina);

		crownMaquina = new JLabel("");
		crownMaquina.setHorizontalAlignment(SwingConstants.CENTER);
		crownMaquina.setBounds(643, 430, 60, 45);
		contentPane.add(crownMaquina);

		JLabel lblPlacarGeral = new JLabel("PONTOS", SwingConstants.CENTER);
		lblPlacarGeral.setFont(FONTE_TITULO);
		lblPlacarGeral.setBounds(340, 410, 120, 20);
		contentPane.add(lblPlacarGeral);
		
		JLabel textLabelPR = new JLabel("Rodada", SwingConstants.CENTER);
		textLabelPR.setFont(FONTE_ATRIBUTO);
		textLabelPR.setBounds(340, 432, 120, 20);
		contentPane.add(textLabelPR);
		
		textPontosRodadaHumano = new JLabel("0", SwingConstants.RIGHT);
		textPontosRodadaHumano.setFont(FONTE_TITULO);
		textPontosRodadaHumano.setBounds(275, 432, 55, 20);
		contentPane.add(textPontosRodadaHumano);
		
		textPontosRodadaMaquina = new JLabel("0", SwingConstants.LEFT);
		textPontosRodadaMaquina.setFont(FONTE_TITULO);
		textPontosRodadaMaquina.setBounds(470, 432, 55, 20);
		contentPane.add(textPontosRodadaMaquina);
		
		JLabel textLabelPP = new JLabel("Partida", SwingConstants.CENTER);
		textLabelPP.setFont(FONTE_ATRIBUTO);
		textLabelPP.setBounds(340, 457, 120, 20);
		contentPane.add(textLabelPP);
		
		textPontosPartidaHumano = new JLabel("0", SwingConstants.RIGHT);
		textPontosPartidaHumano.setFont(FONTE_TITULO);
		textPontosPartidaHumano.setBounds(275, 457, 55, 20);
		contentPane.add(textPontosPartidaHumano);
		
		textPontosPartidaMaquina = new JLabel("0", SwingConstants.LEFT);
		textPontosPartidaMaquina.setFont(FONTE_TITULO);
		textPontosPartidaMaquina.setBounds(470, 457, 55, 20);
		contentPane.add(textPontosPartidaMaquina);

		btnTrocarCarta = new JButton("Trocar Carta");
		btnTrocarCarta.setFont(FONTE_ATRIBUTO);
		btnTrocarCarta.setBounds(30, 495, 160, 45);
		contentPane.add(btnTrocarCarta);
		
		labelPontoMaisHumano = new JLabel("");
		labelPontoMaisHumano.setHorizontalAlignment(SwingConstants.CENTER);
		labelPontoMaisHumano.setBounds(201, 430, 75, 55);
		contentPane.add(labelPontoMaisHumano);
		
		labelPontoMaisMaquina = new JLabel("");
		labelPontoMaisMaquina.setHorizontalAlignment(SwingConstants.CENTER);
		labelPontoMaisMaquina.setBounds(516, 430, 75, 55);
		contentPane.add(labelPontoMaisMaquina);
		
		setCoroaHumanoIsVisible(false);
		setCoroaMaquinaIsVisible(false);
		
		setImagemLabel(labelPontoMaisHumano, "/imagens/+1b.png");
		setImagemLabel(labelPontoMaisMaquina, "/imagens/+1r.png");
		
		setIsBtnTrocarCartaEnabled(false);
		limparElementosRodada();
		
		btnTrocarCarta.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				abrirMenuSelecaoCarta();
			}
		});
		
		btnJogar = new JButton("Jogar"); 
		btnJogar.setFont(FONTE_TITULO);
		btnJogar.setBounds(280, 495, 224, 45);
		contentPane.add(btnJogar);
		btnJogar.setEnabled(false);
		
		btnJogar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				controleJogo.processarJogo();
			}
		});
		
		btnAbandonarPartida = new JButton("Abandonar Partida");
		btnAbandonarPartida.setFont(FONTE_ATRIBUTO);
		btnAbandonarPartida.setBounds(594, 495, 160, 45);
		contentPane.add(btnAbandonarPartida);
		
		btnAbandonarPartida.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				int opcaoSelecionada = JOptionPane.showConfirmDialog(ViewJogo.this, 
						"Tem certeza que quer abandonar a partida? Você irá voltar ao menu principal", "Abandonar partida?", JOptionPane.YES_NO_OPTION);
				if (opcaoSelecionada == JOptionPane.YES_OPTION) 
				{
					voltarMenuTitulo();
				}
			}
		});
		
		textNomeCartaHumano.setText("");
		textNomeCartaMaquina.setText("");
		
		setImagemLabel(crownHumano, "/imagens/crown.png");
		setImagemLabel(crownMaquina, "/imagens/crown.png");
		
		setLocationRelativeTo(null);
	}
	
	public void atualizarImagemCarta(byte[] imagem, JLabel labelCarta)
	{
	    try
	    {
	        if (imagem == null || imagem.length <= 0)
	        {
	            throw new Exception(); // joga excecão pra cair no exception, pra settar a imagem hatena (ponto de interrogação)
	        }

	        ByteArrayInputStream bis = new ByteArrayInputStream(imagem);
	        Image img = ImageIO.read(bis);
	        Image imgRedimensionada = img.getScaledInstance(labelCarta.getWidth(), labelCarta.getHeight(), Image.SCALE_SMOOTH);
	        labelCarta.setIcon(new ImageIcon(imgRedimensionada));
	        labelCarta.setText("");
	    }
	    catch (Exception e)
	    {
	        try 
	        {
	            Image img = ImageIO.read(getClass().getResource("/imagens/hatena.png"));
	            Image imgRedimensionada = img.getScaledInstance(labelCarta.getWidth(), labelCarta.getHeight(), Image.SCALE_DEFAULT);
	            labelCarta.setIcon(new ImageIcon(imgRedimensionada));
	            labelCarta.setText("");
	        } 
	        catch (Exception ex) 
	        {
	            labelCarta.setIcon(null);
	        }
	    }
	}

	public void atualizarImagemCartaHumano(byte[] imagem)
	{
	    atualizarImagemCarta(imagem, imagemCartaHumano);
	}

	public void atualizarImagemCartaMaquina(byte[] imagem)
	{
	    atualizarImagemCarta(imagem, imagemCartaMaquina);
	}

	public void limparElementosRodada()
	{
		textNomeCartaHumano.setText("");
		textNomeCartaMaquina.setText("");
		
		labelSpeedHumano.setText("");
		labelWeightHumano.setText("");
		labelAccelerationHumano.setText("");
		labelHandlingHumano.setText("");
		labelDriftHumano.setText("");
		labelOffroadHumano.setText("");
		labelMTHumano.setText("");
		
		labelSpeedMaquina.setText("");
		labelWeightMaquina.setText("");
		labelAccelerationMaquina.setText("");
		labelHandlingMaquina.setText("");
		labelDriftMaquina.setText("");
		labelOffroadMaquina.setText("");
		labelMTMaquina.setText("");
		
		atualizarImagemCartaHumano(null);
		imagemCartaHumano.setText("");
		
		atualizarImagemCartaMaquina(null);
		imagemCartaMaquina.setText("");
		
		textEasterEgg.setVisible(false);
		
		setImagemComparacaoAtribIsVisible(false);
		
		labelPontoMaisHumano.setVisible(false);
		labelPontoMaisMaquina.setVisible(false);
	}

	public void abrirMenuSelecaoCarta() 
	{
		setVisible(true);
		String rodadaAtual = textLabelRodadaAtual.getText();
		String totalRodadas = textTotalRodadas.getText();
		String ptsHumano = textPontosPartidaHumano.getText();
		String ptsMaquina = textPontosPartidaMaquina.getText();
		
		viewJogoMenuCarta = new ViewJogoMenuCarta(this, controleJogo, controleMao, controleCarta, rodadaAtual, totalRodadas, ptsHumano, ptsMaquina);
		viewJogoMenuCarta.setVisible(true); 
		setIsBtnTrocarCartaEnabled(false);
	}
	
	public void atualizarElementosRodada()
	{
		atualizarTextoCartaMaquina();
		atualizarTextoAtributosMaquina();
		atualizarImagemCartaMaquina(controleCarta.getImagem(TipoJogador.MAQUINA));
		atualizarTextoRodada();
		atualizarTextoPontos();
		setImagemComparacaoAtribIsVisible(true);
	}
	
	public void mostrarElementosFimPartida()
	{
		textFim.setVisible(true);
		textVencedor.setText(controleJogo.getStringVencedor() + " ganhou!");
		textVencedor.setVisible(true);
		btnAbandonarPartida.setVisible(false);
		btnJogar.setText("Voltar ao menu principal");
		JOptionPane.showMessageDialog(null, controleJogo.getStringVencedor() + " venceu!", "Fim!", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void atualizarTextoRodada()
	{
		textLabelRodadaAtual.setText(Integer.toString(controleJogo.getRodadaAtual()));
	}
	
	public void voltarMenuTitulo()
	{
		if (viewJogoMenuCarta != null) viewJogoMenuCarta.setVisible(false);
		viewMenuPrincipal.setVisible(true);
	    this.setVisible(false);
	    
	    if (Debug.DEBUG_MENU_ENABLED && viewJogoDebugMenu != null)
	    {
	    	viewJogoDebugMenu.setVisible(false);
	    }
	}
	
	public void atualizarTextoPontos() 
	{
		textPontosPartidaHumano.setText(Integer.toString(controleJogo.getPontosPartida(TipoJogador.HUMANO)));
		textPontosPartidaMaquina.setText(Integer.toString(controleJogo.getPontosPartida(TipoJogador.MAQUINA)));
			
		textPontosRodadaHumano.setText(Integer.toString(controleJogo.getPontosRodada(TipoJogador.HUMANO)));
		textPontosRodadaMaquina.setText(Integer.toString(controleJogo.getPontosRodada(TipoJogador.MAQUINA)));
	}
	
	public void atualizarTextoCartaHumano()
	{
		textNomeCartaHumano.setText(controleCarta.getNome(TipoJogador.HUMANO));
		textNomeCartaHumano.setVisible(true);
	}
	
	public void atualizarTextoCartaMaquina()
	{
		textNomeCartaMaquina.setText(controleCarta.getNome(TipoJogador.MAQUINA));
		textNomeCartaMaquina.setVisible(true);
	}

	public void atualizarTextoAtributosHumano()
	{
		labelSpeedHumano.setText(Float.toString(controleCarta.getSpeed(TipoJogador.HUMANO)));
		labelWeightHumano.setText(Float.toString(controleCarta.getWeight(TipoJogador.HUMANO)));
		labelAccelerationHumano.setText(Float.toString(controleCarta.getAcceleration(TipoJogador.HUMANO)));
		labelHandlingHumano.setText(Float.toString(controleCarta.getHandling(TipoJogador.HUMANO)));
		labelDriftHumano.setText(Float.toString(controleCarta.getDrift(TipoJogador.HUMANO)));
		labelOffroadHumano.setText(Float.toString(controleCarta.getOffroad(TipoJogador.HUMANO)));
		labelMTHumano.setText(Float.toString(controleCarta.getMiniturbo(TipoJogador.HUMANO)));
	}
	
	public void atualizarTextoAtributosMaquina()
	{
		labelSpeedMaquina.setText(Float.toString(controleCarta.getSpeed(TipoJogador.MAQUINA)));
		labelWeightMaquina.setText(Float.toString(controleCarta.getWeight(TipoJogador.MAQUINA)));
		labelAccelerationMaquina.setText(Float.toString(controleCarta.getAcceleration(TipoJogador.MAQUINA)));
		labelHandlingMaquina.setText(Float.toString(controleCarta.getHandling(TipoJogador.MAQUINA)));
		labelDriftMaquina.setText(Float.toString(controleCarta.getDrift(TipoJogador.MAQUINA)));
		labelOffroadMaquina.setText(Float.toString(controleCarta.getOffroad(TipoJogador.MAQUINA)));
		labelMTMaquina.setText(Float.toString(controleCarta.getMiniturbo(TipoJogador.MAQUINA)));
	}
	
	public void setImagemComparacaoAtribIsVisible(boolean isVisible)
	{
		lblIconSpeedHumano.setVisible(isVisible);
		lblIconSpeedMaquina.setVisible(isVisible);
		lblIconWeightHumano.setVisible(isVisible);
		lblIconWeightMaquina.setVisible(isVisible);
		lblIconAccelerationHumano.setVisible(isVisible);
		lblIconAccelerationMaquina.setVisible(isVisible);
		lblIconHandlingHumano.setVisible(isVisible);
		lblIconHandlingMaquina.setVisible(isVisible);
		lblIconDriftHumano.setVisible(isVisible);
		lblIconDriftMaquina.setVisible(isVisible);
		lblIconOffroadHumano.setVisible(isVisible);
		lblIconOffroadMaquina.setVisible(isVisible);
		lblIconMTHumano.setVisible(isVisible);
		lblIconMTMaquina.setVisible(isVisible);
	}
	
	public void setNomeCartaHumanoVisibility(boolean isVisible)
	{
		textNomeCartaHumano.setVisible(isVisible);
	}
	
	public void setNomeCartaMaquinaVisibility(boolean isVisible)
	{
		textNomeCartaMaquina.setVisible(isVisible);
	}
	
	public void setTextoBotaoJogar()
	{
		btnJogar.setText("Jogar");
	}
	
	public void setTextoBotaoNenhumaCarta()
	{
		btnJogar.setText("Selecione uma carta");
	}
	
	public void setTextoBotaoProximaRodada()
	{
		btnJogar.setText("Próxima Rodada");
	}
	
	public void setTextoBotaoFinalizarPartida()
	{
		btnJogar.setText("Finalizar Partida");
	}
	
	public void setTextoTotalRodadasDesempate()
	{
		textTotalRodadas.setText(controleJogo.getTotalRodadas() + " (Desempate)");
	}
	
	public void setIsBtnTrocarCartaEnabled(boolean isEnabled)
	{
		btnTrocarCarta.setEnabled(isEnabled);
	}
	
	public void setIsBtnJogarEnabled(boolean isEnabled)
	{
		btnJogar.setEnabled(isEnabled);
	}
	
	public void mostrarAvisoDesempate()
	{
		JOptionPane.showMessageDialog(null, "Por causa do empate na pontuação final, haverá mais uma rodada com cartas distribuidas do baralho", "Empate!", JOptionPane.INFORMATION_MESSAGE);
	}
		
	public void mostrarTextoEasterEggBumpfest()
	{
		textEasterEgg.setVisible(true);
		textEasterEgg.setText("Blast off to Quacker Island!!");
		
		if (Debug.DEBUG_PRINTS_ENABLED)
		{
			System.out.println("[ViewJogo] Easter Egg bumpfest executado!");
		}
	}
	
	public void displayDebugMark()
	{
		if (textLabelDirty != null) textLabelDirty.setVisible(true);
	}

	private void setImagemLabel(JLabel label, String caminhoImagem)
	{
		if (caminhoImagem == null || caminhoImagem.isEmpty()) 
		{
			return;
		}
		
		try 
		{
			Image img = ImageIO.read(getClass().getResource(caminhoImagem));
			Image imgRedimensionada = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
			label.setIcon(new ImageIcon(imgRedimensionada));
			label.setText("");
		} 
		catch (Exception e) 
		{
		}
	}

	public void setImagemComparacaoSpeed(String caminhoHumano, String caminhoMaquina) 
	{
		setImagemLabel(lblIconSpeedHumano, caminhoHumano);
		setImagemLabel(lblIconSpeedMaquina, caminhoMaquina);
	}

	public void setImagemComparacaoWeight(String caminhoHumano, String caminhoMaquina) 
	{
		setImagemLabel(lblIconWeightHumano, caminhoHumano);
		setImagemLabel(lblIconWeightMaquina, caminhoMaquina);
	}

	public void setImagemComparacaoAccel(String caminhoHumano, String caminhoMaquina) 
	{
		setImagemLabel(lblIconAccelerationHumano, caminhoHumano);
		setImagemLabel(lblIconAccelerationMaquina, caminhoMaquina);
	}

	public void setImagemComparacaoHandling(String caminhoHumano, String caminhoMaquina) 
	{
		setImagemLabel(lblIconHandlingHumano, caminhoHumano);
		setImagemLabel(lblIconHandlingMaquina, caminhoMaquina);
	}

	public void setImagemComparacaoDrift(String caminhoHumano, String caminhoMaquina) 
	{
		setImagemLabel(lblIconDriftHumano, caminhoHumano);
		setImagemLabel(lblIconDriftMaquina, caminhoMaquina);
	}

	public void setImagemComparacaoOffroad(String caminhoHumano, String caminhoMaquina) 
	{
		setImagemLabel(lblIconOffroadHumano, caminhoHumano);
		setImagemLabel(lblIconOffroadMaquina, caminhoMaquina);
	}

	public void setImagemComparacaoMT(String caminhoHumano, String caminhoMaquina) 
	{
		setImagemLabel(lblIconMTHumano, caminhoHumano);
		setImagemLabel(lblIconMTMaquina, caminhoMaquina);
	}
	
	public void setCoroaHumanoIsVisible(boolean isVisible)
	{
		crownHumano.setVisible(isVisible);
	}
	
	public void setCoroaMaquinaIsVisible(boolean isVisible)
	{
		crownMaquina.setVisible(isVisible);
	}
	
	public void mostrarIncrementoPontoHumano()
	{
		labelPontoMaisHumano.setVisible(true);
		labelPontoMaisMaquina.setVisible(false);
	}
	
	public void mostrarIncrementoPontoMaquina()
	{
		labelPontoMaisHumano.setVisible(false);
		labelPontoMaisMaquina.setVisible(true);
	}
}