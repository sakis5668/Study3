package com.sakis.anthologium.main;

import java.util.Optional;

import com.sakis.anthologium.photos.PhotosController;
import com.sakis.anthologium.photos.PhotosModel;
import com.sakis.anthologium.photos.PhotosView;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

@SuppressWarnings("unused")
public class AnthologiumController 
{
	
	private AnthologiumView view;
	private AnthologiumModel model;
	
	/**
	 * Constructor
	 * @param view and model
	 */
	
	public AnthologiumController(AnthologiumView view, AnthologiumModel model) 
	{
		this.view = view;
		this.model = model;
		this.view.getPhotoOverViewButton().setOnAction(e->handlePhotoOverview());
		this.view.getActorsButton().setOnAction(e->handleActors());
		this.view.getSongsButton().setOnAction(e->handleSongs());
		this.view.getExitButton().setOnAction(e->handleExit());
		this.view.getMinimizeButton().setOnAction(e->handleMinimize());
	}

	
	/**
	 * Handle the Photo overview button
	 */
	private void handlePhotoOverview() 
	{
		view.setCenter(null);
		PhotosController photosController = new PhotosController(new PhotosView(), new PhotosModel());
		view.setCenter(photosController.getView());
	}

	
	/**
	 * Handle the actors button
	 */
	private void handleActors() 
	{
		view.setCenter(null);
	}

	
	/**
	 * Handle the songs overview button
	 */
	private void handleSongs() 
	{
		view.setCenter(null);
	}

	
	/**
	 *  Handle the exit button 
	 */
	private void handleExit() 
	{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Confirm");
        alert.setHeaderText("Are you sure you want to exit ?");
        alert.setContentText("Press OK to really exit ...");
        Optional<javafx.scene.control.ButtonType> result = alert.showAndWait();
        if (result.get()==javafx.scene.control.ButtonType.OK) {
        	System.exit(0);
        }
	}

	
	/**
	 * Handle the minimize button
	 */
	private void handleMinimize() 
	{
		Anthologium.stage.setIconified(true);
	}


	/**
	 * Returns this controllers view object
	 * @return view
	 */
	public AnthologiumView getView() 
	{
		return view;
	}

}
