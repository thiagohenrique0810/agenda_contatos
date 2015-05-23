package Ygor.bancodedados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class CriaConexao {
	

	public  Connection getConexao() throws SQLException{
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("conectando ao banco");
			return DriverManager.getConnection("jdbc:mysql://localhost/agendabd","root","#$su2015");
			
		}catch(ClassNotFoundException e){
			throw new SQLException(e.getMessage());
			
		}
	}
	
	
}


