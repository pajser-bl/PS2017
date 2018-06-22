package reprezentacija_i_pristup_podacima.model.korisnici;

import java.time.LocalDateTime;
import reprezentacija_i_pristup_podacima.model.kredencijali.Kredencijali;

public class TerenskiRadnik extends Korisnik {
	public Stanje stanje;

	public TerenskiRadnik() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TerenskiRadnik(String ime, String prezime, LocalDateTime datum_rodjenja, int id_korisnika,
			Kredencijali kredencijali, String strucna_sprema) {
		super(ime, prezime, datum_rodjenja, id_korisnika, kredencijali, strucna_sprema);
		// TODO Auto-generated constructor stub
	}

	public Stanje getStanje() {
		return stanje;
	}

	public void setStanje(Stanje stanje) {
		this.stanje = stanje;
	}
}
