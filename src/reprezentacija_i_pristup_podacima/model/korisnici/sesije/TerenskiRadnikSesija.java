package reprezentacija_i_pristup_podacima.model.korisnici.sesije;

import java.util.Date;

import reprezentacija_i_pristup_podacima.model.korisnici.TerenskiRadnik;

public class TerenskiRadnikSesija {
	private TerenskiRadnik terenski_radnik;
	private String tekst;
	private Date pocetak_sesije;
	private Date kraj_sesije;

	public TerenskiRadnikSesija(TerenskiRadnik terenski_radnik) {
		super();
		this.terenski_radnik = terenski_radnik;
	}

	public TerenskiRadnik getTerenski_radnik() {
		return terenski_radnik;
	}

	public void setTerenski_radnik(TerenskiRadnik terenski_radnik) {
		this.terenski_radnik = terenski_radnik;
	}

	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public Date getPocetak_sesije() {
		return pocetak_sesije;
	}

	public void setPocetak_sesije(Date pocetak_sesije) {
		this.pocetak_sesije = pocetak_sesije;
	}

	public Date getKraj_sesije() {
		return kraj_sesije;
	}

	public void setKraj_sesije(Date kraj_sesije) {
		this.kraj_sesije = kraj_sesije;
	}

}
