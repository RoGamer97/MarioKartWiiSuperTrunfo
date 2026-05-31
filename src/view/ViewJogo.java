package view;

import java.awt.Color;
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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controle.ControleBaralho;
import controle.ControleCarta;
import controle.ControleJogo;
import controle.ControleMao;
import modelo.Carta;
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
	private ViewJogoMenuCarta viewJogoMenuCarta = null; // DECLARADO COMO ATRIBUTO GLOBAL AQUI
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldSpeedHumano;
	private JTextField textFieldWeightHumano;
	private JTextField textFieldAccelerationHumano;
	private JTextField textFieldHandlingHumano;
	private JTextField textFieldDriftHumano;
	private JTextField textFieldOffroadHumano;
	private JTextField textFieldMTHumano;
	private JTextField textFieldSpeedMaquina;
	private JTextField textFieldWeightMaquina;
	private JTextField textFieldAccelerationMaquina;
	private JTextField textFieldHandlingMaquina;
	private JTextField textFieldDriftMaquina;
	private JTextField textFieldOffroadMaquina;
	private JTextField textFieldMTMaquina;
	
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
		
		setTitle("Mario Kart Wii Super Trunfo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel textSpeedHumano = new JLabel("Speed");
		textSpeedHumano.setBounds(30, 65, 46, 14);
		contentPane.add(textSpeedHumano);
		
		textFieldSpeedHumano = new JTextField();
		textFieldSpeedHumano.setBounds(86, 62, 86, 20);
		contentPane.add(textFieldSpeedHumano);
		textFieldSpeedHumano.setColumns(10);
		
		JLabel textWeightHumano = new JLabel("Weight");
		textWeightHumano.setBounds(30, 102, 46, 14);
		contentPane.add(textWeightHumano);
		
		textFieldWeightHumano = new JTextField();
		textFieldWeightHumano.setColumns(10);
		textFieldWeightHumano.setBounds(86, 99, 86, 20);
		contentPane.add(textFieldWeightHumano);
		
		JLabel textAccelerationHumano = new JLabel("Acceleration");
		textAccelerationHumano.setBounds(10, 147, 66, 14);
		contentPane.add(textAccelerationHumano);
		
		textFieldAccelerationHumano = new JTextField();
		textFieldAccelerationHumano.setColumns(10);
		textFieldAccelerationHumano.setBounds(86, 144, 86, 20);
		contentPane.add(textFieldAccelerationHumano);
		
		JLabel textHandlingHumano = new JLabel("Handling");
		textHandlingHumano.setBounds(10, 205, 66, 14);
		contentPane.add(textHandlingHumano);
		
		textFieldHandlingHumano = new JTextField();
		textFieldHandlingHumano.setColumns(10);
		textFieldHandlingHumano.setBounds(86, 199, 86, 20);
		contentPane.add(textFieldHandlingHumano);
		
		JLabel textDriftHumano = new JLabel("Drift");
		textDriftHumano.setBounds(10, 251, 66, 14);
		contentPane.add(textDriftHumano);
		
		textFieldDriftHumano = new JTextField();
		textFieldDriftHumano.setColumns(10);
		textFieldDriftHumano.setBounds(86, 248, 86, 20);
		contentPane.add(textFieldDriftHumano);
		
		JLabel textOffRoadHumano = new JLabel("Off-Road");
		textOffRoadHumano.setBounds(10, 314, 66, 14);
		contentPane.add(textOffRoadHumano);
		
		textFieldOffroadHumano = new JTextField();
		textFieldOffroadHumano.setColumns(10);
		textFieldOffroadHumano.setBounds(86, 311, 86, 20);
		contentPane.add(textFieldOffroadHumano);
		
		JLabel textMTHumano = new JLabel("Mini-Turbo");
		textMTHumano.setBounds(10, 364, 66, 14);
		contentPane.add(textMTHumano);
		
		textFieldMTHumano = new JTextField();
		textFieldMTHumano.setColumns(10);
		textFieldMTHumano.setBounds(86, 361, 86, 20);
		contentPane.add(textFieldMTHumano);
		
		JLabel textSpeedMaquina = new JLabel("Speed");
		textSpeedMaquina.setBounds(438, 65, 46, 14);
		contentPane.add(textSpeedMaquina);
		
		textFieldSpeedMaquina = new JTextField();
		textFieldSpeedMaquina.setColumns(10);
		textFieldSpeedMaquina.setBounds(346, 62, 86, 20);
		contentPane.add(textFieldSpeedMaquina);
		
		JLabel textWeightMaquina = new JLabel("Weight");
		textWeightMaquina.setBounds(438, 102, 46, 14);
		contentPane.add(textWeightMaquina);
		
		textFieldWeightMaquina = new JTextField();
		textFieldWeightMaquina.setColumns(10);
		textFieldWeightMaquina.setBounds(346, 99, 86, 20);
		contentPane.add(textFieldWeightMaquina);
		
		JLabel textAccelerationMaquina = new JLabel("Acceleration");
		textAccelerationMaquina.setBounds(438, 147, 66, 14);
		contentPane.add(textAccelerationMaquina);
		
		textFieldAccelerationMaquina = new JTextField();
		textFieldAccelerationMaquina.setColumns(10);
		textFieldAccelerationMaquina.setBounds(346, 144, 86, 20);
		contentPane.add(textFieldAccelerationMaquina);
		
		JLabel textHandlingMaquina = new JLabel("Handling");
		textHandlingMaquina.setBounds(438, 205, 66, 14);
		contentPane.add(textHandlingMaquina);
		
		textFieldHandlingMaquina = new JTextField();
		textFieldHandlingMaquina.setColumns(10);
		textFieldHandlingMaquina.setBounds(346, 199, 86, 20);
		contentPane.add(textFieldHandlingMaquina);
		
		JLabel textDriftMaquina = new JLabel("Drift");
		textDriftMaquina.setBounds(438, 248, 66, 14);
		contentPane.add(textDriftMaquina);
		
		textFieldDriftMaquina = new JTextField();
		textFieldDriftMaquina.setColumns(10);
		textFieldDriftMaquina.setBounds(346, 245, 86, 20);
		contentPane.add(textFieldDriftMaquina);
		
		JLabel textOffroadMaquina = new JLabel("Off-Road");
		textOffroadMaquina.setBounds(438, 311, 66, 14);
		contentPane.add(textOffroadMaquina);
		
		textFieldOffroadMaquina = new JTextField();
		textFieldOffroadMaquina.setColumns(10);
		textFieldOffroadMaquina.setBounds(346, 308, 86, 20);
		contentPane.add(textFieldOffroadMaquina);
		
		JLabel textMTMaquina = new JLabel("Mini-Turbo");
		textMTMaquina.setBounds(438, 376, 66, 14);
		contentPane.add(textMTMaquina);
		
		textFieldMTMaquina = new JTextField();
		textFieldMTMaquina.setColumns(10);
		textFieldMTMaquina.setBounds(346, 373, 86, 20);
		contentPane.add(textFieldMTMaquina);
		
		JLabel textLabelRodada = new JLabel("Rodada");
		textLabelRodada.setBounds(225, 11, 116, 14);
		contentPane.add(textLabelRodada);
		
		textLabelRodadaAtual = new JLabel("1");
		textLabelRodadaAtual.setBounds(225, 36, 46, 14);
		contentPane.add(textLabelRodadaAtual);
		
		textTotalRodadas = new JLabel(Integer.toString(totalRodadas));
		textTotalRodadas.setBounds(256, 36, 46, 14);
		contentPane.add(textTotalRodadas);
		
		JLabel textDash = new JLabel("/");
		textDash.setBounds(246, 36, 46, 14);
		contentPane.add(textDash);
		
		textFim = new JLabel("FIM!");
		textFim.setBounds(235, 224, 46, 14);
		contentPane.add(textFim);
		textFim.setVisible(false);
		
		if (Debug.DEBUG_MENU_ENABLED)
		{
			textLabelDirty = new JLabel("(D)");
			textLabelDirty.setForeground(new Color(255, 0, 0));
			textLabelDirty.setBounds(10, 0, 30, 25);
			contentPane.add(textLabelDirty);
			textLabelDirty.setVisible(false);
		}
	
		btnJogar = new JButton("Selecione uma carta"); 
		btnJogar.setBounds(418, 581, 221, 78);
		contentPane.add(btnJogar);
		btnJogar.setEnabled(false);
		
		btnJogar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				controleJogo.processarJogo();
			}
		});
		
		textVencedor = new JLabel("Vencedor: (TipoResultado)");
		textVencedor.setBounds(190, 399, 128, 14);
		contentPane.add(textVencedor);
		textVencedor.setVisible(false);
		
		btnAbandonarPartida = new JButton("Abandonar Partida");
		btnAbandonarPartida.setBounds(25, 432, 105, 20);
		contentPane.add(btnAbandonarPartida);
		
		btnTrocarCarta = new JButton("Trocar");
		btnTrocarCarta.setBounds(57, 36, 115, 20);
		contentPane.add(btnTrocarCarta);
		setIsBtnTrocarCartaEnabled(false);
		
		btnTrocarCarta.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				abrirMenuSelecaoCarta();
			}
		});
		
		JLabel textPontosRodada = new JLabel("Pontos Rodada");
		textPontosRodada.setBounds(207, 308, 120, 26);
		contentPane.add(textPontosRodada);
		
		JLabel textVoce = new JLabel("Você");
		textVoce.setBounds(172, 373, 74, 20);
		contentPane.add(textVoce);
		
		JLabel textMaquina = new JLabel("Maquina");
		textMaquina.setBounds(300, 333, 80, 14);
		contentPane.add(textMaquina);
		
		JLabel textVoce1 = new JLabel("Você");
		textVoce1.setBounds(172, 330, 74, 20);
		contentPane.add(textVoce1);
		
		JLabel textMaquina1 = new JLabel("Maquina");
		textMaquina1.setBounds(300, 376, 80, 14);
		contentPane.add(textMaquina1);
		
		JLabel textPontosPartida = new JLabel("Pontos Partida");
		textPontosPartida.setBounds(217, 345, 120, 26);
		contentPane.add(textPontosPartida);
		
		textPontosRodadaHumano = new JLabel("0");
		textPontosRodadaHumano.setBounds(200, 333, 46, 14);
		contentPane.add(textPontosRodadaHumano);
		
		textPontosRodadaMaquina = new JLabel("0");
		textPontosRodadaMaquina.setBounds(272, 333, 46, 14);
		contentPane.add(textPontosRodadaMaquina);
		
		textPontosPartidaHumano = new JLabel("0");
		textPontosPartidaHumano.setBounds(200, 376, 46, 14);
		contentPane.add(textPontosPartidaHumano);
		
		textPontosPartidaMaquina = new JLabel("0");
		textPontosPartidaMaquina.setBounds(272, 376, 46, 14);
		contentPane.add(textPontosPartidaMaquina);
		
		textNomeCartaHumano = new JLabel("(Nome Carta Humano)");
		textNomeCartaHumano.setBounds(30, 11, 152, 14);
		contentPane.add(textNomeCartaHumano);
		textNomeCartaHumano.setVisible(false);
		
		textNomeCartaMaquina = new JLabel("(Nome Carta Maquina)");
		textNomeCartaMaquina.setBounds(332, 11, 152, 14);
		contentPane.add(textNomeCartaMaquina);
		textNomeCartaMaquina.setVisible(false);
		
		imagemCartaHumano = new JLabel("(Imagem Carta Humano)");
		imagemCartaHumano.setBounds(200, 147, 102, 51);
		imagemCartaHumano.setIcon(null);

		contentPane.add(imagemCartaHumano);
		
		imagemCartaMaquina = new JLabel("(Imagem Carta Maquina)");
		imagemCartaMaquina.setBounds(500, 147, 96, 51);
		contentPane.add(imagemCartaMaquina);
		
		imagemCartaMaquina.setVisible(false);
		imagemCartaHumano.setVisible(false);
		
		textEasterEgg = new JLabel("(Texto de Easter Egg)");
		textEasterEgg.setBounds(418, 543, 202, 26);
		contentPane.add(textEasterEgg);
		textEasterEgg.setVisible(false);

		
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
		
		setLocationRelativeTo(null);
	}
	
	public void atualizarImagemCarta(byte[] imagem, JLabel labelCarta)
	{
	    if (imagem == null || imagem.length <= 0)
	    {
	        labelCarta.setIcon(null);
	        return;
	    }

	    try
	    {
	        ByteArrayInputStream bis = new ByteArrayInputStream(imagem);
	        Image img = ImageIO.read(bis);
	        Image imgRedimensionada = img.getScaledInstance(labelCarta.getWidth(), labelCarta.getHeight(), Image.SCALE_SMOOTH);
	        labelCarta.setIcon(new ImageIcon(imgRedimensionada));
	    }
	    catch (Exception e)
	    {
	        labelCarta.setIcon(null);
	    }
	}

	public void atualizarImagemCartaHumano(byte[] imagem)
	{
	    atualizarImagemCarta(imagem, imagemCartaHumano);
		imagemCartaHumano.setVisible(true);
	}

	public void atualizarImagemCartaMaquina(byte[] imagem)
	{
	    atualizarImagemCarta(imagem, imagemCartaMaquina);
		imagemCartaMaquina.setVisible(true);
	}

	public void limparElementosRodada()
	{
		textNomeCartaHumano.setText("");
		textNomeCartaMaquina.setText("");
		
		textFieldSpeedHumano.setText("");
		textFieldWeightHumano.setText("");
		textFieldAccelerationHumano.setText("");
		textFieldHandlingHumano.setText("");
		textFieldDriftHumano.setText("");
		textFieldOffroadHumano.setText("");
		textFieldMTHumano.setText("");
		
		textFieldSpeedMaquina.setText("");
		textFieldWeightMaquina.setText("");
		textFieldAccelerationMaquina.setText("");
		textFieldHandlingMaquina.setText("");
		textFieldDriftMaquina.setText("");
		textFieldOffroadMaquina.setText("");
		textFieldMTMaquina.setText("");
		
		atualizarImagemCartaHumano(null);
		imagemCartaHumano.setVisible(false);
		
		atualizarImagemCartaMaquina(null);
		imagemCartaMaquina.setVisible(false);
		
		textEasterEgg.setVisible(false);
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
	}
	
	public void mostrarElementosFimPartida()
	{
		textFim.setVisible(true);

		textVencedor.setText("Vencedor: " + controleJogo.getStringVencedor());
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
		viewJogoMenuCarta.setVisible(false);
		viewMenuPrincipal.setVisible(true);
	    this.setVisible(false);
	    
	    if (Debug.DEBUG_MENU_ENABLED)
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
		textFieldSpeedHumano.setText(Float.toString(controleCarta.getSpeed(TipoJogador.HUMANO)));
		textFieldWeightHumano.setText(Float.toString(controleCarta.getWeight(TipoJogador.HUMANO)));
		textFieldAccelerationHumano.setText(Float.toString(controleCarta.getAcceleration(TipoJogador.HUMANO)));
		textFieldHandlingHumano.setText(Float.toString(controleCarta.getHandling(TipoJogador.HUMANO)));
		textFieldDriftHumano.setText(Float.toString(controleCarta.getDrift(TipoJogador.HUMANO)));
		textFieldOffroadHumano.setText(Float.toString(controleCarta.getOffroad(TipoJogador.HUMANO)));
		textFieldMTHumano.setText(Float.toString(controleCarta.getMiniturbo(TipoJogador.HUMANO)));
		
		Carta cartaSelecionada = controleMao.getMaoPorTipoJogador(TipoJogador.HUMANO).getCartaEscolhida();
	}
	
	public void atualizarTextoAtributosMaquina()
	{
		textFieldSpeedMaquina.setText(Float.toString(controleCarta.getSpeed(TipoJogador.MAQUINA)));
		textFieldWeightMaquina.setText(Float.toString(controleCarta.getWeight(TipoJogador.MAQUINA)));
		textFieldAccelerationMaquina.setText(Float.toString(controleCarta.getAcceleration(TipoJogador.MAQUINA)));
		textFieldHandlingMaquina.setText(Float.toString(controleCarta.getHandling(TipoJogador.MAQUINA)));
		textFieldDriftMaquina.setText(Float.toString(controleCarta.getDrift(TipoJogador.MAQUINA)));
		textFieldOffroadMaquina.setText(Float.toString(controleCarta.getOffroad(TipoJogador.MAQUINA)));
		textFieldMTMaquina.setText(Float.toString(controleCarta.getMiniturbo(TipoJogador.MAQUINA)));
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
		
		if (!Debug.DEBUG_PRINTS_ENABLED)
		{
			return;
		}
		
		System.out.println("[ViewJogo] Easter Egg bumpfest executado!");
	}
	
	// USADAS NO DEBUG MENU!
	public void displayDebugMark()
	{
		textLabelDirty.setVisible(true);
	}
}