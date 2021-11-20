package cuentasBancarias;

@SuppressWarnings("serial")
public class SaldoInsuficienteException extends RuntimeException {
	
	public SaldoInsuficienteException(String string) {
		super(string);
	}

}
