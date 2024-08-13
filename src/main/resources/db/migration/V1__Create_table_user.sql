CREATE TABLE IF NOT EXISTS blank.user
(
    id         UUID PRIMARY KEY,
    name       VARCHAR(120)        NOT NULL,
    username   VARCHAR(255) UNIQUE NOT NULL,
    password   VARCHAR(255)        NOT NULL,
    role       VARCHAR(30)         NOT NULL,
    created_at TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP
);
