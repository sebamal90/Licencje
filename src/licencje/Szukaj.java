package licencje;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

public class Szukaj extends JDialog {
    List<Osoba> magazyn;
    final TableRowSorter<MyModel> sorter;
    private int returnStatus = 0;
    public static final int RET_CANCEL = 0;
    public static final int RET_OK = 1;
    private int selected = -1;
    private int select;
    private JButton anuluj;
    private JTextField filtrImie;
    private JTextField filtrKlub;
    private JTextField filtrKodUCI;
    private JTextField filtrNazwisko;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JPanel jPanel1;
    private JScrollPane jScrollPane2;
    private JButton ok;
    private JTable table;

    public Szukaj(JDialog parent, boolean modal, List magazyn, int select) {
        super(parent, modal);
        initComponents();
        this.magazyn = magazyn;
        this.select = select;
        MyModel modelNew;
        if (select == 0) {
            modelNew = new MyModel(new Object[0][], new String[] { "Id", "Nazwisko", "Imę", "KodUCI", "Klub" });
        } else {
            modelNew = new MyModel(new Object[0][], new String[] { "Id", "Nazwisko", "Imę", "Data ur" });
        }
        Iterator iter = magazyn.iterator();

        int i = 1;
        while (iter.hasNext()) {
            Osoba os = (Osoba)iter.next();
            if (select == 0) {
              modelNew.addRow(new Object[] { i + "", os.getNazwisko(), os.getImie(), Integer.valueOf(os.getKodUCI()), os.getKlub() });
            } else {
              modelNew.addRow(new Object[] { i + "", os.getNazwisko(), os.getImie(), os.getDataUrodzenia() });
            }
            i++;
        }
        this.table.setModel(modelNew);

        TableColumn column = null;
        column = this.table.getColumnModel().getColumn(0);
        column.setPreferredWidth(20);
        column = this.table.getColumnModel().getColumn(1);
        column.setPreferredWidth(130);
        column = this.table.getColumnModel().getColumn(2);
        column.setPreferredWidth(100);
        column = this.table.getColumnModel().getColumn(3);
        column.setPreferredWidth(55);
        if (select == 0) {
            column = this.table.getColumnModel().getColumn(4);
            column.setPreferredWidth(180);
        }
        this.sorter = new TableRowSorter(modelNew);
        this.table.setRowSorter(this.sorter);
        if (select == 1) {
            this.jLabel3.setText("Data ur.");
            this.jLabel4.setVisible(false);
            this.filtrKlub.setVisible(false);
        }
        setVisible(true);
    }

