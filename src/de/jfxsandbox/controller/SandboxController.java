package de.jfxsandbox.controller;

import de.jfxsandbox.application.MainApp;
import de.jfxsandbox.moddel.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

/**
 * The controller class for the SandboxView
 * 
 * @author Bastian Bräunel
 *
 */
public class SandboxController {
	
	private MainApp mainApp;
	
	@FXML
	private Button myButton;
	@FXML
	private CheckBox myCheckBox;
	@FXML
	private Slider mySlider;
	@FXML
	private TextField myTextFiled;
	@FXML
	private ListView<Person> myListView;
	@FXML
	private TextArea textAreaToFill;
	@FXML
	private ComboBox<Person> myComboBox;
	
	/**
	 * Default constructor
	 */
	public SandboxController() {
		// TODO Auto-generated constructor stub
	}
	
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
	@FXML
	private void initialize() {
		// init special components
		initComboBox();
		initListView();
		
		// register all event handlers
		hookEventhandlers();
	}
	
	/**
	 * This tells the ListView how to render our Person objects
	 */
	private void initListView() {
		// Define rendering of the list of values in ListView
		myListView.setCellFactory((list) -> {
			return new ListCell<Person>() {
				@Override
		        protected void updateItem(Person item, boolean empty) {
		            super.updateItem(item, empty);

		            if (item == null || empty) {
		                setText(null);
		            } else {
		                setText(item.getFirstName() + " " + item.getLastName());
		            }
		        }
			};
		});
	}

	/**
	 * This tells the ComboBox how to render our Person objects
	 */
	private void initComboBox() {
		// Define rendering of the list of values in ComboBox drop down. 
		myComboBox.setCellFactory((comboBox) -> {
		    return new ListCell<Person>() {
		        @Override
		        protected void updateItem(Person item, boolean empty) {
		            super.updateItem(item, empty);

		            if (item == null || empty) {
		                setText(null);
		            } else {
		                setText(item.getFirstName() + " " + item.getLastName());
		            }
		        }
		    };
		});

		// Define rendering of selected value shown in ComboBox.
		myComboBox.setConverter(new StringConverter<Person>() {
		    @Override
		    public String toString(Person person) {
		        if (person == null) {
		            return null;
		        } else {
		            return person.getFirstName() + " " + person.getLastName();
		        }
		    }

		    @Override
		    public Person fromString(String personString) {
		        return null; // No conversion fromString needed.
		    }
		});
	}
	
	/**
	 *  Register all event handler
	 */
	private void hookEventhandlers() {
		// Handle Button event
		myButton.setOnAction((event) -> {
		    // Button was clicked, do something...
			textAreaToFill.appendText("Button Action\n");
		});
		
		// Handle CheckBox events
		myCheckBox.setOnAction((event) -> {
			if (myCheckBox.isSelected()) {
				textAreaToFill.appendText("CheckBox is now checked.\n");
			}else {
				textAreaToFill.appendText("CheckBox is now unchecked.\n");
			}
		});
		
		// Handle ComboBox event.
		myComboBox.setOnAction((event) -> {
		    Person selectedPerson = myComboBox.getSelectionModel().getSelectedItem();
		    textAreaToFill.appendText("ComboBox Action (selected: " + selectedPerson.toString() + ")\n");
		});
		
		// Handle ListView with Change Listener
		myListView.getSelectionModel()
			// gives us access to this property
			.selectedItemProperty()
			// expects a ChangeListener every time something the selection changes
			.addListener((observable, oldValue, newValue) -> {
				textAreaToFill.appendText("ListView Selection Changed (selected: " + newValue.toString() + ")");
		});
		
	}
		
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
	public void setReferenceToMainApp(MainApp app) {
		this.mainApp = app;
		
		// init items for the combo box
		myComboBox.setItems(mainApp.getPersonList());
		
		// init items for the list view
		myListView.setItems(mainApp.getPersonList());
	}
}
