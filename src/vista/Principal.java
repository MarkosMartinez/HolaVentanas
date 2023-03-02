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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.Cliente;
import modelo.GestorBBDD;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textDNI;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textDireccion;
	private JTextField textLocalidad;
	private JTextField textUpDni;
	private JTextField textUpNombre;
	private JTextField textUpApellido;
	private JTextField textUpDireccion;
	private JTextField textUpLocalidad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
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
		setTitle("Hola Ventanas");
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
		
		textDNI = new JTextField();
		textDNI.setToolTipText("");
		textDNI.setColumns(10);
		textDNI.setBounds(79, 11, 86, 20);
		instCliente.add(textDNI);
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(79, 42, 86, 20);
		instCliente.add(textNombre);
		
		textApellido = new JTextField();
		textApellido.setColumns(10);
		textApellido.setBounds(79, 72, 86, 20);
		instCliente.add(textApellido);
		
		textDireccion = new JTextField();
		textDireccion.setColumns(10);
		textDireccion.setBounds(79, 102, 86, 20);
		instCliente.add(textDireccion);
		
		textLocalidad = new JTextField();
		textLocalidad.setColumns(10);
		textLocalidad.setBounds(79, 132, 86, 20);
		instCliente.add(textLocalidad);
		
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
		btnGuardar.setBounds(79, 176, 89, 23);
		instCliente.add(btnGuardar);
		
		JPanel modCliente = new JPanel();
		tabbedPane.addTab("Modificar / Eliminar Cliente", null, modCliente, null);
		modCliente.setLayout(null);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(27, 11, 46, 14);
		modCliente.add(lblDni);
		
		JLabel lblNombre_1 = new JLabel("Nombre");
		lblNombre_1.setBounds(27, 41, 46, 14);
		modCliente.add(lblNombre_1);
		
		JLabel lblApellido_1 = new JLabel("Apellido");
		lblApellido_1.setBounds(27, 71, 46, 14);
		modCliente.add(lblApellido_1);
		
		JLabel lblDireccion_1 = new JLabel("Direccion");
		lblDireccion_1.setBounds(27, 101, 59, 14);
		modCliente.add(lblDireccion_1);
		
		JLabel lblLocalidad_1 = new JLabel("Localidad");
		lblLocalidad_1.setBounds(27, 131, 59, 14);
		modCliente.add(lblLocalidad_1);
		
		textUpDni = new JTextField();
		textUpDni.setBounds(85, 8, 86, 20);
		modCliente.add(textUpDni);
		textUpDni.setColumns(10);
		
		textUpNombre = new JTextField();
		textUpNombre.setColumns(10);
		textUpNombre.setBounds(85, 38, 86, 20);
		modCliente.add(textUpNombre);
		
		textUpApellido = new JTextField();
		textUpApellido.setColumns(10);
		textUpApellido.setBounds(85, 68, 86, 20);
		modCliente.add(textUpApellido);
		
		textUpDireccion = new JTextField();
		textUpDireccion.setColumns(10);
		textUpDireccion.setBounds(85, 98, 86, 20);
		modCliente.add(textUpDireccion);
		
		textUpLocalidad = new JTextField();
		textUpLocalidad.setColumns(10);
		textUpLocalidad.setBounds(85, 128, 86, 20);
		modCliente.add(textUpLocalidad);
		
		JButton btnCargar = new JButton("Cargar");
		JButton btnDniEquivocado = new JButton("¿DNI Erroneo?");
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				GestorBBDD gestorbbdd = new GestorBBDD();
				Cliente nuevoCliente = new Cliente();
				nuevoCliente.setDni(textUpDni.getText());
				nuevoCliente.setNombre(textUpNombre.getText());
				nuevoCliente.setApellidos(textUpApellido.getText());
				nuevoCliente.setDireccion(textUpDireccion.getText());
				nuevoCliente.setLocalidad(textUpLocalidad.getText());
				try {
					gestorbbdd.modificarCliente(nuevoCliente);
					JOptionPane.showMessageDialog(null, "Cliente modificado!");
					
					btnModificar.setEnabled(false);
					btnCargar.setEnabled(true);
					textUpDni.setEditable(true);
					btnDniEquivocado.setVisible(false);
					
					textUpDni.setText(null);
					textUpNombre.setText(null);
					textUpApellido.setText(null);
					textUpDireccion.setText(null);
					textUpLocalidad.setText(null);
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnModificar.setEnabled(false);
		btnModificar.setBounds(85, 156, 89, 23);
		modCliente.add(btnModificar);
		
		btnDniEquivocado.setVisible(false);
		
		btnDniEquivocado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				btnModificar.setEnabled(false);
				btnCargar.setEnabled(true);
				textUpDni.setEditable(true);
				btnDniEquivocado.setVisible(false);
				
				textUpNombre.setText(null);
				textUpApellido.setText(null);
				textUpDireccion.setText(null);
				textUpLocalidad.setText(null);
				
			}
		});
		btnDniEquivocado.setBounds(189, 37, 127, 23);
		modCliente.add(btnDniEquivocado);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textUpDni.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Error! Introduce el DNI del cliente al que quieres eliminar!");
				}else {
					GestorBBDD gestorbbdd = new GestorBBDD();
					Cliente clienteAEliminar = new Cliente();
					clienteAEliminar.setDni(textUpDni.getText());
					try {
						gestorbbdd.bajaCliente(clienteAEliminar);
						JOptionPane.showMessageDialog(null, "Cliente eliminado!");
						textUpDni.setText(null);
						textUpNombre.setText(null);
						textUpApellido.setText(null);
						textUpLocalidad.setText(null);
						textUpDireccion.setText(null);
						
						btnModificar.setEnabled(false);
						btnCargar.setEnabled(true);
						btnDniEquivocado.setVisible(false);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
			}
		});
		btnEliminar.setBounds(288, 7, 89, 23);
		modCliente.add(btnEliminar);
		
		
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				GestorBBDD gestorbbdd = new GestorBBDD();
				try {
					Cliente clienteCargado = gestorbbdd.getCliente(textUpDni.getText());
					if(clienteCargado.getDni() == "-1") {
						JOptionPane.showMessageDialog(null, "No hemos encontrado ese cliente!");
					}else {
					btnModificar.setEnabled(true);
					btnDniEquivocado.setVisible(true);
					btnCargar.setEnabled(false);
					textUpDni.setEditable(false);
		
					textUpNombre.setText(clienteCargado.getNombre());
					textUpApellido.setText(clienteCargado.getApellidos());
					textUpDireccion.setText(clienteCargado.getDireccion());
					textUpLocalidad.setText(clienteCargado.getLocalidad());
				}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnCargar.setBounds(189, 7, 89, 23);
		modCliente.add(btnCargar);
		
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
		verClientes.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		table.setModel(model);

		
		verClientes.add(table);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            try {
	                ArrayList<Cliente> nuevosClientes = gestorbbdd.getClientes();
	                model.setRowCount(0);
	                Iterator<Cliente> iter = nuevosClientes.iterator();
	                while (iter.hasNext()) {
	                    Cliente cliente = iter.next();
	                    Object[] fila = {cliente.getDni(), cliente.getNombre(),
	                                     cliente.getApellidos(), cliente.getDireccion(),
	                                     cliente.getLocalidad()};
	                    model.addRow(fila);
	                }
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        }
	    });
		verClientes.add(btnActualizar);
		
	}
}
