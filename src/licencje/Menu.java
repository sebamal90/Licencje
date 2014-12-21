package licencje;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileFilter;

public class Menu extends JPanel {
    private JFileChooser fc;
    private String sciezkaOdczytana;
    private Panel panel;
    private Licencje licencje;
    private DefaultListModel listModel = new DefaultListModel();
    private Calendar z = Calendar.getInstance();
    private int selected;
    private ButtonGroup buttonGroup1;
    private JButton dodajB;
    private JButton edytuj;
    private JButton generujB;
    private JButton jButton1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JScrollPane jScrollPane1;
    private JSeparator jSeparator1;
    private JSeparator jSeparator2;
    private JComboBox kolorBox;
    private JList listaOsob;
    private JTextField rok;
    private JButton usun;
    private JButton zapiszB;

    public Menu(Licencje parent) {
        initComponents();
        setSize(250, 400);
        this.licencje = parent;
        this.rok.setText(this.z.get(1) + "");
    }
  
    public void setNew(Panel pan, int selecte) {
        this.panel = pan;
        this.kolorBox.setSelectedIndex(getIndex(this.z.get(1)));
        this.selected = selecte;
    }
  
    public void setNew(Panel pan, int selecte, List<Osoba> lista) {
        this.panel = pan;
        this.kolorBox.setSelectedIndex(getIndex(this.z.get(1)));
        this.selected = selecte;

        Iterator it = lista.iterator();
        while (it.hasNext()) {
          Osoba os = (Osoba)it.next();
          dodaj(os);
        }
        this.licencje.repaint();
    }
  
