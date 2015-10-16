package ud.prog3.pr02;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.sql.Date;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class JLabelEstrella extends JLabel{
	
	public static final int TAMANYO_ESTRELLA = 40; 
	public static final int RADIO_ESFERA_ESTRELLA = 17;
	private double miGiro = Math.PI/2;
	private static final boolean DIBUJAR_ESFERA_ESTRELLA = true;
	
	
	
	public JLabelEstrella() {
		
		try {
			setIcon( new ImageIcon( JLabelEstrella.class.getResource( "img/estrella.png" ).toURI().toURL() ) );
		} catch (Exception e) {
			System.err.println( "Error en carga de recurso: coche.png no encontrado" );
			e.printStackTrace();
		}
		setBounds( 0, 0, TAMANYO_ESTRELLA, TAMANYO_ESTRELLA );
		
	}
	
	
	
	public void setGiro( double gradosGiro ) {
		// De grados a radianes...
		miGiro = gradosGiro/180*Math.PI;
		// El giro en la pantalla es en sentido horario (inverso):
		miGiro = -miGiro;  // Cambio el sentido del giro
		// Y el gráfico del coche apunta hacia arriba (en lugar de derecha OX)
		miGiro = miGiro + Math.PI/2; // Sumo 90º para que corresponda al origen OX
	}

	@Override
	protected void paintComponent(Graphics g) {
//		super.paintComponent(g);   // En este caso no nos sirve el pintado normal de un JLabel
		Image img = ((ImageIcon)getIcon()).getImage();
		Graphics2D g2 = (Graphics2D) g;  // El Graphics realmente es Graphics2D
		// Escalado más fino con estos 3 parámetros:
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);	
		// Prepara rotación (siguientes operaciones se rotarán)
        g2.rotate( miGiro, TAMANYO_ESTRELLA/2, TAMANYO_ESTRELLA/2 ); // getIcon().getIconWidth()/2, getIcon().getIconHeight()/2 );
        // Dibujado de la imagen
        g2.drawImage( img, 0, 0, TAMANYO_ESTRELLA, TAMANYO_ESTRELLA, null );
        if (DIBUJAR_ESFERA_ESTRELLA) g2.drawOval( TAMANYO_ESTRELLA/2-RADIO_ESFERA_ESTRELLA, TAMANYO_ESTRELLA/2-RADIO_ESFERA_ESTRELLA,
        		RADIO_ESFERA_ESTRELLA*2, RADIO_ESFERA_ESTRELLA*2 );
	}
	
	
	
}
