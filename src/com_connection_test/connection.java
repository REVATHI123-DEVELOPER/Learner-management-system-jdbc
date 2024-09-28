package com_connection_test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;



public class connection {

	public static void main(String[] args) throws Exception{
		System.out.println("revathi");
//		step1 :  load the driver class
		Class.forName("com.mysql.cj.jdbc.Driver");
//add th default url of the mysql along with the local host we need to add the database name also
		String url = "jdbc:mysql://localhost:3306/learner_management_system";
//give the username always the root name will be root
		String uname = "root";
//give the password also for the mysql
		String pass = "Revathi006@";
	
		
		
//  step 2: create an connection using the driver manager class
		Connection obj = DriverManager.getConnection(url,uname,pass);
		
		
//step 3 :  using the connection we need to fire the queries by using the static queries or prepared statement dynamic queries
		Statement statement = obj.createStatement();
		
//step 4: process the result
		
		ResultSet resultset = null;
		
		
		
//	asking the values from the suer
		Scanner sc = new Scanner(System.in);
		int option;
		
		do
		{
			System.out.println("------------------------------------------------");
			System.out.println("1.insert");
			System.out.println("2.display");
			System.out.println("3.update");
			System.out.println("4.delete");
			System.out.println("5.search");
			System.out.println("6.count records");
			System.out.println("7.Order by descending order");
			System.out.println("8.display data starts from a(like)");
			System.out.println("9.exit");
			System.out.println("------------------------------------------------");
			
			option = sc.nextInt();
			int id;
			String sql;
			switch(option)
			{
			case 1:
//				insert case
				System.out.println("enter the learner id");
				int lid = sc.nextInt();
				
				System.out.println("ente the learner name");
				String lname = sc.next();
				
				
				System.out.println("enter the degree");
				String degree = sc.next();
				
				sql  = "insert into learner(lid,lname,degree) values("+lid+",'"+lname+"','"+degree+"')";
				statement.executeUpdate(sql);
				System.out.println("record inserted successfully");
			
				

				break;
			case 2:
				sql = "select * from learner";
				resultset  =statement.executeQuery(sql);
				while(resultset.next())
				{
					System.out.println("learner id: "+ resultset.getInt(1));
					System.out.println("learner name: "+ resultset.getString(2));
					System.out.println("learner degree: "+ resultset.getString(3));
					System.out.println("--------------------------------------------------------------------");



					
					
				}
					
				
				break;
			case 3:
				System.out.println("enter the id whose data you want to update");
				lid = sc.nextInt();
				
				
				System.out.println("enter the new name");
				lname = sc.next();
				
				System.out.println("enter the new degree");
				degree = sc.next();
				
				
				sql = "update learner set lname = '"+lname+"',degree='"+degree+"' where lid='"+lid+"'" ;
				
				statement.executeLargeUpdate(sql);
				System.out.println("data is updated successfully");
				
				break;
			case 4:
				System.out.println("enter the id whose record you want to delete");
				id = sc.nextInt();
				
				
				sql = "delete from learner where lid = '"+id+"'";
				statement.executeUpdate(sql);
				
				System.out.println("data deleted successfully");
				break;
			case 5:
				System.out.println("enter the name or id which you want to search");
				id = sc.nextInt();
				String name = sc.next();
				
				
				sql = "select * from learner where lid = '"+id+"' or lname ='"+name+"'";
//				storing the execution gof sql query in resultset
				resultset = statement.executeQuery(sql);
				if(resultset.next())
				{
				
				System.out.println("data is present in the database");
				System.out.println("learner id:"+resultset.getInt(1));
				System.out.println("learner name: "+resultset.getString(2));
				System.out.println("learner degree: "+resultset.getString(3));
				}
				else
				{
					System.out.println("data is not present in the database");
				}

				
				
				
				
				break;
			case 6:
				sql = "select count(*) from learner";
				resultset = statement.executeQuery(sql);
				resultset.next();
				System.out.println("the total number of records are:"+resultset.getInt(1));
				
				break;
			case 7:
				sql = "select * from learner order by lid desc";
				resultset  =statement.executeQuery(sql);
				while(resultset.next())
				{
					System.out.println("learner id: "+resultset.getInt(1));
					System.out.println("learner name: "+resultset.getString(2));
					System.out.println("learner degree: "+resultset.getString(3));
					System.out.println("------------------------------------------------------");


				}
				
				
				break;
			case 8:
				sql = "select * from learner where lname like 's%'";
				resultset  = statement.executeQuery(sql);
				while(resultset.next())
				{
					System.out.println("learner id: "+resultset.getInt(1));
					System.out.println("learner name: "+resultset.getString(2));
					System.out.println("learner degree: "+resultset.getString(3));
					System.out.println("--------------------------------------------------");
					
				}
				break;
			case 9:
				System.exit(0);
				break;
			
		
			
			}
			
		}
		while(option!=9);
		
//need to close the statement		
		statement.close();
//close the connection also..
		obj.close();
		
		
		
		
		
		
	}

}
