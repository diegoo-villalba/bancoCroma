package cuentasBancariasTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import cuentasBancarias.Cuenta;
import cuentasBancarias.PlazoFijo;
import cuentasBancarias.Tarjeta;
import cuentasBancarias.TarjetaDeCredito;
import cuentasBancarias.TarjetaDeDebito;

public class TarjetasTests {

	@Test
	public void tarjetaTest() {
		Cuenta cuenta = new Cuenta(373250, 1000.0);
		Tarjeta miTarjeta = new Tarjeta(cuenta);
		assertNotNull(cuenta);
		assertNotNull(miTarjeta);
	}

	@Test
	public void tarjetaDebitoTest() {
		Cuenta cuenta = new Cuenta(373250, 1000.00);
		TarjetaDeDebito tarjeta = new TarjetaDeDebito(cuenta);
		tarjeta.depositar(500.00);
		assertEquals(1500.00, tarjeta.consultarSaldo(), 0);
		tarjeta.retirar(750.0);
		assertEquals(750.0, tarjeta.consultarSaldo(), 0);
		// System.out.println(cuenta.getTransacciones());

	}

	@Test
	public void tarjetaCreditoTest() {
		Cuenta cuenta = new Cuenta(373250, 1000.00);
		TarjetaDeCredito tarjeta = new TarjetaDeCredito(cuenta, 2000);
		tarjeta.tarjetear(1500);
		assertEquals(500, tarjeta.consultarCupo(), 0.0);
	}

	@Test
	public void cobrarTarjetaCreditoTest() {
		Cuenta cuenta = new Cuenta(373250, 2000.00);
		TarjetaDeCredito tarjeta = new TarjetaDeCredito(cuenta, 2000); // Tarjeta con $2000 de cupo
		tarjeta.tarjetear(1000); // Se descuentan $1000 del cupo
		assertEquals(1000, tarjeta.consultarCupo(), 0.0);
		tarjeta.debitar(); // Se tienen que debitar $1030 del saldo de la cuenta asociada a la tarjeta de
							// credito.
		assertEquals(970, cuenta.obtenerSaldo(), 0.0);
	}

	@Test
	public void plazoFijoTest() {
		Cuenta cuenta = new Cuenta(373250, 25000.00);
		PlazoFijo plazo = new PlazoFijo(cuenta);

		plazo.dineroPlazoFijo(20000.00); // Quito $20000 disponibles de mi cuenta para meterlos en un plazo fijo
		assertEquals(5000, cuenta.obtenerSaldo(), 0.0);
		assertEquals(20000.00, plazo.getMontoPlazoFijo(), 0.0); // Confirmo que ingresaron $20000.00 al plazo fijo
		plazo.reintegroCapital();
		assertEquals(32200, cuenta.obtenerSaldo(), 0.0); // Reintegro capital al saldo de la cuenta + intereses 36%
															// anual
	}

}
