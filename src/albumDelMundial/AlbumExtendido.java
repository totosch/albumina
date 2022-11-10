package albumDelMundial;

public class AlbumExtendido extends Album {
	private int lugaresParaPegar;
	
	public AlbumExtendido(String nombreDeUsuario, int codigo, int lugaresParaPegar) {
		super(nombreDeUsuario, codigo);
		
		this.lugaresParaPegar = lugaresParaPegar;
	}
}
