package com.devalgas.portofolio.bookstore.resource;

import com.devalgas.portofolio.bookstore.config.SecurityConfig;
import com.devalgas.portofolio.bookstore.config.SecurityUtility;
import com.devalgas.portofolio.bookstore.domain.User;
import com.devalgas.portofolio.bookstore.domain.security.Role;
import com.devalgas.portofolio.bookstore.domain.security.UserRole;
import com.devalgas.portofolio.bookstore.service.UserService;
import com.devalgas.portofolio.bookstore.utility.MailConstructor;
import com.devalgas.portofolio.bookstore.utility.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.*;

/**
 * @author devalgas kamga on 15/01/2022. 16:10
 */
public class UserResource {
    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    private final UserService userService;

    private final MailConstructor mailConstructor;

    private final JavaMailSender mailSender;

    public UserResource(UserService userService, MailConstructor mailConstructor, JavaMailSender mailSender) {
        this.userService = userService;
        this.mailConstructor = mailConstructor;
        this.mailSender = mailSender;
    }


    /**
     * {@code POST  /newUser} :new user post.
     *
     * @param mapper  the mapper of HashMap the retrieve.
     * @param request the request of the HttpServletRequest.
     * @return the {@link ResponseEntity} with status {@code 200 (ok)} and with body the string: User Added Successfully!, or with status {@code 500 (Internal Server Error)} if the user nul.
     * @throws IOException if the signals that an I/O exception to some sort has occurred.
     */
    @PostMapping("/newUser")
    public ResponseEntity<String> newUserPost(HttpServletRequest request, @RequestBody HashMap<String, String> mapper) throws Exception {
        log.debug("REST request to new user : {} ", mapper);
        String username = mapper.get("username");
        String userEmail = mapper.get("email");

        if (userService.findByUsername(username).isPresent()) return ResponseEntity.badRequest().body("usernameExists");
        if (userService.findByEmail(userEmail).isPresent()) return ResponseEntity.badRequest().body("emailExists");

        User user = new User();
        user.setUsername(username);
        user.setEmail(userEmail);

        String password = SecurityUtility.randomPassword();
        String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
        user.setPassword(encryptedPassword);

        Role role = new Role();
        role.setRoleId(1);
        role.setName("ROLE_USER");
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(user, role));
        userService.createUser(user, userRoles);

        SimpleMailMessage email = mailConstructor.constructNewUserEmail(user, password);
        mailSender.send(email);
        return ResponseEntity.ok().body("User Added Successfully!");

    }

    /**
     * {@code POST  /forgetPassword} :forget password.
     *
     * @param mapper  the mapper of HashMap the retrieve.
     * @param request the request of the HttpServletRequest.
     * @return the {@link ResponseEntity} with status {@code 200 (ok)} and with body the string: Email sent!, or with status {@code 500 (Internal Server Error)} if the user nul.
     * @throws IOException if the signals that an I/O exception to some sort has occurred.
     */
    @PostMapping("/forgetPassword")
    public ResponseEntity<String> forgetPasswordPost(HttpServletRequest request, @RequestBody HashMap<String, String> mapper) throws Exception {
        log.debug("REST request to forget password: {} ", mapper);

        User user = userService.findByEmail(mapper.get("email")).orElseThrow(() -> new Exception("user not found"));

        String password = SecurityUtility.randomPassword();

        String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
        user.setPassword(encryptedPassword);
        userService.save(user);

        SimpleMailMessage newEmail = mailConstructor.constructNewUserEmail(user, password);
        mailSender.send(newEmail);
        return ResponseEntity.ok().body("Email sent!");

    }

    /**
     * {@code POST  /updateUserInfo} :profile info.
     *
     * @param mapper the mapper of HashMap the retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (ok)} and with body the string: Update Success, or with status {@code 500 (Internal Server Error)} if the user nul.
     * @throws IOException if the signals that an I/O exception to some sort has occurred.
     */
    @PostMapping("/updateUserInfo")
    public ResponseEntity<String> profileInfo(@RequestBody HashMap<String, Object> mapper) throws Exception {
        log.debug("REST request to profile Info : {} ", mapper);

        int id = (Integer) mapper.get("id");
        String email = (String) mapper.get("email");
        String username = (String) mapper.get("username");
        String firstName = (String) mapper.get("firstName");
        String lastName = (String) mapper.get("lastName");
        String newPassword = (String) mapper.get("newPassword");
        String currentPassword = (String) mapper.get("currentPassword");

        User currentUser = userService.findOne((long) id).orElse(null);

        if (currentUser == null) throw new Exception("User not found");

        Optional<User> byEmail = userService.findByEmail(email);
        if (byEmail.isPresent()) {
            if (!Objects.equals(byEmail.get().getId(), currentUser.getId()))
                return ResponseEntity.badRequest().body("Email not found");
        }

        Optional<User> byUsername = userService.findByUsername(username);
        if (byUsername.isPresent()) {
            if (!Objects.equals(byUsername.get().getId(), currentUser.getId()))
                return ResponseEntity.badRequest().body("Username not found!");
        }

        SecurityConfig securityConfig = new SecurityConfig();

        BCryptPasswordEncoder passwordEncoder = SecurityUtility.passwordEncoder();
        String dbPassword = currentUser.getPassword();

        if (null != currentPassword)
            if (passwordEncoder.matches(currentPassword, dbPassword)) {
                if (newPassword != null && !newPassword.isEmpty()) {
                    currentUser.setPassword(passwordEncoder.encode(newPassword));
                }
                currentUser.setEmail(email);
            } else {
                return ResponseEntity.badRequest().body("Incorrect current password!");
            }

        currentUser.setFirstName(firstName);
        currentUser.setLastName(lastName);
        currentUser.setUsername(username);

        userService.save(currentUser);
        return ResponseEntity.ok().body("Update Success");
    }

    /**
     * {@code POST  /updateUserInfo} :get current user.
     *
     * @param principal the principal of the Principal.
     * @return the {@link ResponseEntity} with status {@code 200 (ok)} and with body the string: Update Success, or with status {@code 500 (Internal Server Error)} if the user nul.
     */
    @RequestMapping("/getCurrentUser")
    public ResponseEntity<User> getCurrentUser(Principal principal) {
        User user = new User();
        if (null != principal) {
            user = userService.findByUsername(principal.getName()).orElse(null);
        }
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(user));
    }
}
