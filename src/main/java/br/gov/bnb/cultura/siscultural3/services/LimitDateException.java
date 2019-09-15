package br.gov.bnb.cultura.siscultural3.services;

public class LimitDateException extends Exception {

	public LimitDateException() {
		super("Não foi realizada a operação solicitada porque a data limite da chamada foi ultrapassada.");
	}
}
