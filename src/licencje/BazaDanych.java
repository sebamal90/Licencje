package licencje;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFileChooser;
import jxl.Cell;
import jxl.CellType;
import jxl.LabelCell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
 
public class BazaDanych {
    private JFileChooser fc;
    private List<Osoba> magazynZaw = new LinkedList();
    private List<Osoba> magazynFun = new LinkedList();
   
    public BazaDanych() {
        try {
            importuj();
        }
        catch (Exception ex) {}
    }
   
    public List getMagazyn(int selected) {
        if (selected == 0) {
          return this.magazynZaw;
        }
        return this.magazynFun;
    }
   
   public void importuj() throws IOException, BiffException {
        Workbook workbook = Workbook.getWorkbook(new File("daneZawodnik.xls"));
        Sheet sheet = workbook.getSheet(0);

        this.magazynZaw.removeAll(this.magazynZaw);
        for (int i = 1; i < sheet.getRows(); i++) {
            Cell idC = sheet.getCell(0, i);
            Cell nazwiskoC = sheet.getCell(1, i);
            Cell imieC = sheet.getCell(2, i);
            Cell narC = sheet.getCell(3, i);
            Cell ekipaC = sheet.getCell(4, i);
            Cell klubC = sheet.getCell(5, i);
            Cell kodUCIC = sheet.getCell(6, i);
            Cell adres1C = sheet.getCell(7, i);
            Cell adres2C = sheet.getCell(8, i);
            Cell plecC = sheet.getCell(9, i);
            Cell nrLicC = sheet.getCell(10, i);
            Cell dataWydC = sheet.getCell(11, i);
       
            Osoba osoba = new Osoba("Zawodnik", 0);
            if (nazwiskoC.getType() == CellType.LABEL) {
                osoba.setNazwisko(((LabelCell)nazwiskoC).getString());
            }
            if (imieC.getType() == CellType.LABEL) {
                osoba.setImie(((LabelCell)imieC).getString());
            }
            if (narC.getType() == CellType.LABEL) {
                osoba.setNarodowosc(((LabelCell)narC).getString());
            }
            if (ekipaC.getType() == CellType.LABEL) {
                osoba.setEkipa(((LabelCell)ekipaC).getString());
            }
            if (klubC.getType() == CellType.LABEL) {
                osoba.setKlub(((LabelCell)klubC).getString());
            }
            if (kodUCIC.getType() == CellType.NUMBER) {
                osoba.setKodUCI((int)((NumberCell)kodUCIC).getValue());
            }
            if (adres1C.getType() == CellType.LABEL) {
                osoba.setAdres1(((LabelCell)adres1C).getString());
            }
            if (adres2C.getType() == CellType.LABEL) {
                osoba.setAdres2(((LabelCell)adres2C).getString());
            }
            if (plecC.getType() == CellType.LABEL) {
                osoba.setPlec(((LabelCell)plecC).getString());
            }
            if (nrLicC.getType() == CellType.LABEL) {
                osoba.setNrLicencji(((LabelCell)nrLicC).getString());
            }
            if (dataWydC.getType() == CellType.LABEL) {
                osoba.setDataWydania(((LabelCell)dataWydC).getString());
            }
            osoba.genKategoria();
            osoba.genDataUr();

            this.magazynZaw.add(osoba);
        }
        workbook.close();
     
 
        Workbook workbook2 = Workbook.getWorkbook(new File("daneFunkcja.xls"));
        Sheet sheet2 = workbook2.getSheet(0);
    
        this.magazynFun.removeAll(this.magazynFun);
        for (int i = 1; i < sheet2.getRows(); i++) {
            Cell idC = sheet2.getCell(0, i);
            Cell nazwiskoC = sheet2.getCell(1, i);
            Cell imieC = sheet2.getCell(2, i);
            Cell fun1C = sheet2.getCell(3, i);
            Cell fun2C = sheet2.getCell(4, i);
            Cell stow1C = sheet2.getCell(5, i);
            Cell stow2C = sheet2.getCell(6, i);
            Cell dataC = sheet2.getCell(7, i);
            Cell adres1C = sheet2.getCell(8, i);
            Cell adres2C = sheet2.getCell(9, i);
            Cell adres3C = sheet2.getCell(10, i);
            Cell nrLicC = sheet2.getCell(11, i);
            Cell dataWydC = sheet2.getCell(12, i);

            Osoba osoba = new Osoba("Towarzyszaca", 1);
            if (nazwiskoC.getType() == CellType.LABEL) {
                osoba.setNazwisko(((LabelCell)nazwiskoC).getString());
            }
            if (imieC.getType() == CellType.LABEL) {
                osoba.setImie(((LabelCell)imieC).getString());
            }
            if (fun1C.getType() == CellType.LABEL) {
                osoba.setFunkcja1(((LabelCell)fun1C).getString());
            }
            if (fun2C.getType() == CellType.LABEL) {
                osoba.setFunkcja2(((LabelCell)fun2C).getString());
            }
            if (stow1C.getType() == CellType.LABEL) {
                osoba.setStowarzyszenie1(((LabelCell)stow1C).getString());
            }
            if (stow2C.getType() == CellType.LABEL) {
                osoba.setStowarzyszenie2(((LabelCell)stow2C).getString());
            }
            if (dataC.getType() == CellType.LABEL) {
                osoba.setDataUrodzenia(((LabelCell)dataC).getString());
            }
            if (adres1C.getType() == CellType.LABEL) {
                osoba.setAdres1(((LabelCell)adres1C).getString());
            }
            if (adres2C.getType() == CellType.LABEL) {
                osoba.setAdres2(((LabelCell)adres2C).getString());
            }
            if (adres3C.getType() == CellType.LABEL) {
                osoba.setAdres3(((LabelCell)adres3C).getString());
            }
            if (nrLicC.getType() == CellType.LABEL) {
                osoba.setNrLicencji(((LabelCell)nrLicC).getString());
            }
            if (dataWydC.getType() == CellType.LABEL) {
                osoba.setDataWydania(((LabelCell)dataWydC).getString());
            }
            this.magazynFun.add(osoba);
        }
        workbook.close();
    }
}