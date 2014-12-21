package licencje;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class Nowy extends JDialog {
    private int returnstatus = 0;
    private int selected = 0;
    private DocumentBuilderFactory factory;
    private DocumentBuilder builder;
    private Document doc;
    private List<Osoba> lista = new LinkedList();
    private String typ;
    private int akcja = 0;
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JLabel jLabel1;
    private JRadioButton jRadioButton1;
    private JRadioButton jRadioButton3;
    private ButtonGroup rodzaj;
    private JPanel rysunek;
    private String imageSource = "/licencjaZAWODNIKm.png";

    public Nowy(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTitle("Nowy arkusz");
        setResizable(false);
        setDefaultCloseOperation(2);
        setLocationRelativeTo(null);
        setVisible(true);
    }
  
    public Nowy(Frame parent, boolean modal, int akcja) {
        super(parent, modal);
        initComponents();
        setTitle("Nowy arkusz");
        setResizable(false);
        setDefaultCloseOperation(2);
        setLocationRelativeTo(null);

        this.akcja = akcja;
        if (akcja == 1) {
            importuj();
        } else {
            if (akcja == 2) {
                this.jButton3.setVisible(false);
            }
            setVisible(true);
        }
    }
  
    public int getReturnStatus() {
        return this.returnstatus;
    }
  
    public int getSelected() {
        return this.selected;
    }
  
    private void initComponents() {
        this.rodzaj = new ButtonGroup();
        this.jRadioButton1 = new JRadioButton();
        this.jRadioButton3 = new JRadioButton();
        this.jButton1 = new JButton();
        this.jButton2 = new JButton();
        this.jLabel1 = new JLabel();
        this.rysunek = new JPanel() {
            public void paint(Graphics g2) {
                super.paint(g2);

                BufferedImage image = new BufferedImage(400, 226, 1);
                try {
                  URL myurl = getClass().getResource(Nowy.this.imageSource);
                  image = ImageIO.read(myurl);
                  g2.drawImage(image, 0, 0, 400, 226, this);
                } catch (IOException ex) {}
            }
        };
        this.jButton3 = new JButton();    
        setDefaultCloseOperation(2);
        this.rodzaj.add(this.jRadioButton1);
        this.jRadioButton1.setSelected(true);
        this.jRadioButton1.setText("Zawodnik");
        this.jRadioButton1.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                Nowy.this.jRadioButton1StateChanged(evt);
            }
        });
        this.rodzaj.add(this.jRadioButton3);
        this.jRadioButton3.setText("Osoba towarzysząca");
        this.jRadioButton3.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                Nowy.this.jRadioButton3StateChanged(evt);
            }
        });
        this.jButton1.setText("OK");
        this.jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Nowy.this.jButton1ActionPerformed(evt);
            }
        });
        this.jButton2.setText("Anuluj");
        this.jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Nowy.this.jButton2ActionPerformed(evt);
            }
        });
        this.jLabel1.setFont(new Font("Tahoma", 1, 12));
        this.jLabel1.setText("Rodzaj licencji:");

        GroupLayout rysunekLayout = new GroupLayout(this.rysunek);
        this.rysunek.setLayout(rysunekLayout);
        rysunekLayout.setHorizontalGroup(rysunekLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 400, 32767));
        rysunekLayout.setVerticalGroup(rysunekLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 226, 32767));

        this.jButton3.setText("Wczytaj");
        this.jButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Nowy.this.jButton3ActionPerformed(evt);
            }
        });
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jButton3, -2, 122, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.jButton2, -2, 75, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton1, -2, 75, -2)).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jRadioButton1).addComponent(this.jRadioButton3).addComponent(this.jLabel1)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.rysunek, -2, -1, -2).addGap(0, 0, 32767))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(6, 6, 6).addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jRadioButton1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jRadioButton3)).addComponent(this.rysunek, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton1).addComponent(this.jButton2).addComponent(this.jButton3)).addContainerGap()));

        pack();
    }
  
    private void jRadioButton1StateChanged(ChangeEvent evt) {
        if (this.jRadioButton1.isSelected()) {
            this.imageSource = "/licencjaZAWODNIKm.png";
            this.selected = 0;
        }
        this.rysunek.repaint();
    }
  
    private void jRadioButton3StateChanged(ChangeEvent evt) {
        if (this.jRadioButton3.isSelected()) {
            this.imageSource = "/licencjaFUNKCJAm.png";
            this.selected = 1;
        }
        this.rysunek.repaint();
    }
  
    private void jButton2ActionPerformed(ActionEvent evt) {
        doClose(0);
    }
  
    private void jButton1ActionPerformed(ActionEvent evt) {
        doClose(1);
    }
  
    private void jButton3ActionPerformed(ActionEvent evt) {
        importuj();
    }
  
    public void newFilter(JFileChooser fc, String description, String[] extensions) {
        FileFilter filter1 = new ExtensionFileFilter(description, extensions);
        fc.setFileFilter(filter1);
    }
  
    private void doClose(int status) {
        this.returnstatus = status;
        setVisible(false);
        dispose();
    }
  
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName()))    {
                  UIManager.setLookAndFeel(info.getClassName());
                  break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Nowy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Nowy.class.getName()).log(Level.SEVERE, null, ex);
        }   catch (IllegalAccessException ex) {
            Logger.getLogger(Nowy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Nowy.class.getName()).log(Level.SEVERE, null, ex);
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Nowy dialog = new Nowy(new JFrame(), true);
                dialog.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                      System.exit(0);
                    }
                });
              dialog.setVisible(true);
            }
        });
    }
  
    private void readXML(File file) {
        this.factory = DocumentBuilderFactory.newInstance();
        try {
            this.builder = this.factory.newDocumentBuilder();
            this.doc = this.builder.parse(file);
            Element root = this.doc.getDocumentElement();
            NodeList children = root.getChildNodes();
            this.typ = "";
            for (int i = 0; i < children.getLength(); i++) {
                Node child = children.item(i);
                if ((child instanceof Element)) {
                    Element childElement = (Element)child;
                    if (childElement.getTagName().equals("rodzaj")) {
                        Text textNode = (Text)childElement.getFirstChild();
                        this.typ = textNode.getData().trim();
                    } else if (childElement.getTagName().equals("osoby")) {
                        if (this.typ.equalsIgnoreCase("ZAWODNIK")) {
                            getZawodnik(childElement);
                        } else {
                            getTowarzyszaca(childElement);
                        }
                    }
                }
            }
        } catch (ParserConfigurationException ex) {
        } catch (SAXException ex) {
        } catch (IOException ex) {
        }
    }
  
    private void getZawodnik(Element root) {
        NodeList osoby = root.getChildNodes();
        for (int i = 0; i < osoby.getLength(); i++) {
            Node child = osoby.item(i);
            if ((child instanceof Element)) {
                Osoba osoba = new Osoba("Zawodnik", 0);
                Element childElement = (Element)child;
                System.out.println(childElement.getTagName());
                NodeList dane = childElement.getChildNodes();
                for (int j = 0; j < dane.getLength(); j++) {
                    Node danaW = dane.item(j);
                    if ((danaW instanceof Element)) {
                        Element dana = (Element)danaW;

                        Text textNode = (Text)dana.getFirstChild();
                        if (textNode != null) {
                            String d = textNode.getData().trim();
                            if (dana.getTagName().equalsIgnoreCase("kategoriaUCI")) {
                                osoba.setKategoriaUCI(d);
                            } else if (dana.getTagName().equalsIgnoreCase("kategoriaNar")) {
                                osoba.setKategoriaNar(d);
                            } else if (dana.getTagName().equalsIgnoreCase("nazwisko")) {
                                osoba.setNazwisko(d);
                            } else if (dana.getTagName().equalsIgnoreCase("imie")) {
                                osoba.setImie(d);
                            } else if (dana.getTagName().equalsIgnoreCase("narodowosc")) {
                                osoba.setNarodowosc(d);
                            } else if (dana.getTagName().equalsIgnoreCase("ekipa")) {
                                osoba.setEkipa(d);
                            } else if (dana.getTagName().equalsIgnoreCase("klub")) {
                                osoba.setKlub(d);
                            } else if (dana.getTagName().equalsIgnoreCase("kodUCI")) {
                                if (!d.equals("")) {
                                    osoba.setKodUCI(Integer.parseInt(d));
                                }
                            } else if (dana.getTagName().equalsIgnoreCase("nrLicencji")) {
                                osoba.setNrLicencji(d);
                            } else if (dana.getTagName().equalsIgnoreCase("dataUrodzenia")) {
                                osoba.setDataUrodzenia(d);
                            } else if (dana.getTagName().equalsIgnoreCase("adres1")) {
                                osoba.setAdres1(d);
                            } else if (dana.getTagName().equalsIgnoreCase("adres2")) {
                                osoba.setAdres2(d);
                            } else if (dana.getTagName().equalsIgnoreCase("plec")) {
                                osoba.setPlec(d);
                            } else if (dana.getTagName().equalsIgnoreCase("dataWydania")) {
                                osoba.setDataWydania(d);
                            }
                        }
                    }
                }
                osoba.rysujDane();
                this.lista.add(osoba);
            }
        }
    }
  
    private void getTowarzyszaca(Element root) {
        NodeList osoby = root.getChildNodes();
        for (int i = 0; i < osoby.getLength(); i++) {
            Node child = osoby.item(i);
            if ((child instanceof Element)) {
                Osoba osoba = new Osoba("Towarzyszaca", 1);
                Element childElement = (Element)child;
                System.out.println(childElement.getTagName());
                NodeList dane = childElement.getChildNodes();
                for (int j = 0; j < dane.getLength(); j++) {
                    Node danaW = dane.item(j);
                    if ((danaW instanceof Element)) {
                        Element dana = (Element)danaW;

                        Text textNode = (Text)dana.getFirstChild();
                        if (textNode != null) {
                            String d = textNode.getData().trim();
                            if (dana.getTagName().equalsIgnoreCase("funkcja1")) {
                                osoba.setFunkcja1(d);
                            } else if (dana.getTagName().equalsIgnoreCase("funkacja2")) {
                                osoba.setFunkcja2(d);
                            } else if (dana.getTagName().equalsIgnoreCase("nazwisko")) {
                                osoba.setNazwisko(d);
                            } else if (dana.getTagName().equalsIgnoreCase("imie")) {
                                osoba.setImie(d);
                            } else if (dana.getTagName().equalsIgnoreCase("stowarzyszenie1")) {
                                osoba.setStowarzyszenie1(d);
                            } else if (dana.getTagName().equalsIgnoreCase("stowarzyszenie2")) {
                                osoba.setStowarzyszenie2(d);
                            } else if (dana.getTagName().equalsIgnoreCase("nrLicencji")) {
                                osoba.setNrLicencji(d);
                            } else if (dana.getTagName().equalsIgnoreCase("dataUrodzenia")) {
                                osoba.setDataUrodzenia(d);
                            } else if (dana.getTagName().equalsIgnoreCase("adres1")) {
                                osoba.setAdres1(d);
                            } else if (dana.getTagName().equalsIgnoreCase("adres2")) {
                                osoba.setAdres2(d);
                            } else if (dana.getTagName().equalsIgnoreCase("adres3")) {
                                osoba.setAdres3(d);
                            } else if (dana.getTagName().equalsIgnoreCase("dataWydania")) {
                                osoba.setDataWydania(d);
                            }
                        }
                    }
                }
                osoba.rysujDane();
                this.lista.add(osoba);
            }
        }
    }
  
    private void addList(Osoba nowa) {
        this.lista.add(nowa);
    }
  
    public List<Osoba> getLista() {
        return this.lista;
    }
  
    public void importuj() {
        JFileChooser fc = new JFileChooser();
        newFilter(fc, "xml", new String[] { "xml" });
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == 0) {
            File file = fc.getSelectedFile();
            readXML(file);
            if (this.typ.equalsIgnoreCase("ZAWODNIK")) {
                this.selected = 2;
            } else {
                this.selected = 3;
            }
            doClose(1);
        }
    }
}