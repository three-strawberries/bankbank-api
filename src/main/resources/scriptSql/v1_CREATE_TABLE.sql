CREATE TABLE IF NOT EXISTS balance (
       idBalance SERIAL PRIMARY KEY,
       amount DOUBLE PRECISION NOT NULL,
       date Date NOT NULL,
       type VARCHAR(150) NOT NULL
);

CREATE TABLE IF NOT EXISTS account (
    idAccount SERIAL PRIMARY KEY,
    lastName VARCHAR(200) NOT NULL,
    firstName VARCHAR(200) NOT NULL,
    birthDate Date NOT NULL,
    monthlySalary Double PRECISION NOT NULL,
    authorizedCredit VARCHAR(150) NOT NULL,
    bankName VARCHAR(200) NOT NULL,
    idBalance SERIAL,
    CONSTRAINT fk_balance_id
    FOREIGN KEY (idBalance)
    REFERENCES balance(idBalance)

);
CREATE TABLE IF NOT EXISTS transactionGroup (
    idTransactionGroup SERIAL PRIMARY KEY,
    date Date NOT NULL,
    description VARCHAR(200) NOT NULL,
    method VARCHAR(200) NOT NULL,
    idAccountSender SERIAL,
    CONSTRAINT fk_account_sender_id
    FOREIGN KEY (idAccountSender)
    REFERENCES account(idAccount)

    );
