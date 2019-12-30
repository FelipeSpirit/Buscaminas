package com.setupteam.buscaminas.view;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * 
 * @author Andrés Felipe Chaparro Rosas
 * @version 1
 * @date 14/09/2019
 */
public class StatsPanel extends JPanel {
	private JLabel timeLabel;

	public StatsPanel() {
		this.timeLabel= new JLabel("Tiempo");
		this.init();
	}

	private void init() {
		this.setLayout(new BorderLayout());
		this.add(this.timeLabel, BorderLayout.NORTH);
	}
	
	public void setTime(String timeLabel) {
		this.timeLabel.setText(timeLabel);
	}
}
