package com.wt.xwttravel.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	private static final String DB_NAME="messages.db";

	private static final int DB_VERSION=1;
	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}
	public DBHelper(Context context) {
		this(context,DB_NAME,null,DB_VERSION);
	}

	public void onCreate(SQLiteDatabase db) {
		String sql="CREATE TABLE qt_im" +
				"(" +
				"    id BIGINT(20) PRIMARY KEY NOT NULL," +
				"    user_id INTEGER(20) NOT NULL," +
				"    target_user_id INTEGER(20) NOT NULL," +
				"    send_time BIGINT(20) NOT NULL," +
				"    type INT(11) NOT NULL," +
				"    content TEXT NOT NULL," +
				"    sent INT(11) DEFAULT '0' NOT NULL," +
				"    baidupush_request_id INTEGER(20) DEFAULT '0' NOT NULL" +
				")" ;
		db.execSQL(sql);
	}


	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		
	}


}
