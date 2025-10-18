package jaas;

import java.security.Principal;
import java.util.Objects;

/**
 * A principal with a named value (such as "role=HR" or "username=harry").
 */
public class SimplePrincipal implements Principal {
    private String descr;
    private String value;

    /**
     * Constructs a SimplePrincipal to hold a description and a value.
     * @param descr the description
     * @param value the associated value
     */
    public SimplePrincipal(String descr, String value) {
        this.descr = descr;
        this.value = value;
    }

    public SimplePrincipal(String descrAndValue) {
        String[] dv = descrAndValue.split("=");
        this.descr = dv[0];
        this.value = dv[1];
    }

    /**
     * @return the description and value of this simple principal.
     */
    @Override
    public String getName() {
        return descr + "=" + value;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) return true;
        if (otherObject == null) return false;
        if (getClass() != otherObject.getClass()) return false;
        var other = (SimplePrincipal) otherObject;
        return Objects.equals(getName(), other.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }
}
