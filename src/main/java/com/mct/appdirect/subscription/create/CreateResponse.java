package com.mct.appdirect.subscription.create;

public class CreateResponse {

    private boolean success;
    private String accountIdentifier;

    public CreateResponse() {
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setAccountIdentifier(String accountIdentifier) {
        this.accountIdentifier = accountIdentifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreateResponse response = (CreateResponse) o;

        return success == response.success
                && (accountIdentifier != null ? accountIdentifier.equals(response.accountIdentifier) : response.accountIdentifier == null);

    }

    @Override
    public int hashCode() {
        int result = (success ? 1 : 0);
        result = 31 * result + (accountIdentifier != null ? accountIdentifier.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CreateResponse{" +
                "success=" + success +
                ", accountIdentifier='" + accountIdentifier + '\'' +
                '}';
    }
}
