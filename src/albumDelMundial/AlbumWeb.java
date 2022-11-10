package albumDelMundial;

public class AlbumWeb extends Album {
	private int codigoPromocional;
	
	public AlbumWeb(String nombreDeUsuario, int codigo, int codigoPromocional) {
		super(nombreDeUsuario, codigo);
		
		this.codigoPromocional = codigoPromocional;
	}
	
}
