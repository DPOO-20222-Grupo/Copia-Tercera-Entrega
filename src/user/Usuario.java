package user;

public abstract class Usuario {
    private String login;
    private String password;
    private boolean isLoggedIn;

    public Usuario(String login, String password) {
        this.login = login;
        this.password = password;
        this.isLoggedIn = false;
    }
    
    // Getters y Setters
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    // Métodos funcionales del programa
    
    public boolean login(String login, String contraseña) {
        if (this.login.equals(login) && this.password.equals(contraseña)) {
            this.isLoggedIn = true;
            return true;
        }
        return false;
    }
    
    public void logout() {
        if (this.isLoggedIn) {
            this.isLoggedIn = false; 
            System.out.println("Sesión cerrada exitosamente.");
        } else {
            System.out.println("No hay una sesión activa.");
        }
    }
    
    public boolean isLoggedIn() {
        return this.isLoggedIn;
    }
    
    public abstract String getTipo();
}

