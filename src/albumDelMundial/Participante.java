package albumDelMundial;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Participante {
	private int dni;
	private String nombreDeUsuario;
	private HashSet<Figurita> figuritasObtenidas;
	private ArrayList<Figurita> figuritasRepetidas;
	private Album albumComprado;
	private String tipoDeAlbumComprado;
	private boolean haUtilizadoCodigoPromocional;

	public Participante(int dni, String nombreDeUsuario, String tipoDeAlbum) {
		this.dni = dni;
		this.nombreDeUsuario = nombreDeUsuario;
		this.figuritasObtenidas = new HashSet<Figurita>();
		this.figuritasRepetidas = new ArrayList<Figurita>();

		this.albumComprado = Album.generarAlbumPorSuTipo(tipoDeAlbum, nombreDeUsuario);
		
		this.tipoDeAlbumComprado = tipoDeAlbum;
		this.haUtilizadoCodigoPromocional = false;
	}
	
	public int obtenerCodigoDeAlbum() {
		return this.albumComprado.obtenerCodigo();
	}
	
	public void recibirFiguritas (List<Figurita> figuritas) {
		for (Figurita figurita : figuritas) {
			recibirFigurita(figurita);
		}
	}
	
	private void recibirFigurita(Figurita figurita) {
		boolean yaLaPoseeParticipante = false;
		for (Figurita figuritaActual: figuritasObtenidas) {
			if(figurita.compareTo(figuritaActual) == 0)
				yaLaPoseeParticipante = true;
			
		}
		
		if (yaLaPoseeParticipante) {
			figuritasRepetidas.add(figurita);
			
			return;
		}
		
		figuritasObtenidas.add(figurita);
	}
	
	public void entregarFigurita(Figurita figurita, Participante participanteQueRecibe) {
		participanteQueRecibe.recibirFigurita(figurita);
		if (figuritasObtenidas.contains(figurita)) {
			figuritasObtenidas.remove(figurita);
			
			return;
		}
		
		figuritasRepetidas.remove(figurita);
	}

	public boolean completoAlbum() {
		return this.albumComprado.estoyCompletado();
	}
	
	public HashSet<Figurita> obtenerFiguritasPegadasDeAlbum() {
		return this.albumComprado.obtenerFiguritasPegadas();
	}
	
	public void pegarFiguritas() {
		for (Figurita figurita: figuritasObtenidas)
			if (!albumComprado.obtenerFiguritasPegadas().contains(figurita))
				albumComprado.pegarFigurita(figurita);		
	}
	 
	public boolean tieneFiguritasRepetidas() {
		return this.figuritasRepetidas.size() > 0;
	}
	
	public int obtenerNumeroDeFiguritaRepetida(int indice) {
		return getFiguritasRepetidas().get(indice - 1).getNumero();
	}
	
	public String toString(String premio) {
		return "- " + this.dni + " " + this.nombreDeUsuario + ": " + premio;
	}
	
	@Override
	public String toString() {
		return this.dni + ", " + this.nombreDeUsuario + ", " + this.albumComprado.toString();
	}
	
	public Figurita buscarFiguritaMedianteCodigo(int codigoDeFigurita) {
		for (Figurita figurita: figuritasObtenidas) 
			if (figurita.getNumero() == codigoDeFigurita)
				return figurita;
		
		for (Figurita figurita: figuritasRepetidas) 
			if (figurita.getNumero() == codigoDeFigurita)
				return figurita;		
		
		return null;
	}
	
	public Figurita encontrarFiguritaRepetidaMenorOIgualEnValor(int valor) {
		Figurita figuritaEncontrada = null;
		int indice = 0;
		
		while(figuritaEncontrada.equals(null)) {
			Figurita figurita = figuritasRepetidas.get(indice);
			
			if (figurita.calcularValorFinal() <= valor)
				figuritaEncontrada = figurita;
			
			indice++;
		}
		
		return figuritaEncontrada;
	}

	public boolean completoPais(String pais) {
		return albumComprado.obtenerSeccionesDelAlbum().get(pais) == 12;
	}
	
	public int getDni() {
		return dni;
	}

	public String getNombreDeUsuario() {
		return nombreDeUsuario;
	}
	
	public String obtenerTipoDeAlbumComprado() { 
		return tipoDeAlbumComprado;
	}

	public HashSet<Figurita> getFiguritasObtenidas() {
		return figuritasObtenidas;
	}

	public ArrayList<Figurita> getFiguritasRepetidas() {
		return figuritasRepetidas;
	}

	public Album getAlbumComprado() {
		return albumComprado;
	}
	
	public boolean haUtilizadoCodigoPromocional() {
		return haUtilizadoCodigoPromocional;
	}
	
	public void utilizarCodigoPromocional() {
		this.haUtilizadoCodigoPromocional = true;
	}
	
}
