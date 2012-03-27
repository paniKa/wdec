package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;

import dane.IN;
import dane.OUT;

public class Wyjscie extends JPanel{
	
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    
    
    private javax.swing.JButton wyjscieButton;
    private javax.swing.JSpinner jakosc;
    private javax.swing.JSpinner kredyt;
    private javax.swing.JSpinner magazyn;
    private javax.swing.JSpinner tv;
    private javax.swing.JSpinner wolumen;
    private javax.swing.JSpinner internet;
    private javax.swing.JSpinner cena;
	
    private wykres wykresPanel;
	public void setWykresPanel(wykres wykresPanel) {
		this.wykresPanel = wykresPanel;
	}

	private JLabel infoLabel;
	private JLabel zyskLabel;
	private JLabel ryzykoLabel;
	private JLabel koszt_zmLabel;
	private JLabel koszt_cLabel;
	

	private JTextArea zysk;
	private JTextArea ryzyko;
	private JTextArea koszt_zm;
	private JTextArea koszt_c;
	
    private OUT wyjscie;
    private IN wejscie;
    private Wyjscie wyjsciePanel;
    
    
	public Wyjscie(OUT wyjscie, IN wejscie){
		this.wyjscie = wyjscie;
		this.wejscie = wejscie;
		initComponents();
		
	}

