package cuentasBancariasTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import cuentasBancarias.Cuenta;
import cuentasBancarias.CuentaCorriente;
import cuentasBancarias.CuentaDeAhorros;
import cuentasBancarias.DineroNegativoException;
import cuentasBancarias.SaldoInsuficienteException;
import cuentasBancarias.Transaccion;

public class CuentasBancariasTests {

	@Test
	public void cuentasBancariasTest() {
		Cuenta miCuenta = new Cuenta(373250, 0.00);
		assertNotNull(miCuenta);
	}

	@Test
	public void obtenerSaldoTest() {
		Cuenta miCuenta = new Cuenta(373250, 0.00);
		assertEquals(0.00, miCuenta.obtenerSaldo(), 0);
	}

	@Test
	public void cargarSaldoTest() throws DineroNegativoException {
		Cuenta miCuenta = new Cuenta(373250, 0.00);
		miCuenta.depositar(1000.0);
		assertEquals(1000.0, miCuenta.obtenerSaldo(), 0);
	}

	@Test
	public void retirarSaldoTest() throws SaldoInsuficienteException, DineroNegativoException {
		Cuenta miCuenta = new Cuenta(373250, 0.00);
		miCuenta.depositar(1000.0);
		miCuenta.retirarSaldo(550.0);
		assertEquals(450.0, miCuenta.obtenerSaldo(), 0);

	}

	/*
	 * @Test public void cargarSaldoNegativoTest() throws DineroNegativoException {
	 * Cuenta miCuenta = new Cuenta(0.00); miCuenta.cargarSaldo(-1000.0); }
	 * 
	 * @Test public void retirarDineroDeMasTest() throws SaldoInsuficienteException,
	 * DineroNegativoException { Cuenta miCuenta = new Cuenta(0.00);
	 * miCuenta.cargarSaldo(1000.0); miCuenta.retirarSaldo(1150.0); }
	 */

	@Test
	public void puedeTransferirTest() throws DineroNegativoException, SaldoInsuficienteException {
		Cuenta cuentaOrigen = new Cuenta(373250, 0.00);
		Cuenta cuentaDestino = new Cuenta(3832550, 0.00);

		cuentaOrigen.depositar(1000.0);
		assertEquals(1000.0, cuentaOrigen.obtenerSaldo(), 0);

		cuentaOrigen.transferir(550.0, cuentaDestino);
		assertEquals(450.0, cuentaOrigen.obtenerSaldo(), 0);
		assertEquals(550.0, cuentaDestino.obtenerSaldo(), 0);

	}

	/*
	 * @Test public void noPuedeTransferirTest() throws DineroNegativoException,
	 * SaldoInsuficienteException { Cuenta cuentaOrigen = new Cuenta(0.00); Cuenta
	 * cuentaDestino = new Cuenta(0.00);
	 * 
	 * cuentaOrigen.depositar(500.0); assertEquals(500.0,
	 * cuentaOrigen.obtenerSaldo(), 0);
	 * 
	 * cuentaOrigen.transferir(550.0, cuentaDestino); assertEquals(550.0,
	 * cuentaDestino.obtenerSaldo(), 0);
	 * 
	 * }
	 * 
	 * @Test public void TransferirSaldoNegativoTest() throws
	 * DineroNegativoException, SaldoInsuficienteException { Cuenta cuentaOrigen =
	 * new Cuenta(0.00); Cuenta cuentaDestino = new Cuenta(0.00);
	 * 
	 * cuentaOrigen.depositar(500.0); assertEquals(500.0,
	 * cuentaOrigen.obtenerSaldo(), 0);
	 * 
	 * cuentaOrigen.transferir(-500.0, cuentaDestino); assertEquals(550.0,
	 * cuentaDestino.obtenerSaldo(), 0);
	 * 
	 * }
	 */

	@Test
	public void cuentaDeAhorroTest() throws DineroNegativoException {
		CuentaDeAhorros cuentaAhorro = new CuentaDeAhorros(373250, 0.00);

		cuentaAhorro.depositar(10000);
		assertEquals(10000, cuentaAhorro.obtenerSaldo(), 0);
		assertEquals(0.00, cuentaAhorro.getSaldoSecundario(), 0);
	}

	@Test
	public void transferirAhorroTest() throws DineroNegativoException {
		CuentaDeAhorros cuentaAhorro = new CuentaDeAhorros(373250, 0.00);

		cuentaAhorro.depositar(10000);
		assertEquals(10000, cuentaAhorro.obtenerSaldo(), 0);
		cuentaAhorro.reservarAhorro(5000.0);
		assertEquals(5000.0, cuentaAhorro.getSaldoSecundario(), 0);
		assertEquals(5000.0, cuentaAhorro.obtenerSaldo(), 0);
	}

	@Test
	public void reincorporarDineroTest() throws DineroNegativoException {
		CuentaDeAhorros cuentaAhorro = new CuentaDeAhorros(373250, 1000.00);

		cuentaAhorro.reservarAhorro(500.0);

		assertEquals(500, cuentaAhorro.obtenerSaldo(), 0);
		cuentaAhorro.reintegrar();
		assertEquals(1000.0, cuentaAhorro.obtenerSaldo(), 0);
		assertEquals(0.00, cuentaAhorro.getSaldoSecundario(), 0);

	}

	@Test
	public void retirarDineroTest() {
		CuentaCorriente cuentaCorriente = new CuentaCorriente(373250, 10000.0, 2000.0);
		cuentaCorriente.retirar(10000.0);
		assertEquals(2000.0, cuentaCorriente.obtenerSaldo(), 0);
		cuentaCorriente.retirar(2000.00);
		assertEquals(0.00, cuentaCorriente.obtenerSaldo(), 0);

	}

	@Test
	public void generaRegistroTest() {
		Cuenta cuentaOrigen = new Cuenta(373250, 10000.00);
		Cuenta cuentaDestino = new Cuenta(3832550, 0.00);

		cuentaOrigen.transferir(5000.0, cuentaDestino);
		cuentaOrigen.depositar(2500.0);
		cuentaOrigen.retirarSaldo(2000.0);

		List<Transaccion> debitos = new LinkedList<Transaccion>();
		List<Transaccion> depositos = new LinkedList<Transaccion>();
		List<Transaccion> transferencias = new LinkedList<Transaccion>();

		System.out.println(cuentaOrigen.getTransaccionesDebito());
		System.out.println(cuentaOrigen.getTransaccionesDeposito());
		System.out.println(cuentaOrigen.getTransaccionesTransferencias());

	}

}
