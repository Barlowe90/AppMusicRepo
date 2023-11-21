package utilidades.pulsador;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.Vector;

import javax.swing.*;

public class DarkMode extends JPanel implements Serializable {
	private static final long serialVersionUID = 1L;
	// Propiedades
	private boolean encendido = false;
	private String nombre;
	private JPanel panelOn, panelOff;

	// Propiedades
	private Vector<IEncendidoListener> encendidoListeners = new Vector<>();

	public DarkMode() {
        setPreferredSize(new Dimension(60, 30));
        setLayout(new GridLayout(1, 2, 0, 0));

		panelOn = new JPanel();
		panelOff = new JPanel();

		configurarPanel(panelOn, Color.WHITE, "luna.png");
		configurarPanel(panelOff, Color.BLACK, "sol.png");

		panelOn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ModoOscuroPressed();
			}
		});
		
		panelOff.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ModoOscuroPressed();
			}
		});

		add(panelOn);
		add(panelOff);
	}

	private void configurarPanel(JPanel panel, Color fondo, String iconoPath) {
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBackground(fondo);
		panel.setBorder(BorderFactory.createEmptyBorder());

		panel.add(Box.createHorizontalGlue());

		JLabel lblIcono = new JLabel();
		ImageIcon icono = new ImageIcon(DarkMode.class.getResource(iconoPath));
		lblIcono.setIcon(icono);
		panel.add(lblIcono);

		panel.add(Box.createHorizontalGlue());
	}

	public void ModoOscuroPressed() {
		actualizarPaneles();
		encendido = !encendido;
		notificarCambioEncendido(new EncendidoEvent(this, !encendido, encendido));
	}

	public void setEncendido(boolean newEncendido) {
		boolean oldEncendido = encendido;
		encendido = newEncendido;
		actualizarPaneles();
		notificarCambioEncendido(new EncendidoEvent(this, oldEncendido, newEncendido));
	}

	private void actualizarPaneles() {
		panelOn.setBackground(encendido ? Color.WHITE : Color.BLACK);
		panelOff.setBackground(encendido ? Color.BLACK : Color.WHITE);
	}

	private void notificarCambioEncendido(EncendidoEvent evento) {
		Vector<IEncendidoListener> lista;
		synchronized (this) {
			lista = new Vector<>(encendidoListeners);
		}
		for (IEncendidoListener listener : lista) {
			listener.enteradoCambioEncendido(evento);
		}
	}

	public synchronized void addEncendidoListener(IEncendidoListener listener) {
		encendidoListeners.addElement(listener);
	}

	public synchronized void removeEncendidoListener(IEncendidoListener listener) {
		encendidoListeners.removeElement(listener);
	}

	public Dimension getPreferredSize() {
		return new Dimension(14, 7);
	}

	// deprecated
	public Dimension getMinimumSize() {
		return getPreferredSize();
	}

	public boolean isEncendido() {
		return encendido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
