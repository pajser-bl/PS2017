package reprezentacija_i_pristup_podacima.model.korisnici.sesije;

import java.time.LocalDateTime;
import reprezentacija_i_pristup_podacima.model.korisnici.TerenskiRadnik;

public class TerenskiRadnikSesija {
	private TerenskiRadnik terenski_radnik;
	private String tekst;
	private LocalDateTime pocetak_sesije;
	private LocalDateTime kraj_sesije;

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

	public LocalDateTime getPocetak_sesije() {
		return pocetak_sesije;
	}

	public void setPocetak_sesije(LocalDateTime pocetak_sesije) {
		this.pocetak_sesije = pocetak_sesije;
	}

	public LocalDateTime getKraj_sesije() {
		return kraj_sesije;
	}

	public void setKraj_sesije(LocalDateTime kraj_sesije) {
		this.kraj_sesije = kraj_sesije;
	}

}
