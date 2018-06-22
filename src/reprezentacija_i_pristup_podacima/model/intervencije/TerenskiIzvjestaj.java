package reprezentacija_i_pristup_podacima.model.intervencije;

import java.util.Date;

import reprezentacija_i_pristup_podacima.model.korisnici.TerenskiRadnik;

public class TerenskiIzvjestaj {
	private Date vrijeme_slanja;
	private TerenskiRadnik terenski_radnik;
	private Date vrijeme_prijema;
	private Intervencija intervencija;
	private VrstaUsluge vrsta_usluge;
	private String opis;
	

	public TerenskiIzvjestaj(TerenskiRadnik terenski_radnik, Date vrijeme_prijema, Intervencija intervencija) {
		super();
		this.terenski_radnik = terenski_radnik;
		this.vrijeme_prijema = vrijeme_prijema;
		this.intervencija = intervencija;
	}

	public Date getVrijeme_slanja() {
		return vrijeme_slanja;
	}

	public void setVrijeme_slanja(Date vrijeme_slanja) {
		this.vrijeme_slanja = vrijeme_slanja;
	}

	public TerenskiRadnik getTerenski_radnik() {
		return terenski_radnik;
	}

	public void setTerenski_radnik(TerenskiRadnik terenski_radnik) {
		this.terenski_radnik = terenski_radnik;
	}

	public Date getVrijeme_prijema() {
		return vrijeme_prijema;
	}

	public void setVrijeme_prijema(Date vrijeme_prijema) {
		this.vrijeme_prijema = vrijeme_prijema;
	}

	public String getVrsta_usluge() {
		return vrsta_usluge.toString();
	}

	public void setVrsta_usluge(String vrsta_usluge) {
		switch (vrsta_usluge) {
		case "POPRAVKA_NA_LICU_MJESTA": {
			this.vrsta_usluge = VrstaUsluge.POPRAVKA_NA_LICU_MJESTA;
		}
		case "SLEPANJE": {
			this.vrsta_usluge = VrstaUsluge.SLEPANJE;
		}
		case "NEDEFINISANO": {
			this.vrsta_usluge = VrstaUsluge.OSTALO;
		}
		}
		this.vrsta_usluge.toString();
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Intervencija getIntervencija() {
		return intervencija;
	}

	public void setIntervencija(Intervencija intervencija) {
		this.intervencija = intervencija;
	}


}
