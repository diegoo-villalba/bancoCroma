package cuentasBancarias;

public class TarjetaDeCredito extends Tarjeta {

	private double cupoTarjeta;
	private double unMonto;

	public TarjetaDeCredito(Cuenta cuenta, double cupo) {
		super(cuenta);
		this.cupoTarjeta = cupo;

	}

	public double consultarCupo() {
		return this.cupoTarjeta;
	}

	private boolean montoValido(double monto) {
		return monto > 0.0;
	}

	public void tarjetear(double monto) {
		if (montoValido(monto)) {
			this.unMonto += monto;
			this.cupoTarjeta -= monto;
		}
	}

	public void debitar() {
		this.cuenta.retirarSaldo(this.unMonto + (this.unMonto * 0.03));
		System.out.println(cuenta.getTransaccionesDebito());
	}

}