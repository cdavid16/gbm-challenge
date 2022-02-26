CREATE DATABASE IF NOT EXISTS gbm_challenge;

use gbm_challenge;

CREATE TABLE IF NOT EXISTS tbl_account(account_id varchar(36) primary key, balance decimal(9, 2));

CREATE TABLE IF NOT EXISTS tbl_issuer(issuer_id varchar(36) primary key, name varchar(16));

CREATE TABLE IF NOT EXISTS tbl_account_stock(account_id varchar(36) REFERENCES TblAccount(account_id),
                                        issuer_id varchar(36) REFERENCES TblIssuer(issuer_id),
                                        quantity bigint,
                                        PRIMARY KEY (account_id, issuer_id));

CREATE TABLE IF NOT EXISTS tbl_transaction(transaction_id varchar(36) primary key,
                                        account_id varchar(36) REFERENCES TblAccount(account_id),
                                        timestmp BIGINT, success boolean);

CREATE TABLE IF NOT EXISTS tbl_transaction_detail(transaction_id varchar(36) references TblTransaction(transaction_id),
                                        issuer_id varchar(36) references TblIssuer(issuer_id),
                                        operation varchar(8), quantity bigint, price decimal(9, 2),
                                        check (operation in ('BUY','SELL')));