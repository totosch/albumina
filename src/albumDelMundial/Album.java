package albumDelMundial;

import java.util.HashSet;
import java.util.List;

public class Album {
	private int codigo;
	private String nombreDeUsuario;
	private HashSet<Figurita> figuritasPegadas;
	
	public Album(String nombreDeUsuario, int codigo) {
		this.nombreDeUsuario = nombreDeUsuario;
		this.codigo = codigo;
	}
	
	private static int TOTAL_DE_FIGURITAS_PARA_PEGAR = 320;
	
	public int obtenerCodigo() {
		return codigo;
	}

	public boolean estoyCompletado() {
		return (figuritasPegadas.size() == TOTAL_DE_FIGURITAS_PARA_PEGAR);
	}
	
	public boolean completoPais() {
		return false;
	}
	
	public static Album obtenerAlbumPorSuTipo (String tipoDeAlbum, String nombreDeUsuario) {
		Album albumElegido = null;
		
		switch (tipoDeAlbum) {
		 case "Tradicional":
			 albumElegido = Fabrica.solicitudAFabrica.crearAlbumTradicional();
		 case "Extendido":
			 albumElegido = Fabrica.solicitudAFabrica.crearAlbumExtendido();
		 case "Web":
			 albumElegido = Fabrica.solicitudAFabrica.crearAlbumWeb();
		}
		
		if (albumElegido == null)
			throw new RuntimeException("El tipo de album elegido no es valido");
		
		return albumElegido;
		
	}
	
	public HashSet<Figurita> obtenerFiguritasPegadas() {
		return figuritasPegadas;
	}
	
	public void pegarFigurita(Figurita figurita) {
		this.figuritasPegadas.add(figurita);
	}
}
