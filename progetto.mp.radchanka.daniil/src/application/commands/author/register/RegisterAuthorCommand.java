package application.commands.author.register;

import base.mediator.request.Request;
import base.result.ErrorOr;
import domain.author.AuthorId;

public class RegisterAuthorCommand implements Request<ErrorOr<AuthorId>> {
    private final String country;
    private final String name;
    private final String surname;
    
    public RegisterAuthorCommand(String name, String surname, String country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }
    
    public String getCountry() {
        return country;
    }
    
    public String getName() {
        return name;
    }
    
    public String getSurname() {
        return surname;
    }
}
