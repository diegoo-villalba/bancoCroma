package cuentasBancarias;

import java.util.Objects;
import java.util.Date;

public class Transaccion {

	private double monto;
	private TipoDeTransaccion tipoDeTransaccion;
	// private Fecha fecha;
	private Date fecha;

	public Transaccion(double monto, TipoDeTransaccion tipoDeTransaccion, Date fecha) {
		this.monto = monto;
		this.tipoDeTransaccion = tipoDeTransaccion;
		this.fecha = fecha;
	}

	@Override
	public int hashCode() {
		return Objects.hash(monto, tipoDeTransaccion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaccion other = (Transaccion) obj;
		return Double.doubleToLongBits(monto) == Double.doubleToLongBits(other.monto)
				&& tipoDeTransaccion == other.tipoDeTransaccion;
	}

	@Override
	public String toString() {
		return "Transaccion [monto=" + monto + ", tipoDeTransaccion=" + tipoDeTransaccion + ", fecha=" + fecha + "]";
	}

}
