package licencje;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Calendar;
import javax.swing.JPanel;

public class Osoba extends JPanel {
    private String kategoriaUCI = "";
    private String kategoriaNar = "";
    private String nazwisko = "";
    private String imie = "";
    private String narodowosc = "";
    private String ekipa = "";
    private String klub = "";
    private int kodUCI;
    private String nrLicencji = "";
    private String dataUrodzenia = "";
    private String adres1 = "";
    private String adres2 = "";
    private String adres3 = "";
    private boolean plec = true;
    private String dataWydania = "";
    private BufferedImage image2;
    private String funkcja = "";
    private String funkcja1 = "";
    private String funkcja2 = "";
    private String stowarzyszenie1 = "";
    private String stowarzyszenie2 = "";
    private int selected;
  
    public Osoba(String funkcja, int selecte) {
        this.selected = selecte;
        this.funkcja = funkcja;
        setSize(1000, 566);
    }
  
    public BufferedImage getImage() {
        return this.image2;
    }
  
    public void rysujDane() {
        this.image2 = new BufferedImage(getWidth(), getHeight(), 2);
        Graphics2D g = this.image2.createGraphics();
        g.setColor(Color.black);
        Font f = new Font("Arial", 0, 30);
        g.setFont(f);
        if ((this.funkcja.equals("Zawodnik")) || (this.selected == 0)) {
            g.drawString(this.kategoriaUCI, 240, 180);
            g.drawString(this.kategoriaNar, 320, 238);
            g.drawString(this.nazwisko, 180, 296);
            g.drawString(this.imie, 130, 354);
            g.drawString(this.narodowosc, 230, 412);
            g.drawString(this.ekipa, 130, 470);
            g.drawString(this.klub, 110, 520);
            if (this.kodUCI != 0) {
                g.drawString("" + this.kodUCI, 710, 180);
            } else {
                g.drawString("", 710, 180);
            }
            g.drawString(this.nrLicencji, 700, 238);
            g.drawString(this.dataUrodzenia, 760, 296);
            g.drawString(this.adres1, 650, 380);
            g.drawString(this.adres2, 580, 412);

            g.drawString(this.dataWydania, 740, 520);
        } else if ((this.funkcja.equals("Towarzyszaca")) || (this.selected == 1)) {
            g.drawString(this.funkcja1, 170, 185);
            g.drawString(this.funkcja2, 80, 242);
            g.drawString(this.nazwisko, 175, 301);
            g.drawString(this.imie, 120, 370);
            g.drawString(this.stowarzyszenie1, 230, 438);
            g.drawString(this.stowarzyszenie2, 80, 500);

            g.drawString(this.nrLicencji, 700, 185);
            g.drawString(this.dataUrodzenia, 760, 255);
            g.drawString(this.adres1, 650, 332);
            g.drawString(this.adres2, 590, 380);
            g.drawString(this.adres3, 590, 428);

            g.drawString(this.dataWydania, 740, 500);
        }
    }
  
    public void genKategoria() {
        Calendar z = Calendar.getInstance();
        int rok = z.get(1);

        int dataUr = this.kodUCI;
        if ((dataUr > 19000000) && (dataUr < 21000000)) {
            int wiek = rok - dataUr / 10000;

            String kategoria = "";
            if (this.plec) {
                if (wiek < 13) {
                    kategoria = "ŻAK";
                } else if (wiek < 15) {
                    kategoria = "MŁODZIK";
                } else if (wiek < 17) {
                    kategoria = "JUNIOR MŁ.";
                } else if (wiek < 19) {
                    this.kategoriaNar = "JUNIOR";
                    this.kategoriaUCI = "HOMMES JUNIOR";
                } else {
                    this.kategoriaNar = "MASTERS";
                    this.kategoriaUCI = "HOMMES MASTER";
                }
            } else if (wiek < 13) {
                kategoria = "ŻACZKA";
            } else if (wiek < 15) {
                kategoria = "MŁODZICZKA";
            } else if (wiek < 17) {
                kategoria = "JUNIORKA MŁ.";
            } else if (wiek < 19) {
                this.kategoriaNar = "JUNIORKA";
                this.kategoriaUCI = "FEMMES JUNIOR";
            } else {
                this.kategoriaNar = "MASTERS";
                this.kategoriaUCI = "FEMMES MASTER";
            }   if (!kategoria.equals("")) {
                this.kategoriaNar = kategoria;
                this.kategoriaUCI = kategoria;
            }
        }
    }
  
