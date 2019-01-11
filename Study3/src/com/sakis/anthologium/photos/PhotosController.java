package com.sakis.anthologium.photos;

public class PhotosController {



	private PhotosView view;
	private PhotosModel model;

	public PhotosController(PhotosView view, PhotosModel model) {
		this.view = view;
		this.model = model;
	}
	
	public PhotosView getView() {
		return view;
	}

}
