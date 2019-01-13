package com.sakis.anthologium.photos;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PhotoActorData {
	
	private IntegerProperty actorPhotoId;
	private IntegerProperty actorId;
	private IntegerProperty photoId;
	private StringProperty lastName;
	private StringProperty firstName;
	
	
	/**
	 * Constructor
	 */
	public PhotoActorData (int actorPhotoId, int photoId, int actorId, String lastName, String firstName)
	{
		this.actorPhotoId = new SimpleIntegerProperty(actorPhotoId);
		this.photoId = new SimpleIntegerProperty(photoId);
		this.actorId = new SimpleIntegerProperty(actorId);
		this.lastName = new SimpleStringProperty(lastName);
		this.firstName = new SimpleStringProperty(firstName);
	}

	
	/**
	 * Getters and Setters for ActorPhotoID
	 * @return
	 */
	public int getActorPhotoId() 
	{
		return this.actorPhotoId.get();
	}
	
	public IntegerProperty getActorPhotoIdProperty() 
	{
		return this.actorPhotoId;
	}
	
	public void setActorPhotoId(int actorPhotoId) 
	{
		this.actorPhotoId.set(actorPhotoId);
	}
	
	
	/**
	 * Getters and Setters for PhotoID
	 * @return
	 */
	public int getPhotoId() 
	{
		return this.photoId.get();
	}
	
	public IntegerProperty getPhotoIdProperty() 
	{
		return this.photoId;
	}
	
	public void setPhotoId (int photoId) 
	{
		this.photoId.set(photoId);
	}
	
	
	/**
	 * Getters and Setters for ActorID
	 * @return
	 */
	public int getActorId()
	{
		return this.actorId.get();
	}
	
	public IntegerProperty getActorIdProperty() 
	{
		return this.actorId;
	}
	
	public void setActorId (int actorId) 
	{
		this.actorId.set(actorId);
	}
	
	
	/**
	 * Getters and Setters for LastName
	 * @return
	 */
	public String getLastname()
	{
		return this.lastName.get();
	}
	
	public StringProperty getLastNameProperty()
	{
		return this.lastName;
	}
	
	public void setLastName (String lastName) 
	{
		this.lastName.set(lastName);
	}
	
	
	/**
	 * Getters and Setters for FirstName
	 * @return
	 */
	public String getFirstName () 
	{
		return this.firstName.get();
	}
	
	public StringProperty getFirstNameProperty()
	{
		return this.firstName;
	}
	
	public void setFirstName (String firstName) 
	{
		this.firstName.set(firstName);
	}
}
