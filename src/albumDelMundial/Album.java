package albumDelMundial;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Album {
	private int codigo;
	private String nombreDeUsuario;
	private HashSet<Figurita> figuritasPegadas;
	private HashMap<String, Integer> seccionesDelAlbum;
	private int maximoDeFiguritas;
	
	public Album(
			String nombreDeUsuario, 
			int codigo, 
			HashMap<String, Integer> seccionesDelAlbum,
			int totalDeFiguritasParaPegar) {
		this.nombreDeUsuario = nombreDeUsuario;
		this.codigo = codigo;
		this.seccionesDelAlbum = seccionesDelAlbum;
		this.figuritasPegadas = new HashSet<Figurita>();
		this.maximoDeFiguritas = totalDeFiguritasParaPegar;
	}
	
	@Override
	public String toString() {
		return "Album nro: " + this.codigo;
	}
	
	public boolean estoyCompletado() {
		return figuritasPegadas.size() == this.maximoDeFiguritas;
	}
	
	public static Album generarAlbumPorSuTipo (String tipoDeAlbum, String nombreDeUsuario) {
		Album albumElegido = null;
		
		switch (tipoDeAlbum) {
		 case "Tradicional":
			 albumElegido = Fabrica.solicitudAFabrica.crearAlbumTradicional(nombreDeUsuario);
		 case "Extendido":
			 albumElegido = Fabrica.solicitudAFabrica.crearAlbumExtendido(nombreDeUsuario);
		 case "Web":
			 albumElegido = Fabrica.solicitudAFabrica.crearAlbumWeb(nombreDeUsuario);
		}
		
		if (albumElegido == null)
			throw new RuntimeException("El tipo de album elegido no es valido");
		
		return albumElegido;
		
	}
	
	public void pegarFigurita(Figurita figurita) {
		this.figuritasPegadas.add(figurita);
		String pais = Fabrica.solicitudAFabrica.obtenerPaisSegunNumeroDeFigurita(figurita.getNumero());
		
		this.seccionesDelAlbum.put(pais, seccionesDelAlbum.get(pais) + 1);
	}
	
	public HashSet<Figurita> obtenerFiguritasPegadas() {
		return figuritasPegadas;
	}
	
	public HashMap<String, Integer> obtenerSeccionesDelAlbum() {
		return this.seccionesDelAlbum;
	}
	
	public int obtenerCodigo() {
		return codigo;
	}
	
	public boolean completoPais() {
		return false;
	}
}
