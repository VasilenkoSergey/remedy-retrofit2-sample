package io.vasilenko.remedy.retrofit.sample.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class User {

    @SerializedName("firstName")
    private String firstName;
    @SerializedName("lastName")
    private String lastName;
}
