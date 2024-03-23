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

CREATE TYPE TypeTransaction AS ENUM ('withdrawal','external_transfer','internal_transfer');
CREATE TABLE IF NOT EXISTS transaction (
    idTransaction SERIAL PRIMARY KEY,
    date DATE NOT NULL,
    reference VARCHAR(200) NOT NULL,
    description VARCHAR(200) NOT NULL,
    debit DOUBLE PRECISION NOT NULL,
    credit DOUBLE PRECISION NOT NULL,
    idAccountSender SERIAL,
    idAccountReceiver SERIAL,
    idTransactionGroup SERIAL,
    CONSTRAINT fk_account_sender_id
    FOREIGN KEY (idAccountSender)
    REFERENCES account(idAccount),
    CONSTRAINT fk_account_receiver_id
    FOREIGN KEY (idAccountReceiver)
    REFERENCES account(idAccount),
    CONSTRAINT fk_transactionGroup_id
    FOREIGN KEY (idTransactionGroup)
    REFERENCES transactionGroup(idTransactionGroup),
    typeTransaction TypeTransaction NOT NULL
    );

