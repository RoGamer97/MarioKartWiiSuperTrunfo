package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import java.awt.Window.Type;

public class ViewMenuPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewMenuPrincipal frame = new ViewMenuPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewMenuPrincipal() {
		setTitle("Super Mario Kart Trunfo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel textTitulo = new JLabel("Super Mario Kart Trunfo PROTOTYPE");
		textTitulo.setBounds(127, 0, 235, 52);
		contentPane.add(textTitulo);
		
		JComboBox<String> totalCartas = new JComboBox<>
		(
			new String[] {"4", "6", "10", "14", "16", "18"}
		);
		totalCartas.setBounds(187, 138, 62, 22);
		contentPane.add(totalCartas);
		
		// 12 selecionado por padrão
		totalCartas.setSelectedItem("10");
		
		JRadioButton rdbtnMostrarCartaMaquina = new JRadioButton("Mostrar cartas da Maquina");
		rdbtnMostrarCartaMaquina.setBounds(136, 167, 205, 24);
		contentPane.add(rdbtnMostrarCartaMaquina);
		
		JButton btnJogar = new JButton("Jogar");
		btnJogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				int qtdCartas = Integer.parseInt(totalCartas.getSelectedItem().toString());
				boolean mostrarCartaMaquina = rdbtnMostrarCartaMaquina.isSelected();
				
				ViewJogo viewJogo = new ViewJogo(ViewMenuPrincipal.this, qtdCartas, mostrarCartaMaquina);
				
				viewJogo.setVisible(true);
				ViewMenuPrincipal.this.setVisible(false);
			}
		});
		btnJogar.setBounds(151, 198, 140, 41);
		contentPane.add(btnJogar);
		
		JLabel textInfoQtdCartas = new JLabel("Quantidade de cartas por jogador");
		textInfoQtdCartas.setBounds(136, 120, 205, 18);
		contentPane.add(textInfoQtdCartas);
	}
}
