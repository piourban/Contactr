/*
 * Copyright Shorif Ali (http://github.com/shorifali/)
 * Licensed under MIT (https://opensource.org/licenses/MIT)
 *
 */

'use strict';

$(document).ready(function() {

    // Default option
    var option = '<option value="0" selected="selected">Wybierz kategorie</option>';

    var subcategories = ['szef', 'klient', 'współpracownik'];
    var test = [];

    // Method to clear dropdowns down to a given level
    var clearDropDown = function(arrayObj, startIndex) {
        $.each(arrayObj, function(index, value) {
            if(index >= startIndex) {
                $(value).html(option);
            }
        });
    };

    // Method to disable dropdowns down to a given level
    var disableDropDown = function(arrayObj, startIndex) {
        $.each(arrayObj, function(index, value) {
            if(index >= startIndex) {
                $(value).css("display", "none");
            }
        });
    };

    // Method to generate and append options
    var generateOptions = function(element, selection, limit) {
        var options = '';
        for(var i = 1; i <= subcategories.length; i++) {
            options += '<option value="' + subcategories[i-1] + '">'+ subcategories[i-1] +'</option>';
        }
        element.append(options);
    };


    // Select each of the dropdowns
    var firstDropDown = $('#first');
    var secondDropDown = $('#second');
    var input = $('#subcategoryInput');

    // Hold selected option
    var firstSelection = '';
    var secondSelection = '';

    // Hold selection
    var selection = '';

    // Selection handler for first level dropdown
    firstDropDown.on('change', function() {

        // Get selected option
        firstSelection = firstDropDown.val();

        // Clear all dropdowns down to the hierarchy
        clearDropDown($('select'), 1);

        // Disable all dropdowns down to the hierarchy
        disableDropDown($('select'), 1);

        // Check current selection
        if(firstSelection === '0') {
            return;
        }

        // Enable second level DropDown
        var categorySelect = document.getElementById("first");
        var selectedText = categorySelect.options[categorySelect.selectedIndex].text;

        if (selectedText === 'służbowy'){
            secondDropDown.show();
            input.hide();
        } else if (selectedText === 'inny') {
            input.show();
            secondDropDown.hide();
        } else {
            input.hide();
            secondDropDown.hide();
        }

        // Generate and append options
        selection = firstSelection;
        generateOptions(secondDropDown, subcategories);
    });

    // Selection handler for second level dropdown
    secondDropDown.on('change', function() {
        secondSelection = secondDropDown.val();

        // Clear all dropdowns down to the hierarchy
        clearDropDown($('select'), 2);

        // Disable all dropdowns down to the hierarchy
        disableDropDown($('select'), 2);

        // Check current selection
        if(secondSelection === '0') {
             return;
        }

    });

    /*
     * In this way we can make any number of dependent dropdowns
     *
     */

});
