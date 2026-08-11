package dto;

public class UserBuilder {

    public static UserDTO createDefaultUser() {
        return UserDTO.builder()
                .id(0)
                .username("user_vlad_qa_" + System.currentTimeMillis() % 10000)
                .firstName("Vlad")
                .lastName("QA")
                .email("vlad@test.com")
                .password("MySecurePass123!")
                .phone("123456789")
                .userStatus(0)
                .build();
    }
}