package cuentasBancarias;

import java.util.LinkedList;
import java.util.Date;
import java.util.List;

public class Cuenta {

	private Transaccion transaccion;
	private Date fecha = new Date();
	protected double saldo;
	protected int numeroCuenta;

	List<Transaccion> transaccionesDebito = new LinkedList<Transaccion>();
	List<Transaccion> transaccionesDeposito = new LinkedList<Transaccion>();
	List<Transaccion> transaccionesTransferencias = new LinkedList<Transaccion>();

	public Cuenta(int numeroCuenta, double saldo) {
		this.saldo = saldo;
		this.numeroCuenta = numeroCuenta;
	}

	public double obtenerSaldo() {
		return this.saldo;
	}

	public void depositar(double monto) {
		if (this.montoValido(monto)) {
			try {

				this.saldo += monto;
				transaccion = new Transaccion(monto, TipoDeTransaccion.DEPOSITO, fecha);
				transaccionesDeposito.add(transaccion);

			} catch (Exception e) {
				throw new DineroNegativoException("Monto ingresado incorrecto");
			}
		}
	}

	public void retirarSaldo(double monto) {
		if (this.puedeRetirar(monto) && this.montoValido(monto)) {
			try {

				this.saldo -= monto;
				transaccion = new Transaccion(monto, TipoDeTransaccion.DEBITO, fecha);
				transaccionesDebito.add(transaccion);

			} catch (Exception e) {
				throw new SaldoInsuficienteException("Saldo insuficiente");
			}
		}

	}

	private boolean puedeRetirar(double monto) {
		return this.saldo >= monto;
	}

	private boolean montoValido(double monto) {
		return monto > 0.0;
	}

	public void transferir(double monto, Cuenta cuentaDestino) {
		if (this.puedeTransferir(monto) && this.montoValido(monto)) {
			try {

				this.saldo -= monto;
				cuentaDestino.saldo += monto;
				transaccion = new Transaccion(monto, TipoDeTransaccion.TRANSFERENCIA, fecha);
				transaccionesTransferencias.add(transaccion);

			} catch (Exception e) {
				throw new SaldoInsuficienteException("Saldo insuficiente");
			}
		}

	}

	private boolean puedeTransferir(double monto) {
		return this.saldo >= monto;
	}

	public List<Transaccion> getTransaccionesDebito() {
		return transaccionesDebito;
	}

	public List<Transaccion> getTransaccionesDeposito() {
		return transaccionesDeposito;
	}

	// TODO: Agregar cuenta destino al registro de la transaccion
	public List<Transaccion> getTransaccionesTransferencias() {
		return transaccionesTransferencias;
	}

}
