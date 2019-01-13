package com.sakis.anthologium.photos;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class PhotosController {

	private PhotosView view;
	private PhotosModel model;

	
	public PhotosController(PhotosView view, PhotosModel model) {
		this.view = view;
		this.model = model;
		
		addHandlers();
	}



	private void addHandlers() {
		this.view.addKeyEventHandler(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					model.setSearchStringProperty(view.getSearchTextField().getText());
				}
			}
		});
	}

	
	public PhotosView getView() {
		return view;
	}

}
