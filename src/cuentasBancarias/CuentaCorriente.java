package cuentasBancarias;

public class CuentaCorriente extends Cuenta {

	private double limiteDescubierto;

	public CuentaCorriente(int numeroCuenta, double saldo, double descubierto) {
		super(numeroCuenta, saldo);
		this.limiteDescubierto = descubierto;
	}

	public double getLimiteDescubierto() {
		return limiteDescubierto;
	}

	public void retirar(double monto) {
		if (this.saldo < 0) {
			limiteDescubierto -= monto;
		} else
			this.saldo -= monto;
	}

	@Override
	public double obtenerSaldo() {
		return this.saldo + this.limiteDescubierto;
	}
	
	

}
