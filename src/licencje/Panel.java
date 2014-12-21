package licencje;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.JPanel;

public class Panel extends JPanel implements KeyListener {
    private String imageSource = "";
    private String imageSource2 = "";
    private BufferedImage image = null;
    private double imageX;
    private double imageY;
    private int rozdzielczoscX;
    private int rozdzielczoscY;
    private int rozdzielczoscOldX;
    private int rozdzielczoscOldY;
    private Licencje licencje;
    private String rok;
    private List<Osoba> lista = new LinkedList();
    private int kolor;
    private double stosunek;
    private boolean cheat = true;

    public Panel(Licencje parent) {
        initComponents();
        this.licencje = parent;
        Calendar z = Calendar.getInstance();
        this.rok = (z.get(1) + "");
        addKeyListener(this);
    }
    
    public void paint(Graphics g2) {
        super.paint(g2);
        Graphics2D g = (Graphics2D)g2;
        this.rozdzielczoscX = this.licencje.getContactsScrollPane().getWidth();
        this.rozdzielczoscY = this.licencje.getContactsScrollPane().getHeight();
        if ((this.rozdzielczoscOldX != this.rozdzielczoscX) || (this.rozdzielczoscOldY != this.rozdzielczoscY) || (!this.imageSource.equals(this.imageSource2))) {
            this.rozdzielczoscOldX = this.rozdzielczoscX;
            this.rozdzielczoscOldY = this.rozdzielczoscY;
            if ((this.image == null) || (!this.imageSource.equals(this.imageSource2))) {
                this.image = new BufferedImage(this.rozdzielczoscX, this.rozdzielczoscY, 1);
                Graphics2D g3 = this.image.createGraphics();
                g3.setColor(Color.white);
                g3.fillRect(0, 0, this.rozdzielczoscX, this.rozdzielczoscY);
                g3.setColor(Color.black);
                try {
                    URL myurl = this.licencje.getClass().getResource(this.imageSource);
                    this.image = ImageIO.read(myurl);
                    this.imageSource2 = this.imageSource;
                } catch (IOException ex) {}
            }
            this.imageX = (this.image.getWidth() * 2);
            this.imageY = (this.image.getHeight() * 5);
            this.stosunek = (this.imageX / this.imageY);
            while ((this.imageX > this.rozdzielczoscX) && (this.imageY > this.rozdzielczoscY)) {
                this.imageX -= 1.0D;
                this.imageY = (this.imageX / this.stosunek);
            }
            this.stosunek = (this.image.getWidth(this.licencje) * 2 / this.imageX);
        }
        rysujTlo(g, this.stosunek);
        rysujWszystkieDane(g, this.stosunek);
    }

    public void rysujTlo(Graphics2D g, double stosunek) {
        if (this.kolor == 0) {
            g.setColor(new Color(210, 60, 72));
        } else if (this.kolor == 1) {
            g.setColor(new Color(93, 209, 144));
        } else if (this.kolor == 2) {
            g.setColor(Color.white);
        } else if (this.kolor == 3) {
            g.setColor(new Color(242, 195, 31));
        } else {
            g.setColor(new Color(83, 185, 217));
        }
        g.fillRect(0, 0, (int)(this.image.getWidth() * 2 / stosunek), (int)(this.image.getHeight() * 5 / stosunek));
        g.setColor(Color.black);

        g.drawLine((int)(1000.0D / stosunek), 0, (int)(1000.0D / stosunek), 3000);
        g.drawLine(0, (int)(566.0D / stosunek), 2000, (int)(566.0D / stosunek));
        g.drawLine(0, (int)(1132.0D / stosunek), 2000, (int)(1132.0D / stosunek));
        g.drawLine(0, (int)(1698.0D / stosunek), 2000, (int)(1698.0D / stosunek));
        g.drawLine(0, (int)(2264.0D / stosunek), 2000, (int)(2264.0D / stosunek));

        Font f = new Font("Arial", 0, (int)(100.0D / stosunek));
        Font f2 = new Font("Arial", 0, (int)(200.0D / stosunek));

        AffineTransform at2 = new AffineTransform();
        at2.setToRotation(0.5235987755982988D);

        BufferedImage image2 = new BufferedImage((int)(1000.0D / stosunek), (int)(600.0D / stosunek), 2);
        Graphics2D g2 = image2.createGraphics();
        g2.setColor(new Color(0.5F, 0.5F, 0.5F, 0.25F));
        g2.setFont(f2);
        g2.setTransform(at2);
        if (this.cheat) {
            g2.drawString("SZABLON", (int)(95.0D / stosunek), (int)(85.0D / stosunek));
        }
        g.setFont(f);
        int k = 0;
        for (int i = 0; i < 10;) {
            g.setColor(Color.black);
            g.drawImage(this.image, (int)(i % 2 * 1000 / stosunek), (int)(k * 566 / stosunek), (int)(this.image.getWidth() / stosunek), (int)(this.image.getHeight() / stosunek), this);
            g.drawString(this.rok, (int)((i % 2 * 1000 + 748) / stosunek), (int)((k * 566 + 98) / stosunek));
            g.drawImage(image2, (int)(i % 2 * 1000 / stosunek), (int)(k * 566 / stosunek), (int)(this.image.getWidth() / stosunek), (int)(this.image.getHeight() / stosunek), this);

            i++;
            if (i % 2 == 0) {
                k++;
            }
        }
    }

    public void setRok(String rok) {
        this.rok = rok;
    }

    public void setCheat() {
        this.cheat = (!this.cheat);
    }

    public void setImageSource(int i) {
        if (i == 0) {
            this.imageSource = "/licencjaZAWODNIK.png";
        } else {
            this.imageSource = "/licencjaFUNKCJA.png";
        }
        repaint();
    }

    public void rysujWszystkieDane(Graphics2D g, double stosunek) {
        Iterator iter = this.lista.iterator();
        int i = 0;
        int j = 0;
        while (iter.hasNext()) {
            Osoba os = (Osoba)iter.next();
            g.drawImage(os.getImage(), (int)(i % 2 * 1000 / stosunek), (int)(j * 566 / stosunek), (int)(os.getWidth() / stosunek), (int)(os.getHeight() / stosunek), this);
            i++;
            if (i % 2 == 0) {
                j++;
            }
        }
    }

    public void addList(Osoba nowa) {
        this.lista.add(nowa);
    }

    public List getLista() {
        return this.lista;
    }

    public BufferedImage zapiszDoPliku(int status) {
        int szer = this.image.getWidth() * 2;
        int wys = this.image.getHeight() * 5;

        BufferedImage bi = new BufferedImage(szer, wys, 1);
        Graphics2D sg = bi.createGraphics();

        sg.setColor(Color.white);
        sg.fillRect(0, 0, szer, wys);
        if (status == 2) {
            rysujTlo(sg, 1.0D);
        }
        rysujWszystkieDane(sg, 1.0D);

        return bi;
    }

    public int getImageX() {
        return (int)this.imageX;
    }

    public int getImageY() {
        return (int)this.imageY;
    }

    public void setRozdzielczosc(int x, int y) {
        this.rozdzielczoscX = x;
        this.rozdzielczoscY = y;
        setSize(x, y);
    }

    private void initComponents() {
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 400, 32767));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 300, 32767));
    }

    void setKolor(int idx) {
        this.kolor = idx;
    }

    public String getImageSource() {
        return this.imageSource;
    }

    public void keyTyped(KeyEvent e) {
        System.out.println("a");
    }

    public void keyPressed(KeyEvent e) {
        System.out.println("a");
    }

    public void keyReleased(KeyEvent e) {
        System.out.println("a");
    }
}