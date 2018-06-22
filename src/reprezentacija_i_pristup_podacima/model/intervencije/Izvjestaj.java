package reprezentacija_i_pristup_podacima.model.intervencije;

import java.time.LocalDateTime;
import reprezentacija_i_pristup_podacima.model.korisnici.Supervizor;

public class Izvjestaj {
	private Supervizor supervizor;
	private LocalDateTime vrijeme_pravljenja;
	private Intervencija intervencija;
	private String napomena;
	private LocalDateTime vrijeme_zatvaranja;
	public Izvjestaj(Supervizor supervizor, LocalDateTime vrijeme_zatvaranja, Intervencija intervencija, String napomena) {
		super();
		this.supervizor = supervizor;
		this.vrijeme_zatvaranja = vrijeme_zatvaranja;
		this.intervencija = intervencija;
		this.napomena = napomena;
	}
	public Supervizor getSupervizor() {
		return supervizor;
	}
	public void setSupervizor(Supervizor supervizor) {
		this.supervizor = supervizor;
	}
	public LocalDateTime getVrijeme_pravljenja() {
		return vrijeme_pravljenja;
	}
	public void setVrijeme_pravljenja(LocalDateTime vrijeme_pravljenja) {
		this.vrijeme_pravljenja = vrijeme_pravljenja;
	}
	public Intervencija getIntervencija() {
		return intervencija;
	}
	public void setIntervencija(Intervencija intervencija) {
		this.intervencija = intervencija;
	}
	public String getNapomena() {
		return napomena;
	}
	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}
	public LocalDateTime getVrijeme_zatvaranja() {
		return vrijeme_zatvaranja;
	}
	public void setVrijeme_zatvaranja(LocalDateTime vrijeme_zatvaranja) {
		this.vrijeme_zatvaranja = vrijeme_zatvaranja;
	}
	
	
	
	
}
