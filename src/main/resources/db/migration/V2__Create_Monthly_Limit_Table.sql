CREATE TABLE monthly_limit
(
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    category     VARCHAR(255),
    limit_amount DECIMAL,
    date_set     TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_monthlylimit PRIMARY KEY (id)
);
