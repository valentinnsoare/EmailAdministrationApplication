package io.emailadministration.entities.digitalcomponents;

public class UserBuilder {

    private User user;

    public UserBuilder() {
        this.user = new User();
    }

    public UserBuilder setupUserName(String userName) {
        user.setUserName(userName);
        return this;
    }

    public PasswordBuilder asPassword () {
        return new PasswordBuilder(user);
    }

    public EmailBuilder asEmail() {
        return new EmailBuilder(user);
    }

    public User build() {
        return user;
    }
}