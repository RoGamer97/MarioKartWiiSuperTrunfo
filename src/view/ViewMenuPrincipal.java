package view;

import java.awt.Font; // Importado
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controle.ControleJogo;

public class ViewMenuPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
		
	private ControleJogo controleJogo;

	public ViewMenuPrincipal(ControleJogo controleJogo) 
	{
		this.controleJogo = controleJogo;
		
		setTitle("Mario Kart Wii Super Trunfo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel textTitulo = new JLabel("Mario Kart Wii Super Trunfo");
		textTitulo.setBounds(73, 0, 420, 52);
		textTitulo.setFont(new Font("SansSerif", Font.BOLD, 24)); 
		
		contentPane.add(textTitulo);
		
		JComboBox<String> totalCartas = new JComboBox<>
		(
			new String[] {"8", "10", "12", "14", "16"}
		);
		totalCartas.setBounds(190, 149, 62, 22);
		contentPane.add(totalCartas);
		
		// 12 selecionado por padrão
		totalCartas.setSelectedItem("12");
		
		JButton btnJogar = new JButton("Jogar");
		btnJogar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{	
				setVisible(false);
			    controleJogo.setTotalRodadas(Integer.parseInt(totalCartas.getSelectedItem().toString()));
				controleJogo.iniciarPartida();
			}
		});
		
		btnJogar.setBounds(151, 198, 140, 41);
		contentPane.add(btnJogar);
		
		JLabel textInfoQtdCartas = new JLabel("Quantidade de rodadas (Cartas por Jogador)");
		textInfoQtdCartas.setBounds(104, 129, 265, 18);
		contentPane.add(textInfoQtdCartas);
		
		setLocationRelativeTo(null);
	}
}