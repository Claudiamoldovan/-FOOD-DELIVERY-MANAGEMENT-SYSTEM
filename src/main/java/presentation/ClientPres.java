package presentation;

import business.DeliveryService;
import business.ItemMeniu;
import business.Comanda;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import dataLayer.FileWriterClass;
import dataLayer.Serializator;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javax.swing.*;
import java.awt.Font;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;

public class ClientPres extends JFrame implements Serializable {

    private JPanel contentPane;
    private JTextField produs;
    private JTable table;
    private JTextField detaliu;
    private int total;
    private HashSet<ItemMeniu> produsecomandate;
    private String bill="";
    private Angajat a;


    public ClientPres(int idUser, DeliveryService ds) {
        a=new Angajat(ds);
        produsecomandate=new HashSet<ItemMeniu>();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 657, 562);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        produs = new JTextField();
        produs.setFont(new Font("Tahoma", Font.PLAIN, 18));
        produs.setBounds(10, 81, 350, 40);
        contentPane.add(produs);
        produs.setColumns(10);

        JLabel lblNewLabel = new JLabel("Produs");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setBounds(10, 40, 117, 31);
        contentPane.add(lblNewLabel);


        JButton adauga = new JButton("Adauga produs!");
        adauga.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                int rand = table.getSelectedRow();
                String numev=model.getValueAt(rand, 0).toString();
                float ratingv=Float.parseFloat(model.getValueAt(rand, 1).toString());
                float caloriiv= Float.parseFloat(model.getValueAt(rand, 2).toString());
                float proteinev= Float.parseFloat(model.getValueAt(rand, 3).toString());
                float grasimiv= Float.parseFloat(model.getValueAt(rand, 4).toString());
                float sodiumv= Float.parseFloat(model.getValueAt(rand, 5).toString());
                float pretv= Float.parseFloat(model.getValueAt(rand, 6).toString());
                ItemMeniu m=new ItemMeniu(numev,ratingv,caloriiv,proteinev,grasimiv,sodiumv,pretv);

                produsecomandate.add(m);
                total+=pretv;
                JOptionPane.showMessageDialog(null,"s-a introdus cu succes");
                Serializator.serialize(ds);
            }});
        adauga.setFont(new Font("Tahoma", Font.PLAIN, 20));
        adauga.setBounds(10, 148, 185, 31);
        contentPane.add(adauga);

        JButton finalizare = new JButton("Finalizare!");
        finalizare.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Comanda nou=new Comanda(total,idUser);//LogInPres.idBill);
                ds.comanda(nou,produsecomandate);
                bill+="Bon client "+idUser+"\n";
                for(ItemMeniu menu:produsecomandate){
                    bill+=menu.toString()+"\n";
                }


                bill+="Total: " +total+"\n";
                FileWriterClass file=new FileWriterClass("Bon client "+idUser+".txt");
                file.writeToFile(bill);
                total=0;
                produsecomandate=null;
                bill="";
                JOptionPane.showMessageDialog(null,"finalizare cu succes");
                Serializator.serialize(ds);

            }});
        finalizare.setFont(new Font("Tahoma", Font.PLAIN, 20));
        finalizare.setBounds(10, 189, 185, 31);
        contentPane.add(finalizare);

        DefaultComboBoxModel list = new DefaultComboBoxModel();
        list.addElement("Nume");
        list.addElement("Rating");
        list.addElement("Calorii");
        list.addElement("Proteine");
        list.addElement("Grasimi");
        list.addElement("Sodiu");
        list.addElement("Pret");
        JComboBox comboBox = new JComboBox(list);
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
        comboBox.setBounds(407, 81, 169, 40);
        contentPane.add(comboBox);

        detaliu = new JTextField();
        detaliu.setFont(new Font("Tahoma", Font.PLAIN, 18));
        detaliu.setColumns(10);
        detaliu.setBounds(407, 139, 169, 40);
        contentPane.add(detaliu);

        JButton cauta = new JButton("Cauta");
        cauta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cat = (String) comboBox.getSelectedItem();
                String det = detaliu.getText();
                List<ItemMeniu> nou = ds.gasesteProduse(cat, det);
                DefaultTableModel c = new DefaultTableModel();
                c.addColumn("Title");
                c.addColumn("Rating");
                c.addColumn("Calories");
                c.addColumn("Protein");
                c.addColumn("Fat");
                c.addColumn("Sodim");
                c.addColumn("Price");
                for (ItemMeniu menu : nou) {
                    Vector row = new Vector();
                    row.add(menu.getProdus());
                    row.add(menu.getnota());
                    row.add(menu.getCalorii());
                    row.add(menu.getProteine());
                    row.add(menu.getGrasimi());
                    row.add(menu.getSodiu());
                    row.add(menu.getPret());
                    c.addRow(row);
                }
                table.setModel(c);
            }
        });
        cauta.setFont(new Font("Tahoma", Font.PLAIN, 20));
        cauta.setBounds(407, 189, 169, 31);
        contentPane.add(cauta);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 278, 624, 195);
        contentPane.add(scrollPane);
        scrollPane.setViewportView(table);
        JButton btnAfiseazaIntregMeniul = new JButton("Afiseaza intreg meniul");
        btnAfiseazaIntregMeniul.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                table = afisare(table);
            }
        });
        btnAfiseazaIntregMeniul.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnAfiseazaIntregMeniul.setBounds(161, 237, 269, 31);
        contentPane.add(btnAfiseazaIntregMeniul);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                int rand = table.getSelectedRow();
                produs.setText(model.getValueAt(rand, 0).toString());
            }
        });

        JButton btnNewButton = new JButton("Log out!");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LogInPres f = new LogInPres(ds);
                f.show();
                dispose();
            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnNewButton.setBounds(524, 484, 110, 31);
        contentPane.add(btnNewButton);
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
