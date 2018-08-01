package de.jfxsandbox.application;

import java.io.IOException;

import de.jfxsandbox.controller.SandboxController;
import de.jfxsandbox.moddel.Person;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * The main class to launch the App
 * 
 * @author Bastian Bräunel
 *
 */
public class MainApp extends Application{

	private Stage primaryStage;
	private BorderPane rootLayout;
	private ObservableList<Person> personList = FXCollections.observableArrayList();
	
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * This method get called when the JFX App is startet 
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("JavaFX 8 Event Handling Examples");
		// create some data
		bootstrapData();
		// initialize the root stage
		initRootLayout();
		// load the view with the sandboxed items
		showSandboxView();
	}

	/**
	 * Add some data to the list
	 */
	private void bootstrapData() {
		personList.add(new Person("Hans", "Muster"));
		personList.add(new Person("Ruth", "Mueller"));
		personList.add(new Person("Heinz", "Kurz"));
		personList.add(new Person("Cornelia", "Meier"));
	}

	/**
	 * Render the primary stage with a new scene on top
	 */
	private void initRootLayout() {
		try {
			// load the layout from the fxml file
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			// create a new scene
			Scene scene = new Scene(rootLayout);
			// set the rootLayout to be the stage of the new scene
			primaryStage.setScene(scene);
			// render the scene
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Open the view with the demo objects
	 */
	private void showSandboxView() {
		try {
			// load the sandbox view
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/SandboxView.fxml"));
			AnchorPane sandboxLayout = (AnchorPane)loader.load();
			
			// position this view inside the root layout
			rootLayout.setCenter(sandboxLayout);
			
			// load the referenced controller for the view
			SandboxController controller = loader.getController();
			// put a reference to view
			controller.setReferenceToMainApp(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the primary stage
	 * 
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	/**
	 * Returns the observable list with the persons
	 * 
	 * @return
	 */
	public ObservableList<Person> getPersonList(){
		return personList;
	}
}
