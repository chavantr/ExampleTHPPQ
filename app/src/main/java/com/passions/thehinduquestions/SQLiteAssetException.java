
package com.passions.thehinduquestions;

import android.database.sqlite.SQLiteException;


public class SQLiteAssetException extends SQLiteException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SQLiteAssetException() {
	}

	public SQLiteAssetException(String message) {
		super(message);
	}

}
