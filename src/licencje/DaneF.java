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
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class DaneF extends JDialog {
    private List<Osoba> baza;
    private int returnStatus = 0;
    public static final int RET_CANCEL = 0;
    public static final int RET_OK = 1;
    private Osoba osoba;
    private JTextField adres1;
    private JTextField adres2;
    private JTextField adres3;
    private ButtonGroup buttonGroup1;
    private JTextField dataUrodzenia;
    private JTextField dataWydania;
    private JTextField funkcja1;
    private JTextField funkcja2;
    private JTextField imie;
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton5;
    private JButton jButton6;
    private JLabel jLabel1;
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel13;
    private JLabel jLabel15;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel9;
    private JTextField nazwisko;
    private JTextField nrLicencji;
    private JTextField stowarzyszenie1;
    private JTextField stowarzyszenie2;

    public DaneF(JFrame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.baza = ((Licencje)parent).getBazaDanych(1);
        this.osoba = new Osoba("Towarzyszaca", 1);
        setVisible(true);
    }
  
    public DaneF(Licencje parent, boolean modal, Osoba osoba) {
        super(parent, modal);
        initComponents();

        this.baza = parent.getBazaDanych(1);
        this.osoba = osoba;
    
        wstawDane(osoba);
        setVisible(true);
    }
  
    public void wstawDane(Osoba osoba) {
        this.funkcja1.setText(osoba.getFunkcja1());
        this.funkcja2.setText(osoba.getFunkcja2());
        this.nazwisko.setText(osoba.getNazwisko());
        this.imie.setText(osoba.getImie());
        this.stowarzyszenie1.setText(osoba.getStowarzyszenie1());
        this.stowarzyszenie2.setText(osoba.getStowarzyszenie2());
        this.nrLicencji.setText(osoba.getNrLicencji());
        this.dataUrodzenia.setText(osoba.getDataUrodzenia());
        this.adres1.setText(osoba.getAdres1());
        this.adres2.setText(osoba.getAdres2());
        this.adres3.setText(osoba.getAdres3());
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
        this.jLabel3 = new JLabel();
        this.jLabel4 = new JLabel();
        this.jLabel5 = new JLabel();
        this.funkcja1 = new JTextField();
        this.funkcja2 = new JTextField();
        this.nazwisko = new JTextField();
        this.imie = new JTextField();
        this.stowarzyszenie1 = new JTextField();
        this.stowarzyszenie2 = new JTextField();
        this.jLabel9 = new JLabel();
        this.jLabel10 = new JLabel();
        this.jLabel11 = new JLabel();
        this.jLabel13 = new JLabel();
        this.nrLicencji = new JTextField();
        this.dataUrodzenia = new JTextField();
        this.adres1 = new JTextField();
        this.adres2 = new JTextField();
        this.adres3 = new JTextField();
        this.dataWydania = new JTextField();
        this.jButton1 = new JButton();
        this.jButton2 = new JButton();
        this.jLabel15 = new JLabel();
        this.jButton5 = new JButton();
        this.jButton6 = new JButton();

        setDefaultCloseOperation(2);
        setTitle("Wprowadź dane");

        this.jLabel1.setText("Funkcja:");
        this.jLabel3.setText("Nazwisko:");    
        this.jLabel4.setText("Imię:");   
        this.jLabel5.setText("Stowarzyszenie:");    
        this.jLabel9.setText("Nr licencji:");    
        this.jLabel10.setText("Data urodzenia:");    
        this.jLabel11.setText("Adres zamieszkania:");    
        this.jLabel13.setText("Data wydania:");    
        this.jButton1.setText("OK");
        this.jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DaneF.this.jButton1ActionPerformed(evt);
            }
        });
        this.jButton2.setText("Wstaw datę");
        this.jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DaneF.this.jButton2ActionPerformed(evt);
            }
        });
        this.jLabel15.setText("Wstaw dziesiejszą datę wydania:");
    
        this.jButton5.setText("Anuluj");
        this.jButton5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DaneF.this.jButton5ActionPerformed(evt);
            }
        });
        this.jButton6.setText("POBIERZ DANE Z BAZY DANYCH (dane.xls)");
        this.jButton6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DaneF.this.jButton6ActionPerformed(evt);
            }
        });
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jButton6, -1, -1, 32767).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel1).addComponent(this.jLabel3).addComponent(this.jLabel4).addComponent(this.jLabel5)).addGap(20, 20, 20).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.funkcja2, -1, 144, 32767).addComponent(this.nazwisko).addComponent(this.imie).addComponent(this.stowarzyszenie2).addComponent(this.stowarzyszenie1).addComponent(this.funkcja1)).addGap(10, 10, 10).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel9).addComponent(this.jLabel10).addComponent(this.jLabel11)).addGap(8, 8, 8).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.nrLicencji, -1, 117, 32767).addComponent(this.dataUrodzenia).addComponent(this.adres1).addComponent(this.adres2).addComponent(this.adres3))).addGroup(layout.createSequentialGroup().addComponent(this.jLabel13).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 35, 32767).addComponent(this.dataWydania, -2, 117, -2)))).addGroup(layout.createSequentialGroup().addComponent(this.jLabel15).addGap(18, 18, 18).addComponent(this.jButton2, -2, 141, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.jButton5).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton1, -2, 55, -2))).addContainerGap()));   
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel1).addComponent(this.funkcja1, -2, -1, -2).addComponent(this.jLabel9)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.funkcja2, -2, -1, -2).addComponent(this.jLabel10)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel3).addComponent(this.nazwisko, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel4).addComponent(this.imie, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel5).addComponent(this.stowarzyszenie1, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.stowarzyszenie2, -2, -1, -2).addComponent(this.jLabel13).addComponent(this.dataWydania, -2, -1, -2))).addGroup(layout.createSequentialGroup().addComponent(this.nrLicencji, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.dataUrodzenia, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.adres1, -2, -1, -2).addComponent(this.jLabel11)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.adres2, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.adres3, -2, -1, -2))).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton6).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel15).addComponent(this.jButton2)).addGap(29, 29, 29)).addGroup(GroupLayout.Alignment.TRAILING, layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton5).addComponent(this.jButton1))).addContainerGap(-1, 32767)));
 
        pack();
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
        this.osoba.setFunkcja1(this.funkcja1.getText());
        this.osoba.setFunkcja2(this.funkcja2.getText());
        this.osoba.setNazwisko(this.nazwisko.getText());
        this.osoba.setImie(this.imie.getText());
        this.osoba.setStowarzyszenie1(this.stowarzyszenie1.getText());
        this.osoba.setStowarzyszenie2(this.stowarzyszenie2.getText());
        this.osoba.setNrLicencji(this.nrLicencji.getText());
        this.osoba.setDataUrodzenia(this.dataUrodzenia.getText());
        this.osoba.setAdres1(this.adres1.getText());
        this.osoba.setAdres2(this.adres2.getText());
        this.osoba.setAdres3(this.adres3.getText());
        this.osoba.setDataWydania(this.dataWydania.getText());
        this.osoba.rysujDane();
        doClose(1);
    }
  
    private void jButton5ActionPerformed(ActionEvent evt) {
        doClose(0);
    }
  
    private void jButton6ActionPerformed(ActionEvent evt) {
        Szukaj szukaj = new Szukaj(this, true, this.baza, 1);
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
            DaneF dialog = new DaneF(new Licencje(), true);
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