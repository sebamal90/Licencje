package licencje;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.GroupLayout;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

public class Informacja extends JDialog {
    public static final int RET_CANCEL = 0;
    public static final int RET_OK = 1;
    public static final int RET_OK_TLO = 2;
    private JButton cancelButton;
    private JLabel jLabel1;
    private JPanel jPanel1;
    private JButton okButton;
    private JCheckBox tlo;
    private int returnStatus = 0;
  
    public Informacja(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        String cancelName = "cancel";
        InputMap inputMap = getRootPane().getInputMap(1);
        inputMap.put(KeyStroke.getKeyStroke(27, 0), cancelName);
        ActionMap actionMap = getRootPane().getActionMap();
        actionMap.put(cancelName, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                Informacja.this.doClose(0);
            }
        });
        setVisible(true);
    }
  
    public int getReturnStatus() {
        return this.returnStatus;
    }
  
    private void initComponents() {
        this.okButton = new JButton();
        this.cancelButton = new JButton();
        this.tlo = new JCheckBox();
        this.jPanel1 = new JPanel();
        this.jLabel1 = new JLabel();

        setTitle("Informacja");
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                Informacja.this.closeDialog(evt);
            }
        });
        this.okButton.setText("OK");
        this.okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Informacja.this.okButtonActionPerformed(evt);
            }
        });
        this.cancelButton.setText("Cancel");
        this.cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Informacja.this.cancelButtonActionPerformed(evt);
            }
        });
        this.tlo.setText("Dodaj tło");
    
        this.jPanel1.setBackground(new Color(255, 255, 255));
    
        this.jLabel1.setFont(new Font("Arial", 0, 12));
        this.jLabel1.setText("<html><b>INFORMACJE:</b><br>\n Obraz jest wygenerowany do wydrukowania jako strona A4.<br>\nW ustawieniech drukowania należy <b>pamietać o ustawieniu skalowania na \"BRAK\"\nlub w ustwaieniach drukarki w ustawieniach strony w sekcji układ strony na \"Bez obramowania\". \n(W przypadku opcji szerokość rozszerzenia - ustawić na minimalną)</b>\nW przypadku wydruku z marginesami, wydruk może odbiegać od oczekiwań.\nMożna również obraz wkleić do edytora tekstu (np. MS Word)\ni odpowiednio ustawić obraz bez marginesów.<br><br>\n Przed wydrukowaniem na drukach upewnić się że wyszstko jest OK!<br>\n(np. drukując na zwykłej kartce i porównując pod światło)</html>");
        this.jLabel1.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));

        GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel1, -2, 442, -2));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel1, -1, 185, 32767));
    
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap(-1, 32767).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(layout.createSequentialGroup().addComponent(this.tlo).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.okButton, -2, 67, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.cancelButton)).addComponent(this.jPanel1, -2, -1, -2)).addContainerGap()));    
        layout.linkSize(0, new Component[] { this.cancelButton, this.okButton });
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(26, 26, 26).addComponent(this.jPanel1, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.tlo).addComponent(this.okButton).addComponent(this.cancelButton)).addContainerGap(29, 32767)));    
        getRootPane().setDefaultButton(this.okButton);
    
        pack();
    }
  
    private void okButtonActionPerformed(ActionEvent evt) {
        if (this.tlo.isSelected()) {
            doClose(2);
        } else {
            doClose(1);
        }
    }
  
    private void cancelButtonActionPerformed(ActionEvent evt) {
        doClose(0);
    }
  
    private void closeDialog(WindowEvent evt) {
        doClose(0);
    }
  
    private void doClose(int retStatus) {
        this.returnStatus = retStatus;
        setVisible(false);
        dispose();
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
            Logger.getLogger(Informacja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Informacja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Informacja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Informacja.class.getName()).log(Level.SEVERE, null, ex);
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Informacja dialog = new Informacja(new JFrame(), true);
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