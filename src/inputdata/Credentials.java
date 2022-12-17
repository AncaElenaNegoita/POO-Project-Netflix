package inputdata;

import java.util.Objects;

public final class Credentials {
    private String name;
    private String password;
    private String accountType;
    private String country;
    private String balance;

    public Credentials() {

    }

    public Credentials(final Credentials credentials) {
        if (credentials != null) {
            this.name = credentials.getName();
            this.password = credentials.getPassword();
            this.accountType = credentials.getAccountType();
            this.country = credentials.getCountry();
            this.balance = credentials.getBalance();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(final String accountType) {
        this.accountType = accountType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(final String balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Credentials that = (Credentials) o;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getPassword(),
                that.getPassword());
    }
}
