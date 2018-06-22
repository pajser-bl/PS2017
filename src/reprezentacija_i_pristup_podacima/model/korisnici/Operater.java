package reprezentacija_i_pristup_podacima.model.korisnici;

import java.time.LocalDateTime;
import reprezentacija_i_pristup_podacima.model.kredencijali.Kredencijali;

public class Operater extends Korisnik {

	public Operater() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Operater(String ime, String prezime, LocalDateTime datum_rodjenja, int id_korisnika,
			Kredencijali kredencijali, String strucna_sprema) {
		super(ime, prezime, datum_rodjenja, id_korisnika, kredencijali, strucna_sprema);
		// TODO Auto-generated constructor stub
	}


}
