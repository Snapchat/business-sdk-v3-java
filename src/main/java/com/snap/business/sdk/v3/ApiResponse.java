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

package com.snap.business.sdk.v3;


import java.util.List;
import java.util.Map;

/** API response returned by API call. */
public class ApiResponse<T> {
    private final int statusCode;
    private final Map<String, List<String>> headers;
    private final T data;

    /**
     * Constructor for ApiResponse.
     *
     * @param statusCode The status code of HTTP response
     * @param headers The headers of HTTP response
     */
    public ApiResponse(int statusCode, Map<String, List<String>> headers) {
        this(statusCode, headers, null);
    }

    /**
     * Constructor for ApiResponse.
     *
     * @param statusCode The status code of HTTP response
     * @param headers The headers of HTTP response
     * @param data The object deserialized from response bod
     */
    public ApiResponse(int statusCode, Map<String, List<String>> headers, T data) {
        this.statusCode = statusCode;
        this.headers = headers;
        this.data = data;
    }

    /**
     * Get the <code>status code</code>.
     *
     * @return the status code
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Get the <code>headers</code>.
     *
     * @return a {@link java.util.Map} of headers
     */
    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    /**
     * Get the <code>data</code>.
     *
     * @return the data
     */
    public T getData() {
        return data;
    }
}
