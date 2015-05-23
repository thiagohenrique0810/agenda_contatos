package Ygor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Agendadecontatos.Contato;
import Ygor.bancodedados.CriaConexao;

public class ContatoDao {
	
	private Connection conexao ;
	
	public ContatoDao() throws SQLException{
		this.conexao = new CriaConexao().getConexao();
	}
	
	public void adiciona(Contato c1) throws SQLException{
	
		//prepara a conexao
		String sql="insert into contato (nome,endereco,email,sexo)values( ?,?,?,? )";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, c1.getNome());
		stmt.setString(2,c1.getEndereco());
		stmt.setString(3,c1.getEmail());
		stmt.setString(4,c1.getSexo());
		
		//executa o codigo sql
		
		stmt.execute();
		stmt.close();
	}
	
	public List<Contato>getLista(String nome) throws SQLException{
		//utilizado p pesquisar todos os contatos
		//String sql = "select * from contato";
		
		//usado p pesquisar dados especificos
		String sql = "select * from contato where nome like ?";
		PreparedStatement stmt = this.conexao.prepareStatement(sql);
		stmt.setString(1, nome);
		ResultSet rs = stmt.executeQuery();
		List<Contato>minhaLista = new ArrayList<Contato>();
		while(rs.next()){
			Contato c1 = new Contato();
			c1.setId(Long.valueOf(rs.getString("id")));
			c1.setNome(rs.getString("nome"));
			c1.setEndereco(rs.getString("endereco"));
			c1.setEmail(rs.getString("email"));
			c1.setSexo(rs.getString("sexo"));
			minhaLista.add(c1);
		}
		
		rs.close();
		stmt.close();
		return minhaLista;
	}
	
	

	
	
	public void altera(Contato c1) throws SQLException{
		
		String sql = "update contato set nome=?, endereco = ? , email=?, sexo=?"
				+ "where id =?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		
		stmt.setString(1,c1.getNome());
		stmt.setString(2,c1.getEndereco());
		stmt.setString(3,c1.getEmail());
		stmt.setString(4,c1.getSexo());
		stmt.setLong(5,c1.getId());
		
		//executa o codigo sql
		stmt.execute();
		stmt.close();
		
	}
	
	
	public void remove(Contato c1) throws SQLException{
		
		String sql = "delete from contato where id=?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		
	 
		stmt.setLong(1,c1.getId());
		stmt.execute();
		stmt.close();
		
		
	}
	
}
