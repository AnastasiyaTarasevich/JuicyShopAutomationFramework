package dtos.registration;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum SecurityQuestionEnum {
    ELDEST_SIBLING_MIDDLE_NAME(1, "Your eldest siblings middle name?"),
    MOTHERS_MAIDEN_NAME(2, "Mother's maiden name?"),
    MOTHERS_BIRTH_DATE(3, "Mother's birth date? (MM/DD/YY)"),
    FATHERS_BIRTH_DATE(4, "Father's birth date? (MM/DD/YY)"),
    MATERNAL_GRANDMOTHER_FIRST_NAME(5, "Maternal grandmother's first name?"),
    PATERNAL_GRANDMOTHER_FIRST_NAME(6, "Paternal grandmother's first name?"),
    FAVORITE_PET_NAME(7, "Name of your favorite pet?"),
    DENTIST_LAST_NAME(8, "Last name of dentist when you were a teenager? (Do not include 'Dr.')"),
    TEENAGER_ZIP_CODE(9, "Your ZIP/postal code when you were a teenager?"),
    FIRST_COMPANY_AS_ADULT(10, "Company you first work for as an adult?"),
    FAVORITE_BOOK(11, "Your favorite book?"),
    FAVORITE_MOVIE(12, "Your favorite movie?"),
    CUSTOMER_OR_ID_CARD_NUMBER(13, "Number of one of your customer or ID cards?"),
    FAVORITE_HIKING_PLACE(14, "What's your favorite place to go hiking?");

    @Getter
    private final int id;
    private final String question;

    SecurityQuestionEnum(int id, String question) {
        this.id = id;
        this.question = question;
    }

    @JsonValue
    public String getQuestion() {
        return question;
    }

    public static SecurityQuestionEnum fromId(int id) {
        for (SecurityQuestionEnum q : SecurityQuestionEnum.values()) {
            if (q.getId() == id) {
                return q;
            }
        }
        throw new IllegalArgumentException("No SecurityQuestionEnum with id " + id);
    }
}
