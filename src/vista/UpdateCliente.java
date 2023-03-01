package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Cliente;
import modelo.GestorBBDD;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;

public class UpdateCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textLocalidad;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textDireccion;
	private JTextField textDni;
	
	private JLabel lblDni;
	private JLabel lblNombre;
	private JLabel lblApellido;

	/**
	 * Create the dialog.
	 * @param principal 
	 * @param b 
	 * @param contentPane 
	 * @wbp.parser.constructor
	 */
	public UpdateCliente(Principal padre) {
		setTitle("Actualizar Cliente");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
			lblDni = new JLabel("DNI");
			lblDni.setBounds(50, 31, 46, 14);
			contentPanel.add(lblDni);
		
		
			lblNombre = new JLabel("Nombre");
			lblNombre.setBounds(50, 61, 46, 14);
			contentPanel.add(lblNombre);

			lblApellido = new JLabel("Apellido");
			lblApellido.setBounds(50, 91, 46, 14);
			contentPanel.add(lblApellido);
		
			JLabel lblDireccion = new JLabel("Direccion");
			lblDireccion.setBounds(50, 121, 59, 14);
			contentPanel.add(lblDireccion);

			JLabel lblLocalidad = new JLabel("Localidad");
			lblLocalidad.setBounds(50, 151, 59, 14);
			contentPanel.add(lblLocalidad);

			textLocalidad = new JTextField();
			textLocalidad.setColumns(10);
			textLocalidad.setBounds(119, 145, 86, 20);
			contentPanel.add(textLocalidad);

			textNombre = new JTextField();
			textNombre.setColumns(10);
			textNombre.setBounds(119, 55, 86, 20);
			contentPanel.add(textNombre);

			textApellido = new JTextField();
			textApellido.setColumns(10);
			textApellido.setBounds(119, 85, 86, 20);
			contentPanel.add(textApellido);

			textDireccion = new JTextField();
			textDireccion.setColumns(10);
			textDireccion.setBounds(119, 115, 86, 20);
			contentPanel.add(textDireccion);

			textDni = new JTextField();
			textDni.setColumns(10);
			textDni.setBounds(119, 24, 86, 20);
			contentPanel.add(textDni);

			JButton btnCargar = new JButton("Cargar");
			btnCargar.setBounds(239, 22, 89, 23);
			contentPanel.add(btnCargar);

			JButton btnModificar = new JButton("Modificar");
			btnModificar.setEnabled(false);
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					GestorBBDD gestorbbdd = new GestorBBDD();
					Cliente nuevoCliente = new Cliente();
					nuevoCliente.setDni(textDni.getText());
					nuevoCliente.setNombre(textNombre.getText());
					nuevoCliente.setApellidos(textApellido.getText());
					nuevoCliente.setDireccion(textDireccion.getText());
					nuevoCliente.setLocalidad(textLocalidad.getText());
					try {
						gestorbbdd.modificarCliente(nuevoCliente);
						JOptionPane.showMessageDialog(null, "Cliente modificado!");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
				
			});
			btnModificar.setBounds(119, 176, 89, 23);
			contentPanel.add(btnModificar);
			
			JButton btnDniEquivocado = new JButton("¿DNI Erroneo?");
			btnDniEquivocado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					btnModificar.setEnabled(false);
					btnCargar.setEnabled(true);
					textDni.setEditable(true);
					btnDniEquivocado.setVisible(false);
					
					textNombre.setText(null);
					textApellido.setText(null);
					textDireccion.setText(null);
					textLocalidad.setText(null);
					
				}
			});
			btnDniEquivocado.setBounds(239, 57, 119, 23);
			contentPanel.add(btnDniEquivocado);
			
			JButton btnEliminar = new JButton("Eliminar");
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(textDni.getText().length() == 0) {
						JOptionPane.showMessageDialog(null, "Error! Introduce el DNI del cliente al que quieres eliminar!");
					}else {
						GestorBBDD gestorbbdd = new GestorBBDD();
						Cliente clienteAEliminar = new Cliente();
						clienteAEliminar.setDni(textDni.getText());
						try {
							gestorbbdd.bajaCliente(clienteAEliminar);
							JOptionPane.showMessageDialog(null, "Cliente eliminado!");
							textDni.setText(null);
							textNombre.setText(null);
							textApellido.setText(null);
							textLocalidad.setText(null);
							textDireccion.setText(null);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
					
				}
			});
			btnEliminar.setBounds(335, 22, 89, 23);
			contentPanel.add(btnEliminar);
			btnDniEquivocado.setVisible(false);

			btnCargar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					GestorBBDD gestorbbdd = new GestorBBDD();
					try {
						Cliente clienteCargado = gestorbbdd.getCliente(textDni.getText());
						if(clienteCargado.getDni() == "-1") {
							JOptionPane.showMessageDialog(null, "No hemos encontrado ese cliente!");
						}else {
						btnModificar.setEnabled(true);
						btnDniEquivocado.setVisible(true);
						btnCargar.setEnabled(false);
						textDni.setEditable(false);
			
						textNombre.setText(clienteCargado.getNombre());
						textApellido.setText(clienteCargado.getApellidos());
						textDireccion.setText(clienteCargado.getDireccion());
						textLocalidad.setText(clienteCargado.getLocalidad());
					}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
			
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);

				JButton cerrarButton = new JButton("Cerrar");
				cerrarButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cerrarButton.setActionCommand("Cancel");
				buttonPane.add(cerrarButton);
			
	}
}
