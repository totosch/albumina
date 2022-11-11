package albumDelMundial;

import java.util.HashMap;

public class AlbumExtendido extends Album {
	
	public AlbumExtendido(
			String nombreDeUsuario, 
			int codigo, 
			HashMap<String, Integer> seccionesDelAlbum,
			int totalDeFiguritasParaPegar 
	) {
		super(nombreDeUsuario, codigo, seccionesDelAlbum, totalDeFiguritasParaPegar);
	}
}
