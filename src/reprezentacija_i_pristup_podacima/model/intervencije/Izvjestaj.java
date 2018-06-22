package reprezentacija_i_pristup_podacima.model.intervencije;

import java.util.Date;

import reprezentacija_i_pristup_podacima.model.korisnici.Supervizor;

public class Izvjestaj {
	private Supervizor supervizor;
	private Date vrijeme_pravljenja;
	private Intervencija intervencija;
	private String napomena;
	private Date vrijeme_zatvaranja;
	public Izvjestaj(Supervizor supervizor, Date vrijeme_zatvaranja, Intervencija intervencija, String napomena) {
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
	public Date getVrijeme_pravljenja() {
		return vrijeme_pravljenja;
	}
	public void setVrijeme_pravljenja(Date vrijeme_pravljenja) {
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
	public Date getVrijeme_zatvaranja() {
		return vrijeme_zatvaranja;
	}
	public void setVrijeme_zatvaranja(Date vrijeme_zatvaranja) {
		this.vrijeme_zatvaranja = vrijeme_zatvaranja;
	}
	
	
	
	
}