    private void initComponents() {
        this.jScrollPane2 = new JScrollPane();
        this.table = new JTable();
        this.jPanel1 = new JPanel();
        this.jLabel1 = new JLabel();
        this.filtrNazwisko = new JTextField();
        this.jLabel2 = new JLabel();
        this.filtrImie = new JTextField();
        this.jLabel3 = new JLabel();
        this.filtrKodUCI = new JTextField();
        this.jLabel4 = new JLabel();
        this.filtrKlub = new JTextField();
        this.ok = new JButton();
        this.anuluj = new JButton();

        setDefaultCloseOperation(2);
        setTitle("Szukaj w bazie");
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                Szukaj.this.formMousePressed(evt);
            }
        });
        this.table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null } }, new String[] { "Id", "Nazwisko", "Imię", "Kod UCI", "Klub" }) {
            boolean[] canEdit = { false, false, false, false, false };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.jScrollPane2.setViewportView(this.table);
        this.jPanel1.setBorder(BorderFactory.createTitledBorder("Filtr"));
        this.jLabel1.setText("Nazwisko:");
        this.filtrNazwisko.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                Szukaj.this.filtrNazwiskoKeyReleased(evt);
            }
        });
        this.jLabel2.setText("Imię:");
        this.filtrImie.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                Szukaj.this.filtrImieKeyReleased(evt);
            }
        });
        this.jLabel3.setText("KodUCI:");
        this.filtrKodUCI.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                Szukaj.this.filtrKodUCIKeyReleased(evt);
            }
        });
        this.jLabel4.setText("Klub:");
        this.filtrKlub.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                Szukaj.this.filtrKlubKeyReleased(evt);
            }
        });
        GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.filtrNazwisko, -2, 91, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.filtrImie, -2, 105, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel3).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.filtrKodUCI, -2, 105, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel4).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.filtrKlub, -2, 105, -2).addContainerGap(-1, 32767)));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel1).addComponent(this.filtrNazwisko, -2, -1, -2).addComponent(this.jLabel2).addComponent(this.jLabel3).addComponent(this.filtrKodUCI, -2, -1, -2).addComponent(this.jLabel4).addComponent(this.filtrKlub, -2, -1, -2).addComponent(this.filtrImie, -2, -1, -2)).addContainerGap(-1, 32767)));
        this.ok.setText("OK");
        this.ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
              Szukaj.this.okActionPerformed(evt);
            }
        });
        this.anuluj.setText("Anuluj");
        this.anuluj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Szukaj.this.anulujActionPerformed(evt);
            }
        });
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.jScrollPane2, GroupLayout.Alignment.LEADING, -1, 602, 32767).addGroup(GroupLayout.Alignment.LEADING, layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel1, -2, -1, -2).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addComponent(this.anuluj).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.ok, -2, 62, -2)))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel1, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jScrollPane2, -1, 220, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.ok).addComponent(this.anuluj)).addContainerGap()));

        pack();
    }

    private void filtrImieKeyReleased(KeyEvent evt) {
        filtr();
    }

    private void filtrKodUCIKeyReleased(KeyEvent evt) {
        filtr();
    }

    private void filtrKlubKeyReleased(KeyEvent evt) {
        filtr();
    }

    private void filtrNazwiskoKeyReleased(KeyEvent evt) {
        filtr();
    }

    private void anulujActionPerformed(ActionEvent evt) {
        doClose(0);
    }

    private void okActionPerformed(ActionEvent evt) {
        if (this.table.getSelectedRow() >= 0) {
            this.selected = Integer.parseInt((String)this.table.getValueAt(this.table.getSelectedRow(), 0));
        }
        this.selected -= 1;
        doClose(1);
    }

    private void formMousePressed(MouseEvent evt) {
        ListSelectionModel model = this.table.getSelectionModel();
        model.removeSelectionInterval(0, this.table.getRowCount() - 1);
    }

    private void filtr() {
        List<RowFilter<Object, Object>> filters = new ArrayList(2);
        filters.add(RowFilter.regexFilter(this.filtrNazwisko.getText(), new int[] { 1 }));
        filters.add(RowFilter.regexFilter(this.filtrImie.getText(), new int[] { 2 }));
        filters.add(RowFilter.regexFilter(this.filtrKodUCI.getText(), new int[] { 3 }));
        if (this.select == 0) {
            filters.add(RowFilter.regexFilter(this.filtrKlub.getText(), new int[] { 4 }));
        }
        RowFilter<Object, Object> rowFilter = RowFilter.andFilter(filters);
        this.sorter.setRowFilter(rowFilter);
    }

    private void doClose(int retStatus) {
        this.returnStatus = retStatus;
        setVisible(false);
        dispose();
    }

    public int getReturnStatus() {
        return this.returnStatus;
    }

    public int getSelected() {
        return this.selected;
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
              if ("Nimbus".equals(info.getName()))
              {
                UIManager.setLookAndFeel(info.getClassName());
                break;
              }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Szukaj.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Szukaj.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Szukaj.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Szukaj.class.getName()).log(Level.SEVERE, null, ex);
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Szukaj dialog = new Szukaj(new JDialog(), true, new BazaDanych().getMagazyn(0), 0);
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