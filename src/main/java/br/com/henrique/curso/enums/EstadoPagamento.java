package br.com.henrique.curso.enums;

public enum EstadoPagamento {
	
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");

	private int cod;
	private String descriçao;
	
	private EstadoPagamento(int cod, String descriçao) {
		this.cod = cod;
		this.descriçao = descriçao;
	}
	
	public int getCod() {
		return cod;
	}

	public String getDescriçao() {
		return descriçao;
	}
	
	public static EstadoPagamento toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		for(EstadoPagamento x : EstadoPagamento.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id Inválido" + cod);
	}
}
