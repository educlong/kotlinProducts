package elong.ui;

import elong.io.ProductsJsonFileFactory;
import elong.models.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ProductsUI {
    private JPanel pnMain;
    private JTable tblProducts;
    private DefaultTableModel modelProduct;
    private JTextField txtMaSp;
    private JTextField txtTenSp;
    private JTextField txtDonGia;
    private JButton btnSave;
    private JButton btnContinue;
    private JButton btnDelete;
    private JButton btnExit;
    private JMenuBar mnuBar;
    private JMenu mnuSystem;
    private JMenuItem mnuSystemSaveFile;
    private JMenuItem mnuSystemReadFile;
    private JMenuItem mnuSystemExit;
    private List<Product> products=new ArrayList<>();

    public ProductsUI() {
        setImageForButton();
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitProcess();
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveProduct();
            }
        });
        btnContinue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                continueProduct();
            }
        });
        tblProducts.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int row=tblProducts.getSelectedRow();
                if (row>=0){
                    Product product =products.get(tblProducts.getSelectedRow());
                    txtMaSp.setText(product.getMaSp());
                    txtTenSp.setText(product.getTenSp());
                    txtDonGia.setText(product.getDonGia()+"");
                }
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteProduct();
            }
        });
    }

    private void exitProcess() {
        int result =JOptionPane.showConfirmDialog(null,
                "Exit","Answer the question?",
                JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION)
            System.exit(0);
    }

    private void continueProduct() {
        txtMaSp.setText("");
        txtTenSp.setText("");
        txtDonGia.setText("");
        txtMaSp.requestFocus();
    }

    private void deleteProduct() {
        if (tblProducts.getSelectedRow()>=0) {
            products.remove(tblProducts.getSelectedRow());
            displayProducts();
            continueProduct();
        }
    }

    private void saveProduct() {
        Product product =new Product();
        product.setMaSp(txtMaSp.getText());
        product.setTenSp(txtTenSp.getText());
        product.setDonGia(Double.parseDouble(txtDonGia.getText()));
        products.add(product);
        displayProducts();
    }

    public JPanel getPnMain() {
        return pnMain;
    }
    private void displayProducts(){
        modelProduct.setRowCount(0);
        for(Product product : products){
            Vector<Object> vec=new Vector<Object>();
            vec.add(product.getMaSp());
            vec.add(product.getTenSp());
            vec.add(product.getDonGia());
            modelProduct.addRow(vec);
        }
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        modelProduct=new DefaultTableModel();
        modelProduct.addColumn("Code Product");
        modelProduct.addColumn("Name Product");
        modelProduct.addColumn("Price");
        tblProducts=new JTable(modelProduct);
    }
    private void setImageForButton(){
        btnSave.setIcon(new ImageIcon("images/save.png"));
        btnContinue.setIcon(new ImageIcon("images/clear.png"));
        btnDelete.setIcon(new ImageIcon("images/delete.png"));
        btnExit.setIcon(new ImageIcon("images/exit.png"));
    }
    public void createMenu(JFrame jparents) {
        mnuBar=new JMenuBar();
        mnuSystem=new JMenu("System");
        mnuSystem.setIcon(new ImageIcon("images/system.png"));
        mnuBar.add(mnuSystem);
        mnuSystemSaveFile=new JMenuItem("Save File");
        mnuSystemSaveFile.setIcon(new ImageIcon("images/save.png"));
        mnuSystem.add(mnuSystemSaveFile);
        mnuSystem.addSeparator();
        mnuSystemReadFile=new JMenuItem("Read File");
        mnuSystemReadFile.setIcon(new ImageIcon("images/readFile.png"));
        mnuSystem.add(mnuSystemReadFile);
        mnuSystem.addSeparator();
        mnuSystemExit=new JMenuItem("Exit");
        mnuSystemExit.setIcon(new ImageIcon("images/exit.png"));
        mnuSystem.add(mnuSystemExit);
        jparents.setJMenuBar(mnuBar);
        mnuSystemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                exitProcess();
                btnExit.doClick();
            }
        });
        mnuSystemSaveFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile();
            }
        });
        mnuSystemReadFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readFile();
            }
        });
    }

    private void readFile() {
        JFileChooser jFileChooser = new JFileChooser();
        if (jFileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
            String path = jFileChooser.getSelectedFile().getAbsolutePath();
            ProductsJsonFileFactory pjff = new ProductsJsonFileFactory();
            products=pjff.ReadFile(path);
            displayProducts();
        }
    }

    private void saveFile() {
        JFileChooser jFileChooser = new JFileChooser();
        if (jFileChooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
            String path=jFileChooser.getSelectedFile().getAbsolutePath();
            ProductsJsonFileFactory pjff=new ProductsJsonFileFactory();
            if (pjff.SaveFiles(products,path)==true){
                JOptionPane.showMessageDialog(null,"Saved");
            }
            else{
                JOptionPane.showMessageDialog(null,"Error");
            }
        }
    }
}
