package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.Cliente;
import modelo.GestorBBDD;

public class UsandoPestañas extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsandoPestañas frame = new UsandoPestañas();
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
	public UsandoPestañas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 434, 261);
		contentPane.add(tabbedPane);
		
		JPanel instCliente = new JPanel();
		tabbedPane.addTab("Insertar Cliente", null, instCliente, null);
		instCliente.setLayout(null);
		
		JLabel lblDNI = new JLabel("DNI");
		lblDNI.setBounds(10, 15, 46, 14);
		instCliente.add(lblDNI);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 45, 46, 14);
		instCliente.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(10, 75, 46, 14);
		instCliente.add(lblApellido);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(10, 105, 59, 14);
		instCliente.add(lblDireccion);
		
		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(10, 135, 59, 14);
		instCliente.add(lblLocalidad);
		
		textField = new JTextField();
		textField.setToolTipText("");
		textField.setColumns(10);
		textField.setBounds(79, 11, 86, 20);
		instCliente.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(79, 42, 86, 20);
		instCliente.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(79, 72, 86, 20);
		instCliente.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(79, 102, 86, 20);
		instCliente.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(79, 132, 86, 20);
		instCliente.add(textField_4);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(79, 176, 89, 23);
		instCliente.add(btnGuardar);
		
		JButton btnModificar = new JButton("Modificar Cliente");
		btnModificar.setBounds(46, 210, 155, 23);
		instCliente.add(btnModificar);
		
		JPanel verClientes = new JPanel();
		tabbedPane.addTab("Ver Clientes", null, verClientes, null);
		GestorBBDD gestorbbdd = new GestorBBDD();
		ArrayList<Cliente> clientes = new ArrayList<>();
		try {
			 clientes = gestorbbdd.getClientes();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		table = new JTable();
		DefaultTableModel model = new DefaultTableModel(new Object[][] {}, new String[] {"DNI", "Nombre", "Apellido", "Dirección", "Localidad"});
		Iterator<Cliente> it = clientes.iterator();
		while (it.hasNext()) {
		  Cliente cliente = it.next();
		  model.addRow(new Object[]{cliente.getDni(), cliente.getNombre(), cliente.getApellidos(), cliente.getDireccion(), cliente.getLocalidad()});
		}
		table.setModel(model);

		
		verClientes.add(table);
		
	}
}
