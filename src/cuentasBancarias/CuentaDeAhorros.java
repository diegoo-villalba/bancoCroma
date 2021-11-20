package cuentasBancarias;

public class CuentaDeAhorros extends Cuenta {

	private double saldoSecundario;

	public CuentaDeAhorros(int numeroCuenta, double saldo) {
		super(numeroCuenta, saldo);
	}

	public void reintegrar() {
		this.saldo += saldoSecundario;
		this.saldoSecundario = 0.00;
	}

	public void reservarAhorro(double monto) {
		this.saldoSecundario += monto;
		this.saldo -= saldoSecundario;
	}

	public double getSaldoSecundario() {
		return saldoSecundario;
	}
	
}
