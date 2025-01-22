package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString

//"id": "string",
//        "name": "string",
//        "lastName": "string",
//        "email": "string",
//        "phone": "7779256054",
//        "address": "string",
//        "description": "string"


public class ContactDtoLombok {
    private String id;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String description;
}
