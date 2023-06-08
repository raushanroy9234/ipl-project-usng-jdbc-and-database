package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        try {
            // Step 1: Register the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish the connection
            String url = "jdbc:mysql://localhost:3306/project";
            String username = "root";
            String password = "1Admin@#";
            Connection connection = null;
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root" ,"1Admin@#") ;

            String firstStatement = "select season,count(season) as total from matches group by season";
            PreparedStatement firstQuestion = connection.prepareStatement(firstStatement);
            ResultSet fs = firstQuestion.executeQuery();
            System.out.println("Season    "+"total");
            while (fs.next()) {
                int season = fs.getInt("season");
                int total = fs.getInt("total");
                System.out.println(season + "   " + total);
            }


            String secondStatement = "select winner,count(winner) as noOfWin from Matches group by winner order by count(winner)";
            PreparedStatement secondQuestion = connection.prepareStatement(secondStatement);
            ResultSet ss = secondQuestion.executeQuery();
            while(ss.next()){
                String winner = ss.getString("winner");
                int total = ss.getInt("noOfWin");
                System.out.println(winner+"   "+total);
            }

            String thirdStatement = "select season,bowling_team,sum(extra_runs) as xtraRuns from Deliveries join Matches on Matches.id = Deliveries.match_id where season=2016 group by bowling_team";
            PreparedStatement thirdQuestion = connection.prepareStatement(thirdStatement);
            ResultSet ts = thirdQuestion.executeQuery();
            while(ts.next())
            {
                int season = ts.getInt("season");
                String bowlingTeam = ts.getString("bowling_team");
                int extraRuns = ts.getInt("xtraRuns");
                System.out.println(season+"   "+bowlingTeam+"         "+extraRuns);
            }

            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}