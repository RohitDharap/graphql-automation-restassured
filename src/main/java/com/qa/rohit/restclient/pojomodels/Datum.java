package com.qa.rohit.restclient.pojomodels;

import java.util.Objects;

public class Datum implements Comparable<Datum> {
    public Integer id;
    public String email, first_name, last_name, avatar;

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getAvatar() {
        return avatar;
    }

    @Override
    public int compareTo(Datum datum) {
        return id.compareTo(datum.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Datum datum = (Datum) o;
        return Objects.equals(id, datum.id) && Objects.equals(email, datum.email) && Objects.equals(first_name, datum.first_name) && Objects.equals(last_name, datum.last_name) && Objects.equals(avatar, datum.avatar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
