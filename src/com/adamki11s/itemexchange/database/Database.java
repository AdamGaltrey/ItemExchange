package com.adamki11s.itemexchange.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.adamki11s.itemexchange.Config;
import com.adamki11s.itemexchange.ItemExchange;
import com.adamki11s.itemexchange.exchange.Entry;
import com.adamki11s.itemexchange.exchange.Exchange;
import com.adamki11s.itemexchange.sql.SyncSQL;

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
							+ "(id INT NOT NULL PRIMARY KEY, seller VARCHAR(36), item VARCHAR(60), quantity INTEGER, cpu INTEGER, sold INTEGER, time LONG);";
					sql.standardQuery(create);
					ItemExchange.getLog().info("Item table created.");
				}

				if (!sql.doesTableExist(HISTORY_TABLE)) {
					create = "CREATE TABLE "
							+ HISTORY_TABLE
							+ "(id INT NOT NULL PRIMARY KEY, seller VARCHAR(36), buyer VARCHAR(36), item VARCHAR(60), quantity INTEGER, cpu INTEGER, time LONG);";
					sql.standardQuery(create);
					ItemExchange.getLog().info("History table created.");
				}

				if (!sql.doesTableExist(DATA_TABLE)) {
					/*
					 * used to keep track of successful trades and record the
					 * average price point cumulative is the total cost of all
					 * sold items, and quantity is used to calculate an average
					 * price point
					 */
					create = "CREATE TABLE " + DATA_TABLE
							+ "(id INT NOT NULL PRIMARY KEY, item VARCHAR(60), cumulative LONG, quantity LONG);";
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

	public static List<Entry> loadEntries() {
		ResultSet s;
		List<Entry> entries = new ArrayList<Entry>();
		try {
			s = sql.sqlQuery("SELECT * FROM " + ITEM_TABLE + ";");
			while (s.next()) {
				Entry e = new Entry(s.getString("seller"), Material.valueOf(s.getString("item")), s.getInt("quantity"), s.getInt("cpu"),
						s.getInt("sold"), s.getLong("time"));
				entries.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entries;
	}

	public static void addEntryAsync(final Entry e, final Player callback) {
		Bukkit.getScheduler().runTaskAsynchronously(ItemExchange.getPlugin(), new Runnable() {
			
			@Override
			public void run() {
				try {
					sql.standardQuery("INSERT INTO "
							+ ITEM_TABLE
							+ "(seller, item, quantity, cpu, sold, time) VALUES ("
							+ String.format("%s, %s, %d, %d, %d, %d", e.getSellerUUID(), e.getItem().toString(), e.getListedQuantity(),
									e.getCostPerUnit(), e.getQuantitySold(), e.getTimeListed()) + ");");
					Exchange.addEntryAsync(e);
					callback.sendMessage(ChatColor.GREEN + "Your item was successfully listed on the exchange.");
				} catch (SQLException e1) {
					e1.printStackTrace();
					callback.sendMessage(ChatColor.RED + "There was an error syncing your item with the database.");
				}
			}
		});
	}

}
