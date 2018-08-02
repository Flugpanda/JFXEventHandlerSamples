package de.jfxsandbox.controller;

import de.jfxsandbox.application.MainApp;
import javafx.fxml.FXML;
import javafx.scene.AmbientLight;
import javafx.scene.control.MenuItem;

/**
 * 
 * @author Bastian Bröunel
 *
 */
public class RootLayoutController {
	
	private MainApp mainApp;

	@FXML
	private MenuItem menuClose;
	
	/**
	 * Default constructor
	 */
	public RootLayoutController() {
	}
	
	/**
	 * Close the app
	 */
	@FXML
	public void handleMenuClose() {
		System.exit(0);
	}
	
	/**
	 * Set a reference to the MainApp
	 * 
	 * @param app	the reference to the main app
	 */
	public void setMainApp(MainApp app) {
		this.mainApp = app;
	}
}
