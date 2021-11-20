package cuentasBancarias;

public class PlazoFijo {

	private Cuenta cuenta;
	private double montoPlazoFijo;

	public PlazoFijo(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public double getMontoPlazoFijo() {
		return montoPlazoFijo;
	}

	private boolean montoValido(double monto) {
		return monto > 0.0;
	}

	public void dineroPlazoFijo(double monto) {
		if (montoValido(monto) && monto < this.cuenta.saldo) {
			try {
				this.montoPlazoFijo += monto;
				this.cuenta.retirarSaldo(monto);
			} catch (Exception e) {
				throw new SaldoInsuficienteException(
						"Tu cuenta no posee el dinero suficiente para realizar este plazo fijo. Por favor"
								+ "inserte otro monto.");
			}
		}
	}

	public void reintegroCapital() {
		this.cuenta.depositar(montoPlazoFijo + (montoPlazoFijo * 0.36));

	}

}
