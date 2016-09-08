package ru.sbt.les18_DesignPatterns.creational.builder03;

/**
 * Created by SBT-Mikhiyenko-YuA on 08.09.2016.
 */
public class Account {
    private String userId;
    private String token;

    private Account() {
        // private constructor
    }

    public String getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }

    public static Builder builder() {
        return new Account().new Builder();
    }

    public class Builder {

        private Builder() {
            // private constructor
        }

        public Builder setUserId(String userId) {
            Account.this.userId = userId;

            return this;
        }

        public Builder setToken(String token) {
            Account.this.token = token;

            return this;
        }

        public Account build() {
            return Account.this;
        }

    }

}
