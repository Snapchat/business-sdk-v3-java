/*
 * Snap Conversions API V3
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 1.0.0
 *
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.snap.business.sdk.v3.model;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.snap.business.sdk.v3.JSON;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/** Content */
@javax.annotation.Generated(
        value = "org.openapitools.codegen.languages.JavaClientCodegen",
        comments = "Generator version: 7.4.0")
public class Content {
    public static final String SERIALIZED_NAME_ID = "id";

    @SerializedName(SERIALIZED_NAME_ID)
    private String id;

    public static final String SERIALIZED_NAME_QUANTITY = "quantity";

    @SerializedName(SERIALIZED_NAME_QUANTITY)
    private String quantity;

    public static final String SERIALIZED_NAME_ITEM_PRICE = "item_price";

    @SerializedName(SERIALIZED_NAME_ITEM_PRICE)
    private String itemPrice;

    public static final String SERIALIZED_NAME_DELIVERY_CATEGORY = "delivery_category";

    @SerializedName(SERIALIZED_NAME_DELIVERY_CATEGORY)
    private String deliveryCategory;

    public static final String SERIALIZED_NAME_BRAND = "brand";

    @SerializedName(SERIALIZED_NAME_BRAND)
    private String brand;

    public Content() {}

    public Content id(String id) {
        this.id = id;
        return this;
    }

