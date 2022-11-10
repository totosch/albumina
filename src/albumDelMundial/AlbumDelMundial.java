package albumDelMundial;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import albumDelMundial.Figurita.TipoDeFigurita;

public class AlbumDelMundial implements IAlbumDelMundial{
	private ArrayList<Participante> participantes;
	
	public AlbumDelMundial() {
		this.participantes = new ArrayList<Participante>();
	}
	
	private void verificarParticipanteRegistrado(int dni) {
		Participante participante = obtenerParticipanteConDni(dni);
		
		if (participante == null)
			throw new RuntimeException("El participante no esta registrado en el sistema");
	}
	
	private Participante obtenerParticipanteConDni(int dni) {
		Participante participante = null;
		
		for (Participante p : participantes) {
			if (dni == p.getDni())
				participante = p;
		}
		
		return participante;
	}
	
	@Override
	public int registrarParticipante(int dni, String nombre, String tipoAlbum) {
		if (obtenerParticipanteConDni(dni) != null)
			throw new RuntimeException("Ya hay un participante registrado con este DNI");
		
		Participante participante = new Participante(dni, nombre, tipoAlbum);
		
		participantes.add(participante);
		
		return participante.obtenerCodigoDeAlbum();
	}

	@Override
	public void comprarFiguritas(int dni) {
		this.verificarParticipanteRegistrado(dni);
		
		Participante participante = this.obtenerParticipanteConDni(dni);
		
		List<Figurita> figuritasNuevas = Figurita.generarFiguritas(4, TipoDeFigurita.Tradicional);
		
		participante.recibirFiguritas(figuritasNuevas);
	}

	@Override
	public void comprarFiguritasTop10(int dni) {
		this.verificarParticipanteRegistrado(dni);
		
		Participante participante = this.obtenerParticipanteConDni(dni);
		
		if (participante.obtenerTipoDeAlbumComprado() != "Extendido")
			throw new RuntimeException("Debes poseer un album extendido para adquirir figuritas Top10");
		
		List<Figurita> figuritasNuevas = Figurita.generarFiguritas(4, TipoDeFigurita.Top10);
		
		participante.recibirFiguritas(figuritasNuevas);
		
	}

	@Override
	public void comprarFiguritasConCodigoPromocional(int dni) {
		this.verificarParticipanteRegistrado(dni);
		
		Participante participante = this.obtenerParticipanteConDni(dni);
		
		if (participante.obtenerTipoDeAlbumComprado() != "Web") {
			throw new RuntimeException("Debes poseer un album tradicional o web para adquirir figuritas tradicionales");
		}
				
		if (participante.haUtilizadoCodigoPromocional()) {
			throw new RuntimeException("Ya utilizaste tu codigo promocional");
		}
		
		List<Figurita> figuritasNuevas = Figurita.generarFiguritas(4, TipoDeFigurita.Tradicional);
		
		participante.recibirFiguritas(figuritasNuevas);
		participante.utilizarCodigoPromocional();		
	}

	@Override
	public List<String> pegarFiguritas(int dni) {
		this.verificarParticipanteRegistrado(dni);	
		
		Participante participante = this.obtenerParticipanteConDni(dni);
		
		participante.pegarFiguritas();
		
		return Figurita.generarMuestraDeFiguritas(participante.obtenerFiguritasPegadasDeAlbum());
	}

	@Override
	public boolean llenoAlbum(int dni) {
		this.verificarParticipanteRegistrado(dni);
		
		Participante participante = this.obtenerParticipanteConDni(dni);
		
		return participante.completoAlbum();
	}

	@Override
	public String aplicarSorteoInstantaneo(int dni) {		
		this.verificarParticipanteRegistrado(dni);	
		
		Participante participante = this.obtenerParticipanteConDni(dni);
		
		if (participante.obtenerTipoDeAlbumComprado() != "Tradicional")
			throw new RuntimeException("Debes poseer un album tradicional para participar del sorteo");
		
		if (participante.haUtilizadoCodigoPromocional()) {
			throw new RuntimeException("Ya utilizaste tu codigo promocional");
		}
		
		Random numRandomParaElParticipante = new Random();
		Random numRandomParaElSorteo = new Random();
		
		int rangoParaElParticipante = numRandomParaElParticipante.nextInt(4)+1;
		int rangoParaElSorteo = numRandomParaElParticipante.nextInt(4)+1;
		
		if (rangoParaElSorteo == rangoParaElParticipante) {
			return darPremio(participante.getDni());
		}		
		return "Segui participando!";
	}

