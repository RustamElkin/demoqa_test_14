package com.demoqa.pages.components;

import static com.codeborne.selenide.Selenide.$;

public class StateAndCityComponent {
    public void setStateAndCity(String state, String city) {
        $("#react-select-3-input").setValue(state).pressEnter();
        $("#react-select-4-input").setValue(city).pressEnter();
    }
}
