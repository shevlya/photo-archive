package com.photo.photo_archive.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "app_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Email обязателен")
    @Email(message = "Неверный формат email")
    @Pattern(
            regexp = "^(?!\\d)(?!\\d+$)[a-zA-Z0-9._%+-]+@[a-zA-Z]+(\\.[a-zA-Z]+)+$",
            message = "Email должен начинаться с буквы, содержать не только цифры до '@', и не иметь цифр в домене"
    )
    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @NotBlank(message = "Пароль обязателен")
    @Size(min = 4, message = "Пароль должен быть не короче 4 символов")
    @Column(nullable = false, length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Role role = Role.ROLE_USER;

    @NotBlank(message = "Имя обязательно")
    @Pattern(
            regexp = "^([A-Za-z]+|[А-Яа-яЁё]+)$",
            message = "Имя может содержать только буквы одного алфавита — латиницу или кириллицу"
    )
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @NotBlank(message = "Фамилия обязательна")
    @Pattern(
            regexp = "^([A-Za-z]+|[А-Яа-яЁё]+)$",
            message = "Фамилия может содержать только буквы одного алфавита — латиницу или кириллицу"
    )
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;
}
