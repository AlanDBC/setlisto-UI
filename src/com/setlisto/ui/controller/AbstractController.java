package com.setlisto.ui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import com.setlisto.ui.view.View;

public abstract class AbstractController extends AbstractAction /* ActionListener*/ implements ItemListener, KeyListener, Controller { 
	
	private View view = null;
	
	public AbstractController() {			
	}
	
	public AbstractController(String name) {
		super(name);
	}
	
	public AbstractController(String name, Icon icon) {
		super(name, icon);
	}
	
	public AbstractController(String name, Icon icon, View view) {
		super(name, icon);
		this.view = view;
	}
	
	public AbstractController(View view) {
		this.view = view;
	}

	@Override
	public View getView() {
		return this.view;
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
    	doAction();
    }
    
	@Override
    public void itemStateChanged(ItemEvent e) {
		doAction();
    }


	@Override
	public void keyTyped(KeyEvent e) {
		 doAction();		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		 doAction();
	}


	@Override
	public void keyReleased(KeyEvent e) {
		doAction();		
	}
	
}
