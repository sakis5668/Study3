package com.sakis.anthologium.main;
	
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Anthologium extends Application {
	
	public static Stage stage;
	
	@Override
	public void start(Stage primaryStage) 
	{
		try {
			AnthologiumController ctrl = new AnthologiumController(new AnthologiumView(), new AnthologiumModel());
			BorderPane root = ctrl.getView();
			Scene scene = new Scene(root,1200,800);
			scene.setFill(null);
			primaryStage.setScene(scene);
			//primaryStage.initStyle(StageStyle.TRANSPARENT);
			Anthologium.stage = primaryStage;
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}
