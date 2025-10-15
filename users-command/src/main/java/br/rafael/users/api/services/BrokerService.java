package br.rafael.users.api.services;

public interface BrokerService {
    
    public void send(String type, String exchange, Object data);

}