    public void genDataUr() {
        int dataUr = this.kodUCI;
        if ((dataUr > 19000000) && (dataUr < 21000000)) {
            int rok = dataUr / 10000;
            int miesiac = (dataUr - rok * 10000) / 100;
            int dzien = dataUr - rok * 10000 - miesiac * 100;
            if (miesiac < 10) {
                this.dataUrodzenia = (dzien + ".0" + miesiac + "." + rok);
            } else {
                this.dataUrodzenia = (dzien + "." + miesiac + "." + rok);
            }
        }
    }
  
    public void setAdres1(String adres1) {
        this.adres1 = adres1;
    }
  
    public void setAdres2(String adres2) {
        this.adres2 = adres2;
    }
  
    public void setEkipa(String ekipa) {
        this.ekipa = ekipa;
    }
  
    public void setImie(String imie) {
        this.imie = imie;
    }
  
    public void setKlub(String klub) {
        this.klub = klub;
    }
  
    public void setKodUCI(int kodUCI) {
        this.kodUCI = kodUCI;
    }
  
    public void setNarodowosc(String narodowosc) {
        this.narodowosc = narodowosc;
    }
  
    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }
  
    public void setPlec(String string) {
        if (string.equalsIgnoreCase("M")) {
            this.plec = true;
        } else {
            this.plec = false;
        }
    }
  
    public void setDataUrodzenia(String dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }
  
    public void setDataWydania(String dataWydania) {
        this.dataWydania = dataWydania;
    }
  
    public void setKategoriaNar(String kategoriaNar) {
        this.kategoriaNar = kategoriaNar;
    }
  
    public void setKategoriaUCI(String kategoriaUCI) {
        this.kategoriaUCI = kategoriaUCI;
    }
  
    public void setNrLicencji(String nrLicencji) {
        this.nrLicencji = nrLicencji;
    }
  
    public void setPlec(boolean plec) {
        this.plec = plec;
    }
  
    public void setFunkcja1(String funkcja1) {
        this.funkcja1 = funkcja1;
    }
  
    public void setFunkcja2(String funkcja2) {
        this.funkcja2 = funkcja2;
    }

    public void setAdres3(String adres3) {
        this.adres3 = adres3;
    }

    public String getImie() {
        return this.imie;
    }

    public int getKodUCI() {
        return this.kodUCI;
    }

    public String getNazwisko() {
        return this.nazwisko;
    }

    public String getAdres1() {
        return this.adres1;
    }

    public String getAdres2() {
        return this.adres2;
    }

    public String getDataUrodzenia() {
        return this.dataUrodzenia;
    }

    public String getDataWydania() {
        return this.dataWydania;
    }

    public String getKategoriaNar() {
        return this.kategoriaNar;
    }

    public String getKategoriaUCI() {
        return this.kategoriaUCI;
    }

    public String getKlub() {
        return this.klub;
    }

    public String getNarodowosc() {
        return this.narodowosc;
    }

    public String getNrLicencji() {
        return this.nrLicencji;
    }

    public boolean isPlec() {
        return this.plec;
    }

    public String getEkipa() {
        return this.ekipa;
    }

    public void setFunkcja(String funkcja) {
        this.funkcja = funkcja;
    }

    public void setStowarzyszenie1(String stowarzyszenie1) {
        this.stowarzyszenie1 = stowarzyszenie1;
    }

    public void setStowarzyszenie2(String stowarzyszenie2) {
        this.stowarzyszenie2 = stowarzyszenie2;
    }

    public String getAdres3() {
        return this.adres3;
    }

    public String getFunkcja1() {
        return this.funkcja1;
    }

    public String getFunkcja2() {
        return this.funkcja2;
    }

    public String getStowarzyszenie1() {
        return this.stowarzyszenie1;
    }

    public String getStowarzyszenie2() {
        return this.stowarzyszenie2;
    }
}