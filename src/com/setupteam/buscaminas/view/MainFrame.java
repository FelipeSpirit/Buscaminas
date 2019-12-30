package com.setupteam.buscaminas.view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import com.setupteam.buscaminas.controller.MenuBarListener;
import com.setupteam.buscaminas.view.game.GameAreaPanel;

/**
 * 
 * @author Andrés Felipe Chaparro Rosas
 * @version 1
 * @date 14/09/2019
 */

public class MainFrame extends JFrame implements MouseListener {
	private MainPanel mp;
	private MainMenuBar mmb;
	private HostDialog hostDialog;

	public MainFrame(GameAreaPanel gap) {
		super("Buscaminas");
		this.mp = new MainPanel(gap);
		this.mmb = new MainMenuBar();
		this.hostDialog= new HostDialog();
		this.hostDialog.setLocationRelativeTo(this);
		MenuBarListener.getInstance().setGameAreaPanel(gap);
		MenuBarListener.getInstance().setHostDialog(this.hostDialog);
		this.init();
	}
	
	public MainFrame() {
		super("Buscaminas");
		this.mp = new MainPanel();
		this.mmb = new MainMenuBar();
		this.hostDialog= new HostDialog();
		this.hostDialog.setLocationRelativeTo(this);
		MenuBarListener.getInstance().setHostDialog(this.hostDialog);
		this.init();
	}

	private void init() {
		this.setJMenuBar(mmb);
		this.add(this.mp);
		this.addMouseListener(this);
		this.setSize(600, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (this.mp.getGameAreaPanel().isEnabled())
			switch (e.getButton()) {
			case MouseEvent.BUTTON1:
				this.mp.leftClick(e.getPoint());
				break;
			case MouseEvent.BUTTON3:
				this.mp.rightClick(e.getPoint());
				break;
			}
		this.mp.repaint();
		this.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}