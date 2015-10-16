package ud.prog3.pr02;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.*;

/** Clase principal de minijuego de coche para Práctica 02 - Prog III
 * Ventana del minijuego.
 * @author Andoni Eguíluz
 * Facultad de Ingeniería - Universidad de Deusto (2014)
 */
public class VentanaJuego extends JFrame {
	private static final long serialVersionUID = 1L;  // Para serialización
	JPanel pPrincipal;         // Panel del juego (layout nulo)
	MundoJuego miMundo;        // Mundo del juego
	CocheJuego miCoche;        // Coche del juego
	MiRunnable miHilo = null;  // Hilo del bucle principal de juego	
	boolean[] booleanos;
//	JLabel estrellasAtrapadas;
//	JLabel estrellasDejadas;
	int fallos = 0;
	int cogidos = 0;
	JLabel mensaje; 

	/** Constructor de la ventana de juego. Crea y devuelve la ventana inicializada
	 * sin coches dentro
	 */
	public VentanaJuego() {
		// Liberación de la ventana por defecto al cerrar
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		// Creación contenedores y componentes
		pPrincipal = new JPanel();
		mensaje = new JLabel();
//		estrellasAtrapadas = new JLabel();
//		estrellasDejadas= new JLabel();
		booleanos = new boolean[4];
//		JPanel pBotonera = new JPanel();
//		JButton bAcelerar = new JButton( "Acelera" );
//		JButton bFrenar = new JButton( "Frena" );
//		JButton bGiraIzq = new JButton( "Gira Izq." );
//		JButton bGiraDer = new JButton( "Gira Der." );
		// Formato y layouts
		pPrincipal.setLayout( null );
		pPrincipal.setBackground( Color.white );
		// Añadido de componentes a contenedores
		add( pPrincipal, BorderLayout.CENTER );
//		pBotonera.add( bAcelerar );
//		pBotonera.add( bFrenar );
//		pBotonera.add( bGiraIzq );
//		pBotonera.add( bGiraDer );
//		add( pBotonera, BorderLayout.SOUTH );
		// Formato de ventana
//		mensaje.add(estrellasAtrapadas);
//		mensaje.add(estrellasDejadas);
		add( mensaje, BorderLayout.SOUTH );
		setSize( 500, 500 );
		setResizable( false );
		// Escuchadores de botones
//		bAcelerar.addActionListener( new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				miCoche.acelera( +10, 1 );
//				// System.out.println( "Nueva velocidad de coche: " + miCoche.getVelocidad() );
//			}
//		});
//		bFrenar.addActionListener( new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				miCoche.acelera( -10, 1 );
//				// System.out.println( "Nueva velocidad de coche: " + miCoche.getVelocidad() );
//			}
//		});
//		bGiraIzq.addActionListener( new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				miCoche.gira( +10 );
//				// System.out.println( "Nueva dirección de coche: " + miCoche.getDireccionActual() );
//			}
//		});
//		bGiraDer.addActionListener( new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				miCoche.gira( -10 );
//				// System.out.println( "Nueva dirección de coche: " + miCoche.getDireccionActual() );
//			}
//		});
		
		// Añadido para que también se gestione por teclado con el KeyListener
		pPrincipal.addKeyListener( new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
					case KeyEvent.VK_UP: {
						
						booleanos[0] = true;
						
						break;
					}
					case KeyEvent.VK_DOWN: {
						
						
						booleanos[1] = true;
						
						
						break;
					}
					case KeyEvent.VK_LEFT: {
						
							booleanos[2] = true;
						
						break;
					}
					case KeyEvent.VK_RIGHT: {
						
							booleanos[3] = true;
						
						break;
					}
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
					case KeyEvent.VK_UP: {
						
						booleanos[0] = false;
						
						break;
					}
					case KeyEvent.VK_DOWN: {
						
						
						booleanos[1] = false;
						
						
						break;
					}
					case KeyEvent.VK_LEFT: {
						
							booleanos[2] = false;
						
						break;
					}
					case KeyEvent.VK_RIGHT: {
						
							booleanos[3] = false;
						
						break;
					}
				}
			}
		});
		
		pPrincipal.setFocusable(true);
		pPrincipal.requestFocus();
		pPrincipal.addFocusListener( new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				pPrincipal.requestFocus();
			}
		});
		// Cierre del hilo al cierre de la ventana
		addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (miHilo!=null) miHilo.acaba();
			}
		});
	}
	
	/** Programa principal de la ventana de juego
	 * @param args
	 */
	public static void main(String[] args) {
		// Crea y visibiliza la ventana con el coche
		try {
			final VentanaJuego miVentana = new VentanaJuego();
			SwingUtilities.invokeAndWait( new Runnable() {
				@Override
				public void run() {
					miVentana.setVisible( true );
				}
			});
			miVentana.miMundo = new MundoJuego( miVentana.pPrincipal );
			miVentana.miMundo.creaCoche( 150, 100 );
			miVentana.miCoche = miVentana.miMundo.getCoche();
			miVentana.miCoche.setPiloto( "Fernando Alonso" );
			// Crea el hilo de movimiento del coche y lo lanza
			miVentana.miHilo = miVentana.new MiRunnable();  // Sintaxis de new para clase interna
			Thread nuevoHilo = new Thread( miVentana.miHilo );
			nuevoHilo.start();
		} catch (Exception e) {
			System.exit(1);  // Error anormal
		}
	}
	
	/** Clase interna para implementación de bucle principal del juego como un hilo
	 * @author Andoni Eguíluz
	 * Facultad de Ingeniería - Universidad de Deusto (2014)
	 */
	class MiRunnable implements Runnable {
		boolean sigo = true;
		double segundos;
		@Override
		public void run() {
			// Bucle principal forever hasta que se pare el juego...
			
//			estrellasDejadas.setText(estrellasDejadas.getText() + "Fallos = " + fallos );
//			estrellasAtrapadas.setText(estrellasAtrapadas.getText() + "Puntuación = " + cogidos );
			
			while (sigo) {
				// Mover coche
				miCoche.mueve( 0.040 );
				
				
				double rozamiento = miMundo.calcFuerzaRozamiento(miCoche.getMasa(), miCoche.getCoefRozSuelo(), miCoche.getCoefRozAire(), miCoche.getVelocidad());

				if(booleanos[0]==true){
					miMundo.calcAceleracionConFuerza(miCoche.fuerzaAceleracionAdelante(), miCoche.getMasa());
					miMundo.aplicarFuerza(miCoche.fuerzaAceleracionAdelante(), miCoche);
					
				}
				if(booleanos[0]==false){
					miMundo.calcAceleracionConFuerza(miCoche.fuerzaAceleracionAdelante(), miCoche.getMasa());
					miMundo.aplicarFuerza(rozamiento, miCoche);
					
				}
				if(booleanos[1]==true){
					miMundo.calcAceleracionConFuerza(-miCoche.fuerzaAceleracionAtras(), miCoche.getMasa());
					miMundo.aplicarFuerza(-miCoche.fuerzaAceleracionAtras(), miCoche);
					
				}
				if(booleanos[1]==false){
					miMundo.calcAceleracionConFuerza(miCoche.fuerzaAceleracionAdelante(), miCoche.getMasa());
					miMundo.aplicarFuerza(rozamiento, miCoche);
					
				}
				if(booleanos[2]==true){
					miCoche.gira(+10);
				}
				if(booleanos[3]==true){
					miCoche.gira(-10);
				}
				
				if (segundos>=1.2){
					
					miMundo.creaEstrella();
					segundos=0.0;
				} else{
					
					segundos = segundos + 0.040;
					
				}
				fallos=miMundo.quitaYRotaEstrellas(6000);
				cogidos = miMundo.choquesConEstrellas();
				int puntuacion = cogidos*5;
				
				mensaje.setText( "  Atrapadas :                 " + cogidos + "    Puntuación :               " + puntuacion +"    Perdidas :               " + fallos );
				if(fallos==10){
					acaba();
					JOptionPane.showMessageDialog(pPrincipal, "GAME OVER ", "Has dejado escapar 10 estrellas",JOptionPane.INFORMATION_MESSAGE);
					pPrincipal.setVisible(false);						
				}
				
				// Chequear choques
				// (se comprueba tanto X como Y porque podría a la vez chocar en las dos direcciones (esquinas)
				if (miMundo.hayChoqueHorizontal(miCoche)) // Espejo horizontal si choca en X
					miMundo.rebotaHorizontal(miCoche);
				if (miMundo.hayChoqueVertical(miCoche)) // Espejo vertical si choca en Y
					miMundo.rebotaVertical(miCoche);
				// Dormir el hilo 40 milisegundos
				try {
					Thread.sleep( 40 );
				} catch (Exception e) {
				}
			}
		}
		/** Ordena al hilo detenerse en cuanto sea posible
		 */
		public void acaba() {
			sigo = false;
		}
	};
	
}
