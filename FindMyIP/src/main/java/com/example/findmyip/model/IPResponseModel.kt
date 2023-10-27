package com.example.findmyip.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class IPResponseModel(
    val asn: String? = null,
    val city: String? = null,
    @Json(name = "continent_code")
    val continentCode: String? = null,
    val country: String? = null,
    @Json(name = "country_area")
    val countryArea: Double? = null,
    @Json(name = "country_calling_code")
    val countryCallingCode: String? = null,
    @Json(name = "country_capital")
    val countryCapital: String? = null,
    @Json(name = "country_code")
    val countryCode: String? = null,
    @Json(name = "country_code_iso3")
    val countryCodeISO3: String? = null,
    @Json(name = "country_name")
    val countryName: String? = null,
    @Json(name = "country_population")
    val countryPopulation: Int? = null,
    @Json(name = "country_tld")
    val countryTLD: String? = null,
    val currency: String? = null,
    @Json(name = "currency_name")
    val currencyName: String? = null,
    @Json(name = "in_eu")
    val inEU: Boolean? = null,
    val ip: String? = null,
    val languages: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val network: String? = null,
    val org: String? = null,
    val postal: String? = null,
    val region: String? = null,
    @Json(name = "region_code")
    val regionCode: String? = null,
    val timezone: String? = null,
    @Json(name = "utc_offset")
    val utcOffset: String? = null,
    val version: String? = null
)