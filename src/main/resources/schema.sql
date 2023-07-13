CREATE TABLE IF NOT EXISTS users (
    id SERIAL NOT NULL PRIMARY KEY,
    username VARCHAR(256) NOT NULL,
    password VARCHAR(256) NOT NULL
);

CREATE TABLE IF NOT EXISTS image_data (
    id SERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(256) NOT NULL,
    type VARCHAR(256) NOT NULL,
   imagedata BYTEA
);

CREATE TABLE IF NOT EXISTS posts (
    id SERIAL NOT NULL PRIMARY KEY,
    title VARCHAR(256) NOT NULL,
    content VARCHAR(256) NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    image_data_id BIGINT,
    FOREIGN KEY (image_data_id) REFERENCES image_data (id)
);

CREATE TABLE IF NOT EXISTS choices (
    id SERIAL NOT NULL PRIMARY KEY,
    choice_content VARCHAR(256) NOT NULL,
　　 count BIGINT,
    post_id BIGINT NOT NULL,
    FOREIGN KEY (post_id) REFERENCES posts (id)
);
