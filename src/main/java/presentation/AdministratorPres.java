package presentation;

import business.DeliveryService;
import business.ItemMeniu;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.HashSet;
import java.util.Vector;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdministratorPres extends JFrame {

    private JPanel contentPane;
    private JTextField nume;
    private JTextField nota;
    private JTextField calorii;
    private JTextField proteine;
    private JTextField grasimi;
    private JTextField sodium;
    private JTextField pret;
    private JTable table;
    private JTextField numeMeniu;
    private JLabel pretMeniu;
    private HashSet<ItemMeniu> meniuNou;
    int i=1;
    float p=0;


    public AdministratorPres(DeliveryService ds) {
        meniuNou=new HashSet<>();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 971, 688);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Produs");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setBounds(81, 30, 78, 30);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Nume");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_1.setBounds(10, 70, 78, 25);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("nota");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_1_1.setBounds(10, 105, 78, 25);
        contentPane.add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_2 = new JLabel("Calorii");
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_1_2.setBounds(10, 140, 78, 25);
        contentPane.add(lblNewLabel_1_2);

        JLabel lblNewLabel_1_3 = new JLabel("Proteine");
        lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_1_3.setBounds(10, 175, 78, 25);
        contentPane.add(lblNewLabel_1_3);

        JLabel lblNewLabel_1_4 = new JLabel("Grasimi");
        lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_1_4.setBounds(10, 210, 78, 25);
        contentPane.add(lblNewLabel_1_4);

        JLabel lblNewLabel_1_5 = new JLabel("Sodium");
        lblNewLabel_1_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_1_5.setBounds(10, 245, 78, 25);
        contentPane.add(lblNewLabel_1_5);

        JLabel lblNewLabel_1_6 = new JLabel("Pret");
        lblNewLabel_1_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_1_6.setBounds(10, 280, 78, 25);
        contentPane.add(lblNewLabel_1_6);

        nume = new JTextField();
        nume.setFont(new Font("Tahoma", Font.PLAIN, 18));
        nume.setBounds(114, 70, 350, 25);
        contentPane.add(nume);
        nume.setColumns(10);

        nota = new JTextField();
        nota.setFont(new Font("Tahoma", Font.PLAIN, 18));
        nota.setColumns(10);
        nota.setBounds(114, 105, 156, 25);
        contentPane.add(nota);

        calorii = new JTextField();
        calorii.setFont(new Font("Tahoma", Font.PLAIN, 18));
        calorii.setColumns(10);
        calorii.setBounds(114, 140, 156, 25);
        contentPane.add(calorii);

        proteine = new JTextField();
        proteine.setFont(new Font("Tahoma", Font.PLAIN, 18));
        proteine.setColumns(10);
        proteine.setBounds(114, 175, 156, 25);
        contentPane.add(proteine);

        grasimi = new JTextField();
        grasimi.setFont(new Font("Tahoma", Font.PLAIN, 18));
        grasimi.setColumns(10);
        grasimi.setBounds(114, 210, 156, 25);
        contentPane.add(grasimi);

        sodium = new JTextField();
        sodium.setFont(new Font("Tahoma", Font.PLAIN, 18));
        sodium.setColumns(10);
        sodium.setBounds(114, 245, 156, 25);
        contentPane.add(sodium);

        pret = new JTextField();
        pret.setFont(new Font("Tahoma", Font.PLAIN, 18));
        pret.setColumns(10);
        pret.setBounds(114, 280, 156, 25);
        contentPane.add(pret);

        table = new JTable();
        table = afisare(table);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(21, 325, 908, 251);
        contentPane.add(scrollPane);
        scrollPane.setViewportView(table);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                int rand = table.getSelectedRow();
                nume.setText(model.getValueAt(rand, 0).toString());
                nota.setText(model.getValueAt(rand, 1).toString());
                calorii.setText(model.getValueAt(rand, 2).toString());
                proteine.setText(model.getValueAt(rand, 3).toString());
                grasimi.setText(model.getValueAt(rand, 4).toString());
                sodium.setText(model.getValueAt(rand, 5).toString());
                pret.setText(model.getValueAt(rand, 6).toString());


            }
        });

        JButton btnNewButton = new JButton("Log out");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LogInPres f = new LogInPres( ds);
                f.show();
                dispose();

            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnNewButton.setBounds(816, 611, 113, 30);
        contentPane.add(btnNewButton);

        JButton adauga = new JButton("Adaugare");
        adauga.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String numep = nume.getText();
                Float pretp = Float.valueOf(pret.getText());
                Float caloriip = Float.valueOf(calorii.getText());
                Float proteinep = Float.valueOf(proteine.getText());
                Float grasimip = Float.valueOf(grasimi.getText());
                Float sodiup = Float.valueOf(sodium.getText());
                Float notap = Float.valueOf(nota.getText());
                ItemMeniu nou = new ItemMeniu(numep, notap, caloriip, proteinep, grasimip, sodiup,pretp);
                ds.adaugareMeniu(nou);
                table = afisare(table);
                JOptionPane.showMessageDialog(null,"s-a introdus cu succes");
            }
        });
        adauga.setFont(new Font("Tahoma", Font.PLAIN, 18));
        adauga.setBounds(323, 105, 121, 25);
        contentPane.add(adauga);

        JButton editare = new JButton("Editare");
        editare.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ItemMeniu vechi;
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                int rand = table.getSelectedRow();
                String numev=model.getValueAt(rand, 0).toString();
                float notav=Float.parseFloat(model.getValueAt(rand, 1).toString());
                float caloriiv= Float.parseFloat(model.getValueAt(rand, 2).toString());
                float proteinev= Float.parseFloat(model.getValueAt(rand, 3).toString());
                float grasimiv= Float.parseFloat(model.getValueAt(rand, 4).toString());
                float sodiumv= Float.parseFloat(model.getValueAt(rand, 5).toString());
                float pretv= Float.parseFloat(model.getValueAt(rand, 6).toString());
                vechi = new ItemMeniu(numev,notav,caloriiv,proteinev,grasimiv,sodiumv,pretv);

                ItemMeniu nou = new ItemMeniu(nume.getText(),  Float.valueOf(nota.getText()), Float.valueOf(calorii.getText()), Float.valueOf(proteine.getText()), Float.valueOf(grasimi.getText()), Float.valueOf(sodium.getText()),Float.valueOf(pret.getText()));
                ds.editareMeniu(vechi,nou);
                table = afisare(table);
                JOptionPane.showMessageDialog(null,"s-a editat cu succes");
            }
        });
        editare.setFont(new Font("Tahoma", Font.PLAIN, 18));
        editare.setBounds(323, 175, 121, 25);
        contentPane.add(editare);

        JButton stergere = new JButton("Stergere");
        stergere.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String numep = nume.getText();
                Float pretp = Float.valueOf(pret.getText());
                Float caloriip = Float.valueOf(calorii.getText());
                Float proteinep = Float.valueOf(proteine.getText());
                Float grasimip = Float.valueOf(grasimi.getText());
                Float sodiup = Float.valueOf(sodium.getText());
                Float notap = Float.valueOf(nota.getText());
                ItemMeniu nou = new ItemMeniu(numep,  notap, caloriip, proteinep, grasimip, sodiup,pretp);
                ds.stergereMeniu(nou);
                table = afisare(table);
                JOptionPane.showMessageDialog(null,"s-a sters cu succes");
            }
        });
        stergere.setFont(new Font("Tahoma", Font.PLAIN, 18));
        stergere.setBounds(323, 245, 121, 25);
        contentPane.add(stergere);


        JButton adInMeniu = new JButton("Adaugare in meniu");
        adInMeniu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ItemMeniu m=new ItemMeniu(nume.getText(),  Float.valueOf(nota.getText()), Float.valueOf(calorii.getText()), Float.valueOf(proteine.getText()), Float.valueOf(grasimi.getText()), Float.valueOf(sodium.getText()),Float.valueOf(pret.getText()));
                meniuNou.add(m);
                p=p+m.getPret()-10;
                pretMeniu.setText(String.valueOf(p));
                JOptionPane.showMessageDialog(null,"s-a adaugat cu succes");
        }
        });
        adInMeniu.setFont(new Font("Tahoma", Font.PLAIN, 18));
        adInMeniu.setBounds(559, 67, 200, 30);
        contentPane.add(adInMeniu);

        JLabel lblNewLabel_1_7 = new JLabel("Nume meniu");
        lblNewLabel_1_7.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_1_7.setBounds(504, 114, 113, 25);
        contentPane.add(lblNewLabel_1_7);

        JLabel lblNewLabel_1_7_1 = new JLabel("Pret meniu");
        lblNewLabel_1_7_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_1_7_1.setBounds(504, 149, 113, 25);
        contentPane.add(lblNewLabel_1_7_1);

        numeMeniu = new JTextField();
        numeMeniu.setFont(new Font("Tahoma", Font.PLAIN, 18));
        numeMeniu.setColumns(10);
        numeMeniu.setBounds(627, 111, 156, 25);
        contentPane.add(numeMeniu);

        pretMeniu = new JLabel("");
        pretMeniu.setFont(new Font("Tahoma", Font.PLAIN, 18));
        pretMeniu.setBounds(627, 146, 156, 25);
        contentPane.add(pretMeniu);

        JButton btnFinalizareMeniu = new JButton("Finalizare meniu");
        btnFinalizareMeniu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ds.adaugareMeniuNou(numeMeniu.getText(),meniuNou);
                meniuNou=null;
                table=afisare(table);
                p=0;i=1;
                JOptionPane.showMessageDialog(null,"noul meniu s-a introdus cu succes");
            }
        });
        btnFinalizareMeniu.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnFinalizareMeniu.setBounds(559, 189, 200, 30);
        contentPane.add(btnFinalizareMeniu);

        JButton btnNewButton_1 = new JButton("Rapoarte");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ReportsPres r = new ReportsPres(ds);
                r.show();
                dispose();
            }
        });
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnNewButton_1.setBounds(587, 244, 142, 36);
        contentPane.add(btnNewButton_1);
    }

    public JTable afisare(JTable t) {
        File csv = new File("products.csv");
        DefaultTableModel tab = new DefaultTableModel();
        try {
            int start = 0;
            InputStreamReader i = new InputStreamReader(new FileInputStream(csv));
            CSVParser csvParser = CSVFormat.DEFAULT.parse(i);
            for (CSVRecord csvRecord : csvParser) {
                if (start == 0) {
                    start = 1;
                    tab.addColumn(csvRecord.get(0));
                    tab.addColumn(csvRecord.get(1));
                    tab.addColumn(csvRecord.get(2));
                    tab.addColumn(csvRecord.get(3));
                    tab.addColumn(csvRecord.get(4));
                    tab.addColumn(csvRecord.get(5));
                    tab.addColumn(csvRecord.get(6));
                } else {
                    Vector row = new Vector();
                    row.add(csvRecord.get(0));
                    row.add(csvRecord.get(1));
                    row.add(csvRecord.get(2));
                    row.add(csvRecord.get(3));
                    row.add(csvRecord.get(4));
                    row.add(csvRecord.get(5));
                    row.add(csvRecord.get(6));
                    tab.addRow(row);
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        t.setModel(tab);
        return t;
    }
}
