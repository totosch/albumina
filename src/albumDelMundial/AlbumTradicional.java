package albumDelMundial;

import java.util.HashMap;

public class AlbumTradicional extends Album {
	
	public AlbumTradicional(
			String nombreDeUsuario, 
			int codigo,
			HashMap<String, Integer> seccionesDelAlbum,
			int totalDeFiguritasParaPegar 
	) {
		super(nombreDeUsuario, codigo, seccionesDelAlbum, totalDeFiguritasParaPegar);
	}
}
