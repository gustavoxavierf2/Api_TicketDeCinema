CREATE TABLE Ticket (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    personName VARCHAR(255) NOT NULL,
    cinemaName VARCHAR(255) NOT NULL,
    movieName VARCHAR(255) NOT NULL,
    hour TIME NOT NULL,
    sessionDate DATE NOT NULL,
    roomNumber INT NOT NULL,
    price FLOAT NOT NULL,
    ticketType VARCHAR(255) NOT NULL,
    seat VARCHAR(255) NOT NULL
);
