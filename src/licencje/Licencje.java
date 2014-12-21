package licencje;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Licencje extends JFrame implements KeyListener {
    private Panel panel;
    private JScrollPane scrollPanel;
    private Menu menu = new Menu(this);
    private BazaDanych bazaDanych = new BazaDanych();
    private String wpisane = "";

    public Licencje() {
        setTitle("Licencje v1.3");
        URL url = getClass().getResource("/favicon.png");
        ImageIcon imaicon = new ImageIcon(url);
        Image image = imaicon.getImage();
        setIconImage(image);
        setExtendedState(getExtendedState() | 0x6);
        setMinimumSize(new Dimension(400, 400));
        setSize(800, 600);
        setDefaultCloseOperation(3);
        setLayout(new FlowLayout(1));
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        setFocusable(true);
        addKeyListener(this);
        menuGorne();
        this.menu.setPreferredSize(new Dimension(200, 300));
        add(this.menu, "West");
        setVisible(true);
        nowy(0);
    }
  
    private void nowy(int i) {
        Nowy nowy = new Nowy(this, true, i);
        if (nowy.getReturnStatus() == 1) {
            if (nowy.getSelected() % 2 == 0) {
                setTitle("Licencje v1.3 - zawodnik");
            } else if (nowy.getSelected() % 2 == 1) {
                setTitle("Licencje v1.3 - osoba towarzysząca");
            }
            this.panel = new Panel(this);
            if (i == 0) {
                this.scrollPanel = new JScrollPane(this.panel);
                add(this.scrollPanel, "Center");
                this.scrollPanel.getVerticalScrollBar().setUnitIncrement(16);
            } else {
                this.menu.usunWszystko();
                this.scrollPanel.setViewportView(this.panel);
            }
            if (nowy.getSelected() < 2) {
                this.menu.setNew(this.panel, nowy.getSelected());
            } else {
                this.menu.setNew(this.panel, nowy.getSelected() % 2, nowy.getLista());
            }
            this.panel.setImageSource(nowy.getSelected() % 2);
            this.panel.addKeyListener(this);
            this.menu.addKeyListener(this);
            repaint();
            validate();
        } else if (i == 0) {
            System.exit(0);
        }
    }
  
    public void paint(Graphics g2) {
        super.paint(g2);
        if (this.panel != null) {
            int y = this.panel.getImageY();
            int x = this.panel.getImageX();
            this.panel.setPreferredSize(new Dimension(x - 21, y - 20));
            this.panel.revalidate();
        }
    }
  
    public JScrollPane getContactsScrollPane() {
        return this.scrollPanel;
    }
  
    public List getBazaDanych(int selected) {
        return this.bazaDanych.getMagazyn(selected);
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
            Logger.getLogger(Licencje.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Licencje.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Licencje.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Licencje.class.getName()).log(Level.SEVERE, null, ex);
        }
        EventQueue.invokeLater(new Runnable() {
            public void run()
            {
              new Licencje();
            }
        });
    }
  
    private void menuGorne() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu plikMenu = new JMenu("Plik");
        menuBar.add(plikMenu);
        jMenuItemAdd("Nowy", plikMenu);
        plikMenu.addSeparator();
        jMenuItemAdd("Zapisz jako PDF", plikMenu);
        jMenuItemAdd("Zapisz jako obraz", plikMenu);
        plikMenu.addSeparator();
        jMenuItemAdd("Importuj dane", plikMenu);
        jMenuItemAdd("Eksportuj dane", plikMenu);
        plikMenu.addSeparator();
        jMenuItemAdd("Zakończ", plikMenu);
    }
  
    private void jMenuItemAdd(String name, JMenu jMenuName) {
        JMenuItem newItem = new JMenuItem(name);
        Akcja newItemListener = new Akcja(name);
        newItem.addActionListener(newItemListener);
        jMenuName.add(newItem);
    }
  
    private class Akcja implements ActionListener {
        private String zdarzenie;
    
        public Akcja(String zdarzenie) {
            this.zdarzenie = zdarzenie;
        }
    
        public void actionPerformed(ActionEvent e) {
            if (this.zdarzenie.equals("Zakończ")) {
                System.exit(0);
            } else if (this.zdarzenie.equals("Nowy")) {
                Licencje.this.nowy(2);
            } else if (this.zdarzenie.equals("Importuj dane")) {
                Licencje.this.nowy(1);
            } else if (this.zdarzenie.equals("Zapisz jako PDF")) {
                Licencje.this.menu.savePDF();
            } else if (this.zdarzenie.equals("Zapisz jako obraz")) {
                Licencje.this.menu.zapiszObraz();
            } else if (this.zdarzenie.equals("Eksportuj dane")) {
                try {
                    Licencje.this.eksportuj();
                } catch (Exception ex) {}
            }
        }
    }
  
    private void eksportuj() {
        JFileChooser fc = new JFileChooser();
        newFilter(fc, "xml", new String[] { "xml" });   
        int returnVal = fc.showSaveDialog(this);
        if (returnVal == 0) {
            File file = fc.getSelectedFile();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.newDocument();

                Element rootElement = doc.createElement("arkusz");
                doc.appendChild(rootElement);

                Element childElement = doc.createElement("rodzaj");
                Text textNode;
                if (this.panel.getImageSource().equals("/licencjaZAWODNIK.png")) {
                    textNode = doc.createTextNode("ZAWODNIK");
                } else {
                    textNode = doc.createTextNode("TOWARZYSZACA");
                }
                childElement.appendChild(textNode);
                rootElement.appendChild(childElement);

                childElement = doc.createElement("osoby");
                rootElement.appendChild(childElement);

                Iterator it = this.panel.getLista().iterator();
                while (it.hasNext()) {
                    Osoba os = (Osoba)it.next();
                    Element osobaElement = doc.createElement("osoba");
                    childElement.appendChild(osobaElement);
                    if (this.panel.getImageSource().equals("/licencjaZAWODNIK.png")) {
                        addChildZ(osobaElement, doc, "kategoriaUCI", os);
                        addChildZ(osobaElement, doc, "kategoriaNar", os);
                        addChildZ(osobaElement, doc, "nazwisko", os);
                        addChildZ(osobaElement, doc, "imie", os);
                        addChildZ(osobaElement, doc, "narodowosc", os);
                        addChildZ(osobaElement, doc, "ekipa", os);
                        addChildZ(osobaElement, doc, "klub", os);
                        addChildZ(osobaElement, doc, "kodUCI", os);
                        addChildZ(osobaElement, doc, "nrLicencji", os);
                        addChildZ(osobaElement, doc, "dataUrodzenia", os);
                        addChildZ(osobaElement, doc, "adres1", os);
                        addChildZ(osobaElement, doc, "adres2", os);
                        addChildZ(osobaElement, doc, "plec", os);
                        addChildZ(osobaElement, doc, "dataWydania", os);
                    } else {
                        addChildT(osobaElement, doc, "funkcja1", os);
                        addChildT(osobaElement, doc, "funkcja2", os);
                        addChildT(osobaElement, doc, "nazwisko", os);
                        addChildT(osobaElement, doc, "imie", os);
                        addChildT(osobaElement, doc, "stowarzyszenie1", os);
                        addChildT(osobaElement, doc, "stowarzyszenie2", os);
                        addChildT(osobaElement, doc, "nrLicencji", os);
                        addChildT(osobaElement, doc, "dataUrodzenia", os);
                        addChildT(osobaElement, doc, "adres1", os);
                        addChildT(osobaElement, doc, "adres2", os);
                        addChildT(osobaElement, doc, "adres3", os);
                        addChildT(osobaElement, doc, "dataWydania", os);
                    }
                    try {
                        Transformer t = TransformerFactory.newInstance().newTransformer();
                        t.setOutputProperty("indent", "yes");
                        t.setOutputProperty("method", "xml");
                        t.setOutputProperty("{http://xml.apache.org/xslt}ident-amount", "2");
                        t.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(new File(file.getAbsolutePath() + ".xml"))));
                    } catch (TransformerException ex) {
                        Logger.getLogger(Licencje.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Licencje.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (ParserConfigurationException ex) {}
        }
    }
  
    private void addChildT(Element osobaElement, Document doc, String pole, Osoba os) {
        Element nowy = doc.createElement(pole);
        Text textNode;
        if (pole.equals("funkcja1")) {
          textNode = doc.createTextNode(os.getFunkcja1());
        } else  if (pole.equals("funkcja2")) {
            textNode = doc.createTextNode(os.getFunkcja2());
        } else if (pole.equals( "nazwisko")) {
            textNode = doc.createTextNode(os.getNazwisko());
        } else if (pole.equals("imie")) {
            textNode = doc.createTextNode(os.getImie());
        } else if (pole.equals("stowarzyszenie1")) {
            textNode = doc.createTextNode(os.getStowarzyszenie1());
        } else if (pole.equals("stowarzyszenie2")) {
            textNode = doc.createTextNode(os.getStowarzyszenie2());
        } else if (pole.equals("nrLicencji")) {
            textNode = doc.createTextNode(os.getNrLicencji());
        } else if (pole.equals("dataUrodzenia")) {
            textNode = doc.createTextNode(os.getDataUrodzenia());
        } else if (pole.equals("adres1")) {
            textNode = doc.createTextNode(os.getAdres1());
        } else if (pole.equals("adres2")) {
            textNode = doc.createTextNode(os.getAdres2());
        } else if ((pole.equals("adres3")) && (os.isPlec())) {
            textNode = doc.createTextNode(os.getAdres3());
        } else {
            textNode = doc.createTextNode(os.getDataWydania());
        }
        nowy.appendChild(textNode);
        osobaElement.appendChild(nowy);
    }
  
    private void addChildZ(Element osobaElement, Document doc, String pole, Osoba os) {
        Element nowy = doc.createElement(pole);
        Text textNode;
        if (pole.equals("kategoriaUCI")) {
            textNode = doc.createTextNode(os.getKategoriaUCI());
        } else if (pole.equals("kategoriaNar")) {
            textNode = doc.createTextNode(os.getKategoriaNar());
        } else if (pole.equals("nazwisko")) {
            textNode = doc.createTextNode(os.getNazwisko());
        } else if (pole.equals("imie")) {
            textNode = doc.createTextNode(os.getImie());
        } else if (pole.equals("narodowosc")) {
            textNode = doc.createTextNode(os.getNarodowosc());
        } else if (pole.equals("ekipa")) {
            textNode = doc.createTextNode(os.getEkipa());
        } else if (pole.equals("klub")) {
            textNode = doc.createTextNode(os.getKlub());
        } else if (pole.equals("kodUCI")) {
            textNode = doc.createTextNode(os.getKodUCI() + "");
        } else if (pole.equals("nrLicencji")) {
            textNode = doc.createTextNode(os.getNrLicencji());
        } else if (pole.equals("dataUrodzenia")) {
            textNode = doc.createTextNode(os.getDataUrodzenia());
        } else if (pole.equals("adres1")) {
            textNode = doc.createTextNode(os.getAdres1()); 
        } else if (pole.equals("adres2")) {
            textNode = doc.createTextNode(os.getAdres2());
        } else if ((pole.equals("plec")) && (os.isPlec())) {
            textNode = doc.createTextNode("M");
        } else if ((pole.equals("plec")) && (!os.isPlec())) {
            textNode = doc.createTextNode("K");
        } else {
            textNode = doc.createTextNode(os.getDataWydania());
        }
        nowy.appendChild(textNode);
        osobaElement.appendChild(nowy);
    }
  
    private void newFilter(JFileChooser fc, String description, String[] extensions) {
        FileFilter filter1 = new ExtensionFileFilter(description, extensions);
        fc.setFileFilter(filter1);
    }

    public void keyTyped(KeyEvent e) {
        this.wpisane += e.getKeyChar();
        if (this.wpisane.matches(".*\\bsmalec\\b.*")) {
            this.panel.setCheat();
            this.wpisane = "";
            repaint();
            validate();
        }
    }
  
    public void keyPressed(KeyEvent e) {}
  
    public void keyReleased(KeyEvent e) {}
}