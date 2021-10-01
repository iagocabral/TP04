package sistemapastel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import controle.*;


public class CRUDVenda implements ActionListener, ListSelectionListener {
    
    private JFrame janela;
    private JLabel titulo;
    private JButton cadastroVenda;
    private JButton refreshVenda;
    private static ControleDados dados;
    private JList<String> listaVendasCadastrados;
    private String[] listaNomes = new String[50];
    
    
    public void mostrarDados(ControleDados d, int op){
        dados = d;
        
        switch (op) {
        case 1:
            listaNomes = new ControleVenda(dados).getNomeVenda(); 
        	
            listaVendasCadastrados = new JList<String>(listaNomes);
            janela = new JFrame("Vendas");
            titulo = new JLabel("Vendas Cadastrados");
            cadastroVenda = new JButton("Cadastrar");
            refreshVenda = new JButton("Refresh");

            titulo.setFont(new Font("Arial", Font.BOLD, 20));
            titulo.setBounds(90, 10, 250, 30);
            listaVendasCadastrados.setBounds(20, 50, 350, 120);
            listaVendasCadastrados.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            listaVendasCadastrados.setVisibleRowCount(10);

            cadastroVenda.setBounds(70, 177, 100, 30);
            refreshVenda.setBounds(200, 177, 100, 30);

            janela.setLayout(null);

            janela.add(titulo);
            janela.add(listaVendasCadastrados);
            janela.add(cadastroVenda);
            janela.add(refreshVenda);

            janela.setSize(400, 250);
            janela.setVisible(true);

            cadastroVenda.addActionListener(this);
            refreshVenda.addActionListener(this);
            listaVendasCadastrados.addListSelectionListener(this);
            break; 

        default:
            JOptionPane.showMessageDialog(null,"Op��o n�o encontrada!", null, 
                    JOptionPane.ERROR_MESSAGE);
        }

    }
    
    //Captura eventos relacionados aos bot�es da interface
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        
        //Cadastro de novo pastel
        if(src == cadastroVenda)
            new TelaDetalheVenda().inserirEditar(1, dados, this, 0);



        // Atualiza a lista de pasteis mostrada no JList
        if(src == refreshVenda) {
            listaVendasCadastrados.setListData(new ControleVenda(dados).getNomeVenda());            
            listaVendasCadastrados.updateUI();
        }
    }
    public void valueChanged(ListSelectionEvent e) {
        Object src = e.getSource();

        if(e.getValueIsAdjusting() && src == listaVendasCadastrados) {
            new TelaDetalheVenda().inserirEditar(3, dados, this, 
                   listaVendasCadastrados.getSelectedIndex());
        
    }
    }   
    
}