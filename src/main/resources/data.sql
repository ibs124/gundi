INSERT INTO user_roles (id, type) VALUES
(1, 'USER'), (2, 'ADMIN'), (3, 'ROOT');

INSERT INTO users (id, username, email, full_name, password) VALUES
(1, 'aegon', 'aegon.targaryen@example.com', 'Aegon Targaryen', 'rootpass'),
(2, 'tywin', 'tywin.lannister@example.com', 'Tywin Lannister', 'adminpass'),
(3, 'ned', 'ned.stark@example.com', 'Eddard Stark', 'adminpass'),
(4, 'arya', 'arya.stark@example.com', 'Arya Stark', 'password'),
(5, 'jon', 'jon.snow@example.com', 'Jon Snow', 'password'),
(6, 'daenerys', 'daenerys.targaryen@example.com', 'Daenerys Targaryen', 'password');

INSERT INTO users_roles (user_id, role_id) VALUES
(1, 3), (2, 2), (3, 2), (4, 1), (5, 1), (6, 1);