    private void initComponents() {
        this.buttonGroup1 = new ButtonGroup();
        this.dodajB = new JButton();
        this.jScrollPane1 = new JScrollPane();
        this.listaOsob = new JList();
        this.zapiszB = new JButton();
        this.edytuj = new JButton();
        this.usun = new JButton();
        this.jLabel1 = new JLabel();
        this.jSeparator1 = new JSeparator();
        this.kolorBox = new JComboBox();
        this.jLabel2 = new JLabel();
        this.rok = new JTextField();
        this.jButton1 = new JButton();
        this.jSeparator2 = new JSeparator();
        this.generujB = new JButton();

        setMinimumSize(new Dimension(150, 0));
        setPreferredSize(new Dimension(150, 398));
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                Menu.this.formMouseClicked(evt);
            }
        });
        this.dodajB.setFont(new Font("Arial", 0, 11));
        this.dodajB.setText("Dodaj");
        this.dodajB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Menu.this.dodajBActionPerformed(evt);
            }
        });
        this.listaOsob.setSelectionMode(0);
        this.jScrollPane1.setViewportView(this.listaOsob);
    
        this.zapiszB.setFont(new Font("Arial", 0, 11));
        this.zapiszB.setText("Zapisz jako obraz");
        this.zapiszB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Menu.this.zapiszBActionPerformed(evt);
            }
        });
        this.edytuj.setFont(new Font("Arial", 0, 11));
        this.edytuj.setText("Edytuj");
        this.edytuj.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Menu.this.edytujActionPerformed(evt);
            }
        });
        this.usun.setFont(new Font("Arial", 0, 11));
        this.usun.setText("Usuń");
        this.usun.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Menu.this.usunActionPerformed(evt);
            }
        });
        this.jLabel1.setFont(new Font("Arial", 0, 11));
        this.jLabel1.setText("<html> <b>Wersja:</b> 1.2 (16.03.2013)<br> <b>Autor:</b> Sebastian Malecki<br> <b>Kontakt:</b> seba_mal@op.pl<br> Wszelkie prawa zastrzeżone<br> 19.01.2012r. </html> ");
    
        this.kolorBox.setFont(new Font("Arial", 0, 11));
        this.kolorBox.setModel(new DefaultComboBoxModel(new String[] { "czerwone", "zielone", "białe", "zółte", "niebieskie" }));
        this.kolorBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                Menu.this.kolorBoxItemStateChanged(evt);
            }
        });
        this.jLabel2.setFont(new Font("Arial", 0, 11));
        this.jLabel2.setText("Rok:");

        this.rok.setColumns(2);
    
        this.jButton1.setFont(new Font("Arial", 0, 11));
        this.jButton1.setText("Ustaw");
        this.jButton1.setMargin(new Insets(2, 5, 2, 5));
        this.jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Menu.this.jButton1ActionPerformed(evt);
            }
        });
        this.generujB.setText("Zapisz jako pdf");
        this.generujB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Menu.this.generujBActionPerformed(evt);
            }
        });
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane1, GroupLayout.Alignment.TRAILING, -2, 0, 32767).addGroup(layout.createSequentialGroup().addComponent(this.edytuj, -2, 77, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.usun, -1, -1, 32767)).addComponent(this.dodajB, -1, -1, 32767).addComponent(this.jLabel1, -2, 0, 32767).addComponent(this.jSeparator1, GroupLayout.Alignment.TRAILING).addComponent(this.jSeparator2).addGroup(layout.createSequentialGroup().addComponent(this.jLabel2, -2, 31, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.rok, -2, 48, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton1, -1, -1, 32767)).addComponent(this.kolorBox, GroupLayout.Alignment.TRAILING, 0, -1, 32767).addComponent(this.generujB, -1, -1, 32767).addComponent(this.zapiszB, -1, -1, 32767)).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.dodajB).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jScrollPane1, -2, 236, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.edytuj).addComponent(this.usun)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.generujB).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.zapiszB).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 27, 32767).addComponent(this.jSeparator2, -2, 10, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel2).addComponent(this.rok, -2, -1, -2).addComponent(this.jButton1)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.kolorBox, -2, -1, -2).addGap(26, 26, 26).addComponent(this.jSeparator1, -2, 10, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel1, -2, -1, -2).addContainerGap()));
    }
  
    private void zapiszBActionPerformed(ActionEvent evt) {
        zapiszObraz();
    }
  
    private void dodajBActionPerformed(ActionEvent evt) {
        if (this.selected == 0) {
            Dane dan = new Dane(this.licencje, true);
            if (dan.getReturnStatus() == 1) {
                Osoba os = dan.getOsoba();
                dodaj(os);
            }
        } else if (this.selected == 1) {
            DaneF dan = new DaneF(this.licencje, true);
            if (dan.getReturnStatus() == 1) {
                Osoba os = dan.getOsoba();
                dodaj(os);
            }
        }
    }
  
    private void dodaj(Osoba os) {
        this.panel.addList(os);

        String nazwa = os.getKodUCI() + ":" + os.getNazwisko() + ":" + os.getImie();
        this.listModel.add(this.listModel.getSize(), nazwa);
        this.listaOsob.setModel(this.listModel);

        this.licencje.repaint();
        if (this.listModel.getSize() == 10) {
            this.dodajB.setEnabled(false);
        }
    }
  
     private void usunActionPerformed(ActionEvent evt) {
        int idx = this.listaOsob.getSelectedIndex();
        if (idx >= 0) {
            int selection = 0;
            List x = this.panel.getLista();
            if (x.size() > 0) {
                selection = JOptionPane.showConfirmDialog(this, "Czy usunąć?", "", 0, 3);
            }
            if (selection == 0) {
                x.remove(idx);
                this.listModel.remove(idx);
                this.listaOsob.setModel(this.listModel);
                repaint();
                this.licencje.repaint();
            }
            if (this.listModel.getSize() < 10) {
                this.dodajB.setEnabled(true);
            }
        }
    }
  
    public void usunWszystko() {
        this.listModel.removeAllElements();
        this.listaOsob.setModel(this.listModel);
        repaint();
        this.licencje.repaint();
    }
  
    private void edytujActionPerformed(ActionEvent evt) {
        int idx = this.listaOsob.getSelectedIndex();
        if (idx >= 0) {
            if (this.selected == 0) {
                Dane dan = new Dane(this.licencje, true, (Osoba)this.panel.getLista().get(idx));
                if (dan.getReturnStatus() == 1) {
                    Osoba os = dan.getOsoba();

                    String nazwa = os.getKodUCI() + ":" + os.getNazwisko() + ":" + os.getImie();
                    this.listModel.remove(idx);
                    this.listModel.add(idx, nazwa);
                    this.listaOsob.setModel(this.listModel);

                    this.licencje.repaint();
                }
            } else {
                DaneF dan = new DaneF(this.licencje, true, (Osoba)this.panel.getLista().get(idx));
                if (dan.getReturnStatus() == 1) {
                    Osoba os = dan.getOsoba();

                    String nazwa = os.getNazwisko() + ":" + os.getImie() + ":" + os.getDataUrodzenia();
                    this.listModel.remove(idx);
                    this.listModel.add(idx, nazwa);
                    this.listaOsob.setModel(this.listModel);

                    this.licencje.repaint();
                }
            }
        }
    }
  
    private void formMouseClicked(MouseEvent evt) {
        ListSelectionModel model = this.listaOsob.getSelectionModel();
        model.removeSelectionInterval(0, 10);
    }
  
    private void kolorBoxItemStateChanged(ItemEvent evt) {
        this.panel.setKolor(this.kolorBox.getSelectedIndex());
        this.panel.repaint();
    }
  
    private void jButton1ActionPerformed(ActionEvent evt) {
        this.panel.setRok(this.rok.getText());
        this.panel.repaint();
        this.kolorBox.setSelectedIndex(getIndex(Integer.parseInt(this.rok.getText())));
    }
  
    private void generujBActionPerformed(ActionEvent evt) {
        savePDF();
    }
  
    public void savePDF() {
        Informacja inf = new Informacja(this.licencje, true);
        if (inf.getReturnStatus() >= 1) {
            zapiszPDF(inf.getReturnStatus());
        }
    }
  
    public void zapiszObraz() {
        Informacja inf = new Informacja(this.licencje, true);
        if (inf.getReturnStatus() >= 1) {
            zapisz(inf.getReturnStatus());
        }
    }
  
    public void newFilter(String description, String[] extensions) {
        FileFilter filter1 = new ExtensionFileFilter(description, extensions);
        this.fc.setFileFilter(filter1);
    }
  
    private void zapisz(int status) {
        this.fc = new JFileChooser();

        newFilter("jpg", new String[] { "jpg", "jpeg", "jpe" });
        newFilter("gif", new String[] { "gif" });
        newFilter("bmp", new String[] { "bmp" });
        newFilter("png", new String[] { "png" });

        int returnVal = this.fc.showSaveDialog(this);
        if (returnVal == 0) {
            File file = this.fc.getSelectedFile();
            this.sciezkaOdczytana = file.getAbsolutePath();
            String rozszezenie = this.fc.getFileFilter().getDescription();
            if (rozszezenie.equals("All Files")) {
                rozszezenie = "";
            }
            try {
                ImageIO.write(this.panel.zapiszDoPliku(status), rozszezenie, new File(this.sciezkaOdczytana + "." + rozszezenie));
            } catch (IOException ex) {}
        }
    }
  
    private void zapiszPDF(int status) {
        this.fc = new JFileChooser();
    
        newFilter("pdf", new String[] { "pdf" });
    
        int returnVal = this.fc.showSaveDialog(this);
        if (returnVal == 0) {
            File file = this.fc.getSelectedFile();
            this.sciezkaOdczytana = file.getAbsolutePath();
            String rozszezenie = this.fc.getFileFilter().getDescription();
            if (rozszezenie.equals("All Files")) {
              rozszezenie = "";
            }
            Document document = new Document();
            document.setPageSize(PageSize.A4);
            document.setMargins(0.0F, 0.0F, 0.0F, 0.0F);
            try {
                PdfWriter.getInstance(document, new FileOutputStream(this.sciezkaOdczytana + "." + rozszezenie));
                document.open();
                Image image1 = Image.getInstance(this.panel.zapiszDoPliku(status), Color.WHITE);
                image1.setAbsolutePosition(0.0F, 0.0F);
                image1.scaleAbsolute(595.0F, 842.0F);
                document.add(image1);
                document.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
  
    private int getIndex(int rok) {
        int idx = (rok - 2010) % 5;
        this.panel.setKolor(idx);
        return idx;
    }
}