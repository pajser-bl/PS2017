package aplikativna_logika.controller.rad_sa_korisnicima.rad_sa_stanjima_radnika;

import reprezentacija_i_pristup_podacima.model.korisnici.Korisnik;
import reprezentacija_i_pristup_podacima.model.korisnici.Stanje;
import reprezentacija_i_pristup_podacima.model.korisnici.TerenskiRadnik;

public class RadSaStanjima {
	
	public static boolean postaviStanjeTerenskogRadnika(TerenskiRadnik terenski_radnik,Stanje stanje) {
		return true;
	}
	public static Stanje getStanjeTerenskogRadnika(TerenskiRadnik terenski_radnik) {
		return terenski_radnik.getStanje();
	}
	public static Korisnik[] getListaAktrivnihKorisnika() {
		return null;
	}
}