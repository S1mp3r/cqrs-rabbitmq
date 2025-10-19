package br.rafael.users.api.listeners;

public interface ListenerConfig {
    
    void listenToUsersQueue(String message);
    void listenToUsersQueueDeleted(String message);
    void listenToProcedureQueue(String message);
    void listenToProcedureQueueDeleted(String message);
    void listenToAppointmentQueue(String message);
    void listenToAppointmentQueueDeleted(String message);
}