package br.rafael.users.api.listeners;

public interface ListenerConfig {
    
    void listenToUsersQueue(String message);
    void listenToProcedureQueue(String message);
    void listenToAppointmentQueue(String message);

}