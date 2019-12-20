package api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class OfferInfo {

    @JsonProperty
    private Integer id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String imageUrl;
    @JsonProperty
    private String retailerName;

    public OfferInfo(Integer id, String name, String imageUrl, String retailerName) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.retailerName = retailerName;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getRetailerName() {
        return retailerName;
    }

    @Override
    public String toString() {
        return "OfferInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", retailerName='" + retailerName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfferInfo offerInfo = (OfferInfo) o;
        return id.equals(offerInfo.id) &&
                name.equals(offerInfo.name) &&
                imageUrl.equals(offerInfo.imageUrl) &&
                retailerName.equals(offerInfo.retailerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, imageUrl, retailerName);
    }
}
