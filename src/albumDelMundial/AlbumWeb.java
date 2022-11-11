package albumDelMundial;

import java.util.HashMap;

public class AlbumWeb extends Album {
	private int codigoPromocional;
	
	public AlbumWeb(
			String nombreDeUsuario, 
			int codigo, 
			int codigoPromocional,
			HashMap<String, Integer> seccionesDelAlbum,
			int totalDeFiguritasParaPegar
	) {
		super(nombreDeUsuario, codigo, seccionesDelAlbum, totalDeFiguritasParaPegar);
		
		this.codigoPromocional = codigoPromocional;
	}
	
}
