package reprezentacija_i_pristup_podacima.model.korisnici.sesije;

import java.time.LocalDateTime;
import reprezentacija_i_pristup_podacima.model.korisnici.Operater;

public class OperaterSesija {
	private Operater operater;
	private String tekst;
	private LocalDateTime pocetak_sesije;
	private LocalDateTime kraj_sesije;

	public OperaterSesija(Operater operater) {
		super();
		this.setOperater(operater);
	}

	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public Operater getOperater() {
		return operater;
	}

	public void setOperater(Operater operater) {
		this.operater = operater;
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
