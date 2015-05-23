package Ygor.forms;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Agendadecontatos.Contato;
import Ygor.dao.ContatoDao;

public class JTAgendaContatos extends JFrame {

	private JPanel contentPane;
	private JTextField jTId;
	private JTextField jTNome;
	private JTextField jTEmail;
	private JTextField jtEndereco;
	private JTextField jTSexo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JTAgendaContatos frame = new JTAgendaContatos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JTAgendaContatos() {
		
		initComponentes();
		desabilitaDados();
	}
	
	public void initComponentes(){
		
		
		setTitle("Agenda");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 525, 467);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Dados do Contato", TitledBorder.LEADING, TitledBorder.TOP, null, Color.GRAY));
		panel.setBounds(10, 95, 317, 213);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel jLId = new JLabel("ID :");
		jLId.setBounds(25, 34, 46, 14);
		panel.add(jLId);
		
		JLabel jLNome = new JLabel("Nome :");
		jLNome.setBounds(25, 72, 46, 14);
		panel.add(jLNome);
		
		JLabel jLEndereco = new JLabel("Endere\u00E7o :");
		jLEndereco.setBounds(25, 113, 60, 14);
		panel.add(jLEndereco);
		
		JLabel jLEmail = new JLabel("Email :");
		jLEmail.setBounds(25, 150, 46, 14);
		panel.add(jLEmail);
		
		JLabel jLSexo = new JLabel("Sexo :");
		jLSexo.setBounds(25, 181, 46, 14);
		panel.add(jLSexo);
		
		jTId = new JTextField();
		jTId.setBounds(117, 31, 158, 20);
		panel.add(jTId);
		jTId.setColumns(10);
		
		jTNome = new JTextField();
		jTNome.setBounds(117, 69, 158, 20);
		panel.add(jTNome);
		jTNome.setColumns(10);
		
		jTEmail = new JTextField();
		jTEmail.setBounds(117, 147, 158, 20);
		panel.add(jTEmail);
		jTEmail.setColumns(10);
		
		jtEndereco = new JTextField();
		jtEndereco.setBounds(117, 110, 158, 20);
		panel.add(jtEndereco);
		jtEndereco.setColumns(10);
		
