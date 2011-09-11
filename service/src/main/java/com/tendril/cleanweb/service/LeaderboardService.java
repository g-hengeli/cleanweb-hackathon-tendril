package com.tendril.cleanweb.service;

import com.tendril.cleanweb.domain.LeaderboardEntry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LeaderboardService {

	private String driver = "org.apache.derby.jdbc.EmbeddedDriver";

	public LeaderboardService() {
		try {
			Class.forName(driver).newInstance();
			System.out.println("Loaded the appropriate driver");
		} catch (ClassNotFoundException cnfe) {
			System.err.println("\nUnable to load the JDBC driver " + driver);
			System.err.println("Please check your CLASSPATH.");
			cnfe.printStackTrace(System.err);
		} catch (InstantiationException ie) {
			System.err.println("\nUnable to instantiate the JDBC driver "
					+ driver);
			ie.printStackTrace(System.err);
		} catch (IllegalAccessException iae) {
			System.err.println("\nNot allowed to access the JDBC driver "
					+ driver);
			iae.printStackTrace(System.err);
		}

		Connection conn = null;
		Statement s = null;
		try {
			conn = DriverManager
					.getConnection("jdbc:derby:energyFightDB;create=true");
			s = conn.createStatement();

			try {
				s.executeQuery("SELECT COUNT(*) FROM leaderboard");
			} catch (SQLException sqlException) {
				s.execute("CREATE TABLE leaderboard(userId VARCHAR(100), zipCode VARCHAR(10), score INT, tariffName VARCHAR(100))");
			}
		} catch (Exception e) {
		} finally {
			try {
				if (s != null) {
					s.close();
					s = null;
				}
			} catch (SQLException sqle) {
				printSQLException(sqle);
			}

			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException sqle) {
				printSQLException(sqle);
			}
		}
	}

	public void persist(LeaderboardEntry leaderboardEntry) {
		Connection connection = null;
		ArrayList<Statement> statements = new ArrayList<Statement>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DriverManager
					.getConnection("jdbc:derby:energyFightDB;create=true");

			preparedStatement = connection
					.prepareStatement("SELECT * FROM leaderboard WHERE userId = ?");
			statements.add(preparedStatement);

			preparedStatement.setString(1, leaderboardEntry.getUserId());
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				preparedStatement = connection
						.prepareStatement("UPDATE leaderboard SET score = ?, tariffName = ? WHERE userId = ?");
				statements.add(preparedStatement);

				preparedStatement.setInt(1, leaderboardEntry.getScore());
				preparedStatement
						.setString(2, leaderboardEntry.getTariffName());
				preparedStatement.setString(3, leaderboardEntry.getUserId());
			} else {
				preparedStatement = connection
						.prepareStatement("INSERT INTO leaderboard VALUES (?, ?, ?, ?)");
				statements.add(preparedStatement);

				preparedStatement.setString(1, leaderboardEntry.getUserId());
				preparedStatement.setString(2, leaderboardEntry.getZipcode());
				preparedStatement.setInt(3, leaderboardEntry.getScore());
				preparedStatement
						.setString(4, leaderboardEntry.getTariffName());
			}

			preparedStatement.executeUpdate();

		} catch (SQLException sqle) {
			printSQLException(sqle);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
					resultSet = null;
				}
			} catch (SQLException sqle) {
				printSQLException(sqle);
			}

			while (!statements.isEmpty()) {
				Statement st = (Statement) statements.remove(0);
				try {
					if (st != null) {
						st.close();
						st = null;
					}
				} catch (SQLException sqle) {
					printSQLException(sqle);
				}
			}

			try {
				if (connection != null) {
					connection.close();
					connection = null;
				}
			} catch (SQLException sqle) {
				printSQLException(sqle);
			}
		}
	}

	public List<LeaderboardEntry> getLeaderboard() {
		List<LeaderboardEntry> leaderboardEntries = new ArrayList<LeaderboardEntry>();

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DriverManager
					.getConnection("jdbc:derby:energyFightDB;create=true");

			statement = connection.createStatement();
			resultSet = statement
					.executeQuery("SELECT * FROM leaderboard ORDER BY score");
			while (resultSet.next()) {
				leaderboardEntries.add(new LeaderboardEntry(resultSet
						.getString(1), resultSet.getString(2), resultSet
						.getInt(3), resultSet.getString(4)));
			}
		} catch (SQLException sqle) {
			printSQLException(sqle);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
					resultSet = null;
				}
			} catch (SQLException sqle) {
				printSQLException(sqle);
			}

			try {
				if (statement != null) {
					statement.close();
					statement = null;
				}
			} catch (SQLException sqle) {
				printSQLException(sqle);
			}

			try {
				if (connection != null) {
					connection.close();
					connection = null;
				}
			} catch (SQLException sqle) {
				printSQLException(sqle);
			}
		}

		return leaderboardEntries;
	}

	public List<LeaderboardEntry> getNeighbors(LeaderboardEntry leaderboardEntry) {
		List<LeaderboardEntry> leaderboardEntries = new ArrayList<LeaderboardEntry>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DriverManager
					.getConnection("jdbc:derby:energyFightDB;create=true");

			preparedStatement = connection
					.prepareStatement("SELECT * FROM leaderboard "
                            + "WHERE userId != ? AND tariffName != ? "
                            + "ORDER BY ABS(INTEGER(?) - INTEGER(zipCode)), score");

			preparedStatement.setString(1, leaderboardEntry.getUserId());
			preparedStatement.setString(2, leaderboardEntry.getTariffName());
			preparedStatement.setString(3, leaderboardEntry.getZipcode());

			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				LeaderboardEntry leaderboardEntry2 = new LeaderboardEntry(
						resultSet.getString(1), resultSet.getString(2),
						resultSet.getInt(3), resultSet.getString(4));
				leaderboardEntries.add(leaderboardEntry2);
			}
		} catch (SQLException sqle) {
			printSQLException(sqle);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
					resultSet = null;
				}
			} catch (SQLException sqle) {
				printSQLException(sqle);
			}

			try {
				if (preparedStatement != null) {
					preparedStatement.close();
					preparedStatement = null;
				}
			} catch (SQLException sqle) {
				printSQLException(sqle);
			}

			try {
				if (connection != null) {
					connection.close();
					connection = null;
				}
			} catch (SQLException sqle) {
				printSQLException(sqle);
			}
		}

		return leaderboardEntries;
	}

	public static void printSQLException(SQLException e) {
		// Unwraps the entire exception chain to unveil the real cause of the
		// Exception.
		while (e != null) {
			System.err.println("\n----- SQLException -----");
			System.err.println("  SQL State:  " + e.getSQLState());
			System.err.println("  Error Code: " + e.getErrorCode());
			System.err.println("  Message:    " + e.getMessage());
			// for stack traces, refer to derby.log or uncomment this:
			// e.printStackTrace(System.err);
			e = e.getNextException();
		}
	}

	public void reset() {
		Connection connection = null;
		ArrayList<Statement> statements = new ArrayList<Statement>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DriverManager
					.getConnection("jdbc:derby:energyFightDB;create=true");

			preparedStatement = connection
					.prepareStatement("DELETE FROM leaderboard");
			preparedStatement.executeUpdate();

		} catch (SQLException sqle) {
			printSQLException(sqle);
		} finally {
			while (preparedStatement != null) {
				try {
					if (preparedStatement != null) {
						preparedStatement.close();
						preparedStatement = null;
					}
				} catch (SQLException sqle) {
					printSQLException(sqle);
				}
			}

			try {
				if (connection != null) {
					connection.close();
					connection = null;
				}
			} catch (SQLException sqle) {
				printSQLException(sqle);
			}
		}
	}
}