	private void initComponents(){

        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        
        infoLabel = new javax.swing.JLabel();
        zyskLabel = new javax.swing.JLabel();
        ryzykoLabel = new javax.swing.JLabel();
        koszt_zmLabel = new javax.swing.JLabel();
        koszt_cLabel = new javax.swing.JLabel();
        
        jLabel12.setText("Jakosc");
        jLabel13.setText("Wolumen");
        jLabel14.setText("Cena");
        jLabel15.setText("Reklama tv");
        jLabel16.setText("Reklama magazyn");
        jLabel17.setText("Reklama internet");
        jLabel18.setText("Kredyt pobrany");
        
        ryzyko = new JTextArea();
        zysk = new JTextArea();
        koszt_zm = new JTextArea();
        koszt_c = new JTextArea();
        
        infoLabel.setText("Wyliczone wartosci:");
        zyskLabel.setText("Zysk");
        ryzykoLabel.setText("Ryzyko");
        koszt_zmLabel.setText("Koszt jednoskowy zmienny");
        koszt_cLabel.setText("Koszt calkowity");
        
        wejscie.generujPkt(wyjscie);
        zysk.setText(Float.toString(wejscie.getZysk()));
        ryzyko.setText(Float.toString(wejscie.getRyzyko()));
        koszt_zm.setText(Float.toString(wejscie.getKosztZm()));
        koszt_c.setText(Float.toString(wejscie.getKosztStaly()));
        
        
        wyjscieButton = new JButton();
        wyjscieButton.setText("Uaktualnienie wykresu");
        wyjscieButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				wyjscie.setCena((Float)cena.getValue());
				wyjscie.setJakosc((Float)jakosc.getValue());
				wyjscie.setWolumen((Float)wolumen.getValue());
				
				wyjscie.setReklamaTv((Float)tv.getValue());
				wyjscie.setReklamaNet((Float)internet.getValue());
				wyjscie.setReklamaMagazyn((Float)magazyn.getValue());
				
				wyjscie.setKredyt((Float)kredyt.getValue());
				
				
				wejscie.generujPkt(wyjscie);
		        zysk.setText(Float.toString(wejscie.getZysk()));
		        ryzyko.setText(Float.toString(wejscie.getRyzyko()));
		        koszt_zm.setText(Float.toString(wejscie.getKosztZm()));
		        koszt_c.setText(Float.toString(wejscie.getKosztStaly()));
		        wykresPanel.changeButtonPanel();
			}
        });
        
        zysk.setDisabledTextColor(Color.black);
        zysk.setAlignmentX(RIGHT_ALIGNMENT);
        
        
        
        jakosc = new javax.swing.JSpinner();
        jakosc.setValue(wyjscie.getJakosc());
        jakosc.setEditor(new JSpinner.NumberEditor(jakosc,  "###,###,##0.00"));

        wolumen = new javax.swing.JSpinner();
        wolumen.setValue(wyjscie.getWolumen());
        wolumen.setEditor(new JSpinner.NumberEditor(wolumen,  "###,###,##0.00"));

        cena = new javax.swing.JSpinner();
        cena.setValue(wyjscie.getCena());
        cena.setEditor(new JSpinner.NumberEditor(cena,  "###,###,##0.00"));

        tv = new javax.swing.JSpinner();
        tv.setValue(wyjscie.getReklamaTv());
        tv.setEditor(new JSpinner.NumberEditor(tv,  "###,###,##0.00"));

        magazyn = new javax.swing.JSpinner();
        magazyn.setValue(wyjscie.getReklamaMagazyn());
        magazyn.setEditor(new JSpinner.NumberEditor(magazyn,  "###,###,##0.00"));

        internet = new javax.swing.JSpinner();
        internet.setValue(wyjscie.getReklamaNet());
        internet.setEditor(new JSpinner.NumberEditor(internet,  "###,###,##0.00"));

        kredyt = new javax.swing.JSpinner();
        kredyt.setValue(wyjscie.getKredyt());
        kredyt.setEditor(new JSpinner.NumberEditor(kredyt,  "###,###,##0.00"));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(this);
        this.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
            	.addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                	.addComponent(wyjscieButton)
                    .addGroup(jPanel2Layout.createSequentialGroup()      
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addGap(5, 5, 5)
                            .addComponent(infoLabel)
                            .addComponent(zyskLabel)
                            .addComponent(ryzykoLabel)
                            .addComponent(koszt_zmLabel)
                            .addComponent(koszt_cLabel)
                        	)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(kredyt)
                            .addComponent(internet)
                            .addComponent(magazyn)
                            .addComponent(tv)
                            .addComponent(cena)
                            .addComponent(wolumen)
                            .addComponent(jakosc)
                            .addComponent(zysk)
                            .addComponent(ryzyko)
                            .addComponent(koszt_zm)
                            .addComponent(koszt_c,javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))))
                .addGap(360, 360, 360))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jakosc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(wolumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(cena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(tv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(magazyn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(internet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(kredyt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                 .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(infoLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(zyskLabel)
                    .addComponent(zysk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                 .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ryzykoLabel)
                    .addComponent(ryzyko, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                 .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(koszt_zmLabel)
                    .addComponent(koszt_zm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                  .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(koszt_cLabel)
                    .addComponent(koszt_c, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addGap(33, 33, 33)
                .addComponent(wyjscieButton)
                .addGap(33, 33, 33))
        );
        
	}
	
	public void setValues(){

        jakosc.setValue(wyjscie.getJakosc());
        jakosc.setEditor(new JSpinner.NumberEditor(jakosc,  "###,###,##0.00"));


        wolumen.setValue(wyjscie.getWolumen());
        wolumen.setEditor(new JSpinner.NumberEditor(wolumen,  "###,###,##0.00"));

        cena.setValue(wyjscie.getCena());
        cena.setEditor(new JSpinner.NumberEditor(cena,  "###,###,##0.00"));


        tv.setValue(wyjscie.getReklamaTv());
        tv.setEditor(new JSpinner.NumberEditor(tv,  "###,###,##0.00"));


        magazyn.setValue(wyjscie.getReklamaMagazyn());
        magazyn.setEditor(new JSpinner.NumberEditor(magazyn,  "###,###,##0.00"));


        internet.setValue(wyjscie.getReklamaNet());
        internet.setEditor(new JSpinner.NumberEditor(internet,  "###,###,##0.00"));

        kredyt.setValue(wyjscie.getKredyt());
        kredyt.setEditor(new JSpinner.NumberEditor(kredyt,  "###,###,##0.00"));
        
	}
	
	public void setZyskText(Float t){
		zysk.setText(Float.toString(t));
	}
	
	public void setRyzykoText(Float t){
		ryzyko.setText(Float.toString(t));
	}
	
	public void setKosztZmText(Float t){
		koszt_zm.setText(Float.toString(t));
	}
	
	public void setKosztCText(Float t){
		koszt_c.setText(Float.toString(t));
	}

		
}
