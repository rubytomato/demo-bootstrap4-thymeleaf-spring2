package com.example.demo.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleForm implements Serializable {

    @NotNull
    @Size(min = 2, max = 120)
    private String singleLineText;

    @NotNull
    @Pattern(regexp = "((19|[2-9][0-9])[0-9]{2})/(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])")
    private String textDate;

    @NotNull
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate date;

    @NotNull
    @Digits(integer = 9, fraction = 0)
    private String textNum;

    @NotNull
    @Min(0)
    @Max(999999999)
    private Integer num;

    // Optional
    @Size(min = 10, max = 600)
    private String multiLineText;

    // Optional
    @Email
    private String email;

    @NotNull
    @Size(min = 6, max = 99)
    private String password;

    // Optional
    @Pattern(regexp = "A|B|C|D|E")
    private String singleSelect;

    // Optional
    @Size(min = 0, max = 5, message = "{custom.validation.constraints.SelectSize.message}")
    private String[] multiSelects;

    @Pattern(regexp = "on")
    private String singleCheck;

    @NotNull
    @Size(min = 1, max = 5, message = "{custom.validation.constraints.SelectSize.message}")
    private String[] multiChecks;

    @NotNull
    @Pattern(regexp = "A|B|C|D|E")
    private String radio;

    public String getMultiLineTextNl2br() {
        if (this.multiLineText == null || this.multiLineText.length() == 0) {
            return null;
        }
        return this.multiLineText.replaceAll("\n", "<br/>");
    }

}
