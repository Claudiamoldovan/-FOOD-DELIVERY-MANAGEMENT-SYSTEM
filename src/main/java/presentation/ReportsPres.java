package presentation;

import business.DeliveryService;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReportsPres extends JFrame {

	private JPanel contentPane;
	private JTextField ora1;
	private JTextField ora2;
	private JTextField raport2;
	private JTextField raport3;
	private JTextField raport31;
	private JTextField raport4;


	public ReportsPres(DeliveryService ds) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 970, 505);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("De la ora:");
		lblNewLabel.setBounds(25, 51, 113, 30);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblNewLabel);
		
		JLabel lblPanaLaOra = new JLabel("Pana la ora:");
		lblPanaLaOra.setBounds(25, 91, 113, 30);
		lblPanaLaOra.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblPanaLaOra);
		
		ora1 = new JTextField();
		ora1.setBounds(137, 52, 131, 28);
		ora1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(ora1);
		ora1.setColumns(10);
		
		ora2 = new JTextField();
		ora2.setBounds(137, 91, 131, 28);
		ora2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ora2.setColumns(10);
		contentPane.add(ora2);
		
		JLabel lblNewLabel_1 = new JLabel("Produse comandate mai mult de:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(25, 180, 281, 28);
		contentPane.add(lblNewLabel_1);
		
		raport2 = new JTextField();
		raport2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		raport2.setBounds(305, 180, 113, 27);
		contentPane.add(raport2);
		raport2.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Clienti care au comandat de mai mult de:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(25, 237, 350, 28);
		contentPane.add(lblNewLabel_1_1);
		
		raport3 = new JTextField();
		raport3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		raport3.setColumns(10);
		raport3.setBounds(400, 237, 50, 27);
		contentPane.add(raport3);
		
		JLabel lblNewLabel_2 = new JLabel("ori si cu valoarea comenzii mai mare de:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(475, 239, 350, 24);
		contentPane.add(lblNewLabel_2);
		
		raport31 = new JTextField();
		raport31.setFont(new Font("Tahoma", Font.PLAIN, 18));
		raport31.setColumns(10);
		raport31.setBounds(806, 238, 113, 27);
		contentPane.add(raport31);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Produse comandate in ziua de:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1_1.setBounds(25, 358, 281, 28);
		contentPane.add(lblNewLabel_1_1_1);
		
		raport4 = new JTextField();
		raport4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		raport4.setColumns(10);
		raport4.setBounds(289, 358, 150, 27);
		contentPane.add(raport4);
		
		JButton rap1 = new JButton("Generare raport!");
		rap1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ds.generareInterval(Integer.parseInt(ora1.getText()),Integer.parseInt(ora2.getText()));
			}});
		rap1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rap1.setBounds(342, 68, 200, 30);
		contentPane.add(rap1);
		
		JButton rap2 = new JButton("Generare raport!");
		rap2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ds.generareRaport2(Integer.parseInt(raport2.getText()));
			}});
		rap2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rap2.setBounds(476, 178, 200, 30);
		contentPane.add(rap2);
		
		JButton rap3 = new JButton("Generare raport!");
		rap3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ds.generareRaport3(Integer.parseInt(raport3.getText()),Integer.parseInt(raport31.getText()));
			}});
		rap3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rap3.setBounds(363, 275, 200, 30);
		contentPane.add(rap3);
		
		JButton rap4 = new JButton("Generare raport!");
		rap4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ds.generareRaport4(Integer.parseInt(raport4.getText()));
			}});
		rap4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rap4.setBounds(537, 356, 200, 30);
		contentPane.add(rap4);
		
		JButton inapoi = new JButton("Inapoi");
		inapoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdministratorPres a=new AdministratorPres( ds);
				a.show();
				dispose();
			}
		});
		inapoi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		inapoi.setBounds(788, 428, 131, 30);
		contentPane.add(inapoi);
		
		JButton log = new JButton("Log out!");
		log.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogInPres f=new LogInPres(ds);
				f.show();
				dispose();
			}
		});
		log.setFont(new Font("Tahoma", Font.PLAIN, 18));
		log.setBounds(25, 428, 131, 30);
		contentPane.add(log);
	}
}
