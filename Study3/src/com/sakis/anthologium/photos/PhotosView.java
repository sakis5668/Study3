package com.sakis.anthologium.photos;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;
import com.jfoenix.controls.JFXTextField;
import com.sakis.anthologium.util.DisplayShelf;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class PhotosView extends Pane {
	
	

	HBox bigHBox;
	VBox leftVBox, rightVBox;
	JFXButton btn1, btn2, btn3;
	JFXTextField searchTextField;
	DisplayShelf displayShelf;
	
	ObservableList<Image> images;

	public PhotosView(ObservableList<Image> images) {
		
		this.images = images;
		
		// first get the style sheet
		final String cssString = getClass().getResource("/com/sakis/anthologium/css/StyleSheet.css").toExternalForm();
		getStylesheets().add(cssString);

		// set the style class for MainBorderPane
		getStyleClass().add("photooverview-pane");
		
		
		// Left VBox
		leftVBox = new VBox();
		leftVBox.setId("leftvbox");
		
		ImageView searchButtonView = new ImageView(new Image(getClass().getResourceAsStream("/com/sakis/anthologium/images/search.png")));
		
		searchTextField = new JFXTextField();
		searchTextField.setPromptText("Actor to look for ...");
		//earchTextField.addEventHandler(KeyEvent, eventHandler);
		
		HBox innerHBox = new HBox();
		innerHBox.setId("innerhbox");
		innerHBox.getChildren().addAll(searchButtonView, searchTextField);
		
		displayShelf = new DisplayShelf(this.images);
		displayShelf.setPrefSize(300, 600);
		
		leftVBox.getChildren().addAll(innerHBox, displayShelf);
		
		// Right VBox
		
		rightVBox = new VBox();
		rightVBox.setId("rightvbox");
		
		btn1 = new JFXButton("New");
		btn1.setButtonType(ButtonType.RAISED);
		
		btn2 = new JFXButton("Edit");
		btn2.setButtonType(ButtonType.RAISED);
		
		btn3 = new JFXButton("Delete");
		btn3.setButtonType(ButtonType.RAISED);
		
		rightVBox.getChildren().addAll(btn1, btn2, btn3);
		
		// Now put all together
		
		bigHBox = new HBox();
		bigHBox.getStyleClass().add("testbox");
		bigHBox.getChildren().addAll(leftVBox, rightVBox);
		getChildren().add(bigHBox);
		
	}
	
	public void addKeyEventHandler (EventHandler<KeyEvent> keyEventHandler)
	{
		this.searchTextField.addEventHandler(KeyEvent.KEY_PRESSED,  keyEventHandler);
	}
	
	public JFXTextField getSearchTextField() {
		return searchTextField;
	}

	public ObservableList<Image> getImages() {
		return images;
	}

	public void setImages(ObservableList<Image> images) {
		this.images = images;
	}

}
