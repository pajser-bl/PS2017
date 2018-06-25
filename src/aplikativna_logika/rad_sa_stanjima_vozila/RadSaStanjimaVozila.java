package aplikativna_logika.controller.rad_sa_vozilima.rad_sa_stanjima_vozila;

import reprezentacija_i_pristup_podacima.model.vozila.Vozilo;
import reprezentacija_i_pristup_podacima.model.vozila.Stanje;
import reprezentacija_i_pristup_podacima.model.vozila.TerenskoVozilo;

public class RadSaStanjima {
	
	public static boolean postaviStanjaTerenskogVozila(TerenskoVozilo terensko_vozilo,Stanje stanje) {
		return true;
	}
	public static Stanje getStanjeTerenskogVozila(TerenskoVozilo terensko_vozilo) {
		return terensko_vozilo.getStanje();
	}
	public static Vozilo[] getListaAktrivnihVozila() {
		return null;
	}
}