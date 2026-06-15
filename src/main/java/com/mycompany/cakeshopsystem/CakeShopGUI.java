package com.mycompany.cakeshopsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CakeShopGUI extends JFrame {

    private JComboBox<String> cakeTypeCombo;
    private JCheckBox creamCheck;
    private JCheckBox skittlesCheck;
    private JCheckBox chocoChipsCheck;
    private JTextArea orderArea;

    private CakeOrderingSystem system;

    public CakeShopGUI() {
        system = CakeOrderingSystem.getInstance();

        CustomerDashboard customerDisplay = new CustomerDashboard();
        ManagerDashboard managerDisplay = new ManagerDashboard();

        system.addObserver(managerDisplay);
        system.addObserver(customerDisplay);

        setTitle("Cake Shop Ordering System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createUI();
    }

    private void createUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(5, 1, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        cakeTypeCombo = new JComboBox<>(new String[]{"Apple", "Chocolate", "Cheese"});

        creamCheck = new JCheckBox("Cream");
        skittlesCheck = new JCheckBox("Skittles");
        chocoChipsCheck = new JCheckBox("Chocolate Chips");

        JButton orderButton = new JButton("Place Order");
        orderButton.addActionListener((ActionEvent e) -> placeOrder());

        formPanel.add(new JLabel("Choose Cake Type:"));
        formPanel.add(cakeTypeCombo);
        formPanel.add(creamCheck);
        formPanel.add(skittlesCheck);
        formPanel.add(chocoChipsCheck);

        orderArea = new JTextArea();
        orderArea.setEditable(false);

        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(orderButton, BorderLayout.CENTER);
        mainPanel.add(new JScrollPane(orderArea), BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void placeOrder() {
        String selectedCake = cakeTypeCombo.getSelectedItem().toString().toLowerCase();

        boolean cream = creamCheck.isSelected();
        boolean skittles = skittlesCheck.isSelected();
        boolean chocoChips = chocoChipsCheck.isSelected();

        system.takeOrder(selectedCake, cream, skittles, chocoChips);

        orderArea.append("New Order:\n");
        orderArea.append("Cake Type: " + selectedCake + "\n");
        orderArea.append("Cream: " + cream + "\n");
        orderArea.append("Skittles: " + skittles + "\n");
        orderArea.append("Chocolate Chips: " + chocoChips + "\n");
        orderArea.append("-----------------------------\n");
    }
}