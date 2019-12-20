package api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class OfferDetails {

    @JsonProperty
    private Integer id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String description;
    @JsonProperty
    private String terms;
    @JsonProperty
    private String imageUrl;
    @JsonProperty
    private String expiration;

    public OfferDetails(Integer id, String name, String description, String terms, String imageUrl, String expiration) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.terms = terms;
        this.imageUrl = imageUrl;
        this.expiration = expiration;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getTerms() {
        return terms;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getExpiration() {
        return expiration;
    }

    @Override
    public String toString() {
        return "OfferDetails{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", terms='" + terms + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", expiration='" + expiration + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfferDetails that = (OfferDetails) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(terms, that.terms) &&
                Objects.equals(imageUrl, that.imageUrl) &&
                Objects.equals(expiration, that.expiration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, terms, imageUrl, expiration);
    }
}
