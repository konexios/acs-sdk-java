package com.arrow.acs.client.model;

import java.io.Serializable;

public abstract class StatusModelAbstract<T extends StatusModelAbstract<T>> implements Serializable {
    private static final long serialVersionUID = -588836765825242950L;

    private String status;
    private String message;

    protected abstract T self();

    public T withOkStatus() {
        setStatus("OK");
        return self();
    }

    public T withErrorStatus(String message) {
        setStatus("ERROR");
        setMessage(message);
        return self();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StatusModelAbstract<?> other = (StatusModelAbstract<?>) obj;
        if (message == null) {
            if (other.message != null)
                return false;
        } else if (!message.equals(other.message))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        return true;
    }
}
