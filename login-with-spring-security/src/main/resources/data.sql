INSERT INTO app_user(
            id, name, username, password, authority)
    VALUES 
    (1, 'Test User 1', 'test.user1@xyz.com', '$2a$12$5clbbuNf1GSmnO/UW8bSluQJmxKEPB4neav9zd7oTOsMKYcOMtaUu', 'ROLE_USER'),
    (2, 'Test User 2', 'test.user2@xyz.com', '$2a$12$5clbbuNf1GSmnO/UW8bSluQJmxKEPB4neav9zd7oTOsMKYcOMtaUu', 'ROLE_ADMIN')
    ;
COMMIT;

INSERT INTO role(
            id, name)
    VALUES
    (1, 'ROLE_USER'),
    (2, 'ROLE_ADMIN')
    ;
COMMIT;

INSERT INTO app_user_roles (
        users_id, roles_id)
     VALUES
     (1, 1),
     (2, 2)
     ;
COMMIT;