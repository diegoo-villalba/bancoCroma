package cuentasBancarias;

public class TarjetaDeDebito extends Tarjeta {

	public TarjetaDeDebito(Cuenta cuenta) {
		super(cuenta);
	}

	public double consultarSaldo() {
		return this.cuenta.obtenerSaldo();
	}

	public void retirar(double monto) {
		this.cuenta.retirarSaldo(monto);
		System.out.println(cuenta.getTransaccionesDebito());
	}

	public void depositar(double monto) {
		this.cuenta.depositar(monto);
		System.out.println(cuenta.getTransaccionesDeposito());
	}

}
