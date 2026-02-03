public class User {

    private User(UserBuilder builder){
        id = builder.id;
        name = builder.name;
        surname = builder.surname;
        balance = builder.balance;
        login = builder.login;
        password = builder.password;
        role = builder.role;
    }

    public static class UserBuilder{
        private int id;
        private String name;
        private String surname;
        private double balance;
        private String login;
        private String password;
        private int role;

        public UserBuilder(String name, String surname) {
            this.name=name;
            this.surname=surname;
        }

        public UserBuilder(int id, String name, String surname){
            this(name, surname);
            this.id=id;
        }

        public UserBuilder WithRole(int role){
            this.role=role;
            return this;
        }
        public UserBuilder WithBalance(double balance){
            this.balance=balance;
            return this;
        }
        public UserBuilder WithLogin(String login){
            this.login=login;
            return this;
        }
        public UserBuilder WithPassword(String password){
            this.password=password;
            return this;
        }
        public User build(){
            return new User(this);
        }
    }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder role(String role) {
            this.role = role;
            return this;
        }

        public Builder active(boolean active) {
            this.active = active;
            return this;
        }

    @Override
    public String toString(){
        return "User{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", balance=" + getBalance() + '\'' +
                ", login=" + getLogin() + '\'' +
                ", password=" + getPassword() + '\'' +
                ", role=" + getRole() + '\'' +
                '}';
    }
}
