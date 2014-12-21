/*
 * Dane.java
 * Dialog pojawiający się po naciśnięciu przycisk Dodaj/Edytuj
 * 
 * Created on 2012-01-16, 21:56:12
 */
package licencje;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Dane extends JDialog {
    private List<Osoba> baza;
    private int returnStatus = 0;
    public static final int RET_CANCEL = 0;
    public static final int RET_OK = 1;
    private Osoba osoba;
    private JTextField adres1;
    private JTextField adres2;
    private JLabel blad1;
    private JLabel blad2;
    private ButtonGroup buttonGroup1;
    private JTextField dataUrodzenia;
    private JTextField dataWydania;
    private JTextField ekipa;
    private JTextField imie;
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JButton jButton4;
    private JButton jButton5;
    private JButton jButton6;
    private JLabel jLabel1;
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel12;
    private JLabel jLabel13;
    private JLabel jLabel14;
    private JLabel jLabel15;
    private JLabel jLabel16;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JRadioButton jRadioButtonK;
    private JRadioButton jRadioButtonM;
    private JTextField kategoriaNar;
    private JTextField kategoriaUCI;
    private JComboBox klub;
    private JTextField kodUCI;
    private JTextField narodowosc;
    private JTextField nazwisko;
    private JTextField nrLicencji;
    private int rok;
  
    public Dane(JFrame parent, boolean modal, int rok) {
        super(parent, modal);
        initComponents();
        this.baza = ((Licencje)parent).getBazaDanych(0);
        this.osoba = new Osoba("Zawodnik", 0);
        this.rok = rok;
        setVisible(true);
    }
  
    public Dane(Licencje parent, boolean modal, Osoba osoba, int rok) {
        super(parent, modal);
        initComponents();

        this.baza = parent.getBazaDanych(0);
        this.osoba = osoba;
        this.rok = rok;
        
        wstawDane(osoba);
        setVisible(true);
    }
  
    public void wstawDane(Osoba osoba) {
        this.kategoriaUCI.setText(osoba.getKategoriaUCI());
        this.kategoriaNar.setText(osoba.getKategoriaNar());
        this.nazwisko.setText(osoba.getNazwisko());
        this.imie.setText(osoba.getImie());
        this.narodowosc.setText(osoba.getNarodowosc());
        this.ekipa.setText(osoba.getEkipa());
        this.klub.setSelectedItem(osoba.getKlub());
        this.kodUCI.setText(osoba.getKodUCI() + "");
        this.nrLicencji.setText(osoba.getNrLicencji());
        this.dataUrodzenia.setText(osoba.getDataUrodzenia());
        this.adres1.setText(osoba.getAdres1());
        this.adres2.setText(osoba.getAdres2());
        if (osoba.isPlec()) {
          this.jRadioButtonM.setSelected(true);
        } else {
          this.jRadioButtonK.setSelected(true);
        }
        this.dataWydania.setText(osoba.getDataWydania());
    }
  
    public void bazaDanych(){
    /*    Iterator iter=lista.iterator();
        int i=0; 
        int j=0;
        while (iter.hasNext())
        {
            Osoba os=(Osoba)iter.next();
                BufferedImage simage2=new BufferedImage(os.getWidth(), os.getHeight(), BufferedImage.TYPE_INT_ARGB);
                Graphics2D sg3 = image2.createGraphics();
                //g3.setColor(Color.white);
                //g3.fillRect(0, 0, rozdzielczoscX, rozdzielczoscY);
                sg3.setColor(Color.black);
            os.rysujDane(sg3);
            sg.drawImage(image2, (int)((i%2)*1000), (int)(j*600), (int)(os.getWidth()), (int)(os.getHeight()), this);
            i++;
            if (i%2==0) j++;
        }*/
    }
    
    private void initComponents() {
        this.buttonGroup1 = new ButtonGroup();
        this.jLabel1 = new JLabel();
        this.jLabel2 = new JLabel();
        this.jLabel3 = new JLabel();
        this.jLabel4 = new JLabel();
        this.jLabel5 = new JLabel();
        this.jLabel6 = new JLabel();
        this.jLabel7 = new JLabel();
        this.kategoriaUCI = new JTextField();
        this.kategoriaNar = new JTextField();
        this.nazwisko = new JTextField();
        this.imie = new JTextField();
        this.narodowosc = new JTextField();
        this.ekipa = new JTextField();
        this.jLabel8 = new JLabel();
        this.jLabel9 = new JLabel();
        this.jLabel10 = new JLabel();
        this.jLabel11 = new JLabel();
        this.jLabel12 = new JLabel();
        this.jLabel13 = new JLabel();
        this.kodUCI = new JTextField();
        this.nrLicencji = new JTextField();
        this.dataUrodzenia = new JTextField();
        this.adres1 = new JTextField();
        this.adres2 = new JTextField();
        this.dataWydania = new JTextField();
        this.jRadioButtonK = new JRadioButton();
        this.jRadioButtonM = new JRadioButton();
        this.jButton1 = new JButton();
        this.jButton2 = new JButton();
        this.jButton3 = new JButton();
        this.jLabel14 = new JLabel();
        this.jLabel15 = new JLabel();
        this.jLabel16 = new JLabel();
        this.jButton4 = new JButton();
        this.blad2 = new JLabel();
        this.blad1 = new JLabel();
        this.klub = new JComboBox();
        this.jButton5 = new JButton();
        this.jButton6 = new JButton();
    
        setDefaultCloseOperation(2);
        setTitle("Wprowadź dane");
    
        this.jLabel1.setText("Kategoria UCI:");
        this.jLabel2.setText("Kategoria narodowa:");    
        this.jLabel3.setText("Nazwisko:");   
        this.jLabel4.setText("Imię:");    
        this.jLabel5.setText("Narodowość:");    
        this.jLabel6.setText("Ekipa:");    
        this.jLabel7.setText("Klub:");    
        this.jLabel8.setText("Kod UCI: POL");    
        this.jLabel9.setText("Nr licencji:");    
        this.jLabel10.setText("Data urodzenia:");    
        this.jLabel11.setText("Adres zamieszkania:");    
        this.jLabel12.setText("Płeć:");    
        this.jLabel13.setText("Data wydania:");

        this.buttonGroup1.add(this.jRadioButtonK);
        this.jRadioButtonK.setText("K");

        this.buttonGroup1.add(this.jRadioButtonM);
        this.jRadioButtonM.setSelected(true);
        this.jRadioButtonM.setText("M");

        this.jButton1.setText("OK");
        this.jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Dane.this.jButton1ActionPerformed(evt);
            }
        });
        this.jButton2.setText("Wstaw datę");
        this.jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Dane.this.jButton2ActionPerformed(evt);
            }
        });
        this.jButton3.setText("Wstaw kategorie");
        this.jButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Dane.this.jButton3ActionPerformed(evt);
            }
        });
        this.jLabel14.setText("Generuj kategorie na podsawie kodu UCI:");    
        this.jLabel15.setText("Wstaw dziesiejszą datę wydania:");    
        this.jLabel16.setText("Generuj datę urodzenia z kodu UCI:");    
        this.jButton4.setText("Wstaw datę urodzenia");
        this.jButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Dane.this.jButton4ActionPerformed(evt);
            }
        });
        this.klub.setEditable(true);
        this.klub.setModel(new DefaultComboBoxModel(new String[] { " ", "GKS CARTUSIA KARTUZY", "MLKS BASZTA BYTÓW", "WKS FLOTA GDYNIA", "KS TREK GDYNIA", "KS LECHIA GDAŃSK", "BELTA TEAM GDAŃSK", "NIEZRZESZONY" }));
    
        this.jButton5.setText("Anuluj");
        this.jButton5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Dane.this.jButton5ActionPerformed(evt);
            }
        });
        this.jButton6.setText("POBIERZ DANE Z BAZY DANYCH (dane.xls)");
        this.jButton6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Dane.this.jButton6ActionPerformed(evt);
            }
        });
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel14).addComponent(this.jLabel16).addComponent(this.jLabel15)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.jButton2, -1, -1, 32767).addComponent(this.jButton4, -1, -1, 32767).addComponent(this.jButton3, -1, -1, 32767)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addComponent(this.jButton5).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton1, -2, 55, -2)).addComponent(this.blad2, -1, -1, 32767).addComponent(this.blad1, -1, -1, 32767))).addComponent(this.jButton6, -1, -1, 32767).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel2).addComponent(this.jLabel1).addComponent(this.jLabel3).addComponent(this.jLabel4).addComponent(this.jLabel5).addComponent(this.jLabel6).addComponent(this.jLabel7)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.kategoriaUCI).addComponent(this.kategoriaNar).addComponent(this.nazwisko).addComponent(this.imie).addComponent(this.ekipa).addComponent(this.narodowosc).addComponent(this.klub, 0, -1, 32767)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel8).addComponent(this.jLabel9).addComponent(this.jLabel10).addComponent(this.jLabel12).addComponent(this.jLabel13).addComponent(this.jLabel11)).addGap(8, 8, 8).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addGroup(layout.createSequentialGroup().addComponent(this.jRadioButtonK).addGap(18, 18, 18).addComponent(this.jRadioButtonM)).addComponent(this.kodUCI).addComponent(this.nrLicencji).addComponent(this.dataUrodzenia).addComponent(this.adres1).addComponent(this.adres2).addComponent(this.dataWydania, -2, 117, -2)))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel1).addComponent(this.kategoriaUCI, -2, -1, -2).addComponent(this.jLabel8)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel2).addComponent(this.kategoriaNar, -2, -1, -2).addComponent(this.jLabel9)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel3).addComponent(this.nazwisko, -2, -1, -2).addComponent(this.jLabel10)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel4).addComponent(this.imie, -2, -1, -2).addComponent(this.jLabel11)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel5).addComponent(this.narodowosc, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel6).addComponent(this.ekipa, -2, -1, -2).addComponent(this.jLabel12)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.klub, -2, -1, -2).addComponent(this.jLabel13)).addComponent(this.jLabel7))).addGroup(layout.createSequentialGroup().addComponent(this.kodUCI, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.nrLicencji, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.dataUrodzenia, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.adres1, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.adres2, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jRadioButtonM).addComponent(this.jRadioButtonK)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.dataWydania, -2, -1, -2))).addGap(9, 9, 9).addComponent(this.jButton6).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel14).addComponent(this.jButton3)).addComponent(this.blad1, -2, 23, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel16).addComponent(this.jButton4)).addComponent(this.blad2, -2, 23, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton2).addComponent(this.jLabel15)).addGap(4, 4, 4).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton1).addComponent(this.jButton5)).addContainerGap()));
    
        pack();
    }
  
    private void jButton3ActionPerformed(ActionEvent evt) {
        //Calendar z = Calendar.getInstance();
        //int rok = z.get(1);
        try {
            int dataUr = Integer.parseInt(this.kodUCI.getText());
            if ((dataUr > 19000000) && (dataUr < 21000000)) {
                int wiek = rok - dataUr / 10000;

                String kategoria = "";
                if (this.jRadioButtonM.isSelected()) {
                    if (wiek < 13) {
                        kategoria = "ŻAK";
                    } else if (wiek < 15) {
                        kategoria = "MŁODZIK";
                    } else if (wiek < 17) {
                        kategoria = "JUNIOR MŁ.";
                    } else if (wiek < 19) {
                        this.kategoriaNar.setText("JUNIOR");
                        this.kategoriaUCI.setText("HOMMES JUNIOR");
                    } else if (wiek < 30) {
                        this.kategoriaNar.setText("CYKLOSPORT");
                        this.kategoriaUCI.setText("CYKLOSPORT");
                    } else {
                        this.kategoriaNar.setText("MASTERS");
                        this.kategoriaUCI.setText("HOMMES MASTER");
                    }
                } else if (wiek < 13) {
                    kategoria = "ŻAKINI";
                } else if (wiek < 15) {
                    kategoria = "MŁODZICZKA";
                } else if (wiek < 17) {
                    kategoria = "JUNIORKA MŁ.";
                } else if (wiek < 19) {
                    this.kategoriaNar.setText("JUNIORKA");
                    this.kategoriaUCI.setText("FEMMES JUNOR");
                } else if (wiek < 30) {
                        this.kategoriaNar.setText("CYKLOSPORT");
                        this.kategoriaUCI.setText("CYKLOSPORT");
                } else {
                    this.kategoriaNar.setText("MASTERS");
                    this.kategoriaUCI.setText("FEMMES MASTER");
                }
                if (!kategoria.equals("")) {
                    this.kategoriaUCI.setText(kategoria);
                    this.kategoriaNar.setText(kategoria);
                }
                this.blad1.setText("");
            } else {
                this.blad1.setText("Błędny kod UCI");
            }
        } catch (Exception e) {
            this.blad1.setText("Błędny kod UCI");
        }
    }
  
    private void jButton4ActionPerformed(ActionEvent evt) {
        try {
            int dataUr = Integer.parseInt(this.kodUCI.getText());
            if ((dataUr > 19000000) && (dataUr < 21000000)) {
                int rok = dataUr / 10000;
                int miesiac = (dataUr - rok * 10000) / 100;
                int dzien = dataUr - rok * 10000 - miesiac * 100;
                if (miesiac < 10) {
                    this.dataUrodzenia.setText(dzien + ".0" + miesiac + "." + rok);
                } else {
                    this.dataUrodzenia.setText(dzien + "." + miesiac + "." + rok);
                }
                this.blad2.setText("");
            } else {
                this.blad2.setText("Błędny kod UCI");
            }
        } catch (Exception e) {
            this.blad2.setText("Błędny kod UCI");
        }
    }
  
    private void jButton2ActionPerformed(ActionEvent evt) {
        Calendar z = Calendar.getInstance();
        int miesiac = z.get(2) + 1;
        if (miesiac < 10) {
            this.dataWydania.setText(z.get(5) + ".0" + miesiac + "." + z.get(1));
        } else {
            this.dataWydania.setText(z.get(5) + "." + miesiac + "." + z.get(1));
        }
    }
  
    private void jButton1ActionPerformed(ActionEvent evt) {
        this.osoba.setKategoriaUCI(this.kategoriaUCI.getText());
        this.osoba.setKategoriaNar(this.kategoriaNar.getText());
        this.osoba.setNazwisko(this.nazwisko.getText());
        this.osoba.setImie(this.imie.getText());
        this.osoba.setNarodowosc(this.narodowosc.getText());
        this.osoba.setEkipa(this.ekipa.getText());
        this.osoba.setKlub((String)this.klub.getSelectedItem());
        try {
            this.osoba.setKodUCI(Integer.parseInt(this.kodUCI.getText()));
        } catch (Exception e) {
            this.osoba.setKodUCI(0);
        }
        this.osoba.setNrLicencji(this.nrLicencji.getText());
        this.osoba.setDataUrodzenia(this.dataUrodzenia.getText());
        this.osoba.setAdres1(this.adres1.getText());
        this.osoba.setAdres2(this.adres2.getText());
        if (this.jRadioButtonM.isSelected()) {
            this.osoba.setPlec(true);
        } else {
            this.osoba.setPlec(false);
        }
        this.osoba.setDataWydania(this.dataWydania.getText());
        this.osoba.rysujDane();
        doClose(1);
    }
  
    private void jButton5ActionPerformed(ActionEvent evt) {
        doClose(0);
    }
  
    private void jButton6ActionPerformed(ActionEvent evt) {
        Szukaj szukaj = new Szukaj(this, true, this.baza, 0);
        if ((szukaj.getReturnStatus() == 1) && 
            (szukaj.getSelected() >= 0)) {
            Osoba osobaZ = (Osoba)this.baza.get(szukaj.getSelected());
            wstawDane(osobaZ);
        }
    }
  
    public Osoba getOsoba() {
        return this.osoba;
    }
  
    private void doClose(int retStatus) {
        this.returnStatus = retStatus;
        setVisible(false);
        dispose();
    }
  
    public int getReturnStatus() {
        return this.returnStatus;
    }
  
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Dane.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Dane.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Dane.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Dane.class.getName()).log(Level.SEVERE, null, ex);
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Dane dialog = new Dane(new Licencje(), true, 2015);
                dialog.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
}