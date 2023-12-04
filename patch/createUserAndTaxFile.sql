-- Create User table
CREATE TABLE Users (
                       UserID SERIAL PRIMARY KEY,
                       Username VARCHAR(255) UNIQUE NOT NULL,
                       Password VARCHAR(255) NOT NULL,
                       Email VARCHAR(255) UNIQUE NOT NULL
);

-- Create TaxFilers table
CREATE TABLE TaxFilers (
                           FilerID SERIAL PRIMARY KEY,
                           Name VARCHAR(255) NOT NULL,
                           Contact VARCHAR(255),
                           AnnualIncome DECIMAL,
                           Expenses DECIMAL,
                           TaxYear INTEGER,
                           UserID INTEGER REFERENCES Users(UserID)
);