package albumDelMundial;

import albumDelMundial.Fabrica.TipoDeBalon;

public class FiguritaTop10 extends Figurita {
	private TipoDeBalon balonGanado;
	
	public FiguritaTop10 (int numeroRandom, String pais, TipoDeBalon balonGanado) {
		super(numeroRandom, pais);
		
		this.balonGanado = balonGanado; 
	}
	
	public int calcularValorFinal(TipoDeBalon balonGanado) {

		if (balonGanado == TipoDeBalon.Oro)
			return (super.calcularValorFinal() * 100)/ 20;			

		return (super.calcularValorFinal() * 100)/ 10;	


	}
}
