package licencje;

import javax.swing.table.DefaultTableModel;

class MyModel extends DefaultTableModel {
    public MyModel(Object[][] data, Object[] col) {
        super(data, col);
    }

    public boolean isCellEditable(int row, int column) {
        return false;
    }
}