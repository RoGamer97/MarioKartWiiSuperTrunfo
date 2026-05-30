package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controle.ControleJogo;
import modelo.TipoJogador;

public class ViewJogoDebugMenu extends JFrame 
{

	private ViewJogo viewJogo;
    private ControleJogo controleJogo;

    public ViewJogoDebugMenu(ViewJogo viewJogo, ControleJogo controleJogo) 
    {
    	this.viewJogo = viewJogo;
        this.controleJogo = controleJogo;

        setTitle("Debug Menu");
        setSize(350, 200);
        getContentPane().setLayout(null);
        
        JLabel textLabelPontosPartidaHumano = new JLabel("Pontos da Partida Humano");
        textLabelPontosPartidaHumano.setBounds(21, 11, 141, 14);
        getContentPane().add(textLabelPontosPartidaHumano);
        
        JButton btnPlusPontosHumano = new JButton("+");
        btnPlusPontosHumano.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent arg0) 
        	{
            	controleJogo.debugMudarPontosPartida(TipoJogador.HUMANO, true);
        	}
        });
        btnPlusPontosHumano.setBounds(95, 36, 54, 23);
        getContentPane().add(btnPlusPontosHumano);
        
        JButton btnMinusPontosHumano_1 = new JButton("-");
        btnMinusPontosHumano_1.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent e) 
        	{
        		controleJogo.debugMudarPontosPartida(TipoJogador.HUMANO, false);
        	}
        });
        btnMinusPontosHumano_1.setBounds(31, 36, 54, 23);
        getContentPane().add(btnMinusPontosHumano_1);
        
        JLabel textLabelPontosPartidaHumano_1 = new JLabel("Pontos da Partida Humano");
        textLabelPontosPartidaHumano_1.setBounds(183, 11, 141, 14);
        getContentPane().add(textLabelPontosPartidaHumano_1);
        
        JButton btnMinusPontosMaquina = new JButton("-");
        btnMinusPontosMaquina.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) 
        	{
        		controleJogo.debugMudarPontosPartida(TipoJogador.MAQUINA, false);
        	}
        });
        btnMinusPontosMaquina.setBounds(193, 36, 54, 23);
        getContentPane().add(btnMinusPontosMaquina);
        
        JButton btnPlusPontosMaquina = new JButton("+");
        btnPlusPontosMaquina.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent e) 
        	{
        		controleJogo.debugMudarPontosPartida(TipoJogador.MAQUINA, true);
        	}
        });
        btnPlusPontosMaquina.setBounds(253, 36, 54, 23);
        getContentPane().add(btnPlusPontosMaquina);
        
        JLabel textLabelRodadaAtual = new JLabel("Rodada Atual");
        textLabelRodadaAtual.setBounds(131, 70, 101, 23);
        getContentPane().add(textLabelRodadaAtual);
        
        JButton btnPlusRodadaAtual = new JButton("+");
        btnPlusRodadaAtual.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) 
        	{
        		controleJogo.debugAumentarRodada();
        	}
        });
        btnPlusRodadaAtual.setBounds(141, 88, 54, 23);
        getContentPane().add(btnPlusRodadaAtual);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(50, 50);
        
        setAlwaysOnTop(true); 

        JLabel lblPontos = new JLabel("Pontos da Partida:");
        JButton btnMenosPontos = new JButton("-");
        JButton btnMaisPontos = new JButton("+");

        JLabel lblRodada = new JLabel("Rodada Atual:");
        JButton btnMenosRodada = new JButton("-");
        JButton btnMaisRodada = new JButton("+");
    }
}