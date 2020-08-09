package br.com.henrique.curso.enums;

public enum TipoCliente {
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private int cod;
	private String descriçao;
	
	private TipoCliente(int cod, String descriçao) {
		this.cod = cod;
		this.descriçao = descriçao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescriçao() {
		return descriçao;
	}
	
	public static TipoCliente toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		for(TipoCliente x : TipoCliente.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id Inválido" + cod);
	}
}
