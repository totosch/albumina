package albumDelMundial;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class Figurita implements Comparable {
	private int numero;

	private String nombreDeJugador;
	private String paisAnfitrion = "Qatar";
	private int valorBase;
	private String pais;
	
	enum TipoDeFigurita {
		Top10,
		Tradicional
	}
	
	public Figurita (int numeroRandom, String pais) {
		this.numero = numeroRandom;
		this.nombreDeJugador = "Jugador " + numeroRandom;
		this.pais = pais;
	}

	public int calcularValorFinal() {
		return Fabrica.solicitudAFabrica.calcularValorBase(this.pais, this.numero);
	}
	
	@Override
	public String toString() {
		return this.pais + " " + this.numero;
	}

	public static List<Figurita> generarFiguritas(int cantidadDeFiguritas, TipoDeFigurita tipo) {
		Random random = new Random();
		List<Figurita> figuritas = null;
		
		if (tipo != TipoDeFigurita.Top10)
			figuritas = Fabrica.solicitudAFabrica.generarSobre(cantidadDeFiguritas);
		else 
			figuritas = Fabrica.solicitudAFabrica.generarSobreTop10(cantidadDeFiguritas);
	
		return figuritas;
	}
	
	public static List<String> generarMuestraDeFiguritas(HashSet<Figurita> figuritas) {
		List<String> muestraDeFiguritas = new ArrayList<String>();
		
		for(Figurita figurita: figuritas)
			muestraDeFiguritas.add(figurita.toString());
		
		return muestraDeFiguritas;
	}
	
	public boolean incluidaEnListaDeFiguritas(List<Figurita> figuritas) {
		for (Figurita figurita: figuritas) {
			System.out.println(this.compareTo(figurita));
			if (this.compareTo(figurita) == 0)
				return false;			
		}
		
		return true;
	}
	
	public int getNumero() {
		return numero;
	}
	public String getPais() {
		return pais;
	}

	@Override
	public int compareTo(Object figuritaAComparar) {
		Figurita figurita = (Figurita) figuritaAComparar;
		
		if (this.numero > figurita.numero)
			return 1;
		if (this.numero < figurita.numero)
			return -1;
		
		return 0;
	}
}