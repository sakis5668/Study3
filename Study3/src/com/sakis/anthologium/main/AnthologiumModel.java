package com.sakis.anthologium.main;

import java.sql.Connection;
import java.sql.SQLException;

import com.sakis.anthologium.util.Database;

public class AnthologiumModel 
{
	Connection connection;
	
	/**
	 * Constructor
	 * First establish a connection to the database
	 * then look for the tables, and create them if they do not exist
	 */
	public AnthologiumModel() 
	{	
		// set connection to Database.getConnection. Establishes the connection.
		try {
			this.connection = Database.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// If no connection, exit the programm
		if (this.connection==null) System.exit(1);
		
		// now look in the database if the tables exist, and if not, create them 
		if (!Database.tableExists("actor"))
			Database.createTableActor();
		if (!Database.tableExists("photo"))
			Database.createTablePhoto();
		if (!Database.tableExists("actor_photo"))
			Database.createTableActorPhoto();
		if (!Database.tableExists("songs"))
			Database.createTableSongs();
		if (!Database.tableExists("song_url"))
			Database.createTableSongURLs();
	}
}