		jTSexo = new JTextField();
		jTSexo.setBounds(117, 178, 158, 20);
		panel.add(jTSexo);
		jTSexo.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.windowBorder);
		panel_1.setForeground(SystemColor.inactiveCaption);
		panel_1.setBorder(new LineBorder(Color.GRAY, 2));
		panel_1.setBounds(10, 11, 489, 55);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnPesquisa = new JButton("Pesquisa");
		btnPesquisa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					listarContatos();
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnPesquisa.setBounds(378, 11, 89, 23);
		panel_1.add(btnPesquisa);
		
		tFPesquisa = new JTextField();
		tFPesquisa.setBounds(10, 12, 340, 20);
		panel_1.add(tFPesquisa);
		tFPesquisa.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.windowBorder);
		panel_2.setBorder(new LineBorder(Color.GRAY, 2));
		panel_2.setBounds(10, 319, 489, 110);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 469, 88);
		panel_2.add(scrollPane);
		
		jTTabela = new JTable();
		jTTabela.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"ID", "NOME", "ENDERE\u00C7O", "EMAIL", "SEXO"
			}
		));
		scrollPane.setViewportView(jTTabela);
		
		//metodo adicional para a tabela
		
		jTTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lsmContato = jTTabela.getSelectionModel();
		lsmContato.addListSelectionListener(new ListSelectionListener() {
			
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(e.getValueIsAdjusting()){
					jTTabelaLinhaSelecionada(jTTabela);
				}
				
			}
		});
		
		
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.windowBorder);
		panel_3.setForeground(SystemColor.textHighlight);
		panel_3.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 2));
		panel_3.setBounds(356, 95, 143, 213);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				habilitaDados();
				jTNome.setText("");
				jtEndereco.setText("");
				jTEmail.setText("");
				jTSexo.setText("");
				jTId.setText("");
		

			}
		});
		btnNovo.setBounds(24, 11, 89, 23);
		panel_3.add(btnNovo);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					alteraContato();
                    listarContatos();
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Problema no jbalterar !!");
					e1.printStackTrace();
				}
				
				
			}
		});
		btnAlterar.setBounds(24, 57, 89, 23);
		panel_3.add(btnAlterar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					excluirContato();
					//metodo utilizado p mostrar contatos renovando a tabela
					listarContatos();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Erro no botao excluir "+e1.getMessage());
					e1.printStackTrace();
				}
				
			}
		});
		btnExcluir.setBounds(24, 98, 89, 23);
		panel_3.add(btnExcluir);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(verificaDados()==true){
					cadastro();
					desabilitaDados();
				}
			}
		});
		btnSalvar.setBounds(24, 137, 89, 23);
		panel_3.add(btnSalvar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
				
			}
		});
		btnSair.setBounds(24, 171, 89, 23);
		panel_3.add(btnSair);
		
		
		
	}
	//metodo para verificar o preenchimento dos dados
	public boolean verificaDados(){
		
		if(jTNome.getText().equals("")||(jtEndereco.getText().equals(""))){
		JOptionPane.showMessageDialog(null, "Nome e endereço nao preenchidos !!");
		return false;
	}else{
		return true;
	}
}
	//metodo para retornar os valores do banco para a tabela
	private void jTTabelaLinhaSelecionada(JTable tabela){
		
		if(jTTabela.getSelectedRow()!=-1){
			
			habilitaDados();
			
		jTId.setText(String.valueOf(contatos.get(tabela.getSelectedRow()).getId()));
		jTNome.setText(contatos.get(tabela.getSelectedRow()).getNome());
		jtEndereco.setText(contatos.get(tabela.getSelectedRow()).getEndereco());
		jTEmail.setText(contatos.get(tabela.getSelectedRow()).getEmail());
		jTSexo.setText(contatos.get(tabela.getSelectedRow()).getSexo());
		
		}else{
			
			jTId.setText("");
			jTNome.setText("");
			jtEndereco.setText("");
			jTEmail.setText("");
			jTSexo.setText("");
			
		}
		
	}
	
	
	
	
	
	
	//metodo para cadastrar(salvar contatos)
	public  void cadastro(){
		
		
		try{
			Contato c1 = new Contato();
			c1.setNome(jTNome.getText());
			c1.setEndereco(jtEndereco.getText());
			c1.setEmail(jTEmail.getText());
			c1.setSexo(jTSexo.getText());
			
			ContatoDao dao = new ContatoDao();
			dao.adiciona(c1);
			}catch(SQLException ex){
				Logger.getLogger(JTAgendaContatos.class.getName()).log(Level.SEVERE,null,ex);
				System.out.println("");
			}
		
	}
	
	//metodo usado para excluir contato
	public void excluirContato() throws SQLException{
		
		int resp = Integer.parseInt(JOptionPane.showInputDialog("Você deseja excluir esse contato ? \n 1.sim \n 2.não \n"));
		
		if(resp == 1){
			
			ContatoDao dao = new ContatoDao();
			dao.remove(contatos.get(jTTabela.getSelectedRow()));
			
			JOptionPane.showMessageDialog(null,"Cadastro excluido ");
			
		}else if(resp==2){
			JOptionPane.showMessageDialog(null,"cadastro não foi excluido !");
		}else {
			JOptionPane.showMessageDialog(null, "Opção invalida !!!!");
		}
		
		
		
	}
	
	
	//metodo utilizado para alterar contato
	
	public void alteraContato() throws SQLException{
		
		if(jTTabela.getSelectedRow() !=-1){
			
			if(verificaDados()){
				
				Contato c1 = new Contato();
				ContatoDao dao = new ContatoDao();
				
				c1.setId(Long.valueOf(jTId.getText()));
				c1.setNome(jTNome.getText());
				c1.setEndereco(jtEndereco.getText());
				c1.setEmail(jTEmail.getText());
				c1.setSexo(jTSexo.getText());
				
				dao.altera(c1);
				
				JOptionPane.showMessageDialog(null, "Alterado com sucesso !!");
			}
			
			
		}
		
	}
	//metodo usado para deixar os campos sem acesso na tela principal
	public void desabilitaDados(){
		
		jTId.setEditable(false);
		jTNome.setEditable(false);
		jtEndereco.setEditable(false);
		jTEmail.setEditable(false);
		jTSexo.setEditable(false);
		
	}
	//metodo usado para deixar os campos abertos para acesso na tela principal
	public void habilitaDados(){
		
		jTNome.setEditable(true);
		jtEndereco.setEditable(true);
		jTEmail.setEditable(true);
		jTSexo.setEditable(true);
		
		
	}
	
	//criar as colunas da tabela
	
	DefaultTableModel tmContato = new DefaultTableModel(null,new String[]{"ID","NOME","ENDEREÇO","EMAIL","SEXO"});
	List<Contato>contatos;
	ListSelectionModel lsmContato;
	private JTextField tFPesquisa;
	private JTable jTTabela;
	// o metodo acima serve para configurar a tabela
	
	
	//serve para mostrar atraves da tabela os dados armazenados do banco
	protected void listarContatos() throws SQLException{
		
		ContatoDao dao = new ContatoDao();
		contatos = dao.getLista("%"+tFPesquisa.getText()+"%");
		mostraPesquisa(contatos);
		
	}
	
	//serve para atribuir a tabela os dados do banco
	private void mostraPesquisa(List<Contato>contatos){
		
		//metodo utilizado p zerar a consulta ao iniciar outra
		while(tmContato.getRowCount()>0){
			tmContato.removeRow(0);
		}
		
		if(contatos.size()==0){
			JOptionPane.showMessageDialog(null,"nenhum contato cadastrado !");
		}else{
			String [] linha =new String[]{null,null,null,null,null};
			
			for(int i=0;i<contatos.size();i++){

			tmContato.addRow(linha);
			tmContato.setValueAt(contatos.get(i).getId(), i, 0);
			tmContato.setValueAt(contatos.get(i).getNome(), i,1);
			tmContato.setValueAt(contatos.get(i).getEndereco(), i,2);
			tmContato.setValueAt(contatos.get(i).getEmail(), i, 3);
			tmContato.setValueAt(contatos.get(i).getSexo(), i, 4);
			
			jTTabela.setModel(tmContato);
			
			}
		}
		
	}
}
