package reprezentacija_i_pristup_podacima.model.korisnici;

import java.util.Date;

import reprezentacija_i_pristup_podacima.model.kredencijali.Kredencijali;

public class Administrator extends Korisnik {

	public Administrator(String ime, String prezime, Date datum_rodjenja, int id_korisnika, Kredencijali kredencijali,
			String strucna_sprema) {
		super(ime, prezime, datum_rodjenja, id_korisnika, kredencijali, strucna_sprema);
		// TODO Auto-generated constructor stub
	}

}
