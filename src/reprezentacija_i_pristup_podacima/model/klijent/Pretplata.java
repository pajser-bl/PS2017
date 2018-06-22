package reprezentacija_i_pristup_podacima.model.klijent;

import java.time.LocalDateTime;

public class Pretplata {
	private int id_pretplate;
	private LocalDateTime od;
	private LocalDateTime _do;
	private int id_klijenta;
	public Pretplata() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Pretplata(int id_pretplate, LocalDateTime od, LocalDateTime _do, int id_klijenta) {
		super();
		this.id_pretplate = id_pretplate;
		this.od = od;
		this._do = _do;
		this.id_klijenta = id_klijenta;
	}
	public int getId_pretplate() {
		return id_pretplate;
	}
	public void setId_pretplate(int id_pretplate) {
		this.id_pretplate = id_pretplate;
	}
	public LocalDateTime getOd() {
		return od;
	}
	public void setOd(LocalDateTime od) {
		this.od = od;
	}
	public LocalDateTime get_do() {
		return _do;
	}
	public void set_do(LocalDateTime _do) {
		this._do = _do;
	}
	public int getId_klijenta() {
		return id_klijenta;
	}
	public void setId_klijenta(int id_klijenta) {
		this.id_klijenta = id_klijenta;
	}
	
}
