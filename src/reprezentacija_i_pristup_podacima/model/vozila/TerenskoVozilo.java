package reprezentacija_i_pristup_podacima.model.vozilo;

import java.time.LocalDateTime;

public class TerenskoVozilo extends Vozilo {

	public TerenskoVozilo() {
		super();
	}
	
	public TerenskoVozilo(String broj_registarskih_tablica, String marka_vozila, 
					LocalDateTime poslednji_tehnicki_pregled) {
		super(broj_registarskih_tablica, marka_vozila, poslednji_tehnicki_pregled);
	}
}
	