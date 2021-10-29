package kg.easy.finalproject.services;

public interface SendMailService {
    public void sendMessage(String to, String subject, String text);
}
