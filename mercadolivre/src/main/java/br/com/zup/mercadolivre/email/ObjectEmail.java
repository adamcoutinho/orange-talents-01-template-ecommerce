package br.com.zup.mercadolivre.email;

public class ObjectEmail {
    private String from;
    private String to;
    private String subject;
    private String composeEmail;

    public ObjectEmail(String from, String to, String subject, String composeEmail) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.composeEmail = composeEmail;
    }

    @Override
    public String toString() {
        return "ObjectEmail{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", composeEmail='" + composeEmail + '\'' +
                '}';
    }
}
