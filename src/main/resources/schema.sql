CREATE TABLE PriemTest (
    id BIGINT IDENTITY PRIMARY KEY,
    kandidaat BIGINT NOT NULL,
    is_priem BIT NOT NULL,
    gebruikersnaam NVARCHAR(255)  NOT NULL,
    datum_tijd TIMESTAMP NOT NULL
);