package albumDelMundial;

import java.util.HashMap;

public class AlbumWeb extends Album {
	private int codigoPromocional;
	
    public AlbumWeb(
            String nombreDeUsuario, 
            int codigo, 
            int codigoPromocional,
            HashMap<String, Integer> seccionesDelAlbum
    ) {
        super(nombreDeUsuario, codigo, seccionesDelAlbum);
        
        this.codigoPromocional = codigoPromocional;
    }
}
