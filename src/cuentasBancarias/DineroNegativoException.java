package cuentasBancarias;

@SuppressWarnings("serial")
public class DineroNegativoException extends RuntimeException {

	public DineroNegativoException(String string) {
		super(string);
	}
}
