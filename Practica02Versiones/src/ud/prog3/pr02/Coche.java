package ud.prog3.pr02;

/** Clase para definir instancias l�gicas de coches con posici�n, direcci�n y velocidad.
 * @author Andoni Egu�luz
 * Facultad de Ingenier�a - Universidad de Deusto (2014)
 */
public class Coche {
	protected double miVelocidad;  // Velocidad en pixels/segundo
	protected double miDireccionActual;  // Direcci�n en la que estoy mirando en grados (de 0 a 360)
	protected double posX;  // Posici�n en X (horizontal)
	protected double posY;  // Posici�n en Y (vertical)
	protected String piloto;  // Nombre de piloto
	static double masa;
	protected double fuerzabaseadelante;
	protected double fuerzabaseatras;
	static double coefRozAire;
	static double coefRozSuelo;
	// Constructores
	
	public Coche() {
		miVelocidad = 0;
		miDireccionActual = 0;
		posX = 300;
		posY = 300;
		masa = 1;
		fuerzabaseadelante = 2000;
		fuerzabaseatras = 1000;
		coefRozAire = 0.35;
		coefRozSuelo = 15.5;
	}
	
	/** Devuelve la velocidad actual del coche en p�xeles por segundo
	 * @return	velocidad
	 */
	public double getVelocidad() {
		return miVelocidad;
	}

	/** Cambia la velocidad actual del coche
	 * @param miVelocidad
	 */
	public void setVelocidad( double miVelocidad ) {
		this.miVelocidad = miVelocidad;
	}

	public double getDireccionActual() {
		return miDireccionActual;
	}

	public void setDireccionActual( double dir ) {
		// if (dir < 0) dir = 360 + dir;
		if (dir > 360) dir = dir - 360;
		miDireccionActual = dir;
	}

	public double getPosX() {
		return posX;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosicion( double posX, double posY ) {
		setPosX( posX );
		setPosY( posY );
	}
	
	public void setPosX( double posX ) {
		this.posX = posX; 
	}
	
	public void setPosY( double posY ) {
		this.posY = posY; 
	}
	
	public String getPiloto() {
		return piloto;
	}

	public void setPiloto(String piloto) {
		this.piloto = piloto;
	}


	/** Cambia la velocidad actual del coche
	 * @param aceleracion	Incremento/decremento de la velocidad en pixels/segundo
	 * @param tiempo	Tiempo transcurrido en segundos
	 */
	public void acelera( double aceleracion, double tiempo ) {
		miVelocidad = MundoJuego.calcVelocidadConAceleracion( miVelocidad, aceleracion, tiempo );
	}
	
	/** Cambia la direcci�n actual del coche
	 * @param giro	Angulo de giro a sumar o restar de la direcci�n actual, en grados (-180 a +180)
	 * 				Considerando positivo giro antihorario, negativo giro horario
	 */
	public void gira( double giro ) {
		setDireccionActual( miDireccionActual + giro );
	}
	
	/** Cambia la posici�n del coche dependiendo de su velocidad y direcci�n
	 * @param tiempoDeMovimiento	Tiempo transcurrido, en segundos
	 */
	public void mueve( double tiempoDeMovimiento ) {
		setPosX( posX + MundoJuego.calcMovtoX( miVelocidad, miDireccionActual, tiempoDeMovimiento ) );
		setPosY( posY + MundoJuego.calcMovtoY( miVelocidad, miDireccionActual, tiempoDeMovimiento ) );
	}
	
	@Override
	public String toString() {
		return piloto + " (" + posX + "," + posY + ") - " +
			   "Velocidad: " + miVelocidad + " ## Direcci�n: " + miDireccionActual; 
	}
	
	/** Devuelve la fuerza de aceleraci�n del coche, de acuerdo al motor definido en la pr�ctica 2
	 * @return Fuerza de aceleraci�n en Newtixels
	 */
	 public double fuerzaAceleracionAdelante() {
		 if (miVelocidad<=-150) return fuerzabaseadelante;
		 else if (miVelocidad<=0)
		 return fuerzabaseadelante*(-miVelocidad/150*0.5+0.5);
		 else if (miVelocidad<=250)
		 return fuerzabaseadelante*(miVelocidad/250*0.5+0.5);
		 else if (miVelocidad<=750)
		 return fuerzabaseadelante;
		 else return fuerzabaseadelante*(-(miVelocidad-1000)/250);
	 } 
	 
	 public double fuerzaAceleracionAtras() {
		 if (miVelocidad<=-150) return fuerzabaseatras;
		 else if (miVelocidad<=0)
		 return fuerzabaseatras*(-miVelocidad/150*0.5+0.5);
		 else if (miVelocidad<=250)
		 return fuerzabaseatras*(miVelocidad/250*0.5+0.5);
		 else if (miVelocidad<=750)
		 return fuerzabaseatras;
		 else return fuerzabaseatras*(-(miVelocidad-1000)/250);
	 }

	public static double getMasa() {
		return masa;
	}

	public static void setMasa(double masa) {
		Coche.masa = masa;
	}

	public static double getCoefRozAire() {
		return coefRozAire;
	}

	public static void setCoefRozAire(double coefRozAire) {
		Coche.coefRozAire = coefRozAire;
	}

	public static double getCoefRozSuelo() {
		return coefRozSuelo;
	}

	public static void setCoefRozSuelo(double coefRozSuelo) {
		Coche.coefRozSuelo = coefRozSuelo;
	}

	public double getFuerzabaseadelante() {
		return fuerzabaseadelante;
	}

	public void setFuerzabaseadelante(double fuerzabaseadelante) {
		this.fuerzabaseadelante = fuerzabaseadelante;
	}

	public double getFuerzabaseatras() {
		return fuerzabaseatras;
	}

	public void setFuerzabaseatras(double fuerzabaseatras) {
		this.fuerzabaseatras = fuerzabaseatras;
	} 
	 
	 
	 
}
