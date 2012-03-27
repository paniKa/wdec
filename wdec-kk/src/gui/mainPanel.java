/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * mainPanel.java
 *
 * Created on 2011-12-30, 23:30:48
 */

package gui;

import dane.IN;
import dane.OUT;

/**
 * 
 * @author Kasia
 */
public class mainPanel extends javax.swing.JFrame {

	private IN wejscie;
	private OUT wyjscie;

	private Wejscie wejscieTab;
	private Wyjscie wyjscieTab;
	private wykres wykresPanel;

	private javax.swing.JTabbedPane jTabbedPane1;

	/** Creates new form mainPanel */
	public mainPanel() {
		wyjscie = new OUT();
		wejscie = new IN((float) 1000000, (float) 500000, (float) 300000,
				(float) 0, (float) 10000, (float) 5000, (float) 4, (float) 4,
				(float) 18, (float) 400000);
		initComponents();
	}

	private void initComponents() {

		jTabbedPane1 = new javax.swing.JTabbedPane();

		wejscieTab = new Wejscie(wejscie, wyjscie);

		wyjscieTab = new Wyjscie(wyjscie, wejscie);
		
		wykresPanel = wejscieTab.getWykresPanel();
		wykresPanel.setWyjscie(wyjscieTab);
		wyjscieTab.setWykresPanel(wykresPanel);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jTabbedPane1.addTab("Wejscie", wejscieTab);

		wyjscieTab.setAlignmentX(1.0F);
		wyjscieTab.setAlignmentY(1.0F);

		jTabbedPane1.addTab("Wyjscie", wyjscieTab);

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(
				wykresPanel);
		wykresPanel.setLayout(jPanel3Layout);
		jTabbedPane1.addTab("Wykres", wykresPanel);


		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jTabbedPane1,
								200, 523,
								600)
						.addGap(18,18,18)));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jTabbedPane1,
								200, 560,
								840)
						.addGap(18,18,18)));
		this.setBounds(100, 100, 100, 300);
		pack();
	}

}
