package com.blessy.dclmservice.utils;

import lombok.Getter;

public enum WebPage {

    NOT_FOUND("404"),
    EXCEPTION("exception"),
    LOGIN("login"),
    REGISTER("register"),
    ACCESS_DENIED("error/403"),
    RESET_PASSWORD_FOR_USER("user/reset_password"),

    HOME("index"),

    /**
        PROFILE
     */

    PROFILE("profile"),
    CHANGE_PASSWORD("profile/change_password"),

    /**
     * ADMIN PAGES
     */

    DENOMINATION("admin/denomination/about"),

    CONTINENTS("admin/continents/continents"),
    CONTINENT_COUNTRIES("admin/continents/continent_countries"),
    ADD_CONTINENT_COUNTRY("admin/continents/add_country"),
    ADD_CONTINENT("admin/continents/add_continent"),
    CONTINENT_COUNTRY_STATES("admin/continents/continent_country_states"),
    CONTINENT_COUNTRY_STATE_CITIES("admin/continents/continent_country_state_cities"),

    SERVICE_TYPES("admin/services/service_types"),
    ADD_SERVICE_TYPE("admin/services/add_service_type"),
    EDIT_SERVICE_TYPE("admin/services/edit_service_type");

    @Getter
    private final String pageName;

    WebPage(String pageName) {
        this.pageName = pageName;
    }

    @Override
    public String toString() {
        return getPageName();
    }

}