	@Override
	public int buscarFiguritaRepetida(int dni) {
		this.verificarParticipanteRegistrado(dni);	
		
		Participante participante = this.obtenerParticipanteConDni(dni);
		
		if (participante.tieneFiguritasRepetidas()) {
			return participante.obtenerNumeroDeFiguritaRepetida(1);
		}		
		return -1;
	}

	@Override
	public boolean intercambiar(int dni, int codFigurita) {
		this.verificarParticipanteRegistrado(dni);
		
		Participante participante = this.obtenerParticipanteConDni(dni);
		
		String tipoDeAlbum = participante.obtenerTipoDeAlbumComprado();
		
		ArrayList<Participante> participantesConMismoAlbum = this.devolverParticipantesConMismoAlbum(tipoDeAlbum);
		
		Figurita figuritaAIntercambiar = participante.buscarFiguritaRepetidaMedianteCodigo(codFigurita);
		
		int valorDeFigurita = figuritaAIntercambiar.calcularValorFinal();
		
		Figurita figuritaEncontrada = null;
		Participante participanteEncontrado = null;
		int numeroDeParticipanteActual = 0;
		
		while(figuritaEncontrada.equals(null)) {
			participanteEncontrado = participantesConMismoAlbum.get(numeroDeParticipanteActual);
			figuritaEncontrada = participante.encontrarFiguritaRepetidaMenorOIgualEnValor(valorDeFigurita);
			
			numeroDeParticipanteActual++;
		}
		
		if (figuritaEncontrada.equals(null))
			return false;	
		
		// complicado, falta implementar logica de intercambio
		// repensar como hacer para intercambiar, no
		// entiendo si de una se intercambian figuritas repetidas o en este caso,
		// serian obtenidas.
		
		return true;
	}
	
	private ArrayList<Participante> devolverParticipantesConMismoAlbum(String tipoDeAlbum) {
		ArrayList<Participante> participanteConMismoAlbum = new ArrayList<Participante>();
		
		for(Participante participante: participantes) 
			if (participante.obtenerTipoDeAlbumComprado() == tipoDeAlbum)
				participanteConMismoAlbum.add(participante);
		
		return participanteConMismoAlbum;
	}

	@Override
	public boolean intercambiarUnaFiguritaRepetida(int dni) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String darNombre(int dni) {
		this.verificarParticipanteRegistrado(dni);
		
		Participante participante = this.obtenerParticipanteConDni(dni);
		
		return participante.getNombreDeUsuario();
		
	}

	@Override
	public String darPremio(int dni) {
		this.verificarParticipanteRegistrado(dni);
		
		Participante participante = this.obtenerParticipanteConDni(dni);
		
		if (!llenoAlbum(dni)){
			throw new RuntimeException("El participante no completo su album");
		}
		
		if (participante.obtenerTipoDeAlbumComprado() == "Web") {
			return "Te ganaste una camiseta de la seleccion!";
		}
		
		if (participante.obtenerTipoDeAlbumComprado() == "Tradicional") {
			return "Te ganaste una pelota!";
		}		
		return "Te ganaste una pelota y un viaje!";
	}

	@Override
	public String listadoDeGanadores() {
		String listadoDeGanadores = "";
		
		for (Participante participante: participantes)
			if (llenoAlbum(participante.getDni()))
				listadoDeGanadores += participante.toString(darPremio(participante.getDni())) + "\n";
		
		return listadoDeGanadores;
	}

	@Override
	public List<String> participantesQueCompletaronElPais(String nombrePais) {
				
		List<String> participantesQueCompletaronElPais = new ArrayList<String>();
		for (Participante participante: participantes)
			if (participante.completoPais(nombrePais))
				participantesQueCompletaronElPais.add(String.valueOf(participante.getDni()) + ", " + participante.getNombreDeUsuario() + ", " + participante.getAlbumComprado());
				
					
		
		return participantesQueCompletaronElPais;
	}
}	