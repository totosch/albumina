package albumDelMundial;

public class AlbumTradicional extends Album {
	private int lugaresParaPegar;
	
	public AlbumTradicional (String nombreDeUsuario, int codigo, int lugaresParaPegar) {
		super(nombreDeUsuario, codigo);
		
		this.lugaresParaPegar = lugaresParaPegar;
	}
}
