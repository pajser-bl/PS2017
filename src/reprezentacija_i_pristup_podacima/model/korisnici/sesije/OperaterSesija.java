package reprezentacija_i_pristup_podacima.model.korisnici.sesije;

import java.util.Date;

import reprezentacija_i_pristup_podacima.model.korisnici.Operater;

public class OperaterSesija {
	private Operater operater;
	private String tekst;
	private Date pocetak_sesije;
	private Date kraj_sesije;

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
