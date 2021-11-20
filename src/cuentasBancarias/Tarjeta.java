package cuentasBancarias;

import java.util.Date;
import java.util.List;

public class Tarjeta {
	protected Cuenta cuenta;
	protected List<Transaccion> transacciones;
	protected Transaccion transaccion;
	protected Date fecha = new Date();

	public Tarjeta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public List<Transaccion> getTransacciones() {
		return transacciones;
	}

}
