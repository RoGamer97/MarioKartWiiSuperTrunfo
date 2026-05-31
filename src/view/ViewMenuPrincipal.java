package view;

import java.awt.EventQueue;
import java.awt.Font; // Importado
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;

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
	public ViewMenuPrincipal() 
	{
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
		textTitulo.setFont(new Font("Dialog", Font.PLAIN, 24)); 
		
		contentPane.add(textTitulo);
		
		JComboBox<String> totalCartas = new JComboBox<>
		(
			new String[] {"8", "10", "12", "14", "16"}
		);
		totalCartas.setBounds(187, 138, 62, 22);
		contentPane.add(totalCartas);
		
		// 12 selecionado por padrão
		totalCartas.setSelectedItem("12");
		
		JRadioButton rdbtnMostrarCartaMaquina = new JRadioButton("Mostrar cartas da Maquina");
		rdbtnMostrarCartaMaquina.setBounds(136, 167, 205, 24);
		contentPane.add(rdbtnMostrarCartaMaquina);
		
		JButton btnJogar = new JButton("Jogar");
		btnJogar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				int totalRodadas = Integer.parseInt(totalCartas.getSelectedItem().toString());
				boolean mostrarCartaMaquina = rdbtnMostrarCartaMaquina.isSelected();
				
				ViewJogo viewJogo = new ViewJogo(ViewMenuPrincipal.this, totalRodadas, mostrarCartaMaquina);
				ViewMenuPrincipal.this.setVisible(false);
				
				viewJogo.abrirMenuSelecaoCarta();
			}
		});
		
		btnJogar.setBounds(151, 198, 140, 41);
		contentPane.add(btnJogar);
		
		JLabel textInfoQtdCartas = new JLabel("Quantidade de rodadas");
		textInfoQtdCartas.setBounds(151, 120, 205, 18);
		contentPane.add(textInfoQtdCartas);
		
		setLocationRelativeTo(null);
	}
}