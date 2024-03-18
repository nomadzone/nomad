

CREATE TABLE `user` (
                        id BIGINT NOT NULL AUTO_INCREMENT,
                        open_id VARCHAR(255),
                        union_id VARCHAR(255),
                        nickname VARCHAR(100),
                        signature VARCHAR(255),
                        gender BOOLEAN,
                        phone VARCHAR(20),
                        email VARCHAR(20),
                        description VARCHAR(20),
                        create_at BIGINT,
                        update_at BIGINT,
                        PRIMARY KEY (id)
);

CREATE TABLE fan (
                     id BIGINT NOT NULL AUTO_INCREMENT,
                     user_id BIGINT,
                     fan_id BIGINT,
                     FOREIGN KEY (user_id) REFERENCES `user` (id),
                     FOREIGN KEY (fan_id) REFERENCES `user` (id),
                     PRIMARY KEY (id)
);

CREATE TABLE posts (
                       id BIGINT NOT NULL AUTO_INCREMENT,
                       user_id BIGINT,
                       title VARCHAR(255),
                       content TEXT,
                       resources TEXT,
                       created_at BIGINT,
                       updated_at BIGINT,
                       latitude FLOAT,
                       longitude FLOAT,
                       location_name VARCHAR(255),
                       image_url VARCHAR(255),
                       FOREIGN KEY (user_id) REFERENCES `user` (id),
                       PRIMARY KEY (id)
);

CREATE TABLE likes (
                       id BIGINT NOT NULL AUTO_INCREMENT,
                       post_id BIGINT,
                       user_id BIGINT,
                       created_at BIGINT,
                       FOREIGN KEY (post_id) REFERENCES posts (id),
                       FOREIGN KEY (user_id) REFERENCES `user` (id),
                       PRIMARY KEY (id)
);

CREATE TABLE comments (
                          id BIGINT NOT NULL AUTO_INCREMENT,
                          post_id BIGINT,
                          user_id BIGINT,
                          content TEXT,
                          created_at BIGINT,
                          status BOOLEAN,
                          FOREIGN KEY (post_id) REFERENCES posts (id),
                          FOREIGN KEY (user_id) REFERENCES `user` (id),
                          PRIMARY KEY (id)
);

CREATE TABLE sub_comments (
                              id BIGINT NOT NULL AUTO_INCREMENT,
                              comment_id BIGINT,
                              user_id BIGINT,
                              content TEXT,
                              created_at BIGINT,
                              FOREIGN KEY (comment_id) REFERENCES comments (id),
                              FOREIGN KEY (user_id) REFERENCES `user` (id),
                              PRIMARY KEY (id)
);

