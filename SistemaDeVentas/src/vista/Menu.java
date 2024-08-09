package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;

import modelo.*;
import controlador.*;

public class Menu extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuItem mntmClientes;
	private JButton btnClientes;
	private JLabel lblNewLabel;
	private JButton btnProducto;
	private JButton btnIngresarVenta;
	private JButton btnIngresarStock;
	private JButton btnReportesGeneral;
	private JButton btnReportesImpTotal;
	private JButton btnReportesVentas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 767, 449);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMantenimiento = new JMenu("Mantenimiento");
		menuBar.add(mnMantenimiento);
		
		mntmClientes = new JMenuItem("Clientes");
		mntmClientes.addActionListener(this);
		mnMantenimiento.add(mntmClientes);
		
		JMenuItem mntmProductos = new JMenuItem("Productos");
		mnMantenimiento.add(mntmProductos);
		
		JMenu mnVentas = new JMenu("Ventas");
		menuBar.add(mnVentas);
		
		JMenu mnAlmacen = new JMenu("Almacen");
		menuBar.add(mnAlmacen);
		
		JMenu mnReportes = new JMenu("Reportes");
		menuBar.add(mnReportes);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(142, 155, 151));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnClientes = new JButton("CLIENTES");
		btnClientes.setForeground(new Color(244, 237, 219));
		btnClientes.setBackground(new Color(44, 74, 82));
		btnClientes.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnClientes.addActionListener(this);
		btnClientes.setBounds(35, 96, 140, 52);
		contentPane.add(btnClientes);
		
		lblNewLabel = new JLabel("MANTENIMIENTO");
		lblNewLabel.setForeground(new Color(244, 237, 219));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(24, 35, 165, 14);
		contentPane.add(lblNewLabel);
		
		btnProducto = new JButton("PRODUCTO");
		btnProducto.addActionListener(this);
		btnProducto.setForeground(new Color(244, 237, 219));
		btnProducto.setBackground(new Color(44, 74, 82));
		btnProducto.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnProducto.setBounds(35, 159, 140, 52);
		contentPane.add(btnProducto);
		
		JLabel lblVentas = new JLabel("VENTAS");
		lblVentas.setForeground(new Color(244, 237, 219));
		lblVentas.setHorizontalAlignment(SwingConstants.CENTER);
		lblVentas.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblVentas.setBounds(210, 35, 151, 14);
		contentPane.add(lblVentas);
		
		btnIngresarVenta = new JButton("INGRESAR VENTA");
		btnIngresarVenta.setForeground(new Color(244, 237, 219));
		btnIngresarVenta.setBackground(new Color(44, 74, 82));
		btnIngresarVenta.addActionListener(this);
		btnIngresarVenta.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnIngresarVenta.setBounds(210, 96, 160, 52);
		contentPane.add(btnIngresarVenta);
		
		JLabel lblAlmacen = new JLabel("ALMACEN");
		lblAlmacen.setForeground(new Color(244, 237, 219));
		lblAlmacen.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlmacen.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAlmacen.setBounds(383, 35, 151, 14);
		contentPane.add(lblAlmacen);
		
		JLabel lblReportes = new JLabel("REPORTES");
		lblReportes.setForeground(new Color(244, 237, 219));
		lblReportes.setHorizontalAlignment(SwingConstants.CENTER);
		lblReportes.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblReportes.setBounds(562, 35, 151, 14);
		contentPane.add(lblReportes);
		
		btnIngresarStock = new JButton("INGRESAR STOCK");
		btnIngresarStock.setForeground(new Color(244, 237, 219));
		btnIngresarStock.setBackground(new Color(44, 74, 82));
		btnIngresarStock.addActionListener(this);
		btnIngresarStock.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnIngresarStock.setBounds(383, 96, 160, 52);
		contentPane.add(btnIngresarStock);
		
		btnReportesGeneral = new JButton("GENERAL");
		btnReportesGeneral.setForeground(new Color(244, 237, 219));
		btnReportesGeneral.setBackground(new Color(44, 74, 82));
		btnReportesGeneral.addActionListener(this);
		btnReportesGeneral.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnReportesGeneral.setBounds(573, 96, 140, 52);
		contentPane.add(btnReportesGeneral);
		
		JButton btnReportesStock = new JButton("STOCK");
		btnReportesStock.setForeground(new Color(244, 237, 219));
		btnReportesStock.setBackground(new Color(44, 74, 82));
		btnReportesStock.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnReportesStock.setBounds(573, 159, 140, 52);
		contentPane.add(btnReportesStock);
		
		btnReportesVentas = new JButton("VENTAS");
		btnReportesVentas.setForeground(new Color(244, 237, 219));
		btnReportesVentas.setBackground(new Color(44, 74, 82));
		btnReportesVentas.addActionListener(this);
		btnReportesVentas.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnReportesVentas.setBounds(573, 222, 140, 52);
		contentPane.add(btnReportesVentas);
		
		btnReportesImpTotal = new JButton("IMP. TOTAL");
		btnReportesImpTotal.setForeground(new Color(244, 237, 219));
		btnReportesImpTotal.setBackground(new Color(44, 74, 82));
		btnReportesImpTotal.addActionListener(this);
		btnReportesImpTotal.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnReportesImpTotal.setBounds(573, 285, 140, 52);
		contentPane.add(btnReportesImpTotal);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnProducto) {
			actionPerformedBtnProducto(e);
		}
		if (e.getSource() == btnReportesVentas) {
			actionPerformedBtnReportesVentas(e);
		}
		if (e.getSource() == btnReportesImpTotal) {
			actionPerformedBtnReportesImpTotal(e);
		}
		if (e.getSource() == btnReportesGeneral) {
			actionPerformedBtnReportesGeneral(e);
		}
		if (e.getSource() == btnIngresarStock) {
			actionPerformedBtnIngresarStock(e);
		}
		if (e.getSource() == btnIngresarVenta) {
			actionPerformedBtnIngresarVenta(e);
		}
		if (e.getSource() == btnClientes) {
			actionPerformedBtnClientes(e);
		}
		if (e.getSource() == mntmClientes) {
			actionPerformedMntmClientes(e);
		}
	}
	protected void actionPerformedMntmClientes(ActionEvent e) {
	}
	protected void actionPerformedBtnClientes(ActionEvent e) {
		Clientes c = new Clientes();
		c.setLocationRelativeTo(this);
		c.setVisible(true);
	}
	protected void actionPerformedBtnIngresarVenta(ActionEvent e) {
	}
	protected void actionPerformedBtnIngresarStock(ActionEvent e) {
		Almacen a = new Almacen();
		a.setLocationRelativeTo(this);
		a.setVisible(true);
		
		
	}
	protected void actionPerformedBtnReportesGeneral(ActionEvent e) {
	}
	protected void actionPerformedBtnReportesImpTotal(ActionEvent e) {
	}
	protected void actionPerformedBtnReportesVentas(ActionEvent e) {
	}
	protected void actionPerformedBtnProducto(ActionEvent e) {
		
		Productos p = new Productos();
		p.setLocationRelativeTo(this);
		p.setVisible(true);
		
	}
}
