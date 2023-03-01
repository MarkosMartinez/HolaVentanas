package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Cliente;
import modelo.GestorBBDD;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;

public class Principal extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textDireccion;
	private JTextField textLocalidad;
	private JTextField textDNI;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		setTitle("Alta de Usuario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDNI = new JLabel("DNI");
		lblDNI.setBounds(49, 31, 46, 14);
		contentPane.add(lblDNI);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(49, 61, 46, 14);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(49, 91, 46, 14);
		contentPane.add(lblApellido);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(49, 121, 59, 14);
		contentPane.add(lblDireccion);
		
		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(49, 151, 59, 14);
		contentPane.add(lblLocalidad);
		
		textDNI = new JTextField();
		textDNI.setToolTipText("");
		textDNI.setBounds(118, 27, 86, 20);
		contentPane.add(textDNI);
		textDNI.setColumns(10);
		
		textNombre = new JTextField();
		textNombre.setBounds(118, 58, 86, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		textApellido = new JTextField();
		textApellido.setBounds(118, 88, 86, 20);
		contentPane.add(textApellido);
		textApellido.setColumns(10);
		
		textDireccion = new JTextField();
		textDireccion.setBounds(118, 118, 86, 20);
		contentPane.add(textDireccion);
		textDireccion.setColumns(10);
		
		textLocalidad = new JTextField();
		textLocalidad.setBounds(118, 148, 86, 20);
		contentPane.add(textLocalidad);
		textLocalidad.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dni = textDNI.getText();
				String nombre = textNombre.getText();
				String apellido = textApellido.getText();
				String direccion = textDireccion.getText();
				String localidad = textLocalidad.getText();
				
				Cliente cliente = new Cliente();
				GestorBBDD gestorbbdd = new GestorBBDD();
				cliente.setDni(dni);
				cliente.setNombre(nombre);
				cliente.setApellidos(apellido);
				cliente.setDireccion(direccion);
				cliente.setLocalidad(localidad);
				
				try {
					if (textDNI.getText().length() == 0) {
						JOptionPane.showMessageDialog(null, "Error datos no validos!");
					}else {
					gestorbbdd.altaCliente(cliente);
					JOptionPane.showMessageDialog(null, "Cliente insertado!");
					textDNI.setText(null);
					textNombre.setText(null);
					textApellido.setText(null);
					textDireccion.setText(null);
					textLocalidad.setText(null);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnGuardar.setBounds(118, 192, 89, 23);
		contentPane.add(btnGuardar);
		
		JButton btnModificar = new JButton("Modificar Cliente");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirMdificar();
			}
		});
		btnModificar.setBounds(85, 226, 155, 23);
		contentPane.add(btnModificar);
	}

	protected void abrirMdificar() {
		UpdateCliente modificarCliente = new UpdateCliente(this);
	    modificarCliente.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    modificarCliente.setModal(true);
	    modificarCliente.setVisible(true);
		
	}
}
