package domain;

public class BDexception extends Exception{

	
	private static final long serialVersionUID = 1L;
	
	public BDexception(String mensaje, Throwable e) {
		super(mensaje,e);
	}
	
	public BDexception(String mensaje) {
		super(mensaje);
	}

}
