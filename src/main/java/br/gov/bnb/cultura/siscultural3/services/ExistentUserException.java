package br.gov.bnb.cultura.siscultural3.services;

public class ExistentUserException extends Exception {
    public ExistentUserException(String message) {
        super(message);
    }
}
