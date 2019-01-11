package com.sakis.anthologium.main;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;
import com.sakis.anthologium.photos.PhotosController;
import com.sakis.anthologium.photos.PhotosModel;
import com.sakis.anthologium.photos.PhotosView;

import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Paint;

public class MainBorderPane extends BorderPane{
	
	private HBox topHBox;
	private ToolBar toolBar1, toolBar2;
	private JFXButton photoOverViewButton, actorsButton, songsButton, exitButton, minimizeButton;	
	
	/**
	 * Constructor
	 * This is going to draw the main application window 
	 */
	public MainBorderPane() {
		
		// first get the stylesheet 
		final String cssString = getClass().getResource("/com/sakis/anthologium/css/StyleSheet.css").toExternalForm();
		getStylesheets().add(cssString);
		
		// set the style class for MainBorderPane
		getStyleClass().add("mainborderpane");

		// *** draw the top content 
		
		// create the the 2 toolbars and set their style 
		// we need here 2 toolsbars, because one contains the button on the upper
		// right , and the second contains only the minimize nutton on nthe top left corner
		toolBar1 = new ToolBar();
		toolBar1.getStyleClass().add("toolbar");
		
		toolBar2 = new ToolBar();
		toolBar2.getStyleClass().add("toolbar");
		
		// now create the buttons 
		
		// 1. Photo overview button
		
		String url1 = "/com/sakis/anthologium/images/photos.png";
		Image img1 = new Image(getClass().getResourceAsStream(url1));
		ImageView iv1 = new ImageView(img1);
		iv1.setFitHeight(32);
		iv1.setFitWidth(32);
		iv1.setPreserveRatio(true);
		photoOverViewButton = new JFXButton("Photos", iv1);
		photoOverViewButton.setOnAction(e->handlePhotoOverview());
		photoOverViewButton.setButtonType(ButtonType.RAISED);
		
		// 2. Actors button 
		String url2 = "/com/sakis/anthologium/images/actor.png";
		ImageView iv2 = new ImageView(new Image(getClass().getResourceAsStream(url2)));
		iv2.setFitHeight(32);
		iv2.setFitWidth(32);
		iv2.setPreserveRatio(true);
		actorsButton = new JFXButton("Actors", iv2);
		actorsButton.setOnAction(e->handleActors());
		actorsButton.setButtonType(ButtonType.RAISED);
		
		// 3. Songs button
		String url3 = "/com/sakis/anthologium/images/songs.png";
		ImageView iv3 = new ImageView(new Image(getClass().getResourceAsStream(url3)));
		iv3.setFitHeight(32);
		iv3.setFitWidth(32);
		iv3.setPreserveRatio(true);
		songsButton = new JFXButton("Songs", iv3);
		songsButton.setOnAction(e->handleSongs());
		songsButton.setButtonType(ButtonType.RAISED);
		
		// 4. Exit button 
		String url4 = "/com/sakis/anthologium/images/exit.png";
		ImageView iv4 = new ImageView(new Image(getClass().getResourceAsStream(url4)));
		iv4.setFitHeight(32);
		iv4.setFitWidth(32);
		iv4.setPreserveRatio(true);
		exitButton = new JFXButton("Exit", iv4);
		exitButton.setOnAction(e-> handleExit());
		exitButton.setButtonType(ButtonType.RAISED);
		
		// 5. Minimize button 
		String url5 = "/com/sakis/anthologium/images/minimize.png";
		ImageView iv5 = new ImageView(new Image(getClass().getResourceAsStream(url5)));
		iv5.setFitHeight(32);
		iv5.setFitWidth(32);
		iv5.setPreserveRatio(true);
		minimizeButton = new JFXButton("",iv5);
		minimizeButton.setOnAction(e->handleMinimize());
		minimizeButton.setButtonType(ButtonType.RAISED);
		minimizeButton.setRipplerFill(Paint.valueOf("gray"));
		
		// Add the created buttons to the corresponding toolbars
		toolBar1.getItems().add(photoOverViewButton);
		toolBar1.getItems().add(actorsButton);
		toolBar1.getItems().add(songsButton);
		toolBar1.getItems().add(exitButton);
		toolBar2.getItems().add(minimizeButton);
		
		topHBox = new HBox();
		HBox.setHgrow(toolBar1, Priority.ALWAYS);
		topHBox.getChildren().addAll(toolBar1, toolBar2);
		
		// and finally set the the top content
		setTop(topHBox);
		
	}

	private void handlePhotoOverview() {
		setCenter(null);
		PhotosController photosController = new PhotosController(new PhotosView(), new PhotosModel());
		setCenter(photosController.getView());
	}

	private void handleActors() {
		setCenter(null);
	}

	private void handleSongs() {
		setCenter(null);
	}

	private void handleExit() {
		System.exit(0);
	}

	private void handleMinimize() {
		Anthologium.stage.setIconified(true);
	}

}