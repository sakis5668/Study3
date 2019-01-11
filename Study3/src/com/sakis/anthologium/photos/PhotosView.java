package com.sakis.anthologium.photos;

import javafx.scene.layout.Pane;

public class PhotosView extends Pane {

	public PhotosView() {
		// first get the stylesheet
		final String cssString = getClass().getResource("/com/sakis/anthologium/css/StyleSheet.css").toExternalForm();
		getStylesheets().add(cssString);

		// set the style class for MainBorderPane
		getStyleClass().add("photooverview-pane");
	}

}
