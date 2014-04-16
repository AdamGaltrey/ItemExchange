package com.adamki11s.itemexchange.sql;

import java.sql.SQLException;

import com.adamki11s.itemexchange.Config;
import com.adamki11s.itemexchange.ItemExchange;

public class Database {

	public static SyncSQL sql;

	public static String ITEM_TABLE = "iex_items", HISTORY_TABLE = "iex_history", DATA_TABLE = "iex_data";

	public static void initDatabase(boolean useSQL, String... data) {
		// [host, database, username, password]
		sql = useSQL ? new SyncSQL(data[0], data[1], data[2], data[3]) : new SyncSQL(Config.SQLITE);

		ItemExchange.getLog().info(useSQL ? "Using MySQL database storage." : "Using SQLite database storage.");

		if (sql.initialise()) {
			ItemExchange.getLog().info("SQL connection successful.");

			try {
				String create;
				if (!sql.doesTableExist(ITEM_TABLE)) {
					/*
					 * VARCHAR(36) for storing UUID cpu = cost per unit sold
					 * integer represents how many of the item have been sold
					 * time is the unix timestamp of when the item was listed
					 */
					create = "CREATE TABLE "
							+ ITEM_TABLE
							+ "(id INT NOT NULL PRIMARY KEY, seller VARCHAR(36), itemid INTEGER, quantity INTEGER, cpu INTEGER, sold INTEGER, time LONG);";
					sql.standardQuery(create);
					ItemExchange.getLog().info("Item table created.");
				}

				if (!sql.doesTableExist(HISTORY_TABLE)) {
					create = "CREATE TABLE "
							+ HISTORY_TABLE
							+ "(id INT NOT NULL PRIMARY KEY, seller VARCHAR(36), buyer VARCHAR(36), itemid INTEGER, quantity INTEGER, cpu INTEGER, time LONG);";
					sql.standardQuery(create);
					ItemExchange.getLog().info("History table created.");
				}
				
				if (!sql.doesTableExist(DATA_TABLE)) {
					/*used to keep track of successful trades and record the average price point
					 * cumulative is the total cost of all sold items, and quantity is used to calculate an average price point
					 */
					create = "CREATE TABLE "
							+ DATA_TABLE
							+ "(id INT NOT NULL PRIMARY KEY, itemid INTEGER, cumulative LONG, quantity LONG);";
					sql.standardQuery(create);
					ItemExchange.getLog().info("Data table created.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {
			ItemExchange.getLog().severe("SQL connection failed!");
		}
	}

}