    /**
     * The ID of the content.
     *
     * @return id
     */
    @javax.annotation.Nullable
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Content quantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    /**
     * The quantity of the content.
     *
     * @return quantity
     */
    @javax.annotation.Nullable
    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Content itemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
        return this;
    }

    /**
     * The price of the content.
     *
     * @return itemPrice
     */
    @javax.annotation.Nullable
    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Content deliveryCategory(String deliveryCategory) {
        this.deliveryCategory = deliveryCategory;
        return this;
    }

    /**
     * The delivery category of the content.
     *
     * @return deliveryCategory
     */
    @javax.annotation.Nullable
    public String getDeliveryCategory() {
        return deliveryCategory;
    }

    public void setDeliveryCategory(String deliveryCategory) {
        this.deliveryCategory = deliveryCategory;
    }

    public Content brand(String brand) {
        this.brand = brand;
        return this;
    }

    /**
     * The brand of the content item.
     *
     * @return brand
     */
    @javax.annotation.Nullable
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Content content = (Content) o;
        return Objects.equals(this.id, content.id)
                && Objects.equals(this.quantity, content.quantity)
                && Objects.equals(this.itemPrice, content.itemPrice)
                && Objects.equals(this.deliveryCategory, content.deliveryCategory)
                && Objects.equals(this.brand, content.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, itemPrice, deliveryCategory, brand);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Content {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
        sb.append("    itemPrice: ").append(toIndentedString(itemPrice)).append("\n");
        sb.append("    deliveryCategory: ").append(toIndentedString(deliveryCategory)).append("\n");
        sb.append("    brand: ").append(toIndentedString(brand)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces (except the first
     * line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    public static HashSet<String> openapiFields;
    public static HashSet<String> openapiRequiredFields;

    static {
        // a set of all properties/fields (JSON key names)
        openapiFields = new HashSet<String>();
        openapiFields.add("id");
        openapiFields.add("quantity");
        openapiFields.add("item_price");
        openapiFields.add("delivery_category");
        openapiFields.add("brand");

        // a set of required properties/fields (JSON key names)
        openapiRequiredFields = new HashSet<String>();
    }

    /**
     * Validates the JSON Element and throws an exception if issues found
     *
     * @param jsonElement JSON Element
     * @throws IOException if the JSON Element is invalid with respect to Content
     */
    public static void validateJsonElement(JsonElement jsonElement) throws IOException {
        if (jsonElement == null) {
            if (!Content.openapiRequiredFields
                    .isEmpty()) { // has required fields but JSON element is null
                throw new IllegalArgumentException(
                        String.format(
                                "The required field(s) %s in Content is not found in the empty"
                                        + " JSON string",
                                Content.openapiRequiredFields.toString()));
            }
        }

        Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
        // check to see if the JSON string contains additional fields
        for (Map.Entry<String, JsonElement> entry : entries) {
            if (!Content.openapiFields.contains(entry.getKey())) {
                throw new IllegalArgumentException(
                        String.format(
                                "The field `%s` in the JSON string is not defined in the `Content`"
                                        + " properties. JSON: %s",
                                entry.getKey(), jsonElement.toString()));
            }
        }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
        if ((jsonObj.get("id") != null && !jsonObj.get("id").isJsonNull())
                && !jsonObj.get("id").isJsonPrimitive()) {
            throw new IllegalArgumentException(
                    String.format(
                            "Expected the field `id` to be a primitive type in the JSON string but"
                                    + " got `%s`",
                            jsonObj.get("id").toString()));
        }
        if ((jsonObj.get("quantity") != null && !jsonObj.get("quantity").isJsonNull())
                && !jsonObj.get("quantity").isJsonPrimitive()) {
            throw new IllegalArgumentException(
                    String.format(
                            "Expected the field `quantity` to be a primitive type in the JSON"
                                    + " string but got `%s`",
                            jsonObj.get("quantity").toString()));
        }
        if ((jsonObj.get("item_price") != null && !jsonObj.get("item_price").isJsonNull())
                && !jsonObj.get("item_price").isJsonPrimitive()) {
            throw new IllegalArgumentException(
                    String.format(
                            "Expected the field `item_price` to be a primitive type in the JSON"
                                    + " string but got `%s`",
                            jsonObj.get("item_price").toString()));
        }
        if ((jsonObj.get("delivery_category") != null
                        && !jsonObj.get("delivery_category").isJsonNull())
                && !jsonObj.get("delivery_category").isJsonPrimitive()) {
            throw new IllegalArgumentException(
                    String.format(
                            "Expected the field `delivery_category` to be a primitive type in the"
                                    + " JSON string but got `%s`",
                            jsonObj.get("delivery_category").toString()));
        }
        if ((jsonObj.get("brand") != null && !jsonObj.get("brand").isJsonNull())
                && !jsonObj.get("brand").isJsonPrimitive()) {
            throw new IllegalArgumentException(
                    String.format(
                            "Expected the field `brand` to be a primitive type in the JSON string"
                                    + " but got `%s`",
                            jsonObj.get("brand").toString()));
        }
    }

    public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
        @SuppressWarnings("unchecked")
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            if (!Content.class.isAssignableFrom(type.getRawType())) {
                return null; // this class only serializes 'Content' and its subtypes
            }
            final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
            final TypeAdapter<Content> thisAdapter =
                    gson.getDelegateAdapter(this, TypeToken.get(Content.class));

            return (TypeAdapter<T>)
                    new TypeAdapter<Content>() {
                        @Override
                        public void write(JsonWriter out, Content value) throws IOException {
                            JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
                            elementAdapter.write(out, obj);
                        }

                        @Override
                        public Content read(JsonReader in) throws IOException {
                            JsonElement jsonElement = elementAdapter.read(in);
                            validateJsonElement(jsonElement);
                            return thisAdapter.fromJsonTree(jsonElement);
                        }
                    }.nullSafe();
        }
    }

    /**
     * Create an instance of Content given an JSON string
     *
     * @param jsonString JSON string
     * @return An instance of Content
     * @throws IOException if the JSON string is invalid with respect to Content
     */
    public static Content fromJson(String jsonString) throws IOException {
        return JSON.getGson().fromJson(jsonString, Content.class);
    }

    /**
     * Convert an instance of Content to an JSON string
     *
     * @return JSON string
     */
    public String toJson() {
        return JSON.getGson().toJson(this);
    }
}
